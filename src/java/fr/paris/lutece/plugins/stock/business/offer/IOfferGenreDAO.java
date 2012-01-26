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
package fr.paris.lutece.plugins.stock.business.offer;

import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.jpa.IGenericDAO;

import java.util.List;


/**
* IProviderDAO Interface
*/
public interface IOfferGenreDAO extends IGenericDAO<Integer, OfferGenre>
{
    /**
     * Find the offer genre by its id. If an offer genre is found, its offers will be loaded
     * @param nIdOfferGenre the id of the wanted offer genre
     * @return the {@link OfferGenre} object with the same identifier as the parameter
     */
    OfferGenre findWithOffersLinked( int nIdOfferGenre );

    /**
     * Build a {@link ReferenceList} with all the offer genre
     * @return the {@link ReferenceList} of all the offer genre
     */
    ReferenceList findAllReferenceList(  );

    /**
     * Find offers genre by their name
     * @param strOfferName the name of the offer genre
     * @return the list of all offer genre with the same name as the parameter
     */
    List<OfferGenre> findByName( String strOfferName );
}
