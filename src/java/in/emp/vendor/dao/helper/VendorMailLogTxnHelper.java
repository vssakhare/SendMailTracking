/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Vaishali S
 */

package in.emp.vendor.dao.helper;

import in.emp.dao.TxnHelper;
import in.emp.util.ApplicationUtils;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import in.emp.common.ApplicationConstants;
import in.emp.dao.TxnHelper;

import in.emp.util.ApplicationUtils;
import in.emp.vendor.bean.VendorCommMailLogBean;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Rikma Rai
 */
public class VendorMailLogTxnHelper implements TxnHelper {

    private static Logger logger = Logger.getLogger(VendorMailLogTxnHelper.class);
    private VendorCommMailLogBean vendorMailLogBean;



    public VendorMailLogTxnHelper(VendorCommMailLogBean vendorMailLogBean) {
        this.vendorMailLogBean = vendorMailLogBean;
    }

    public VendorMailLogTxnHelper() {
    }

    @Override
    public Object createObject(Connection conn) throws Exception {
        int count;
        PreparedStatement statement = null;
        //PreparedStatement statement1 = null;
        CallableStatement proc_stmt = null;
        StringBuilder sql = new StringBuilder();
        try {
            logger.log(Level.INFO, "VendorMailLogTxnHelper ::: createObject() :: method called ::");
            System.out.println("VendorMailLogTxnHelper ::: createObject() :: method called ::");
            vendorMailLogBean.setCommLogId(ApplicationUtils.getNextSequenceId(conn, "SEQ_VENDOR_COMM_MAIL_LOG").intValue());
            sql.append("INSERT INTO vendor_comm_mail_log ");
            sql.append(" ( COMMUNICATION_LOG_ID,CREATED	,RECIPIENTS,SUBJECT,HOST,MESSAGE_BODY ) ");

           
            sql.append(" VALUES (?,SYSTIMESTAMP,?,?,?,? )");
             

            statement = conn.prepareStatement(sql.toString());
            statement.setInt(1, vendorMailLogBean.getCommLogId());
            statement.setString(2, vendorMailLogBean.getRecipients());
            statement.setString(3, vendorMailLogBean.getSubject());
            statement.setString(4, vendorMailLogBean.getHost());
              statement.setString(5, vendorMailLogBean.getMessageBody());
            
            logger.log(Level.INFO, "VendorMailLogTxnHelper ::: createObject() :: SQL :: " + sql.toString());

            count = statement.executeUpdate();
            if (count == 0) {
                vendorMailLogBean.setCommLogId(0);
            }

            if (count > 0) {

//                vendorPrezDataObj.setVendorInputBean(vendorInputBeanObj);
              //  String appl_id = legalInvoiceBean.getApplId() + "";
                //int appl_id1 = Integer.parseInt(appl_id);
//               proc_stmt = conn.prepareCall("{ call PROC_SUMMARY_UPD_PS(?) }");
//               proc_stmt.setString(1, legalInvoiceBean.getApplId());
//                proc_stmt.executeQuery();
                conn.commit();

            }
            System.out.println("count::" + count + "   app_id::" + vendorMailLogBean.getCommLogId());

        } catch (Exception e) {
            e.printStackTrace();
         vendorMailLogBean.setCommLogId(0);
        }

        return vendorMailLogBean;

    }

    @Override
    public void updateObject(Connection conn) throws Exception {
         PreparedStatement statement = null;
         CallableStatement proc_stmt = null;
        StringBuilder sql = new StringBuilder();
        int count = 0;
      int i=1;
     
        try {
            logger.log(Level.INFO, "VendorMailLogTxnHelper ::: updateObject() :: method called ::");
            
       
            sql.append(" UPDATE vendor_comm_mail_log ");
            sql.append(" SET is_mail_sent = ?,UPDATED = SYSTIMESTAMP "); 
            
            if (vendorMailLogBean.getError() != null)
            {
               sql.append(" , error = ? "); 
            }
          
            sql.append(" WHERE COMMUNICATION_LOG_ID = ?  ");
         
             logger.log(Level.INFO, "VendorMailLogTxnHelper :: getQueryResults() :: SQL :: " + sql.toString());
            statement = conn.prepareStatement(sql.toString());
          
            
             if (vendorMailLogBean.getError() != null){
                  statement.setString(1, vendorMailLogBean.getIsMailSent());
                  statement.setString(2, vendorMailLogBean.getError());
                  statement.setInt(3, vendorMailLogBean.getCommLogId());
             }
             else{
                 
                 statement.setString(1,  vendorMailLogBean.getIsMailSent());
                 statement.setInt(2, vendorMailLogBean.getCommLogId());
             }
          
            count = statement.executeUpdate();
           

           conn.commit();
    }
              catch (Exception ex) {
                  ex.printStackTrace();
            logger.log(Level.ERROR, "VendorMailLogTxnHelper ::: updateObject() :: Exception :: " + ex);
            throw ex;
        }

    }

    @Override
    public void deleteObject(Connection conn) throws Exception {
       
        try {
        
        } catch (Exception ex) {
           
        }
    }

    @Override
    public void updateObjectAttribute(Connection conn, HashMap attributeMap) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
