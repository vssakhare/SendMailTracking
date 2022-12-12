/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.bean;

import java.util.Date;

/**
 *
 * @author Pooja Jadhav
 */
public class VendorStatuBean {

    private String VENDOR_CODE;
    private String VENDOR_NAME;
    private String GST_REG_NUM;
    private String CITY;
    private String PINCODE;
    private String VENDOR_REGION;
    private String VENDOR_STATUS;
    private String PHN_LL;
    private String PHN_MOB;
    private String EMAIL;
    private Date VENDOR_DB_INSERT_DATE;
    private Date VENDOR_PROC_CREATE_DATE;
    private String PAN_NO;
    private String TAX_CODE;
    private String VENDOR_INVOICE_NUMBER;
    private String SUBMITTED_SMS_FLAG;
    private String SUBMITTED_MAIL_FLAG;
    private Date SUBMITTED_MAIL_DATE;
    private Date SUBMITTED_SMS_DATE;
 private String VERIFIED_SMS_FLAG;
    private String VERIFIED_MAIL_FLAG;
    private Date VERIFIED_MAIL_DATE;
    private Date VERIFIED_SMS_DATE;
      private String REJECTED_SMS_FLAG;
    private String REJECTED_MAIL_FLAG;
    private Date REJECTED_MAIL_DATE;
    private Date REJECTED_SMS_DATE;
    private String PO_NUMBER;
    private Date INVOICE_SUBMIT_DATE;
    private String Save_Flag;
private String APPL_ID;
    public String getVERIFIED_SMS_FLAG() {
        return VERIFIED_SMS_FLAG;
    }

    public void setVERIFIED_SMS_FLAG(String VERIFIED_SMS_FLAG) {
        this.VERIFIED_SMS_FLAG = VERIFIED_SMS_FLAG;
    }

    public String getVERIFIED_MAIL_FLAG() {
        return VERIFIED_MAIL_FLAG;
    }

    public void setVERIFIED_MAIL_FLAG(String VERIFIED_MAIL_FLAG) {
        this.VERIFIED_MAIL_FLAG = VERIFIED_MAIL_FLAG;
    }

    public Date getVERIFIED_MAIL_DATE() {
        return VERIFIED_MAIL_DATE;
    }

    public void setVERIFIED_MAIL_DATE(Date VERIFIED_MAIL_DATE) {
        this.VERIFIED_MAIL_DATE = VERIFIED_MAIL_DATE;
    }

    public Date getVERIFIED_SMS_DATE() {
        return VERIFIED_SMS_DATE;
    }

    public void setVERIFIED_SMS_DATE(Date VERIFIED_SMS_DATE) {
        this.VERIFIED_SMS_DATE = VERIFIED_SMS_DATE;
    }

    public String getREJECTED_SMS_FLAG() {
        return REJECTED_SMS_FLAG;
    }

    public void setREJECTED_SMS_FLAG(String REJECTED_SMS_FLAG) {
        this.REJECTED_SMS_FLAG = REJECTED_SMS_FLAG;
    }

    public String getREJECTED_MAIL_FLAG() {
        return REJECTED_MAIL_FLAG;
    }

    public void setREJECTED_MAIL_FLAG(String REJECTED_MAIL_FLAG) {
        this.REJECTED_MAIL_FLAG = REJECTED_MAIL_FLAG;
    }

    public Date getREJECTED_MAIL_DATE() {
        return REJECTED_MAIL_DATE;
    }

    public void setREJECTED_MAIL_DATE(Date REJECTED_MAIL_DATE) {
        this.REJECTED_MAIL_DATE = REJECTED_MAIL_DATE;
    }

    public Date getREJECTED_SMS_DATE() {
        return REJECTED_SMS_DATE;
    }

    public void setREJECTED_SMS_DATE(Date REJECTED_SMS_DATE) {
        this.REJECTED_SMS_DATE = REJECTED_SMS_DATE;
    }
     
    public String getAPPL_ID() {
        return APPL_ID;
    }

    public void setAPPL_ID(String APPL_ID) {
        this.APPL_ID = APPL_ID;
    }

    public String getSave_Flag() {
        return Save_Flag;
    }

    public void setSave_Flag(String Save_Flag) {
        this.Save_Flag = Save_Flag;
    }

    public Date getINVOICE_SUBMIT_DATE() {
        return INVOICE_SUBMIT_DATE;
    }

    public void setINVOICE_SUBMIT_DATE(Date INVOICE_SUBMIT_DATE) {
        this.INVOICE_SUBMIT_DATE = INVOICE_SUBMIT_DATE;
    }

