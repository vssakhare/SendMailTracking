/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.system.dao.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * By implementing this interface, classes can be used by Dao objects to help in executing queries on the database
 */
public interface QueryHelperSSO
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
     * @return the PreparedStatement of the query
     * @throws SQLException if a database problem occured
     */
    public PreparedStatement getQueryResults(Connection connection) throws Exception;
}
