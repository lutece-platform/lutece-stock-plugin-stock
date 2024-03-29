/*
 * Copyright (c) 2002-2020, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.stock.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import fr.paris.lutece.plugins.stock.business.attribute.AbstractAttribute;
import fr.paris.lutece.plugins.stock.business.attribute.AbstractAttributeDate;
import fr.paris.lutece.plugins.stock.business.attribute.AbstractAttributeNum;
import fr.paris.lutece.plugins.stock.utils.EntityBean;

/**
 * Abstract class extended by all stock entities.
 * 
 * @param <E>
 *            the concrete entity type
 */
public abstract class StockEntityBean<E> implements Serializable, EntityBean
{

    /** SUID. */
    private static final long serialVersionUID = 4564488633622666908L;

    // Lazy initialization
    /** The attribute map. */
    private Map<String, String> _attributeMap = null;

    /** The attribute date map. */
    private Map<String, Timestamp> _attributeDateMap = null;

    /** The attribute num map. */
    private Map<String, BigDecimal> _attributeNumMap = null;

    /**
     * Gets the attribute list.
     * 
     * @return the attribute list
     */
    public abstract Set<? extends AbstractAttribute> getAttributeList( );

    /**
     * Gets the attribute date list.
     * 
     * @return the attribute date list
     */
    public abstract Set<? extends AbstractAttributeDate> getAttributeDateList( );

    /**
     * Gets the attribute num list.
     * 
     * @return the attribute num list
     */
    public abstract Set<? extends AbstractAttributeNum> getAttributeNumList( );

    /**
     * Return map of dynamic attributes.
     * 
     * @return map of dynamic attributes
     */
    public Map<String, String> getAttributeMap( )
    {
        if ( _attributeMap == null )
        {
            _attributeMap = new HashMap<>( );
            for ( AbstractAttribute attribute : getAttributeList( ) )
            {
                _attributeMap.put( attribute.getKey( ), attribute.getValue( ) );
            }
        }
        return _attributeMap;
    }

    /**
     * Return map of dynamic numeric attributes.
     * 
     * @return map of dynamic numeric attributes
     */
    public Map<String, BigDecimal> getAttributeNumMap( )
    {
        if ( _attributeNumMap == null )
        {
            _attributeNumMap = new HashMap<>( );
            for ( AbstractAttributeNum attribute : getAttributeNumList( ) )
            {
                _attributeNumMap.put( attribute.getKey( ), attribute.getValue( ) );
            }
        }
        return _attributeNumMap;
    }

    /**
     * Return map of dynamic attributes.
     * 
     * @return map of dynamic attributes
     */
    public Map<String, Timestamp> getAttributeDateMap( )
    {
        if ( _attributeDateMap == null )
        {
            _attributeDateMap = new HashMap<>( );
            for ( AbstractAttributeDate attribute : getAttributeDateList( ) )
            {
                _attributeDateMap.put( attribute.getKey( ), attribute.getValue( ) );
            }
        }
        return _attributeDateMap;
    }
}