    public String getPO_NUMBER() {
        return PO_NUMBER;
    }

    public void setPO_NUMBER(String PO_NUMBER) {
        this.PO_NUMBER = PO_NUMBER;
    }

    public Date getSUBMITTED_SMS_DATE() {
        return SUBMITTED_SMS_DATE;
    }

    public void setSUBMITTED_SMS_DATE(Date SUBMITTED_SMS_DATE) {
        this.SUBMITTED_SMS_DATE = SUBMITTED_SMS_DATE;
    }

    public Date getSUBMITTED_MAIL_DATE() {
        return SUBMITTED_MAIL_DATE;
    }

    public void setSUBMITTED_MAIL_DATE(Date SUBMITTED_MAIL_DATE) {
        this.SUBMITTED_MAIL_DATE = SUBMITTED_MAIL_DATE;
    }

    public String getSUBMITTED_MAIL_FLAG() {
        return SUBMITTED_MAIL_FLAG;
    }

    public void setSUBMITTED_MAIL_FLAG(String SUBMITTED_MAIL_FLAG) {
        this.SUBMITTED_MAIL_FLAG = SUBMITTED_MAIL_FLAG;
    }

    public String getVENDOR_INVOICE_NUMBER() {
        return VENDOR_INVOICE_NUMBER;
    }

    public void setVENDOR_INVOICE_NUMBER(String VENDOR_INVOICE_NUMBER) {
        this.VENDOR_INVOICE_NUMBER = VENDOR_INVOICE_NUMBER;
    }

    public String getSUBMITTED_SMS_FLAG() {
        return SUBMITTED_SMS_FLAG;
    }

    public void setSUBMITTED_SMS_FLAG(String SUBMITTED_SMS_FLAG) {
        this.SUBMITTED_SMS_FLAG = SUBMITTED_SMS_FLAG;
    }

    public String getPAN_NO() {
        return PAN_NO;
    }

    public void setPAN_NO(String PAN_NO) {
        this.PAN_NO = PAN_NO;
    }

    public String getTAX_CODE() {
        return TAX_CODE;
    }

    public void setTAX_CODE(String TAX_CODE) {
        this.TAX_CODE = TAX_CODE;
    }

    public String getVENDOR_CODE() {
        return VENDOR_CODE;
    }

    public void setVENDOR_CODE(String VENDOR_CODE) {
        this.VENDOR_CODE = VENDOR_CODE;
    }

    public String getVENDOR_NAME() {
        return VENDOR_NAME;
    }

    public void setVENDOR_NAME(String VENDOR_NAME) {
        this.VENDOR_NAME = VENDOR_NAME;
    }

    public String getGST_REG_NUM() {
        return GST_REG_NUM;
    }

    public void setGST_REG_NUM(String GST_REG_NUM) {
        this.GST_REG_NUM = GST_REG_NUM;
    }

    public String getCITY() {
        return CITY;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public String getPINCODE() {
        return PINCODE;
    }

    public void setPINCODE(String PINCODE) {
        this.PINCODE = PINCODE;
    }

    public String getVENDOR_REGION() {
        return VENDOR_REGION;
    }

    public void setVENDOR_REGION(String VENDOR_REGION) {
        this.VENDOR_REGION = VENDOR_REGION;
    }

    public String getVENDOR_STATUS() {
        return VENDOR_STATUS;
    }

    public void setVENDOR_STATUS(String VENDOR_STATUS) {
        this.VENDOR_STATUS = VENDOR_STATUS;
    }

    public String getPHN_LL() {
        return PHN_LL;
    }

    public void setPHN_LL(String PHN_LL) {
        this.PHN_LL = PHN_LL;
    }

    public String getPHN_MOB() {
        return PHN_MOB;
    }

    public void setPHN_MOB(String PHN_MOB) {
        this.PHN_MOB = PHN_MOB;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public Date getVENDOR_DB_INSERT_DATE() {
        return VENDOR_DB_INSERT_DATE;
    }

    public void setVENDOR_DB_INSERT_DATE(Date VENDOR_DB_INSERT_DATE) {
        this.VENDOR_DB_INSERT_DATE = VENDOR_DB_INSERT_DATE;
    }

    public Date getVENDOR_PROC_CREATE_DATE() {
        return VENDOR_PROC_CREATE_DATE;
    }

    public void setVENDOR_PROC_CREATE_DATE(Date VENDOR_PROC_CREATE_DATE) {
        this.VENDOR_PROC_CREATE_DATE = VENDOR_PROC_CREATE_DATE;
    }
}
