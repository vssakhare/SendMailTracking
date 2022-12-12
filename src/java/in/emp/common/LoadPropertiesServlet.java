package in.emp.common;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import java.io.InputStream;
import java.io.File;
import java.util.Properties;
import java.util.Iterator;
import java.util.List;

import in.emp.system.SystemDelegate;
import in.emp.system.manager.SystemManager;
import in.emp.system.beans.SystemParameterData;
import in.emp.util.ApplicationUtils;

//-- logger imports
import org.apache.log4j.PropertyConfigurator;

public class LoadPropertiesServlet extends HttpServlet {

    /**
     * API called by Servlet container. The API loads the application properties
     * file and initilizes the Listener.
     *
     * @param config Description of the Parameter
     * @throws ServletException
     */
    public void init(ServletConfig config) throws ServletException {
        try {

            //-- Load System Parameters from DB
            SystemDelegate systemDelegate = new SystemManager();
            List systemParameterList = systemDelegate.getAllSystemParameters();
            SystemParameterData systemParameterData = null;

            if (systemParameterList != null && systemParameterList.size() > 0) {
                Iterator systemParameterItr = systemParameterList.iterator();
                while (systemParameterItr.hasNext()) {
                    systemParameterData = (SystemParameterData) systemParameterItr.next();
                    //System.out.println(systemParameterData.getParameterName()+" = "+systemParameterData.getParameterValue());
                    System.setProperty(systemParameterData.getParameterName(), systemParameterData.getParameterValue());
                }
            }

            //-- Load log4J logger properties
            Properties loggerProps = new Properties();
            InputStream inputStream = (config.getServletContext()).getResourceAsStream(ApplicationConstants.PROPERTIES_PATH);
            if (!ApplicationUtils.isBlank(inputStream)) {
                loggerProps.load(inputStream);
            } else {
                System.out.println(ApplicationConstants.PROPERTIES_PATH + " file not found");
            }
            InputStream inputStreamLog = (config.getServletContext()).getResourceAsStream(ApplicationConstants.LOGGER_PATH);

            if (!ApplicationUtils.isBlank(inputStreamLog)) {
                loggerProps.load(inputStreamLog);
            } else {
                System.out.println(ApplicationConstants.LOGGER_PATH + " file not found");
            }

            if (!ApplicationUtils.isBlank(loggerProps)) {
                String logFilePath = System.getProperty("catalina.base") + "/logs";
                String enableLog = System.getProperty("ENABLE_LOGGER");
                if (ApplicationUtils.validateString(enableLog)) {
                    if (enableLog.equalsIgnoreCase("true")) {
                        if (ApplicationUtils.validateString(logFilePath)) {
                            File lFile = new File(logFilePath);
                            if (!(lFile.isDirectory())) {
                                boolean created = lFile.mkdirs();
                            }
                            System.out.println(logFilePath);
                            loggerProps.setProperty("log4j.appender.file.File", logFilePath + "/mail-logger.log");
                            PropertyConfigurator.configure(loggerProps);
                            logFilePath = ApplicationUtils.validateURL(logFilePath);
                        } else {
                            System.out.println("**** ERROR: MAIL logger is enabled but logger path is not provided. Please provide the logger path ****");
                        }
                    }
                }
            }

            System.out.println("\n************************************************");
            System.out.println("* MAIL System parameters loaded succesfully *");
            System.out.println("* MAIL Logger loaded succesfully            *");
            System.out.println("************************************************\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addProperty() {
        try {

        } catch (Exception ex) {
        }
    }
}
