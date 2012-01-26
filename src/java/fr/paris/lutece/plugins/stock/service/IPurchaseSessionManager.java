package fr.paris.lutece.plugins.stock.service;

import fr.paris.lutece.plugins.stock.business.purchase.IPurchaseDTO;
import fr.paris.lutece.plugins.stock.business.purchase.exception.PurchaseSessionExpired;
import fr.paris.lutece.plugins.stock.business.purchase.exception.PurchaseUnavailable;


/**
 * Manager for purchase session.
 * Contains methods for managing the purchase mechanism :
 * - reserve an offer into session
 * - check if purchase is into session just before store it in database
 * - release that
 * 
 * @author abataille
 */
public interface IPurchaseSessionManager
{

    /**
     * Reserves an offer quantity for a session.
     * Check if offer is available (quantity remaining in database + purchases
     * in session) and add purchase in session
     * @param sessionId session id
     * @param purchase purchase (offer id, quantity and user)
     * @throws PurchaseUnavailable exception thrown if purchase is impossible
     */
    void reserve( String sessionId, IPurchaseDTO purchase ) throws PurchaseUnavailable;

    /**
     * Check that purchase has been reserved before to save it.
     * 
     * @param sessionId session id
     * @param purchase purchase
     * @throws PurchaseSessionExpired the purchase session expired
     */
    void checkReserved( String sessionId, IPurchaseDTO purchase ) throws PurchaseSessionExpired;

    /**
     * Remove purchase from session
     * @param sessionId session id
     * @param purchase purchase
     */
    void release( String sessionId, IPurchaseDTO purchase );

    /**
     * Remove all active purchases for a session id
     * @param sessionId session id
     */
    void releaseAll( String sessionId );

}