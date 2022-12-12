/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.system.dao;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class MasterDTO extends UserAuditDTO implements Serializable {
   private long id;
   private String code;
   private String name;
   private String status;

        /**
         * @return the id
         */
        public long getId() {
                return id;
        }

        /**
         * @param id the id to set
         */
        public void setId(long id) {
                this.id = id;
        }

        /**
         * @return the code
         */
        public String getCode() {
                return code;
        }

        /**
         * @param code the code to set
         */
        public void setCode(String code) {
                this.code = code;
        }

        /**
         * @return the name
         */
        public String getName() {
                return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
                this.name = name;
        }

        /**
         * @return the status
         */
        public String getStatus() {
                return status;
        }

        /**
         * @param status the status to set
         */
        public void setStatus(String status) {
                this.status = status;
        }

}
