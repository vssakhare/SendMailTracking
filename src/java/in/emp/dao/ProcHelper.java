package in.emp.dao;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * By implementing this interface, classes can be used by Dao objects to help in executing PLSQL procedures on the database
 */
public interface ProcHelper {

    /**
     * Executes a PLSQL procedure (defined in the method) on a supplied connection
     *
     * @return the Object containing returned values
     * @throws SQLException if a database problem occured
     */
    public Object callProcedure(Connection connection) throws Exception;
}
