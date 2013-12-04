package fr.paris.lutece.plugins.stock.service;

import fr.paris.lutece.plugins.stock.business.district.IDistrictDAO;
import fr.paris.lutece.plugins.stock.service.impl.AbstractService;

import javax.inject.Inject;
import javax.inject.Named;


public class DistrictService extends AbstractService implements IDistrictService
{
    @Inject
    @Named( "stock.districtDAO" )
    private IDistrictDAO _daoDistrict;

    @Override
    public String findLibelleById( Integer id )
    {
        return _daoDistrict.findLibelleById( id );
    }

}
