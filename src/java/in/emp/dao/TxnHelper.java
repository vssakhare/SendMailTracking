package in.emp.dao;

import java.sql.Connection;
import java.util.HashMap;

/**
 * Public interface for TxnHelper
 */
public interface TxnHelper 
{   
	/**
	 * Public API to create  object.
	 * @param Connection conn
	 * @return Object
	 * @throws Exception
	 */
	public Object createObject(Connection conn) throws Exception;
    
	/**
	 * Public API to update  object.
	 * @param Connection conn
	 * @return void
	 * @throws Exception
	 */
	public void updateObject(Connection conn) throws Exception;
	
	/**
	 * Public API to delete  object.
	 * @param Connection conn
	 * @return void
	 * @throws Exception
	 */
	public void deleteObject(Connection conn) throws Exception;
 
	/**
	 * Public API to update Object Attribute
	 * @param Connection conn
	 * @return void
	 * @throws Exception
	 */
	 public void updateObjectAttribute(Connection conn,HashMap attributeMap) throws Exception;
}
