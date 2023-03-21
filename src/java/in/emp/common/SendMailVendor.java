/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.common;

import in.emp.vendor.VendorDelegate;
import in.emp.vendor.bean.MailStatusBean;
import in.emp.vendor.bean.VendorCommMailLogBean;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.manager.VendorManager;
import java.sql.Connection;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Pooja Jadhav
 */
public class SendMailVendor {

    private static Logger logger = Logger.getLogger(SendMailVendor.class);
    private static Connection conn = null;

    public  void SendMail(MailStatusBean mailbean) throws Exception {
       // LinkedList<VendorInputBean> MailFileList = new LinkedList();
      VendorDelegate vendorMgrObj = new VendorManager();
   StringBuilder str = new StringBuilder();
   StringBuilder htmlMsg= new StringBuilder();
   MailStatusBean unsubscribemailbean=new MailStatusBean();
   LinkedList<VendorInputBean> InvalidfileList = new LinkedList<VendorInputBean>();
        try {
              logger.log(Level.INFO, "Vendor mail Scheduler :: run() :: method called .. ");
          if(mailbean != null){
          String VendorMailId=mailbean.getEmail();
           try{
                String newline =System.getProperty("line.separator");
                str.append("<body>Dear Sir/Madam,");
                
         str.append("<br><br>");
 str.append(mailbean.getBillingDetails()+"<br>");
  str.append("<br>");
  str.append("Regards"+"<br>");
   str.append("<br>");
str.append("Note: The payment transferred to your bank account is subject to correctness of the details provided by you"+"<br>");
 str.append(" ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"+"<br>");  
 str.append( mailbean.getBilling_detail_doc()+"<br>");
 str.append(mailbean.getScheme()+"<br>"); 
 str.append(mailbean.getRefOrderNo()+"<br>");
 str.append(mailbean.getProfitCenterCode()+"<br>");
 str.append(mailbean.getProfitCenterName()+"<br>");
 str.append(mailbean.getDocumentType()+"<br>"); 
 str.append(mailbean.getDocumentNo()+"<br>"); 
 str.append(mailbean.getProcessingDate()+"<br>");
 str.append(mailbean.getVendorNo()+"<br>");
  str.append(mailbean.getDueDate()+"<br>"); 
    str.append(mailbean.getDocDate()+"<br>");
   str.append(mailbean.getMop()+"<br>");
 str.append(mailbean.getVendorName()+"<br>"); 
 str.append(mailbean.getBankAcc()+"<br>"); 
 str.append(mailbean.getIfsc()+"<br>");
 str.append(mailbean.getBankName()+"<br>");
 str.append("<br><br>");
htmlMsg.append( "<table align='left' border='1' cellpadding='0' cellspacing='0'  >"
         + "<tr>"                                                                                       
                 +  "<th>Sr. No.</th>"
                +  "<th>Spl GL Ind</th>" 
                + "<th>GL Code</th>" 
                + "<th>	GL Description</th>"  
                 + "<th>Text</th>"                                    
                + "<th>	Cost Centre</th>"  
         + "<th>Amount</th>" 
         + "<th>DR/CR</th>" 
          + "</tr>");
 StringBuffer bankdtls=mailbean.getBankDtls();
 String [] a = new String(bankdtls).split("\\r?\\n");
 for (String line : a) {
     String lines[]=line.split("[|]");
     htmlMsg.append("<tr><td>"+lines[0]+"</td><td>"+lines[1]+"</td><td>"+lines[2]+"</td><td>"+lines[3]+"</td><td>"+lines[4]+"</td><td>"+lines[5]+"</td><td>"+lines[6]+"</td><td>"+lines[7]+"</td></tr>"    );
     
        }
                htmlMsg.append("</table></p>");
   str.append( htmlMsg);
   str.append("<br><br>");

  str.append("<p style='float: left;'> Note: The Billing details in above informations is subject to data availability in SAP against the document number<br>");
  
 //   str.append("<p style='float: left;'>  To Assure mail delivery in your inbox ,Please add message@mahadiscom.in into your contact list<br>");

  str.append(" ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");  
str.append("<br><br></p>");
//str.append("<p style='clear:both ;'><center><a href='http://localhost:8082/SendMailTracking/erp?uiActionName=unsubscribe_page'>unsubscribe</a></center></p>");//uncomment for unsubscribe option
 str.append("</body>");
 // System.out.println(MailMessage);
                 
               // PdfGeneration.generatePdf(v);
// unsubscribemailbean=vendorMgrObj.checkUnsubscribeList(mailbean);//uncomment for unsubscribe option
//if(unsubscribemailbean.getCount_of_unsubscribe()==0){//uncomment for unsubscribe option

 InvalidfileList= vendorMgrObj.getInvalidMailTrackerList(VendorMailId);
 //  System.out.println(InvalidfileList.isEmpty()); 

   if(InvalidfileList.isEmpty()){
       
         VendorCommMailLogBean vendorMailLogBean = saveMailLog(str.toString(),mailbean);
        try{
          
       int success=SendMailjava.sendmail(VendorMailId,mailbean.getSubject(),str.toString());
           //  int success=SendMail.sendmail(VendorMailId,Subject,MailMessage,v.getVendorName()+".pdf","D:/"+v.getVendorName()+".pdf");
                  // PdfGeneration.generatePdf();
              if(success==1)
              {
                  vendorMgrObj.VendorMailSendTxnHelper(mailbean);
                  vendorMailLogBean.setIsMailSent("Y");
                  vendorMailLogBean=updateMailLog(vendorMailLogBean);
                 
              }
              else{
                  vendorMailLogBean.setIsMailSent("N");
                    vendorMailLogBean=updateMailLog(vendorMailLogBean);
              }
              
              }catch(Exception e){
                    vendorMailLogBean.setIsMailSent("N");
                    vendorMailLogBean.setError(e.getMessage());
                    vendorMailLogBean=updateMailLog(vendorMailLogBean);
           }
   }else{
         vendorMgrObj.VendorMailNotSendTxnHelper(mailbean);
   }
   
//}//uncomment for unsubscribe option
           }
             catch(Exception e){
                 logger.log(Level.ERROR, "SendMAIL :: run() :: Exception .. " + e.getMessage());
                 
                 
                 e.printStackTrace();
             }
             
          
        }
        }
        catch (Exception ex) {
            logger.log(Level.ERROR, "SendMAIL :: run() :: Exception .. " + ex.getMessage());
            ex.printStackTrace();
        }
}
    
      
    private  VendorCommMailLogBean saveMailLog(String mailmessage, MailStatusBean mailbean) throws Exception {
       // String sReturnPage = ApplicationConstants.UNSUBSCRIBE_MESSAGE;
       
        VendorCommMailLogBean vendorMailLogBean = new VendorCommMailLogBean();
        VendorDelegate vendorMgrObj = new VendorManager();

        //HttpSession session = request.getSession();

        try {
            logger.log(Level.INFO, "SendMailJava :: saveMailLog() :: method called :: ");
            vendorMailLogBean.setSubject(mailbean.getSubject());
            vendorMailLogBean.setHost("bulkmail.mahadiscom.in");
            vendorMailLogBean.setMessageBody(mailmessage);
            vendorMailLogBean.setRecipients(mailbean.getEmail());
            vendorMailLogBean.setIsMailSent("N");
            vendorMailLogBean.setBillNo(mailbean.getRefOrderNo());
            vendorMailLogBean.setVendorNumber(mailbean.getVendorNumber());
            vendorMailLogBean = vendorMgrObj.saveVendorCommLog(vendorMailLogBean);

           

        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: getUnsubscribe_Message() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return vendorMailLogBean;
    }
    
    
    private  VendorCommMailLogBean updateMailLog(VendorCommMailLogBean vendorMailLogBean) throws Exception {
       // String sReturnPage = ApplicationConstants.UNSUBSCRIBE_MESSAGE;
       
      
        VendorDelegate vendorMgrObj = new VendorManager();

        //HttpSession session = request.getSession();

        try {
            logger.log(Level.INFO, "SendMailJava :: saveMailLog() :: method called :: ");
          
            vendorMailLogBean = vendorMgrObj.saveVendorCommLog(vendorMailLogBean);

           

        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: getUnsubscribe_Message() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return vendorMailLogBean;
    }
    
    
    
    
    
    
    
    
    
    
    
    
         }

    

