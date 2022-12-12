package in.emp.system.dao;

import java.util.Collection;
import in.emp.system.beans.SystemMessageData;



/**
 * Data access object interface for returning document related value objects from the database.
 *
 * @author MDA
 */
public interface SystemDao
{
	/**
	 * Public API to get active system parameters
	 *
	 * @param 
	 * @return						the Collection of SystemParameterData objects
	 * @throws Exception			if an error occurs
	 */
	public Collection getAllSystemParameters() throws Exception;

	/**
	 * Gets message details from message code
	 *
	 * @param sysMessageCd				the SecGroupData object
	 * @throws	Exception				if an error occurs
	 * @see								in.mda.system.beans.SystemMessageData
	 * @return							the obj of SystemMessageData
	 */	
	public SystemMessageData getSystemMessageDetails(String sysMessageCd) throws Exception;
}//end class