/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.system.dao.helpers;
import in.emp.dao.QueryHelper;
import in.emp.util.ApplicationUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.HashMap;

import java.util.Map;
import java.util.TreeMap;

public class BaseDaoSSO
{

        /**
         * Gets a single dataobject resulting from the query supplied via the queryHelper
         * when used on a query that returns multiple rows, the first row will be returned.
         * @param queryHelper represents the query that needs to be executed
         * @return a Collection of dataobjects representing the rows returned from the query
         * @throws DaoException if a problem occured with the database
         */
        protected Object getDataObject(QueryHelperSSO queryHelper)
                throws Exception
        {
                Object dataObject = null;
                Connection connection = null;
                ResultSet results = null;
                PreparedStatement statement = null;

                try
                {
                        connection = ApplicationUtils.getConnection();

                        statement = queryHelper.getQueryResults(connection);

                        results = statement.executeQuery();

                        while (results.next())
                        {

                                dataObject = queryHelper.getDataObject(results);

                        }
                }
                catch (Exception exception)
                {
                        exception.printStackTrace();
                }
                finally
                {
                        if (results != null)
                        {
                                try
                                {
                                        results.close();
                                        results = null;
                                }
                                catch (Exception ignored)
                                {}
                        }
                        if (statement != null)
                        {
                                try
                                {
                                        statement.close();
                                        statement = null;
                                }
                                catch (Exception ignored)
                                {}
                        }
                        if (connection != null)
                        {
                                try
                                {
                                        connection.close();
                                        connection = null;
                                }
                                catch (Exception ignored)
                                {}
                        }
                }

                return dataObject;
        }

        /**
         * Creates a list of dataobjects resulting from the query supplied via the queryHelper
         * @param queryHelper represents the query that needs to be executed
         * @return a Collection of dataobjects representing the rows returned from the query
         * @throws DaoException if a problem occured with the database
         */
        protected LinkedList getObjectList(QueryHelperSSO queryHelper)
                throws Exception
        {
                LinkedList list = new LinkedList();
                Connection connection = null;
                ResultSet results = null;
                PreparedStatement statement = null;

                try
                {
                        connection = ApplicationUtils.getConnection();
                        statement = queryHelper.getQueryResults(connection);

                        results =  statement.executeQuery();

                        int iCount = 0;

                        while (results.next())
                        {

                                iCount++;

                                list.add(queryHelper.getDataObject(results));

                        }
                }
                catch (Exception exception)
                {
                        throw exception;
                }
                finally
                {
                        if (results != null)
                        {
                                try
                                {
                                        results.close();
                                        results = null;
                                }
                                catch (Exception ignored)
                                {}
                        }
                        if (statement != null)
                        {
                                try
                                {
                                        statement.close();
                                        statement = null;
                                }
                                catch (Exception ignored)
                                {}
                        }
                        if (connection != null)
                        {
                                try
                                {
                                        connection.close();
                                        connection = null;
                                }
                                catch (Exception ignored)
                                {}
                        }
                }

                return list;

        }

        /**
         * Public API to create object
         * @param txnHelperObj object of TxnHelper
         * @returns Object
         * @throws Exception
         */
        protected Object createObject(TxnHelperSSO txnHelperObj)
                throws Exception
        {
                Connection connection = null;
                Object obj = null;

                try
                {

                        connection = ApplicationUtils.getConnection();

                        obj = txnHelperObj.createObject(connection);

                }
                catch (Exception exception)
                {
                        throw exception;
                }
                finally
                {
                        if (connection != null)
                        {
                                try
                                {
                                        connection.close();
                                        connection = null;
                                }
                                catch (Exception ignored)
                                {}
                        }
                }

                return obj;
        }


        protected Object updateA1Object(TxnHelperSSO txnHelperObj)
                throws Exception
        {
                Connection connection = null;
                Object obj = null;

                try
                {

                        connection = ApplicationUtils.getConnection();

                        obj = txnHelperObj.updateA1Object(connection);

                }
                catch (Exception exception)
                {
                        throw exception;
                }
                finally
                {
                        if (connection != null)
                        {
                                try
                                {
                                        connection.close();
                                        connection = null;
                                }
                                catch (Exception ignored)
                                {}
                        }
                }

                return obj;
        }


        /**
         * Public API to update.object
         * @param txnHelperObj object of TxnHelper
         * @returns void
         * @throws Exception
         */
        protected void updateObject(TxnHelperSSO txnHelperObj)
                throws Exception
        {
                Connection connection = null;

                try
                {

                        connection = ApplicationUtils.getConnection();

                        txnHelperObj.updateObject(connection);

                }
                catch (Exception exception)
                {
                        throw exception;
                }
                finally
                {
                        if (connection != null)
                        {
                                try
                                {
                                        connection.close();
                                        connection = null;
                                }
                                catch (Exception ignored)
                                {}
                        }
                }
        }

        /**
         * Public API to delete.Object
         * @param txnHelperObj object of TxnHelper
         * @returns void
         * @throws Exception
         */
        protected void removeObject(TxnHelperSSO txnHelperObj)
                throws Exception
        {
                Connection connection = null;

                try
                {

                        connection = ApplicationUtils.getConnection();

                        txnHelperObj.deleteObject(connection);

                }
                catch (Exception exception)
                {
                        throw exception;
                }
                finally
                {
                        if (connection != null)
                        {
                                try
                                {
                                        connection.close();
                                        connection = null;
                                }
                                catch (Exception ignored)
                                {}
                        }
                }
        }

        /**
         * Public API to update attribute.
         * @param txnHelperObj object of TxnHelper
         * @param attributeMap object of HashMap

         * @throws Exception
         */
        protected void updateObjectAttribute(TxnHelperSSO txnHelperObj, HashMap attributeMap)
                throws Exception
        {
                Connection connection = null;

                try
                {
                        connection = ApplicationUtils.getConnection();

                        txnHelperObj.updateObjectAttribute(connection, attributeMap);

                }
                catch (Exception exception)
                {
                        throw exception;
                }
                finally
                {
                        if (connection != null)
                        {
                                try
                                {
                                        connection.close();
                                        connection = null;
                                }
                                catch (Exception ignored)
                                {}
                        }
                }
        }

        protected Map getObjectMap(QueryHelperSSO queryHelper, String type)
                throws Exception
        {
                Map map = new TreeMap();
                Connection connection = null;
                ResultSet results = null;
                PreparedStatement statement = null;

                try
                {

                        connection = ApplicationUtils.getConnection();

                        statement = queryHelper.getQueryResults(connection);

                        results = statement.executeQuery();

                        int iCount = 0;
                        while (results.next())
                        {

                                //list.add(queryHelper.getDataObject(results));
                                //nc LocationDataBean loc = (LocationDataBean) queryHelper.getDataObject(results);

                                //nc map.put(loc.getLocationCode(), loc);

                                iCount++;

                        }

                }
                catch (Exception exception)
                {
                        throw exception;
                }
                finally
                {
                        if (results != null)
                        {
                                try
                                {
                                        results.close();
                                        results = null;
                                }
                                catch (Exception ignored)
                                {}
                        }
                        if (statement != null)
                        {
                                try
                                {
                                        statement.close();
                                        statement = null;
                                }
                                catch (Exception ignored)
                                {}
                        }
                        if (connection != null)
                        {
                                try
                                {
                                        connection.close();
                                        connection = null;
                                }
                                catch (Exception ignored)
                                {}
                        }
                }

                return map;
        }
}
