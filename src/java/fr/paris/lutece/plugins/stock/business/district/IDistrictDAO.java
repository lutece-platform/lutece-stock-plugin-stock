package fr.paris.lutece.plugins.stock.business.district;

/**
 * DAO interface for district
 */
public interface IDistrictDAO
{
    /**
     * Get district libelle by id
     * @param id the id of the district search
     * @return the District
     */
    String findLibelleById( Integer id );
}
