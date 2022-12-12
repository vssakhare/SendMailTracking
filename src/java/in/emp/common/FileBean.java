/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.common;

/**
 *
 * @author Prajakta
 */
public class FileBean implements java.io.Serializable {

    private String FileName = "";
    private String FileType = "";
    private byte[] FileContents;
    private String FilePath = "";
private String path = "";
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
     

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String FilePath) {
        this.FilePath = FilePath;
    }
    

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String FileName) {
        this.FileName = FileName;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String FileType) {
        this.FileType = FileType;
    }

    public byte[] getFileContents() {
        return FileContents;
    }

    public void setFileContents(byte[] FileContents) {
        this.FileContents = FileContents;
    }
}
