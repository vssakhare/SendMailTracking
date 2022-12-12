/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
public class GetInvalidMailListQueryHelper  implements QueryHelper{
     private static Logger logger = Logger.getLogger(GetInvalidMailListQueryHelper.class); 
   String mailid;
     public GetInvalidMailListQueryHelper(String mailid) {
        this.mailid = mailid;
    }
     public Object getDataObject(ResultSet results) throws Exception {
        VendorInputBean vendorBeanObj = new VendorInputBean();
        try {
            logger.log(Level.INFO, "GetInvalidMailListQueryHelper ::: getDataObject() :: method called ::");
      
           
        
           vendorBeanObj.setEmailId(results.getString("EMAILID"));
          // vendorBeanObj.setVendorName(results.getString("VENDOR_NAME"));
          // vendorBeanObj.setVENDOR_EMAILID(results.getString("VENDOR_EMAILID"));
          // vendorBeanObj.setMAIL_SUBJECT(results.getString("MAIL_SUBJECT"));
          // vendorBeanObj.setMAIL_BODY(results.getString("MAIL_BODY"));
          
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetInvalidMailListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return vendorBeanObj;
    }
       public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {
            sql.append(" SELECT P.EMAILID   ");
            sql.append("  FROM INVALID_EMAILID P  WHERE EMAILID = ? ");
        
            
           
            logger.log(Level.INFO, "GetInvalidMailListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
             statement.setString(1, mailid);
                        
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetInvalidMailListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }

}
