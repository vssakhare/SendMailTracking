<%-- 
    Document   : unsubscribe_message
    Created on : 7 Oct, 2020, 11:21:14 AM
    Author     : Pooja Jadhav
--%>

<%@page import="in.emp.util.ApplicationUtils"%>
<%@page import="in.emp.vendor.bean.VendorBean"%>
<%@page import="in.emp.common.ApplicationConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
     VendorBean vendorBeanObj = new VendorBean();
     if (request.getSession().getAttribute(ApplicationConstants.UNSUBSCRIBE_MESSAGE_SESSION_DATA) != null) {
        vendorBeanObj = (VendorBean) request.getSession().getAttribute(ApplicationConstants.UNSUBSCRIBE_MESSAGE_SESSION_DATA);

       

   
}
     
      String MailId = "";
      String UNSUBSCRIBE_FLAG = "";
       if (vendorBeanObj != null) {

        if (!ApplicationUtils.isBlank(vendorBeanObj.getEmail_id())) {
            MailId = vendorBeanObj.getEmail_id();
        }
          if (!ApplicationUtils.isBlank(vendorBeanObj.getUNSUBSCRIBE_FLAG())) {
            UNSUBSCRIBE_FLAG = vendorBeanObj.getUNSUBSCRIBE_FLAG();
        }
       }
%>
<!DOCTYPE html>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>UnSubscribe</title>      
        
           <!-- BOOTSTRAP STYLES-->
        <link href="assets/css/bootstrap.css" rel="stylesheet" />
        <!-- FONTAWESOME STYLES-->
        <link href="assets/css/font-awesome.css" rel="stylesheet" />
        <!-- CUSTOM STYLES-->
        <link href="assets/css/custom.css" rel="stylesheet" />
         <!-- EMP STYLES-->
        <link href="css/emp.css" rel="stylesheet" />
        <!-- GOOGLE FONTS-->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
        
    <body style="text-align:center;background-color:#efeaea">
        <div class="unsubscribeContent">
             
            
            <%if(UNSUBSCRIBE_FLAG.equals("N")){%>
            <div class="subsInnerContent">
                
           <span class="msgTxt"><strong><%=MailId%> </strong><br /> has been already unsubscribed to our mailing list(s)</span>
          
            </div> <%}else{%>
            
            <div class="subsInnerContent"><strong class="msgTitle">Done!</strong><br /><hr />
            <span class="msgTxt"><strong><%=MailId%> </strong><br /> has been successfully unsubscribed to our mailing list(s)</span>
            </div>
            <%}%>
        </div>
    </body>
</html>
