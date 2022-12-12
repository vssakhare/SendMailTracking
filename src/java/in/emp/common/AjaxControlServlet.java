package in.emp.common;

// java imports--



import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;




//import java.util.Date;





/* public Class AjaxServlet extends Http Servlet to validate the login information of the user */
public class AjaxControlServlet extends HttpServlet {
    //private static Logger logger = new Logger(AjaxControlServlet.class.getName());

    private static String CLASS_NAME = AjaxControlServlet.class.getName();
    private static Logger logger = Logger.getLogger(AjaxControlServlet.class);
    private static String url;
   
     

    // Initializing the servlet --
    public void init(ServletConfig config) throws ServletException {
        String autoScheduleOnLoad = "N";
        try {
            logger.log(Level.INFO, "AjaxControlServlet :: init() :: method called");
            if (System.getProperty("AUTO_SCHEDULE_ON_LOAD") != null) {
                autoScheduleOnLoad = System.getProperty("AUTO_SCHEDULE_ON_LOAD");
            }

            if (autoScheduleOnLoad.equals("Y")) {
                //ApplicationUtils.autoScheduleAll();
            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, "AjaxControlServlet :: init() :: Exception :: " + ex);
        }
    }

    /* public doGet method that calls ececute method
     @param HttpServletRequest and HttpServletResponse
     @throws IOException and Servlet Exception*/
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            execute(request, response);
        } catch (Exception e) {
            logger.log(Level.ERROR, "AjaxControlServlet :: execute() :: Exception :: " + e);
        }
    } //-- End of method

    /* public doPostt method that calls doGet method
     @param HttpServletRequest and HttpServletResponse
     @throws IOException and Servlet Exception*/
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            doGet(request, response);
        } catch (Exception e) {
            logger.log(Level.ERROR, "AjaxControlServlet :: doPost() :: Exception :: " + e);
        }

    } // -- End of method

    /**
     * API to process a user request
     *
     * @param request object of HttpServletRequest and HttpServletResponse
     * @return String the UI action name
     * @throws Exception
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uiActionName = "";
        String responseString = "";
        
        try {
            

            //logger.log(Level.INFO, "AjaxControlServlet :: execute() :: responseString == "+responseString);
        } catch (Exception e) {
            logger.log(Level.ERROR, "AjaxControlServlet :: execute() :: Exception :: " + e);
            responseString = ApplicationConstants.SYS_MSG_INVALID_USER;
        } finally {
            try {
                response.setContentType("text/xml");
                response.setHeader("Cache", "no-cache");
                System.out.println("response");
                response.getWriter().write(responseString);
            } catch (Exception ex) {
                logger.log(Level.ERROR, "AjaxControlServlet :: finally :: Exception :: " + ex);
            }
        }
        return responseString;

    }//--End of method
    /* public validateUser method that validates user info
     @param HttpServletRequest
     @returns String
     @throws Exception*/
   
}