package fr.paris.lutece.plugins.stock.service;

import fr.paris.lutece.plugins.stock.business.purchase.IPurchaseDTO;
import fr.paris.lutece.plugins.stock.business.purchase.exception.PurchaseException;


/**
 * Interface implemented by class that check business rules.
 */
public interface IPurchaseRules
{

    /**
     * Called before to save the purchase. Should be used for checking if
     * purchase is allow.
     * 
     * @param purchase the purchase
     * @param sessionId the session id
     * @throws PurchaseException the purchase exception
     */
    void checkBeforePurchase( IPurchaseDTO purchase, String sessionId ) throws PurchaseException;
}
