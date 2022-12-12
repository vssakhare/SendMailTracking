package in.emp.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.*;
import java.text.*;
import javax.servlet.http.HttpServletRequest;
import java.net.*;
import java.io.*;

import in.emp.common.ApplicationConstants;
import in.emp.common.AttributeData;

import in.emp.system.manager.SystemManager;
import in.emp.system.SystemDelegate;
import in.emp.system.beans.SystemMessageData;

//-- logger Imports

import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.Charset;
import java.sql.DriverManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Class	ApplicationUtils
 *
 * @author
 * @version LastModifedDate LastModfiedBy
 */
public class ApplicationUtils {

    private static Logger logger = Logger.getLogger(ApplicationUtils.class);
    private static Context initContext = null;
    private static Context envContext = null;
    private static DataSource dataSource = null;
    private static Connection conn = null;
    private static final int BLOCK_SIZE = 24680;
    private static String DriveLetter = "D";
    private static String ipAddress = "";

    static {


        try {
            System.out.println("Hello");
            initContext = new InitialContext();
            envContext = (Context) initContext.lookup(ApplicationConstants.INIT_CONTEXT);
            dataSource = (DataSource) envContext.lookup(ApplicationConstants.CONN_POOL_NAME);
            ///For Shareable Connection
            //dataSource = (OracleDataSource) envContext.lookup(ApplicationConstants.CONN_POOL_NAME);
            if (dataSource == null) {
                System.out.println("DS look up :-(");
            } else {
                System.out.println("ApplicationConstants.CONN_POOL_NAME");
                System.out.println("DS look up :-)");
            }

        } catch (Exception ex) {
            dataSource = null;
            ex.printStackTrace();
        }
    }

