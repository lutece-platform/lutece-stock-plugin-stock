/*
 * Copyright (c) 2002-2021, City of Paris
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
package fr.paris.lutece.plugins.stock.business.product;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import fr.paris.lutece.plugins.stock.commons.ResultList;
import fr.paris.lutece.plugins.stock.commons.dao.AbstractStockDAO;
import fr.paris.lutece.plugins.stock.commons.dao.PaginationProperties;
import fr.paris.lutece.plugins.stock.service.StockPlugin;
import fr.paris.lutece.plugins.stock.utils.jpa.StockJPAUtils;

/**
 * This class provides Data Access methods for {@link Product} objects.
 */
public class ProductDAO extends AbstractStockDAO<Integer, Product> implements IProductDAO
{

    /** The Constant JPQL_GET_QUANTITY. */
    private static final String JPQL_IS_TYPE = "SELECT CASE WHEN (COUNT(o.id) > 0) THEN true ELSE false END " + "FROM Product p, Offer o "
            + "WHERE p.id= o.product.id AND o.type.id = :genreId AND p.id = :productId";

    private static final String JPQL_IS_TYPE_OFFER = "SELECT CASE WHEN (COUNT(o.id) > 0) THEN true ELSE false END "
            + "FROM Product p, Offer o, OfferAttributeDate d  "
            + "WHERE p.id= o.product.id AND o.type.id = :genreId AND p.id = :productId AND o.id = d.owner.id AND d.key = :keyDate AND d.value >= :now AND o.statut <> :annuleKey";

    private static final String JPQL_GET_PRODUCTS_TASK = "SELECT p.id "
            + "FROM Product p, Offer o, OfferAttributeDate d  "
            + "WHERE p.id= o.product.id AND o.id = d.owner.id AND d.key = :keyDate AND d.value < :timestampStart AND d.value >= :timestampEnd ";

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
     * @param filter
     *            the filter
     * @return the list of product
     */
    public List<Product> findByFilter( ProductFilter filter )
    {
        EntityManager em = getEM( );
        CriteriaBuilder cb = em.getCriteriaBuilder( );

        CriteriaQuery<Product> cq = cb.createQuery( Product.class );

        Root<Product> root = cq.from( Product.class );

        buildCriteriaQuery( filter, root, cq, cb );

        cq.distinct( true );

        TypedQuery<Product> query = em.createQuery( cq );

        return query.getResultList( );
    }

    /**
     * Finds by filter.
     * 
     * @param filter
     *            the filter
     * @param paginationProperties
     *            the pagination properties
     * @return the product list
     */
    public ResultList<Product> findByFilter( ProductFilter filter, PaginationProperties paginationProperties )
    {
        EntityManager em = getEM( );
        CriteriaBuilder cb = em.getCriteriaBuilder( );

        CriteriaQuery<Product> cq = cb.createQuery( Product.class );

        Root<Product> root = cq.from( Product.class );
        buildCriteriaQuery( filter, root, cq, cb );
        buildSortQuery( filter, root, cq, cb );
        cq.distinct( true );

        return createPagedQuery( cq, paginationProperties ).getResultList( );
    }

    /**
     * Build the predicate list from filter.
     * 
     * @param filter
     *            the filter
     * @param root
     *            the product root
     * @param builder
     *            the criteria builder
     * @return the predicate list
     */
    protected List<Predicate> buildPredicates( ProductFilter filter, Root<Product> root, CriteriaBuilder builder )
    {
        // predicates list
        List<Predicate> listPredicates = new ArrayList<>( );

        if ( filter.getIdProduct( ) != null )
        {
            listPredicates.add( builder.equal( root.get( Product_.id ), filter.getIdProduct( ) ) );
        }

        return listPredicates;
    }

