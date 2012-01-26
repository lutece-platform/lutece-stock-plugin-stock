/*
 * Copyright (c) 2002-2011, Mairie de Paris
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

import java.util.ArrayList;
import java.util.List;


/**
 *
 * ProviderFilter
 *
 */
public class ProviderFilter
{
    private String _strAddress;
    private String _strComment;
    private String _strContactName;
    private String _strMail;
    private String _strName;
    private String _strPhoneNumber;
    private boolean _bProducts;

    // order
    private List<String> _orders = new ArrayList<String>( );
    private boolean _orderAsc;

    /**
     * Return the adress filtered
     * @return the adress
     */
    public String getAddress(  )
    {
        return _strAddress;
    }

    /**
     * Set the adress to filter
     * @param address to filter
     */
    public void setAddress( String address )
    {
        this._strAddress = address;
    }

    /**
     * Return the comment filtered
     * @return the comment filtered
     */
    public String getComment(  )
    {
        return _strComment;
    }

    /**
     * Set the comment to filter
     * @param comment to filter
     */
    public void setComment( String comment )
    {
        this._strComment = comment;
    }

    /**
     * Return the contact filtered
     * @return the contact to filter
     */
    public String getContactName(  )
    {
        return _strContactName;
    }

    /**
     * Set the contact to filter
     * @param contactName  the contact to filter
     */
    public void setContactName( String contactName )
    {
        this._strContactName = contactName;
    }

    /**
     * Return the provider mail filtered
     * @return the provider mail
     */
    public String getMail(  )
    {
        return _strMail;
    }

    /**
     * Set the provider mail to filter
     * @param mail the mail to fitler
     */
    public void setMail( String mail )
    {
        this._strMail = mail;
    }

    /**
     * Return the provider name filtered
     * @return the name filtered
     */
    public String getName(  )
    {
        return _strName;
    }

    /**
     * Set the name to filter
     * @param name the name to filter
     */
    public void setName( String name )
    {
        this._strName = name;
    }

    /**
     * Return the phone number
     * @return the phone number
     */
    public String getPhoneNumber(  )
    {
        return _strPhoneNumber;
    }

    /**
     * Set the phonenumber to filter
     * @param phoneNumber to filter
     */
    public void setPhoneNumber( String phoneNumber )
    {
        this._strPhoneNumber = phoneNumber;
    }

    /**
     * Return true if the order is ascending
     * @return true if ascending false if descending
     */
    public boolean isOrderAsc(  )
    {
        return _orderAsc;
    }

    /**
     * Set the order way
     * @param isOrderAsc true if ascending false if descending
     */
    public void setOrderAsc( boolean isOrderAsc )
    {
        this._orderAsc = isOrderAsc;
    }

    /**
     * Get the order column name
     * @return the column name
     */
    public List<String> getOrders(  )
    {
        return _orders;
    }

    /**
     * Set the column to order
     * @param orders the column name
     */
    public void setOrders( List<String> orders )
    {
        _orders = orders;
    }

    /**
     * Return true if provider must have product
     * @return boolean true if we want the products of the provider. False otherwise
     */
    public boolean isProducts(  )
    {
        return _bProducts;
    }

    /**
     * Set if a provider must have product
     * @param bProducts true if we want the products of the provider. False otherwise
     */
    public void setProducts( boolean bProducts )
    {
        this._bProducts = bProducts;
    }
}
