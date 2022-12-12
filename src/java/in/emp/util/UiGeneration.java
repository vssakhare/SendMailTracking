package in.emp.util;

import java.util.*;
import java.sql.*;

//--  Imports

//-- logger Imports


public class  UiGeneration
{
	HashMap annotationMap = new HashMap();
	HashMap paramCodesMap = new HashMap();
	HashMap eventCodesMap = new HashMap();
	HashMap unitCodesMap = new HashMap();

	// public constructor

	public UiGeneration() throws Exception
	{
		Connection conn = null;
		try
		{	
			// getting connection object
			conn = ApplicationUtils.getConnection();

			annotationMap = getAnnotationMap(conn);
			paramCodesMap = getParamCodesMap(conn);
			eventCodesMap = getEventCodesMap(conn);
			unitCodesMap = getUnitCodesMap(conn);
		
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		finally
		{
			if (conn != null)
			{
				conn.close();
			}
		}
		
	
	}
	
	/*
	*public API to get the annotation for the given xpath
	*@param String xPath
	*@returns String Annotation
	*/
	public String getAnnotation(String xPath,String nodeName)
	{
		String annotation = null;
		String xPathStr = null;
		try
		{
			//System.out.println("Xpath from xsl:::" + xPath);
                   //     System.out.println("Node Name:::" + nodeName);
			if (xPath.indexOf("D3-") != -1)
				xPathStr = getPathString(xPath);
			else
				xPathStr = xPath;
			
		//	System.out.println("XpathStr b4 getting Annotation :::" + xPathStr);
			if (annotationMap.containsKey(xPathStr))
			{
				annotation = (String)annotationMap.get(xPathStr);
			}
			else
				annotation = nodeName;
		//	System.out.println("Annotation for Xpath:::" + annotation);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return annotation;
	} // End of Method



	public String getPathString(String path)
	{
		String newPath = null;
		String charVal = "";
		try
		{
			int index = path.indexOf("D3-");
			String sub = path.substring(index);
			index = sub.indexOf("/");
			for (int i= 0 ;i<index ;i++ )
			{
				charVal = charVal + sub.charAt(i);
			}
			newPath = path.replaceAll(charVal,"D3-x");

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return newPath; 
	} // End Of Method
	
	/*
	*public API to get Hash Map of Xpath & annotations from database
	*@param String xPath
	*@returns String Annotation
	*/
	public HashMap getAnnotationMap(Connection conn) throws Exception
	{
		PreparedStatement sqlSt = null;
		ResultSet result = null;
		StringBuffer sqlStr = new StringBuffer();
		try
		{
			sqlStr.append("SELECT DEVICE_DATA_FIELD_XPATH, ");
			sqlStr.append("DEVICE_DATA_FIELD_ANNOTATION ");
			sqlStr.append("FROM ");
			sqlStr.append("DEVICE_DATA_FIELD");
				
			sqlSt = conn.prepareStatement(sqlStr.toString());
			result = sqlSt.executeQuery();
			
			while (result.next())
			{
				annotationMap.put(result.getString("DEVICE_DATA_FIELD_XPATH"),result.getString("DEVICE_DATA_FIELD_ANNOTATION"));		
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		finally
		{
			if (result != null)
			{
				result.close();
			}
			
			if (sqlSt != null)
			{
				sqlSt.close();
			}
		}

		return annotationMap;
	} // End Of Method
	
	/*
	*public API to get Hash Map of Parameter Code & description from database
	*@param String xPath
	*@returns String Annotation
	*/
	public HashMap getParamCodesMap(Connection conn) throws Exception
	{
		PreparedStatement sqlSt = null;
		ResultSet result = null;
		StringBuffer sqlStr = new StringBuffer();
		try
		{
			sqlStr.append(" SELECT PARAM_CODE, ");
			sqlStr.append(" PARAM_DESC ");
			sqlStr.append(" FROM ");
			sqlStr.append(" XML_PARAM_CODE ");
			
			sqlSt = conn.prepareStatement(sqlStr.toString());
			result = sqlSt.executeQuery();
			
			while (result.next())
			{
				paramCodesMap.put(result.getString("PARAM_CODE"),result.getString("PARAM_DESC"));		
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		finally
		{
			if (result != null)
			{
				result.close();
			}
			
			if (sqlSt != null)
			{
				sqlSt.close();
			}
		}

		return paramCodesMap;
	} // End Of Method

	/*
	*public API to get Hash Map of Event Code & description from database
	*@param String xPath
	*@returns String Annotation
	*/
	public HashMap getEventCodesMap(Connection conn) throws Exception
	{
		PreparedStatement sqlSt = null;
		ResultSet result = null;
		StringBuffer sqlStr = new StringBuffer();
		try
		{
			sqlStr.append(" SELECT EVENT_CODE, ");
			sqlStr.append(" EVENT_DESC ");
			sqlStr.append(" FROM ");
			sqlStr.append(" XML_EVENT_CODE ");
					
			sqlSt = conn.prepareStatement(sqlStr.toString());
			result = sqlSt.executeQuery();
			
			while (result.next())
			{
				eventCodesMap.put(result.getString("EVENT_CODE"),result.getString("EVENT_DESC"));		
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		finally
		{
			if (result != null)
			{
				result.close();
			}
			
			if (sqlSt != null)
			{
				sqlSt.close();
			}
		}

		return eventCodesMap;
	}

		/*
	*public API to get the Parameter Code description from code 
	*@param String xPath
	*@returns String Annotation
	*/
	public String getParamCodeDesc(String code)
	{
		String paramDesc = null;
	
		try
		{
	//		System.out.println("Xpath from xsl:::" + code);
		
			if (paramCodesMap.containsKey(code))
				paramDesc = (String)paramCodesMap.get(code);
			else
				paramDesc = code;

	//		System.out.println("Annotation for Xpath:::" + paramDesc);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return paramDesc;
	} // End of Method


	/*
	*public API to get the Event Description from codes 
	*@param String xPath
	*@returns String Annotation
	*/
	public String getEventCodeDesc(String code)
	{
		String eventDesc = null;

		try
		{
	//		System.out.println("Xpath from xsl:::" + code);

			if (eventCodesMap.containsKey(code))
				eventDesc = (String)eventCodesMap.get(code);
			else
				eventDesc = code;

	//		System.out.println("Annotation for Xpath:::" + eventDesc);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return eventDesc;
	} // End of Method
	
		/*
	*public API to get Hash Map of Unit Code & description from database
	*@param String xPath
	*@returns String Annotation
	*/
	public HashMap getUnitCodesMap(Connection conn) throws Exception
	{
		PreparedStatement sqlSt = null;
		ResultSet result = null;
		StringBuffer sqlStr = new StringBuffer();
		try
		{
			sqlStr.append(" SELECT UNIT_CODE, ");
			sqlStr.append(" UNIT_DESC ");
			sqlStr.append(" FROM ");
			sqlStr.append(" XML_UNIT_CODE ");
					
			sqlSt = conn.prepareStatement(sqlStr.toString());
			result = sqlSt.executeQuery();
			
			while (result.next())
			{
				unitCodesMap.put(result.getString("UNIT_CODE"),result.getString("UNIT_DESC"));		
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		finally
		{
			if (result != null)
			{
				result.close();
			}
			
			if (sqlSt != null)
			{
				sqlSt.close();
			}
		}

		return unitCodesMap;
	}
		
	/*
	*public API to get the Event Description from codes 
	*@param String xPath
	*@returns String Annotation
	*/
	public String getUnitCodeDesc(String code)
	{
		String unitDesc = null;

		try
		{
	//		System.out.println("Xpath from xsl:::" + code);

			if (unitCodesMap.containsKey(code))
				unitDesc = (String)unitCodesMap.get(code);
			else
				unitDesc = code;

	//		System.out.println("Annotation for Xpath:::" + unitDesc);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return unitDesc;
	} // End of Method


} // End Of Class