    /**
     * Build the criteria query from the filter.
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
    protected void buildCriteriaQuery( ProductFilter filter, Root<Product> root, CriteriaQuery<Product> query, CriteriaBuilder builder )
    {
        // predicates list
        List<Predicate> listPredicates = new ArrayList<>( );

        if ( StringUtils.isNotBlank( filter.getName( ) ) )
        {
            listPredicates.add( builder.like( root.get( Product_.name ), StockJPAUtils.buildCriteriaLikeString( filter.getName( ) ) ) );
        }

        if ( filter.getIdCategory( ) != null && filter.getIdCategory( ) > 0 )
        {
            listPredicates.add( builder.equal( root.get( Product_.category ), filter.getIdCategory( ) ) );
        }

        if ( filter.getIdProvider( ) != null && filter.getIdProvider( ) > 0 )
        {
            listPredicates.add( builder.equal( root.get( Product_.provider ), filter.getIdProvider( ) ) );
        }

        if ( !listPredicates.isEmpty( ) )
        {
            // add existing predicates to Where clause
            query.where( listPredicates.toArray( new Predicate [ listPredicates.size( )] ) );
        }
    }

    /**
     * Build the sort query.
     * 
     * @param filter
     *            the filter
     * @param root
     *            the product root
     * @param query
     *            the criteria query
     * @param builder
     *            the criteria builder
     */
    protected void buildSortQuery( ProductFilter filter, Root<Product> root, CriteriaQuery<Product> query, CriteriaBuilder builder )
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

    /*
     * (non-Javadoc)
     * 
     * @see fr.paris.lutece.plugins.stock.business.product.IProductDAO#getAllByName (java.lang.String)
     */
    /**
     * {@inheritDoc}
     */
    public List<Product> getAllByName( String name )
    {
        EntityManager em = getEM( );
        CriteriaBuilder cb = em.getCriteriaBuilder( );

        CriteriaQuery<Product> cq = cb.createQuery( Product.class );

        Root<Product> root = cq.from( Product.class );
        // predicates list
        List<Predicate> listPredicates = new ArrayList<>( );

        if ( StringUtils.isNotBlank( name ) )
        {
            listPredicates.add( cb.equal( root.get( Product_.name ), name ) );
        }

        if ( !listPredicates.isEmpty( ) )
        {
            // add existing predicates to Where clause
            cq.where( listPredicates.toArray( new Predicate [ listPredicates.size( )] ) );
        }
        cq.distinct( true );

        TypedQuery<Product> query = em.createQuery( cq );

        return query.getResultList( );
    }

