package fr.paris.lutece.plugins.stock.business.offer;

import fr.paris.lutece.plugins.stock.business.attribute.offer.OfferAttribute;
import fr.paris.lutece.plugins.stock.business.attribute.offer.OfferAttributeDate;
import fr.paris.lutece.plugins.stock.business.attribute.offer.OfferAttributeNum;
import fr.paris.lutece.plugins.stock.business.product.Product;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


@StaticMetamodel( Offer.class )
public abstract class Offer_
{

    public static volatile SingularAttribute<Offer, Product> product;
    public static volatile SingularAttribute<Offer, Integer> id;
    public static volatile SetAttribute<Offer, OfferAttributeNum> attributeNumList;
    public static volatile SingularAttribute<Offer, String> statut;
    public static volatile SetAttribute<Offer, OfferAttribute> attributeList;
    public static volatile SingularAttribute<Offer, String> description;
    public static volatile SingularAttribute<Offer, String> name;
    public static volatile SingularAttribute<Offer, Integer> quantity;
    public static volatile SetAttribute<Offer, OfferAttributeDate> attributeDateList;
    public static volatile SingularAttribute<Offer, OfferGenre> type;
    public static volatile SingularAttribute<Offer, Integer> minTickets;
    public static volatile SingularAttribute<Offer, Integer> maxTickets;

}
