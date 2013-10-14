--
-- Structure de la table 'stock_product_subscription_user'
--

CREATE TABLE IF NOT EXISTS stock_product_subscription_user (
  id int(11) NOT NULL,
  userName varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  id_product int(11) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


--
-- Contraintes pour la table `stock_product_subscription_user`
--
ALTER TABLE `stock_product_subscription_user`
  ADD CONSTRAINT stock_product_subscription_user_ibfk_1 FOREIGN KEY (id_product) REFERENCES stock_product (id_product);