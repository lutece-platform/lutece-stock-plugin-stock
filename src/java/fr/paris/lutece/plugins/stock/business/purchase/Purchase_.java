package fr.paris.lutece.plugins.stock.business.purchase;

import fr.paris.lutece.plugins.stock.business.attribute.purchase.PurchaseAttribute;
import fr.paris.lutece.plugins.stock.business.attribute.purchase.PurchaseAttributeDate;
import fr.paris.lutece.plugins.stock.business.attribute.purchase.PurchaseAttributeNum;
import fr.paris.lutece.plugins.stock.business.offer.Offer;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


@StaticMetamodel( Purchase.class )
public abstract class Purchase_
{

    public static volatile SingularAttribute<Purchase, Integer> id;
    public static volatile SetAttribute<Purchase, PurchaseAttributeNum> attributeNumList;
    public static volatile SetAttribute<Purchase, PurchaseAttribute> attributeList;
    public static volatile SingularAttribute<Purchase, String> userName;
    public static volatile SingularAttribute<Purchase, Integer> quantity;
    public static volatile SingularAttribute<Purchase, Offer> offer;
    public static volatile SetAttribute<Purchase, PurchaseAttributeDate> attributeDateList;

}
