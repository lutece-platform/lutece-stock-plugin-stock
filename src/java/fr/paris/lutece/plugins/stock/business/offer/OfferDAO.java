/*
 * Copyright (c) 2002-2018, Mairie de Paris
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

import fr.paris.lutece.plugins.stock.business.product.Product;
import fr.paris.lutece.plugins.stock.business.product.Product_;
import fr.paris.lutece.plugins.stock.commons.ResultList;
import fr.paris.lutece.plugins.stock.commons.dao.AbstractStockDAO;
import fr.paris.lutece.plugins.stock.commons.dao.PaginationProperties;
import fr.paris.lutece.plugins.stock.service.StockPlugin;
import fr.paris.lutece.plugins.stock.utils.jpa.StockJPAUtils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;

/**
 * This class provides Data Access methods for {@link Offer} objects.
 * 
 * @param <K>
 *            the key type
 * @param <E>
 *            the entity type
 */
public class OfferDAO<K, E> extends AbstractStockDAO<Integer, Offer> implements IOfferDAO
{

    /** The Constant JPQL_GET_QUANTITY. */
    private static final String JPQL_GET_QUANTITY = "SELECT o.quantity FROM Offer o WHERE o.id = :offerId";

    /*
     * (non-Javadoc)
     * 
     * @see fr.paris.lutece.portal.service.jpa.JPALuteceDAO#getPluginName()
     */
    @Override
    public String getPluginName( )
    {
        return StockPlugin.PLUGIN_NAME;
    }

    /**
     * Find offers by filter.
     * 
     * @param filter
     *            the filter
     * @param paginationProperties
     *            the pagination properties
     * @return list of offers
     */
    public ResultList<Offer> findByFilter( OfferFilter filter, PaginationProperties paginationProperties )
    {
        EntityManager em = getEM( );
        CriteriaBuilder cb = em.getCriteriaBuilder( );

        CriteriaQuery<Offer> cq = cb.createQuery( Offer.class );

        Root<Offer> root = cq.from( Offer.class );
        buildCriteriaQuery( filter, root, cq, cb );
        buildSortQuery( filter, root, cq, cb );
        cq.distinct( true );

        return createPagedQuery( cq, paginationProperties ).getResultList( );
    }

    /**
     * Build the criteria query used when offers are searched by filter.
     * 
     * @param filter
     *            the filter
     * @param root
     *            the offer root
     * @param query
     *            the criteria query
     * @param builder
     *            the criteria builder
     */
    protected void buildCriteriaQuery( OfferFilter filter, Root<Offer> root, CriteriaQuery<Offer> query, CriteriaBuilder builder )
    {
        // predicates list
        List<Predicate> listPredicates = new ArrayList<Predicate>( );

        Join<Offer, Product> product = root.join( Offer_.product, JoinType.INNER );
        Join<Offer, OfferGenre> type = root.join( Offer_.type, JoinType.INNER );

        if ( StringUtils.isNotBlank( filter.getProductName( ) ) )
        {
            listPredicates.add( builder.like( product.get( Product_.name ), StockJPAUtils.buildCriteriaLikeString( filter.getProductName( ) ) ) );
        }

        if ( StringUtils.isNotBlank( filter.getName( ) ) )
        {
            listPredicates.add( builder.like( root.get( Offer_.name ), StockJPAUtils.buildCriteriaLikeString( filter.getName( ) ) ) );
        }
        if ( filter.getIdGenre( ) != null && filter.getIdGenre( ) > 0 )
        {
            listPredicates.add( builder.equal( type.get( OfferGenre_.id ), filter.getIdGenre( ) ) );
        }

        if ( filter.getProductId( ) != null && filter.getProductId( ) > 0 )
        {
            listPredicates.add( builder.equal( product.get( Product_.id ), filter.getProductId( ) ) );
        }

        if ( !listPredicates.isEmpty( ) )
        {
            // add existing predicates to Where clause
            query.where( listPredicates.toArray( new Predicate [ listPredicates.size( )] ) );
        }
    }

    /**
     * Add the order by parameter to the query.
     * 
     * @param filter
     *            the filter
     * @param root
     *            the offer root
     * @param query
     *            the criteria query
     * @param builder
     *            the criteria builder
     */
    protected void buildSortQuery( OfferFilter filter, Root<Offer> root, CriteriaQuery<Offer> query, CriteriaBuilder builder )
    {
        if ( filter.getOrders( ) != null && !filter.getOrders( ).isEmpty( ) )
        {
            List<Order> orderList = new ArrayList<Order>( );
            Join<Offer, Product> product = root.join( Offer_.product, JoinType.INNER );
            Join<Offer, OfferGenre> type = root.join( Offer_.type, JoinType.INNER );

            if ( filter.isOrderAsc( ) )
            {
                // get asc order
                for ( String order : filter.getOrders( ) )
                {
                    if ( order.equals( "product.name" ) )
                    {
                        orderList.add( builder.asc( product.get( "name" ) ) );
                    }
                    else
                        if ( order.equals( "type.name" ) )
                        {
                            orderList.add( builder.asc( type.get( "name" ) ) );
                        }
                        else
                        {
                            orderList.add( builder.asc( root.get( order ) ) );
                        }
                }
            }
            else
            {
                // get desc order
                for ( String order : filter.getOrders( ) )
                {
                    if ( order.equals( "product.name" ) )
                    {
                        orderList.add( builder.desc( product.get( "name" ) ) );
                    }
                    else
                        if ( order.equals( "type.name" ) )
                        {
                            orderList.add( builder.desc( type.get( "name" ) ) );
                        }
                        else
                        {
                            orderList.add( builder.desc( root.get( order ) ) );
                        }
                }
            }

            query.orderBy( orderList );
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.paris.lutece.plugins.stock.business.offer.IOfferDAO#findByProduct( java.lang.Integer)
     */
    /**
     * {@inheritDoc}
     */
    public List<Offer> findByProduct( Integer productId, OfferFilter filter )
    {

        EntityManager em = getEM( );
        CriteriaBuilder cb = em.getCriteriaBuilder( );

        CriteriaQuery<Offer> cq = cb.createQuery( Offer.class );

        Root<Offer> root = cq.from( Offer.class );

        Join<Offer, Product> product = root.join( Offer_.product, JoinType.INNER );

        cq.where( cb.equal( product.get( Product_.id ), productId ) );

        buildSortQuery( filter, root, cq, cb );

        return em.createQuery( cq ).getResultList( );
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.paris.lutece.plugins.stock.business.offer.IOfferDAO#getQuantity(java .lang.Integer)
     */
    /**
     * {@inheritDoc}
     */
    public Integer getQuantity( Integer offerId )
    {
        EntityManager em = getEM( );
        Query query = em.createQuery( JPQL_GET_QUANTITY );
        query.setParameter( "offerId", offerId );
        return (Integer) query.getSingleResult( );
    }
}
