package in.emp.common;

import java.util.HashMap;
import java.io.File;
import java.io.InputStream;

import in.emp.common.ConfigBean;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
* Public class for Actions 
*/
public class ActionClass
{

    /**
     * This public static synchronized API will read the xml file containing the Action and Class mapping
     * and populate in the hasmap.
     * @param inputStreamObj, InputStream it provide the xml content.
     * @return java.util.HashMap containing the mapping of Action and Class.
     */
    public synchronized static HashMap buildActionClassMap(InputStream inputStreamObj)
	{
		DocumentBuilderFactory factory = null;
        DocumentBuilder builder = null;
        Document doc = null;
		HashMap map = null;
		ConfigBean configBeanObj = null;

        try
		{
			factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
			doc = builder.parse(inputStreamObj);    	
			Element docEle = doc.getDocumentElement();
			NodeList nList = docEle.getChildNodes();            
			map = new HashMap();
            for(int i=0; i<nList.getLength(); i++)
			{
				String attrNodeValue = "";
				String attrNodeName = "";
				String successPageVal = "";
				String errorPageVal = "";
				String uiActionName = "";
				Node childNode = nList.item(i);
				if(childNode.getNodeName().equals("actionhandlerclass"))
				{
					NamedNodeMap attrs = childNode.getAttributes();
					if(attrs != null && attrs.getLength() > 0)
					{
						for(int iCount = 0 ; iCount < (attrs.getLength()) ; iCount++)
						{
							Node attrNode = attrs.item(iCount);
							if(attrNode != null)
							{
								attrNodeName = attrNode.getNodeName();								
								attrNodeValue = attrNode.getNodeValue();
							}
						}
					}
				}		
				NodeList nList1 = childNode.getChildNodes();
				for(int j=0;j<nList1.getLength();j++)
				{
					Node childNode1 = nList1.item(j);					
					NodeList nList2 = childNode1.getChildNodes();
					for(int k=0; k<nList2.getLength(); k++)
					{
						Node childNode2 = nList2.item(k);

						NodeList nList3 = childNode2.getChildNodes();
						for(int l=0;l<nList3.getLength();l++)
						{
							Node childNode3 = nList3.item(l);
							if(childNode3.getNodeName().equals("action-name"))
							{
								uiActionName = childNode3.getFirstChild().getNodeValue();
								configBeanObj = new ConfigBean();
								configBeanObj.setUiActionName(uiActionName);
								configBeanObj.setHandlerClassName(attrNodeValue);
							}
							else if(childNode3.getNodeName().equals("success-page"))
							{
								successPageVal = childNode3.getFirstChild().getNodeValue();
								configBeanObj.setUiSuccessPage(successPageVal);
							}
							else if(childNode3.getNodeName().equals("error-page"))
							{
								errorPageVal = childNode3.getFirstChild().getNodeValue();
								configBeanObj.setUiErrorPage(errorPageVal);								
							}
							map.put(uiActionName,configBeanObj);
						}
					}
				}
			}			
        } 
		catch (Exception e) 
		{
			e.printStackTrace();
            
        } 
		finally 
		{
			if (doc != null)
			{
                doc = null;
			}
            if (builder != null)
			{
                builder = null;
			}
            if (factory != null)
			{
                factory = null;
			}
        }
        return map;
    }

    /**
     * The only private constructor to the class.
     */
    private ActionClass() 
	{
    }
}