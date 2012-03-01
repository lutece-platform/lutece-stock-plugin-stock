/*
 * Copyright (c) 2002-2012, Mairie de Paris
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
package fr.paris.lutece.plugins.stock.utils.constants;



/**
 * This class provides the constants of this plugin
 */
public final class StockConstants
{
    public static final String EMPTY_STRING = "";
    public static final int UNDEFINED = -1;
    public static final String RIGHT_MANAGE_STOCK = "STOCK_MANAGEMENT";

    // Parameters
    public static final String PARAMETER_BUTTON_CANCEL = "cancel";
    public static final String PARAMETER_PRODUCT_PRICE = "product_price";
    public static final String PARAMETER_PRODUCT_QUANTITY = "product_quantity";

    // Messages
    public static final String MESSAGE_ERROR_PHONE_NUMBER_FORMAT = "stock.error.message.phoneNumber.format";
    public static final String MESSAGE_CONFIRMATION_DELETE_PROVIDER = "stock.message.deleteProvider.confirmation";
    public static final String MESSAGE_ERROR_CATEGORY_ID = "stock.message.categoryId.error";
    public static final String MESSAGE_ERROR_OCCUR = "stock.message.error.errorOccur";
    public static final String MESSAGE_ERROR_NAN = "stock.message.error.nan";

    // Marks
    public static final String MARK_CATEGORY = "category";
    public static final String MARK_JSP_BACK = "jsp_back";

    // FILTERS
    public static final String PARAMETER_FILTER = "filter";
    public static final String PARAMETER_FILTER_NAME = "filter_name";
    public static final String PARAMETER_FILTER_PRICE = "filter_price";
    public static final String PARAMETER_FILTER_QUANTITY = "filter_quantity";

    public static final String HTML_SPACE = "&nbsp;";


    /**
     * Private constructor
     */
    private StockConstants(  )
    {
    }
}
