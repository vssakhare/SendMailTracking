package in.emp.ws;

/**
 *
 * @author Sachin
 */
public interface WSUtility {

    public String callWSGet(String wsURL, String params) throws Exception;

    public String callWSPost(String wsURL, String input) throws Exception;

    public String createParamString(Object o) throws Exception;
    
    public String callWSGetNonParam(String wsURL) throws Exception;
    
    public String callWSGetProxy(String wsURL, String params) throws Exception;
}
