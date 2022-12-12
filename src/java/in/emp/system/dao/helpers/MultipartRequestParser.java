/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.system.dao.helpers;

import java.io.*;

import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Public class for MultipartRequestParser reads a multipart/form-data HTTP
 * request, writes any files to disk, and stores all web
 * variables in a hashmap.
 *
 * @author
 */
public class MultipartRequestParser {

    private static final int BUFFER_SIZE = 8 * 1024; //8K buffer
    private ServletRequest request;
    private ServletInputStream in;
    private MultipartStreamReader msr;
    private String boundary;
    private String fileSeparator;
    public HashMap webVars;
    public String fileName = null;
    public String filePath = null;
    public byte[] output = null;
    public String filesize = null;
    private HashMap files = new HashMap();

    /**
     * Public constructor that initializes all instance variables.
     *
     * @param request ServletRequest
     * @throws IOException
     */
    public MultipartRequestParser(ServletRequest request) throws IOException {

        this.request = request;

        in = request.getInputStream();
        msr = new MultipartStreamReader();
        fileSeparator = System.getProperty("file.separator");

        //instantiate hashmap to store web variables
        webVars = new HashMap();
    }

    /**
     * Public API get the web variables hashmap.
     *
     * @return HashMap containing all of the web variables.
     */
    public HashMap getWebVars() {

        return webVars;

    }


    /**
     * Public API gets the boundary marker, moves past the first boundary,
     * and calls processParts() to process each part of the
     * request.
     * @return void
     * @throws Exception
     */
    public void parseOnly() throws Exception {

        String contentType = request.getContentType();

        //make sure the request is multipart/form-data
        if (!contentType.startsWith("multipart/form-data")) {

            throw new Exception("Request not multipart/form-data.");

        }

        //get the boundary marker
        boundary = getBoundary(contentType);

        findNextBoundary(); //move past first boundary

        processParts(true); //process each part of the multipart request

    }

    /**
     * Private API to processes each part of the multipart/form-data request.
     * @param parseOnly , it is boolean type .
     * @return void
     * @throws Exception
     */
    private void processParts(boolean parseOnly) throws Exception {

        //get the web variable name and filename from the request
        ContentDisposition disposition = getDisposition();

        //loop through all of the parts of the request
        while (!disposition.getName().equals("")) {


            if (disposition.getFilename().equals("")) {

                //if filename is empty, this part must be a web variable
                storeVariable(disposition.getName());

            } else {

                //filename exists so this part is a file being uploaded
                storeFile(disposition);

            }

            //get name of next web variable and filename
            disposition = getDisposition();

        }

    }

    /**
     * Private API to reads the name and value of the web variable and places it
     * in the web variable hashmap.
     *
     * @param name , that string containing the name of the variable to
     *  store
     * @return void
     * @throws IOException
     */
    private void storeVariable(String name) throws IOException {

        findEmptyLine(); //move past empty line

        webVars.put(name, msr.readLine()); //add variable to hashmap

        findNextBoundary(); //move past next boundary

    }

    /**
     * Private API to reads a file from the multipart/form-data request and writes
     * it to the file system.
     *
     * @param disposition , ContentDisposition object containing the
     *  name of the web variable and filename.
     * @return void
     * @throws IOException
     */
    private void storeFile(ContentDisposition disposition) throws IOException {

        ByteArrayOutputStream fileOut = new ByteArrayOutputStream();

        try {

            //create file object and output stream for writing file
            fileName = disposition.getFilename();

            findEmptyLine(); //move past empty line

            int bytesRead = 0;

            boolean rnAddFlag = false;

            byte[] bytes = new byte[BUFFER_SIZE];

            boolean isFirstLoop = true;

            //read entire upload file from request in 8K chunks
            while ((bytesRead = in.readLine(bytes, 0, BUFFER_SIZE)) != -1) {

                //pre-check for boundary marker
                if ((bytes[0] == '-') && (bytes[1] == '-') && (bytes[2] == '-') && (bytesRead < 500)) {

                    //this may be boundary marker, get string to make sure
                    String line = new String(bytes, 0, bytesRead, "UTF-8");

                    if (line.startsWith(boundary)) {

                        //we're at the boundary marker so we're done reading
                        break;

                    }

                }

                //we read a \r\n from the previous iteration that needs
                //to be written to the file
                if (rnAddFlag) {

                    fileOut.write('\r');
                    fileOut.write('\n');
                    rnAddFlag = false;

                }

                //since ServletInputStream adds its own \r\n to the last
                //line read, don't write it to the file until we're sure
                //that this is not the last line
                if ((bytesRead > 2) && (bytes[bytesRead - 2] == '\r') && (bytes[bytesRead - 1] == '\n')) {

                    bytesRead = bytesRead - 2;
                    rnAddFlag = true;

                }

                //if(isFirstLoop && bytesRead==2) // zero byte file
                //	break;

                if (isFirstLoop && (bytesRead == 2)) { // zero byte file

                    break;
                }

                fileOut.write(bytes, 0, bytesRead); //write data to file

                isFirstLoop = false;

            }


            output = fileOut.toByteArray();
            /*String op = new String(output,"UTF-8");
            FileOutputStream out = new FileOutputStream(new File("D:\\JNJ-CRS\\mult.doc"));
            out.write(output);*/


        } finally {

            if (fileOut != null) {
                fileOut.close();
            }


        }

    }

    /**
     * Private API to get the boundary marker from the Content-Type header.
     *
     * @param contentType , The value passed in the Content-Type as a string.
     *  header
     * @return String containing the boundary marker
     */
    private String getBoundary(String contentType) {

        String boundaryMarker = "boundary=";

        int boundaryIndex = contentType.indexOf(boundaryMarker) + boundaryMarker.length();

        //the boundary in the Content-Type lacks a leading "--"
        boundaryMarker = "--" + contentType.substring(boundaryIndex);

        return boundaryMarker;

    }

