package fr.paris.lutece.plugins.stock.business.subscription;

import fr.paris.lutece.plugins.stock.business.product.Product;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


@StaticMetamodel( SubscriptionProduct.class )
public abstract class SubscriptionProduct_
{

    public static volatile SingularAttribute<SubscriptionProduct, Integer> id;
    public static volatile SingularAttribute<SubscriptionProduct, String> userName;
    public static volatile SingularAttribute<SubscriptionProduct, Product> product;

}
