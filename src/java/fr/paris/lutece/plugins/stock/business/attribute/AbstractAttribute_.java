package fr.paris.lutece.plugins.stock.business.attribute;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


/**
 * Metamodel for AbstractAttribute
 * 
 * @author abataille
 */
@StaticMetamodel( AbstractAttribute.class )
public abstract class AbstractAttribute_
{

    public static volatile SingularAttribute<AbstractAttribute<?>, Integer> owner;
    public static volatile SingularAttribute<AbstractAttribute<?>, String> value;
    // public static volatile SingularAttribute<AbstractAttributeNum, String>
    // key;
    // Ne fonctionne pas bug hibernate : HHH-5024
    public static volatile String key = "key";

}
