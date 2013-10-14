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
package fr.paris.lutece.plugins.stock.business.subscription;

import fr.paris.lutece.plugins.stock.business.product.Product;
import fr.paris.lutece.plugins.stock.commons.ResultList;
import fr.paris.lutece.plugins.stock.commons.dao.AbstractStockDAO;
import fr.paris.lutece.plugins.stock.commons.dao.PaginationProperties;
import fr.paris.lutece.plugins.stock.service.StockPlugin;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


/**
 * This class provides Data Access methods for {@link Product} objects.
 * 
 * @param <K> the key type
 * @param <E> the element type
 * @author jchaline
 */
public class SubscriptionProductDAO<K, E> extends AbstractStockDAO<Integer, SubscriptionProduct>
    implements ISubscriptionProductDAO
{
    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getPluginName( )
    {
        return StockPlugin.PLUGIN_NAME;
    }

	    /**
     * Find product list by filter.
     * 
     * @param filter the filter
     * @return the list of product
     */
    public List<SubscriptionProduct> findByFilter( SubscriptionProductFilter filter )
    {
        EntityManager em = getEM( );
        CriteriaBuilder cb = em.getCriteriaBuilder( );

        CriteriaQuery<SubscriptionProduct> cq = cb.createQuery( SubscriptionProduct.class );

        Root<SubscriptionProduct> root = cq.from( SubscriptionProduct.class );

        buildCriteriaQuery( filter, root, cq, cb );

        cq.distinct( true );

        TypedQuery<SubscriptionProduct> query = em.createQuery( cq );

        return query.getResultList( );
	}

    /**
     * Finds by filter.
     * 
     * @param filter the filter
     * @param paginationProperties the pagination properties
     * @return the subscriptionProduct list
     */
    public ResultList<SubscriptionProduct> findByFilter( SubscriptionProductFilter filter,
            PaginationProperties paginationProperties )
    {
        EntityManager em = getEM( );
        CriteriaBuilder cb = em.getCriteriaBuilder( );

        CriteriaQuery<SubscriptionProduct> cq = cb.createQuery( SubscriptionProduct.class );

        Root<SubscriptionProduct> root = cq.from( SubscriptionProduct.class );

        buildCriteriaQuery( filter, root, cq, cb );
        cq.distinct( true );

        return createPagedQuery( cq, paginationProperties ).getResultList( );
    }

    /**
     * Build the predicate list from filter.
     * 
     * @param filter the filter
     * @param root the product root
     * @param builder the criteria builder
     * @return the predicate list
     */
    protected List<Predicate> buildPredicates( SubscriptionProductFilter filter, Root<SubscriptionProduct> root,
            CriteriaBuilder builder )
    {
		// predicates list
		List<Predicate> listPredicates = new ArrayList<Predicate>();

        if ( filter.getIdSubscriptionProduct( ) != null )
        {
            listPredicates
                    .add( builder.equal( root.get( SubscriptionProduct_.id ), filter.getIdSubscriptionProduct( ) ) );
        }

        return listPredicates;
	}

    /**
     * Build the criteria query from the filter.
     * 
     * @param filter the filter
     * @param root the category root
     * @param query the criteria query
     * @param builder the criteria builder
     */
    protected void buildCriteriaQuery( SubscriptionProductFilter filter, Root<SubscriptionProduct> root,
            CriteriaQuery<SubscriptionProduct> query,
            CriteriaBuilder builder )
    {
        // predicates list
        List<Predicate> listPredicates = new ArrayList<Predicate>( );

        if ( filter.getIdSubscriptionProduct( ) != null && filter.getIdSubscriptionProduct( ) > 0 )
        {
            listPredicates
                    .add( builder.equal( root.get( SubscriptionProduct_.id ), filter.getIdSubscriptionProduct( ) ) );
        }

        if ( filter.getNameUser( ) != null && !filter.getNameUser( ).isEmpty( ) )
        {
            listPredicates.add( builder.equal( root.get( SubscriptionProduct_.userName ), filter.getNameUser( ) ) );
        }

        if ( filter.getProduct( ) != null )
        {
            listPredicates.add( builder.equal( root.get( SubscriptionProduct_.product ), filter.getProduct( ) ) );
        }

        if ( !listPredicates.isEmpty(  ) )
        {
            // add existing predicates to Where clause
            query.where( listPredicates.toArray( new Predicate[listPredicates.size( )] ) );
        }
    }
}
