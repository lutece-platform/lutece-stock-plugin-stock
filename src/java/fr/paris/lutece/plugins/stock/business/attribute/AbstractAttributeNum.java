/*
 * Copyright (c) 2002-2018, Mairie de Paris
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
package fr.paris.lutece.plugins.stock.business.attribute;

import fr.paris.lutece.plugins.stock.business.StockEntityBean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.MappedSuperclass;

/**
 * 
 * Category
 * 
 * @param <E>
 *            entity
 */
@MappedSuperclass
@IdClass( value = AbstractAttributeId.class )
public abstract class AbstractAttributeNum<E extends StockEntityBean<?>> implements Serializable
{
    /**  
     *
     */
    private static final long serialVersionUID = 3145178572137613258L;
    /**  
     *
     */
    // private Integer id;
    protected String _key;
    protected BigDecimal _value;

    /**
     * Constructor
     */
    public AbstractAttributeNum( )
    {
        super( );
    }

    // /**
    // * @return the id
    // */
    // @TableGenerator( table = StockJPAUtils.SEQUENCE_TABLE_NAME, name =
    // JPA_SEQUENCE_NAME, pkColumnValue = "id", allocationSize = 1 )
    // @Id
    // @GeneratedValue( strategy = GenerationType.TABLE, generator =
    // JPA_SEQUENCE_NAME )
    // @Column( name = "id" )
    // public Integer getId( )
    // {
    // return id;
    // }
    //
    // /**
    // * @param id the id to set
    // */
    // public void setId( Integer id )
    // {
    // this.id = id;
    // }

    /**
     * @return the key
     */
    @Column( name = "attribute_key" )
    @Id
    public String getKey( )
    {
        return _key;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey( String key )
    {
        this._key = key;
    }

    /**
     * @return the value
     */
    @Column( name = "attribute_value" )
    public BigDecimal getValue( )
    {
        return _value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue( BigDecimal value )
    {
        this._value = value;
    }

    /**
     * @return the owner
     */
    public abstract E getOwner( );

}
