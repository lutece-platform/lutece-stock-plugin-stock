package fr.paris.lutece.plugins.stock.business.purchase.exception;

/**
 * Exception thrown when quantity available for offer is not enough
 */
public class PurchaseOutOfStock extends PurchaseException
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -52377360163133736L;
    /** The purchase id. */
    private Integer _purchaseId;

    /** The quantity requested. */
    private Integer _quantityRequested;

    /** The quantity available. */
    private Integer _quantityAvailable;

    /**
     * Instantiates a new purchase out of stock.
     * 
     * @param purchaseId the purchase id
     * @param msg the msg
     */
    public PurchaseOutOfStock( Integer purchaseId, String msg )
    {
        super( purchaseId, msg );
        this._purchaseId = purchaseId;
    }

    /**
     * Instantiates a new purchase out of stock.
     * 
     * @param purchaseId the purchase id
     * @param quantityRequested the quantity requested
     * @param quantityAvailable the quantity available
     * @param msg the msg
     */
    public PurchaseOutOfStock( Integer purchaseId, Integer quantityRequested, Integer quantityAvailable, String msg )
    {
        super( purchaseId, msg );
        this._purchaseId = purchaseId;
        this.setQuantityRequested( quantityRequested );
        this.setQuantityAvailable( quantityAvailable );
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

    /**
     * Gets the quantity requested.
     * 
     * @return the quantityRequested
     */
    public Integer getQuantityRequested( )
    {
        return _quantityRequested;
    }

    /**
     * Sets the quantity requested.
     * 
     * @param quantityRequested the quantityRequested to set
     */
    public void setQuantityRequested( Integer quantityRequested )
    {
        this._quantityRequested = quantityRequested;
    }

    /**
     * Gets the quantity available.
     * 
     * @return the quantityAvailable
     */
    public Integer getQuantityAvailable( )
    {
        return _quantityAvailable;
    }

    /**
     * Sets the quantity available.
     * 
     * @param quantityAvailable the quantityAvailable to set
     */
    public void setQuantityAvailable( Integer quantityAvailable )
    {
        this._quantityAvailable = quantityAvailable;
    }

}
