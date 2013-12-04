package fr.paris.lutece.plugins.stock.business.district;

import fr.paris.lutece.plugins.stock.service.StockPlugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.util.sql.DAOUtil;

import org.apache.commons.lang.StringUtils;


/**
 * Implementation of {@link IDistrictDAO}
 */
public class DistrictDAO implements IDistrictDAO
{
    private static final String SELECT_LIBELLE_DISTRICT = "SELECT libelle from billetterie_district WHERE id = ?";

    @Override
    public String findLibelleById( Integer id )
    {
        DAOUtil daoUtil = new DAOUtil( SELECT_LIBELLE_DISTRICT, PluginService.getPlugin( StockPlugin.PLUGIN_NAME ) );
        daoUtil.setInt( 1, id );

        daoUtil.executeQuery( );

        String result = StringUtils.EMPTY;

        if ( daoUtil.next( ) )
        {
            result = daoUtil.getString( 1 );
        }

        daoUtil.free( );

        return result;
    }
}
