/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.bean;
import java.util.LinkedList;
import java.util.Date;
/**
 *
 * @author Pooja Jadhav
 */
public class PoStatusBean  implements java.io.Serializable{
    private String   ZONE   ;            
private String 	CIRCLE    ;          
private String	DIVISION  ;          
private String	PLANT     ;          
private String	PO_NUMBER  ;         
private String	PO_DESC     ;        
private String	PO_TYPE_CODE  ;      
private String	PO_TYPE       ;      
 private Date	PO_CREATION_DATE ;   
 private Date	PO_DOC_DATE      ;   
 private Date	VALIDITY_START   ;   
 private Date	VALIDITY_END    ;    
private String	TOTAL_PO_AMT ;       
private String	VENDOR_NUMBER;       
private String	VENDOR_NAME;         
private String	SES_NO    ;          
private String	SES_DESC   ;         
 private Date	 SES_CREATE_DATE ;   
 private Date	 SES_DOC_DATE ;      
private String	SES_AMOUNT ;         
private String	RELEASE_STATUS;      
private String	MIGO_DOC   ;         
private String	DOC_YEAR ;           
private String	MIGO_AMT ;           
private String	PROFIT_CENTER  ;     
private String	BAL_AMOUNT;          
private String	VENDOR_INV_NO ;      
 private Date	INVOICE_DATE;        
private String	MSEDCL_INV_NO ;      
private String	INV_AMT  ;           
private String	CL_DOC_NO ;          
private String	PAID_AMT ;          
private String	PO_SHORT_CLOSE ;     
private Date	CL_DOC_DT ;          
private Date	MIGO_DT;             
private Date	INV_DT;              
private String	TAX_CODE  ;          
private String	TAX_AMOUNT ;         
private String	IT_TDS_AMOUNT;       
private String	GST_TDS  ;           
private String	RETENSION_AMOUNT  ;  
private String	PROJECT_CODE    ;    
private String	PROJECT_SCHEME  ;    
private Date	CREATE_DATE  ;  
private String MSEDCL_PSINV_NO;
private String SES_VEN_INV_NO;
private String MIGO_VEN_INV_NO;
private String MSEDCL_INV_NO_FLAG;
private String SES_FLAG;
private String MIGO_FLAG;
private String MSEDCL_INV_NO_REVERSAL;
private String Purchasing_group;
    public String getPurchasing_group() {
        return Purchasing_group;
    }

    public void setPurchasing_group(String Purchasing_group) {
        this.Purchasing_group = Purchasing_group;
    }

    public String getMSEDCL_INV_NO_REVERSAL() {
        return MSEDCL_INV_NO_REVERSAL;
    }

    public void setMSEDCL_INV_NO_REVERSAL(String MSEDCL_INV_NO_REVERSAL) {
        this.MSEDCL_INV_NO_REVERSAL = MSEDCL_INV_NO_REVERSAL;
    }

    public String getMSEDCL_INV_NO_FLAG() {
        return MSEDCL_INV_NO_FLAG;
    }

    public void setMSEDCL_INV_NO_FLAG(String MSEDCL_INV_NO_FLAG) {
        this.MSEDCL_INV_NO_FLAG = MSEDCL_INV_NO_FLAG;
    }

    public String getSES_FLAG() {
        return SES_FLAG;
    }

    public void setSES_FLAG(String SES_FLAG) {
        this.SES_FLAG = SES_FLAG;
    }

    public String getMIGO_FLAG() {
        return MIGO_FLAG;
    }

    public void setMIGO_FLAG(String MIGO_FLAG) {
        this.MIGO_FLAG = MIGO_FLAG;
    }

    public String getMIGO_VEN_INV_NO() {
        return MIGO_VEN_INV_NO;
    }

    public void setMIGO_VEN_INV_NO(String MIGO_VEN_INV_NO) {
        this.MIGO_VEN_INV_NO = MIGO_VEN_INV_NO;
    }


    public String getSES_VEN_INV_NO() {
        return SES_VEN_INV_NO;
    }

    public void setSES_VEN_INV_NO(String SES_VEN_INV_NO) {
        this.SES_VEN_INV_NO = SES_VEN_INV_NO;
    }


    public String getMSEDCL_PSINV_NO() {
        return MSEDCL_PSINV_NO;
    }

