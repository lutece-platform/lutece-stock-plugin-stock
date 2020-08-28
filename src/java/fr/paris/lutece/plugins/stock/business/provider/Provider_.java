/*
 * Copyright (c) 2002-2020, City of Paris
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
package fr.paris.lutece.plugins.stock.business.provider;

import fr.paris.lutece.plugins.stock.business.attribute.provider.ProviderAttribute;
import fr.paris.lutece.plugins.stock.business.attribute.provider.ProviderAttributeDate;
import fr.paris.lutece.plugins.stock.business.attribute.provider.ProviderAttributeNum;
import fr.paris.lutece.plugins.stock.business.category.Category;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel( Provider.class )
public abstract class Provider_
{

    public static volatile SingularAttribute<Provider, Integer> id;
    public static volatile SingularAttribute<Provider, String> mail;
    public static volatile SetAttribute<Provider, ProviderAttributeNum> attributeNumList;
    public static volatile SetAttribute<Provider, ProviderAttribute> attributeList;
    public static volatile SingularAttribute<Provider, String> phoneNumber;
    public static volatile SingularAttribute<Provider, String> address;
    public static volatile SingularAttribute<Provider, String> contactName;
    public static volatile SingularAttribute<Provider, String> name;
    public static volatile SetAttribute<Provider, ProviderAttributeDate> attributeDateList;
    public static volatile SingularAttribute<Provider, String> comment;
    public static volatile ListAttribute<Provider, Category> products;

}
