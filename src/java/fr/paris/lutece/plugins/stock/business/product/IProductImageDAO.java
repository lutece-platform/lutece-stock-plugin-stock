package fr.paris.lutece.plugins.stock.business.product;

import java.io.File;


/**
 * The Interface IProductImageDAO.
 */
public interface IProductImageDAO
{

    /**
     * Save image.
     * 
     * @param idProduct the id product
     * @param fisTbImage the fis tb image
     * @param fisImage the fis image
     */
    void saveImage( Integer idProduct, File fisTbImage, File fisImage );

    /**
     * Gets the image.
     * 
     * @param idProduct the id product
     * @return the image
     */
    byte[] getImage( Integer idProduct );

    /**
     * Gets the thumbnail image.
     * 
     * @param idProduct the id product
     * @return the tb image
     */
    byte[] getTbImage( Integer idProduct );

    /**
     * Removes the image in database.
     * 
     * @param idProduct the id product
     */
    void remove( Integer idProduct );

}