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
package fr.paris.lutece.plugins.stock.business.product;

import fr.paris.lutece.plugins.stock.business.StockEntityBean;
import fr.paris.lutece.plugins.stock.business.attribute.product.ProductAttribute;
import fr.paris.lutece.plugins.stock.business.attribute.product.ProductAttributeDate;
import fr.paris.lutece.plugins.stock.business.attribute.product.ProductAttributeNum;
import fr.paris.lutece.plugins.stock.business.category.Category;
import fr.paris.lutece.plugins.stock.business.provider.Provider;
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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

// TODO: Auto-generated Javadoc
/**
 * Product.
 */
@Entity
@Inheritance( strategy = InheritanceType.JOINED )
@Table( name = "stock_product" )
public class Product extends StockEntityBean<Product>
{

    /**  
     *
     */
    private static final long serialVersionUID = -2357849090898194150L;

    /** Sequence name. */
    private static final String JPA_SEQUENCE_NAME = "stock_product_sequence";

    /** Unique value. */
    private static final String JPA_COLUMN_NAME = "stock_product_id";

    /** The _id product. */
    private Integer _idProduct;

    /** The _str description. */
    private String _strDescription;

    /** The _str name. */
    private String _strName;

    /** The _price. */
    private Float _price;

    /** The _category. */
    private Category _category;

    /** The _provider. */
    private Provider _provider;

    /** The _attribute date list. */
    private Set<ProductAttributeDate> _attributeDateList;

    /** The _attribute num list. */
    private Set<ProductAttributeNum> _attributeNumList;

    /** The _attribute list. */
    private Set<ProductAttribute> _attributeList;

    /**
     * Constructor.
     */
    public Product( )
    {
        super( );
        this._attributeDateList = new HashSet<ProductAttributeDate>( );
        this._attributeList = new HashSet<ProductAttribute>( );
        this._attributeNumList = new HashSet<ProductAttributeNum>( );
        this._provider = new Provider( );
        this._category = new Category( );
    }

    /**
     * Constructor from an other product object.
     * 
     * @param product
     *            the product
     */
    public Product( Product product )
    {
        _strName = product.getName( );
        _strDescription = product.getDescription( );
        _price = product.getPrice( );
        _category = product.getCategory( );
        _idProduct = product.getId( );
    }

    /**
     * Return the product id.
     * 
     * @return the product id
     */
    @TableGenerator( table = StockJPAUtils.SEQUENCE_TABLE_NAME, name = JPA_SEQUENCE_NAME, pkColumnValue = JPA_COLUMN_NAME, allocationSize = 1 )
    @Id
    @GeneratedValue( strategy = GenerationType.TABLE, generator = JPA_SEQUENCE_NAME )
    @Column( name = "id_product" )
    public Integer getId( )
    {
        return _idProduct;
    }

    /**
     * Set the product id.
     * 
     * @param idProduct
     *            the product id
     */
    public void setId( Integer idProduct )
    {
        _idProduct = idProduct;
    }

    /**
     * Return the product description.
     * 
     * @return the description
     */
    @Column( name = "description" )
    public String getDescription( )
    {
        return _strDescription;
    }

    /**
     * Set the product description.
     * 
     * @param description
     *            the product description
     */
    public void setDescription( String description )
    {
        _strDescription = description;
    }

    /**
     * Return the product name.
     * 
     * @return the name
     */
    @Column( name = "name" )
    public String getName( )
    {
        return _strName;
    }

    /**
     * Set the product name.
     * 
     * @param name
     *            the product name
     */
    public void setName( String name )
    {
        _strName = name;
    }

    /**
     * Return the price.
     * 
     * @return the price
     */
    @Column( name = "price" )
    public Float getPrice( )
    {
        return _price;
    }

    /**
     * Set the price.
     * 
     * @param price
     *            the price
     */
    public void setPrice( Float price )
    {
        _price = price;
    }

    /**
     * Get the category linked to the product.
     * 
     * @return the category
     */
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "category_id_category", nullable = true )
    public Category getCategory( )
    {
        return _category;
    }

    /**
     * Set the category linked to the product.
     * 
     * @param category
     *            the category
     */
    public void setCategory( Category category )
    {
        _category = category;
    }

    /**
     * Get the provider linked to the product.
     * 
     * @return the provider
     */
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "provider_id_provider", nullable = true )
    public Provider getProvider( )
    {
        return _provider;
    }

    /**
     * Set the provider linked to the product.
     * 
     * @param provider
     *            the provider
     */
    public void setProvider( Provider provider )
    {
        _provider = provider;
    }

    /**
     * Get the product list.
     * 
     * @return the product list
     */
    @OneToMany( cascade = {
        CascadeType.ALL
    }, mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER )
    public Set<ProductAttribute> getAttributeList( )
    {
        return _attributeList;
    }

    /**
     * Sets the attribute list.
     * 
     * @param stringAttribute
     *            the new attribute list
     */
    public void setAttributeList( Set<ProductAttribute> stringAttribute )
    {
        this._attributeList = stringAttribute;
    }

    /**
     * Returns dynamic attributes list.
     * 
     * @return dynamic attributes list
     */
    @OneToMany( cascade = {
        CascadeType.ALL
    }, mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER )
    public Set<ProductAttributeDate> getAttributeDateList( )
    {
        return _attributeDateList;
    }

    /**
     * Sets the attribute date list.
     * 
     * @param dateAttribute
     *            the new attribute date list
     */
    public void setAttributeDateList( Set<ProductAttributeDate> dateAttribute )
    {
        this._attributeDateList = dateAttribute;
    }

    /**
     * Returns dynamic attributes list.
     * 
     * @return dynamic attributes list
     */
    @OneToMany( cascade = {
        CascadeType.ALL
    }, mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER )
    public Set<ProductAttributeNum> getAttributeNumList( )
    {
        return _attributeNumList;
    }

    /**
     * Set attribute list.
     * 
     * @param numAttribute
     *            numAttribute
     */
    public void setAttributeNumList( Set<ProductAttributeNum> numAttribute )
    {
        this._attributeNumList = numAttribute;
    }

}
