/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.bean;

import java.util.Date;

/**
 *
 * @author Prajakta
 */
public class VendorInputBean implements java.io.Serializable {

    private String ApplId;
    private String vendorNumber;
    private String vendorName;
    private String PONumberHdr;
    private String PONumber;
    private String PODesc;
    private String POType;
    private String ContactNumber;
    private String Zone;
    private String Circle;
    private String Division;
    private String DaysDelayed;
    private Date POCreationDate;
    private Date ValidityStart;
    private Date ValidityEnd;
    private Date InvoiceFromDate;
    private Date InvoiceToDate;
    private Date ApplDate;
    private Date InvoiceUpdatedDate;

     private Date SormDate;
    private String VendorInvoiceNumber;
    private Date VendorInvoiceDate;
    private String InwardNumber;
    private Date InwardDate;

   
    private Date InvoiceREjDAte;
    
    private Date InvoiceApprDAte;
    private String VendorInvoiceAmount;
    private String MsedclInvoiceNumber;
    private String paidAmt;

    private String saveFlag;
    private String userType;
    private String LocationId;
    private String DispVendorNumber;
    private String DispVendorName;
    private String LocationOpt;
private String Reason = "";
private String Tax_Code;
private String Tax_Amount;
private String Office_Code;
private String Parent_Office_Code;
private String Designation;
private String smsFlag;
private String HigherAuthsmsFlag;
private String emp_Cpf;
private String emp_Name;
private String status;
private String SelectedModule;//use for escalation
private String Location;
private String Plant_desc;
private Date VendorInvoiceResubmit;
private String IsRejectedFlag;
private String IsApprovedFlag;
private String SesMigoInvNo;
private String InvResubmitCounter;
private String EmailId;
private String PendingSince;
private String SelectedModuleType;//use for selecting module to display po number or project id
private String ProjectId;
private String ProjectDesc;
private String WorkDetailDesc;
private String SubDivision;
private String ModuleSaveFlag;
private String TotalPoAmt;
private String BalPoAmt;
private Date msedclInwardDate;
private String  msedclInwardNumber;
private String sesMigoAmt;
private String invAmt;
private String invoiceStatus;
private String IT_TDS_AMOUNT;
private String GST_TDS;
private String RETENSION_AMOUNT;
private String INVOICE_TYPE;
private String SubmitAtPlant;
private String SubmitAtDesc;
private String PurchasingDesc;
private String SerialNo;
    public String getSerialNo() {
        return SerialNo;
    }

    public void setSerialNo(String SerialNo) {
        this.SerialNo = SerialNo;
    }

    public String getPurchasingDesc() {
        return PurchasingDesc;
    }

    public void setPurchasingDesc(String PurchasingDesc) {
        this.PurchasingDesc = PurchasingDesc;
    }

    public String getSubmitAtDesc() {
        return SubmitAtDesc;
    }

    public void setSubmitAtDesc(String SubmitAtDesc) {
        this.SubmitAtDesc = SubmitAtDesc;
    }

    public String getSubmitAtPlant() {
        return SubmitAtPlant;
    }

    public void setSubmitAtPlant(String SubmitAtPlant) {
        this.SubmitAtPlant = SubmitAtPlant;
    }

    public String getINVOICE_TYPE() {
        return INVOICE_TYPE;
    }

    public void setINVOICE_TYPE(String INVOICE_TYPE) {
        this.INVOICE_TYPE = INVOICE_TYPE;
    }

    public String getIT_TDS_AMOUNT() {
        return IT_TDS_AMOUNT;
    }

    public void setIT_TDS_AMOUNT(String IT_TDS_AMOUNT) {
        this.IT_TDS_AMOUNT = IT_TDS_AMOUNT;
    }

    public String getGST_TDS() {
        return GST_TDS;
    }

    public void setGST_TDS(String GST_TDS) {
        this.GST_TDS = GST_TDS;
    }

    public String getRETENSION_AMOUNT() {
        return RETENSION_AMOUNT;
    }

    public void setRETENSION_AMOUNT(String RETENSION_AMOUNT) {
        this.RETENSION_AMOUNT = RETENSION_AMOUNT;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }


    public String getSesMigoAmt() {
        return sesMigoAmt;
    }

