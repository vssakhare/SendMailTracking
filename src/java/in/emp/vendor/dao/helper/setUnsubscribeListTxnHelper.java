/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper;

/**
 *
 * @author Pooja Jadhav
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


//-- Java imports
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.*;

import in.emp.dao.TxnHelper;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.bean.MailStatusBean;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorPrezData;


//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Pooja Jadhav
 */
public class setUnsubscribeListTxnHelper implements TxnHelper {

    private static Logger logger = Logger.getLogger(setUnsubscribeListTxnHelper.class);
   VendorBean vendorBeanObj;
private VendorPrezData vendorPrezDataObj;
    
    public setUnsubscribeListTxnHelper(VendorBean vendorBeanObj) {
        this.vendorBeanObj = vendorBeanObj;
    }//constructor ends

    /**
     * @public Constructor TAclaimsTxnHelper()
     *
     * @param vendorPrezDataObj	TAclaimsPrezData
     */
    public setUnsubscribeListTxnHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
       
    }//constructor ends

    /**
     * Public API to create data Object.
     *
     * @param conn object of Connection
     * @return Object
     * @throws Exception
     */
    public Object createObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
      
        int count = 0;
        try {
            logger.log(Level.INFO, "setUnsubscribeListTxnHelper ::: createObject() :: method called ::");

              StringBuilder sql = new StringBuilder();
           
           sql.append(" INSERT INTO UNSUBSCRIBE_MAIL_DETAILS ");
            sql.append(" (ID ,EMAIL_ID,REASON,UNSUBSCRIBE_FLAG  ) "); // 4 here
             sql.append(" SELECT ");
            
            sql.append(" UNSUBSCRIBE_SEQ.NEXTVAL,?, ?, 'Y' FROM DUAL WHERE NOT EXISTS(SELECT 1 FROM  "); // 1 + 9 till here
         sql.append("  UNSUBSCRIBE_MAIL_DETAILS where EMAIL_ID=?) ");

            statement = conn.prepareStatement(sql.toString());
             
            statement.setString(1, vendorBeanObj.getEmail_id());
              statement.setString(2, vendorBeanObj.getREASON());
           statement.setString(3, vendorBeanObj.getEmail_id());
          
           
           
            
            logger.log(Level.INFO, "setUnsubscribeListTxnHelper ::: createObject() :: SQL :: " + sql.toString());
            
            count = statement.executeUpdate();
           if (count == 0)
            vendorBeanObj.setUNSUBSCRIBE_FLAG("N");
                    
            if (count > 0)
            {
                
             
                conn.commit();
                 
            }
       
        

        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "setUnsubscribeListTxnHelper ::: createObject() :: Exception :: " + ex);
            ex.printStackTrace();
            throw ex;
            
        }
        return vendorBeanObj;
    }//createObject() ends

    /**
     * Public API to update data Object.
     *
     * @param conn object of Connection
     * @return void
     * @throws Exception
     */
    public void updateObject(Connection conn) throws Exception {
      
        try {
          
            
            
        } catch (Exception ex) {
         
        }

    }//updateObject() ends

    /**
     * Public API to delete data Object.
     *
     * @param conn object of Connection
     * @return void
     * @throws Exception
     */
    public void deleteObject(Connection conn) throws Exception {
      
        try {
        
        } catch (Exception ex) {
           
        }
    }//deleteObject() ends

    /**
     * Public API to update ObjectAttribute
     *
     * @param conn object of Connection
     * @return void
     * @throws Exception
     */
    public void updateObjectAttribute(Connection conn, HashMap attributeMap) throws Exception {
    }//updateObjectAttribute ends
}