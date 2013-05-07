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

import fr.paris.lutece.portal.service.rbac.Permission;
import fr.paris.lutece.portal.service.rbac.ResourceIdService;
import fr.paris.lutece.portal.service.rbac.ResourceType;
import fr.paris.lutece.portal.service.rbac.ResourceTypeManager;
import fr.paris.lutece.util.ReferenceList;

import java.util.Locale;


/**
 * RBAC Resource Id Service to allow roles on AdminUser Services
 */
public class StockResourceIdService extends ResourceIdService
{
    /** Permission for creating a product */
    public static final String PERMISSION_CREATE = "CREATE_PRODUCT";

    /** Permission for deleting a product */
    public static final String PERMISSION_DELETE = "DELETE_PRODUCT";

    /** Permission for modifying a product */
    public static final String PERMISSION_MODIFY = "MODIFY_PRODUCT";

    /** Permission for creating a category */
    public static final String PERMISSION_CREATE_CATEGORY = "CREATE_CATEGORY";

    /** Permission for deleting a category */
    public static final String PERMISSION_DELETE_CATEGORY = "DELETE_CATEGORY";

    /** Permission for modifying a category */
    public static final String PERMISSION_MODIFY_CATEGORY = "MODIFY_CATEGORY";
    public static final String PERMISSION_MANAGE = "MANAGE";
    public static final String RESOURCE_TYPE = "STOCK";
    private static final String PROPERTY_LABEL_RESOURCE_TYPE = "stock.stockService.resourceType";
    private static final String PROPERTY_LABEL_CREATE_PRODUCT = "stock.permission.label.createProduct";
    private static final String PROPERTY_LABEL_DELETE_PRODUCT = "stock.permission.label.deleteProduct";
    private static final String PROPERTY_LABEL_MODIFY_PRODUCT = "stock.permission.label.modifyProduct";
    private static final String PROPERTY_LABEL_CREATE_CATEGORY = "stock.permission.label.createCategory";
    private static final String PROPERTY_LABEL_DELETE_CATEGORY = "stock.permission.label.deleteCategory";
    private static final String PROPERTY_LABEL_MODIFY_CATEGORY = "stock.permission.label.modifyCategory";

    /**
     * Initializes the service
     */
    public void register(  )
    {
        ResourceType rt = new ResourceType(  );
        rt.setResourceIdServiceClass( StockResourceIdService.class.getName(  ) );
        rt.setResourceTypeKey( RESOURCE_TYPE );
        rt.setResourceTypeLabelKey( PROPERTY_LABEL_RESOURCE_TYPE );

        Permission p = new Permission(  );

        p.setPermissionKey( PERMISSION_CREATE );
        p.setPermissionTitleKey( PROPERTY_LABEL_CREATE_PRODUCT );
        rt.registerPermission( p );

        p = new Permission(  );
        p.setPermissionKey( PERMISSION_MODIFY );
        p.setPermissionTitleKey( PROPERTY_LABEL_MODIFY_PRODUCT );
        rt.registerPermission( p );

        p = new Permission(  );
        p.setPermissionKey( PERMISSION_DELETE );
        p.setPermissionTitleKey( PROPERTY_LABEL_DELETE_PRODUCT );
        rt.registerPermission( p );

        p = new Permission(  );
        p.setPermissionKey( PERMISSION_CREATE_CATEGORY );
        p.setPermissionTitleKey( PROPERTY_LABEL_CREATE_CATEGORY );
        rt.registerPermission( p );

        p = new Permission(  );
        p.setPermissionKey( PERMISSION_DELETE_CATEGORY );
        p.setPermissionTitleKey( PROPERTY_LABEL_DELETE_CATEGORY );
        rt.registerPermission( p );

        p = new Permission(  );
        p.setPermissionKey( PERMISSION_MODIFY_CATEGORY );
        p.setPermissionTitleKey( PROPERTY_LABEL_MODIFY_CATEGORY );
        rt.registerPermission( p );

        ResourceTypeManager.registerResourceType( rt );
    }

    /**
     * Returns a list of resource ids
     * @param locale The current locale
     * @return A list of resource ids
     */
    public ReferenceList getResourceIdList( Locale locale )
    {
        return null;
    }

    /**
     * Returns the Title of a given resource
     * @param strAdminUserId The Id of the resource
     * @param locale The current locale
     * @return The Title of a given resource
     */
    public String getTitle( String strAdminUserId, Locale locale )
    {
        return "";
    }
}
