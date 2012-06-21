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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * Filter for purchase
 */
public class PurchaseFilter
{

    /** The _id. */
    private String _id;

    /** The user id. */
    private String _userName;
    
    /**
     * Name of the agent
     */
    private String _agentName;

    /** The _product name. */
    private String _productName;

    /** The _date begin offer. */
    private Timestamp _dateBeginOffer;

    /** The _date end offer. */
    private Timestamp _dateEndOffer;

    /** The _id genre. */
    private Integer _idGenre = 0;

    /** The _id offer. */
    private Integer _idOffer = 0;

    /** The _id show. */
    private Integer _idShow = 0;

    /** The _is without session. */
    private boolean _isWithoutSession;
    
    // order
    /** The _orders. */
    private List<String> _orders = new ArrayList<String>( );

    /** The _order asc. */
    private boolean _orderAsc;

    /**
     * Checks if is without session.
     * 
     * @return true, if is without session
     */
    public boolean isWithoutSession(  )
    {
        return _isWithoutSession;
    }

    /**
     * Sets the without session.
     * 
     * @param isWithoutSession the new without session
     */
    public void setWithoutSession( boolean isWithoutSession )
    {
        _isWithoutSession = isWithoutSession;
    }

    /**
     * Gets the id.
     * 
     * @return the id
     */
    public String getId(  )
    {
        return _id;
    }

    /**
     * Sets the id.
     * 
     * @param strId the new id
     */
    public void setId( String strId )
    {
        this._id = strId;
    }

    /**
     * Gets the user name.
     * 
     * @return the user name
     */
    public String getUserName(  )
    {
        return _userName;
    }

    /**
     * Sets the user name.
     * 
     * @param name the new user name
     */
    public void setUserName( String name )
    {
        this._userName = name;
    }

    /**
     * Return true if the order is ascending.
     * 
     * @return true if ascending false if descending
     */
    public boolean isOrderAsc(  )
    {
        return _orderAsc;
    }

    /**
     * Set the order way.
     * 
     * @param isOrderAsc true if ascending false if descending
     */
    public void setOrderAsc( boolean isOrderAsc )
    {
        this._orderAsc = isOrderAsc;
    }

    /**
     * Get the order column name.
     * 
     * @return the column name
     */
    public List<String> getOrders(  )
    {
        return _orders;
    }

    /**
     * Set the column to order.
     * 
     * @param orders the column name
     */
    public void setOrders( List<String> orders )
    {
        _orders = orders;
    }

	    /**
     * Sets the id offer.
     * 
     * @param idOffer the idOffer to set
     */
	public void setIdOffer( Integer idOffer )
	{
		this._idOffer = idOffer;
	}

	    /**
     * Gets the id offer.
     * 
     * @return the idOffer
     */
	public Integer getIdOffer( )
	{
		return _idOffer;
	}

	    /**
     * Sets the product name.
     * 
     * @param productName the productName to set
     */
	public void setProductName( String productName )
	{
        this._productName = productName;
	}

	    /**
     * Gets the product name.
     * 
     * @return the productName
     */
	public String getProductName( )
	{
        return _productName;
	}

	    /**
     * Sets the date begin offer.
     * 
     * @param dateBeginOffer the dateBeginOffer to set
     */
	public void setDateBeginOffer( Timestamp dateBeginOffer )
	{
		this._dateBeginOffer = dateBeginOffer;
	}

	    /**
     * Gets the date begin offer.
     * 
     * @return the dateBeginOffer
     */
	public Timestamp getDateBeginOffer( )
	{
		return _dateBeginOffer;
	}

	    /**
     * Sets the date end offer.
     * 
     * @param dateEndOffer the dateEndOffer to set
     */
	public void setDateEndOffer( Timestamp dateEndOffer )
	{
		this._dateEndOffer = dateEndOffer;
	}

	    /**
     * Gets the date end offer.
     * 
     * @return the dateEndOffer
     */
	public Timestamp getDateEndOffer( )
	{
		return _dateEndOffer;
	}

	    /**
     * Sets the id genre.
     * 
     * @param idGenre the idGenre to set
     */
	public void setIdGenre( Integer idGenre )
	{
		this._idGenre = idGenre;
	}

	    /**
     * Gets the id genre.
     * 
     * @return the idGenre
     */
	public Integer getIdGenre( )
	{
		return _idGenre;
	}

    /**
     * Gets the id product.
     * 
     * @return the idShow
     */
    public Integer getIdProduct( )
    {
        return _idShow;
    }

    /**
     * Sets the id product.
     * 
     * @param idShow the idShow to set
     */
    public void setIdProduct( Integer idShow )
    {
        this._idShow = idShow;
    }

	public String getAgentName() {
		return _agentName;
	}

	public void setAgentName(String agentName) {
		this._agentName = agentName;
	}
}
