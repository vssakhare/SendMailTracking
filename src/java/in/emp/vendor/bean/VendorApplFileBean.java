/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.bean;

import in.emp.common.FileBean;

/**
 *
 * @author Prajakta
 */
public class VendorApplFileBean extends FileBean implements java.io.Serializable {

    private String Id = "";
    private String ApplicationId = "";
  private String Po_Number ="";
    private int SrNo = 0;
    private String EmpNumber = "";
    private String Location = "";
    private String Remark = "";
 private String Option = "";
 private String Path="";

    public String getPath() {
        return Path;
    }

    public void setPath(String Path) {
        this.Path = Path;
    }
 
    public String getOption() {
        return Option;
    }

    public void setOption(String Option) {
        this.Option = Option;
    }
   
    
    public String getPo_Number() {
        return Po_Number;
    }

    public void setPo_Number(String Po_Number) {
        this.Po_Number = Po_Number;
    }
  

    public String getApplicationId() {
        return ApplicationId;
    }

    public void setApplicationId(String ApplicationId) {
        this.ApplicationId = ApplicationId;
    }

    public String getEmpNumber() {
        return EmpNumber;
    }

    public void setEmpNumber(String EmpNumber) {
        this.EmpNumber = EmpNumber;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public int getSrNo() {
        return SrNo;
    }

    public void setSrNo(int SrNo) {
        this.SrNo = SrNo;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

  
}
