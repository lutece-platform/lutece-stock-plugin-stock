package fr.paris.lutece.plugins.stock.business.purchase.exception;

/**
 * Exceptions thrown while purchase procedure
 */
public class PurchaseException extends Exception
{
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -52377360163133736L;

    /** The purchase id. */
    private Integer _purchaseId;

    /**
     * Instantiates a new purchase exception.
     * 
     * @param purchaseId the purchase id
     * @param msg the msg
     */
    public PurchaseException( Integer purchaseId, String msg )
    {
        super( msg );
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
     * @param thePurchaseId the purchase id to set
     */
    public void setPurchaseId( Integer thePurchaseId )
    {
        this._purchaseId = thePurchaseId;
    }

}
