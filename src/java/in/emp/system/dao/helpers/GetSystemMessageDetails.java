package in.emp.system.dao.helpers;

import in.emp.system.beans.SystemMessageData;
import in.emp.dao.QueryHelper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Query helper class to get System Parameters
 *
 * @author   MDA
 */
public class GetSystemMessageDetails implements QueryHelper
{

	/** the logger object for this class
	 *
	 * @private sysMessageCd    
	 */

	private static Logger logger = Logger.getLogger(GetSystemMessageDetails.class);
	private String sysMessageCd = null;
	/**
	 * public Constructor of class GetSystemMessageDetails
	 */
	public GetSystemMessageDetails(String sysMessageCd)
	{
		this.sysMessageCd = sysMessageCd;
	}
	
	/**
	 * Public API to get Data Object.
	 *
	 * @param results			the object of ResultSet
	 * @return Object			the object of SystemParameterData
	 * @throws Exception		if an error occurs
	 */	
	public Object getDataObject(ResultSet results) throws Exception 
	{		
		SystemMessageData data = new SystemMessageData();
		try
		{
			int i  = 0;
			data.setSysMessageId(results.getLong(++i) <= 0 ? null : new Long(results.getLong(i)));
			data.setSysMessageCd(results.getString(++i) == null ? "" : results.getString(i));
			data.setSysMessage(results.getString(++i) == null ? "" : results.getString(i));
			data.setSysMessageSeverity(results.getString(++i) == null ? "" : results.getString(i));
			data.setStatusCd(results.getString(++i) == null ? "" : results.getString(i));
			data.setCreatedBy(results.getLong(++i) <= 0 ? null : new Long(results.getLong(i)));
			data.setCreatedDt(results.getDate(++i) == null ? null : results.getDate(i));
			data.setUpdatedBy(results.getLong(++i) <= 0 ? null : new Long(results.getLong(i)));
			data.setUpdatedDt(results.getDate(++i) == null ? null : results.getDate(i));	
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "GetSystemMessageDetails :: getDataObject() :: Exception :: " + ex);
			throw ex;
		}
		return data;
	}
	
	/**
	 * Public API to get Query Results
	 * @param connection			the object of Connection
	 * @return ResultSet			the object of ResultSet
	 * @throws Exception			if an error occurs
	 */
    public ResultSet getQueryResults(Connection connection) throws Exception 
	{
		PreparedStatement statement = null;
		StringBuffer sql = new StringBuffer();
		ResultSet resSet = null;
		
		try
		{	
			sql.append("SELECT MESSAGE_ID, MESSSAGE_CD, MESSAGE_DESCRIPTION,  "
				+ "MESSAGE_SEVERITY, STATUS_CD, CREATED_BY, CREATED_DT, UPDATED_BY, UPDATED_DT "
				+ "FROM SYSTEM_MESSAGE "
				+ "WHERE MESSSAGE_CD = ? "
				+ " AND STATUS_CD = 'A'");

			statement = connection.prepareStatement(sql.toString());
			statement.setString(1,sysMessageCd);
			resSet = statement.executeQuery();
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "GetSystemMessageDetails :: getQueryResults() :: Exception :: " + ex);	
			throw ex;
		}
		finally
		{
			try
			{
				sql = null;
			}
			catch (Exception ignore)
			{
			}			
		}
		return resSet;
    }
}//class ends