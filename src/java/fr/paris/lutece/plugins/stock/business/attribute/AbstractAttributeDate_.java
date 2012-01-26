package fr.paris.lutece.plugins.stock.business.attribute;

import java.sql.Timestamp;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


/**
 * Metamodel for AbstractAttributeDate
 * 
 * @author abataille
 */
@StaticMetamodel(AbstractAttributeDate.class)
public abstract class AbstractAttributeDate_ {

    public static volatile SingularAttribute<AbstractAttributeDate<?>, Integer> owner;
    public static volatile SingularAttribute<AbstractAttributeDate<?>, Timestamp> value;
    // public static volatile SingularAttribute<AbstractAttributeDate, String>
    // key;
    // Ne fonctionne pas bug hibernate : HHH-5024
    public static volatile String key = "key";

}