    public void setSesMigoAmt(String sesMigoAmt) {
        this.sesMigoAmt = sesMigoAmt;
    }

    public String getInvAmt() {
        return invAmt;
    }

    public void setInvAmt(String invAmt) {
        this.invAmt = invAmt;
    }

    public Date getMsedclInwardDate() {
        return msedclInwardDate;
    }

    public void setMsedclInwardDate(Date msedclInwardDate) {
        this.msedclInwardDate = msedclInwardDate;
    }

    public String getMsedclInwardNumber() {
        return msedclInwardNumber;
    }

    public void setMsedclInwardNumber(String msedclInwardNumber) {
        this.msedclInwardNumber = msedclInwardNumber;
    }

    public String getBalPoAmt() {
        return BalPoAmt;
    }

    public void setBalPoAmt(String BalPoAmt) {
        this.BalPoAmt = BalPoAmt;
    }

    public String getTotalPoAmt() {
        return TotalPoAmt;
    }

    public void setTotalPoAmt(String TotalPoAmt) {
        this.TotalPoAmt = TotalPoAmt;
    }

    public String getModuleSaveFlag() {
        return ModuleSaveFlag;
    }

    public void setModuleSaveFlag(String ModuleSaveFlag) {
        this.ModuleSaveFlag = ModuleSaveFlag;
    }

    public String getSubDivision() {
        return SubDivision;
    }

    public void setSubDivision(String SubDivision) {
        this.SubDivision = SubDivision;
    }

    public String getWorkDetailDesc() {
        return WorkDetailDesc;
    }

    public void setWorkDetailDesc(String WorkDetailDesc) {
        this.WorkDetailDesc = WorkDetailDesc;
    }

    public String getProjectDesc() {
        return ProjectDesc;
    }

    public void setProjectDesc(String ProjectDesc) {
        this.ProjectDesc = ProjectDesc;
    }

    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String ProjectId) {
        this.ProjectId = ProjectId;
    }

    public String getSelectedModuleType() {
        return SelectedModuleType;
    }

    public void setSelectedModuleType(String SelectedModuleType) {
        this.SelectedModuleType = SelectedModuleType;
    }
    public String getPendingSince() {
        return PendingSince;
    }

    public void setPendingSince(String PendingSince) {
        this.PendingSince = PendingSince;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String EmailId) {
        this.EmailId = EmailId;
    }

    public String getInvResubmitCounter() {
        return InvResubmitCounter;
    }

    public void setInvResubmitCounter(String InvResubmitCounter) {
        this.InvResubmitCounter = InvResubmitCounter;
    }

 public Date getInvoiceREjDAte() {
        return InvoiceREjDAte;
    }

    public void setInvoiceREjDAte(Date InvoiceREjDAte) {
        this.InvoiceREjDAte = InvoiceREjDAte;
    }

    public Date getInvoiceApprDAte() {
        return InvoiceApprDAte;
    }

    public void setInvoiceApprDAte(Date InvoiceApprDAte) {
        this.InvoiceApprDAte = InvoiceApprDAte;
    }
    public String getSesMigoInvNo() {
        return SesMigoInvNo;
    }

    public void setSesMigoInvNo(String SesMigoInvNo) {
        this.SesMigoInvNo = SesMigoInvNo;
    }

    public Date getInvoiceSubmitDate() {
        return InvoiceSubmitDate;
    }

    public void setInvoiceSubmitDate(Date InvoiceSubmitDate) {
        this.InvoiceSubmitDate = InvoiceSubmitDate;
    }
private Date InvoiceSubmitDate;


    public String getIsApprovedFlag() {
        return IsApprovedFlag;
    }

    public void setIsApprovedFlag(String IsApprovedFlag) {
        this.IsApprovedFlag = IsApprovedFlag;
    }

