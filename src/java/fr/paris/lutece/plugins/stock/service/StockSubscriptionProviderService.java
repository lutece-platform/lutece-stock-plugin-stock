package fr.paris.lutece.plugins.stock.service;

import fr.paris.lutece.plugins.subscribe.business.Subscription;
import fr.paris.lutece.plugins.subscribe.service.ISubscriptionProviderService;
import fr.paris.lutece.portal.service.security.LuteceUser;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;


/**
 * Subscription provider service for products
 */
public class StockSubscriptionProviderService implements ISubscriptionProviderService
{
    public static final String STOCK_SUBSCRIPTION_KEY = "stock.subscription.key";
    public static final String STOCK_PROVIDER_NAME = "stock.subscription.provider.name";

    /**
     * {@inheritDoc}
     */
    @Override
    public String getProviderName( )
    {
        return STOCK_PROVIDER_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSubscriptionHtmlDescription( LuteceUser user, String strSubscriptionKey,
            String strIdSubscribedResource, Locale locale )
    {
        return StringUtils.EMPTY;
    }

    /**
     * {@inheritDoc}
     * @return True
     */
    @Override
    public boolean isSubscriptionRemovable( LuteceUser user, String strSubscriptionKey, String strIdSubscribedResource )
    {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUrlModifySubscription( LuteceUser user, String strSubscriptionKey, String strIdSubscribedResource )
    {
        // Subscriptions can not be modified by the subscription API
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifySubscriptionRemoval( Subscription subscription )
    {
        // Nothing to do yet
    }

    @Override
    public String getSubscriptionHtmlDescriptionBis(LuteceUser arg0,
            String arg1, String arg2, Locale arg3, String arg4)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
