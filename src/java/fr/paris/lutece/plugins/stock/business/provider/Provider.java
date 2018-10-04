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
package fr.paris.lutece.plugins.stock.business.provider;

import fr.paris.lutece.plugins.stock.business.StockEntityBean;
import fr.paris.lutece.plugins.stock.business.attribute.provider.ProviderAttribute;
import fr.paris.lutece.plugins.stock.business.attribute.provider.ProviderAttributeDate;
import fr.paris.lutece.plugins.stock.business.attribute.provider.ProviderAttributeNum;
import fr.paris.lutece.plugins.stock.business.category.Category;
import fr.paris.lutece.plugins.stock.utils.jpa.StockJPAUtils;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 *
 * Provider
 *
 */
@Entity
// @Inheritance( strategy = InheritanceType.JOINED )
@Table( name = "stock_provider" )
public class Provider extends StockEntityBean<Provider>
{
    /**
     * SUID
     */
    private static final long serialVersionUID = 4296244688535088812L;

    /** Sequence name */
    private static final String JPA_SEQUENCE_NAME = "stock_provider_sequence";

    /** Unique value */
    private static final String JPA_COLUNM_NAME = "stock_provider_id";
    private Integer _id;
    private String _strName;
    private String _strAddress;
    private String _strContactName;
    private String _strPhoneNumber;
    private String _strMail;
    private String _strComment;
    private List<Category> _listProducts;
    private Set<ProviderAttribute> _attributeList;
    private Set<ProviderAttributeDate> _attributeDateList;
    private Set<ProviderAttributeNum> _attributeNumList;

    /**
     * Constructor
     */
    public Provider( )
    {
        this._attributeDateList = new HashSet<ProviderAttributeDate>( );
        this._attributeList = new HashSet<ProviderAttribute>( );
        this._attributeNumList = new HashSet<ProviderAttributeNum>( );
    }

    /**
     * Construct a new provider from an other
     * 
     * @param provider
     *            a provider object to copy
     */
    public Provider( Provider provider )
    {
        this._attributeDateList = new HashSet<ProviderAttributeDate>( );
        this._attributeList = new HashSet<ProviderAttribute>( );
        this._attributeNumList = new HashSet<ProviderAttributeNum>( );
        copy( provider );
    }

    /**
     * Copy values from a provider object to this
     * 
     * @param provider
     *            a provider object
     */
    private void copy( Provider provider )
    {
        setId( provider.getId( ) );
        setName( provider.getName( ) );
        setAddress( provider.getAddress( ) );
        setContactName( provider.getContactName( ) );
        setPhoneNumber( provider.getPhoneNumber( ) );
        setMail( provider.getMail( ) );
        setComment( provider.getComment( ) );
        setProducts( provider.getProducts( ) );
    }

    /**
     * Return the provider id
     * 
     * @return the provider id
     */
    @TableGenerator( table = StockJPAUtils.SEQUENCE_TABLE_NAME, name = JPA_SEQUENCE_NAME, pkColumnValue = JPA_COLUNM_NAME, allocationSize = 1 )
    @Id
    @GeneratedValue( strategy = GenerationType.TABLE, generator = JPA_SEQUENCE_NAME )
    @Column( name = "id_provider" )
    public Integer getId( )
    {
        return _id;
    }

    /**
     * Set the provider id
     * 
     * @param idProvider
     *            the provider id
     */
    public void setId( Integer idProvider )
    {
        _id = idProvider;
    }

    /**
     * Get the phone number to contact the provider
     * 
     * @return the phone number
     */
    @Column( name = "phone_number" )
    public String getPhoneNumber( )
    {
        return _strPhoneNumber;
    }

    /**
     * Set the phone number
     * 
     * @param strPhoneNumber
     *            the phone number
     */
    public void setPhoneNumber( String strPhoneNumber )
    {
        _strPhoneNumber = strPhoneNumber;
    }

    /**
     * Get the provider address
     * 
     * @return the provider address
     */
    @Column( name = "address" )
    public String getAddress( )
    {
        return _strAddress;
    }

    /**
     * Set the provider address
     * 
     * @param address
     *            address
     */
    public void setAddress( String address )
    {
        _strAddress = address;
    }

    /**
     * Get the provider mail
     * 
     * @return the mail
     */
    @Column( name = "mail" )
    public String getMail( )
    {
        return _strMail;
    }

    /**
     * Set the provider mail
     * 
     * @param mail
     *            the mail
     */
    public void setMail( String mail )
    {
        _strMail = mail;
    }

    /**
     * Get the provider name
     * 
     * @return the provider name
     */
    @Column( name = "name" )
    public String getName( )
    {
        return this._strName;
    }

    /**
     * Set the provider name
     * 
     * @param name
     *            the provider name
     */
    public void setName( String name )
    {
        this._strName = name;
    }

    /**
     * Get the product list
     * 
     * @return the product list
     */
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    public List<Category> getProducts( )
    {
        return _listProducts;
    }

    /**
     * Set the product list
     * 
     * @param products
     *            the product list
     */
    public void setProducts( List<Category> products )
    {
        _listProducts = products;
    }

    /**
     * Get the contact name
     * 
     * @return the contact name
     */
    public String getContactName( )
    {
        return _strContactName;
    }

    /**
     * Set the contact name
     * 
     * @param contactName
     *            the name of the contact
     */
    public void setContactName( String contactName )
    {
        this._strContactName = contactName;
    }

    /**
     * Get the comment
     * 
     * @return the comment
     */
    public String getComment( )
    {
        return _strComment;
    }

    /**
     * Set the comment
     * 
     * @param comment
     *            the comment
     */
    public void setComment( String comment )
    {
        this._strComment = comment;
    }

    /**
     * Returns dynamic attributes list
     * 
     * @return dynamic attributes list
     */
    @OneToMany( cascade = {
        CascadeType.ALL
    }, mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER )
    public Set<ProviderAttribute> getAttributeList( )
    {
        return _attributeList;
    }

    public void setAttributeList( Set<ProviderAttribute> stringAttribute )
    {
        this._attributeList = stringAttribute;
    }

    /**
     * Returns dynamic attributes list
     * 
     * @return dynamic attributes list
     */
    @OneToMany( cascade = {
        CascadeType.ALL
    }, mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER )
    public Set<ProviderAttributeDate> getAttributeDateList( )
    {
        return _attributeDateList;
    }

    /**
     * Sets the attribute date list.
     * 
     * @param dateAttribute
     *            the new attribute date list
     */
    public void setAttributeDateList( Set<ProviderAttributeDate> dateAttribute )
    {
        this._attributeDateList = dateAttribute;
    }

    /**
     * Returns dynamic attributes list
     * 
     * @return dynamic attributes list
     */
    @OneToMany( cascade = {
        CascadeType.ALL
    }, mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER )
    public Set<ProviderAttributeNum> getAttributeNumList( )
    {
        return _attributeNumList;
    }

    /**
     * Set attribute list
     * 
     * @param numAttribute
     *            numAttribute
     */
    public void setAttributeNumList( Set<ProviderAttributeNum> numAttribute )
    {
        this._attributeNumList = numAttribute;
    }

}
