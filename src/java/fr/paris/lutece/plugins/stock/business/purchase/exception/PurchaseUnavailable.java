package fr.paris.lutece.plugins.stock.business.purchase.exception;

/**
 * General exception for forbidden purchase
 * @author abataille
 */
public class PurchaseUnavailable extends PurchaseException
{
    /**  
     *
     */
    private static final long serialVersionUID = -52377360163133736L;

    private Integer _purchaseId;

    /**
     * Instantiates a new purchase unavailable.
     * 
     * @param purchaseId the purchase id
     * @param msg the msg
     */
    public PurchaseUnavailable( Integer purchaseId, String msg )
    {
        super( purchaseId, msg );
        this._purchaseId = purchaseId;
    }
    /**
     * @return the purchaseId
     */
    public Integer getPurchaseId( )
    {
        return _purchaseId;
    }
    /**
     * @param purchaseId the purchaseId to set
     */
    public void setPurchaseId( Integer purchaseId )
    {
        this._purchaseId = purchaseId;
    }

}
