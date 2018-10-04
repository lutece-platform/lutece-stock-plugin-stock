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
package fr.paris.lutece.plugins.stock.business.attribute.provider;

import fr.paris.lutece.plugins.stock.business.attribute.AbstractAttributeId;
import fr.paris.lutece.plugins.stock.business.provider.Provider;

/**
 * Composite key
 * 
 * @author abataille
 */
public class ProviderAttributeId extends AbstractAttributeId
{

    /**  
     *
     */
    private static final long serialVersionUID = -196027664560791200L;

    private Provider _owner;

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode( )
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( _key == null ) ? 0 : _key.hashCode( ) );
        result = prime * result + ( ( _owner == null ) ? 0 : _owner.getId( ).hashCode( ) );
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass( ) != obj.getClass( ) )
            return false;
        ProviderAttributeId other = (ProviderAttributeId) obj;
        if ( _key == null )
        {
            if ( other.getKey( ) != null )
                return false;
        }
        else
            if ( !_key.equals( other.getKey( ) ) )
                return false;
        if ( _owner == null )
        {
            if ( other.getOwner( ) != null )
                return false;
        }
        else
            if ( !_owner.getId( ).equals( other.getOwner( ).getId( ) ) )
                return false;
        return true;
    }

    /**
     * @return the owner
     */
    public Provider getOwner( )
    {
        return _owner;
    }

    /**
     * @param owner
     *            the owner to set
     */
    public void setOwner( Provider owner )
    {
        this._owner = owner;
    }
}
