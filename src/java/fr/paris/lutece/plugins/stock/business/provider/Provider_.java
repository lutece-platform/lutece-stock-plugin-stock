package fr.paris.lutece.plugins.stock.business.provider;

import fr.paris.lutece.plugins.stock.business.attribute.provider.ProviderAttribute;
import fr.paris.lutece.plugins.stock.business.attribute.provider.ProviderAttributeDate;
import fr.paris.lutece.plugins.stock.business.attribute.provider.ProviderAttributeNum;
import fr.paris.lutece.plugins.stock.business.category.Category;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


@StaticMetamodel( Provider.class )
public abstract class Provider_
{

    public static volatile SingularAttribute<Provider, Integer> id;
    public static volatile SingularAttribute<Provider, String> mail;
    public static volatile SetAttribute<Provider, ProviderAttributeNum> attributeNumList;
    public static volatile SetAttribute<Provider, ProviderAttribute> attributeList;
    public static volatile SingularAttribute<Provider, String> phoneNumber;
    public static volatile SingularAttribute<Provider, String> address;
    public static volatile SingularAttribute<Provider, String> contactName;
    public static volatile SingularAttribute<Provider, String> name;
    public static volatile SetAttribute<Provider, ProviderAttributeDate> attributeDateList;
    public static volatile SingularAttribute<Provider, String> comment;
    public static volatile ListAttribute<Provider, Category> products;

}
