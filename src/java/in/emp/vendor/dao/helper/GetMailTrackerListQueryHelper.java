/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper;

import in.emp.dao.QueryHelper;
import in.emp.vendor.bean.VendorInputBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Pooja Jadhav
 */
public class GetMailTrackerListQueryHelper  implements QueryHelper{
      private static Logger logger = Logger.getLogger(GetMailTrackerListQueryHelper.class); 
    private VendorInputBean vendorInputBeanObj;
     public GetMailTrackerListQueryHelper(VendorInputBean vendorInputBeanObj) {
        this.vendorInputBeanObj = vendorInputBeanObj;
    }
     public Object getDataObject(ResultSet results) throws Exception {
        VendorInputBean vendorBeanObj = new VendorInputBean();
        try {
            logger.log(Level.INFO, "GetMailTrackerListQueryHelper ::: getDataObject() :: method called ::");
      
           
        
           vendorBeanObj.setVendorNumber(results.getString("vendor_number"));
           vendorBeanObj.setVendorName(results.getString("VENDOR_NAME"));
          // vendorBeanObj.setVENDOR_EMAILID(results.getString("VENDOR_EMAILID"));
          // vendorBeanObj.setMAIL_SUBJECT(results.getString("MAIL_SUBJECT"));
          // vendorBeanObj.setMAIL_BODY(results.getString("MAIL_BODY"));
          
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetMailTrackerListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return vendorBeanObj;
    }
       public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {
            sql.append(" SELECT P.VENDOR_EMAILID,P.VENDOR_NAME ,P.VENDOR_NUMBER,P.MAIL_SUBJECT,MAIL_BODY   ");
            sql.append("  FROM MAIL_INFO_TEST P ");
        
            
           
            logger.log(Level.INFO, "GetMailTrackerListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            
                        
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetMailTrackerListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }

}
