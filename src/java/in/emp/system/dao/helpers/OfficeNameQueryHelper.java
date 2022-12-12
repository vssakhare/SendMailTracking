/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.system.dao.helpers;

import in.emp.system.dao.SecurityDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class OfficeNameQueryHelper implements QueryHelperSSO {

   private static Logger logger = Logger.getLogger(OfficeNameQueryHelper.class);
   private SecurityDTO securityDTO;

   /**
    * @param securityDTO
    */
   public OfficeNameQueryHelper(SecurityDTO securityDTO) {
      this.securityDTO = securityDTO;
   }

   /* (non-Javadoc)
    * @see in.mahadiscom.ndm.dao.QueryHelper#getDataObject(java.sql.ResultSet)
    */
   public Object getDataObject(ResultSet results) throws Exception {
      String officeName;
      try {
         logger.log(Level.INFO, "OfficeNameQueryHelper :: getDataObject() ::");
         officeName = results.getString("LOCATION_NAME_C");

      } catch (Exception ex) {
         logger.log(Level.ERROR, "OfficeNameQueryHelper :: getDataObject() :: Exception" + ex);
         throw ex;
      }
      return officeName;
   }

   /* (non-Javadoc)
    * @see in.mahadiscom.ndm.dao.QueryHelper#getQueryResults(java.sql.Connection)
    */
   public PreparedStatement getQueryResults(Connection connection) throws Exception {
      PreparedStatement statement = null;
      StringBuffer sql = new StringBuffer();
      ResultSet rs = null;

      try {

         sql.append(" SELECT MOL.LOCATION_NAME_C	"
                 + "FROM MBC_OFFICE_LOCATION MOL "
                 + "WHERE MOL.LOCATION_CD_C = LPAD('" + securityDTO.getOfficeLocationCode() + "',3,'0') "
                 + "AND MOL.OFFICE_TYPE_ID_N = '" + securityDTO.getOfficeTypeId() + "' ");

         statement = connection.prepareStatement(sql.toString());

         //rs = statement.executeQuery();
      } catch (Exception ex) {
         logger.log(Level.ERROR, "OfficeNameQueryHelper :: getQueryResults() :: Exception :: " + ex);
         throw ex;
      }
      return statement;
   }
}
