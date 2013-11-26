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

import fr.paris.lutece.plugins.stock.business.product.IProductDAO;
import fr.paris.lutece.plugins.stock.business.product.Product;
import fr.paris.lutece.plugins.stock.service.impl.AbstractService;
import fr.paris.lutece.plugins.subscribe.business.Subscription;
import fr.paris.lutece.plugins.subscribe.business.SubscriptionFilter;
import fr.paris.lutece.plugins.subscribe.service.SubscriptionService;

import java.util.ArrayList;
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

    @Inject
    @Named( "stock.productDAO" )
    private IProductDAO _daoProduct;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasUserSubscribedToProduct( String strUserEmail, String strIdProduct )
    {
        SubscriptionFilter filter = new SubscriptionFilter( strUserEmail,
                StockSubscriptionProviderService.STOCK_PROVIDER_NAME,
                StockSubscriptionProviderService.STOCK_SUBSCRIPTION_KEY, strIdProduct );
        return SubscriptionService.getInstance( ).findByFilter( filter ).size( ) > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Product> getProductsByUserSubscription( String strUserEmail )
    {
        SubscriptionFilter filter = new SubscriptionFilter( );
        filter.setIdSubscriber( strUserEmail );
        filter.setSubscriptionKey( StockSubscriptionProviderService.STOCK_SUBSCRIPTION_KEY );
        filter.setSubscriptionProvider( StockSubscriptionProviderService.STOCK_PROVIDER_NAME );
        List<Subscription> listSubscriptions = SubscriptionService.getInstance( ).findByFilter( filter );

        List<Product> listProducts = new ArrayList<Product>( listSubscriptions.size( ) );
        for ( Subscription subscription : listSubscriptions )
        {
            Product product = _daoProduct.findById( Integer.parseInt( subscription.getIdSubscribedResource( ) ) );
            listProducts.add( product );
        }
        return listProducts;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional( readOnly = false, propagation = Propagation.REQUIRES_NEW )
    public void doSaveSubscriptionProduct( String strUserEmail, String strIdProduct )
    {
        Subscription subscription = new Subscription( );
        subscription.setUserId( strUserEmail );
        subscription.setIdSubscribedResource( strIdProduct );
        subscription.setSubscriptionKey( StockSubscriptionProviderService.STOCK_SUBSCRIPTION_KEY );
        subscription.setSubscriptionProvider( StockSubscriptionProviderService.STOCK_PROVIDER_NAME );
        SubscriptionService.getInstance( ).createSubscription( subscription );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doDeleteSubscriptionProduct( String strUserEmail, String strIdProduct )
    {
        SubscriptionFilter filter = new SubscriptionFilter( strUserEmail,
                StockSubscriptionProviderService.STOCK_PROVIDER_NAME,
                StockSubscriptionProviderService.STOCK_SUBSCRIPTION_KEY, strIdProduct );

        List<Subscription> listSubscription = SubscriptionService.getInstance( ).findByFilter( filter );
        for ( Subscription subscription : listSubscription )
        {
            SubscriptionService.getInstance( ).removeSubscription( subscription.getIdSubscription( ), false );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doDeleteByIdProduct( String strIdProduct )
    {
        SubscriptionFilter filter = new SubscriptionFilter( );
        filter.setIdSubscribedResource( strIdProduct );
        filter.setSubscriptionKey( StockSubscriptionProviderService.STOCK_SUBSCRIPTION_KEY );
        filter.setSubscriptionProvider( StockSubscriptionProviderService.STOCK_PROVIDER_NAME );

        List<Subscription> listSubscriptions = SubscriptionService.getInstance( ).findByFilter( filter );
        for ( Subscription subscription : listSubscriptions )
        {
            SubscriptionService.getInstance( ).removeSubscription( subscription.getIdSubscription( ), false );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getListEmailSubscriber( String strIdProduct )
    {
        SubscriptionFilter filter = new SubscriptionFilter( );
        filter.setIdSubscribedResource( strIdProduct );
        filter.setSubscriptionKey( StockSubscriptionProviderService.STOCK_SUBSCRIPTION_KEY );
        filter.setSubscriptionProvider( StockSubscriptionProviderService.STOCK_PROVIDER_NAME );

        List<Subscription> listSubscriptions = SubscriptionService.getInstance( ).findByFilter( filter );
        List<String> listUserId = new ArrayList<String>( listSubscriptions.size( ) );
        for ( Subscription subscription : listSubscriptions )
        {
            listUserId.add( subscription.getUserId( ) );
        }
        return listUserId;
    }
}
