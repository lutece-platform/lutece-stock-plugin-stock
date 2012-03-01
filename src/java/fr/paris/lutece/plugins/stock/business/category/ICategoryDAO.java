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
package fr.paris.lutece.plugins.stock.business.category;

import fr.paris.lutece.plugins.stock.commons.ResultList;
import fr.paris.lutece.plugins.stock.commons.dao.PaginationProperties;
import fr.paris.lutece.util.jpa.IGenericDAO;

import java.util.List;


/**
* IPersonDAO Interface
*/
public interface ICategoryDAO extends IGenericDAO<Integer, Category>
{
    /**
     * Finds all first level categories (i.e. parent == null ) and fetch children
     * @return all first level categories
     */
    List<Category> selectAllFirstLevelWithChildrenWithProduct(  );

    /**
     * Finds all category's children and fetch children
     * @param idCategory the id of upper category
     * @return all category's children
     */
    List<Category> selectAllChildrenWithChildrenWithProduct( Integer idCategory );

    /**
     * Finds a category and all his subcategories  and fetch children
     * @param idCategory the id of upper category
     * @return the category
     */
    Category findByIdWithChildren( Integer idCategory );

    /**
     * Finds a category and fetch parent
     * @param idCategory the id of the searched category
     * @return the category
     */
    Category findByIdWithParent( Integer idCategory );

    /**
     * Finds a category and fetch products
     * @param idCategory the id of the searched category
     * @return the category
     */
    Category findByIdWithProduct( Integer idCategory );

    /**
     * Finds by filter and fetch children
     * @param filter the filter
     * @return the category list
     */
    List<Category> findByFilterWithChildren( CategoryFilter filter );

    /**
     * 
     * Finds by filter
     * @param filter the filter
     * @param paginationProperties properties for pagination
     * @return the category list
     */
    ResultList<Category> findByFilter( CategoryFilter filter, PaginationProperties paginationProperties );

    /**
     * 
     * Finds by filter
     * @param filter the filter
     * @return the category list
     */
    List<Category> findByFilter( CategoryFilter filter );

    /**
     * Returns categories by name
     * @param name category name
     * @return list of categories
     */
    List<Category> getAllByName( String name );

    /**
     * Return all category
     * @param orderList the order list
     * @return the list of all category
     */
    ResultList<Category> findAll( List<String> orderList );
}
