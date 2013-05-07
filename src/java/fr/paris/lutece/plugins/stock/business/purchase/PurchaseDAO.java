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
package fr.paris.lutece.plugins.stock.business.purchase;

import fr.paris.lutece.plugins.stock.business.offer.Offer;
import fr.paris.lutece.plugins.stock.business.offer.OfferGenre;
import fr.paris.lutece.plugins.stock.business.offer.OfferGenre_;
import fr.paris.lutece.plugins.stock.business.offer.Offer_;
import fr.paris.lutece.plugins.stock.business.product.Product;
import fr.paris.lutece.plugins.stock.business.product.Product_;
import fr.paris.lutece.plugins.stock.commons.ResultList;
import fr.paris.lutece.plugins.stock.commons.dao.AbstractStockDAO;
import fr.paris.lutece.plugins.stock.commons.dao.PaginationProperties;
import fr.paris.lutece.plugins.stock.service.StockPlugin;
import fr.paris.lutece.plugins.stock.utils.jpa.StockJPAUtils;

import java.math.BigInteger;
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
 * This class provides Data Access methods for {@link Purchase} objects.
 * 
 * @param <K> the key type
 * @param <E> the entity type
 */
public class PurchaseDAO<K, E> extends AbstractStockDAO<Integer, Purchase>  implements
		IPurchaseDAO
{

    /*
     * (non-Javadoc)
     * 
     * @see fr.paris.lutece.portal.service.jpa.JPALuteceDAO#getPluginName()
     */
	@Override
	public String getPluginName(  )
	{
        return StockPlugin.PLUGIN_NAME;
	}

    /**
     * Find purchases by filter.
     * 
     * @param filter the filter
     * @param paginationProperties the pagination properties
     * @return list of purchases
     */
    public ResultList<Purchase> findByFilter( PurchaseFilter filter, PaginationProperties paginationProperties )
    {
        EntityManager em = getEM(  );
        CriteriaBuilder cb = em.getCriteriaBuilder(  );

        CriteriaQuery<Purchase> cq = cb.createQuery( Purchase.class );

        Root<Purchase> root = cq.from( Purchase.class );
        buildCriteriaQuery( filter, root, cq, cb );
        buildSortQuery( filter, root, cq, cb );
        cq.distinct( true );

        return createPagedQuery( cq, paginationProperties ).getResultList( );
    }
    
    /**
     * Build the criteria query used when purchases are searched by filter.
     * 
     * @param filter the filter
     * @param root the purchase root
     * @param query the criteria query
     * @param builder the criteria builder
     */
    protected void buildCriteriaQuery( PurchaseFilter filter, Root<Purchase> root, CriteriaQuery<Purchase> query,
        CriteriaBuilder builder )
    {
        // predicates list
        List<Predicate> listPredicates = new ArrayList<Predicate>(  );
        
        Join<Purchase, Offer> offer = root.join( Purchase_.offer, JoinType.INNER );
        Join<Offer, Product> product = offer.join( Offer_.product, JoinType.INNER );
        Join<Offer, OfferGenre> type = offer.join( Offer_.type, JoinType.INNER );
        
        if ( StringUtils.isNotBlank( filter.getProductName(  ) ) )
        {
            listPredicates.add( builder.like( product.get( Product_.name ),
                    StockJPAUtils.buildCriteriaLikeString( filter.getProductName(  ) ) ) );
        }

        if ( filter.getIdProduct( ) != null && filter.getIdProduct( ) > 0 )
        {
            listPredicates.add( builder.equal( product.get( Product_.id ), filter.getIdProduct( ) ) );
        }
        
        if ( filter.getIdGenre(  ) != null && filter.getIdGenre(  ) > 0 )
        {
            listPredicates.add( builder.equal( type.get( OfferGenre_.id ),
            		filter.getIdGenre(  ) ) );
        }
        
        if ( filter.getIdOffer(  ) != null && filter.getIdOffer(  ) > 0 )
        {
            listPredicates.add( builder.equal( offer.get( Offer_.id ),
            		filter.getIdOffer(  ) ) );
        }

        if ( !listPredicates.isEmpty(  ) )
        {
            // add existing predicates to Where clause
            query.where( listPredicates.toArray( new Predicate[listPredicates.size( )] ) );
        }
    }
    
    /**
     * Add the order by parameter to the query.
     * 
     * @param filter the filter
     * @param root the purchase root
     * @param query the criteria query
     * @param builder the criteria builder
     */
    protected void buildSortQuery( PurchaseFilter filter, Root<Purchase> root, CriteriaQuery<Purchase> query,
        CriteriaBuilder builder )
    {
        if ( filter.getOrders( ) != null && !filter.getOrders( ).isEmpty( ) )
        {
            List<Order> orderList = new ArrayList<Order>( );
            
            Join<Purchase, Offer> offer = root.join( Purchase_.offer, JoinType.INNER );
            Join<Offer, Product> product = offer.join( Offer_.product, JoinType.INNER );
            Join<Offer, OfferGenre> type = offer.join( Offer_.type, JoinType.INNER );

            if ( filter.isOrderAsc(  ) )
            {
                // get asc order
            	for ( String order : filter.getOrders( ) )
            	{
            		if ( order.equals( "offer.product.name" ) )
            		{
            			orderList.add( builder.asc( product.get( "name" ) ) );
            		}
            		else if ( order.equals( "offer.type.name" ) )
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
            		if ( order.equals( "offer.product.name" ) )
            		{
            			orderList.add( builder.desc( product.get( "name" ) ) );
            		}
            		else if ( order.equals( "offer.type.name" ) )
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

    /**
     * {@inheritDoc}
     */
    public Integer getQuantityPurchasedByIdProductAndUserName( Integer idProduct, Integer idOfferGenre, String userName )
	{
        EntityManager em = getEM(  );
        CriteriaBuilder cb = em.getCriteriaBuilder(  );

        CriteriaQuery<Purchase> cq = cb.createQuery( Purchase.class );

        Root<Purchase> root = cq.from( Purchase.class );
        PurchaseFilter filter = new PurchaseFilter( );
        filter.setIdProduct( idProduct );
        filter.setUserName( userName );
        filter.setIdGenre( idOfferGenre );
        buildCriteriaQuery( filter, root, cq, cb );
        cq.distinct( true );

        ResultList<Purchase> listPurchase = createPagedQuery( cq, null ).getResultList( );
        Integer numberOfReservation = 0;
        for( Purchase purchase : listPurchase )
        {
        	numberOfReservation += purchase.getQuantity( );
        }
        
        return numberOfReservation;
	}

    /*
     * (non-Javadoc)
     * 
     * @see fr.paris.lutece.plugins.stock.business.purchase.IPurchaseDAO#
     * getCountPurchaseByBeginDateAndLastDate(java.lang.String,
     * java.lang.String)
     */
    /**
     * {@inheritDoc}
     */
    public Integer getCountPurchaseByBeginDateAndLastDate( String strDateDebut, String strDateFin )
    {
        Integer result = 0;
        StringBuffer requeteSQL = new StringBuffer( );

        requeteSQL.append( "SELECT count( distinct purchase_date.owner_id)  " );
        requeteSQL.append( " FROM stock_purchase_attribute_date AS purchase_date" );
        requeteSQL.append( " WHERE purchase_date.attribute_value >= CAST('" + strDateDebut + " 00:00:00' AS DATETIME)" );
        requeteSQL.append( " AND purchase_date.attribute_value <= CAST('" + strDateFin + " 23:59:59' AS DATETIME)" );
        requeteSQL.append( " AND purchase_date.attribute_key = 'date'" );

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
}
