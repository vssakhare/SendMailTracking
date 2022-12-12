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
import in.emp.vendor.bean.MailStatusBean;
import in.emp.vendor.bean.VendorPrezData;


//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Pooja Jadhav
 */
public class VendorMailNotSendTxnHelper implements TxnHelper {

    private static Logger logger = Logger.getLogger(VendorMailNotSendTxnHelper.class);
    private MailStatusBean mailbean;
private VendorPrezData vendorPrezDataObj;
    
    public VendorMailNotSendTxnHelper(MailStatusBean mailbean) {
        this.mailbean = mailbean;
    }//constructor ends

  
    public VendorMailNotSendTxnHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
       
    }//constructor ends

   
    public Object createObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
       
        int count = 0;
        try {
            logger.log(Level.INFO, "VendorMailNotSendTxnHelper ::: createObject() :: method called ::");

	
              StringBuilder sql = new StringBuilder();
           
           sql.append(" INSERT INTO MAIL_NOT_SENT_INFO ");
            sql.append(" (VENDOR_EMAILID ,DOCUMENT_NUMBER,FILE_NAME,INSERT_DT,MAIL_STATUS,VENDOR_NAME,VENDOR_NUMBER  ) "); // 4 here
             sql.append(" VALUES ");
            
            sql.append(" (?, ?, ?,   SYSTIMESTAMP,'MAIL NOT SENT' ,?,? ) "); // 1 + 9 till here


            statement = conn.prepareStatement(sql.toString());
            statement.setString(1, mailbean.getEmail());
            statement.setString(2, mailbean.getDocumentNumber());
            statement.setString(3, mailbean.getFileName());
            statement.setString(4, mailbean.getVendor_Name());
            statement.setString(5, mailbean.getVendorNumber());
          
           
           
            
            logger.log(Level.INFO, "VendorMailNotSendTxnHelper ::: createObject() :: SQL :: " + sql.toString());
            
            count = statement.executeUpdate();
           conn.commit();
        
       
        

        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "VendorMailNotSendTxnHelper ::: createObject() :: Exception :: " + ex);
            ex.printStackTrace();
            throw ex;
            
        }
        return mailbean;
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