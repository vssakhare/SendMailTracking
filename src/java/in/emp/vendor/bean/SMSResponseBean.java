/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.bean;

import java.util.Date;

/**
 *
 * @author Pooja Jadhav
 */
public class SMSResponseBean {
    private String MOBILE_NUMBER;
    private String TEMPLATEID;
    private String F1;
    private String F2;
    private String F3;
    private String F4;
    private String F5;
    private Date INSERT_DATE;
    private String RESPONSE_ID;
    private String F6;

    public String getMOBILE_NUMBER() {
        return MOBILE_NUMBER;
    }

    public void setMOBILE_NUMBER(String MOBILE_NUMBER) {
        this.MOBILE_NUMBER = MOBILE_NUMBER;
    }

    public String getTEMPLATEID() {
        return TEMPLATEID;
    }

    public void setTEMPLATEID(String TEMPLATEID) {
        this.TEMPLATEID = TEMPLATEID;
    }

    public String getF1() {
        return F1;
    }

    public void setF1(String F1) {
        this.F1 = F1;
    }

    public String getF2() {
        return F2;
    }

    public void setF2(String F2) {
        this.F2 = F2;
    }

    public String getF3() {
        return F3;
    }

    public void setF3(String F3) {
        this.F3 = F3;
    }

    public String getF4() {
        return F4;
    }

    public void setF4(String F4) {
        this.F4 = F4;
    }

    public String getF5() {
        return F5;
    }

    public void setF5(String F5) {
        this.F5 = F5;
    }

    public Date getINSERT_DATE() {
        return INSERT_DATE;
    }

    public void setINSERT_DATE(Date INSERT_DATE) {
        this.INSERT_DATE = INSERT_DATE;
    }

    public String getRESPONSE_ID() {
        return RESPONSE_ID;
    }

    public void setRESPONSE_ID(String RESPONSE_ID) {
        this.RESPONSE_ID = RESPONSE_ID;
    }

    public String getF6() {
        return F6;
    }

    public void setF6(String F6) {
        this.F6 = F6;
    }
}