    public void setMSEDCL_PSINV_NO(String MSEDCL_PSINV_NO) {
        this.MSEDCL_PSINV_NO = MSEDCL_PSINV_NO;
    }



    public String getZONE() {
        return ZONE;
    }

    public void setZONE(String ZONE) {
        this.ZONE = ZONE;
    }

    public String getCIRCLE() {
        return CIRCLE;
    }

    public void setCIRCLE(String CIRCLE) {
        this.CIRCLE = CIRCLE;
    }

    public String getDIVISION() {
        return DIVISION;
    }

    public void setDIVISION(String DIVISION) {
        this.DIVISION = DIVISION;
    }

    public String getPLANT() {
        return PLANT;
    }

    public void setPLANT(String PLANT) {
        this.PLANT = PLANT;
    }

    public String getPO_NUMBER() {
        return PO_NUMBER;
    }

    public void setPO_NUMBER(String PO_NUMBER) {
        this.PO_NUMBER = PO_NUMBER;
    }

    public String getPO_DESC() {
        return PO_DESC;
    }

    public void setPO_DESC(String PO_DESC) {
        this.PO_DESC = PO_DESC;
    }

    public String getPO_TYPE_CODE() {
        return PO_TYPE_CODE;
    }

    public void setPO_TYPE_CODE(String PO_TYPE_CODE) {
        this.PO_TYPE_CODE = PO_TYPE_CODE;
    }

    public String getPO_TYPE() {
        return PO_TYPE;
    }

    public void setPO_TYPE(String PO_TYPE) {
        this.PO_TYPE = PO_TYPE;
    }

    public Date getPO_CREATION_DATE() {
        return PO_CREATION_DATE;
    }

    public void setPO_CREATION_DATE(Date PO_CREATION_DATE) {
        this.PO_CREATION_DATE = PO_CREATION_DATE;
    }

    public Date getPO_DOC_DATE() {
        return PO_DOC_DATE;
    }

    public void setPO_DOC_DATE(Date PO_DOC_DATE) {
        this.PO_DOC_DATE = PO_DOC_DATE;
    }

    public Date getVALIDITY_START() {
        return VALIDITY_START;
    }

    public void setVALIDITY_START(Date VALIDITY_START) {
        this.VALIDITY_START = VALIDITY_START;
    }

    public Date getVALIDITY_END() {
        return VALIDITY_END;
    }

    public void setVALIDITY_END(Date VALIDITY_END) {
        this.VALIDITY_END = VALIDITY_END;
    }

    public String getTOTAL_PO_AMT() {
        return TOTAL_PO_AMT;
    }

    public void setTOTAL_PO_AMT(String TOTAL_PO_AMT) {
        this.TOTAL_PO_AMT = TOTAL_PO_AMT;
    }

    public String getVENDOR_NUMBER() {
        return VENDOR_NUMBER;
    }

    public void setVENDOR_NUMBER(String VENDOR_NUMBER) {
        this.VENDOR_NUMBER = VENDOR_NUMBER;
    }

    public String getVENDOR_NAME() {
        return VENDOR_NAME;
    }

    public void setVENDOR_NAME(String VENDOR_NAME) {
        this.VENDOR_NAME = VENDOR_NAME;
    }

    public String getSES_NO() {
        return SES_NO;
    }

    public void setSES_NO(String SES_NO) {
        this.SES_NO = SES_NO;
    }

    public String getSES_DESC() {
        return SES_DESC;
    }

    public void setSES_DESC(String SES_DESC) {
        this.SES_DESC = SES_DESC;
    }

    public Date getSES_CREATE_DATE() {
        return SES_CREATE_DATE;
    }

    public void setSES_CREATE_DATE(Date SES_CREATE_DATE) {
        this.SES_CREATE_DATE = SES_CREATE_DATE;
    }

    public Date getSES_DOC_DATE() {
        return SES_DOC_DATE;
    }

    public void setSES_DOC_DATE(Date SES_DOC_DATE) {
        this.SES_DOC_DATE = SES_DOC_DATE;
    }

    public String getSES_AMOUNT() {
        return SES_AMOUNT;
    }

    public void setSES_AMOUNT(String SES_AMOUNT) {
        this.SES_AMOUNT = SES_AMOUNT;
    }

    public String getRELEASE_STATUS() {
        return RELEASE_STATUS;
    }

    public void setRELEASE_STATUS(String RELEASE_STATUS) {
        this.RELEASE_STATUS = RELEASE_STATUS;
    }

