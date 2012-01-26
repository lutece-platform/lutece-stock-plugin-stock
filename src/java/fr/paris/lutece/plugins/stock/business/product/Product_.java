package fr.paris.lutece.plugins.stock.business.product;

import fr.paris.lutece.plugins.stock.business.attribute.product.ProductAttribute;
import fr.paris.lutece.plugins.stock.business.attribute.product.ProductAttributeDate;
import fr.paris.lutece.plugins.stock.business.attribute.product.ProductAttributeNum;
import fr.paris.lutece.plugins.stock.business.category.Category;
import fr.paris.lutece.plugins.stock.business.provider.Provider;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


@StaticMetamodel( Product.class )
public abstract class Product_
{

    public static volatile SingularAttribute<Product, Integer> id;
    public static volatile SetAttribute<Product, ProductAttributeNum> attributeNumList;
    public static volatile SingularAttribute<Product, Category> category;
    public static volatile SingularAttribute<Product, Float> price;
    public static volatile SetAttribute<Product, ProductAttribute> attributeList;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, Provider> provider;
    public static volatile SetAttribute<Product, ProductAttributeDate> attributeDateList;

}