private Date VendorInwardDate;
    public Date getVendorInwardDate() {
        return VendorInwardDate;
    }

    public void setVendorInwardDate(Date VendorInwardDate) {
        this.VendorInwardDate = VendorInwardDate;
    }

    
    public String getIsRejectedFlag() {
        return IsRejectedFlag;
    }

    public void setIsRejectedFlag(String IsRejectedFlag) {
        this.IsRejectedFlag = IsRejectedFlag;
    }

    public Date getVendorInvoiceResubmit() {
        return VendorInvoiceResubmit;
    }

    public void setVendorInvoiceResubmit(Date VendorInvoiceResubmit) {
        this.VendorInvoiceResubmit = VendorInvoiceResubmit;
    }

    public String getPlant_desc() {
        return Plant_desc;
    }

    public void setPlant_desc(String Plant_desc) {
        this.Plant_desc = Plant_desc;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getSelectedModule() {
        return SelectedModule;
    }

    public void setSelectedModule(String SelectedModule) {
        this.SelectedModule = SelectedModule;
    }


    public Date getSormDate() {
        return SormDate;
    }

    public void setSormDate(Date SormDate) {
        this.SormDate = SormDate;
    }
    public String getTechnical_sms_flag() {
        return Technical_sms_flag;
    }

    public void setTechnical_sms_flag(String Technical_sms_flag) {
        this.Technical_sms_flag = Technical_sms_flag;
    }

    public String getAccounts_sms_flag() {
        return Accounts_sms_flag;
    }

    public void setAccounts_sms_flag(String Accounts_sms_flag) {
        this.Accounts_sms_flag = Accounts_sms_flag;
    }

    public String getCashier_sms_flag() {
        return Cashier_sms_flag;
    }

    public void setCashier_sms_flag(String Cashier_sms_flag) {
        this.Cashier_sms_flag = Cashier_sms_flag;
    }
private String Technical_sms_flag;
private String Accounts_sms_flag;
private String Cashier_sms_flag;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



 public String getParent_Office_Code() {
    return Parent_Office_Code;
}
public void setParent_Office_Code(String Parent_Office_Code) {
    this.Parent_Office_Code=Parent_Office_Code;
}           
 public String getHigherAuthsmsFlag() {
    return HigherAuthsmsFlag;
}
public void setHigherAuthsmsFlag(String HigherAuthsmsFlag) {
    this.HigherAuthsmsFlag=HigherAuthsmsFlag;
}       
public String getDaysDelayed() {
    return DaysDelayed;
}
public void setDaysDelayed(String DaysDelayed) {
    this.DaysDelayed=DaysDelayed;
}
public String getempCpf() {
    return emp_Cpf;
}
public void setempCpf(String emp_Cpf) {
    this.emp_Cpf=emp_Cpf;
}
public String getempName() {
    return emp_Name;
}
public void setempName(String emp_Name) {
    this.emp_Name=emp_Name;
}

public String getsmsFlag() {
    return smsFlag;
}
public void setsmsFlag(String smsFlag) {
    this.smsFlag=smsFlag;
}
public String getContactNumber() {
    return ContactNumber;
}
public void setContactNumber(String ContactNumber) {
    this.ContactNumber=ContactNumber;
}
public String getDesignation () {
    return Designation;
}
public void setDesignation(String Designation) {
    this.Designation=Designation;
}

public String getOffice_Code () {
    return Office_Code;
}

public void setOffice_Code(String Office_Code) {
    this.Office_Code=Office_Code;
}

 public Date getVendorUpdatedDate() {
        return InvoiceUpdatedDate;
    }
 
   public void setVendorUpdatedDate(Date InvoiceUpdatedDate){
        this.InvoiceUpdatedDate=InvoiceUpdatedDate;
    }
    public String getLocationOpt() {
        return LocationOpt;
    }
  
      
      public void setTaxCode(String Tax_Code){
        this.Tax_Code=Tax_Code;
    }
      
      public void setTaxAmount(String Tax_Amount){
        this.Tax_Amount=Tax_Amount;
    }
       public String getTaxCode(){
        return Tax_Code;
    }
     public String getTaxAmount(){
        return Tax_Amount;
    }

    public void setLocationOpt(String LocationOpt) {
        this.LocationOpt = LocationOpt;
    }

    public String getDispVendorNumber() {
        return DispVendorNumber;
    }

    public void setDispVendorNumber(String DispVendorNumber) {
        this.DispVendorNumber = DispVendorNumber;
    }

    public String getDispVendorName() {
        return DispVendorName;
    }

    public void setDispVendorName(String DispVendorName) {
        this.DispVendorName = DispVendorName;
    }
    
    
    
    //    private String PaidAmt;
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getLocationId() {
        return LocationId;
    }

    public void setLocationId(String LocationId) {
        this.LocationId = LocationId;
    }
    
    
    public Date getApplDate() {
        return ApplDate;
    }

    public void setApplDate(Date ApplDate) {
        this.ApplDate = ApplDate;
    }
    
    
    public String getInwardNumber() {
        return InwardNumber;
    }

    public void setInwardNumber(String InwardNumber) {
        this.InwardNumber = InwardNumber;
    }

    public Date getInwardDate() {
        return InwardDate;
    }

    public void setInwardDate(Date InwardDate) {
        this.InwardDate = InwardDate;
    }
        
    public String getApplId() {
        return ApplId;
    }

    public void setApplId(String ApplId) {
        this.ApplId = ApplId;
    }
    
    public String getVendorNumber() {
        return vendorNumber;
    }

    public void setVendorNumber(String vendorNumber) {
        this.vendorNumber = vendorNumber;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getPONumberHdr() {
        return PONumberHdr;
    }

    public void setPONumberHdr(String PONumberHdr) {
        this.PONumberHdr = PONumberHdr;
    }

    public String getPONumber() {
        return PONumber;
    }

    public void setPONumber(String PONumber) {
        this.PONumber = PONumber;
    }

    public String getPODesc() {
        return PODesc;
    }

    public void setPODesc(String PODesc) {
        this.PODesc = PODesc;
    }

    public String getPOType() {
        return POType;
    }

    public void setPOType(String POType) {
        this.POType = POType;
    }

    public String getZone() {
        return Zone;
    }

    public void setZone(String Zone) {
        this.Zone = Zone;
    }

    public String getCircle() {
        return Circle;
    }

    public void setCircle(String Circle) {
        this.Circle = Circle;
    }

    public String getDivision() {
        return Division;
    }

    public void setDivision(String Division) {
        this.Division = Division;
    }

    public Date getPOCreationDate() {
        return POCreationDate;
    }

    public void setPOCreationDate(Date POCreationDate) {
        this.POCreationDate = POCreationDate;
    }

    public Date getValidityStart() {
        return ValidityStart;
    }

    public void setValidityStart(Date ValidityStart) {
        this.ValidityStart = ValidityStart;
    }

    public Date getValidityEnd() {
        return ValidityEnd;
    }

    public void setValidityEnd(Date ValidityEnd) {
        this.ValidityEnd = ValidityEnd;
    }

    public Date getInvoiceFromDate() {
        return InvoiceFromDate;
    }

    public void setInvoiceFromDate(Date InvoiceFromDate) {
        this.InvoiceFromDate = InvoiceFromDate;
    }

    public Date getInvoiceToDate() {
        return InvoiceToDate;
    }

    public void setInvoiceToDate(Date InvoiceToDate) {
        this.InvoiceToDate = InvoiceToDate;
    }

    public String getVendorInvoiceNumber() {
        return VendorInvoiceNumber;
    }

    public void setVendorInvoiceNumber(String VendorInvoiceNumber) {
        this.VendorInvoiceNumber = VendorInvoiceNumber;
    }

    public Date getVendorInvoiceDate() {
        return VendorInvoiceDate;
    }

    public void setVendorInvoiceDate(Date VendorInvoiceDate) {
        this.VendorInvoiceDate = VendorInvoiceDate;
    }

    public String getVendorInvoiceAmount() {
        return VendorInvoiceAmount;
    }

    public void setVendorInvoiceAmount(String VendorInvoiceAmount) {
        this.VendorInvoiceAmount = VendorInvoiceAmount;
    }

    public String getMsedclInvoiceNumber() {
        return MsedclInvoiceNumber;
    }

    public void setMsedclInvoiceNumber(String MsedclInvoiceNumber) {
        this.MsedclInvoiceNumber = MsedclInvoiceNumber;
    }

    public String getPaidAmt() {
        return paidAmt;
    }

    public void setPaidAmt(String paidAmt) {
        this.paidAmt = paidAmt;
    }

    public String getSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(String saveFlag) {
        this.saveFlag = saveFlag;
    }
    public String getReason() {
        return Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }
    
}