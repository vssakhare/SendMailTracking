package in.emp.system.dao;

import java.util.Collection;

import in.emp.dao.BaseDao;
import in.emp.system.beans.SystemMessageData;
import in.emp.system.dao.helpers.GetAllSystemParametersQueryHelper;
import in.emp.system.dao.helpers.GetSystemMessageDetails;

/**
 * Data access object for returning compound related value objects from the Oracle database. 
 *
 * @author   MDA
 */
public class OracleSystemDao extends BaseDao implements SystemDao 
{	
	/**
	 * Public API to get active system parameters
	 *
	 * @return				the Collection of SystemParameterData objects
	 * @throws DaoException	 if an error occurs
	 */
	public Collection getAllSystemParameters() throws Exception
	{
		return getObjectList(new GetAllSystemParametersQueryHelper());		
	}

	/**
	 * Gets message details from message code
	 *
	 * @param sysMessageCd			the SecGroupData object
	 * @throws Exception			if an error occurs
	 * @see							in.mda.system.beans.SystemMessageData
	 * @return						the obj of SystemMessageData
	 */	
	public SystemMessageData getSystemMessageDetails(String sysMessageCd) throws Exception
	{
		return (SystemMessageData)getDataObject(new GetSystemMessageDetails(sysMessageCd));	
	}

}//end class