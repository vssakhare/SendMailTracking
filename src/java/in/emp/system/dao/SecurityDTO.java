/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.system.dao;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class SecurityDTO extends UserAuditDTO implements Serializable {

   private long officeCode = 0;
   private long officeLocationCode = 0;
   private long officeTypeId = 0;
   private String officeCodeActual ;
   private long officeTypeIdSection ;




   public SecurityDTO(long officeCode, long officeLocationCode,
           long officeTypeId) {
      super();
      this.officeCode = officeCode;
      this.officeLocationCode = officeLocationCode;
      this.officeTypeId = officeTypeId;
   }

   public long getOfficeTypeIdSection() {
      return officeTypeIdSection;
   }

   public void setOfficeTypeIdSection(long officeTypeIdSection) {
      this.officeTypeIdSection = officeTypeIdSection;
   }


   public String getOfficeCodeActual() {
      return officeCodeActual;
   }

   public void setOfficeCodeActual(String officeCodeActual) {
      this.officeCodeActual = officeCodeActual;
   }

   /**
    * @return the officeCode
    */
   public long getOfficeCode() {
      return officeCode;
   }

   /**
    * @param officeCode the officeCode to set
    */
   public void setOfficeCode(long officeCode) {
      this.officeCode = officeCode;
   }

   /**
    * @return the officeLocationCode
    */
   public long getOfficeLocationCode() {
      return officeLocationCode;
   }

   /**
    * @param officeLocationCode the officeLocationCode to set
    */
   public void setOfficeLocationCode(long officeLocationCode) {
      this.officeLocationCode = officeLocationCode;
   }

   /**
    * @return the officeTypeId
    */
   public long getOfficeTypeId() {
      return officeTypeId;
   }

   /**
    * @param officeTypeId the officeTypeId to set
    */
   public void setOfficeTypeId(long officeTypeId) {
      this.officeTypeId = officeTypeId;
   }

   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   public String toString() {
      return "SecurityDTO [officeCode=" + officeCode
              + ", officeLocationCode=" + officeLocationCode
              + ", officeTypeId=" + officeTypeId + "]";
   }
}
