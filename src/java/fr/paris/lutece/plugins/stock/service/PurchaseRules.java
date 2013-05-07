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

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import fr.paris.lutece.plugins.stock.business.offer.Offer;
import fr.paris.lutece.plugins.stock.business.purchase.IPurchaseDTO;
import fr.paris.lutece.plugins.stock.business.purchase.exception.PurchaseOutOfStock;
import fr.paris.lutece.plugins.stock.business.purchase.exception.PurchaseSessionExpired;


/**
 * To be overrided.
 * Default implementation for purchase rules.
 * 
 * @author abataille
 */
@Transactional
public class PurchaseRules implements IPurchaseRules
{

    private static final Logger LOGGER = Logger.getLogger( PurchaseRules.class );
    @Inject
    private IPurchaseSessionManager _purchaseSessionManager;

    @Inject
    @Named( "stock.offerService" )
    private IOfferService _offerService;

    /* (non-Javadoc)
     * @see fr.paris.lutece.plugins.stock.service.IPurchaseRules#checkBeforePurchase(fr.paris.lutece.plugins.stock.business.purchase.Purchase)
     */
    /**
     * {@inheritDoc}
     */
    public void checkBeforePurchase( IPurchaseDTO purchase, String sessionId ) throws PurchaseOutOfStock,
            PurchaseSessionExpired
    {

        LOGGER.trace( "Vérification des règles de gestion pour un achat, SID = " + sessionId );

        // Check if session opened for this booking
        _purchaseSessionManager.checkReserved( sessionId, purchase );

        Offer offer = _offerService.findById( purchase.getOfferId( ) );

        // Check quantity
        if ( purchase.getQuantity( ) > offer.getQuantity( ) )
        {
            throw new PurchaseOutOfStock( offer.getId( ), purchase.getQuantity( ), offer
                    .getQuantity( ), "Purchase out of stock." );
        }

    }

}
