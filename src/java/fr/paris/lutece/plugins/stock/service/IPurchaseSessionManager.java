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
package fr.paris.lutece.plugins.stock.service;

import fr.paris.lutece.plugins.stock.business.purchase.IPurchaseDTO;
import fr.paris.lutece.plugins.stock.business.purchase.exception.PurchaseSessionExpired;
import fr.paris.lutece.plugins.stock.business.purchase.exception.PurchaseUnavailable;


/**
 * Manager for purchase session.
 * Contains methods for managing the purchase mechanism :
 * - reserve an offer into session
 * - check if purchase is into session just before store it in database
 * - release that
 * 
 * @author abataille
 */
public interface IPurchaseSessionManager
{

    /**
     * Reserves an offer quantity for a session.
     * Check if offer is available (quantity remaining in database + purchases
     * in session) and add purchase in session
     * @param sessionId session id
     * @param purchase purchase (offer id, quantity and user)
     * @throws PurchaseUnavailable exception thrown if purchase is impossible
     */
    void reserve( String sessionId, IPurchaseDTO purchase ) throws PurchaseUnavailable;

    /**
     * Check that purchase has been reserved before to save it.
     * 
     * @param sessionId session id
     * @param purchase purchase
     * @throws PurchaseSessionExpired the purchase session expired
     */
    void checkReserved( String sessionId, IPurchaseDTO purchase ) throws PurchaseSessionExpired;

    /**
     * Remove purchase from session
     * @param sessionId session id
     * @param purchase purchase
     */
    void release( String sessionId, IPurchaseDTO purchase );

    /**
     * Remove all active purchases for a session id
     * @param sessionId session id
     */
    void releaseAll( String sessionId );

    /**
     * Update offer quantity with quantity in session
     * @param quantity the quantity
     * @param offerId the offer id
     * @return the quantity update
     */
    Integer updateQuantityWithSession( Integer quantity, Integer offerId );

}