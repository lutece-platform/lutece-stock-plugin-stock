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
package fr.paris.lutece.plugins.stock.business.subscription;

import fr.paris.lutece.plugins.stock.business.product.Product;
import fr.paris.lutece.plugins.stock.utils.jpa.StockJPAUtils;

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
import javax.persistence.Table;
import javax.persistence.TableGenerator;


/**
 * Subscription Product.
 * @author jchaline
 */
@Entity
@Inheritance( strategy = InheritanceType.JOINED )
@Table( name = "stock_product_subscription_user" )
public class SubscriptionProduct
{

    /**  
     *
     */
    private static final long serialVersionUID = -2357849090898194150L;

    /** Sequence name. */
    private static final String JPA_SEQUENCE_NAME = "stock_product_subscription_user_sequence";

    /** Unique value. */
    private static final String JPA_COLUMN_NAME = "stock_product_subscription_user_id";

    /** The _id subscription. */
    private Integer _id;

    /** The _str userName. */
    private String _strUserName;

    /** The offer. */
    private Product _product;

    /**
     * Constructor.
     */
    public SubscriptionProduct(  )
    {
        super(  );
        this._product = new Product( );
    }

    /**
     * Constructor from an other subscriptionProduct object.
     * 
     * @param subscriptionProduct the subscriptionProduct
     */
    public SubscriptionProduct( SubscriptionProduct subscriptionProduct )
    {
        _id = subscriptionProduct.getId( );

    }

    /**
     * Return the SubscriptionProduct id.
     * 
     * @return the subscriptionProduct id
     */
    @TableGenerator( table = StockJPAUtils.SEQUENCE_TABLE_NAME, name = JPA_SEQUENCE_NAME, pkColumnValue = JPA_COLUMN_NAME, allocationSize = 1 )
    @Id
    @GeneratedValue( strategy = GenerationType.TABLE, generator = JPA_SEQUENCE_NAME )
    @Column( name = "id" )
    public Integer getId( )
    {
        return _id;
    }

    /**
     * Set the subscriptionProduct id.
     * 
     * @param id the subscriptionProduct id
     */
    public void setId( Integer id )
    {
        _id = id;
    }

    /**
     * Return the subscriptionProduct userName, actually the email.
     * 
     * 
     * @return the user name
     * @see fr.paris.lutece.plugins.mylutece.modules.cas.authentication.CasAuthentication
     */
    @Column( name = "userName" )
    public String getUserName( )
    {
        return _strUserName;
    }

    /**
     * Set the subscriptionProduct userName. Actualy use the mail instead of the
     * id, cause of CASAuthentication
     * 
     * @param userName the subscriptionProduct user name
     * @see fr.paris.lutece.plugins.mylutece.modules.cas.authentication.CasAuthentication
     */
    public void setUserName( String userName )
    {
        _strUserName = userName;
    }

    /**
     * Get the product linked to the subscriptionProduct.
     * 
     * @return the product
     */
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "id_product", nullable = true )
    public Product getProduct( )
    {
        return _product;
    }

    /**
     * Set the product linked to the subscriptionProduct.
     * 
     * @param product the product
     */
    public void setProduct( Product product )
    {
        _product = product;
    }

}
