/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.common;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class FileDownloadServlet extends HttpServlet {

 

    @Override

    public void doGet(HttpServletRequest request, HttpServletResponse response)

            throws ServletException, IOException {

        response.setContentType("APPLICATION/OCTET-STREAM");

        PrintWriter out = response.getWriter();

        String filename = "CirEng.pdf";

        String filepath = "/data/VPTS/Manuals";

 

        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

        FileInputStream fl = new FileInputStream(filepath + filename);
        int i;

        while ((i = fl.read()) != -1) {

            out.write(i);

        }

        fl.close();

        out.close();

    }

 
}