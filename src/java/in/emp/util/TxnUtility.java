package in.emp.util;

import in.emp.util.ApplicationUtils;
import java.sql.*;
//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;




public class TxnUtility 
{
	/** The logger object for this class */
	//private static Logger logger = new Logger(TxnUtility.class.getName());
	private static Logger logger = Logger.getLogger(TxnUtility.class);


	/**
	 * Create a new transaction and associate it with the current thread
	 * 
	 * @throws Exception
	 *             if an error occurs
	 */
	public static Connection beginTransaction() throws Exception 
	{
		Connection conn;
		try 
		{
			conn = ApplicationUtils.getConnection();

			if(conn == null)
				throw new Exception("connection is null");

			//System.out.println("TxnUtility :: beginTransaction :: Connection Established");
			logger.log(Level.INFO,"TxnUtility :: beginTransaction :: Connection Established");
		} 
		catch (Exception e) 
		{
			logger.log(Level.ERROR, "TxnUtility :: beginTransaction() :: Exception  :: " + e);
			throw e;
		}

		return conn;
	} // End Of Method

	/**
	 * Complete the transaction associated with the current thread
	 * 
	 * @throws Exception
	 *             if an error occurs
	 */
	public static void commitTransaction(Connection conn) throws Exception 
	{
		try 
		{
			if(conn != null)
			{
				conn.commit();
				closeConnection(conn);
				//System.out.println("TxnUtility :: commitTransaction() :: Transaction Done");
				logger.log(Level.INFO,"TxnUtility :: commitTransaction() :: Transaction Done");
			}
			else
				throw new Exception("connection is null");		
		} 
		catch (Exception e) 
		{
			logger.log(Level.ERROR, "TxnUtility :: commitTransaction() :: Exception  :: " + e);
			throw e;
		}
	} // End Of Method

	/**
	 * Roll back the transaction associated with the current thread
	 * 
	 * @throws Exception
	 *             if an error occurs
	 */
	public static void rollbackTransaction(Connection conn) throws Exception 
	{
		try 
		{
			if (conn != null)
			{
				conn.rollback();
				closeConnection(conn);
				//System.out.println("TxnUtility :: rollbackTransaction :: Transaction Failed");
				logger.log(Level.INFO,"TxnUtility :: rollbackTransaction :: Transaction Failed");
			}
			else
				throw new Exception("connection is null");
			
		} 
		catch (Exception e) 
		{
			logger.log(Level.ERROR, "TxnUtility :: rollbackTransaction() :: Exception  :: " + e);
			throw e;
		}
	} // End Of Method
	
	/**
	 * Close the Connection object 
	 * 
	 * @throws Exception
	 *             if an error occurs
	 */
	public static void closeConnection(Connection conn) throws Exception 
	{
		try 
		{
			if (conn != null)
			{
				conn.close();
			}	
		} 
		catch (Exception e) 
		{
			logger.log(Level.ERROR, "TxnUtility :: closeConnection() :: Exception  :: " + e);
			throw e;
		}
	} // End Of Method
	
} // End Of Class

/** USAGE
public void createUser(UserBean userBean) throws Exception 
{ 
	try 
	{
		TxnUtility.beginTransaction();
		
		//--------Call TxnHelper to insert into main table and it's relational tables

		TxnUtility.commitTransaction(); 
	} 
	catch(Exception ex) 
	{ 
		try 
		{
			TxnUtility.rollbackTransaction(); 
		} 
		catch(Exception e) 
		{ 
			throw e; 
		} 
		throw ex; 
	} 
}
*/