    public String getMIGO_DOC() {
        return MIGO_DOC;
    }

    public void setMIGO_DOC(String MIGO_DOC) {
        this.MIGO_DOC = MIGO_DOC;
    }

    public String getDOC_YEAR() {
        return DOC_YEAR;
    }

    public void setDOC_YEAR(String DOC_YEAR) {
        this.DOC_YEAR = DOC_YEAR;
    }

    public String getMIGO_AMT() {
        return MIGO_AMT;
    }

    public void setMIGO_AMT(String MIGO_AMT) {
        this.MIGO_AMT = MIGO_AMT;
    }

    public String getPROFIT_CENTER() {
        return PROFIT_CENTER;
    }

    public void setPROFIT_CENTER(String PROFIT_CENTER) {
        this.PROFIT_CENTER = PROFIT_CENTER;
    }

    public String getBAL_AMOUNT() {
        return BAL_AMOUNT;
    }

    public void setBAL_AMOUNT(String BAL_AMOUNT) {
        this.BAL_AMOUNT = BAL_AMOUNT;
    }

    public String getVENDOR_INV_NO() {
        return VENDOR_INV_NO;
    }

    public void setVENDOR_INV_NO(String VENDOR_INV_NO) {
        this.VENDOR_INV_NO = VENDOR_INV_NO;
    }

    public Date getINVOICE_DATE() {
        return INVOICE_DATE;
    }

    public void setINVOICE_DATE(Date INVOICE_DATE) {
        this.INVOICE_DATE = INVOICE_DATE;
    }

    public String getMSEDCL_INV_NO() {
        return MSEDCL_INV_NO;
    }

    public void setMSEDCL_INV_NO(String MSEDCL_INV_NO) {
        this.MSEDCL_INV_NO = MSEDCL_INV_NO;
    }

    public String getINV_AMT() {
        return INV_AMT;
    }

    public void setINV_AMT(String INV_AMT) {
        this.INV_AMT = INV_AMT;
    }

    public String getCL_DOC_NO() {
        return CL_DOC_NO;
    }

    public void setCL_DOC_NO(String CL_DOC_NO) {
        this.CL_DOC_NO = CL_DOC_NO;
    }

    public String getPAID_AMT() {
        return PAID_AMT;
    }

    public void setPAID_AMT(String PAID_AMT) {
        this.PAID_AMT = PAID_AMT;
    }

    public String getPO_SHORT_CLOSE() {
        return PO_SHORT_CLOSE;
    }

    public void setPO_SHORT_CLOSE(String PO_SHORT_CLOSE) {
        this.PO_SHORT_CLOSE = PO_SHORT_CLOSE;
    }

    public Date getCL_DOC_DT() {
        return CL_DOC_DT;
    }

    public void setCL_DOC_DT(Date CL_DOC_DT) {
        this.CL_DOC_DT = CL_DOC_DT;
    }

    public Date getMIGO_DT() {
        return MIGO_DT;
    }

    public void setMIGO_DT(Date MIGO_DT) {
        this.MIGO_DT = MIGO_DT;
    }

    public Date getINV_DT() {
        return INV_DT;
    }

    public void setINV_DT(Date INV_DT) {
        this.INV_DT = INV_DT;
    }

    public String getTAX_CODE() {
        return TAX_CODE;
    }

    public void setTAX_CODE(String TAX_CODE) {
        this.TAX_CODE = TAX_CODE;
    }

    public String getTAX_AMOUNT() {
        return TAX_AMOUNT;
    }

    public void setTAX_AMOUNT(String TAX_AMOUNT) {
        this.TAX_AMOUNT = TAX_AMOUNT;
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

    public String getPROJECT_CODE() {
        return PROJECT_CODE;
    }

    public void setPROJECT_CODE(String PROJECT_CODE) {
        this.PROJECT_CODE = PROJECT_CODE;
    }

    public String getPROJECT_SCHEME() {
        return PROJECT_SCHEME;
    }

    public void setPROJECT_SCHEME(String PROJECT_SCHEME) {
        this.PROJECT_SCHEME = PROJECT_SCHEME;
    }

    public Date getCREATE_DATE() {
        return CREATE_DATE;
    }

    public void setCREATE_DATE(Date CREATE_DATE) {
        this.CREATE_DATE = CREATE_DATE;
    }
  
}