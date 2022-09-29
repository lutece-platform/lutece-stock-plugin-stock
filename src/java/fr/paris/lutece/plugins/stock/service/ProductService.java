/*
 * Copyright (c) 2002-2021, City of Paris
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
package fr.paris.lutece.plugins.stock.service;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import fr.paris.lutece.plugins.stock.business.product.IProductDAO;
import fr.paris.lutece.plugins.stock.business.product.Product;
import fr.paris.lutece.plugins.stock.service.impl.AbstractService;

/**
 * 
 * ProductService
 * 
 */
public class ProductService extends AbstractService implements IProductService
{
    @Inject
    @Named( "stock.productDAO" )
    private IProductDAO _daoProduct;

    /**
     * Return a list of products
     * 
     * @return list of product
     */
    public List<Product> getAllProduct( )
    {
        return _daoProduct.findAll( );
    }

    /**
     * Check if product is ful
     * 
     * @param productId
     *            the product id
     * @return true if full, false otherwise
     */
    public Boolean isFull( Integer productId )
    {
        return _daoProduct.isFull( productId );
    }

    /**
     * Check if product is type of representation
     * 
     * @param genreId
     *            the genre to check
     * @return true if product is, false otherwise
     */
    public Boolean isType( Integer productId, Integer genreId )
    {
        return _daoProduct.isType( productId, genreId );
    }

    public Boolean isTypeOffer( Integer productId, Integer genreId, String keyDate, Timestamp now, String annuleKey )
    {
        return _daoProduct.isTypeOffer( productId, genreId, keyDate, now, annuleKey );
    }

    /**
     * Returns products IDs between two timestamps
     *
     * @param keyDate
     *            the attribute key
     * @param timestampStart
     *            the start timestamp
     * @param timestamsEnd
     *            the end timestamp
     * @return the products ids list
     */
    public List<Integer> getProductsIdsForTaskTimed( String keyDate, Timestamp timestampStart, Timestamp timestampEnd )
    {
        return _daoProduct.getProductsIdsForTaskTimed( keyDate, timestampStart, timestampEnd );
    }
}
