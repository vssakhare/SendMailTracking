package in.emp.system.manager;

//-- Java Imports
import java.util.List;

//-- DAO Imports
import in.emp.system.dao.SystemDao;
import in.emp.system.dao.OracleSystemDao;
import in.emp.system.beans.SystemMessageData;
import in.emp.system.SystemDelegate;


//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;



/**
 * Manager for accessing and manipulating system related objects.
 *
 * @author  MDA
 */
public class SystemManager implements SystemDelegate 
{
	/** The logger object for this class */

	//private static Logger logger = new Logger(SystemManager.class.getName());
	private static Logger logger = Logger.getLogger(SystemManager.class);

	/**
	 * Gets all system parameters.
	 *
	 * @param	
	 * @throws Exception		if an error occurs
	 * @return			the list of SystemParameter
	 */	
	public List getAllSystemParameters() throws Exception
	{
		logger.log(Level.INFO, "Inside SystemManager :: getAllSystemParameters()");
		List systemParameterList = null;
		SystemDao systemDao = null;

		try
		{
			systemDao = new OracleSystemDao();
			systemParameterList = (List)systemDao.getAllSystemParameters();
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "SystemManager :: getAllSystemParameters() :: Exception :: " + ex);
			throw ex;
		}		
		finally
		{
			try
			{
				systemDao = null;
			}
			catch (Exception ignore){}
		}
		return systemParameterList;
	}

	/**
	 * Gets message details from message code
	 *
	 * @param sysMessageCd				the SecGroupData object
	 * @throws Exception				if an error occurs
	 * @see						in.mda.system.beans.SystemMessageData
	 * @return					the object of SystemMessageData
	 */	
	public SystemMessageData getSystemMessageDetails(String sysMessageCd) throws Exception
	{
		SystemMessageData systemMessageData = null;
		SystemDao systemDao = null;
		try
		{
			systemDao = new OracleSystemDao();
			systemMessageData = systemDao.getSystemMessageDetails(sysMessageCd);
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "SystemManager :: getSystemMessageDetails() :: Exception :: " + ex);
			throw ex;
		}
		finally
		{
			try
			{
				systemDao = null;
			}
			catch (Exception ignore){}
		}
		return systemMessageData;
	}
}//-- End class