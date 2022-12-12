/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.bean;

import java.util.List;

/**
 *
 * @author Pooja Jadhav
 */
public class SmsDTO {
    
    private List<String> LstParams = null;
    String userName =   null;
        String userPass =   null;
        String smsId    =   null;
        String smsUrl   =   null;
        String smsTemplateId   =   null;
 
  String MobileNumber = null;
      
    public List<String> getLstParams(){
        return LstParams;
    }
    public void setLstParams(List<String> lstParams){
        this.LstParams=lstParams;
    }
    
    public void setUser(String  userName){
        this.userName=userName;
    }
    public void setPasswd(String userPass){
        this.userPass=userPass;
    }
    public void setSid(String smsId){
        this.smsId=smsId;
    }
    public void setUrl(String smsUrl){
        this.smsUrl=smsUrl;
    }
     public void setTemplateId(String smsTemplateId){
        this.smsTemplateId=smsTemplateId;
    }
    public void  setMobileNumber(String MobileNumber )
    {
        this.MobileNumber=MobileNumber;
    }
      public String getUser(){
        return userName;
    }
       public String getPasswd(){
        return userPass;
    }
        public String getSid(){
        return smsId;
    }
         public String getUrl(){
        return smsUrl;
    }
          public String getTemplateId(){
        return smsTemplateId;
    }

   public String getMobileNumber(){
       return MobileNumber;
   }
   
   
}
