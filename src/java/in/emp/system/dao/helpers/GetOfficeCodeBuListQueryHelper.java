/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.system.dao.helpers;

/**
 *
 * @author admin
 */
import in.emp.system.dao.MasterDTO;
import in.emp.system.dao.SecurityDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetOfficeCodeBuListQueryHelper implements QueryHelperSSO {

	//private static Logger logger = Logger.getLogger(GetOfficeCodeBuListQueryHelper.class);

	private SecurityDTO securityDTO;

	public GetOfficeCodeBuListQueryHelper(SecurityDTO securityDTO) {
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
			//logger.log(Level.INFO, "GetOfficeCodeBuListQueryHelper :: getDataObject() :: ");
//			billingUnitDTO.setBillingUnit(results.getString("BU_CODE"));
//			billingUnitDTO.setBillingUnitName(results.getString("LOCATION_NAME_C"));

                        masterDTO.setId(results.getLong("LOC_ID"));
			masterDTO.setCode(results.getString("BU_CODE"));
			masterDTO.setName(results.getString("LOCATION_NAME"));

		}catch(Exception ex){
			//logger.log(Level.ERROR, "GetOfficeCodeBuListQueryHelper :: getDataObject() :: Exception "+ ex);
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
		//	logger.log(Level.INFO, "GetOfficeCodeBuListQueryHelper :: getQueryResults() ::");

//			sql.append("SELECT MBOL.BU_CODE BU_CODE, MBOL.LOCATION_ID_N LOC_ID, MOL.LOCATION_CD_C LOC_CD, MOL.LOCATION_NAME_C LOCATION_NAME "
//                                + "FROM HRMS_SECTION_CODE_MASTER HSCM, MBC_OFFICE_LOCATION MOL, MBC_BU_OFFICE_LOCATION MBOL "
//                                + "WHERE HSCM.SUB_DIVISION_CODE = MOL.LOCATION_CD_C "
//                                + "AND MOL.LOCATION_ID_N = MBOL.LOCATION_ID_N "
//                                + "AND MOL.STATUS_CD_C = 'A' AND MBOL.STATUS_CD_C = 'A' "
//                                + "AND HSCM.OFFICE_CODE = '"+securityDTO.getOfficeCode()+"'");

                    sql.append("SELECT MBOL.BU_CODE BU_CODE, MBOL.LOCATION_ID_N LOC_ID, MOL.LOCATION_CD_C LOC_CD, MOL.LOCATION_NAME_C LOCATION_NAME "
                                + "FROM HRMS_SECTION_CODE_MASTER HSCM, MBC_OFFICE_LOCATION MOL, MBC_BU_OFFICE_LOCATION MBOL "
                                + "WHERE HSCM.SUB_DIVISION_CODE = MOL.LOCATION_CD_C "
                                + "AND MOL.LOCATION_ID_N = MBOL.LOCATION_ID_N "
                                + "AND MOL.STATUS_CD_C = 'A' AND MBOL.STATUS_CD_C = 'A' "
                                + "AND HSCM.OFFICE_CODE = '"+securityDTO.getOfficeCode()+"'");

                    System.out.println("GetOfficeCodeBuListQueryHelper : "+sql.toString());

			statement = connection.prepareStatement(sql.toString());

			//rs = statement.executeQuery();
		}catch(Exception ex)
		{
		//	logger.log(Level.ERROR, "GetOfficeCodeBuListQueryHelper :: getQueryResults() :: Exception" + ex);
			throw ex;
		}
		return statement;
	}

}
