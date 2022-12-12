package in.emp.system.dao.helpers;

import in.emp.system.beans.SystemParameterData;
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
public class GetAllSystemParametersQueryHelper implements QueryHelper
{

	/** the logger object for this class */
	//private static Logger logger = new Logger(GetAllSystemParametersQueryHelper.class.getName());
	private static Logger logger = Logger.getLogger(GetAllSystemParametersQueryHelper.class);


	/**
	 * public Constructor of class GetAllSystemParametersQueryHelper
	 */
	public GetAllSystemParametersQueryHelper()
	{
	}
	
	/**
	 * Public API to get Data Object.
	 *
	 * @param results		the object of ResultSet
	 * @return Object		the object of SystemParameterData
	 * @throws Exception		if an error occurs
	 */							
	public Object getDataObject(ResultSet results) throws Exception 
	{		
		SystemParameterData data = new SystemParameterData();
		try
		{
			int i  = 0;
			data.setParameterId(results.getLong(++i) <= 0 ? null : new Long(results.getLong(i)));
			data.setParameterName(results.getString(++i) == null ? "" : results.getString(i));
			data.setParameterValue(results.getString(++i) == null ? "" : results.getString(i));
			data.setStatusCd(results.getString(++i) == null ? "" : results.getString(i));
			data.setCreatedBy(results.getLong(++i) <= 0 ? null : new Long(results.getLong(i)));
			data.setCreatedDt(results.getDate(++i) == null ? null : results.getDate(i));
			data.setUpdatedBy(results.getLong(++i) <= 0 ? null : new Long(results.getLong(i)));
			data.setUpdatedDt(results.getDate(++i) == null ? null : results.getDate(i));	
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "GetAllSystemParametersQueryHelper :: getDataObject() :: Exception :: " + ex);
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
			sql.append("SELECT PARAMETER_ID, PARAMETER_NAME, PARAMETER_VALUE, "
				+ "STATUS_CD, CREATED_BY, CREATED_DT, UPDATED_BY, UPDATED_DT "
				+ "FROM SYSTEM_PARAMETER "
				+ "WHERE STATUS_CD = 'A' ORDER BY PARAMETER_ID");

			statement = connection.prepareStatement(sql.toString());
			resSet = statement.executeQuery();
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "GetAllSystemParametersQueryHelper :: getQueryResults() :: Exception :: " + ex);	
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