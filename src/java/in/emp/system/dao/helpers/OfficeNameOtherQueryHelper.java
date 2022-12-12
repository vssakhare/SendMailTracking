/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.system.dao.helpers;

import in.emp.system.dao.SecurityDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
/**
 *
 * @author admin
 */
public class OfficeNameOtherQueryHelper implements QueryHelperSSO {

	private static Logger logger = Logger.getLogger(OfficeNameOtherQueryHelper.class);

	private SecurityDTO securityDTO;

	/**
	 * @param securityDTO
	 */
	public OfficeNameOtherQueryHelper(SecurityDTO securityDTO) {
		this.securityDTO = securityDTO;
	}

	/* (non-Javadoc)
	 * @see in.mahadiscom.ndm.dao.QueryHelper#getDataObject(java.sql.ResultSet)
	 */
	public Object getDataObject(ResultSet results) throws Exception {
		String officeName;
		try{
			logger.log(Level.INFO, "OfficeNameOtherQueryHelper :: getDataObject() ::");
			officeName = results.getString("OFFICE_NAME");

		}catch(Exception ex){
			logger.log(Level.ERROR, "OfficeNameOtherQueryHelper :: getDataObject() :: Exception" + ex);
			throw ex;
		}
		return officeName;
	}

	/* (non-Javadoc)
	 * @see in.mahadiscom.ndm.dao.QueryHelper#getQueryResults(java.sql.Connection)
	 */
	public PreparedStatement getQueryResults(Connection connection) throws Exception {
		PreparedStatement statement = null;
		StringBuffer sql = new StringBuffer();
		ResultSet rs = null;

		try{
			sql.append(" SELECT HSCM.OFFICE_NAME	"+
					" FROM HRMS_SECTION_CODE_MASTER HSCM	"+
					" WHERE HSCM.OFFICE_CODE = '"+securityDTO.getOfficeCode()+"' ");

                        System.out.println("sql for office name : "+sql.toString()+" iffice id : "+securityDTO.getOfficeCode());
			logger.debug(sql.toString());
			statement = connection.prepareStatement(sql.toString());
			//rs = statement.executeQuery();
		}catch(Exception ex){
			logger.log(Level.ERROR, "OfficeNameOtherQueryHelper :: getQueryResults() :: Exception :: " + ex);
			throw ex;
		}
		return statement;
	}

}
