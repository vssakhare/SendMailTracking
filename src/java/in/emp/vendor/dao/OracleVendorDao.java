/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao;

//-- java imports
import java.util.LinkedList;
import java.util.HashMap;

//-- Logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import in.emp.dao.BaseDao;
import in.emp.dao.QueryHelper;
import in.emp.vendor.bean.MailStatusBean;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorCommMailLogBean;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.bean.VendorStatuBean;
import in.emp.vendor.dao.helper.GetInvalidMailListQueryHelper;
import in.emp.vendor.dao.helper.GetMailTrackerListQueryHelper;
import in.emp.vendor.dao.helper.VendorMailLogTxnHelper;
import in.emp.vendor.dao.helper.VendorMailNotSendTxnHelper;
import in.emp.vendor.dao.helper.VendorMailSendTxnHelper;
import in.emp.vendor.dao.helper.checkUnsubscribeListQueryHelper;
import in.emp.vendor.dao.helper.setUnsubscribeListTxnHelper;







/**
 *
 * @author Sachin
 */
public class OracleVendorDao extends BaseDao implements VendorDao {

    private static Logger logger = Logger.getLogger(OracleVendorDao.class);


    /**
     * API to get the search data for populating Drop downs
     *
     * @throws Exception	if an error occurs
     * @see	in.mda.device.DeviceDelegate
     * @returns	LinkedList
     */  
       
      public LinkedList<VendorInputBean> getMailTrackerList(VendorInputBean vendorInputBeanObj) throws Exception {
        LinkedList<VendorInputBean> fileList = new LinkedList<VendorInputBean>();
        try {
            logger.log(Level.INFO, " OracleVendorDao :: getSmsTrackerList() :: method called");

            fileList = (LinkedList<VendorInputBean>) getObjectList(new GetMailTrackerListQueryHelper(vendorInputBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, " OracleVendorDao :: getSmsTrackerList() :: Exception :: " + ex);
            ex.printStackTrace();
        }
        return fileList;
    }
       public LinkedList<VendorInputBean> getInvalidMailTrackerList(String mailid) throws Exception {
        LinkedList<VendorInputBean> fileList = new LinkedList<VendorInputBean>();
        try {
            logger.log(Level.INFO, " OracleVendorDao :: getInvalidMailTrackerList() :: method called");

            fileList = (LinkedList<VendorInputBean>)getObjectList(new GetInvalidMailListQueryHelper(mailid));
        } catch (Exception ex) {
            logger.log(Level.ERROR, " OracleVendorDao :: getInvalidMailTrackerList() :: Exception :: " + ex);
            ex.printStackTrace();
        }
        return fileList;
    }
         public void VendorMailSendTxnHelper(MailStatusBean mailbean) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: VendorMailSendTxnHelper() :: method called ::");
             createObject(new VendorMailSendTxnHelper(mailbean));
          //  removeObject(new VendorMailSendTxnHelper(mailbean)); //this call updates or inserts according to the output of deleteobject
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: VendorMailSendTxnHelper() :: Exception :: " + ex);
            throw ex;
        }

    }
          public void VendorMailNotSendTxnHelper(MailStatusBean mailbean) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: VendorMailNotSendTxnHelper() :: method called ::");
             createObject(new VendorMailNotSendTxnHelper(mailbean));
          //  removeObject(new VendorMailSendTxnHelper(mailbean)); //this call updates or inserts according to the output of deleteobject
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: VendorMailNotSendTxnHelper() :: Exception :: " + ex);
            throw ex;
        }

    }
        public VendorBean setUnsubscribeList(VendorBean vendorBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: setUnsubscribeList() :: method called ::");
           vendorBeanObj=  (VendorBean)createObject(new setUnsubscribeListTxnHelper(vendorBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: setUnsubscribeList() :: Exception :: " + ex);
            throw ex;
        }
return vendorBeanObj;
    }      
    public MailStatusBean checkUnsubscribeList(MailStatusBean mailbean) throws Exception {
        try {
            logger.log(Level.INFO, "OracleVendorDao ::: checkUnsubscribeList() :: method called ::");
           mailbean=  (MailStatusBean)getDataObject(new checkUnsubscribeListQueryHelper(mailbean));
         
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: checkUnsubscribeList() :: Exception :: " + ex);
            throw ex;
        }
return mailbean;
    }  
    
    
    public VendorCommMailLogBean saveVendorCommLog(VendorCommMailLogBean vendorMailLogBean)throws Exception
     {
         try {
            logger.log(Level.INFO, "OracleVendorDao ::: saveLFeeTypeDtlsForm() :: method called ::    ");
            if(vendorMailLogBean.getCommLogId() == null){
                vendorMailLogBean = (VendorCommMailLogBean) createObject(new VendorMailLogTxnHelper(vendorMailLogBean));
            }else{
                updateObject(new VendorMailLogTxnHelper(vendorMailLogBean));
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: saveLFeeTypeDtlsForm() :: Exception :: " + ex);
            throw ex;
        }
        return vendorMailLogBean;
     }
    
    
}
 