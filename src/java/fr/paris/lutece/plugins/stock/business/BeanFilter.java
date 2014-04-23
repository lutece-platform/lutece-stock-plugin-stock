package fr.paris.lutece.plugins.stock.business;

import java.util.List;

public interface BeanFilter {
    /**
     * Set the order way.
     * 
     * @param isOrderAsc true if ascending false if descending
     */
    void setOrderAsc( boolean isOrderAsc );

    /**
     * Return true if the order is ascending.
     * 
     * @return true if ascending false if descending
     */
    boolean isOrderAsc(  );
    
    /**
     * Get the order column name.
     * 
     * @return the column name
     */
    List<String> getOrders(  );
    
    /**
     * Set the column to order.
     * 
     * @param orders the column name
     */
    void setOrders( List<String> orders );
}
