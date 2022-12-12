/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.system.dao;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class UserAuditDTO {

        private String statusCD;
        private String createdBy;
        private Date createdDate;
        private String updatedBy;
        private Date updatedDate;
        private long totalRecords;
        private int minValue;
        private int maxValue;
        private String sortColTableName;
        private String sortColumnName;
        private String sortOrder;
        private String lastRowValue;
        private String lastValueDataType;

        /**
         * @return the statusCD
         */
        public String getStatusCD() {
                return statusCD;
        }

        /**
         * @param statusCD the statusCD to set
         */
        public void setStatusCD(String statusCD) {
                this.statusCD = statusCD;
        }

        /**
         * @return the createdBy
         */
        public String getCreatedBy() {
                return createdBy;
        }

        /**
         * @param createdBy the createdBy to set
         */
        public void setCreatedBy(String createdBy) {
                this.createdBy = createdBy;
        }

        /**
         * @return the createdDate
         */
        public Date getCreatedDate() {
                return createdDate;
        }

        /**
         * @param createdDate the createdDate to set
         */
        public void setCreatedDate(Date createdDate) {
                this.createdDate = createdDate;
        }

        /**
         * @return the updatedBy
         */
        public String getUpdatedBy() {
                return updatedBy;
        }

        /**
         * @param updatedBy the updatedBy to set
         */
        public void setUpdatedBy(String updatedBy) {
                this.updatedBy = updatedBy;
        }

        /**
         * @return the updatedDate
         */
        public Date getUpdatedDate() {
                return updatedDate;
        }

        /**
         * @param updatedDate the updatedDate to set
         */
        public void setUpdatedDate(Date updatedDate) {
                this.updatedDate = updatedDate;
        }

        /**
         * @return the totalRecords
         */
        public long getTotalRecords() {
                return totalRecords;
        }

        /**
         * @param totalRecords the totalRecords to set
         */
        public void setTotalRecords(long totalRecords) {
                this.totalRecords = totalRecords;
        }

        /**
         * @return the minValue
         */
        public int getMin() {
                return minValue;
        }

        /**
         * @param minValue the minValue to set
         */
        public void setMin(int minValue) {
                this.minValue = minValue;
        }

        /**
         * @return the maxValue
         */
        public int getMax() {
                return maxValue;
        }

        /**
         * @param maxValue the maxValue to set
         */
        public void setMax(int maxValue) {
                this.maxValue = maxValue;
        }

        /**
         * @return the sortColTableName
         */
        public String getSortColTableName() {
                return sortColTableName;
        }

        /**
         * @param sortColTableName the sortColTableName to set
         */
        public void setSortColTableName(String sortColTableName) {
                this.sortColTableName = sortColTableName;
        }

        /**
         * @return the sortColumnName
         */
        public String getSortColumnName() {
                return sortColumnName;
        }

        /**
         * @param sortColumnName the sortColumnName to set
         */
        public void setSortColumnName(String sortColumnName) {
                this.sortColumnName = sortColumnName;
        }

        /**
         * @return the sortOrder
         */
        public String getSortOrder() {
                return sortOrder;
        }

        /**
         * @param sortOrder the sortOrder to set
         */
        public void setSortOrder(String sortOrder) {
                this.sortOrder = sortOrder;
        }

        /**
         * @return the lastRowValue
         */
        public String getLastRowValue() {
                return lastRowValue;
        }

        /**
         * @param lastRowValue the lastRowValue to set
         */
        public void setLastRowValue(String lastRowValue) {
                this.lastRowValue = lastRowValue;
        }

        /**
         * @return the lastValueDataType
         */
        public String getLastValueDataType() {
                return lastValueDataType;
        }

        /**
         * @param lastValueDataType the lastValueDataType to set
         */
        public void setLastValueDataType(String lastValueDataType) {
                this.lastValueDataType = lastValueDataType;
        }
}
