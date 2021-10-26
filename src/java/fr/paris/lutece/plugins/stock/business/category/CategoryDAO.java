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
package fr.paris.lutece.plugins.stock.business.category;

import fr.paris.lutece.plugins.stock.commons.ResultList;
import fr.paris.lutece.plugins.stock.commons.dao.AbstractStockDAO;
import fr.paris.lutece.plugins.stock.commons.dao.PaginationProperties;
import fr.paris.lutece.plugins.stock.service.StockPlugin;
import fr.paris.lutece.plugins.stock.utils.jpa.StockJPAUtils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

/**
 * This class provides Data Access methods for Person objects
 */
public final class CategoryDAO extends AbstractStockDAO<Integer, Category> implements ICategoryDAO
{
    private static final String ATTRIBUTE_CATEGORY_ID = "idCategory";

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
     *
     * {@inheritDoc}
     */
    public List<Category> selectAllFirstLevelWithChildrenWithProduct( )
    {
        EntityManager em = getEM( );
        CriteriaBuilder cb = em.getCriteriaBuilder( );

        CriteriaQuery<Category> cq = cb.createQuery( Category.class );

        Root<Category> root = cq.from( Category.class );
        root.fetch( Category_.childrenList, JoinType.LEFT );
        root.fetch( Category_.productSet, JoinType.LEFT );

        Predicate condition = cb.isNull( root.get( Category_.parent ) );
        cq.where( condition ).distinct( true );

        TypedQuery<Category> query = em.createQuery( cq );

        return query.getResultList( );
    }

    /**
     *
     * {@inheritDoc}
     */
    public List<Category> selectAllChildrenWithChildrenWithProduct( Integer idCategory )
    {
        EntityManager em = getEM( );
        CriteriaBuilder cb = em.getCriteriaBuilder( );

        CriteriaQuery<Category> cq = cb.createQuery( Category.class );

        Root<Category> root = cq.from( Category.class );
        root.fetch( Category_.childrenList, JoinType.LEFT );
        root.fetch( Category_.productSet, JoinType.LEFT );

        Predicate condition = cb.equal( root.get( Category_.childrenList ), idCategory );
        cq.where( condition ).distinct( true );

        TypedQuery<Category> query = em.createQuery( cq );

        return query.getResultList( );
    }

