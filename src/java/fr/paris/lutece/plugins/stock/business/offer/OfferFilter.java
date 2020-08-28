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

import java.util.ArrayList;
import java.util.List;

import fr.paris.lutece.plugins.stock.business.BeanFilter;

// TODO: Auto-generated Javadoc
/**
 * The Class OfferFilter.
 */
public class OfferFilter implements BeanFilter
{
    /** The _statut. */
    private String _statut;

    /** The _id. */
    private String _id;

    /** The _name. */
    private String _name;

    /** The _product name. */
    private String _productName;

    /** The _id genre. */
    private Integer _idGenre = 0;

    /** The _is without session. */
    private boolean _isWithoutSession;

    /** The _list id offers. */
    private List<Integer> _listIdOffers;

    // order
    /** The _orders. */
    private List<String> _orders = new ArrayList<String>( );

    /** The _order asc. */
    private boolean _orderAsc;

    /** The _product id. */
    private Integer _productId = 0;

    /**
     * Sets the product id.
     * 
     * @param productId
     *            the productId to set
     */
    public void setProductId( Integer productId )
    {
        this._productId = productId;
    }

    /**
     * Gets the product id.
     * 
     * @return the productId
     */
    public Integer getProductId( )
    {
        return _productId;
    }

    /**
     * Checks if is without session.
     * 
     * @return true, if is without session
     */
    public boolean isWithoutSession( )
    {
        return _isWithoutSession;
    }

    /**
     * Sets the without session.
     * 
     * @param isWithoutSession
     *            the new without session
     */
    public void setWithoutSession( boolean isWithoutSession )
    {
        _isWithoutSession = isWithoutSession;
    }

    /**
     * Gets the statut.
     * 
     * @return the statut
     */
    public String getStatut( )
    {
        return _statut;
    }

    /**
     * Sets the statut.
     * 
     * @param statut
     *            the new statut
     */
    public void setStatut( String statut )
    {
        _statut = statut;
    }

    /**
     * Gets the id.
     * 
     * @return the id
     */
    public String getId( )
    {
        return _id;
    }

    /**
     * Sets the id.
     * 
     * @param strId
     *            the new id
     */
    public void setId( String strId )
    {
        this._id = strId;
    }

    /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getName( )
    {
        return _name;
    }

    /**
     * Sets the name.
     * 
     * @param name
     *            the new name
     */
    public void setName( String name )
    {
        this._name = name;
    }

    /**
     * Sets the id genre.
     * 
     * @param idGenre
     *            the new id genre
     */
    public void setIdGenre( Integer idGenre )
    {
        _idGenre = idGenre;
    }

    /**
     * Gets the id genre.
     * 
     * @return the id genre
     */
    public Integer getIdGenre( )
    {
        return _idGenre;
    }

    /**
     * Gets the list id offers.
     * 
     * @return the list id offers
     */
    public List<Integer> getListIdOffers( )
    {
        return _listIdOffers;
    }

    /**
     * Sets the list id offers.
     * 
     * @param listIdOffers
     *            the new list id offers
     */
    public void setListIdOffers( List<Integer> listIdOffers )
    {
        _listIdOffers = listIdOffers;
    }

    @Override
    public boolean isOrderAsc( )
    {
        return _orderAsc;
    }

    @Override
    public void setOrderAsc( boolean isOrderAsc )
    {
        this._orderAsc = isOrderAsc;
    }

    @Override
    public List<String> getOrders( )
    {
        return _orders;
    }

    @Override
    public void setOrders( List<String> orders )
    {
        _orders = orders;
    }

    /**
     * Set the product name.
     * 
     * @param productName
     *            the new product name
     */
    public void setProductName( String productName )
    {
        this._productName = productName;
    }

    /**
     * Get the product name.
     * 
     * @return the product name
     */
    public String getProductName( )
    {
        return _productName;
    }
}
