import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Scanner;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class InstallCerts {

	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			System.out.println("Usage: java InstallCerts <fileName>");
			System.out.println("Fichier = liste de <HOST>[:<PORT>][:<PASSWORD>]");
			return;
		}

		Scanner scanner = new Scanner(new File(args[0]));
		while (scanner.hasNextLine()) {
			String argument = scanner.nextLine().trim();
			if (argument.length() > 0 && !argument.startsWith("#")) {
				String[] c = argument.split(":");
				String host = c[0];
				int port = (c.length > 1) ? Integer.parseInt(c[1]) : 443;
				String p = (c.length > 2) ? c[2] : "changeit";
				installCert(host, port, p.toCharArray());
			}
		}
	}

    /**
     * Ajoute le certificat de l'hôte distant à la liste des certificats
     * Edite le fichier %JAVA_HOME%/lib/security/jssecacerts
     * @param host l'hôte
     * @param port le port
     * @param passphrase le mot de passe
     * @throws Exception
     */
	private static void installCert(String host, int port, char[] passphrase) throws Exception {

		File dir = new File(System.getProperty("java.home") + "/lib/security");
		File file = new File(dir, "jssecacerts");
		if (file.isFile() == false) {
			file = new File(dir, "cacerts");
		}

		System.out.println("Loading KeyStore " + file + "...");
		InputStream in = new FileInputStream(file);
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		ks.load(in, passphrase);
		in.close();

		SSLContext context = SSLContext.getInstance("TLS");
		TrustManagerFactory tmf = TrustManagerFactory
				.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmf.init(ks);
		X509TrustManager defaultTrustManager = (X509TrustManager) tmf.getTrustManagers()[0];
		SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
		context.init(null, new TrustManager[] { tm }, null);
		SSLSocketFactory factory = context.getSocketFactory();

		System.out.println("Opening connection to " + host + ":" + port + "...");
		SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
		socket.setSoTimeout(10000);
		try {
			System.out.println("Starting SSL handshake...");
			socket.startHandshake();
			socket.close();
			System.out.println();
			System.out.println("No errors, certificate is already trusted");
			return;
		} catch (SSLException e) {
		}

		X509Certificate[] chain = tm.chain;
		if (chain == null) {
			System.out.println("Could not obtain server certificate chain");
			return;
		}

		System.out.println();
		System.out.println("Server sent " + chain.length + " certificate(s):");
		System.out.println();
		MessageDigest sha1 = MessageDigest.getInstance("SHA1");
		System.out.println("   sha1    " + toHexString(sha1.digest()));
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		System.out.println("   md5     " + toHexString(md5.digest()));

		OutputStream out = new FileOutputStream(dir + "/jssecacerts");
		int k = 1;
		for (X509Certificate cert : chain) {
			System.out.println(" " + k + " Subject " + cert.getSubjectDN());
			System.out.println("   Issuer  " + cert.getIssuerDN());
			sha1.update(cert.getEncoded());
			md5.update(cert.getEncoded());
			System.out.println();

			String alias = host + "-" + (k++);
			ks.setCertificateEntry(alias, cert);
			ks.store(out, passphrase);
			System.out.println();
			System.out.println(cert);
			System.out.println();
			System.out.println("Added certificate to keystore 'jssecacerts' using alias '" + alias + "'");
		}
		out.close();
	}

	private static final char[] HEXDIGITS = "0123456789abcdef".toCharArray();

	private static String toHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * 3);
		for (int b : bytes) {
			b &= 0xff;
			sb.append(HEXDIGITS[b >> 4]);
			sb.append(HEXDIGITS[b & 15]);
			sb.append(' ');
		}
		return sb.toString();
	}

	private static class SavingTrustManager implements X509TrustManager {

		private final X509TrustManager tm;
		private X509Certificate[] chain;

		SavingTrustManager(X509TrustManager tm) {
			this.tm = tm;
		}

		public X509Certificate[] getAcceptedIssuers() {
			throw new UnsupportedOperationException();
		}

		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			throw new UnsupportedOperationException();
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			this.chain = chain;
			tm.checkServerTrusted(chain, authType);
		}
	}
}
