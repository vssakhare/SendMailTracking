/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.vendor.bean;

/**
 *
 * @author Prajakta
 */
public class VendorApplFilePrezData implements java.io.Serializable {

    private VendorApplFileBean leaveapplFileBean;    
 
    public VendorApplFileBean getVendorapplFileBean() {
        return leaveapplFileBean;
    }

    public void setVendorapplFileBean(VendorApplFileBean leaveapplFileBean) {
        this.leaveapplFileBean = leaveapplFileBean;
    }
}