
package in.emp.common;


import java.util.Properties;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
public class SendMail {  
    
 public static int sendmail(String to,String Subject,String mailmessage,String filename,String filepath) {  
  
  String host="mail.mahadiscom.in";  
  final String user="erp.support@mahadiscom.in"; 
    final String password="Prakashgad@321"; 
 
   //Get the session object  
   Properties props = new Properties();  
   props.put("mail.smtp.host",host);  
   props.put("mail.smtp.auth", "true");  
     
   Session session = Session.getDefaultInstance(props,  
    new javax.mail.Authenticator() {  
      protected PasswordAuthentication getPasswordAuthentication() {  
    return new PasswordAuthentication(user,password);  
      }  
    });  
  
   //Compose the message  
    try {  
     MimeMessage message = new MimeMessage(session);  
     message.setFrom(new InternetAddress(user));  
     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
     message.setSubject(Subject); 
     

         // Create the message part
         BodyPart messageBodyPart = new MimeBodyPart();

         // Now set the actual message
         messageBodyPart.setText(mailmessage);

         // Create a multipar message
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);

        addAttachmentFile(multipart,filename,filepath);

         // Send the complete message parts
         message.setContent(multipart);

    //send the message  
     Transport.send(message);  
  
     //
   return 1;
     } catch (MessagingException e) {
         e.printStackTrace();
        return 0;
     }  
 }
 private static void addAttachmentFile(Multipart multipart,String filename,String filepath){
      // Part two is attachment
      BodyPart messageBodyPart = new MimeBodyPart();
     DataSource source = new FileDataSource(filepath);
         messageBodyPart = new MimeBodyPart();
   
         try{
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         multipart.addBodyPart(messageBodyPart);
         }
         catch(MessagingException e)
                 
                 {
                      e.printStackTrace();
                 }
     
 }
 

}  