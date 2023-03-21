/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.bean;

import java.util.Date;

/**
 *
 * @author MSEDCL
 */
public class VendorCommMailLogBean {
    
    
private Integer commLogId;
private Date created;
private Date updated;
private String error;
private String event;
private String recipients;
private String subject	;
private String type;
private String host;
private String  messageBody;
private String isMailSent;
private String billNo;
private String vendorNumber;

    public String getVendorNumber() {
        return vendorNumber;
    }

    public void setVendorNumber(String vendorNumber) {
        this.vendorNumber = vendorNumber;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public Integer getCommLogId() {
        return commLogId;
    }

   
    public void setCommLogId(Integer commLogId) {
        this.commLogId = commLogId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

   

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
    
     public String getIsMailSent() {
        return isMailSent;
    }

    public void setIsMailSent(String isMailSent) {
        this.isMailSent = isMailSent;
    }


}
