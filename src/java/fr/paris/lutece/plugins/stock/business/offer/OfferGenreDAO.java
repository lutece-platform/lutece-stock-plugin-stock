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
package fr.paris.lutece.plugins.stock.business.offer;

import fr.paris.lutece.plugins.stock.service.StockPlugin;
import fr.paris.lutece.plugins.stock.utils.constants.StockConstants;
import fr.paris.lutece.portal.service.jpa.JPALuteceDAO;
import fr.paris.lutece.util.ReferenceItem;
import fr.paris.lutece.util.ReferenceList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * This class provides Data Access methods for partner type objects
 */
public final class OfferGenreDAO extends JPALuteceDAO<Integer, OfferGenre> implements IOfferGenreDAO
{
    @Override
    public String getPluginName( )
    {
        return StockPlugin.PLUGIN_NAME;
    }

    /**
     * {@inheritDoc}
     */
    public OfferGenre findWithOffersLinked( int nIdOfferGenre )
    {
        EntityManager em = getEM( );
        CriteriaBuilder cb = em.getCriteriaBuilder( );

        CriteriaQuery<OfferGenre> cq = cb.createQuery( OfferGenre.class );

        Root<OfferGenre> root = cq.from( OfferGenre.class );
        root.fetch( OfferGenre_.offersList, JoinType.LEFT );

        Predicate condition = cb.equal( root.get( OfferGenre_.id ), nIdOfferGenre );
        cq.where( condition );

        TypedQuery<OfferGenre> query = em.createQuery( cq );

        return query.getSingleResult( );
    }

    /**
     * {@inheritDoc}
     */
    public ReferenceList findAllReferenceList( )
    {
        List<OfferGenre> listAllOfferGenres = findAll( );
        ReferenceList refList = new ReferenceList( );

        for ( OfferGenre offerGenre : listAllOfferGenres )
        {
            ReferenceItem refItem = new ReferenceItem( );
            refItem.setCode( StockConstants.EMPTY_STRING + offerGenre.getId( ) );
            refItem.setName( offerGenre.getName( ) );
            refList.add( refItem );
        }

        return refList;
    }

    /**
     * {@inheritDoc}
     */
    public List<OfferGenre> findByName( String strOfferName )
    {
        EntityManager em = getEM( );
        CriteriaBuilder cb = em.getCriteriaBuilder( );

        CriteriaQuery<OfferGenre> cq = cb.createQuery( OfferGenre.class );

        Root<OfferGenre> root = cq.from( OfferGenre.class );
        root.fetch( OfferGenre_.offersList, JoinType.LEFT );

        Predicate condition = cb.equal( root.get( OfferGenre_.name ), strOfferName );
        cq.where( condition );

        TypedQuery<OfferGenre> query = em.createQuery( cq );

        return query.getResultList( );
    }
}
