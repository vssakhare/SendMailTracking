/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.system.dao.helpers;

import in.emp.system.dao.MasterDTO;
import in.emp.system.dao.SecurityDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class GetOfficeLocCircleListQueryHelper implements QueryHelperSSO {

    private static Logger logger = Logger.getLogger(GetOfficeLocCircleListQueryHelper.class);
    private SecurityDTO securityDTO;

    public GetOfficeLocCircleListQueryHelper(SecurityDTO securityDTO) {
        this.securityDTO = securityDTO;
    }

    /* (non-Javadoc)
     * @see in.mahadiscom.ndm.dao.QueryHelper#getDataObject(java.sql.ResultSet)
     */
    public Object getDataObject(ResultSet results) throws Exception {
        //BillingUnitDTO billingUnitDTO = new BillingUnitDTO();
        MasterDTO masterDTO = new MasterDTO();
        try {
            logger.log(Level.INFO, "GetOfficeLocCircleListQueryHelper :: getDataObject() :: ");
//			billingUnitDTO.setBillingUnit(results.getString("BU_CODE"));
//			billingUnitDTO.setBillingUnitName(results.getString("LOCATION_NAME_C"));

            masterDTO.setId(results.getLong("LOC_ID"));
            masterDTO.setCode(results.getString("LOC_CD"));
            masterDTO.setName(results.getString("LOCATION_NAME"));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetOfficeLocCircleListQueryHelper :: getDataObject() :: Exception " + ex);
            throw ex;
        }
        return masterDTO;
    }

    /* (non-Javadoc)
     * @see in.mahadiscom.ndm.dao.QueryHelper#getQueryResults(java.sql.Connection)
     */
    public PreparedStatement getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuffer sql = new StringBuffer();
        ResultSet rs = null;
        try {
            logger.log(Level.INFO, "GetOfficeLocCircleListQueryHelper :: getQueryResults() ::");

//                        sql.append("SELECT MOL.LOCATION_ID_N LOC_ID, MOL.LOCATION_CD_C LOC_CD, MOL.LOCATION_NAME_C LOCATION_NAME "
//                                + "FROM MBC_OFFICE_LOCATION MOL "
//                                + "WHERE MOL.STATUS_CD_C = 'A' "
//                                        + "AND OFFICE_TYPE_ID_N = 2 "
//                                        + "AND MOL.LOCATION_ID_N IN ("
//                                                + "SELECT LOCATION_ID_N FROM MBC_OFFICE_LOCATION "
//                                                + "START WITH LOCATION_ID_N IN ("
//                                                        + "SELECT LOCATION_ID_N FROM MBC_OFFICE_LOCATION WHERE LOCATION_CD_C = LPAD('"+securityDTO.getOfficeLocationCode()+"',3,'0') AND OFFICE_TYPE_ID_N = '"+securityDTO.getOfficeTypeId()+"') ");
//                        if(securityDTO.getOfficeTypeId() <= 1)
//                                sql.append("CONNECT BY PRIOR LOCATION_ID_N = PARENT_LOCATION_ID_N ) ORDER BY MOL.LOCATION_NAME_C ");
//                        else
//                                sql.append("CONNECT BY PRIOR PARENT_LOCATION_ID_N = LOCATION_ID_N ) ORDER BY MOL.LOCATION_NAME_C ");


            sql.append("WITH LOCATION_VIEW AS (SELECT /*+ MATERIALIZE */ * FROM MBC_OFFICE_LOCATION) "
                    + "SELECT MOL.LOCATION_ID_N LOC_ID, MOL.LOCATION_CD_C LOC_CD, MOL.LOCATION_NAME_C LOCATION_NAME "
                    + "FROM MBC_OFFICE_LOCATION MOL "
                    + "WHERE MOL.STATUS_CD_C = 'A' "
                    + "AND OFFICE_TYPE_ID_N = 2 "
                    + "AND MOL.LOCATION_ID_N IN ("
                    + "SELECT LOCATION_ID_N FROM LOCATION_VIEW "
                    + "START WITH LOCATION_ID_N IN ("
                    + "SELECT LOCATION_ID_N FROM MBC_OFFICE_LOCATION DC_LOCATION WHERE LOCATION_CD_C = LPAD('" + securityDTO.getOfficeLocationCode() + "',3,'0') AND OFFICE_TYPE_ID_N = '" + securityDTO.getOfficeTypeId() + "') ");
            if (securityDTO.getOfficeTypeId() <= 1) {
                sql.append("CONNECT BY PRIOR LOCATION_ID_N = PARENT_LOCATION_ID_N ) ORDER BY MOL.LOCATION_NAME_C ");
            } else {
                sql.append("CONNECT BY PRIOR PARENT_LOCATION_ID_N = LOCATION_ID_N ) ORDER BY MOL.LOCATION_NAME_C ");
            }
            //System.out.println("GetOfficeLocCircleListQueryHelper : "+sql.toString());
            logger.log(Level.INFO, "GetOfficeLocCircleListQueryHelper :: getQueryResults() :: SQL" + sql.toString());
            statement = connection.prepareStatement(sql.toString());

            //rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetOfficeLocCircleListQueryHelper :: getQueryResults() :: Exception" + ex);
            throw ex;
        }
        return statement;
    }
}
