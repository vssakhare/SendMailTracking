package in.emp.util;

//-- Java Imports
import java.util.*;
import java.lang.Math;
import javax.servlet.http.HttpServletRequest;
import in.emp.util.ValueListHandler;
import in.emp.util.ValueListIterator;

//--  Imports
//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class PagingManager implements java.io.Serializable
{
	//private static Logger logger = new Logger(PagingManager.class.getName());
	private static Logger logger = Logger.getLogger(PagingManager.class);

	/**
	 * Constructor to start the paging process
	 * or going to the same page after saving data
	 * @ param HttpServletRequest request, Collection pagingColl, boolean startPaging, String className, double totalrecords
	 * @returns void
	 */
	public static void startPaging(HttpServletRequest request, Collection pagingColl, boolean startPaging, String className,double totRecords) 
	{
		if(pagingColl != null && pagingColl.size() > 0)
		{
			List pagingList = (List) pagingColl;
			if(startPaging)
			{
				startPaging(request, pagingList, className,totRecords);
			}
			else
			{
				goToSamePage(request, pagingList, className,totRecords);				
			}
		}
	}

		/**
	 * Constructor to start the paging process
	 * or going to the same page after saving data
	 * @param HttpServletRequest request, Collection pagingColl, boolean startPaging, String className
	 * @returns void
	 */
	public static void startPaging(HttpServletRequest request, Collection pagingColl, boolean startPaging, String className) 
	{
		if(pagingColl != null && pagingColl.size() > 0)
		{
			List pagingList = (List) pagingColl;
			if(startPaging)
			{
				startPaging(request, pagingList, className);
			}
			else
			{
				goToSamePage(request, pagingList, className);				
			}
		}
	}


	/**
	 * Constructor to go to a page number
	 */
	public static void continuePaging(HttpServletRequest request, String className) 
	{ 
		String nextOrPrevious = request.getParameter("nextOrPrevious");
		if(nextOrPrevious != null)
		{
			if(nextOrPrevious.equalsIgnoreCase("go"))
			{
				String pageNoStr = request.getParameter("pageNo");
				goToPage(pageNoStr, request, className);
			}
			if(nextOrPrevious.equalsIgnoreCase("next"))
			{
				goToNextPage(request, className);
			}
			if(nextOrPrevious.equalsIgnoreCase("previous"))
			{
				goToPreviousPage(request, className);
			}
		}
	}

	/**
	 * Starts the paging process
	 * @param HttpServletRequest request, List pagingList, String className,double totRecords
	 * @return void
	 */	
	private static void startPaging(HttpServletRequest request, List pagingList, String className,double totRecords)
	{
            int pageSize = 0;
		try
		{
			if(request.getSession().getAttribute("valueListHandlerObj"+className) != null)
			{
				request.getSession().removeAttribute("valueListHandlerObj"+className);
			}

			ValueListIterator valueListHandlerObj = new ValueListHandler();
			valueListHandlerObj.setList(pagingList);
			valueListHandlerObj.setTotalSize(totRecords);
			request.getSession().setAttribute("valueListHandlerObj"+className, valueListHandlerObj);

                        if(request.getSession().getAttribute("pageSize") != null)
                        {
                            pageSize = (Integer)request.getSession().getAttribute("pageSize");
                            request.getSession().removeAttribute("pageSize");
                        }
                        else
                        {
                            pageSize = getPageSize();
                        }
			List listOfNextElements = valueListHandlerObj.getNextElements(pageSize);
			int lastRow = pageSize;
			int firstRow = valueListHandlerObj.getFirstRow(pageSize, lastRow);

			request.setAttribute("pagedList", listOfNextElements);
			request.setAttribute("hasNext", (valueListHandlerObj.hasNext())+"");
			request.setAttribute("hasPrevious", "false");
			request.setAttribute("firstRow", firstRow+"");
			request.setAttribute("lastRow", lastRow+"");

			double totalRecords = valueListHandlerObj.getTotalSize();
			setDisplayStr(request,totalRecords,lastRow);
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "PagingManager :: startPaging() :: Exception :: " + ex);
		}
	}

	/**
	 * Starts the paging process
	 * @param HttpServletRequest request, List pagingList, String className
	 * @return void
	 */	
	private static void startPaging(HttpServletRequest request, List pagingList, String className)
	{
            int pageSize = 0;
		try
		{
			if(request.getSession().getAttribute("valueListHandlerObj"+className) != null)
			{
				request.getSession().removeAttribute("valueListHandlerObj"+className);
			}

			ValueListIterator valueListHandlerObj = new ValueListHandler();
			valueListHandlerObj.setList(pagingList);
			valueListHandlerObj.setTotalSize(getDoubleValue(valueListHandlerObj.getSize()));
			request.getSession().setAttribute("valueListHandlerObj"+className, valueListHandlerObj);
                        if(request.getAttribute("pageSize") != null)
                        {
                            pageSize = (Integer)request.getAttribute("pageSize");
                        }
                        else
                            pageSize = getPageSize();
			List listOfNextElements = valueListHandlerObj.getNextElements(pageSize);
                        
			
			int lastRow = pageSize;
			int firstRow = valueListHandlerObj.getFirstRow(pageSize, lastRow);

			request.setAttribute("pagedList", listOfNextElements);
			request.setAttribute("hasNext", (valueListHandlerObj.hasNext())+"");
			request.setAttribute("hasPrevious", "false");
			request.setAttribute("firstRow", firstRow+"");
			request.setAttribute("lastRow", lastRow+"");

			double totalRecords = getDoubleValue(valueListHandlerObj.getSize());
			setDisplayStr(request,totalRecords,lastRow);
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "PagingManager :: startPaging() :: Exception :: " + ex);
		}
	}

	/**
	 * Goes to the next page
	 * @return void
	 */	
	private static void goToNextPage(HttpServletRequest request, String className)
	{
		try
		{
			String lastRowStr = request.getParameter("lastRow");
			int lastRow = getIntValue(lastRowStr);
			int nextPageNo = (lastRow/getPageSize())+1;
			goToPage(nextPageNo+"", request, className);
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "PagingManager :: goToNextPage() :: Exception :: " + ex);
		}
	}

	/**
	 * Goes to the previous page
	 * @return void
	 */		
	private static void goToPreviousPage(HttpServletRequest request, String className)
	{
		try
		{
			String lastRowStr = request.getParameter("lastRow");
			int lastRow = getIntValue(lastRowStr);
			int previousPageNo = (lastRow/getPageSize())-1;
			goToPage(previousPageNo+"", request, className);
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "PagingManager :: goToPreviousPage() :: Exception :: " + ex);
		}
	}

	/**
	 * Remains the same page
	 * For example; if the data in the page is saved, 
	 * the same page should be displayed with the updated data
	 * @return void
	 */			
	private static void goToSamePage(HttpServletRequest request, List pagingList, String className,double totRecords)
	{
		try
		{
			if(request.getSession().getAttribute("valueListHandlerObj"+className) != null)
			{
				request.getSession().removeAttribute("valueListHandlerObj"+className);
			}

			ValueListIterator valueListHandlerObj = new ValueListHandler();
			valueListHandlerObj.setList(pagingList);
			valueListHandlerObj.setTotalSize(totRecords);
			request.getSession().setAttribute("valueListHandlerObj"+className, valueListHandlerObj);
			
			String lastRowStr = request.getParameter("lastRow");
			int lastRow = getIntValue(lastRowStr);
			int samePageNo = (lastRow/getPageSize());
			goToPage(samePageNo+"", request, className);
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "PagingManager :: goToSamePage() :: Exception :: " + ex);
		}
	}


    /**
	 * Remains the same page 
	 * For example; if the data in the page is saved, 
	 * the same page should be displayed with the updated data
	 * @return void
	 */			
	private static void goToSamePage(HttpServletRequest request, List pagingList, String className)
	{
		try
		{
			if(request.getSession().getAttribute("valueListHandlerObj"+className) != null)
			{
				request.getSession().removeAttribute("valueListHandlerObj"+className);
			}

			ValueListIterator valueListHandlerObj = new ValueListHandler();
			valueListHandlerObj.setList(pagingList);
			valueListHandlerObj.setTotalSize(getDoubleValue(valueListHandlerObj.getSize()));
			request.getSession().setAttribute("valueListHandlerObj"+className, valueListHandlerObj);

			String lastRowStr = request.getParameter("lastRow");
			int lastRow = getIntValue(lastRowStr);
			int samePageNo = (lastRow/getPageSize());
			goToPage(samePageNo+"", request, className);
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "PagingManager :: goToSamePage() :: Exception :: " + ex);
		}
	}

	/**
	 * Goes to a partcular page no.
	 * @return void
	 */	
	private static void goToPage(String pageNoStr, HttpServletRequest request, String className)
	{
		try
		{
			ValueListHandler valueListHandlerObj = (ValueListHandler)request.getSession().getAttribute("valueListHandlerObj"+className);
			
			int pageNo = getIntValue(pageNoStr);
			
			double totalRecords = valueListHandlerObj.getTotalSize();
			int totalPages = (int) Math.ceil(totalRecords/getDoubleValue(getPageSize()));
			if(pageNo > totalPages)
				pageNo = totalPages;

			int lastRow = pageNo * getPageSize();
			int firstRow = valueListHandlerObj.getFirstRow(getPageSize(), lastRow);
			getPageElements(request, firstRow, lastRow,className);
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "PagingManager :: goToPage() :: Exception :: " + ex);
		}
	}

	/**
	 * Fetches the page elements for display
	 * @return void
	 */
	private static void getPageElements(HttpServletRequest request, int firstRow, int lastRow,String className)
	{
		try
		{
			ValueListHandler valueListHandlerObj = (ValueListHandler)request.getSession().getAttribute("valueListHandlerObj"+className);
			
	/*		if (className.equals("in.mda.handler.ScheduleHandler"))
			{
				List listOfNextElements = valueListHandlerObj.getSameElements(getPageSize(), firstRow, lastRow,getCachePageSize());
			}
/*			else if (className.equals("in.mda.handler.SearchHandler"))
			{
				System.out.println("Paging MAnager :: getPageElements :: inside if :: ClassName ::" + className);
				listOfNextElements = valueListHandlerObj.getSameElements(getPageSize(), firstRow, lastRow);	
			}
	*/		
			List listOfNextElements = null;
			if(request.getAttribute("noCache") != null && ((String)request.getAttribute("noCache")).equals("true"))
				listOfNextElements = valueListHandlerObj.getSameElements(getPageSize(), firstRow, lastRow);
			else
				listOfNextElements = valueListHandlerObj.getSameElements(getPageSize(), firstRow, lastRow,getCachePageSize());

			request.setAttribute("pagedList", listOfNextElements);
			request.setAttribute("hasNext", (valueListHandlerObj.hasNext())+"");
			request.setAttribute("hasPrevious", (valueListHandlerObj.hasPrevious(getPageSize()))+"");
			request.setAttribute("firstRow", firstRow+"");
			request.setAttribute("lastRow", lastRow+"");

			double totalRecords = valueListHandlerObj.getTotalSize();
			setDisplayStr(request,totalRecords,lastRow);
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "PagingManager :: getPageElements() :: Exception :: " + ex);
		}
	}

	/**
	 * Contructs a string for displaying paging info
	 * @return void
	 */	
	private static void setDisplayStr(HttpServletRequest request, double totalRecords,int lastRow)
	{
		try
		{
			int totalPages = (int) Math.ceil(totalRecords/getDoubleValue(getPageSize()));
			int currentPage = lastRow/getPageSize();
			String displayStr = "Page " + currentPage + " of " + totalPages;
			String totalRecordsStr = ((int)Math.ceil(totalRecords)) + " record(s) found";

		    // -- update starts --	
			if (currentPage < totalPages)
			{
				request.setAttribute("hasNext", "true");
			}
			
			if (currentPage > 1)
			{
				request.setAttribute("hasPrevious", "true");
			}
			// -- update ends --

			request.setAttribute("displayStr", displayStr);
			request.setAttribute("totalRecordsStr", totalRecordsStr);
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "PagingManager :: setDisplayStr() :: Exception :: " + ex);
		}
	}

	/**
	 * Returns the int value given the String value.
	 * @param String
	 * @return int
	 */
	private static int getIntValue(String val)
	{
		int i = 1 ;
		if ((val == null) || (val.trim().equals(""))) return i ;
		try
		{
			i = Integer.parseInt(val);
			if(i <= 0)
				i = 1;
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "PagingManager :: getIntValue() :: Exception :: " + ex);
			i = 1 ;
		}
		return i ;
	}

	/**
	 * Returns the double value given the int value.
	 * @param int
	 * @return double
	 */
	private static double getDoubleValue(int val)
	{
		double d = 1 ;
		try
		{
			d = Double.parseDouble(val+"") ;
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "PagingManager :: getDoubleValue() :: Exception :: " + ex);
			d = 1 ;
		}
		return d ;
	}

	/**
	 * Returns the page size as specified in ApplicationResources.properties
	 * @return int the page size
	 */
	private static int getPageSize()
	{
		int pageSize = 0;
		try
		{
			pageSize = getIntValue(System.getProperty("UI_PAGE_SIZE"));
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "PagingManager :: getPageSize() :: Exception :: " + ex);
			pageSize = 10;
		}
		return pageSize;
	}
	
	/**
	 * Returns the Cache page size as specified in ApplicationResources.properties
	 * @return int the cache page size
	 */
	private static int getCachePageSize()
	{
		int cachePageSize = 0;
		try
		{
			cachePageSize = getIntValue(System.getProperty("CACHE_PAGE_SIZE"));
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "PagingManager :: getCachePageSize() :: Exception :: " + ex);
			cachePageSize = 3;
		}
		return cachePageSize;
	}

}//end class