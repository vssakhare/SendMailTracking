/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.system.dao.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;

/**
 * Public interface for TxnHelper
 */
public interface TxnHelperSSO
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
	 public void updateObjectAttribute(Connection conn, HashMap attributeMap) throws Exception;

         public Object updateA1Object(Connection connection);
}
