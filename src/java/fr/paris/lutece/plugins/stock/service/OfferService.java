/*
 * Copyright (c) 2002-2020, City of Paris
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

import fr.paris.lutece.plugins.stock.business.offer.IOfferDAO;
import fr.paris.lutece.plugins.stock.business.offer.Offer;
import fr.paris.lutece.plugins.stock.business.offer.OfferFilter;
import fr.paris.lutece.plugins.stock.service.impl.AbstractService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * 
 * ProductService
 * 
 */
public class OfferService extends AbstractService implements IOfferService
{
    @Inject
    @Named( "stock.offerDAO" )
    private IOfferDAO _daoOffer;

    /*
     * (non-Javadoc)
     * 
     * @see fr.paris.lutece.plugins.stock.service.IOfferService#getQuantity(java. lang.Integer)
     */
    /**
     * {@inheritDoc}
     */
    public List<Offer> findByProduct( Integer productId )
    {
        return _daoOffer.findByProduct( productId, new OfferFilter( ) );
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.paris.lutece.plugins.stock.service.IOfferService#getQuantity(java. lang.Integer)
     */
    /**
     * {@inheritDoc}
     */
    public Integer getQuantity( Integer offerId )
    {
        return _daoOffer.getQuantity( offerId );
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.paris.lutece.plugins.stock.service.IOfferService#findById(java.lang .Integer)
     */
    /**
     * {@inheritDoc}
     */
    public Offer findById( Integer offerId )
    {
        return _daoOffer.findById( offerId );
    }

}
