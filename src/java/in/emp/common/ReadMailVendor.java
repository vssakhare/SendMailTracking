/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.common;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import in.emp.vendor.VendorDelegate;
import in.emp.vendor.bean.MailStatusBean;
import in.emp.vendor.manager.VendorManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Pooja Jadhav
 */
public class ReadMailVendor {

    private static Logger logger = Logger.getLogger(ReadMailVendor.class);
    VendorDelegate vendorMgrObj = new VendorManager();
    private static Connection conn = null;
    List list;

    public static void  readMailFile() throws ParseException, Exception {
        JSch jsch = new JSch();
        Session session = null;
        int targetSize = 900;
        try{
        session = jsch.getSession("vptserp", "ftp-vpts-erp.mahadiscom.in", 22);
        final Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        JSch.setConfig(config);
        session.setPassword("Erp#V321");
        session.connect();
        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
          String sapTomailFullFilePath = new SimpleDateFormat("yyyyMMdd'.txt'").format(cal.getTime());
        System.out.println("/data/VPTS/VENDOR_PAYMENT_MAIL_" + sapTomailFullFilePath);
        InputStream stream = sftpChannel.get("/data/VPTS/VENDOR_PAYMENT_MAIL_" + sapTomailFullFilePath);
        String SFTPPROCESSEDDIR = "/data/VPTS/VENDOR_PAYMENT_MAIL_Processed";
        String FileName = "VENDOR_PAYMENT_MAIL_" + sapTomailFullFilePath;
        String SFTPERRORDIR = "/data/VPTS/VENDOR_PAYMENT_MAIL_STATUS_Error";
        String Folder = new SimpleDateFormat("yyyy-MM").format(new Date());
        String[] folders = Folder.split("-");
         
         String textLine = null;
                List<MailStatusBean> list = new LinkedList<>();
                StringBuilder builder = new StringBuilder();
                String startPattern = "START";
                String endPattern = "END";
                int i = 0;
 try {
                BufferedReader br = new BufferedReader(new InputStreamReader(stream));
                MailStatusBean fb = null;
                boolean bankDtls = false;
                
                 StringBuffer sbr = null;
                while ((textLine = br.readLine()) != null) {
                //SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMMdd");
                     if (textLine.startsWith(startPattern)){
                       //  if(Integer.parseInt(startPattern.substring(6))%5==0){
                       //      Thread.sleep(1000*60);
                      //   }
                                        fb = new MailStatusBean();
                                        bankDtls = false;
                                        fb.setBillingDetails(new StringBuffer());
                                        fb.setBankDtls(new StringBuffer());
                                        fb.setFileName(FileName);
                                } else if (textLine.startsWith(endPattern))
                                        list.add(fb);
                              
                                else if (textLine.startsWith("Your inv.no-"))
                                        fb.getBillingDetails().append(textLine).append("\n");
                                else if (textLine.startsWith("BENIFICARY")){
                                      String [] a = textLine.split("\\|");
                                        if(a.length > 0 && a[0].equalsIgnoreCase("BENIFICARY NAME BANK NAME"))
                                                fb.setBankName(a.length > 1 ?  a[0]+":"+a[1]  : a[0]+":");
                                        bankDtls = true;
                                } 
                                   else if(textLine.startsWith("Payment Alert"))
                                      fb.setSubject(textLine);
                                else if (textLine.startsWith("Billing Details")){
                                     String [] a = textLine.split("---------------------:");
                                    fb.setBilling_detail_doc(a.length > 1 ? "Document Number:"+a[1] : "Document Number:");
                                
                                }
                                else if(textLine.contains("|")){
                                        String [] a = textLine.split("\\|");
                                        if(a.length > 0 && a[0].equalsIgnoreCase("Email of Receiver"))
                                                fb.setEmail(a.length > 1 ? a[1].replace("\"","") : "");
                                        else if(a.length > 0 && a[0].startsWith("SCHEME"))
                                                fb.setScheme(a.length > 1 ?  a[0]+":"+a[1] : a[0]+":");
                                         else if(a.length > 0 && a[0].startsWith("REF"))
                                                fb.setRefOrderNo(a.length > 1 ?  a[0]+":"+a[1] : a[0]+":");
                                          else if(a.length > 0 && a[0].startsWith("COMPANY"))
                                                fb.setCompanyCode(a.length > 1 ?  a[0]+":"+a[1] : a[0]+":");
                                          else if(a.length > 0 && a[0].equalsIgnoreCase("PROFIT CENTER NO"))
                                                fb.setProfitCenterCode(a.length > 1 ?  a[0]+":"+a[1] : a[0]+":");
                                          else if(a.length > 0 && a[0].startsWith("PROFIT CENTER NAME"))
                                                fb.setProfitCenterName(a.length > 1 ?  a[0]+":"+a[1] : a[0]+":");
                                          else if(a.length > 0 && a[0].equalsIgnoreCase("DOCUMENT TYPE"))
                                                fb.setDocumentType(a.length > 1 ?  a[0]+":"+a[1] : a[0]+":");
                                          else if(a.length > 0 && a[0].equalsIgnoreCase("DOCUMENT NO.")){
                                                fb.setDocumentNo(a.length > 1 ?  a[0]+":"+a[1] : a[0]+":");
                                           fb.setDocumentNumber(a.length > 1 ?  a[1] : " "); 
                                          }
                                          else if(a.length > 0 && a[0].startsWith("POSTING"))
                                                fb.setProcessingDate(a.length > 1 ? a[0]+":"+a[1] : a[0]+":");
                                            else if(a.length > 0 && a[0].startsWith("VENDOR")){
                                                fb.setVendorNo(a.length > 1 ?  a[0]+":"+a[1] : a[0]+":");
                                                fb.setVendorNumber(a.length > 1 ?  a[1] : " "); 
                                            }
                                         else if(a.length > 0 && a[0].startsWith("DUE DATE"))
                                                fb.setDueDate(a.length > 1 ?  a[0]+":"+a[1] : a[0]+":");
                                         else if(a.length > 0 && a[0].equalsIgnoreCase("DOCUMENT DATE"))
                                                fb.setDocDate(a.length > 1 ? a[0]+":"+a[1] : a[0]+":");
                                            else if(a.length >= 0 && a[0].startsWith("MODE"))
                                                fb.setMop(a.length > 1 ?  a[0]+":"+a[1] : a[0]+":");
                                          else if(a.length > 0 && a[0].startsWith("NAME")){
                                                fb.setVendorName(a.length > 1 ?  a[0]+":"+a[1] : a[0]+":");
                                                 fb.setVendor_Name(a.length > 1 ?  a[1] : " "); 
                                          }
                                          else if(a.length > 0 && a[0].startsWith("BANK"))
                                                fb.setBankAcc(a.length > 1 ?  a[0]+":"+a[1] : a[0]+":");
                                         else if(a.length > 0 && a[0].startsWith("IFSC"))
                                                fb.setIfsc(a.length > 1 ?  a[0]+":"+a[1] : a[0]+":");
                                       else if( Character.isDigit(textLine.charAt(0)) ||textLine.startsWith("TOTAL"))
                                                fb.getBankDtls().append(textLine).append("\n");
                                } else if(!(textLine.startsWith("Dear Sir")||textLine.startsWith("Billing Detail :")||textLine.startsWith("Regards")||textLine.startsWith("Note:")))
                                            fb.getBillingDetails().append(textLine).append("\n");
        } 
                for( MailStatusBean mailbean : list ){
            
        SendMailVendor v=new SendMailVendor();
            v.SendMail(mailbean);
                    
                }
         sftpChannel.cd(SFTPPROCESSEDDIR);
            for (String folder : folders) {
    if (folder.length() > 0 && !folder.contains(".")) {
      // This is a valid folder:
      try {
        sftpChannel.cd(folder);
        SFTPPROCESSEDDIR+="/"+folder;
      } catch (SftpException e) {
        // No such folder yet:
        sftpChannel.mkdir(folder);
        sftpChannel.cd(folder);
        SFTPPROCESSEDDIR+="/"+folder;
      }
    }
  }
            
    sftpChannel.rename("/data/VPTS/"+FileName, SFTPPROCESSEDDIR+"/"+FileName);
                 }catch (IOException io) {
                 sftpChannel.rename("/data/VPTS/"+FileName, SFTPERRORDIR+"/"+FileName); 
                System.out.println("Exception occurred during reading file from SFTP server due to " + io.getMessage());
               logger.log(Level.WARN, "ReadPOStatus :: Exception :: " + io);
               io.getMessage();

            } catch (Exception e) {
                 sftpChannel.rename("/data/VPTS/"+FileName, SFTPERRORDIR+"/"+FileName); 
                System.out.println("Exception occurred during reading file from SFTP server on line number "+ i+ " and  column number "+ e.getMessage());
               logger.log(Level.WARN, "ReadPOStatus :: Exception :: " + e);
               e.getMessage();

            }

            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            logger.log(Level.WARN, "ReadMailVendor :: Exception :: " + e);
            System.out.println("exception while reading file from ftp JSCH");
          //  e.printStackTrace();
        } catch (SftpException e) {
            logger.log(Level.WARN, "ReadMailVendor :: Exception :: " + e);
            System.out.println("exception while reading file from ftp SFTP");
           // e.printStackTrace();
        }

    }
}

