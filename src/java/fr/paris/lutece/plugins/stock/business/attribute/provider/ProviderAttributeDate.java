/*
 * Copyright (c) 2002-2012, Mairie de Paris
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
 *     contributors may be used to endorse or promote providers derived from
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
package fr.paris.lutece.plugins.stock.business.attribute.provider;

import fr.paris.lutece.plugins.stock.business.attribute.AbstractAttributeDate;
import fr.paris.lutece.plugins.stock.business.provider.Provider;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 *
 * Category
 *
 */
@Entity
@Table( name = "stock_provider_attribute_date" )
@IdClass( value = ProviderAttributeId.class )
public class ProviderAttributeDate extends AbstractAttributeDate<Provider> implements Serializable
{
    /**  
     *
     */
    private static final long serialVersionUID = -4613744479505847791L;

    private Provider _owner;

    /**
     * 
     * Creates a new ProviderAttributeDate.java object.
     * @param key key
     * @param value value
     * @param provider provider
     */
    public ProviderAttributeDate( String key, Timestamp value, Provider provider )
    {
        this._key = key;
        this._value = value;
        this._owner = provider;
    }

    /**
     * Creates a new ProviderAttributeDate.java object.
     */
    public ProviderAttributeDate( )
    {
        super( );
    }


    /**
     * @return the id
     */
    @Id
    @ManyToOne( optional = true, fetch = FetchType.LAZY )
    @JoinColumn( name = "owner_id" )
    public Provider getOwner( )
    {
        return _owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner( Provider owner )
    {
        this._owner = owner;
    }

    /**
     * @return the key
     */
    @Id
    @Column( name = "attribute_key" )
    public String getKey( )
    {
        return _key;
    }
}
