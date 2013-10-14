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
package fr.paris.lutece.plugins.stock.service;

import fr.paris.lutece.plugins.stock.business.subscription.ISubscriptionProductDAO;
import fr.paris.lutece.plugins.stock.business.subscription.SubscriptionProduct;
import fr.paris.lutece.plugins.stock.business.subscription.SubscriptionProductFilter;
import fr.paris.lutece.plugins.stock.commons.exception.BusinessException;
import fr.paris.lutece.plugins.stock.service.impl.AbstractService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * 
 * ProductService
 * @author jchaline
 * 
 */
public class SubscriptionProductService extends AbstractService implements ISubscriptionProductService
{

    private static final String MESSAGE_ERROR_PRODUCT_NAME_MUST_BE_UNIQUE = "module.stock.billetterie.save_product.error.name.unique";

	@Inject
    @Named( "stock.subscriptionProductDAO" )
    private ISubscriptionProductDAO _daoSubscriptionProduct;

    /**
     * Return a list of products
     * @return list of product
     */
    public List<SubscriptionProduct> getAllSubscriptionProduct( )
    {
        return _daoSubscriptionProduct.findAll( );
    }

    @Override
    public List<SubscriptionProduct> findByFilter( SubscriptionProductFilter filter )
    {
        return _daoSubscriptionProduct.findByFilter( filter );
    }

    /**
     * {@inheritDoc}
     * @throws ValidationException
     */
    @Transactional( readOnly = false, propagation = Propagation.REQUIRES_NEW )
    public SubscriptionProduct doSaveSubscriptionProduct( SubscriptionProduct subscriptionProduct )
    {
        SubscriptionProductFilter filter = new SubscriptionProductFilter( );
        filter.setNameUser( subscriptionProduct.getUserName( ) );
        filter.setProduct( subscriptionProduct.getProduct( ) );
        List<SubscriptionProduct> listeSubscriptionProduct = _daoSubscriptionProduct.findByFilter( filter );
        if ( subscriptionProduct.getId( ) != null && subscriptionProduct.getId( ) > 0 )
        {
            //Update
            //Can have various subscription for the pair product/userName
            if ( listeSubscriptionProduct != null
                    && ( listeSubscriptionProduct.size( ) > 1 || listeSubscriptionProduct.size( ) == 1
                            && !listeSubscriptionProduct.get( 0 ).getId( ).equals( subscriptionProduct.getId( ) ) ) )
            {
                throw new BusinessException( subscriptionProduct, MESSAGE_ERROR_PRODUCT_NAME_MUST_BE_UNIQUE );
            }

            _daoSubscriptionProduct.update( subscriptionProduct );
        }
        else
        {
            //Create
            if ( listeSubscriptionProduct != null && listeSubscriptionProduct.size( ) > 0 )
            {
                throw new BusinessException( subscriptionProduct, MESSAGE_ERROR_PRODUCT_NAME_MUST_BE_UNIQUE );
            }
            _daoSubscriptionProduct.create( subscriptionProduct );
        }

        return subscriptionProduct;
    }

    /**
     * 
     * Delete a subscription for a product to an user
     * @param nIdSubscriptionProduct the id of the subscription
     */
    public void doDeleteSubscriptionProduct( int nIdSubscriptionProduct )
    {
        _daoSubscriptionProduct.remove( nIdSubscriptionProduct );
    }

    /**
     * 
     * Delete subscriptions by filter
     * @param filter the filter of subscription
     */
    public void doDeleteByFilter( SubscriptionProductFilter filter )
    {

        List<SubscriptionProduct> findByFilter = _daoSubscriptionProduct.findByFilter( filter );
        for ( SubscriptionProduct subscription : findByFilter )
        {
            _daoSubscriptionProduct.remove( subscription.getId( ) );
        }
    }

}
