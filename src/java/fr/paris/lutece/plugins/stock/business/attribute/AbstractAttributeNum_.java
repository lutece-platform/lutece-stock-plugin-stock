package fr.paris.lutece.plugins.stock.business.attribute;

import java.math.BigDecimal;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


/**
 * Metamodel for AbstractAttributeNum
 * 
 * @author abataille
 */
@StaticMetamodel(AbstractAttributeNum.class)
public abstract class AbstractAttributeNum_ {

    public static volatile SingularAttribute<AbstractAttributeNum<?>, Integer> owner;
    public static volatile SingularAttribute<AbstractAttributeNum<?>, BigDecimal> value;
    // public static volatile SingularAttribute<AbstractAttributeNum, String>
    // key;
    // Ne fonctionne pas bug hibernate : HHH-5024
    public static volatile String key = "key";

}

