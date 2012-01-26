package fr.paris.lutece.plugins.stock.business.offer;

import fr.paris.lutece.plugins.stock.business.attribute.offer_genre.OfferGenreAttribute;
import fr.paris.lutece.plugins.stock.business.attribute.offer_genre.OfferGenreAttributeDate;
import fr.paris.lutece.plugins.stock.business.attribute.offer_genre.OfferGenreAttributeNum;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OfferGenre.class)
public abstract class OfferGenre_ {

	public static volatile SingularAttribute<OfferGenre, Integer> id;
	public static volatile ListAttribute<OfferGenre, OfferGenreAttributeNum> attributeNumList;
	public static volatile ListAttribute<OfferGenre, OfferGenreAttribute> attributeList;
	public static volatile SingularAttribute<OfferGenre, String> name;
	public static volatile ListAttribute<OfferGenre, OfferGenreAttributeDate> attributeDateList;
    public static volatile ListAttribute<OfferGenre, Offer> offersList;

}

