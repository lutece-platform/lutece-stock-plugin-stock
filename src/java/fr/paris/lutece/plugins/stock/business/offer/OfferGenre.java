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
package fr.paris.lutece.plugins.stock.business.offer;

import fr.paris.lutece.plugins.stock.business.StockEntityBean;
import fr.paris.lutece.plugins.stock.business.attribute.offer_genre.OfferGenreAttribute;
import fr.paris.lutece.plugins.stock.business.attribute.offer_genre.OfferGenreAttributeDate;
import fr.paris.lutece.plugins.stock.business.attribute.offer_genre.OfferGenreAttributeNum;
import fr.paris.lutece.plugins.stock.utils.jpa.StockJPAUtils;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * OfferGenre entity.
 * 
 * @author abataille
 */
@Entity
@Table( name = "stock_offer_genre" )
public class OfferGenre extends StockEntityBean<OfferGenre>
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5200753622831317126L;

    /** Sequence name. */
    private static final String JPA_SEQUENCE_NAME = "stock_offer_genre_sequence";

    /** Unique value. */
    private static final String JPA_COLUMN_NAME = "stock_offer_genre_id";

    /** The _n id genre. */
    private Integer _nIdGenre;

    /** The _str name. */
    private String _strName;

    /** The _attribute list. */
    private Set<OfferGenreAttribute> _attributeList;

    /** The _attribute date list. */
    private Set<OfferGenreAttributeDate> _attributeDateList;

    /** The _attribute num list. */
    private Set<OfferGenreAttributeNum> _attributeNumList;

    /**
     * 
     * Creates a new OfferGenre.java object.
     */
    public OfferGenre( )
    {
        super( );
        this._attributeDateList = new HashSet<>( );
        this._attributeList = new HashSet<>( );
        this._attributeNumList = new HashSet<>( );
    }

    /**
     * Get the identifier number of this offer genre.
     * 
     * @return The identifier number of this offer genre
     */
    @TableGenerator( table = StockJPAUtils.SEQUENCE_TABLE_NAME, name = JPA_SEQUENCE_NAME, pkColumnValue = JPA_COLUMN_NAME, allocationSize = 1 )
    @Id
    @GeneratedValue( strategy = GenerationType.TABLE, generator = JPA_SEQUENCE_NAME )
    @Column( name = "id_offer_genre" )
    public Integer getId( )
    {
        return _nIdGenre;
    }

    /**
     * setter id.
     * 
     * @param nIdGenre
     *            id
     */
    public void setId( Integer nIdGenre )
    {
        _nIdGenre = nIdGenre;
    }

    /**
     * Gets the name.
     * 
     * @return the name
     */
    @Column( name = "name" )
    public String getName( )
    {
        return _strName;
    }

    /**
     * Set name.
     * 
     * @param strName
     *            name
     */
    public void setName( String strName )
    {
        _strName = strName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.paris.lutece.plugins.stock.business.StockEntityBean#getAttributeList()
     */
    /**
     * {@inheritDoc}
     */
    @OneToMany( cascade = {
            CascadeType.ALL
    }, mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER )
    public Set<OfferGenreAttribute> getAttributeList( )
    {
        return _attributeList;
    }

    /**
     * Sets the attribute list.
     * 
     * @param stringAttribute
     *            the new attribute list
     */
    public void setAttributeList( Set<OfferGenreAttribute> stringAttribute )
    {
        this._attributeList = stringAttribute;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.paris.lutece.plugins.stock.business.StockEntityBean#getAttributeDateList ()
     */
    /**
     * {@inheritDoc}
     */
    @OneToMany( cascade = {
            CascadeType.ALL
    }, mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER )
    public Set<OfferGenreAttributeDate> getAttributeDateList( )
    {
        return _attributeDateList;
    }

    /**
     * Sets the attribute date list.
     * 
     * @param dateAttribute
     *            the new attribute date list
     */
    public void setAttributeDateList( Set<OfferGenreAttributeDate> dateAttribute )
    {
        this._attributeDateList = dateAttribute;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.paris.lutece.plugins.stock.business.StockEntityBean#getAttributeNumList ()
     */
    /**
     * {@inheritDoc}
     */
    @OneToMany( cascade = {
            CascadeType.ALL
    }, mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER )
    public Set<OfferGenreAttributeNum> getAttributeNumList( )
    {
        return _attributeNumList;
    }

    /**
     * Sets the attribute num list.
     * 
     * @param numAttribute
     *            the new attribute num list
     */
    public void setAttributeNumList( Set<OfferGenreAttributeNum> numAttribute )
    {
        this._attributeNumList = numAttribute;
    }
}
