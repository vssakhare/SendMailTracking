/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor;

import in.emp.vendor.bean.MailStatusBean;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorCommMailLogBean;
import in.emp.vendor.bean.VendorInputBean;
import java.util.LinkedList;

public interface VendorDelegate {

    public LinkedList<VendorInputBean> getMailTrackerList(VendorInputBean VendorInputBeanObj) throws Exception;
     public void VendorMailSendTxnHelper(MailStatusBean mailbean) throws Exception;
      public void VendorMailNotSendTxnHelper(MailStatusBean mailbean) throws Exception;
    public VendorBean setUnsubscribeList (VendorBean vendorBeanObj) throws Exception;
        public MailStatusBean checkUnsubscribeList (MailStatusBean mailbean) throws Exception;
     public  LinkedList<VendorInputBean> getInvalidMailTrackerList(String mailid) throws Exception;
      public VendorCommMailLogBean saveVendorCommLog(VendorCommMailLogBean vendorMailLogBean) throws Exception;
     
}
