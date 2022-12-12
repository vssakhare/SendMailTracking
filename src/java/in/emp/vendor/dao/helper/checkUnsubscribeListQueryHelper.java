/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper;
import in.emp.dao.QueryHelper;
import in.emp.vendor.bean.MailStatusBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Pooja Jadhav
 */
public class checkUnsubscribeListQueryHelper  implements QueryHelper{
      private static Logger logger = Logger.getLogger(GetMailTrackerListQueryHelper.class); 
    private MailStatusBean mailbean;
     public checkUnsubscribeListQueryHelper(MailStatusBean mailbean) {
        this.mailbean = mailbean;
    }
     public Object getDataObject(ResultSet results) throws Exception {
        MailStatusBean mailbean = new MailStatusBean();
        try {
            logger.log(Level.INFO, "checkUnsubscribeListQueryHelper ::: getDataObject() :: method called ::");
      
           
        
           mailbean.setCount_of_unsubscribe(results.getInt("Count_Of_Unsubscribe"));
           
      
          
        } catch (Exception ex) {
            logger.log(Level.ERROR, "checkUnsubscribeListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return mailbean;
    }
       public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        int count= 0;
        try {
            sql.append(" SELECT count(*)Count_Of_Unsubscribe   ");
            sql.append("  FROM unsubscribe_mail_details P WHERE EMAIL_ID = ? ");
        
            
           
            logger.log(Level.INFO, "checkUnsubscribeListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setInt(1, mailbean.getCount_of_unsubscribe());
                     
            rs = statement.executeQuery();
          

        } catch (Exception ex) {
            logger.log(Level.ERROR, "checkUnsubscribeListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }

}