    /**
     * This public static API use to get the connection from database.
     *
     * @param void
     * @return Connection, java.sql.Connection reference of a database
     * @throws Exception
     */
    public static String encodeURIComponent(String param) throws Exception {
        String ret = "";
        try {
            ret = URLEncoder.encode(param, "UTF-8").replaceAll("\\%28", "(").replaceAll("\\%29", ")").replaceAll("\\+", "%20").replaceAll("\\%27", "'").replaceAll("\\%21", "!").replaceAll("\\%7E", "~");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;

    }
  
    public static Connection getConnection() throws Exception {

        try {
            Connection connection = null;
            if (dataSource == null) {
                // Error during initialization of InitialContext or Datasource
                throw new Exception("###### Fatal Exception ###### - DataSource is not initialized.Pls check the stdout/logs.");
            } else {
                connection = dataSource.getConnection();
                connection.setAutoCommit(false);
            }
            return connection;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }

    /**
     * This public static API use to get currentTimestamp attribute
     *
     * @param conn	java.sql.Connection reference
     * @return	current Timestamp of a connected database
     */
    public static Timestamp getCurrentTimestamp() {
        Timestamp returnTimestamp = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement("SELECT SYSDATE FROM DUAL");
            rs = ps.executeQuery();
            if (rs.next()) {
                returnTimestamp = rs.getTimestamp(1);
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getCurrentTimestamp() :: Exception  :: " + ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
        return returnTimestamp;
    }

    /**
     * This public static API use to convertjava.sql.Date into String.
     *
     * @param ts, java.sql.Date	reference
     * @param format, formate which is suppose to display
     * @return String, java.lang.String value of java.sql.Date object
     */
    public static String dateToString(java.util.Date ts, String format) {
        String dateString = null;
        if (isBlank(format) || ts == null) {
            return null;
        }
        try {
            SimpleDateFormat formatSpec = new SimpleDateFormat(format);
            dateString = formatSpec.format(ts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
    }

    /**
     * This public static API use to check whether Object is Null or not.
     *
     * @param tr, java.lang.Object (mostly java.lang.String)
     * @return boolean, status of java.lang.Object
     */
    public static boolean isBlank(Object str) {
        return (str == null || str.toString().trim().isEmpty());
    }

    /**
     * This public static API use to get the value of Label from resource
     * Bundle.
     *
     * @param request, HttpServletRequest reference
     * @param resourceName, resource name
     * @param key, key of a resource
     * @return String, value of a resource
     */
    public static String getLabel(HttpServletRequest request, String resourceName, String key) {
        String labelValue = null;
        String keyValue = "";

        keyValue = new String(key);
        if (request.getSession().getAttribute(resourceName) != null) {
            HashMap resourceMap = (HashMap) request.getSession().getAttribute(resourceName);
            if (resourceMap != null && resourceMap.size() > 0) {
                labelValue = (String) resourceMap.get(keyValue);
            }
        }
        return labelValue;
    }

    /**
     * This public static API use to get the Render URL
     *
     * @param request, HttpServletRequest reference
     * @param name, name of url
     * @param value, value of url
     * @return String, render url
     * @throws Exception
     */
    public static String getRenderURL(HttpServletRequest request, String name, String value) throws Exception {
        String renderUrl = null;
        try {
            renderUrl = ApplicationConstants.SERVLET_CONTEXT + "?" + name + "=" + value;
        } catch (Exception ex) {
            throw ex;
        }
        return renderUrl;
    }

    /**
     * This public static API use to get the ACTION URL
     *
     * @param request, HttpServletRequest reference
     * @return String, action url
     */
    public static String getActionURL(HttpServletRequest request) throws Exception {
        String actionUrl = null;
        try {
            actionUrl = ApplicationConstants.SERVLET_CONTEXT;
        } catch (Exception ex) {
            throw ex;
        }
        return actionUrl;
    }

    /**
     * This public static API use to get the ACTION URL
     *
     * @param request, HttpServletRequest reference
     * @return String, action url
     */
   

    /**
     * Public static API to convert file separators OS specific.
     *
     * @param url	the URL
     */
    public static String validateURL(String url) {
        String correctedURL = "";
        try {
            if (validateString(url)) {
                url = url.trim();

                StringBuffer sb = new StringBuffer();
                String delimiter = "";

                if (url.indexOf("\\") != -1) {
                    delimiter = "\\";

                    StringTokenizer urlTokenizer = new StringTokenizer(url, delimiter);
                    while (urlTokenizer.hasMoreTokens()) {
                        String token = (String) urlTokenizer.nextToken();
                        sb.append(token);
                        sb.append(File.separator);
                    }

                    correctedURL = sb.toString();
                    correctedURL = correctedURL.substring(0, correctedURL.length() - 1);
                } else {
                    correctedURL = url;
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: validateURL() :: Exception  :: " + ex);
        }
        return correctedURL;
    }

    /**
     * Public static API to check whether Object is Null or not.
     *
     * @param str	the String object
     * @return	the boolean object
     */
    public static boolean validateString(String str) {
        boolean isStringValid = true;
        try {
            if (str == null || str.toString().trim().length() == 0 || str.equals("")) {
                isStringValid = false;
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: isBlank() :: Exception :: " + ex);
        }
        return isStringValid;
    }

    public static String getRequestParameters(HttpServletRequest request, String parameterName) throws Exception {
        String paramValue = null;

        try {
            if (!(isBlank(parameterName))) {
                if (request.getParameter(parameterName) != null && request.getParameter(parameterName).length() > 0) {
                    paramValue = request.getParameter(parameterName);
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
        return paramValue;
    }

    public static void setSessionParameters(HttpServletRequest request, String parameterName, Object parameterValue) throws Exception {
        try {
            if (!(isBlank(parameterName)) && parameterValue != null) {
                request.getSession().setAttribute(parameterName, parameterValue);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static Object getSessionParameters(HttpServletRequest request, String parameterName) throws Exception {
        Object obj = null;
        try {
            if (!(isBlank(parameterName))) {
                if (request.getSession().getAttribute(parameterName) != null) {
                    obj = (Object) request.getSession().getAttribute(parameterName);
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
        return obj;
    }

    public static void setRequestAttribute(HttpServletRequest request, String attributeName, Object attributeValue) throws Exception {
        try {
            if (!(isBlank(attributeName)) && attributeValue != null) {
                request.setAttribute(attributeName, attributeValue);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static Object getRequestAttribute(HttpServletRequest request, String attributeName) throws Exception {
        Object obj = null;
        try {
            if (!(isBlank(attributeName))) {
                if (request.getAttribute(attributeName) != null) {
                    obj = (Object) request.getAttribute(attributeName);
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
        return obj;
    }

    public static String getDataLength(String inpData, int reqdLength, char padChar) {
        return lPad("" + inpData.length(), reqdLength, padChar);
    }

    public static String getDate(String dtFormat) {
        if (dtFormat == "") {
            dtFormat = "dd-MM-yyyy";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dtFormat);
        Calendar cal = Calendar.getInstance();
        return sdf.format(cal.getTime());
    }

    public static String lPad(String input, int length, char pad) {
        int padLength = length - input.length();
        String s;
        for (s = new String(""); s.length() < padLength; s = s + String.valueOf(pad));
        input = s + input;
        if (input.length() != length) {
            return input;
        } else {
            return input;
        }
    }

    public static boolean decodeCmd(String eCommand) {
        Map eCommandsError = new HashMap();
        Map eCommandsSuccess = new HashMap();
        Map eCommandsFinished = new HashMap();
        boolean done = false;
        boolean isError = false;
        String eCmd = null;
        String eCmdQua = null;
        String eAddQua = null;
        if (eCommand.substring(0, 2).equals("02")) {
            eCmd = "Acknowledgement received for ";
        } else if (eCommand.substring(0, 2).equals("04")) {
            eCmd = "Report Progress";
        }
        if (eCommand.substring(2, 4).equals("00")) {
            eCmdQua = "Reading Meter Command ";
        } else if (eCommand.substring(2, 4).equals("01")) {
            eCmdQua = "MRI Download Command ";
        } else if (eCommand.substring(2, 4).equals("02")) {
            eCmdQua = "MRI Prepare Command ";
        } else if (eCommand.substring(2, 4).equals("03")) {
            eCmdQua = "CDF Conversion Command ";
        }
        eAddQua = eCommand.substring(4, 6);
        if (eCommand.substring(0, 2).equals("02")) {
            eCommandsSuccess.put("00", "Accepted");
            eCommandsError.put("01", "Failed due to Duplicate Instance.");
            eCommandsError.put("02", "Failed due to Invalid Configuration File.");
            eCommandsError.put("03", "Invalid / Unknown Command.");
            eCommandsError.put("04", "Command not Supported.");
            eCommandsError.put("04", "Failed due to Invalid Instance ID.");
        } else if (eCommand.substring(0, 2).equals("04")) {
            eCommandsSuccess.put("00", "In Progress");
            eCommandsSuccess.put("01", "Connection Established");
            eCommandsSuccess.put("02", "Meter Reading Started");
            eCommandsSuccess.put("05", "Idle State");
            eCommandsFinished.put("03", "Meter Reading Finished");
            eCommandsFinished.put("04", "CDF Conservation Successful");
            eCommandsFinished.put("77", "MRI Data Transfer Successful");
            eCommandsError.put("51", "Cannot Establish Connection - No Dail Tone");
            eCommandsError.put("52", "Cannot Establish Connection - Local Modem not Responding");
            eCommandsError.put("53", "Cannot Establish Connection - Line Busy");
            eCommandsError.put("54", "Cannot Establish Connection - Port Not Available");
            eCommandsError.put("55", "Cannot Establish Connection - No Hand Shaking");
            eCommandsError.put("56", "Line Disconnected");
            eCommandsError.put("57", "CDF Conservation Failed - File Structure Corrupted");
            eCommandsError.put("58", "CDF Conservation Failed - File Write Error");
            eCommandsError.put("59", "CDF Conservation Failed - File Not Found");
            eCommandsError.put("60", "User Abort");
            eCommandsError.put("61", "Process Stopped - Meter Serial No. Mismatch");
            eCommandsError.put("62", "Meter Reading Failed");
            eCommandsError.put("63", "Conversion Failed");
            eCommandsError.put("64", "File(s) are not Available for Data Conversion");
            eCommandsError.put("65", "Meter Reading Failed - Check sum Error");
            eCommandsError.put("66", "Meter Reading Failed - Data Collection Error");
            eCommandsError.put("67", "Invalid Header");
            eCommandsError.put("68", "Source Folder Path Not Found");
            eCommandsError.put("69", "Unit Code is not Set. Continuing with Remaining Meters.");
            eCommandsError.put("70", "Tariff Cant be Zero");
            eCommandsError.put("71", "Can't Continue with Parsing");
            eCommandsError.put("72", "Unsupported Meter Version");
            eCommandsError.put("73", "Not Enough Free Space");
            eCommandsError.put("74", "Meter is Inactive - Cannot Collect Data");
            eCommandsError.put("75", "Cannot Establish Connection - No Carrier");
            eCommandsError.put("76", "Cannot Establish Connection - No Connection");
            eCommandsError.put("78", "MRI Data Transfer Failed");
            eCommandsError.put("79", "MRI Not Responding");
        }
        String eAddQuaStr = null;
        if (eCommandsSuccess.containsKey(eAddQua)) {
            eAddQuaStr = (String) eCommandsSuccess.get(eAddQua);
            done = false;
            isError = false;
        } else if (eCommandsError.containsKey(eAddQua)) {
            eAddQuaStr = (String) eCommandsError.get(eAddQua);
            done = true;
            isError = true;
        } else if (eCommandsFinished.containsKey(eAddQua)) {
            eAddQuaStr = (String) eCommandsFinished.get(eAddQua);
            done = true;
            isError = false;
        }

        logger.log(Level.INFO, "CPServer :: decodeCmd() ::  eCommand : " + eCommand + " | eCmd : " + eCmd + " | eCmdQua : " + eCmdQua + " | eAddQua : " + eAddQua + " | eAddQuaStr : " + eAddQuaStr + " | Done : " + done + " | isError : " + isError);
        return done;
    }

    /* Method gets the data from the LinkedList
     @Param LinkedList ,Selected option string
     @Returns String*/
   
    public static String getSchedulePeriodDifference(String fromDate, String fromDateFMT, String toDate, String toDateFMT) {
        String returnString = "10";

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        try {
            Timestamp dtFrom = ApplicationUtils.stringToTimestamp(fromDate, fromDateFMT);
            Timestamp dtTo = ApplicationUtils.stringToTimestamp(toDate, toDateFMT);
            String from = ApplicationUtils.timeStampToString(dtFrom, "");   ///Plz dont mention outFormat here like "dd-MMM-yyyy HH:MM:SS" it is giving wrong output. check min field.
            String to = ApplicationUtils.timeStampToString(dtTo, "");

            sql.append("SELECT ");
            sql.append("ROUND(TO_DATE('" + to + "','DD-MON-YYYY HH:MI:SS') ");
            sql.append(" - TO_DATE('" + from + "','DD-MON-YYYY HH:MI:SS')) AS DATE_DIFF ");
            sql.append("FROM ");
            sql.append("DUAL ");
            System.out.println("Date Query:::" + sql.toString());

            con = getConnection();
            pst = con.prepareStatement(sql.toString());
            rs = pst.executeQuery();

            while (rs.next()) {
                returnString = rs.getString("DATE_DIFF");
            }



            logger.log(Level.INFO, "ApplicationUtils :: getSchedulePeriodDifference() :: SQL :::" + sql.toString());
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getSchedulePeriodDifference() :: Exception :: " + ex);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return returnString;

    }///End of Method.

    /*public method to get the Search period for Dropdown
     @PARAM String selected stringoption
     @RETURN STRING */
    public static String getSearchPeriod(String selectedSearchPeriod) {
        StringBuffer strBuff = new StringBuffer();
        if (selectedSearchPeriod.equals("-1")) {
            strBuff.append("<option selected value = -1> -Select-</option> <option value = 1> Today </option> <option value = 2> Yesterday </option><option value = 3> Last Week </option>");
        } else if (selectedSearchPeriod.equals("1")) {
            strBuff.append("<option value = -1> -Select-</option> <option selected value = 1> Today </option> <option value = 2> Yesterday </option> <option value = 3> Last Week </option>");
        } else if (selectedSearchPeriod.equals("2")) {
            strBuff.append("<option value = -1> -Select-</option> <option  value = 1> Today </option> <option selected value = 2> Yesterday </option> <option value = 3> Last Week </option>");
        } else if (selectedSearchPeriod.equals("3")) {
            strBuff.append("<option value = -1> -Select-</option> <option value = 1> Today </option> <option value = 2> Yesterday </option> <option selected value = 3> Last Week </option>");
        }

        return strBuff.toString();
    } // End of Method ---

 

    /* public API to get Hours value from 1 to 12
     @param selected hour value
     @return <option> String*/
    public static String getHoursOptionString(String selectedHour) {
        StringBuffer strBuff = new StringBuffer();
        try {
            if (selectedHour.equals("-1")) {
                strBuff = strBuff.append("<option value= '-1' selected> </option>");
            }
            for (int i = 0; i < 12; i++) {
                if ((i + 1) == Integer.parseInt(selectedHour)) {
                    strBuff = strBuff.append("<option value=\'" + (i + 1) + "\' selected>" + (i + 1) + "</option>");
                } else {
                    strBuff = strBuff.append("<option value=\'" + (i + 1) + "\'>" + (i + 1) + "</option>");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return strBuff.toString();

    }//-- End of Method

    public static String getMinutesOptionString(String selectedMinute) {
        StringBuffer strBuff = new StringBuffer();
        try {
            if (selectedMinute.equals("-1")) {
                strBuff = strBuff.append("<option value= '-1' selected> </option>");
            }
            for (int i = 0; i < 60; i++) {
                if ((i) == Integer.parseInt(selectedMinute)) {
                    strBuff = strBuff.append("<option value=\'" + (i) + "\' selected>" + (i) + "</option>");
                } else {
                    strBuff = strBuff.append("<option value=\'" + (i) + "\'>" + (i) + "</option>");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return strBuff.toString();
    }// End of Method

    /*public Method Thad gets the option String for AM & PM*/
    public static String getAmPmOptionString(String selectedAmPm) {
        StringBuffer strBuff = new StringBuffer();

        if (selectedAmPm.equals("-1")) {
            strBuff.append("<option selected value = -1> </option> <option value = 1> AM </option> <option value = 2> PM </option>");
        } else if (selectedAmPm.equals("1")) {
            strBuff.append("<option value = -1> </option> <option selected value = 1> AM </option> <option value = 2> PM </option> ");
        } else if (selectedAmPm.equals("2")) {
            strBuff.append("<option value = -1> </option> <option  value = 1> AM </option> <option selected value = 2> PM </option>");
        }

        return strBuff.toString();
    } // End of Method ---

   

   
   
    public static Long getNextSequenceId(Connection conn, String sequenceName) throws Exception {

        PreparedStatement pStmt = null;
        ResultSet results = null;
        Long nextKey = null;
        String sql = "";

        try {
            sql = "select " + sequenceName + ".nextval from DUAL";
            pStmt = conn.prepareStatement(sql);
            results = pStmt.executeQuery();
            if ((results != null) && (results.next())) {
                nextKey = new Long(results.getLong(1));
            } else {
                throw new SQLException("sequence failed to return a value");
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getNextSequenceId() :: Exception  :: " + ex);
            throw ex;
        } finally {
            try {
                if (results != null) {
                    results.close();
                    results = null;
                }

            } catch (Exception e) {
            }
            try {
                if (pStmt != null) {
                    pStmt.close();
                    pStmt = null;
                }
            } catch (Exception e) {
            }

        }
        return nextKey;

    } // End of method

    public static String getVoltageDesc(String voltageId) throws Exception {

        PreparedStatement pStmt = null;
        ResultSet results = null;
        String voltageDesc = null;
        String sql = "";
        Connection conn = null;

        try {
            conn = getConnection();
            sql = "select voltage_level_desc from voltage_level where voltage_level_id=" + voltageId;
            pStmt = conn.prepareStatement(sql);
            results = pStmt.executeQuery();
            if ((results != null) && (results.next())) {
                voltageDesc = new String(results.getString(1));
            } else {
                voltageDesc = "";
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getVoltageDesc() :: Exception  :: " + ex);
            voltageDesc = "";
            //throw ex;
        } finally {
            try {
                if (results != null) {
                    results.close();
                    results = null;
                }

            } catch (Exception e) {
            }
            try {
                if (pStmt != null) {
                    pStmt.close();
                    pStmt = null;
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e) {
            }

        }
        return voltageDesc;

    }

    public static String getLocationDesc(String locId) throws Exception {

        PreparedStatement pStmt = null;
        ResultSet results = null;
        String locationDesc = null;
        String sql = "";
        Connection conn = null;

        try {
            conn = getConnection();
            sql = "select location_desc from location where location_id=" + locId;
            pStmt = conn.prepareStatement(sql);

            results = pStmt.executeQuery();
            if ((results != null) && (results.next())) {
                locationDesc = new String(results.getString(1));
            } else {
                throw new SQLException("sequence failed to return a value");
            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getLocationDesc() :: Exception " + sql.toString());
            logger.log(Level.ERROR, "ApplicationUtils :: getLocationDesc() :: Exception  :: " + ex);
            throw ex;
        } finally {
            try {
                if (results != null) {
                    results.close();
                    results = null;
                }

            } catch (Exception e) {
            }
            try {
                if (pStmt != null) {
                    pStmt.close();
                    pStmt = null;
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e) {
            }

        }
        return locationDesc;

    }

    public static String getLocationDesc1(String zoneId, int locType) throws Exception {

        PreparedStatement pStmt = null;
        ResultSet results = null;
        String locationDesc = null;
        String sql = "";
        Connection conn = null;

        try {
            conn = getConnection();
            sql = "select location_desc from location where location_cd='" + zoneId + "' and location_type_id=" + locType
                    + " and status_cd='A' ";
            pStmt = conn.prepareStatement(sql);
            results = pStmt.executeQuery();
            if ((results != null) && (results.next())) {
                locationDesc = new String(results.getString(1));
            } else {
                throw new SQLException("sequence failed to return a value");
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getLocationDesc1() :: Exception " + sql.toString());
            logger.log(Level.ERROR, "ApplicationUtils :: getLocationDesc1() :: Exception  :: " + ex);
            throw ex;
        } finally {
            try {
                if (results != null) {
                    results.close();
                    results = null;
                }

            } catch (Exception e) {
            }
            try {
                if (pStmt != null) {
                    pStmt.close();
                    pStmt = null;
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e) {
            }

        }
        return locationDesc;

    }

    public static String getLocationDesc2(String buCode) throws Exception {

        PreparedStatement pStmt = null;
        ResultSet results = null;
        String locationDesc = null;
        String sql = "";
        Connection conn = null;

        try {
            conn = getConnection();
            sql = "select lm.location_desc from location lm,location_bu_mapping lbm where lbm.bu_code=" + buCode + " and lbm.loc_id=lm.location_id "
                    + " and lm.status_cd='A' and bu_status='Y'";
            pStmt = conn.prepareStatement(sql);
            results = pStmt.executeQuery();
            if ((results != null) && (results.next())) {
                locationDesc = new String(results.getString(1));
            } else {
                throw new SQLException("sequence failed to return a value");
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getLocationDesc2() :: Exception " + sql.toString());
            logger.log(Level.ERROR, "ApplicationUtils :: getLocationDesc2() :: Exception  :: " + ex);
            ex.printStackTrace();
            throw ex;
        } finally {
            try {
                if (results != null) {
                    results.close();
                    results = null;
                }

            } catch (Exception e) {
            }
            try {
                if (pStmt != null) {
                    pStmt.close();
                    pStmt = null;
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e) {
            }

        }
        return locationDesc;

    }

    public static Long getTamperCount(String installId, String fileId) throws Exception {

        PreparedStatement pStmt = null;
        ResultSet results = null;
        long count = 0;
        String sql = "";


        try {
            conn = getConnection();
            sql = "SELECT  COUNT(nvl(TE.EVENT_CODE,0)) EVENT_COUNT"
                    + " FROM TAMPER_EVENTS TE, TAMPER_EVENT_CODE_MASTER TECM WHERE DEVICE_INSTALL_POINT_ID = " + installId
                    + " AND TE.EVENT_CODE = TECM.EVENT_CODE  AND FILE_LOAD_ID = " + fileId;

            pStmt = conn.prepareStatement(sql);
            results = pStmt.executeQuery();
            if ((results != null) && (results.next())) {
                count = results.getLong("EVENT_COUNT");
            } else {
                throw new SQLException("getTamperCount failed to return a value");
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getTamperCount() :: Exception " + sql.toString());
            logger.log(Level.ERROR, "ApplicationUtils :: getTamperCount() :: Exception  :: " + ex);
            throw ex;
        } finally {
            try {
                if (results != null) {
                    results.close();
                    results = null;
                }

            } catch (Exception e) {
            }
            try {
                if (pStmt != null) {
                    pStmt.close();
                    pStmt = null;
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e) {
            }

        }
        return count;

    }

    public static Long getInstaCount(String fileId) throws Exception {

        PreparedStatement pStmt = null;
        ResultSet results = null;
        long count = 0;
        String sql = "";


        try {
            conn = getConnection();
            sql = "SELECT count(*) cnt FROM INST_PARAMS WHERE FILE_LOAD_ID = " + fileId;

            pStmt = conn.prepareStatement(sql);
            results = pStmt.executeQuery();
            if ((results != null) && (results.next())) {
                count = results.getLong("cnt");
            } else {
                throw new SQLException("getInstaCount failed to return a value");
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getInstaCount() :: Exception " + sql.toString());
            logger.log(Level.ERROR, "ApplicationUtils :: getInstaCount() :: Exception  :: " + ex);
            throw ex;
        } finally {
            try {
                if (results != null) {
                    results.close();
                    results = null;
                }

            } catch (Exception e) {
            }
            try {
                if (pStmt != null) {
                    pStmt.close();
                    pStmt = null;
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e) {
            }
        }
        return count;

    }

    public static long stringToLong(String str) throws NumberFormatException {
        long val = -1;
        try {
            if (!isBlank(str)) {
                val = Long.parseLong(str.trim());
            }
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            //throw nfe;
        }
        return val;
    }

    public static double stringToDouble(String str) throws NumberFormatException {
        double val = -1;
        try {
            if (!isBlank(str)) {
                val = Double.parseDouble(str.trim());
            }
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            //throw nfe;
        }
        return val;
    }

    public static long minsToMilliSecs(long mins) {
        return (mins * 60 * 1000);
    } // End of method

    public static long milliSecsToMin(long milliSecs) {
        return (milliSecs / (60 * 1000));
    } // End of method

    public static Date addMinsToDate(Date dt, long mins) {
        java.sql.Date newDt = null;
        try {
            long milliSecs = dt.getTime();
            milliSecs = milliSecs + minsToMilliSecs(mins);
            newDt = new java.sql.Date(milliSecs);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return newDt;
    } // End of method

    /**
     * This public static API use to get current SQL Date
     *
     * @return	current date of a corresponding connected database
     */
    public static java.sql.Date getCurrentDate() {
        java.sql.Date dt = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Timestamp ts = getCurrentTimestamp(conn);
            if (ts != null) {
                dt = new java.sql.Date(ts.getTime());
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getCurrentDate() :: Exception  :: " + ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception ex) {
            }
        }
        return dt;
    }

    /**
     * This public static API use to get currentTimestamp attribute
     *
     * @param conn	java.sql.Connection reference
     * @return	current Timestamp of a connected database
     */
    public static Timestamp getCurrentTimestamp(Connection conn) {
        Timestamp returnTimestamp = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT SYSDATE FROM DUAL");
            rs = ps.executeQuery();
            if (rs.next()) {
                returnTimestamp = rs.getTimestamp(1);
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getCurrentTimestamp() :: Exception  :: " + ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }
        }
        return returnTimestamp;

    } // End of method

    /**
     * This public static API use to get current SQL Date
     *
     * @return	current date of a corresponding connected database
     */
    public static java.sql.Date getCurrentDate(Connection conn) {
        java.sql.Date dt = null;
        try {
            Timestamp ts = getCurrentTimestamp(conn);
            if (ts != null) {
                dt = new java.sql.Date(ts.getTime());
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getCurrentDate() :: Exception  :: " + ex);
        }
        return dt;

    } //End Of Method

    public static String getSYSParameter(String type) throws Exception {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String urlString = null;

        try {
            conn = getConnection();
            pst = conn.prepareStatement("SELECT PARAMETER_VALUE FROM SYSTEM_PARAMETER WHERE PARAMETER_NAME = ?");

            pst.setString(1, type);

            rs = pst.executeQuery();
            if (rs.next()) {
                urlString = rs.getString("PARAMETER_VALUE");
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }

            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
            }

            try {
                if (conn != null) {
                    conn = null;
                }
            } catch (Exception e) {
            }
        }
        return urlString;
    } // End Of String

    public static boolean checkObject(String deviceList, String ip) throws Exception {
        PreparedStatement st = null;
        StringBuffer sql = null;
        ResultSet rs = null;
        boolean exec = false;
        Connection conn = null;
        try {
            conn = getConnection();
            sql = new StringBuffer();
            sql.append(" SELECT count(1) FROM DEVICE D,DEVICE DM,DEVICE_CONNECTION DC WHERE"
                    + " D.DEVICE_ID=DC.DEVICE_ID AND DM.DEVICE_ID =DC.COMM_DEVICE_ID "
                    + "AND DM.IPADDRESS LIKE '" + ip + "%' "
                    + "and d.device_id in(" + deviceList + ")");
            st = conn.prepareStatement(sql.toString());
            rs = st.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) > 0) {
                    exec = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs.close();
            st.close();
            conn.close();

        }
        return exec;
    }

    /*public API to get the query string for where clause
     *@param column name, column value, dataType and operator
     *@returns String
     */
    public static String getQueryConditionString(String colName, String value, String dataType, String operator) {
        StringBuffer str = new StringBuffer();

        try {
            /*str.append(" AND ");
             str.append(colName);
             str.append(operator);
            
             if (dataType.equals("Date"))
             {
             str.append("TO_DATE (");
             str.append(" '"+ value +"' ");
             str.append(",'yyyy/mm/dd HH24:MI') ");
             }
             else
             str.append(" '"+ value +"' ");*/
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return str.toString();
    } //End Of Method

    /* Method gets the InstallPoint type optionString from the LinkedList
     @Param LinkedList ,Selected option string
     @Returns String*/
    
    public static String readStreamData(InputStream stream, long fileSize) throws Exception {

        byte[] byteData = null;
        String strFileContent = null;
        try {
            byte[] fileChunk = new byte[BLOCK_SIZE];
            ByteArrayOutputStream fileData = new ByteArrayOutputStream();
            long bytesToRead = fileSize;
            while (bytesToRead > 0) {
                stream.read(fileChunk);

                if (bytesToRead >= BLOCK_SIZE) {
                    fileData.write(fileChunk);
                } else {
                    fileData.write(fileChunk, 0, (int) bytesToRead);
                }

                bytesToRead -= BLOCK_SIZE;
            }

            strFileContent = new String(fileData.toString());
            //byteData = fileData.toByteArray();
            fileData.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return byteData;
        return strFileContent;
    }

    /**
     * This public static API use to convert java.lang.String to java.sql.Date.
     *
     * @param dateStr	date java.lang.String
     * @param format	format of date
     * @return	java.sql.Date object
     */
    public static java.sql.Date stringToDate(String dateStr, String format) {
        java.sql.Date dt = null;
        try {
            if (validateString(format) && validateString(dateStr)) {
                SimpleDateFormat formatSpec = new SimpleDateFormat(format);
                dt = new java.sql.Date(formatSpec.parse(dateStr).getTime());
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: stringToDate() :: Exception  :: " + ex);
        }
        return dt;
    }

    /**
     * Public API to stripSpecialChars value in String Replace: by &quot; &#39;
     * &amp; &lt; &gt;
     *
     * @param objectName	the String object
     * @return	the String object
     *
     */
    public static String escapeString(String objectName) {
        try {
            if (!isBlank(objectName)) {
                objectName = objectName.replaceAll("&", "&amp;");
                objectName = objectName.replaceAll("\"", "&quot;");
                objectName = objectName.replaceAll("'", "&#39;");
                objectName = objectName.replaceAll("<", "&lt;");
                objectName = objectName.replaceAll(">", "&gt;");
                //objectName = objectName.replaceAll(" ","&nbsp;");
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: escapeString() :: Exception  :: " + ex);
        }
        return objectName;
    }

    /* Method gets the data from the LinkedList
     @Param LinkedList ,Selected option string
     @Returns String*/
   

   
    public static String getBaudRateOptionString(String strBaudRate) throws Exception {
        LinkedList baudRateList;
        Iterator itr;
        StringBuffer strBuff = new StringBuffer();
        try {
            logger.log(Level.INFO, "ApplicationUtils :: getBaudRateOptionString() :: ");

            baudRateList = new LinkedList();
            baudRateList.add("110");
            baudRateList.add("300");
            baudRateList.add("1200");

            baudRateList.add("2400");
            baudRateList.add("4800");
            baudRateList.add("9600");
            baudRateList.add("19200");
            baudRateList.add("38400");
            baudRateList.add("57600");
            baudRateList.add("115200");
            baudRateList.add("230400");
            baudRateList.add("460800");
            baudRateList.add("961600");

            logger.log(Level.INFO, "ApplicationUtils:: getBaudRateOptionString() :: baudRateList size :: " + baudRateList.size());

            itr = baudRateList.iterator();

            strBuff.append("<option selected value = -1>- Select -</option>");

            while (itr.hasNext()) {
                String baudRate = (String) itr.next();
                if (baudRate != null) {
                    if (baudRate.equals(strBaudRate)) {
                        strBuff.append("<option selected value = " + baudRate + ">" + baudRate + "</option>");
                    } else {
                        strBuff.append("<option value = " + baudRate + ">" + baudRate + "</option>");
                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getBaudRateOptionString() :: Exception  :: " + ex);
            ex.printStackTrace();
        }
        return strBuff.toString();
    }

    /**
     * Public API to get columns name and values string
     *
     * @param attributeMap	the HaskMap object
     * @return String
     * @throws Exception	if an error occurs
     */
    public static String generateColumnString(HashMap attributeMap) throws Exception {
        StringBuffer columnStr = new StringBuffer();
        try {
            AttributeData attributeData = null;
            Set columnsSet = attributeMap.keySet();
            Iterator itr = columnsSet.iterator();
            String columnValue = "NULL";
            String columnType = "";
            Object key = null;
            while (itr.hasNext()) {
                columnValue = "NULL";
                columnType = "";
                key = (Object) itr.next();
                attributeData = (AttributeData) attributeMap.get(key);
                columnType = attributeData.getColumnType();

                if (attributeData.getColumnValue() != null) {
                    if (columnType.equals("String")) {
                        columnValue = "'" + attributeData.getColumnValue() + "'";
                    } else if (columnType.equals("Long")) {
                        columnValue = attributeData.getColumnValue() + "";
                    } else if (columnType.equals("Date")) {
                        columnValue = "to_date('" + attributeData.getColumnValue() + "','MM/DD/YYYY')";
                    } else if (columnType.equals("SYSDATE")) {
                        columnValue = " SYSDATE ";
                    } else if (columnType.equals("NULL")) {
                        columnValue = "NULL";
                    }
                }

                columnStr.append(" " + key + " = " + columnValue + " , ");
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            logger.log(Level.ERROR, "ApplicationUtils :: generateColumnString() :: Exception  :: " + ex);
            throw ex;
        }

        return columnStr.toString();
    }

    public static String getLoadProfileOptionString(String selValue) {
        StringBuffer sb = new StringBuffer();
        try {
            if (selValue != null && selValue.equals("NO")) {
                sb.append("<option value=\"NO\" selected>No</option>");
                sb.append("<option value=\"PARTIAL\">Partial</option>");
                sb.append("<option value=\"FULL\">Full</option>");
            } else if (selValue != null && selValue.equals("PARTIAL")) {
                sb.append("<option value=\"NO\">No</option>");
                sb.append("<option value=\"PARTIAL\" selected>Partial</option>");
                sb.append("<option value=\"FULL\">Full</option>");
            } else if (selValue != null && selValue.equals("FULL")) {
                sb.append("<option value=\"NO\">No</option>");
                sb.append("<option value=\"PARTIAL\">Partial</option>");
                sb.append("<option value=\"FULL\" selected>Full</option>");
            } else {
                sb.append("<option value=\"NO\">No</option>");
                sb.append("<option value=\"PARTIAL\">Partial</option>");
                sb.append("<option value=\"FULL\">Full</option>");
            }


        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getLoadProfileOptionString() :: Exception  :: " + ex);
            ex.printStackTrace();
        }

        return sb.toString();
    }

    public static java.sql.Date convertToSqlDate(String date, String hrs, String min) {
        java.sql.Date sqlDate = null;
        String strDate;
        String hours = "00";
        String mins = "00";
        try {
            if (!ApplicationUtils.isBlank(hrs)) {
                hours = hrs;
            }
            if (!ApplicationUtils.isBlank(min)) {
                mins = min;
            }
            if (!ApplicationUtils.isBlank(date)) {
                strDate = date + " " + hours + ":" + mins;
                sqlDate = ApplicationUtils.stringToDate(strDate, "dd-MMM-yyyy HH:mm");
            } else {
                sqlDate = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return sqlDate;
    } // End of method

    /* Method gets the InstallPoint type drop-down optionString from the LinkedList
     * @Param			LinkedList
     * @Param			String
     * @return			String
     */
    
    public static String rtrim(String str) {
        try {
            if (validateString(str)) {
                str = str.replaceAll("^\\s+", "");
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: rtrim() :: Exception :: " + ex);
        }
        return str;
    }

    /* Method gets the data from the LinkedList
     @Param LinkedList
     @Returns Map*/
   
   
    public static LinkedHashMap getParameterCodesMap() {
        LinkedHashMap codesMap = new LinkedHashMap();
        try {
            codesMap.put("P1-1-1-1-0", "R-phase");
            codesMap.put("P1-1-2-1-0", "Y-phase");
            codesMap.put("P1-1-3-1-0", "B-phase");
            codesMap.put("P1-2-1-1-0", "R-phase");
            codesMap.put("P1-2-2-1-0", "Y-phase");
            codesMap.put("P1-2-3-1-0", "B-phase");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return codesMap;
    }

    public static Timestamp stringToTimestamp(String dt, String format) throws java.text.ParseException {
        Timestamp ts = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        java.util.Date d = sdf.parse(dt);
        ts = new Timestamp(d.getTime());
        return ts;
    }


   
    /**
     * Public API to Convert y page size
     *
     * @param
     * @return	the double object
     *
     */
  
    /**
     * Public API to Convert inches to points
     *
     * @param objectName	the double name
     * @return	the double name
     *
     *
     *
     */
    public static double convertInchesToPoints(double objectName) {
        try {
            if (objectName > 0) {

                objectName = objectName * 72;
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: convertInchesToPoints() :: Exception  :: " + ex);
        }
        return objectName;
    }

    private static boolean getBooleanValue(String bStr) {
        boolean isBoolean;
        if (bStr.equals("Y")) {
            isBoolean = true;
        } else {
            isBoolean = false;
        }
        return isBoolean;
    }

    /**
     * This public static API use to convertjava.sql.Date into String.
     *
     * @param ts, java.sql.Date	reference
     * @param format, formate which is suppose to display
     * @return String, java.lang.String value of java.sql.Date object
     */
    public static String dateToString(java.sql.Date ts, String format) {
        String dateString = null;
        if (isBlank(format) || ts == null) {
            return null;
        }
        try {
            SimpleDateFormat formatSpec = new SimpleDateFormat(format);
            dateString = formatSpec.format(ts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
    }

    public static Date timeStampToDate(Timestamp timestamp) {
        long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
        return new Date(milliseconds);
    }

    public static String timeStampToString(Timestamp ts, String outFormat) {
        if (isBlank(outFormat)) {
            outFormat = ApplicationConstants.SQL_DATE_FORMAT;
        }

        if (ts == null) {
            return null;
        }

        Date dt = timeStampToDate(ts);

        return dateToString(dt, outFormat);
    }

    public static String timeStampToString(String ts, String inFormat, String outFormat) {
        String dateString = null;
        if (isBlank(inFormat)) {
            inFormat = ApplicationConstants.TIMESTAMP_DATE_FORMAT;
        }
        if (isBlank(outFormat)) {
            outFormat = ApplicationConstants.SQL_DATE_FORMAT;
        }
        if (ts == null) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(inFormat);
            java.util.Date d = sdf.parse(ts);
            sdf = new SimpleDateFormat(outFormat);
            dateString = sdf.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
    }

 

    public static String getAccessLocationListStr(LinkedList buAccessList) {
        String buAccessString = null;
        Iterator itr = buAccessList.iterator();
        while (itr.hasNext()) {
            if (buAccessString == null) {
                buAccessString = (String) itr.next();
            } else {
                buAccessString += ", " + (String) itr.next();
            }
        }

        return buAccessString;
    }

   
   
   
   
   
    public static Long getRemoteProtocolID(long commDeviceTypeId) {
        long commProtocolId = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement("SELECT REMOTE_COMM_PROTOCOL_ID FROM DEVICE_TYPE_PROTOCOL WHERE COMM_DEVICE_TYPE_ID = " + commDeviceTypeId);
            rs = ps.executeQuery();
            if (rs.next()) {
                commProtocolId = rs.getLong("REMOTE_COMM_PROTOCOL_ID");
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getRemoteProtocolID() :: Exception  :: " + ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
            }
        }
        return commProtocolId;
    }

    public static boolean fileMoveTo(String srcName, String destName) throws Exception {
        boolean moved = false;
        File destFile = new File(destName);
        String destFolder = destFile.getParent();
        boolean isCreated = createDir(destFolder);
        try {
            File f = new File(srcName);
            moved = f.renameTo(new File(destName));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: fileMoveTo() :: Exception == " + ex.getMessage());
            throw ex;
        }
        return moved;
    }

    public static boolean createDir(String path) throws Exception {
        boolean isCreate = false;
        try {
            File f = new File(path);
            isCreate = f.mkdirs();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: makeDir() :: Exception == " + ex);
            throw ex;
        }
        return isCreate;
    }

    public static boolean checkRunningCommand(long deviceId) {
        Connection con = null;
        PreparedStatement pst = null;
        StringBuffer sql = new StringBuffer();
        Boolean check = false;
        int count = 0;

        try {
            sql.append(" SELECT COUNT(1) FROM ");
            sql.append(" API_IN_PROCESS ");
            sql.append(" WHERE DEVICE_TYPE_ID = ( SELECT dEVICE_TYPE_ID FROM DEVICE WHERE DEVICE_ID = ?) ");
            sql.append(" AND STATUS_CD = 'A'");


            con = getConnection();

            logger.log(Level.INFO, "ApplicationUtil :: updateAllConnectionPoolTable() :: update sql  :: " + sql);

            pst = con.prepareStatement(sql.toString());


            pst.setLong(1, deviceId);


            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }

            if (count > 0) {
                check = true;
            }

        } catch (Exception ex) {
            try {
                con.rollback();
            } catch (SQLException ex1) {
            }
            logger.log(Level.ERROR, "ApplicationUtil :: updateAllConnectionPoolTable() :: Exception  :: " + ex);
            ex.printStackTrace();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return check;
    }

    public static int updateCommandTableForStartAPI(String commandId) throws Exception {

        PreparedStatement pStmt = null;
        int results = 0;
        String sql = "";

        Connection conn = null;

        try {
            conn = getConnection();

            /**
             * SELECT * FROM COMMAND WHERE DEVICE_TYPE_ID IN( SELECT
             * DEVICE_TYPE_ID FROM COMMAND WHERE COMMAND_ID=6 )
             *
             */
            ///sql = "UPDATE COMMAND SET SS_STATUS = 1 WHERE COMMAND_ID = "+commandId;
            ///sql = "UPDATE COMMAND SET SS_STATUS = 1 , SS_ACTION = 0 WHERE DEVICE_TYPE_ID IN ( SELECT DEVICE_TYPE_ID FROM COMMAND WHERE COMMAND_ID = "+commandId+" )";
            ///This is working but need to some enhancement
            ///sql = "UPDATE COMMAND SET SS_STATUS = 1 WHERE DEVICE_TYPE_ID IN ( SELECT DEVICE_TYPE_ID FROM COMMAND WHERE COMMAND_ID = "+commandId+" )";
            sql = "UPDATE COMMAND SET SS_STATUS = 1 , SS_ACTION = 1 WHERE DEVICE_TYPE_ID IN ( SELECT DEVICE_TYPE_ID FROM COMMAND WHERE COMMAND_ID = " + commandId + " )";

            logger.log(Level.INFO, "ApplicationUtils :: updateCommandTableForStartAPI() :: SQL ::: " + sql.toString());

            pStmt = conn.prepareStatement(sql);

            results = pStmt.executeUpdate();

            logger.log(Level.INFO, "ApplicationUtils :: updateCommandTableForStartAPI() :: Records updated (results) ::: " + results);

            if (results > 0) {
                logger.log(Level.INFO, "ApplicationUtils :: updateCommandTableForStartAPI() :: conn.commit() ::: ");
                conn.commit();
            }


        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: updateCommandTableForStartAPI() :: Exception " + sql.toString());
            logger.log(Level.ERROR, "ApplicationUtils :: updateCommandTableForStartAPI() :: Exception  :: " + ex);
            throw ex;
        } finally {

            try {
                if (pStmt != null) {
                    pStmt.close();
                    pStmt = null;
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e) {
            }

        }
        return results;

    }

    public static int updateCommandTableForStopAPI(String commandId) throws Exception {

        PreparedStatement pStmt = null;
        int results = 0;
        String sql = "";

        Connection conn = null;

        try {
            conn = getConnection();

            ///The following statement is commented bcoz need to update status of all 3 API (Read,Convert,Load)
            ///sql = "UPDATE COMMAND SET SS_STATUS = 0 WHERE COMMAND_ID = "+commandId;

            ///sql = "UPDATE COMMAND SET SS_STATUS = 0 , SS_ACTION = 1 WHERE DEVICE_TYPE_ID IN ( SELECT DEVICE_TYPE_ID FROM COMMAND WHERE COMMAND_ID = "+commandId+" )";

            ///This is working but need to some enhancement
            ///sql = "UPDATE COMMAND SET SS_STATUS = 0 WHERE DEVICE_TYPE_ID IN ( SELECT DEVICE_TYPE_ID FROM COMMAND WHERE COMMAND_ID = "+commandId+" )";
            sql = "UPDATE COMMAND SET SS_STATUS = 0 , SS_ACTION = 0 WHERE DEVICE_TYPE_ID IN ( SELECT DEVICE_TYPE_ID FROM COMMAND WHERE COMMAND_ID = " + commandId + " )";

            logger.log(Level.INFO, "ApplicationUtils :: updateCommandTableForStopAPI() :: SQL ::: " + sql.toString());

            pStmt = conn.prepareStatement(sql);

            results = pStmt.executeUpdate();

            logger.log(Level.INFO, "ApplicationUtils :: updateCommandTableForStopAPI() :: Records updated (results) ::: " + results);

            if (results > 0) {
                logger.log(Level.INFO, "ApplicationUtils :: updateCommandTableForStopAPI() :: conn.commit() ::: ");
                conn.commit();
            }


        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: updateCommandTableForStopAPI() :: Exception " + sql.toString());
            logger.log(Level.ERROR, "ApplicationUtils :: updateCommandTableForStopAPI() :: Exception  :: " + ex);
            throw ex;
        } finally {

            try {
                if (pStmt != null) {
                    pStmt.close();
                    pStmt = null;
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e) {
            }

        }
        return results;

    }



    public static boolean fileIsPresent(String path) throws Exception {
        String fileName = "";
        boolean isPresent = false;
        try {
            File f = new File(path);
            isPresent = f.isFile();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "ApplicationUtils :: getFileNameFromFolderPath() :: Exception == " + ex);
            throw ex;
        }

        return isPresent;
    }

    public static void loadSchedulers() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String urlString = "";
        URL url = null;
        URLConnection urlCon = null;
        InputStream is = null;
        try {
            conn = getConnection();
            pst = conn.prepareStatement(" SELECT DISTINCT IP_ADDRESS FROM IP_COMMAND_MAP ICP,COMMAND C WHERE ICP.STATUS_CD='A' "
                    + " AND ICP.COMMAND_ID=C.COMMAND_ID AND C.EXECUTION_SEQ=1 AND ICP.SCHEDULER_TYPE=1 order by ip_address");

            rs = pst.executeQuery();
            while (rs.next()) {
                try {
                    urlString = "http://" + rs.getString("IP_ADDRESS") + ":8084/scheduler/LoadSchedulerServlet";
                    url = new URL(urlString);
                    urlCon = (URLConnection) url.openConnection();
                    urlCon.connect();
                    is = urlCon.getInputStream();
                } catch (Exception e) {
                }
            }
        } catch (Exception ex) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }

            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
            }

            try {
                if (conn != null) {
                    conn = null;
                }
            } catch (Exception e) {
            }
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
            }
            try {
                if (urlCon != null) {
                    urlCon = null;
                }
            } catch (Exception e) {
            }
        }

    }

   
    public static String newInvokeAPI(String filePath) {

        String clientTypeChannel = "clientChannel";
        String serverTypeChannel = "serverChannel";
        String channelType = "channelType";
        boolean processCompleted = false;
        int port = 2985;
        String localhost = "127.0.0.1";
        //ipAddress = "10.8.0.21";
        int len = filePath.length();
        filePath = filePath.replaceAll("\\\\", "/");
        String outputLine = "1600" + len + "0104000001" + filePath;
        String inputLine = "\"" + DriveLetter + ":\\DLMS\\MIOS\\MIOS App\\MIOS API5\\DLMSAPI5 CFW.bat\" " + localhost + " " + port;
        String recieved = null;
        Runtime runtime = null;

        try {
            logger.log(Level.INFO, "ApplicationUtils :: newInvokeAPI() :: method called ");
            Thread t = new Thread();
            t.start();
            ServerSocketChannel channel = ServerSocketChannel.open();
            channel.configureBlocking(false);

            channel.socket().bind(new InetSocketAddress(localhost, port));
            channel.socket().setReuseAddress(true);
            channel.socket().setSoTimeout(120000);

            Selector selector = SelectorProvider.provider().openSelector();
            try {
                runtime = Runtime.getRuntime();
                runtime.exec(inputLine);
                System.out.println("Invoked:: " + inputLine);
            } catch (IOException e) {
                inputLine = DriveLetter + ":\\DLMS\\MIOS\\MIOS App\\MIOS API5\\DLMSAPI5 CFW.bat " + localhost + " " + port;
                try {
                    runtime = Runtime.getRuntime();
                    runtime.exec(inputLine);
                    System.out.println("Invoked:: " + inputLine);
                } catch (Exception ex) {
                    logger.log(Level.ERROR, "ApplicationUtils :: newInvokeAPI() :: Exception :: " + e.getMessage());
                    selector.close();
                    channel.socket().close();
                    channel.close();
                    recieved = ex.getMessage();
                    return recieved;
                }
            } catch (Exception e) {
                logger.log(Level.ERROR, "ApplicationUtils :: newInvokeAPI() :: Exception :: " + e.getMessage());
                selector.close();
                channel.socket().close();
                channel.close();
                recieved = e.getMessage();
                return recieved;
            }

            SelectionKey socketServerSelectionKey = channel.register(selector, SelectionKey.OP_ACCEPT);
            Map<String, String> properties = new HashMap<String, String>();
            properties.put(channelType, serverTypeChannel);
            socketServerSelectionKey.attach(properties);

            while (!processCompleted) {
                if (selector.select(10000) == 0) {
                    continue;
                }
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectedKeys.iterator();
                t.sleep(2000);

                while (iterator.hasNext()) {

                    SelectionKey key = iterator.next();
                    iterator.remove();

                    if (((Map) key.attachment()).get(channelType).equals(serverTypeChannel)) {

                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                        SocketChannel clientSocketChannel = serverSocketChannel.accept();
                        clientSocketChannel.socket().setReuseAddress(true);
                        clientSocketChannel.socket().setSoTimeout(120000);
                        clientSocketChannel.socket().setKeepAlive(true);

                        System.out.println("Client Connected on:: " + clientSocketChannel.socket().getRemoteSocketAddress()
                                + ":" + clientSocketChannel.socket().getLocalAddress());

                        System.out.println("Accepting Client Request");
                        clientSocketChannel.configureBlocking(false);
                        SelectionKey clientKey = clientSocketChannel.register(selector, SelectionKey.OP_WRITE, SelectionKey.OP_READ);
                        Map<String, String> clientproperties = new HashMap<String, String>();
                        clientproperties.put(channelType, clientTypeChannel);
                        clientKey.attach(clientproperties);

                    } else {

                        ByteBuffer buffer = ByteBuffer.allocate(1000);
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        int bytesRead = 0;
                        if (key.isWritable()) {
                            System.out.println("Writing To Client");
                            String cmd = "";

                            if (recieved == null) {
                                cmd = "1600000600000000\r\n";
                                buffer = ByteBuffer.wrap(cmd.getBytes());
                            } else if (recieved.equals("1600120700000000DLMSAPI5.bat")) {
                                cmd = outputLine + "\r\n";
                                buffer = ByteBuffer.wrap(cmd.getBytes());
                            } else if (recieved.equals("Init Port failed") || recieved.equals("Writing to DCU finished")) {
                                cmd = "1600000304000000\r\n";
                                processCompleted = true;
                                buffer = ByteBuffer.wrap(cmd.getBytes());
                            }
                            while (buffer.hasRemaining()) {
                                clientChannel.write(buffer);
                                System.out.print("Sent :: " + cmd);
                            }
                            buffer.clear();

                            key.interestOps(SelectionKey.OP_READ);
                        }
                        if (key.isReadable()) {
                            System.out.println("Reading Client Data");
                            if ((bytesRead = clientChannel.read(buffer)) > 0) {
                                buffer.flip();
                                recieved = Charset.defaultCharset().decode(buffer).toString();
                                recieved = recieved.trim();
                                System.out.println("Received :: " + recieved);
                                buffer.clear();
                            }
                            if (bytesRead < 0) {
                                clientChannel.close();
                            }
                            if (recieved.equals("1600120700000000DLMSAPI5.bat") || recieved.equals("Init Port failed") || recieved.equals("Writing to DCU finished")) {
                                try {
                                    key.interestOps(SelectionKey.OP_WRITE);
                                } catch (CancelledKeyException e) {
                                    processCompleted = true;
                                }
                            } else {
                                key.interestOps(SelectionKey.OP_READ);
                            }

                        }
                        selector.wakeup();
                    }
                }
            }
            selector.close();
            channel.socket().close();
            channel.close();
            runtime.exec("WMIC /OUTPUT:C:\\ProcessList2.txt PROCESS WHERE (Commandline.like\"%.jar 127.0.0.1 " + port + "%\" and Description.like\"%java.exe%\") delete").waitFor();

            System.out.println("Final Result::" + recieved);
        } catch (Exception e) {
            recieved = e.getMessage();
            logger.log(Level.ERROR, "ApplicationUtils :: newInvokeAPI() :: Exception :: " + e.getMessage());
            //e.printStackTrace();
        }
        return recieved;
    }

    public static void UpdateDCUWritingStatus(String dcuId, String status) {
        String sql = "";
        Connection con = null;
        PreparedStatement ps = null;

        try {
            logger.log(Level.INFO, "AjaxControlServlet :: UpdateDCUWritingStatus() :: method called");

            sql = "UPDATE DCU_DETAILS SET LAST_WRITTEN_ON=SYSDATE,LAST_WRITING_STATUS='" + status + "' WHERE DCU_ID=" + dcuId;
            //System.out.println(sql);
            con = ApplicationUtils.getConnection();
            ps = con.prepareStatement(sql);
            if (ps.executeUpdate() > 0) {
                con.commit();
            } else {
                con.rollback();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.INFO, "AjaxControlServlet :: UpdateDCUWritingStatus() :: Exception :: " + ex.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
            }
        }
    }

    public static void startDCUWriter(String urlString) {
        URL url = null;
        URLConnection urlCon = null;
        InputStream is = null;
        try {
            System.out.println("Connecting to ::" + urlString);
            url = new URL(urlString);
            urlCon = (URLConnection) url.openConnection();
            urlCon.connect();
            is = urlCon.getInputStream();

        } catch (Exception ex) {
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
            }
            try {
                if (urlCon != null) {
                    urlCon = null;
                }
            } catch (Exception e) {
            }
        }

    }

    public static String BlankInput(String input) throws Exception {
        String Input = "0";
        try {
            if ((input != null) && !(input.equals(""))) {
                Input = input;
            }
            return Input;
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        } finally {
        }
    }

    public static LinkedList getLast12YYYYMM() throws Exception {
        LinkedList list = new LinkedList();
        java.util.Date sysdate = new java.util.Date();
        String[] YYYYMM = new String[12];
        int YYYY;
        int MM;
        try {
            YYYY = Integer.parseInt(getYYYY(sysdate));
            YYYY = YYYY - 1; // Last Year
            MM = Integer.parseInt(getMM(sysdate));
            //MM = MM - 1; // Prev. Month
            for (int i = 0; i < 12; i++) {
                if (MM < 12) {
                    YYYYMM[i] = (("0000" + String.valueOf(YYYY)).substring(String.valueOf(YYYY).length()) + ("00" + String.valueOf(MM + 1)).substring(String.valueOf(MM + 1).length()));
                    MM = MM + 1;
                } else if (MM == 12) {
                    YYYYMM[i] = (("0000" + String.valueOf(YYYY + 1)).substring(String.valueOf(YYYY + 1).length()) + "01");
                    YYYY = YYYY + 1;
                    MM = 1;
                }
            }
            for (int i = 0; i < 12; i++) {
                list.add(YYYYMM[11 - i]);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        } finally {
        }
    }

    public static String getYYYY(java.util.Date date) throws Exception {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy");
            String yyyy = ("0000" + format.format(date)).substring(format.format(date).length());
            return yyyy;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        }
    }

    public static String getMM(java.util.Date date) throws Exception {
        try {
            SimpleDateFormat format = new SimpleDateFormat("MM");
            String mm = ("00" + format.format(date)).substring(format.format(date).length());
            return mm;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        }
    }

    public static int getDayCount(String yyyymm) throws Exception {
        String YYYYMM = yyyymm;
        float YYYY;
        int daycount = 0;
        try {
            if ((YYYYMM.substring(4).equals("01")) || (YYYYMM.substring(4).equals("03"))
                    || (YYYYMM.substring(4).equals("05")) || (YYYYMM.substring(4).equals("07"))
                    || (YYYYMM.substring(4).equals("08")) || (YYYYMM.substring(4).equals("10"))
                    || (YYYYMM.substring(4).equals("12"))) {
                daycount = 31;
            } else if ((YYYYMM.substring(4).equals("04")) || (YYYYMM.substring(4).equals("06"))
                    || (YYYYMM.substring(4).equals("09")) || (YYYYMM.substring(4).equals("11"))) {
                daycount = 30;
            } else if (YYYYMM.substring(4).equals("02")) {
                YYYY = Float.parseFloat(YYYYMM.substring(0, 4));

                if ((YYYY % 4) > 0) {
                    daycount = 28;
                } else if ((YYYY % 100) > 0) {
                    daycount = 29;
                } else if ((YYYY % 400) > 0) {
                    daycount = 28;
                } else {
                    daycount = 29;
                }
            }
            return daycount;
        } catch (Exception e) {
            e.printStackTrace();
            return 28;
        } finally {
            return daycount;
        }
    }

    public static String NullInput(String input) throws Exception {
        String Input = " ";
        try {
            if ((input != null) && !(input.equals(""))) {
                Input = input;
            }
            return Input;
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        } finally {
        }
    }

    public static String getWorkingHours(String intime, String outtime) throws Exception {
        Float In_Time, Out_Time;
        String WorkingHours;
        String WorkingMins;
        String WorkingTime;
        int endindex = 1;
        int sindex = 2;
        try {
            In_Time = (Float.parseFloat(intime.substring(0, 2)) + (Float.parseFloat(intime.substring(3, 5)) / 60));
            Out_Time = (Float.parseFloat(outtime.substring(0, 2)) + (Float.parseFloat(outtime.substring(3, 5)) / 60));
            WorkingHours = String.format("%.2f", (Out_Time - In_Time));
            if ((Out_Time - In_Time) >= 10.01) {
                endindex = 2;
                sindex = 3;
            } else {
                endindex = 1;
                sindex = 2;
            }
            WorkingMins = String.format("%.0f", (Float.parseFloat(WorkingHours.substring(sindex)) / 100 * 60));
            WorkingTime = WorkingHours.substring(0, endindex) + ":" + ("00" + WorkingMins).substring(WorkingMins.length());
            return WorkingTime;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        }
    }

    public static int getFiscalYear() {
        try {
            java.util.Date sysdate = new java.util.Date();
            int YYYY;
            int MM;
            YYYY = Integer.parseInt(getYYYY(sysdate));
            //YYYY = YYYY - 1; // Last Year
            MM = Integer.parseInt(getMM(sysdate));

            int y = MM <= 3 ? YYYY - 1 : YYYY;
            return y;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
        }
    }

    public static LinkedList getTimeList() throws Exception {
        LinkedList list = new LinkedList();
        try {
            for (int i = 0; i < 24; i++) {
                list.add(("00" + String.valueOf(i)).substring(String.valueOf(i).length()) + ":00");
                list.add(("00" + String.valueOf(i)).substring(String.valueOf(i).length()) + ":30");
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        } finally {
        }
    }

//    public static Connection getAppsConnection() throws Exception {
//
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//        } catch (ClassNotFoundException e) {
//            System.out.println("Where is your Oracle JDBC Driver?");
//            e.printStackTrace();
//            return null;
//        }
//        Connection connection = null;
//        try {
//            if (System.getProperty("ENV_TYPE").equals("PROD")) {
//                connection = DriverManager.getConnection(
//                        "jdbc:oracle:thin:@mis2db-scan.mahadiscom.in:1524:pprod", "apps",
//                        "absm1s2");
//            } else if (System.getProperty("ENV_TYPE").equals("TEST")) {
//                connection = DriverManager.getConnection(
//                        "jdbc:oracle:thin:@mis2testdb.mahadiscom.in:1528:mistest", "apps",
//                        "mistetapps");
//            } else if (System.getProperty("ENV_TYPE").equals("DEV")) {
//                connection = DriverManager.getConnection(
//                        "jdbc:oracle:thin:@mis2devdb.mahadiscom.in:1525:misdev", "apps",
//                        "msedcldev1");
//            }
//        } catch (SQLException e) {
//            System.out.println("Connection Failed! Check output console");
//            e.printStackTrace();
//            return null;
//        }
//        if (connection != null) {
//            return connection;
//        } else {
//            return null;
//        }
//
//    }

    public static String getRequestParameter(HttpServletRequest request, String param) throws Exception {
        String ret = "";
        try {
            if (!isBlank(request.getParameter(param))) {
                ret = (String) request.getParameter(param);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

   
}// End of Class

