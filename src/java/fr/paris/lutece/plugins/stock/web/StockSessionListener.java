package fr.paris.lutece.plugins.stock.web;

import fr.paris.lutece.plugins.stock.service.PurchaseSessionManager;
import fr.paris.lutece.portal.service.spring.SpringContextService;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


/**
 * Session listener for releasing all unconfirmed purchases on session expire
 * 
 * @author abataille
 */
public class StockSessionListener implements HttpSessionListener
{


    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet
     * .http.HttpSessionEvent)
     */
    /**
     * Call when session expire. Release all currents purchases.
     * @param se session event
     */
    public void sessionDestroyed( HttpSessionEvent se )
    {

        HttpSession session = se.getSession( );
        String sid = session.getId( );
        PurchaseSessionManager purchaseSessionManager = (PurchaseSessionManager) SpringContextService.getContext( )
                .getBean( PurchaseSessionManager.class );
        synchronized ( this )
        {
            purchaseSessionManager.releaseAll( sid );
        }
    }

    /**
     * Unimplemented
     * @param se session event
     */
    public void sessionCreated( HttpSessionEvent se )
    {

    }
}
