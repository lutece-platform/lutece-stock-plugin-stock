/*
 * Copyright (c) 2002-2013, Mairie de Paris
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
import fr.paris.lutece.plugins.stock.business.attribute.offer.OfferAttribute;
import fr.paris.lutece.plugins.stock.business.attribute.offer.OfferAttributeDate;
import fr.paris.lutece.plugins.stock.business.attribute.offer.OfferAttributeNum;
import fr.paris.lutece.plugins.stock.business.category.Category;
import fr.paris.lutece.plugins.stock.business.product.Product;
import fr.paris.lutece.plugins.stock.utils.jpa.StockJPAUtils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


// TODO: Auto-generated Javadoc
/**
 * Offer.
 */
@Entity
@Table( name = "stock_offer" )
public class Offer extends StockEntityBean<Offer> implements Serializable
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6390433796484715419L;

    /** Sequence name. */
    private static final String JPA_SEQUENCE_NAME = "stock_offer_sequence";

    /** Unique value. */
    private static final String JPA_COLUMN_NAME = "stock_offer_id";

    /** The _id. */
    private Integer _id;

    /** The _str name. */
    private String _strName;

    /** The _str description. */
    private String _strDescription;

    /** The _str statut. */
    private String _strStatut;

    /** The _n quantity. */
    private Integer _nQuantity;

    /** The _product. */
    private Product _product;

    /** The _type. */
    private OfferGenre _type;

    /** The _attribute list. */
    private Set<OfferAttribute> _attributeList;

    /** The _attribute date list. */
    private Set<OfferAttributeDate> _attributeDateList;

    /** The _attribute num list. */
    private Set<OfferAttributeNum> _attributeNumList;

    /*
     * @Transient private Provider _partner;
     */
    /**
     * 
     * Creates a new Offer.java object.
     */
    public Offer( )
    {
        super( );
        this._product = new Product( );
        this._type = new OfferGenre( );
        this._attributeDateList = new HashSet<OfferAttributeDate>( );
        this._attributeList = new HashSet<OfferAttribute>( );
        this._attributeNumList = new HashSet<OfferAttributeNum>( );
    }

    /**
     * 
     * Creates a new Offer.java object.
     * @param category category
     */
    public Offer( Category category )
    {
    }

    /**
     * Return offer id.
     * 
     * @return offer id
     */
    @TableGenerator( table = StockJPAUtils.SEQUENCE_TABLE_NAME, name = JPA_SEQUENCE_NAME, pkColumnValue = JPA_COLUMN_NAME, allocationSize = 1 )
    @Id
    @GeneratedValue( strategy = GenerationType.TABLE, generator = JPA_SEQUENCE_NAME )
    @Column( name = "id_offer" )
    public Integer getId( )
    {
        return _id;
    }

    /**
     * Set the offer id.
     * 
     * @param idOffer the offer id
     */
    public void setId( Integer idOffer )
    {
        _id = idOffer;
    }

    /**
     * Return the offer name.
     * 
     * @return the name
     */
    @Column( name = "name" )
    public String getName( )
    {
        return _strName;
    }

    /**
     * Set the category name.
     * 
     * @param name the offer name
     */
    public void setName( String name )
    {
        _strName = name;
    }

    /**
     * Return the offer description.
     * 
     * @return the description
     */
    @Column( name = "description" )
    public String getDescription( )
    {
        return _strDescription;
    }

    /**
     * Set the category description.
     * 
     * @param description the offer description
     */
    public void setDescription( String description )
    {
        _strDescription = description;
    }

    /**
     * Return the offer name.
     * 
     * @return the name
     */
    @Column( name = "quantity" )
    public Integer getQuantity( )
    {
        return _nQuantity;
    }

    /**
     * Set the category quantity.
     * 
     * @param nQuantity the offer quantity
     */
    public void setQuantity( Integer nQuantity )
    {
        _nQuantity = nQuantity;
    }

    /**
     * Return the product.
     * 
     * @return the product
     */
    @ManyToOne( optional = true, fetch = FetchType.LAZY )
    @JoinColumn( name = "product_id" )
    public Product getProduct( )
    {
        return _product;
    }

    /**
     * Set the product.
     * 
     * @param product the product
     */
    public void setProduct( Product product )
    {
        _product = product;
    }

    /**
     * Return the type.
     * 
     * @return the type
     */
    @ManyToOne( optional = true, fetch = FetchType.LAZY )
    @JoinColumn( name = "offer_genre_id" )
    public OfferGenre getType( )
    {
        return _type;
    }

    /**
     * Set the type.
     * 
     * @param type the type
     */
    public void setType( OfferGenre type )
    {
        _type = type;
    }

    /**
     * Returns dynamic attributes list.
     * 
     * @return dynamic attributes list
     */
    @OneToMany( cascade = { CascadeType.ALL }, mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER )
    public Set<OfferAttribute> getAttributeList( )
    {
        return _attributeList;
    }

    /**
     * Sets the attribute list.
     * 
     * @param stringAttribute the new attribute list
     */
    public void setAttributeList( Set<OfferAttribute> stringAttribute )
    {
        this._attributeList = stringAttribute;
    }

    /**
     * Returns dynamic attributes list.
     * 
     * @return dynamic attributes list
     */
    @OneToMany( cascade = { CascadeType.ALL }, mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER )
    public Set<OfferAttributeDate> getAttributeDateList( )
    {
        return _attributeDateList;
    }

    /**
     * Sets the attribute date list.
     * 
     * @param dateAttribute the new attribute date list
     */
    public void setAttributeDateList( Set<OfferAttributeDate> dateAttribute )
    {
        this._attributeDateList = dateAttribute;
    }

    /**
     * Returns dynamic attributes list.
     * 
     * @return dynamic attributes list
     */
    @OneToMany( cascade = { CascadeType.ALL }, mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER )
    public Set<OfferAttributeNum> getAttributeNumList( )
    {
        return _attributeNumList;
    }

    /**
     * Sets the attribute num list.
     * 
     * @param numAttribute the new attribute num list
     */
    public void setAttributeNumList( Set<OfferAttributeNum> numAttribute )
    {
        this._attributeNumList = numAttribute;
    }

    /**
     * Sets the statut.
     * 
     * @param statut the statut to set
     */
    public void setStatut( String statut )
    {
        this._strStatut = statut;
    }

    /**
     * Gets the statut.
     * 
     * @return the statut
     */
    public String getStatut( )
    {
        return _strStatut;
    }
}
