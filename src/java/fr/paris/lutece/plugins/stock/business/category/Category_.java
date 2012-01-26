package fr.paris.lutece.plugins.stock.business.category;

import fr.paris.lutece.plugins.stock.business.attribute.category.CategoryAttribute;
import fr.paris.lutece.plugins.stock.business.attribute.category.CategoryAttributeDate;
import fr.paris.lutece.plugins.stock.business.attribute.category.CategoryAttributeNum;
import fr.paris.lutece.plugins.stock.business.product.Product;
import fr.paris.lutece.plugins.stock.business.provider.Provider;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Category.class)
public abstract class Category_ {

	public static volatile SingularAttribute<Category, Integer> id;
	public static volatile SetAttribute<Category, CategoryAttributeNum> attributeNumList;
	public static volatile SetAttribute<Category, CategoryAttribute> attributeList;
	public static volatile SingularAttribute<Category, String> description;
	public static volatile SingularAttribute<Category, String> name;
	public static volatile SingularAttribute<Category, Integer> hashCode;
	public static volatile SingularAttribute<Category, Category> parent;
	public static volatile SingularAttribute<Category, Provider> provider;
    public static volatile SetAttribute<Category, Category> childrenList;
	public static volatile SetAttribute<Category, CategoryAttributeDate> attributeDateList;
	public static volatile ListAttribute<Category, Product> productSet;

}

