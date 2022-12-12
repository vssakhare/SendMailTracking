package in.emp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * By implementing this interface, classes can be used by Dao objects to help in executing queries on the database
 */
public interface QueryHelper 
{
    /**
     * Reads the next record in the resultset and puts the values in a data object
     *
     * @return a dataobject that represents a record
     * @throws SQLException if a database problem occured
     */
    public Object getDataObject(ResultSet results) throws Exception;

    /**
     * Executes a query (defined in the method) on a supplied connection
     *
     * @return the resultset of the query
     * @throws SQLException if a database problem occured
     */
    public ResultSet getQueryResults(Connection connection) throws Exception;
}
