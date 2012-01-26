package fr.paris.lutece.plugins.stock.business.purchase.exception;

/**
 * Exception thrown when purchase have not active session
 */
public class PurchaseSessionExpired extends PurchaseException
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6444192762443019402L;

    /** The purchase id. */
    private Integer _purchaseId;

    /**
     * Instantiates a new purchase session expired.
     * 
     * @param purchaseId the purchase id
     * @param msg the msg
     */
    public PurchaseSessionExpired( Integer purchaseId, String msg )
    {
        super( purchaseId, msg );
        this._purchaseId = purchaseId;
    }

    /**
     * Gets the purchase id.
     * 
     * @return the purchaseId
     */
    public Integer getPurchaseId( )
    {
        return _purchaseId;
    }

    /**
     * Sets the purchase id.
     * 
     * @param purchaseId the purchaseId to set
     */
    public void setPurchaseId( Integer purchaseId )
    {
        this._purchaseId = purchaseId;
    }

}
