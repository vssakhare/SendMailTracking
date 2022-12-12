/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.manager;


import in.emp.vendor.VendorDelegate;
import in.emp.vendor.bean.MailStatusBean;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.dao.OracleVendorDao;
import in.emp.vendor.dao.VendorDao;
import java.util.LinkedList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class VendorManager implements VendorDelegate {

    private static String CLASS_NAME = VendorManager.class.getName();
    private static Logger logger = Logger.getLogger(VendorManager.class);

    public VendorManager() {
    }
      public LinkedList<VendorInputBean> getMailTrackerList(VendorInputBean vendorInputBeanObj) throws Exception {
       VendorDao vendorDaoObj = new OracleVendorDao();
        LinkedList<VendorInputBean> fileList = new LinkedList<VendorInputBean>();
        try {
            logger.log(Level.INFO, " VendorManager :: getMailTrackerList() :: method called");

            fileList = vendorDaoObj.getMailTrackerList(vendorInputBeanObj);
        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getMailTrackerList() :: Exception :: " + ex);
        }
        return fileList;
    }
         public LinkedList<VendorInputBean> getInvalidMailTrackerList(String mailid) throws Exception {
       VendorDao vendorDaoObj = new OracleVendorDao();
        LinkedList<VendorInputBean> fileList = new LinkedList<VendorInputBean>();
        try {
            logger.log(Level.INFO, " VendorManager :: getInvalidMailTrackerList() :: method called");

            fileList = vendorDaoObj.getInvalidMailTrackerList(mailid);
        } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: getInvalidMailTrackerList() :: Exception :: " + ex);
        }
        return fileList;
    }
     public  void VendorMailSendTxnHelper(MailStatusBean mailbean){
          VendorDao vendorDaoObj = new OracleVendorDao();
          try {
            logger.log(Level.INFO, " VendorManager :: VendorMailSendTxnHelper() :: method called");
            vendorDaoObj.VendorMailSendTxnHelper(mailbean);
          } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: VendorMailSendTxnHelper() :: Exception :: " + ex);

        }
     }
      public  void VendorMailNotSendTxnHelper(MailStatusBean mailbean){
          VendorDao vendorDaoObj = new OracleVendorDao();
          try {
            logger.log(Level.INFO, " VendorManager :: VendorMailNotSendTxnHelper() :: method called");
            vendorDaoObj.VendorMailNotSendTxnHelper(mailbean);
          } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: VendorMailNotSendTxnHelper() :: Exception :: " + ex);

        }
     }
     public  VendorBean setUnsubscribeList(VendorBean vendorBeanObj){
          VendorDao vendorDaoObj = new OracleVendorDao();
          try {
            logger.log(Level.INFO, " VendorManager :: setUnsubscribeList() :: method called");
            vendorBeanObj=vendorDaoObj.setUnsubscribeList(vendorBeanObj);
          } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: setUnsubscribeList() :: Exception :: " + ex);

        }
          return vendorBeanObj;
     }
    public  MailStatusBean checkUnsubscribeList(MailStatusBean mailbean){
         VendorDao vendorDaoObj = new OracleVendorDao();
          try {
            logger.log(Level.INFO, " VendorManager :: checkUnsubscribeList() :: method called");
            
            mailbean=vendorDaoObj.checkUnsubscribeList(mailbean);
          } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: checkUnsubscribeList() :: Exception :: " + ex);

        }
         return mailbean;
     }     
}