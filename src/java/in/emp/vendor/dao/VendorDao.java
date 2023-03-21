/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao;

//-- java imports
import in.emp.vendor.bean.MailStatusBean;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorCommMailLogBean;
import in.emp.vendor.bean.VendorInputBean;
import java.util.LinkedList;

import java.util.List;

public interface VendorDao {

    public LinkedList<VendorInputBean> getMailTrackerList(VendorInputBean vendorInputBeanObj) throws Exception;

    public LinkedList<VendorInputBean> getInvalidMailTrackerList(String mailid) throws Exception;

    public void VendorMailSendTxnHelper(MailStatusBean mailbean) throws Exception;

        public void VendorMailNotSendTxnHelper(MailStatusBean mailbean) throws Exception;
        
    public VendorBean setUnsubscribeList(VendorBean vendorBeanObj) throws Exception;

    public MailStatusBean checkUnsubscribeList(MailStatusBean mailbean) throws Exception;
    
     public VendorCommMailLogBean saveVendorCommLog(VendorCommMailLogBean vendorMailLogBean) throws Exception;

}
