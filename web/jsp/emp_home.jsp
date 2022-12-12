<%--
    Document   : emp_home
    Created on : Apr 30, 2016, 8:06:44 PM
    Author     : pooja
--%>

<%@page import="java.text.NumberFormat"%>
<%@page import="in.emp.vendor.bean.VendorInputBean"%>


<%@page import="in.emp.common.ApplicationConstants"%>
<%@page import="in.emp.util.ApplicationUtils"%>
<%@page import="java.util.*"%>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>

        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Centralized Mail System</title>
        <!-- BOOTSTRAP STYLES-->
        <link href="assets/css/bootstrap.css" rel="stylesheet" />
        <!-- FONTAWESOME STYLES-->
        <link href="assets/css/font-awesome.css" rel="stylesheet" />
        <!-- CUSTOM STYLES-->
        <link href="assets/css/custom.css" rel="stylesheet" />
        <!-- GOOGLE FONTS-->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />

        <!-- to support html5-->
        <script src="<%=ApplicationConstants.JS_PATH%>html5shiv.js"></script> <!-- <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>-->
        <script src="<%=ApplicationConstants.JS_PATH%>respond.js"></script> <!--<script type='text/javascript' src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.js"></script>-->
    
        <script src="<%=ApplicationConstants.JS_PATH%>emp_home.js"></script>

    </head>
    <body onload="hide();">

        <div id="wrapper">
            <%@ include file="nav_emp_header.jsp"%>
            <!-- /. NAV TOP  -->
         
            <!-- /. NAV SIDE  -->
            <div id="page-wrapper" >

                <div id="page-inner">

                   

                    <div class="content_container">
                        <!--
                        <h1 style="text-align: center"><%//=System.getProperty("HOME_HEADING")%></h1>
                        -->
                        <br/>
                        <br/>
                        <h4 style="text-align: center">Welcome</h4>
                        <!--
                        <h3 style="text-align: center">Version <%//=System.getProperty("VERSION")%></h3>
                        -->
                        <!--h2 style="text-align: center">Early Retirement Scheme 2017 will be active from April 2017</h2>

                        <h4 style="text-align: center">You can Check your Information stored in HRMS</h4>
                        <h5 style="text-align: center">You can Check your Service related information</h5>
                        <h6 style="text-align: center">You can Now Submit your TA Claims in Employee Portal</h6-->
                        
                       
                        <center>
                            <h3> Centralized Mail System </h3>
                            <br><br>
         
                        </center>
                        
                    </div>
                    <!-- /. PAGE inner  -->
                </div>
                <!-- /. PAGE wrapper  -->
                
                <div>
                    <!-- <img src="<%=ApplicationConstants.IMAGE_PATH%>vits.jpg" width="100%" height="30%" border="0" align="absmiddle" />-->
                </div>
            </div>
            <!-- /.  wrapper  -->
        </div>

        <%@include file="nav_emp_footer.jsp" %>



        <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
        <!-- JQUERY SCRIPTS -->
        <script src="assets/js/jquery-1.10.2.js"></script>
        <!-- BOOTSTRAP SCRIPTS -->
        <script src="assets/js/bootstrap.min.js"></script>
        <!-- CUSTOM SCRIPTS -->
        <script src="assets/js/custom.js"></script>

    </body>
</html>