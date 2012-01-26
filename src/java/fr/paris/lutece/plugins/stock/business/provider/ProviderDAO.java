/*
 * Copyright (c) 2002-2011, Mairie de Paris
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
package fr.paris.lutece.plugins.stock.business.provider;

import fr.paris.lutece.plugins.stock.commons.ResultList;
import fr.paris.lutece.plugins.stock.commons.dao.AbstractStockDAO;
import fr.paris.lutece.plugins.stock.commons.dao.PaginationProperties;
import fr.paris.lutece.plugins.stock.service.StockPlugin;
import fr.paris.lutece.plugins.stock.utils.jpa.StockJPAUtils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;


/**
 * This class provides Data Access methods for {@link Provider} objects
 */
public final class ProviderDAO extends AbstractStockDAO<Integer, Provider> implements IProviderDAO
{
    /**
     * {@inheritDoc}
     */
    @Override
    public String getPluginName(  )
    {
        return StockPlugin.PLUGIN_NAME;
    }

    /**
     * Find all providers with product fetched.
     * 
     * @param paginationProperties the pagination properties
     * @return list of providers
     */
    public List<Provider> findAllWithProducts( PaginationProperties paginationProperties )
    {
        EntityManager em = getEM(  );
        CriteriaBuilder cb = em.getCriteriaBuilder(  );

        CriteriaQuery<Provider> cq = cb.createQuery( Provider.class );

        Root<Provider> root = cq.from( Provider.class );
        root.fetch( Provider_.products, JoinType.LEFT );
        cq.distinct( true );

        TypedQuery<Provider> query = em.createQuery( cq );

        return query.getResultList(  );
    }

    /**
     * Find provider by id with product fetched
     * @param nId the id
     * @return the provider found
     */
    public Provider findByIdWithProducts( int nId )
    {
        EntityManager em = getEM(  );
        CriteriaBuilder cb = em.getCriteriaBuilder(  );

        CriteriaQuery<Provider> cq = cb.createQuery( Provider.class );

        Root<Provider> root = cq.from( Provider.class );
        root.fetch( Provider_.products, JoinType.LEFT );
        cq.where( cb.equal( root.get( Provider_.id ), nId ) );
        cq.distinct( true );

        TypedQuery<Provider> query = em.createQuery( cq );

        return query.getSingleResult(  );
    }

    /**
     * Find providers by filter.
     * 
     * @param filter the filter
     * @param paginationProperties the pagination properties
     * @return list of providers
     */
    public ResultList<Provider> findByFilter( ProviderFilter filter, PaginationProperties paginationProperties )
    {
        EntityManager em = getEM(  );
        CriteriaBuilder cb = em.getCriteriaBuilder(  );

        CriteriaQuery<Provider> cq = cb.createQuery( Provider.class );

        Root<Provider> root = cq.from( Provider.class );
        buildCriteriaQuery( filter, root, cq, cb );
        buildSortQuery( filter, root, cq, cb );
        cq.distinct( true );

        return createPagedQuery( cq, paginationProperties ).getResultList( );
    }

    /**
     * Build the criteria query used when providers are searched by filter
     * @param filter the filter
     * @param root the provider root
     * @param query the criteria query
     * @param builder the criteria builder
     */
    private void buildCriteriaQuery( ProviderFilter filter, Root<Provider> root, CriteriaQuery<Provider> query,
        CriteriaBuilder builder )
    {
        // predicates list
        List<Predicate> listPredicates = new ArrayList<Predicate>(  );

        if ( StringUtils.isNotBlank( filter.getName(  ) ) )
        {
            listPredicates.add( builder.like( root.get( Provider_.name ),
                    StockJPAUtils.buildCriteriaLikeString( filter.getName(  ) ) ) );

            //listPredicates.add( cb.like( root.<String>get( Provider.ATTRIBUTE_NAME), StockJPAUtils.buildCriteriaLikeString( filter.getName() ) ) );
        }

        if ( StringUtils.isNotBlank( filter.getAddress(  ) ) )
        {
            listPredicates.add( builder.like( root.get( Provider_.address ),
                    StockJPAUtils.buildCriteriaLikeString( filter.getAddress(  ) ) ) );

            //listPredicates.add( builder.like( root.<String>get( Provider.ATTRIBUTE_ADDRESS), StockJPAUtils.buildCriteriaLikeString( filter.getAddress() ) ) );
        }

        if ( StringUtils.isNotBlank( filter.getContactName(  ) ) )
        {
            listPredicates.add( builder.like( root.get( Provider_.contactName ),
                    StockJPAUtils.buildCriteriaLikeString( filter.getContactName(  ) ) ) );

            //listPredicates.add( builder.like( root.<String>get( Provider.ATTRIBUTE_CONTACT_NAME), StockJPAUtils.buildCriteriaLikeString( filter.getContactName() ) ) );
        }

        if ( StringUtils.isNotBlank( filter.getMail(  ) ) )
        {
            listPredicates.add( builder.like( root.get( Provider_.mail ),
                    StockJPAUtils.buildCriteriaLikeString( filter.getMail(  ) ) ) );

            //listPredicates.add( builder.like( root.<String>get( Provider.ATTRIBUTE_MAIL), StockJPAUtils.buildCriteriaLikeString( filter.getMail() ) ) );
        }

        if ( StringUtils.isNotBlank( filter.getPhoneNumber(  ) ) )
        {
            listPredicates.add( builder.like( root.get( Provider_.phoneNumber ),
                    StockJPAUtils.buildCriteriaLikeString( filter.getPhoneNumber(  ) ) ) );

            //listPredicates.add( builder.like( root.<String>get( Provider.ATTRIBUTE_PHONE_NUMBER), StockJPAUtils.buildCriteriaLikeString( filter.getPhoneNumber() ) ) );
        }

        if ( filter.isProducts(  ) )
        {
            root.fetch( Provider_.products, JoinType.LEFT );
        }

        if ( !listPredicates.isEmpty(  ) )
        {
            // add existing predicates to Where clause
            query.where( listPredicates.toArray( new Predicate[0] ) );
        }
    }

    /**
     * Add the order by parameter to the query
     * @param filter the filter
     * @param root the provider root
     * @param query the criteria query
     * @param builder the criteria builder
     */
    private void buildSortQuery( ProviderFilter filter, Root<Provider> root, CriteriaQuery<Provider> query,
        CriteriaBuilder builder )
    {
        if ( filter.getOrders( ) != null && !filter.getOrders( ).isEmpty( ) )
        {
            List<Order> orderList = new ArrayList<Order>( );

            if ( filter.isOrderAsc(  ) )
            {
                // get asc order
            	for ( String order : filter.getOrders( ) )
            	{
            		orderList.add( builder.asc( root.get( order ) ) );
            	}
            }
            else
            {
                // get desc order
            	for ( String order : filter.getOrders( ) )
            	{
            		orderList.add( builder.desc( root.get( order ) ) );
            	}
            }

            query.orderBy( orderList );
        }
    }
}
