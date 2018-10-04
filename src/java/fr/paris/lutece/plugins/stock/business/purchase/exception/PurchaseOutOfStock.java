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
package fr.paris.lutece.plugins.stock.business.purchase.exception;

/**
 * Exception thrown when quantity available for offer is not enough
 */
public class PurchaseOutOfStock extends PurchaseException
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -52377360163133736L;
    /** The purchase id. */
    private Integer _purchaseId;

    /** The quantity requested. */
    private Integer _quantityRequested;

    /** The quantity available. */
    private Integer _quantityAvailable;

    /**
     * Instantiates a new purchase out of stock.
     * 
     * @param purchaseId
     *            the purchase id
     * @param msg
     *            the msg
     */
    public PurchaseOutOfStock( Integer purchaseId, String msg )
    {
        super( purchaseId, msg );
        this._purchaseId = purchaseId;
    }

    /**
     * Instantiates a new purchase out of stock.
     * 
     * @param purchaseId
     *            the purchase id
     * @param quantityRequested
     *            the quantity requested
     * @param quantityAvailable
     *            the quantity available
     * @param msg
     *            the msg
     */
    public PurchaseOutOfStock( Integer purchaseId, Integer quantityRequested, Integer quantityAvailable, String msg )
    {
        super( purchaseId, msg );
        this._purchaseId = purchaseId;
        this.setQuantityRequested( quantityRequested );
        this.setQuantityAvailable( quantityAvailable );
    }

    /**
     * Gets the purchase id.
     * 
     * @return the purchaseId
     */
    public Integer getPurchaseId( )
    {
        return _purchaseId;
    }

    /**
     * Sets the purchase id.
     * 
     * @param purchaseId
     *            the purchaseId to set
     */
    public void setPurchaseId( Integer purchaseId )
    {
        this._purchaseId = purchaseId;
    }

    /**
     * Gets the quantity requested.
     * 
     * @return the quantityRequested
     */
    public Integer getQuantityRequested( )
    {
        return _quantityRequested;
    }

    /**
     * Sets the quantity requested.
     * 
     * @param quantityRequested
     *            the quantityRequested to set
     */
    public void setQuantityRequested( Integer quantityRequested )
    {
        this._quantityRequested = quantityRequested;
    }

    /**
     * Gets the quantity available.
     * 
     * @return the quantityAvailable
     */
    public Integer getQuantityAvailable( )
    {
        return _quantityAvailable;
    }

    /**
     * Sets the quantity available.
     * 
     * @param quantityAvailable
     *            the quantityAvailable to set
     */
    public void setQuantityAvailable( Integer quantityAvailable )
    {
        this._quantityAvailable = quantityAvailable;
    }

}
