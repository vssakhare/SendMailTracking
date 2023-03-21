/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.common;

import in.emp.util.ApplicationUtils;
import in.emp.vendor.VendorDelegate;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorCommMailLogBean;
import in.emp.vendor.manager.VendorManager;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Pooja Jadhav
 */
public class SendMailjava {
 private static Logger logger = Logger.getLogger(SendMailjava.class);
 public static int sendmail(String to,String Subject,String mailmessage) {  
  
  String host="bulkmail.mahadiscom.in";  
 
  //final String from="message@mahadiscom.in";
final String from="vendorcommunication@mahadiscom.in";
  // final String user="msedcl_cfr";
//final String password="Jeft#34vhe5";
 final String bcc="centralpay.erp@gmail.com";
 //final String user="msedcl_message";
 final String user="msedcl_vcomm";
//final String password="6_ywbvldS";
final String password="PF$e49&%w7";
   //Get the session object  
   Properties props = new Properties();  
   props.put("mail.smtp.host",host); 
   props.put("mail.smtp.port", "587");
   props.put("mail.smtp.auth", "true"); 
   props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.ssl.protocols","TLSv1.2");
   Session session = Session.getDefaultInstance(props,  
    new javax.mail.Authenticator() {  
      protected PasswordAuthentication getPasswordAuthentication() {  
    return new PasswordAuthentication(user,password);  
      }  
    });  
  
   //Compose the message  
    try {  
     MimeMessage message = new MimeMessage(session);  
     message.setFrom(new InternetAddress(from));  
     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
    message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
     message.setSubject(Subject);  
     //message.setText(mailmessage);  
      message.setContent(mailmessage, "text/html");  
    //send the message  
    // Transport.send(message);  
  
     System.out.println("mail sent successfully to " + to);  
   return 1;
     } catch (MessagingException e) {
         e.printStackTrace();
        return 0;
     }  
 }  
 
 
 
 
}

 