    /**
     *
     * {@inheritDoc}
     */
    public Category findByIdWithChildren( Integer idCategory )
    {
        EntityManager em = getEM( );
        CriteriaBuilder cb = em.getCriteriaBuilder( );

        CriteriaQuery<Category> cq = cb.createQuery( Category.class );

        Root<Category> root = cq.from( Category.class );
        root.fetch( Category_.childrenList, JoinType.LEFT );

        Predicate condition = cb.equal( root.get( ATTRIBUTE_CATEGORY_ID ), idCategory );
        cq.where( condition );

        TypedQuery<Category> query = em.createQuery( cq );

        try
        {
            return query.getSingleResult( );
        }
        catch( NoResultException e )
        {
            return null;
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    public Category findByIdWithProduct( Integer idCategory )
    {
        EntityManager em = getEM( );
        CriteriaBuilder cb = em.getCriteriaBuilder( );

        CriteriaQuery<Category> cq = cb.createQuery( Category.class );

        Root<Category> root = cq.from( Category.class );
        root.fetch( Category_.productSet, JoinType.LEFT );

        Predicate condition = cb.equal( root.get( ATTRIBUTE_CATEGORY_ID ), idCategory );
        cq.where( condition );

        TypedQuery<Category> query = em.createQuery( cq );

        try
        {
            return query.getSingleResult( );
        }
        catch( NoResultException e )
        {
            return null;
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    public Category findByIdWithParent( Integer idCategory )
    {
        Category category = findById( idCategory );

        // in order to load the parent
        Category parent = category.getParent( );

        if ( parent != null )
        {
            parent.getChildrenList( ).size( );
        }

        return category;
    }

    /**
     * {@inheritDoc}
     */
    public ResultList<Category> findByFilter( CategoryFilter filter, PaginationProperties paginationProperties )
    {
        EntityManager em = getEM( );
        CriteriaBuilder cb = em.getCriteriaBuilder( );

        CriteriaQuery<Category> cq = cb.createQuery( Category.class );

        Root<Category> root = cq.from( Category.class );
        buildCriteriaQuery( filter, root, cq, cb );
        buildSortQuery( filter, root, cq, cb );
        cq.distinct( true );

        return createPagedQuery( cq, paginationProperties ).getResultList( );
    }

    /**
     * Find category with children fetched by filter
     * 
     * @param filter
     *            the filter
     * @return the category list
     */
    public List<Category> findByFilterWithChildren( CategoryFilter filter )
    {
        EntityManager em = getEM( );
        CriteriaBuilder cb = em.getCriteriaBuilder( );

        CriteriaQuery<Category> cq = cb.createQuery( Category.class );

        Root<Category> root = cq.from( Category.class );
        root.fetch( Category_.childrenList, JoinType.LEFT );

        buildCriteriaQuery( filter, root, cq, cb );
        buildSortQuery( filter, root, cq, cb );

        cq.distinct( true );

        TypedQuery<Category> query = em.createQuery( cq );

        return query.getResultList( );
    }

    /**
     * Build the criteria query from the filter
     * 
     * @param filter
     *            the filter
     * @param root
     *            the category root
     * @param query
     *            the criteria query
     * @param builder
     *            the criteria builder
     */
    private void buildCriteriaQuery( CategoryFilter filter, Root<Category> root, CriteriaQuery<Category> query, CriteriaBuilder builder )
    {
        // predicates list
        List<Predicate> listPredicates = new ArrayList<>( );

        if ( StringUtils.isNotBlank( filter.getName( ) ) )
        {
            listPredicates.add( builder.like( root.get( Category_.name ), StockJPAUtils.buildCriteriaLikeString( filter.getName( ) ) ) );
        }

        if ( filter.getIdCategory( ) != null )
        {
            listPredicates.add( builder.equal( root.get( Category_.id ), filter.getIdCategory( ) ) );
        }

        if ( filter.getParentCategory( ) != null )
        {
            listPredicates.add( builder.equal( root.get( Category_.parent ).get( Category_.id ), filter.getParentCategory( ) ) );
        }

        if ( filter.getProductNull( ) != null )
        {
            if ( Boolean.TRUE.equals( filter.getProductNull( ) ) )
            {
                listPredicates.add( builder.isEmpty( root.get( Category_.productSet ) ) );
            }
            else
            {
                root.fetch( Category_.productSet, JoinType.INNER );
            }
        }

        if ( !listPredicates.isEmpty( ) )
        {
            // add existing predicates to Where clause
            query.where( listPredicates.toArray( new Predicate [ listPredicates.size( )] ) );
        }
    }

    /**
     * Add "order by" to the query
     * 
     * @param filter
     *            the fitler
     * @param root
     *            the category root
     * @param query
     *            the query
     * @param builder
     *            the criteria builder
     */
    private void buildSortQuery( CategoryFilter filter, Root<Category> root, CriteriaQuery<Category> query, CriteriaBuilder builder )
    {
        if ( filter.getOrders( ) != null && !filter.getOrders( ).isEmpty( ) )
        {
            List<Order> orderList = new ArrayList<>( );

            if ( filter.isOrderAsc( ) )
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

    /**
     * Find category list by filter
     * 
     * @param filter
     *            the filter
     * @return the category list
     */
    public List<Category> findByFilter( CategoryFilter filter )
    {
        EntityManager em = getEM( );
        CriteriaBuilder cb = em.getCriteriaBuilder( );

        CriteriaQuery<Category> cq = cb.createQuery( Category.class );

        Root<Category> root = cq.from( Category.class );

        buildCriteriaQuery( filter, root, cq, cb );

        cq.distinct( true );

        TypedQuery<Category> query = em.createQuery( cq );

        return query.getResultList( );
    }

    /**
     * {@inheritDoc}
     */
    public List<Category> getAllByName( String name )
    {
        EntityManager em = getEM( );
        CriteriaBuilder cb = em.getCriteriaBuilder( );

        CriteriaQuery<Category> cq = cb.createQuery( Category.class );

        Root<Category> root = cq.from( Category.class );
        // predicates list
        List<Predicate> listPredicates = new ArrayList<>( );

        if ( StringUtils.isNotBlank( name ) )
        {
            listPredicates.add( cb.equal( root.get( Category_.name ), name ) );
        }

        if ( !listPredicates.isEmpty( ) )
        {
            // add existing predicates to Where clause
            cq.where( listPredicates.toArray( new Predicate [ listPredicates.size( )] ) );
        }
        cq.distinct( true );

        TypedQuery<Category> query = em.createQuery( cq );

        return query.getResultList( );
    }

    /**
     * Find all category
     * 
     * @param orderList
     *            the order list
     * @return list of category
     */
    public ResultList<Category> findAll( List<String> orderList )
    {
        EntityManager em = getEM( );
        CriteriaBuilder cb = em.getCriteriaBuilder( );

        CriteriaQuery<Category> cq = cb.createQuery( Category.class );

        Root<Category> root = cq.from( Category.class );

        CategoryFilter filter = new CategoryFilter( );
        filter.setOrderAsc( true );
        filter.setOrders( orderList );
        buildSortQuery( filter, root, cq, cb );
        cq.distinct( true );

        return createPagedQuery( cq, null ).getResultList( );
    }
}
