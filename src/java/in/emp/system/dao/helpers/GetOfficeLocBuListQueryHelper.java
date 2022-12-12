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

public class GetOfficeLocBuListQueryHelper implements QueryHelperSSO {

	//private static Logger logger = Logger.getLogger(GetOfficeLocBuListQueryHelper.class);

	private SecurityDTO securityDTO;

	public GetOfficeLocBuListQueryHelper(SecurityDTO securityDTO) {
		this.securityDTO = securityDTO;
	}

	/* (non-Javadoc)
	 * @see in.mahadiscom.ndm.dao.QueryHelper#getDataObject(java.sql.ResultSet)
	 */
	public Object getDataObject(ResultSet results) throws Exception {
		//BillingUnitDTO billingUnitDTO = new BillingUnitDTO();
                MasterDTO masterDTO = new MasterDTO();
		try
		{
			//logger.log(Level.INFO, "GetOfficeLocBuListQueryHelper :: getDataObject() :: ");
//			billingUnitDTO.setBillingUnit(results.getString("BU_CODE"));
//			billingUnitDTO.setBillingUnitName(results.getString("LOCATION_NAME_C"));

                        masterDTO.setId(results.getLong("LOC_ID"));
			masterDTO.setCode(results.getString("BU_CODE"));
			masterDTO.setName(results.getString("LOCATION_NAME"));

		}catch(Exception ex){
			//logger.log(Level.ERROR, "GetOfficeLocBuListQueryHelper :: getDataObject() :: Exception "+ ex);
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
		try
		{
			//logger.log(Level.INFO, "GetOfficeLocBuListQueryHelper :: getQueryResults() ::");

//			sql.append(" SELECT MBOL.BU_CODE, MBOL.LOCATION_ID_N, MOL.LOCATION_CD_C, MOL.LOCATION_NAME_C	"+
//						"	FROM MBC_OFFICE_LOCATION MOL, MBC_BU_OFFICE_LOCATION MBOL	"+
//						"	WHERE MOL.LOCATION_ID_N = MBOL.LOCATION_ID_N	"+
//						"	AND MOL.STATUS_CD_C = 'A'		"+
//						"	AND MBOL.STATUS_CD_C = 'A'		"+
//						"	AND MOL.LOCATION_CD_C IN (		"+
//						"	      SELECT LOCATION_CD_C		"+
//						"	  FROM MBC_OFFICE_LOCATION		"+
//						"	  START WITH LOCATION_ID_N IN (		"+
//						"	          SELECT LOCATION_ID_N FROM MBC_OFFICE_LOCATION WHERE LOCATION_CD_C = '"+securityDTO.getOfficeLocationCode()+"' " +
//						"			  AND OFFICE_TYPE_ID_N = '"+securityDTO.getOfficeTypeId()+"'		"+
//						"	  )		"+
//						"	  CONNECT BY PRIOR LOCATION_ID_N = PARENT_LOCATION_ID_N		"+
//						"	) 		");

//                        sql.append("SELECT MBOL.BU_CODE BU_CODE, MBOL.LOCATION_ID_N LOC_ID, MOL.LOCATION_CD_C LOC_CD, MOL.LOCATION_NAME_C LOCATION_NAME "
//                                + "FROM MBC_OFFICE_LOCATION MOL, MBC_BU_OFFICE_LOCATION MBOL "
//                                + "WHERE MOL.LOCATION_ID_N = MBOL.LOCATION_ID_N "
//                                        + "AND MOL.STATUS_CD_C = 'A' "
//                                        + "AND MBOL.STATUS_CD_C = 'A' "
//                                        //+ "AND MBOL.BU_CODE in (SELECT DISTINCT(BU_C) BU_CODE FROM DC_DISCONNECTION) "
//                                        + "AND MOL.LOCATION_ID_N IN ("
//                                                + "SELECT LOCATION_ID_N FROM MBC_OFFICE_LOCATION "
//                                                + "START WITH LOCATION_ID_N IN ("
//                                                        + "SELECT LOCATION_ID_N FROM MBC_OFFICE_LOCATION WHERE LOCATION_CD_C = LPAD('"+securityDTO.getOfficeLocationCode()+"',3,'0') AND OFFICE_TYPE_ID_N = '"+securityDTO.getOfficeTypeId()+"') "
//                                                + "CONNECT BY PRIOR LOCATION_ID_N = PARENT_LOCATION_ID_N ) ORDER BY MOL.LOCATION_NAME_C ");


                       sql.append("WITH LOCATION_VIEW AS (SELECT /*+ MATERIALIZE */ * FROM MBC_OFFICE_LOCATION) "
                                + "SELECT MBOL.BU_CODE BU_CODE, MBOL.LOCATION_ID_N LOC_ID, MOL.LOCATION_CD_C LOC_CD, MOL.LOCATION_NAME_C LOCATION_NAME "
                                + "FROM MBC_OFFICE_LOCATION MOL, MBC_BU_OFFICE_LOCATION MBOL "
                                + "WHERE MOL.LOCATION_ID_N = MBOL.LOCATION_ID_N "
                                        + "AND MOL.STATUS_CD_C = 'A' "
                                        + "AND MBOL.STATUS_CD_C = 'A' "
                                        //+ "AND MBOL.BU_CODE in (SELECT DISTINCT(BU_C) BU_CODE FROM DC_DISCONNECTION) "
                                        + "AND MOL.LOCATION_ID_N IN ("
                                                + "SELECT LOCATION_ID_N FROM LOCATION_VIEW "
                                                + "START WITH LOCATION_ID_N IN ("
                                                        + "SELECT LOCATION_ID_N FROM MBC_OFFICE_LOCATION DC_LOCATION WHERE LOCATION_CD_C = LPAD('"+securityDTO.getOfficeLocationCode()+"',3,'0') AND OFFICE_TYPE_ID_N = '"+securityDTO.getOfficeTypeId()+"') "
                                                + "CONNECT BY PRIOR LOCATION_ID_N = PARENT_LOCATION_ID_N ) ORDER BY MOL.LOCATION_NAME_C ");

                        //logger.log(Level.ERROR, "GetOfficeLocBuListQueryHelper :: getQueryResults() :: SQL:: "+sql.toString()+"  id :"+securityDTO.getOfficeLocationCode());
                        statement = connection.prepareStatement(sql.toString());
			//rs = statement.executeQuery();
		}catch(Exception ex)
		{
			//logger.log(Level.ERROR, "GetOfficeLocBuListQueryHelper :: getQueryResults() :: Exception" + ex);
			throw ex;
		}
		return statement;
	}

}
