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

import fr.paris.lutece.plugins.stock.business.subscription.SubscriptionProduct;
import fr.paris.lutece.plugins.stock.business.subscription.SubscriptionProductFilter;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * 
 * ISubscriptionService
 * @author jchaline
 * 
 */
@Transactional
public interface ISubscriptionProductService
{

    /**
     * Return a list of all subscription products
     * @return list of subscription product
     */
    List<SubscriptionProduct> getAllSubscriptionProduct( );

    /**
     * Find by filter.
     * 
     * @param filter the filter
     * @return the SubscriptionProduct list filtered
     */
    List<SubscriptionProduct> findByFilter( SubscriptionProductFilter filter );

    /**
     * save the subscription to a product for an user
     * @param subscriptionProduct the subscription entity
     * @return the subscription product entity
     * @throws ValidationException
     */
    @Transactional( readOnly = false, propagation = Propagation.REQUIRES_NEW )
    SubscriptionProduct doSaveSubscriptionProduct( SubscriptionProduct subscriptionProduct );

    /**
     * 
     * Delete a subscription for a product to an user
     * @param nIdSubscriptionProduct the id of the subscription
     */
    void doDeleteSubscriptionProduct( int nIdSubscriptionProduct );

    /**
     * 
     * Delete subscriptions by filter
     * @param filter the filter of subscription
     */
    void doDeleteByFilter( SubscriptionProductFilter filter );
}
