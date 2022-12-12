/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.bean;

/**
 *
 * @author Pooja Jadhav
 */
public class VendorBean  implements java.io.Serializable {
   private String ID; 
   private String email_id;
    private String UNSUBSCRIBE_FLAG; 
 private String REASON;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
 
    public String getREASON() {
        return REASON;
    }

    public void setREASON(String REASON) {
        this.REASON = REASON;
    }
    
    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getUNSUBSCRIBE_FLAG() {
        return UNSUBSCRIBE_FLAG;
    }

    public void setUNSUBSCRIBE_FLAG(String UNSUBSCRIBE_FLAG) {
        this.UNSUBSCRIBE_FLAG = UNSUBSCRIBE_FLAG;
    }

}
