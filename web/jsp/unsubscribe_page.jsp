<%-- 
    Document   : unsubscribe_page
    Created on : 25 Apr, 2016, 4:30:08 PM
    Author     : pooja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@page import="in.emp.common.ApplicationConstants"%>
<%@page import="in.emp.util.ApplicationUtils"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>CentralizedMailSystem</title>
       
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

        <script src="<%=ApplicationConstants.JS_PATH%>html5shiv.js"></script> <!-- <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>-->
        <script src="<%=ApplicationConstants.JS_PATH%>respond.js"></script> <!--<script type='text/javascript' src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.js"></script>-->


        <!-- JQUERY SCRIPTS -->
        
        <!-- BOOTSTRAP SCRIPTS -->
        <script src="assets/js/bootstrap.min.js"></script>
        <!-- CUSTOM SCRIPTS -->
        <script src="assets/js/custom.js"></script>
        <!-- jReject CSS -->
        <link href="css/jquery.reject.css" rel="stylesheet" />
        <!-- jReject SCRIPTS -->
        <script src="<%=ApplicationConstants.JS_PATH%>jquery.reject.js"></script>
        <script src="<%=ApplicationConstants.JS_PATH%>erp_reset_password.js"></script>
     
  <script type='text/javascript' src="<%=ApplicationConstants.JS_PATH%>jquery-2.1.4.min.js"></script>
       <script type='text/javascript' src="<%=ApplicationConstants.JS_PATH%>unsubscribe_page.js"></script>
    <%-- Set language based on user's choice --%>

    </head>
  
    <body>
    <div id="wrapper">
       
            

            <!-- /. NAV TOP  -->
            <!-- /. NAV SIDE  -->
           <div id="page-inner" >

          
                <div>&nbsp;</div>

                <div align="center" class="content_container_sub" >  
                    <br>
                        <div class="panel-heading" style="margin-top: 70px;">
                            <h4 style="text-align: center; color: red; font-weight: bold; font-size: x-large;">Unsubscribe from our mailing list</h4>
                            </div>
                                        <!--Start of  content_container_sub div  -->
 
  <div>&nbsp;</div>
                 
                    

                    <form id="subscribeform" name="form1">
                    <table class="panel panel-info" align="center" border="0" cellspacing="0" cellpadding="1" id="table_content" class="login_screen" style="width: 27%"> <!-- Start of   table_content table -->
                        <tbody style="border: 1px solid #bce8f1;"> 
                            <tr>
                                <td class="Label_login" style="padding-top: 30px;">
                                 <input class="SelectinputType" type="text" text-align="right" name="txtReason"  id="txtMail" maxlength="70" placeholder="Enter Your Email ID here *" />

                                </td>  
                            </tr>
                            
                            <tr> 
                               
                              <td class="Label_login" style="padding-top: 30px;"><select id="selectUnsubscribeReason" name="selectUnsubscribeReason"  class ="SelectinputType"  >
                                                            <option value="">Please select reason</option>                                              
                                                            <option>I no longer want to receive these mails</option>
                                                            <option>Your emails are not relevant to me</option>
                                                            <option>Your emails are too frequent</option>
                                                            <option value="others">others</option>

                                                        </select>
                                  <br>  </td>
                              
                            </tr>
                           
                              
                                                <tr><td  class="Label_login" style="padding-top: 30px;">
                                                        <textarea class="SelectinputType"  style='display:none;' text-align="right" name="unsubscribereason"  id="unsubscribereason" maxlength="70" placeholder="Provide Your Reason here.... "></textarea>
                                                      <span  style='display:none' class="errorMsg">Please Enter Reason</span> 
                                                      <span  style='display:none' class="mailerrorMsg">Please Enter Mail ID</span> 
                                                    </td> </tr>
                          <tr >
                          
                            <td class="Label_login" style="padding-top: 30px;padding-bottom: 50px;"><input type="button" value=Unsubscribe name="unsubscribe" id="unsubscribe" class="btn btn-primary" onclick="Unsubcribe(document.form1.txtMail)"/></td>
                          </tr></tbody>
                    </table>    <!-- End of  table_content table  -->
                    </form>
                </div> <!-- End of  content_container_sub div  -->
            </div>  <!-- /. PAGE inner  -->
        </div> 
                        <!-- /.  wrapper  -->
                        
            
   

        <!-- SCRIPTS -AT THE BOTTOM TO REDUCE THE LOAD TIME-->

    </body>
</html>
 