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

import fr.paris.lutece.plugins.stock.business.purchase.IPurchaseDTO;
import fr.paris.lutece.plugins.stock.business.purchase.exception.PurchaseSessionExpired;
import fr.paris.lutece.plugins.stock.business.purchase.exception.PurchaseUnavailable;
import fr.paris.lutece.plugins.stock.utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 * Singleton spring. Store active purchase (not yet stored into database) and
 * allow to reserve some.
 * @author aBataille
 */
public class PurchaseSessionManager implements IPurchaseSessionManager
{
    public static final Logger LOG = Logger.getLogger( PurchaseSessionManager.class );
    private static final SimpleDateFormat format = new SimpleDateFormat( "dd/MM/yyyy HH:mm" );

    @Inject
    @Named( "stock.offerService" )
    private IOfferService _offerService;

    // private static PurchaseSession instance; SINGLETON SPRING

    // private PurchaseSession(){
    // }

    // public static PurchaseSession getInstance(){
    // if(instance == null){
    // instance = new PurchaseSession( );
    // }
    // return instance;
    // }

    /**
     * Purchase idle quantity by offer
     */
    private Map<Integer, Integer> _idleQuantity = new HashMap<Integer, Integer>( );

    /**
     * Store active purchases by session id
     */
    private Map<String, List<IPurchaseDTO>> _activePurchaseBySession = new HashMap<String, List<IPurchaseDTO>>( );

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.paris.lutece.plugins.stock.service.IPurchaseSession#reserve(fr.paris
     * .lutece.plugins.stock.business.purchase.IPurchaseDTO, java.lang.Long)
     */
    /**
     * {@inheritDoc}
     */
    public synchronized void reserve( String sessionId, IPurchaseDTO purchase ) throws PurchaseUnavailable
    {
        Integer offerId = purchase.getOfferId( );
        //place restantes
        Integer qttInDb = _offerService.getQuantity( offerId );
        //place actuellement reservé en session
        Integer qttIdle = _idleQuantity.get( offerId );
        Integer qttAvailable;

        if ( qttIdle == null )
        {
            qttIdle = 0;
        }

        // Quantité disponible = quantité en base - quantité réservée en attente
        qttAvailable = qttInDb - qttIdle;

        if ( ( qttAvailable - purchase.getQuantity( ) ) < 0 )
        {
            throw new PurchaseUnavailable( offerId, "Quantité restante insuffisante (" + qttAvailable + ")" );
        }
        // Quantité disponible ok
        qttIdle = qttIdle + purchase.getQuantity( );
        _idleQuantity.put( offerId, qttIdle );

        // Ajout de l'achat dans la liste
        addPurchase( sessionId, purchase );
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.paris.lutece.plugins.stock.service.IPurchaseSession#hasReserved(java
     * .lang.Long, fr.paris.lutece.plugins.stock.business.purchase.IPurchaseDTO)
     */
    /**
     * {@inheritDoc}
     */
    public void checkReserved( String sessionId, IPurchaseDTO purchase ) throws PurchaseSessionExpired
    {
        boolean hasReserved = false;
        synchronized ( _activePurchaseBySession )
        {
            if ( _activePurchaseBySession.get( sessionId ) != null )
            {
                for ( IPurchaseDTO purchaseIdle : _activePurchaseBySession.get( sessionId ) )
                {
                    if ( purchaseIdle.getOfferId( ).equals( purchase.getOfferId( ) )
                            && purchaseIdle.getQuantity( ).equals( purchase.getQuantity( ) ) )
                    {
                        hasReserved = true;
                    }
                }
            }
            if ( !hasReserved )
            {
                throw new PurchaseSessionExpired( purchase.getOfferId( ), "Aucune session d'achat trouvée (sid="
                        + sessionId + ", id offre=" + purchase.getOfferId( ) + ", qtt=" + purchase.getQuantity( ) + ")" );
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.paris.lutece.plugins.stock.service.IPurchaseSession#release(java.lang
     * .Long, fr.paris.lutece.plugins.stock.business.purchase.IPurchaseDTO)
     */
    /**
     * {@inheritDoc}
     */
    public synchronized void release( String sessionId, IPurchaseDTO purchase )
    {
        synchronized ( _activePurchaseBySession )
        {
            if ( _activePurchaseBySession.get( sessionId ) != null )
            {
                Iterator<IPurchaseDTO> itIdlePurchase = _activePurchaseBySession.get( sessionId ).iterator( );
                IPurchaseDTO purchaseIdle;
                while ( itIdlePurchase.hasNext( ) )
                {
                    purchaseIdle = itIdlePurchase.next( );
                    if ( purchaseIdle.getOfferId( ).equals( purchase.getOfferId( ) ) )
                    {
                        _idleQuantity.put( purchaseIdle.getOfferId( ), _idleQuantity.get( purchaseIdle.getOfferId( ) )
                                - purchaseIdle.getQuantity( ) );
                        itIdlePurchase.remove( );
                        break;
                    }
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.paris.lutece.plugins.stock.service.IPurchaseSession#releaseAll(java
     * .lang.Long)
     */
    /**
     * {@inheritDoc}
     */
    public synchronized void releaseAll( String sessionId )
    {
        synchronized ( _activePurchaseBySession )
        {
            if ( _activePurchaseBySession.get( sessionId ) != null )
            {
                Iterator<IPurchaseDTO> itIdlePurchase = _activePurchaseBySession.get( sessionId ).iterator( );
                IPurchaseDTO purchaseIdle;
                while ( itIdlePurchase.hasNext( ) )
                {
                    purchaseIdle = itIdlePurchase.next( );
                    _idleQuantity.put( purchaseIdle.getOfferId( ), _idleQuantity.get( purchaseIdle.getOfferId( ) )
                            - purchaseIdle.getQuantity( ) );
                    itIdlePurchase.remove( );
                }
            }
            _activePurchaseBySession.remove( sessionId );
        }
    }

    /**
     * Add purchase for user
     * @param sessionId session id
     * @param purchase purchase
     */
    private void addPurchase( String sessionId, IPurchaseDTO purchase )
    {
        synchronized ( _activePurchaseBySession )
        {
            if ( _activePurchaseBySession.get( sessionId ) == null )
            {
                _activePurchaseBySession.put( sessionId, new ArrayList<IPurchaseDTO>( ) );
            }
            else
            {
                // Si un achat sur la même offre est déjà en attente on le supprime
                for ( IPurchaseDTO purchaseIdle : _activePurchaseBySession.get( sessionId ) )
                {
                    if ( purchaseIdle.getOfferId( ).equals( purchase.getOfferId( ) ) )
                    {
                        if ( LOG.isDebugEnabled( ) )
                        {
                            LOG.debug( "Achat pour le produit id " + purchase.getOfferId( )
                                    + " déjà en cours sur la session " + sessionId
                                    + " - suppression de l'achat en attente" );
                        }
                        release( sessionId, purchase );
                        break;
                    }
                }
            }

            _activePurchaseBySession.get( sessionId ).add( purchase );
        }
    }

    @Override
    public Integer updateQuantityWithSession( Integer quantity, Integer offerId )
    {
        //quantité actuellement reservé en session
        Integer qttIdle = _idleQuantity.get( offerId );
        int quantityCopie = quantity;

        //si il existe en session une quantité déjà reservé pour l'offre, on doit la retirer de la quantité disponible pour l'offre 
        if ( qttIdle != null )
        {
            quantityCopie -= qttIdle;
        }

        if ( quantity < 0 )
        {
            quantityCopie = 0;
        }

        return quantityCopie;
    }

    @Override
    public void clearPurchase( Integer minutes )
    {
        synchronized ( _activePurchaseBySession )
        {
            //itération des liste de réservations pour chaque session
            for ( Entry<String, List<IPurchaseDTO>> entry : _activePurchaseBySession.entrySet( ) )
            {
                String idSession = entry.getKey( );
                List<IPurchaseDTO> listPurchase = _activePurchaseBySession.remove( idSession );
                List<IPurchaseDTO> listPurchaseValide = new ArrayList<IPurchaseDTO>( );
                for ( IPurchaseDTO purchase : listPurchase )
                {
                    if ( shouldBeKeep( purchase, minutes ) )
                    {
                        listPurchaseValide.add( purchase );
                    }
                }
                listPurchase.clear( );
                _activePurchaseBySession.put( idSession, listPurchaseValide );
            }
        }
    }

    /**
     * Check if a purchase should be keep in session
     * @param purchase the purchase to check
     * @param minutes the number max of minutes to keep purchase
     * @return true to keep, false to remove
     */
    private boolean shouldBeKeep( IPurchaseDTO purchase, Integer minutes )
    {
        boolean toKeep = true;
        String dateCreate = purchase.getDate( );
        String hourCreate = purchase.getHeure( );
        //si ces deux attributs ne sont pas présent, la reservation n'a pas été faite sur le FO et n'est donc pas concernée.
        if ( StringUtils.isNotBlank( dateCreate ) && StringUtils.isNotBlank( hourCreate ) )
        {
            try
            {
                Date datePurchase = format.parse( dateCreate + " " + hourCreate );
                Date currentDate = DateUtils.getCurrentDate( );
                GregorianCalendar calendarPurchase = new GregorianCalendar( );
                calendarPurchase.setTime( datePurchase );
                calendarPurchase.add( Calendar.MINUTE, minutes );
                if ( calendarPurchase.getTime( ).before( currentDate ) )
                {
                    toKeep = false;
                }
            }
            catch ( ParseException e )
            {
                LOG.error( "Erreur de conversion de string => date : " + e );
            }
        }
        return toKeep;
    }
}
