/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.common;

/**
 *
 * @author Pooja Jadhav
 */
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoadMailSchedulerServlet extends HttpServlet
{
    public void init(ServletConfig config) throws ServletException
	{
		try
		{ //Comment this line before deploy.
                   ReadMailVendor po=new ReadMailVendor();//load po file
                   po.readMailFile();
                  //unComment this line before deploy. 
                VendorMailScheduler.startScheduler();
                //PdfGeneration.generatePdf();
		}
		catch(Exception ex)
		{
                    ex.printStackTrace();
		}
	}

	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException
	{
		try
		{
			//Scheduler.resetScheduler();
		}
		catch(Exception ex)
		{
		}
	}

	public void destroy()
	{
		try
		{
                       VendorMailScheduler.stopScheduler();
		}
		catch(Exception ex)
		{
		}
	}
}

