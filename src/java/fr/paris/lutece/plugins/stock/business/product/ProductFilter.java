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
package fr.paris.lutece.plugins.stock.business.product;

import java.util.ArrayList;
import java.util.List;



/**
 *
 * ProductFilter
 *
 */
public class ProductFilter
{
    private Integer _idProduct;
    private String _name;
    private Integer _idCategory;
    private Integer _idProvider;
    private String _dateFrom;
    private String _dateTo;
    private String _dateThe;
    private boolean _bOrderAsc;
    private Boolean _alaffiche;
    private List<String> _strOrders = new ArrayList<String>( );

    /**
     * Return the category id
     * @return the category id
     */
    public Integer getIdCategory(  )
    {
        return _idCategory;
    }

    /**
     * Set the category id
     * @param idCategory the category id
     */
    public void setIdCategory( Integer idCategory )
    {
        _idCategory = idCategory;
    }

    /**
     * Return the provider id
     * @return the provider id
     */
    public Integer getIdProvider( )
    {
        return _idProvider;
    }

    /**
     * Set the provider id
     * @param idProvider the provider id
     */
    public void setIdProvider( Integer idProvider )
    {
        _idProvider = idProvider;
    }

    /**
     * Return the product id
     * @return the product id
     */
    public String getName( )
    {
        return _name;
    }

    /**
     * Set the product name
     * @param name the product name
     */
    public void setName( String name )
    {
        _name = name;
    }


    /**
     * Return true if the order is ascending
     * @return true if the order is ascending
     */
    public boolean isOrderAsc(  )
    {
        return _bOrderAsc;
    }

    /**
     * Set the order, true for ascending false for descending
     * @param bOrderAsc the wanted order ( true if the order is ascending )
     */
    public void setOrderAsc( boolean bOrderAsc )
    {
        this._bOrderAsc = bOrderAsc;
    }

    /**
     * Get the colunm for order by
     * @return the column name
     */
    public List<String> getOrders(  )
    {
        return _strOrders;
    }

    /**
     * Set the column to order by
     * @param strOrders the column name
     */
    public void setOrders( List<String> strOrders )
    {
        this._strOrders = strOrders;
    }

    /**
     * Return the product id for filter
     * @return the product id for filter
     */
    public Integer getIdProduct(  )
    {
        return _idProduct;
    }

    /**
     * Set the product id to filter
     * @param idProduct the product id to filter
     */
    public void setIdProduct( Integer idProduct )
    {
        this._idProduct = idProduct;
    }


    /**
     * Return true if there is filters
     * @return boolean
     */
    public boolean hasFilter(  )
    {
        return !( ( _strOrders == null ) && ( _idCategory == null ) );
    }

    /**
     * @return the dateStart
     */
    public String getDateFrom( )
    {
        return _dateFrom;
    }

    /**
     * @param dateStart the dateStart to set
     */
    public void setDateFrom( String dateStart )
    {
        this._dateFrom = dateStart;
    }

    /**
     * @return the dateEnd
     */
    public String getDateTo( )
    {
        return _dateTo;
    }

    /**
     * @param dateEnd the dateEnd to set
     */
    public void setDateTo( String dateEnd )
    {
        this._dateTo = dateEnd;
    }

    /**
     * @return the dateThe
     */
    public String getDateThe( )
    {
        return _dateThe;
    }

    /**
     * @param dateThe the dateThe to set
     */
    public void setDateThe( String dateThe )
    {
        this._dateThe = dateThe;
    }

    /**
     * @return the alaffiche
     */
    public Boolean getAlaffiche( )
    {
        return _alaffiche;
    }

    /**
     * @param alaffiche the alaffiche to set
     */
    public void setAlaffiche( Boolean alaffiche )
    {
        this._alaffiche = alaffiche;
    }
}
