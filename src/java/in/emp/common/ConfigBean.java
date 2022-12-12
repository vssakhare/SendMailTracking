package in.emp.common;

/**
 * This public Class use for accumulating Config information
 */
public class ConfigBean implements java.io.Serializable
{
	private String uiActionName;
	private String handlerClassName;
	private String uiSuccessPage;
	private String uiErrorPage;

	/**
	 * Public API to set the UI action name
	 * @param uiActionName, UI action name
	 * @return void
	 */
	public void setUiActionName(String uiActionName)
	{
		this.uiActionName = uiActionName;
	}
	
	/**
	 * Public API to get the UI action name
	 * @param void
	 * @return String uiActionName, UI action name
	 */
	public String getUiActionName()
	{
		return this.uiActionName;
	}

	/**
	 * Public API to set the Handler class name
	 * @param handlerClassName, Handler class name
	 * @return void
	 */
	public void setHandlerClassName(String handlerClassName)
	{
		this.handlerClassName = handlerClassName;
	}
	
	/**
	 * Public API to get the Handler class name
	 * @param void
	 * @return String handlerClassName, Handler class name
	 */
	public String getHandlerClassName()
	{
		return this.handlerClassName;
	}

	/**
	 * Public API to set the UI success page
	 * @param uiSuccessPage, UI success page
	 * @return void
	 */
	public void setUiSuccessPage(String uiSuccessPage)
	{
		this.uiSuccessPage = uiSuccessPage;
	}
	
	/**
	 * Public API to get the UI success page
	 * @param void
	 * @return String uiSuccessPage, UI success page
	 */
	public String getUiSuccessPage()
	{
		return this.uiSuccessPage;
	}

	/**
	 * Public API to set the UI error page
	 * @param uiErrorPage, UI Error page
	 * @return void
	 */
	public void setUiErrorPage(String uiErrorPage)
	{
		this.uiErrorPage = uiErrorPage;
	}
	
	/**
	 * Public API to get the UI error page
	 * @param void
	 * @return String uiErrorPage, UI error page
	 */
	public String getUiErrorPage()
	{
		return this.uiErrorPage;
	}
}