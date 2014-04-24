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
package fr.paris.lutece.plugins.stock.business.category;

import java.util.ArrayList;
import java.util.List;

import fr.paris.lutece.plugins.stock.business.BeanFilter;


/**
 *
 * CategoryFilter
 *
 */
public class CategoryFilter implements BeanFilter
{
    private Integer _idCategory;
    private Boolean _status;
    private String _strName;
    private Integer _parentCategory;
    private Boolean _productNull;
    private boolean _bOrderAsc;
    private List<String> _orders = new ArrayList<String>( );

    /**
     * Return true if the filter is set to product null
     * @return boolean
     */
    public Boolean getProductNull(  )
    {
        return _productNull;
    }

    /**
     * Set if the filter is set to product null
     * @param productNull the boolean
     */
    public void setProductNull( Boolean productNull )
    {
        this._productNull = productNull;
    }

    /**
     * Return true if the order is set to ascending
     * @return the boolean
     */
    public boolean isOrderAsc(  )
    {
        return _bOrderAsc;
    }

    /**
     * Set the order to ascending or descending
     * @param bOrderAsc the order (true if ascending)
     */
    public void setOrderAsc( boolean bOrderAsc )
    {
        this._bOrderAsc = bOrderAsc;
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
     * Get the category id filtered
     * @return the category id
     */
    public Integer getIdCategory(  )
    {
        return _idCategory;
    }

    /**
     * Set the category id to filter
     * @param idCategory the category id
     */
    public void setIdCategory( Integer idCategory )
    {
        this._idCategory = idCategory;
    }

    /**
     * Get the status filtered
     * @return true if the status must be active
     */
    public Boolean getStatus(  )
    {
        return _status;
    }

    /**
     * Set the status filtered
     * @param status the status
     */
    public void setStatus( Boolean status )
    {
        this._status = status;
    }

    /**
     * return the category name filtered
     * @return the category name
     */
    public String getName(  )
    {
        return _strName;
    }

    /**
     * Set the category name to filter
     * @param name the category name
     */
    public void setName( String name )
    {
        this._strName = name;
    }

    /**
     * Get the parent filtered
     * @return the parent
     */
    public Integer getParentCategory(  )
    {
        return _parentCategory;
    }

    /**
     * Set the parent to filter
     * @param parentCategory the parent
     */
    public void setParentCategory( Integer parentCategory )
    {
        this._parentCategory = parentCategory;
    }

    /**
     * Return true if there is filters
     * @return true if there is filters
     */
    public boolean hasFilter(  )
    {
        return ( _productNull != null ) || ( _status != null ) || ( _idCategory != null ) || ( _parentCategory != null ) ||
        ( _strName != null );
    }
}
