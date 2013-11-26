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

import fr.paris.lutece.plugins.stock.business.product.Product;

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
     * Check if a user has subscribed to a given product
     * @param strUserEmail The email of the user
     * @param strIdProduct The id of the product
     * @return True if the user has subscribed to the product, false otherwise
     */
    boolean hasUserSubscribedToProduct( String strUserEmail, String strIdProduct );

    /**
     * Find the list of products a user subscribed to
     * @param strUserEmail The email of the user to get product subscriptions of
     * @return the list of products
     */
    List<Product> getProductsByUserSubscription( String strUserEmail );

    /**
     * save the subscription to a product for an user
     * @param strUserEmail The email of the user that made the subscription
     * @param strIdProduct The id of the product the user subscribed to
     * @param subscriptionProduct the subscription entity
     */
    @Transactional( readOnly = false, propagation = Propagation.REQUIRES_NEW )
    void doSaveSubscriptionProduct( String strUserEmail, String strIdProduct );

    /**
     * Delete a subscription for a product to an user
     * @param strUserEmail the email of the subscription
     * @param strIdProduct The id of the product to unsubscribe
     */
    void doDeleteSubscriptionProduct( String strUserEmail, String strIdProduct );

    /**
     * Delete subscriptions to a given product
     * @param strIdProduct the id of the product to remove subscriptions of
     */
    void doDeleteByIdProduct( String strIdProduct );

    /**
     * Get the list of emails users that subscribed to a list given product
     * @param strIdProduct The id of the product
     * @return The list of emails of users that subscribed to a given product
     */
    List<String> getListEmailSubscriber( String strIdProduct );
}
