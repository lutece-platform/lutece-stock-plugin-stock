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
package fr.paris.lutece.plugins.stock.business.product;

import fr.paris.lutece.plugins.stock.commons.dao.AbstractStockDAO;
import fr.paris.lutece.plugins.stock.commons.exception.TechnicalException;
import fr.paris.lutece.plugins.stock.service.StockPlugin;

import java.io.File;
import java.io.IOException;

import javax.persistence.Query;

import org.apache.commons.io.FileUtils;

/**
 * DOCUMENT ME!
 *
 * @author abataille
 */
public class ProductImageDAO extends AbstractStockDAO<Integer, Object> implements IProductImageDAO
{

    @Override
    public String getPluginName( )
    {
        return StockPlugin.PLUGIN_NAME;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.paris.lutece.plugins.stock.business.product.IProductImageDAO#saveImage(java.lang.Integer, java.io.FileInputStream, java.io.FileInputStream)
     */
    @Override
    public void saveImage( Integer idProduct, File fisTbImage, File fisImage )
    {
        // Remove existing (or not) image for this product
        remove( idProduct );

        Query query = this.getEM( ).createNativeQuery( "INSERT INTO stock_product_image(id_product, tb_image, image) VALUES (?, ?, ?)" );
        query.setParameter( 1, idProduct );

        try
        {
            query.setParameter( 2, FileUtils.readFileToByteArray( fisTbImage ) );
            query.setParameter( 3, FileUtils.readFileToByteArray( fisImage ) );
        }
        catch( IOException e )
        {
            throw new TechnicalException( "Problème lors de l'écriture du poster en base de données : " + e.getMessage( ), e );
        }

        query.executeUpdate( );
    }

    /**
     * {@inheritDoc}
     */
    public byte [ ] getImage( Integer idProduct )
    {
        Query query = this.getEM( ).createNativeQuery( "SELECT image FROM stock_product_image WHERE id_product = ?" );
        query.setParameter( 1, idProduct );
        return (byte [ ]) query.getSingleResult( );
    }

    /**
     * {@inheritDoc}
     */
    public byte [ ] getTbImage( Integer idProduct )
    {
        Query query = this.getEM( ).createNativeQuery( "SELECT tb_image FROM stock_product_image WHERE id_product = ?" );
        query.setParameter( 1, idProduct );
        return (byte [ ]) query.getSingleResult( );
    }

    /**
     * {@inheritDoc}
     */
    public void remove( Integer idProduct )
    {
        Query query = this.getEM( ).createNativeQuery( "DELETE FROM stock_product_image WHERE id_product = ?" );
        query.setParameter( 1, idProduct );
        query.executeUpdate( );
    }
}
