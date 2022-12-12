/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.bean;

import java.util.Date;

/**
 *
 * @author Pooja Jadhav
 */
public class MailStatusBean implements java.io.Serializable{
    private StringBuffer billingDetails;
        private String email;

        private String subject;
        private String scheme;
        private String refOrderNo;
        private String companyCode;
        private String profitCenterCode;
        private String profitCenterName;
        private String documentType;
        private String documentNo;
        private String processingDate;
        private String VendorNo;
        private String dueDate;
//        private String refOrderNo;
        private String docDate;
        private String mop;
        private String VendorName;
        private String bankAcc;
        private String ifsc;
        private String beneficiary;
        private StringBuffer bankDtls;
        private String BankName;
        private String billing_detail_doc;
        private String fileName;
     private String VendorNumber ;//for database insertion
        private String documentNumber ; //for database insertion
        private String Vendor_Name;//for database insertion
 private int count_of_unsubscribe;
 
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    public int getCount_of_unsubscribe() {
        return count_of_unsubscribe;
    }

    public void setCount_of_unsubscribe(int count_of_unsubscribe) {
        this.count_of_unsubscribe = count_of_unsubscribe;
    }
       
    public String getVendorNumber() {
        return VendorNumber;
    }

    public void setVendorNumber(String VendorNumber) {
        this.VendorNumber = VendorNumber;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getVendor_Name() {
        return Vendor_Name;
    }

    public void setVendor_Name(String Vendor_Name) {
        this.Vendor_Name = Vendor_Name;
    }
    
        
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
     
    public String getBilling_detail_doc() {
        return billing_detail_doc;
    }

    public void setBilling_detail_doc(String billing_detail_doc) {
        this.billing_detail_doc = billing_detail_doc;
    }
         
    public String getBankName() {
        return BankName;
    }

    public void setBankName(String BankName) {
        this.BankName = BankName;
    }

        /**
         * @return the email
         */
        public String getEmail() {
                return email;
        }

        /**
         * @param email the email to set
         */
        public void setEmail(String email) {
                this.email = email;
        }

        /**
         * @return the scheme
         */
        public String getScheme() {
                return scheme;
        }

        /**
         * @param scheme the scheme to set
         */
        public void setScheme(String scheme) {
                this.scheme = scheme;
        }

        /**
         * @return the refOrderNo
         */
        public String getRefOrderNo() {
                return refOrderNo;
        }

        /**
         * @param refOrderNo the refOrderNo to set
         */
        public void setRefOrderNo(String refOrderNo) {
                this.refOrderNo = refOrderNo;
        }

        /**
         * @return the companyCode
         */
        public String getCompanyCode() {
                return companyCode;
        }

        /**
         * @param companyCode the companyCode to set
         */
        public void setCompanyCode(String companyCode) {
                this.companyCode = companyCode;
        }

        /**
         * @return the profitCenterCode
         */
        public String getProfitCenterCode() {
                return profitCenterCode;
        }

        /**
         * @param profitCenterCode the profitCenterCode to set
         */
        public void setProfitCenterCode(String profitCenterCode) {
                this.profitCenterCode = profitCenterCode;
        }

        /**
         * @return the profitCenterName
         */
        public String getProfitCenterName() {
                return profitCenterName;
        }

        /**
         * @param profitCenterName the profitCenterName to set
         */
        public void setProfitCenterName(String profitCenterName) {
                this.profitCenterName = profitCenterName;
        }

        /**
         * @return the documentType
         */
        public String getDocumentType() {
                return documentType;
        }

        /**
         * @param documentType the documentType to set
         */
        public void setDocumentType(String documentType) {
                this.documentType = documentType;
        }

        /**
         * @return the documentNo
         */
        public String getDocumentNo() {
                return documentNo;
        }

        /**
         * @param documentNo the documentNo to set
         */
        public void setDocumentNo(String documentNo) {
                this.documentNo = documentNo;
        }

        /**
         * @return the processingDate
         */
        public String getProcessingDate() {
                return processingDate;
        }

        /**
         * @param processingDate the processingDate to set
         */
        public void setProcessingDate(String processingDate) {
                this.processingDate = processingDate;
        }

        /**
         * @return the VendorNo
         */
        public String getVendorNo() {
                return VendorNo;
        }

        /**
         * @param VendorNo the VendorNo to set
         */
        public void setVendorNo(String VendorNo) {
                this.VendorNo = VendorNo;
        }

        /**
         * @return the dueDate
         */
        public String getDueDate() {
                return dueDate;
        }

        /**
         * @param dueDate the dueDate to set
         */
        public void setDueDate(String dueDate) {
                this.dueDate = dueDate;
        }

        /**
         * @return the docDate
         */
        public String getDocDate() {
                return docDate;
        }

        /**
         * @param docDate the docDate to set
         */
        public void setDocDate(String docDate) {
                this.docDate = docDate;
        }

        /**
         * @return the mop
         */
        public String getMop() {
                return mop;
        }

        /**
         * @param mop the mop to set
         */
        public void setMop(String mop) {
                this.mop = mop;
        }

        /**
         * @return the VendorName
         */
        public String getVendorName() {
                return VendorName;
        }

        /**
         * @param VendorName the VendorName to set
         */
        public void setVendorName(String VendorName) {
                this.VendorName = VendorName;
        }

        /**
         * @return the bankAcc
         */
        public String getBankAcc() {
                return bankAcc;
        }

        /**
         * @param bankAcc the bankAcc to set
         */
        public void setBankAcc(String bankAcc) {
                this.bankAcc = bankAcc;
        }

        /**
         * @return the ifsc
         */
        public String getIfsc() {
                return ifsc;
        }

        /**
         * @param ifsc the ifsc to set
         */
        public void setIfsc(String ifsc) {
                this.ifsc = ifsc;
        }

        /**
         * @return the beneficiary
         */
        public String getBeneficiary() {
                return beneficiary;
        }

        /**
         * @param beneficiary the beneficiary to set
         */
        public void setBeneficiary(String beneficiary) {
                this.beneficiary = beneficiary;
        }

        /**
         * @return the bankDtls
         */
        public StringBuffer getBankDtls() {
                return bankDtls;
        }

        /**
         * @param bankDtls the bankDtls to set
         */
        public void setBankDtls(StringBuffer bankDtls) {
                this.bankDtls = bankDtls;
        }

        /**
         * @return the billingDetails
         */
        public StringBuffer getBillingDetails() {
                return billingDetails;
        }

        /**
         * @param billingDetails the billingDetails to set
         */
        public void setBillingDetails(StringBuffer billingDetails) {
                this.billingDetails = billingDetails;
        }
        
        
}
