/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.system;
import in.emp.system.dao.SecurityDTO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author admin
 */
public interface SecurityInterface {

      public List getOfficeCodeBuList(SecurityDTO securityDTO) throws Exception;

	public List getOfficeLocBuList(SecurityDTO securityDTO) throws Exception;

	public List getOfficeCodeCircleList(SecurityDTO securityDTO) throws Exception;

        public List getOfficeLocCircleList(SecurityDTO securityDTO) throws Exception;
        
       // public String getLocationID(HttpServletRequest request) throws Exception;
        
        
        

}
