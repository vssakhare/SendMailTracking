package in.emp.arch;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.io.InputStream;
import java.util.Properties;
import java.util.Enumeration;

import in.emp.common.ApplicationConstants;
import in.emp.util.ApplicationUtils;
import in.emp.common.ActionClass;
import in.emp.common.ConfigBean;

import in.emp.system.dao.helpers.MultipartRequestParser;
import java.util.Iterator;
import java.util.LinkedList;
import javax.servlet.ServletContext;

public class ApplicationControlServlet extends HttpServlet {

    private static HashMap hmActionClassMap;
    private static HashMap resourceBundleMap = new HashMap();

    @Override
    public void init(ServletConfig config) throws ServletException {
        InputStream inputStream = null;
        try {
            super.init(config);
            String path = ApplicationConstants.CONFIG_XML_PATH;
            inputStream = (config.getServletContext()).getResourceAsStream(path);
            hmActionClassMap = ActionClass.buildActionClassMap(inputStream);
            Properties rsProperties = new Properties();
            String rsPath = ApplicationConstants.RESOURCEBUNDLE_PATH;
            InputStream rsInputStream = (config.getServletContext()).getResourceAsStream(rsPath);
            rsProperties.load(rsInputStream);
            for (Enumeration e = rsProperties.propertyNames(); e.hasMoreElements();) {
                String propertyName = (String) e.nextElement();
                String propertyValue = rsProperties.getProperty(propertyName);
                resourceBundleMap.put(propertyName, propertyValue);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (!ApplicationUtils.isBlank(inputStream)) {
                    inputStream.close();
                    inputStream = null;
                }
            } catch (Exception ioEx) {
            }
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param HttpServletRequest request, servlet request
     * @param HttpServletResponse response, servlet response
     * @return void
     * @throws ServletException , IOException
     */
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = "";
        String sResultPage = "";
        String uiActionName = "";
        String successPage = "";
        HttpSession session = request.getSession(true);
        ConfigBean configObj = null;
        String name = "";
        String value = "";
        String department = "";
        String designation = "";
        String uiSubActionName = "";
        String contentType = "";
        String office_loc_id = "";
        HashMap hashObj = null;
       
            //getCircleList(request);
            //getBuList(request);
//            loadServletContextData(request);
            String userPath = request.getServletPath();
            
             if (userPath.equals("/chooseLanguage")) {

    // get language choice
    String language = request.getParameter("language");

    // place in request scope
    request.setAttribute("language", language);
 
    // forward request to welcome page
    try {
        request.getRequestDispatcher("/jsp/emp_login_page.jsp").forward(request, response);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return;
}
             else{
              try {
            if (!ApplicationUtils.isBlank(resourceBundleMap) && !resourceBundleMap.isEmpty()) {
                if (request.getSession().getAttribute(ApplicationConstants.SESSION_APP_RESOURCE) == null) {

                    request.getSession().setAttribute(ApplicationConstants.SESSION_APP_RESOURCE, resourceBundleMap);
                }
            }
           
            if (!ApplicationUtils.isBlank(request.getParameter(ApplicationConstants.UIACTION_NAME))) {
                uiActionName = request.getParameter(ApplicationConstants.UIACTION_NAME);
            } else {
                //uiActionName = ApplicationConstants.UIACTION_GET_LOGIN;
                uiActionName = ApplicationConstants.UIACTION_HOME_GET;
            }

            if (!ApplicationUtils.isBlank(request.getParameter("subAction"))) {
                uiSubActionName = request.getParameter("subAction");
            }

            contentType = request.getContentType();
            if ((!ApplicationUtils.isBlank(contentType)) && (contentType.startsWith("multipart/form-data"))) // Handle multipart request
            {
               MultipartRequestParser mrp = new MultipartRequestParser(request);
                mrp.parseOnly(); //parse the request
                hashObj = mrp.webVars;
                if (!ApplicationUtils.isBlank(hashObj) && !hashObj.isEmpty()) {
                    if (!ApplicationUtils.isBlank(hashObj.get(ApplicationConstants.UIACTION_NAME))) {
                        uiActionName = (String) hashObj.get(ApplicationConstants.UIACTION_NAME);
                    }
                    request.getSession().setAttribute("MultipartRequestMrp", mrp);
                }
            }
            request.setAttribute(ApplicationConstants.UIACTION_NAME, uiActionName);
            if (!ApplicationUtils.isBlank(uiActionName) && !uiActionName.equals(ApplicationConstants.UIACTION_LOGIN_GET)) {
              //  uiActionName = checkUserAccess(request);
            }
            System.out.println("uiActionName::" + uiActionName);

            if (!ApplicationUtils.isBlank(uiActionName) && !uiActionName.isEmpty() && !uiActionName.equals(ApplicationConstants.UIACTION_ENTITLED_ERROR) && !uiActionName.equals(ApplicationConstants.UIACTION_GET_LOGIN)) {
                configObj = (ConfigBean) hmActionClassMap.get(uiActionName);
                if (!ApplicationUtils.isBlank(configObj)) {
                    command = configObj.getHandlerClassName();
                    Class cls = Class.forName(command);
                    GenericFormHandler formHandlerObj = (GenericFormHandler) cls.newInstance();
                    sResultPage = formHandlerObj.execute(request);
                    if (!ApplicationUtils.isBlank(sResultPage) && !sResultPage.isEmpty()) {
                        uiActionName = sResultPage;
                    }
                } else {
                    uiActionName = ApplicationConstants.UIACTION_ERROR;
                }
            }
           // commented by prajakta as on 18-11-2017
           // if (ApplicationUtils.isBlank(session.getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
           //     uiActionName = ApplicationConstants.UIACTION_REDIRECT_LINK;
           // }
            configObj = (ConfigBean) hmActionClassMap.get(uiActionName);
            if (!ApplicationUtils.isBlank(configObj)) {
                uiActionName = configObj.getUiSuccessPage();
            } else {
                uiActionName = ApplicationConstants.UIACTION_REDIRECT_LINK;
            }
        } catch (Exception ex) {
            if (!ApplicationUtils.isBlank(command)) {
                configObj = (ConfigBean) hmActionClassMap.get(uiActionName);
                if (!ApplicationUtils.isBlank(configObj)) {
                    uiActionName = configObj.getUiErrorPage();
                } else {
                    uiActionName = ApplicationConstants.UIACTION_GET_LOGIN;
                }
            }
            ex.printStackTrace();
        } finally {
            RequestDispatcher rdObj = request.getRequestDispatcher(uiActionName);
            rdObj.forward(request, response);
        }}
    }

   

    @Override
    public void destroy() {
        hmActionClassMap = null;
        resourceBundleMap = null;
    }

}
