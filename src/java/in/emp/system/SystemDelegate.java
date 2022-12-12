package in.emp.system;

//-- Java Imports
import java.util.List;

//-- MPD Imports
import in.emp.system.manager.SystemManager;
import in.emp.system.beans.SystemMessageData;


/**
 * Interface for SystemManager.
 *
 * @author  MDA
 */
public interface SystemDelegate 
{
		
	/**
	 * Gets all system parameters.
	 *
	 * @param	
	 * @throws Exception		if an error occurs
	 * @return					the list of SystemParameter
	 */	
	public List getAllSystemParameters() throws Exception;

	/**
	 * Gets message details from message code
	 *
	 * @param sysMessageCd				the SecGroupData object
	 * @throws Exception				if an error occurs
	 * @see						in.scheduler.system.beans.SystemMessageData
	 * @return					the object of SystemMessageData
	 */	
	public SystemMessageData getSystemMessageDetails(String sysMessageCd) throws Exception;

}//-- End class