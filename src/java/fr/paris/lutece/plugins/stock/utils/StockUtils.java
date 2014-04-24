/*
 * Copyright (c) 2002-2014, Mairie de Paris
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
package fr.paris.lutece.plugins.stock.utils;

import fr.paris.lutece.portal.service.util.AppLogService;

import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;


/**
 * This class provides utils methodes
 */
public final class StockUtils
{
    public static final String EMPTY_STRING = "";
    public static final String MESSAGE_ERROR_OCCUR = "stock.message.error.errorOccur";

    /**
     * Private constructor
     */
    private StockUtils(  )
    {
    }

    /**
     * Converts a String into Integer
     * @param strToConvert The {@link String} object to convert into {@link Integer}
     * @return The {@link Integer} object of the given {@link String} object or null if an error occured
     */
    public static Integer getInteger( String strToConvert )
    {
        if ( strToConvert != null )
        {
            try
            {
                return Integer.parseInt( strToConvert );
            }
            catch ( NumberFormatException nfe )
            {
                AppLogService.error( nfe );
            }
        }

        return null;
    }

    /**
     * Converts a String into Float
     * @param strToConvert The {@link String} object to convert into {@link Float}
     * @return The {@link Float} object of the given {@link String} object or null if an error occured
     */
    public static Float getFloat( String strToConvert )
    {
        if ( strToConvert != null )
        {
            try
            {
                return Float.parseFloat( strToConvert );
            }
            catch ( NumberFormatException nfe )
            {
                AppLogService.error( nfe );
            }
        }

        return null;
    }

    /**
    * Get the hour from String date
    * @param strHour the date to format
    * @param locale The Locale
    * @return The Timestamp or null else
    */
    public static Timestamp formatHour( String strHour, Locale locale )
    {
        Date date = null;

        if ( strHour != null )
        {
            DateFormat dateFormat = null;

            if ( locale != null )
            {
                String strLocalizedDateFormat = "HH:mm";

                if ( ( strLocalizedDateFormat != null ) && !strLocalizedDateFormat.equals( "" ) )
                {
                    dateFormat = new SimpleDateFormat( strLocalizedDateFormat );
                }
                else
                {
                    dateFormat = DateFormat.getDateInstance( DateFormat.SHORT, locale );
                }
            }
            else
            {
                dateFormat = DateFormat.getDateInstance( DateFormat.SHORT, Locale.getDefault(  ) );
            }

            dateFormat.setLenient( false );

            try
            {
                date = dateFormat.parse( strHour );
            }
            catch ( ParseException e )
            {
                return null;
            }
        }

        if ( date != null )
        {
            return new Timestamp( date.getTime(  ) );
        }
        else
        {
            return null;
        }
    }
}
