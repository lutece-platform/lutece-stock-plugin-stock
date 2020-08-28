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
package fr.paris.lutece.plugins.stock.business.category;

import fr.paris.lutece.plugins.stock.business.StockEntityBean;
import fr.paris.lutece.plugins.stock.business.attribute.category.CategoryAttribute;
import fr.paris.lutece.plugins.stock.business.attribute.category.CategoryAttributeDate;
import fr.paris.lutece.plugins.stock.business.attribute.category.CategoryAttributeNum;
import fr.paris.lutece.plugins.stock.business.product.Product;
import fr.paris.lutece.plugins.stock.business.provider.Provider;
import fr.paris.lutece.plugins.stock.utils.jpa.StockJPAUtils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
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

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Category.
 */
@Entity
@Table( name = "stock_category" )
public class Category extends StockEntityBean<Category> implements Serializable
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5658942621967331856L;

    /** Sequence name. */
    private static final String JPA_SEQUENCE_NAME = "stock_category_sequence";

    /** Unique value. */
    private static final String JPA_COLUMN_NAME = "stock_category_id";

    /** The _id. */
    private Integer _id;

    /** The _str description. */
    private String _strDescription;

    /** The _str name. */
    @NotEmpty
    private String _strName;

    /** The _children list. */
    private Set<Category> _childrenList;

    /** The _product set. */
    private List<Product> _productSet;

    /** The _parent category. */
    private Category _parentCategory;

    /** The _provider. */
    private Provider _provider;

    /** The _attribute list. */
    private Set<CategoryAttribute> _attributeList;

    /** The _attribute date list. */
    private Set<CategoryAttributeDate> _attributeDateList;

    /** The _attribute num list. */
    private Set<CategoryAttributeNum> _attributeNumList;

    /**
     * Constructor.
     */
    public Category( )
    {
        super( );
        this._attributeDateList = new HashSet<CategoryAttributeDate>( );
        this._attributeList = new HashSet<CategoryAttribute>( );
        this._attributeNumList = new HashSet<CategoryAttributeNum>( );
    }

    /**
     * Build a new category from a category object.
     * 
     * @param category
     *            the category
     */
    public Category( Category category )
    {
        _id = category.getId( );
        _strDescription = category.getDescription( );
        _strName = category.getName( );
        _childrenList = category.getChildrenList( );
        _productSet = category.getProductSet( );
        _parentCategory = category.getParent( );
    }

    /**
     * Return category id.
     * 
     * @return category id
     */
    @TableGenerator( table = StockJPAUtils.SEQUENCE_TABLE_NAME, name = JPA_SEQUENCE_NAME, pkColumnValue = JPA_COLUMN_NAME, allocationSize = 1 )
    @Id
    @GeneratedValue( strategy = GenerationType.TABLE, generator = JPA_SEQUENCE_NAME )
    @Column( name = "id_category" )
    public Integer getId( )
    {
        return _id;
    }

    /**
     * Set the category id.
     * 
     * @param idCategory
     *            the category id
     */
    public void setId( Integer idCategory )
    {
        _id = idCategory;
    }

    /**
     * Return the category description.
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
     * @param description
     *            the category description
     */
    public void setDescription( String description )
    {
        _strDescription = description;
    }

    /**
     * Return the category name.
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
     * @param name
     *            the category name
     */
    public void setName( String name )
    {
        _strName = name;
    }

    /**
     * Return the children list.
     * 
     * @return the children list
     */
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parent" )
    public Set<Category> getChildrenList( )
    {
        return _childrenList;
    }

    /**
     * Set the children list.
     * 
     * @param childrenList
     *            the children list
     */
    public void setChildrenList( Set<Category> childrenList )
    {
        _childrenList = childrenList;
    }

    /**
     * Return the product list.
     * 
     * @return the product list
     */
    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "category" )
    public List<Product> getProductSet( )
    {
        return _productSet;
    }

    /**
     * Set the product list.
     * 
     * @param productSet
     *            the product list
     */
    public void setProductSet( List<Product> productSet )
    {
        _productSet = productSet;
    }

    /**
     * return the category parent.
     * 
     * @return the parent
     */
    @ManyToOne( optional = true, fetch = FetchType.LAZY )
    @JoinColumn( name = "parent_id" )
    // , nullable = false, updatable = false, insertable = false)
    public Category getParent( )
    {
        return _parentCategory;
    }

    /**
     * Set the Category parent.
     * 
     * @param parentCategory
     *            the parent
     */
    public void setParent( Category parentCategory )
    {
        this._parentCategory = parentCategory;
    }

    /**
     * Return the provider.
     * 
     * @return the provider
     */
    @ManyToOne( optional = true, fetch = FetchType.LAZY )
    @JoinColumn( name = "provider_id" )
    public Provider getProvider( )
    {
        return _provider;
    }

    /**
     * Set the provider.
     * 
     * @param provider
     *            the provider
     */
    public void setProvider( Provider provider )
    {
        _provider = provider;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.paris.lutece.plugins.stock.business.StockEntityBean#getAttributeList()
     */
    @OneToMany( cascade = {
            CascadeType.ALL
    }, mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER )
    public Set<CategoryAttribute> getAttributeList( )
    {
        return _attributeList;
    }

    /**
     * Sets the attribute list.
     * 
     * @param stringAttribute
     *            the new attribute list
     */
    public void setAttributeList( Set<CategoryAttribute> stringAttribute )
    {
        this._attributeList = stringAttribute;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.paris.lutece.plugins.stock.business.StockEntityBean#getAttributeDateList ()
     */
    @OneToMany( cascade = {
            CascadeType.ALL
    }, mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER )
    public Set<CategoryAttributeDate> getAttributeDateList( )
    {
        return _attributeDateList;
    }

    /**
     * Sets the attribute date list.
     * 
     * @param dateAttribute
     *            the new attribute date list
     */
    public void setAttributeDateList( Set<CategoryAttributeDate> dateAttribute )
    {
        this._attributeDateList = dateAttribute;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.paris.lutece.plugins.stock.business.StockEntityBean#getAttributeNumList ()
     */
    @OneToMany( cascade = {
            CascadeType.ALL
    }, mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER )
    public Set<CategoryAttributeNum> getAttributeNumList( )
    {
        return _attributeNumList;
    }

    /**
     * Sets the attribute num list.
     * 
     * @param numAttribute
     *            the new attribute num list
     */
    public void setAttributeNumList( Set<CategoryAttributeNum> numAttribute )
    {
        this._attributeNumList = numAttribute;
    }
}
