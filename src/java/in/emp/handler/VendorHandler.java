/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.handler;

import in.emp.arch.GenericFormHandler;
import in.emp.common.ApplicationConstants;

import in.emp.system.dao.helpers.MultipartRequestParser;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.VendorDelegate;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.manager.VendorManager;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author pooja
 */
public class VendorHandler implements GenericFormHandler {
//private static Logger logger = new Logger(RoleHandler.class.getName());

    private static String CLASS_NAME = VendorHandler.class.getName();
    private static Logger logger = Logger.getLogger(VendorHandler.class);

    /**
     * API to process a user request
     *
     * @param request object of HttpServletRequest
     * @return String the UI action name
     * @throws Exception
     */
    public String execute(HttpServletRequest request) throws Exception {
        String uiActionName = "";
        String sReturnPage = "";
        String contentType = "";
        HashMap hashObj = null;

        try {
            logger.log(Level.INFO, "VendorHandler :: execute() :: method called");

            contentType = request.getContentType();

            if ((contentType != null) && (contentType.startsWith("multipart/form-data"))) // Handle multipart request
            {
                MultipartRequestParser mrp = (MultipartRequestParser) request.getSession().getAttribute("MultipartRequestMrp");
                //request.getParameter("inpFile1");

                mrp.parseOnly(); //parse the request
                hashObj = mrp.webVars;

                if (hashObj != null && hashObj.size() > 0) {
                    if (hashObj.get(ApplicationConstants.UIACTION_NAME) != null) {

                        uiActionName = (String) hashObj.get(ApplicationConstants.UIACTION_NAME);
                    }
                    request.getSession().setAttribute("MultipartRequestMrp", mrp);
                }
            } else {
                uiActionName = request.getParameter(ApplicationConstants.UIACTION_NAME);
                String subAction = request.getParameter("subAction");
            }// Getting the parameters from request object for Role application

       // uiActionName = request.getParameter(ApplicationConstants.UIACTION_NAME); **********
            // String subAction = request.getParameter("subAction");
            //  System.out.println("TransferHandler :: execute() :: uiActionName :: " + uiActionName);
            // Getting the parameters from request object for Role application
            if (!ApplicationUtils.isBlank(uiActionName)) {
                  if (uiActionName.equals(ApplicationConstants.UNSUBSCRIBE_MESSAGE)) {
                    sReturnPage = getUnsubscribe_Message(request);
                } 
                  else  if (uiActionName.equals(ApplicationConstants.UNSUBSCRIBE_PAGE)) {
                    sReturnPage = getUnsubscribe_Page(request);
                } 
            else {
                    sReturnPage = ApplicationConstants.UIACTION_HOME_GET;
                }
            }
            logger.log(Level.INFO, "VendorHandler :: execute() :: sReturnPage :: " + sReturnPage);
        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: execute() :: Exception :: " + ex);
        }

        return sReturnPage;

    } //end execute method
private String getUnsubscribe_Message(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UNSUBSCRIBE_MESSAGE;
       
        VendorBean vendorBeanObj = new VendorBean();
        VendorDelegate vendorMgrObj = new VendorManager();

        HttpSession session = request.getSession();

        try {
            logger.log(Level.INFO, "AjaxControlServlet :: getInvoiceList() :: method called :: ");

            vendorBeanObj.setEmail_id(ApplicationUtils.getRequestParameter(request, "email"));
            vendorBeanObj.setREASON(ApplicationUtils.getRequestParameter(request, "UnsubscribeReason"));
            vendorBeanObj = vendorMgrObj.setUnsubscribeList(vendorBeanObj);

            session.setAttribute(ApplicationConstants.UNSUBSCRIBE_MESSAGE_SESSION_DATA, vendorBeanObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: getUnsubscribe_Message() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return sReturnPage;
    }/* End of Method */
  private String getUnsubscribe_Page (HttpServletRequest request) throws Exception {
    String sReturnPage = ApplicationConstants.UNSUBSCRIBE_PAGE; 
     return sReturnPage;
  }
}//class ends