    /**
     * Private API to moves past the next boundary marker.
     * @return void
     * @throws IOException
     */
    private void findNextBoundary() throws IOException {

        String line = msr.readLine();

        //keep reading until the end of  stream or boundary marker
        while ((line != null) && !line.startsWith(boundary)) {

            line = msr.readLine();

        }

    }

    /**
     * Private API to reads the name of web variable and filename from the
     * Content-Disposition header and stores it in a new
     * ContentDisposition object.
     *
     * @return ContentDisposition object with name of web variable
     *  and filename
     * @throws Exception
     */
    private ContentDisposition getDisposition() throws Exception {

        //read first line of this part of the multipart request
        String line = msr.readLine();

        //find the Content-Disposition header
        while ((line != null) && !line.equals("") && !line.toLowerCase().startsWith("content-disposition:")) {

            line = msr.readLine();

        }

        ContentDisposition disposition = new ContentDisposition();

        if ((line == null) || !line.toLowerCase().startsWith("content-disposition:")) {

            //we're at the end of the stream or the part doesn't
            //contain a Content-Disposition header, return empty object
            return disposition;

        }

        //get name of web variable and filename
        String name = getDispositionName(line);

       // System.out.println("---------------------getDispositionName--------------------"+name);

        disposition.setName(name);

        //get filename from Content-Disposition if it exists
        String filename = getDispositionFilename(line);
       //  System.out.println("--------------------filename-----------------"+filename);

        if (filename.lastIndexOf("\\") != -1) {



            filename = filename.substring(filename.lastIndexOf("\\") + 1, filename.length());
            // System.out.println("--------------------filename-----------------"+filename);

        }

        disposition.setFilename(filename);



        return disposition;

    }

    /**
     * Private API get the web variable name from the Content-Disposition
     * header.
     *
     * @param line String containing the Content-Disposition header
     * @return String containing the name of the web variable
     */
    private String getDispositionName(String line) {

        String search = "name=\"";

        int nameIndex = line.toLowerCase().indexOf(search);

        if (nameIndex == -1) {

            return "";

        }

        nameIndex += search.length();

        String dispositionName = line.substring(nameIndex, line.indexOf("\"", nameIndex + 1));

        return dispositionName;

    }

    /**
     * Private API get the filename from the Content-Disposition header.
     *
     * @param line String containing the Content-Disposition header
     * @return String containing the filename if it exists
     */
    private String getDispositionFilename(String line) {

        String search = "filename=\"";

        int filenameIndex = line.toLowerCase().indexOf(search);

        if (filenameIndex == -1) {

            return "";

        }

        filenameIndex += search.length();

        String filename = line.substring(filenameIndex, line.indexOf("\"", filenameIndex));

        filePath = filename;

        filename = filename.substring(filename.lastIndexOf(fileSeparator) + 1);

        return filename;

    }

    /**
     * Private API get the filename from the Content-Disposition header.
     *
     * @param line String containing the Content-Disposition header
     * @return String containing the filename if it exists
     */
    private String getDispositionFilesize(String line) {

        String search = "size=\"";

        int filesizeIndex = line.toLowerCase().indexOf(search);

        if (filesizeIndex == -1) {

            return "";

        }

        filesizeIndex += search.length();

        filesize = line.substring(filesizeIndex, line.indexOf("\"", filesizeIndex));

        return filesize;

    }

    /**
     * Private API to moves past the next empty line in the input stream.
     * @return void
     * @throws IOException
     */
    private void findEmptyLine() throws IOException {

        String line = msr.readLine(); //application/msword

        //loop until end of stream or empty line
        while ((line != null) && !line.equals("")) {
            line = msr.readLine();

        }

    }

    /**
     * MultipartStreamReader is an inner class that contains a
     * single readLine() method. This method returns a single line
     * from the request input stream.
     */
    class MultipartStreamReader {

        /**
         * Reads a single line (up to 1K in length) from the input
         * stream. The line is limited to 1K in length since this
         * method is for reading HTTP headers and web variable
         * values, not files.
         *
         * @return String containing a single line from the input
         *  stream
         * @throws IOException
         */
        public String readLine() throws IOException {

            String line = "";

            byte[] bytes = new byte[1024]; //1K buffer

            int bytesRead = in.readLine(bytes, 0, BUFFER_SIZE);

            if (bytesRead == -1) {

                return null; //no line to read so return null

            } else {

                //convert byte array to a string
                line = new String(bytes, 0, bytesRead, "UTF-8");

            }

            if (line.endsWith("\r\n")) {

                //remove the trailing \r\n
                line = line.substring(0, line.length() - 2);

            }


            return line;

        }
    }

    /**
     * ContentDisposition is an inner class that encapsulates the
     * properties of the Content-Disposition HTTP header.
     * Specifically, this class stores the name of the web variable
     * and the filename.
     */
    class ContentDisposition {

        private String name = "";
        private String filename = "";

        /**
         * Returns the name of the web variable extracted from the
         * Content-Disposition header.
         *
         * @return String, name of web variable
         */
        String getName() {

            return name;

        }

        /**
         * Sets the name of the web variable.
         *
         * @param name, String containing the name of the web
         *  variable
         * @return void
         */
        void setName(String name) {

            this.name = name;

        }

        /**
         * Returns the filename extracted from the Content-
         * Disposition header.
         *
         * @return String containing the filename
         */
        String getFilename() {

            return filename;

        }

        /**
         * Sets the filename value.
         *
         * @param filename String containing the name of the file
         * @return void
         */
        void setFilename(String filename) {

            this.filename = filename;

        }
    }
}
