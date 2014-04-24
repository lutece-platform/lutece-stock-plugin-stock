/*
 * Copyright (c) 2002-2014, Mairie de Paris
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
package fr.paris.lutece.plugins.stock.business.purchase;

import fr.paris.lutece.plugins.stock.business.StockEntityBean;
import fr.paris.lutece.plugins.stock.business.attribute.purchase.PurchaseAttribute;
import fr.paris.lutece.plugins.stock.business.attribute.purchase.PurchaseAttributeDate;
import fr.paris.lutece.plugins.stock.business.attribute.purchase.PurchaseAttributeNum;
import fr.paris.lutece.plugins.stock.business.offer.Offer;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


/**
 *
 * Purchase
 *
 */
@Entity
@Table( name = "stock_purchase" )
public class Purchase extends StockEntityBean<Purchase>
{
    /**  
     *
     */
    private static final long serialVersionUID = -28321587290817581L;

    /** Sequence name */
    private static final String JPA_SEQUENCE_NAME = "stock_purchase_sequence";

    /** Unique value */
    private static final String JPA_COLUMN_NAME = "stock_purchase_id";
    private Integer _idPurchase;

    private String _strUserName;
    private Integer _nQuantity;
    private Offer _offer;
    private Set<PurchaseAttribute> _attributeList;
    private Set<PurchaseAttributeDate> _attributeDateList;
    private Set<PurchaseAttributeNum> _attributeNumList;

    /**
     * 
     * Creates a new Purchase.java object.
     */
    public Purchase(  )
    {
        super(  );
        this._offer = new Offer( );
        this._attributeDateList = new HashSet<PurchaseAttributeDate>( );
        this._attributeList = new HashSet<PurchaseAttribute>( );
        this._attributeNumList = new HashSet<PurchaseAttributeNum>( );
    }

    /**
     * Return purchase id
     * @return purchase id
     */
    @TableGenerator( table = StockJPAUtils.SEQUENCE_TABLE_NAME, name = JPA_SEQUENCE_NAME, pkColumnValue = JPA_COLUMN_NAME, allocationSize = 1 )
    @Id
    @GeneratedValue( strategy = GenerationType.TABLE, generator = JPA_SEQUENCE_NAME )
    @Column( name = "id_purchase" )
    public Integer getId( )
    {
        return _idPurchase;
    }

    /**
     * Set the purchase id
     * @param idPurchase the purchase id
     */
    public void setId( Integer idPurchase )
    {
        _idPurchase = idPurchase;
    }

    /**
     * Return the purchase userName
     * @return the userName
     */
    @Column( name = "userName" )
    public String getUserName( )
    {
        return _strUserName;
    }

    /**
     * Set the category userName
     * @param userName the purchase userName
     */
    public void setUserName( String userName )
    {
    	_strUserName = userName;
    }

    /**
     * Return the purchase quantity
     * @return the quantity
     */
    @Column( name = "quantity" )
    public Integer getQuantity( )
    {
        return _nQuantity;
    }

    /**
     * Set the category quantity
     * @param nQuantity the purchase quantity
     */
    public void setQuantity( Integer nQuantity )
    {
        _nQuantity = nQuantity;
    }

    /**
     * Return the offer
     * @return the offer
     */
    @ManyToOne( optional = true, fetch = FetchType.LAZY )
    @JoinColumn( name = "offer_id" )
    public Offer getOffer( )
    {
        return _offer;
    }

    /**
     * Set the offer
     * @param offer the offer
     */
    public void setOffer( Offer offer )
    {
        _offer = offer;
    }

    /**
     * Returns dynamic attributes list
     * @return dynamic attributes list
     */
    @OneToMany( cascade = { CascadeType.ALL }, mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER )
    public Set<PurchaseAttribute> getAttributeList( )
    {
        return _attributeList;
    }

    /**
     * Set attribute list
     * @param stringAttribute stringAttribute
     */
    public void setAttributeList( Set<PurchaseAttribute> stringAttribute )
    {
        this._attributeList = stringAttribute;
    }

    /**
     * Returns dynamic attributes list
     * @return dynamic attributes list
     */
    @OneToMany( cascade = { CascadeType.ALL }, mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER )
    public Set<PurchaseAttributeDate> getAttributeDateList( )
    {
        return _attributeDateList;
    }

    /**
     * Set attribute list
     * @param dateAttribute dateAttribute
     */
    public void setAttributeDateList( Set<PurchaseAttributeDate> dateAttribute )
    {
        this._attributeDateList = dateAttribute;
    }

    /**
     * Returns dynamic attributes list
     * @return dynamic attributes list
     */
    @OneToMany( cascade = { CascadeType.ALL }, mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER )
    public Set<PurchaseAttributeNum> getAttributeNumList( )
    {
        return _attributeNumList;
    }

    /**
     * Set attribute list
     * @param numAttribute numAttribute
     */
    public void setAttributeNumList( Set<PurchaseAttributeNum> numAttribute )
    {
        this._attributeNumList = numAttribute;
    }
}