    /**
     * {@inheritDoc}
     */
    public Integer getCountProductALAfficheByDate( String strDate )
    {
        Integer result = 0;
        StringBuilder requeteSQL = new StringBuilder( );

        requeteSQL.append( "SELECT count( distinct product_date_begin.owner_id)  " );
        requeteSQL.append( " FROM stock_product_attribute_date AS product_date_begin" );
        requeteSQL.append( " WHERE product_date_begin.attribute_value <= CAST('" + strDate + " 23:59:59' AS DATETIME)" );
        requeteSQL.append( " AND product_date_begin.attribute_key = 'start'" );
        requeteSQL.append( " AND EXISTS (" );
        requeteSQL.append( " SELECT product_date_end.owner_id" );
        requeteSQL.append( " FROM stock_product_attribute_date AS product_date_end " );
        requeteSQL.append( " WHERE product_date_begin.owner_id = product_date_end.owner_id" );
        requeteSQL.append( " AND product_date_end.attribute_value >= CAST('" + strDate + " 23:59:59' AS DATETIME)" );
        requeteSQL.append( " AND product_date_end.attribute_key = 'end'" );
        requeteSQL.append( ");" );

        Query query = getEM( ).createNativeQuery( requeteSQL.toString( ) );
        List<Object> listeCount = query.getResultList( );

        if ( listeCount.size( ) == 1 )
        {
            Object obj = listeCount.get( 0 );
            if ( obj != null )
            {
                BigInteger bigInt = (BigInteger) obj;
                result = bigInt.intValue( );
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Integer getCountProductAVenirByDate( String strDate )
    {
        Integer nResult = 0;
        StringBuilder requeteSQL = new StringBuilder( );

        requeteSQL.append( "SELECT count( distinct product_date_begin.owner_id)  " );
        requeteSQL.append( " FROM stock_product_attribute_date AS product_date_begin" );
        requeteSQL.append( " WHERE product_date_begin.attribute_value > CAST('" + strDate + " 23:59:59' AS DATETIME)" );
        requeteSQL.append( " AND product_date_begin.attribute_key = 'start'" );

        Query query = getEM( ).createNativeQuery( requeteSQL.toString( ) );
        List<Object> listeCount = query.getResultList( );

        if ( listeCount.size( ) == 1 )
        {
            Object obj = listeCount.get( 0 );
            if ( obj != null )
            {
                BigInteger bigInt = (BigInteger) obj;
                nResult = bigInt.intValue( );
            }
        }
        return nResult;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.paris.lutece.plugins.stock.business.offer.IOfferDAO#getQuantity(java .lang.Integer)
     */
    /**
     * {@inheritDoc}
     */
    public Boolean isFull( Integer productId )
    {

        StringBuilder requeteSQL = new StringBuilder( );

        requeteSQL.append( "SELECT sum(o.quantity) FROM stock_offer o,stock_offer_attribute_date d, stock_offer_attribute_date d2" );
        requeteSQL.append( " WHERE o.product_id= " + productId );
        requeteSQL.append( " AND o.id_offer=d.owner_id AND o.id_offer=d2.owner_id" );
        requeteSQL.append( " AND o.statut<>'annule' AND o.statut<>'verrouille'" );
        requeteSQL.append( " AND d.attribute_key ='date' AND d2.attribute_key ='hour'" );
        requeteSQL.append( " AND TIMESTAMP(CONCAT( DATE(d.attribute_value),' ', time(d2.attribute_value)))>=CURRENT_TIMESTAMP();" );

        Query query = getEM( ).createNativeQuery( requeteSQL.toString( ) );
        List<BigDecimal> listeCount = query.getResultList( );

        return CollectionUtils.isNotEmpty( listeCount ) && listeCount.get( 0 ) != null && listeCount.get( 0 ).intValue( ) == 0;
    }

    /**
     * Check if product is type of representation
     * 
     * @param genreId
     *            the genre to check
     * @return true if product is, false otherwise
     */
    public Boolean isType( Integer productId, Integer genreId )
    {
        EntityManager em = getEM( );
        Query query = em.createQuery( JPQL_IS_TYPE );
        query.setParameter( "productId", productId );
        query.setParameter( "genreId", genreId );

        return (Boolean) query.getSingleResult( );
    }

    public Boolean isTypeOffer( Integer productId, Integer genreId, String keyDate, Timestamp now, String annuleKey )
    {
        EntityManager em = getEM( );
        Query query = em.createQuery( JPQL_IS_TYPE_OFFER );
        query.setParameter( "productId", productId );
        query.setParameter( "genreId", genreId );
        query.setParameter( "keyDate", keyDate );
        query.setParameter( "now", now );
        query.setParameter( "annuleKey", annuleKey );

        return (Boolean) query.getSingleResult( );
    }

    /**
     * Returns products IDs between two timestamps
     * 
     * @param keyDate
     *            the attribute key
     * @param timestampStart
     *            the start timestamp
     * @param timestamsEnd
     *            the end timestamp
     * @return the products ids list
     */
    public List<Integer> getProductsIdsForTaskTimed( String keyDate, Timestamp timestampStart, Timestamp timestampEnd )
    {
        EntityManager em = getEM( );
        Query query = em.createQuery( JPQL_GET_PRODUCTS_TASK );
        query.setParameter( "keyDate", keyDate );
        query.setParameter( "timestampStart", timestampStart );
        query.setParameter( "timestampEnd", timestampEnd );

        return query.getResultList( );
    }
}
