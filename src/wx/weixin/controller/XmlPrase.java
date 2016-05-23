package wx.weixin.controller;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * xml
 * 解析
 * @author Moon 
 *
 */
public class XmlPrase {

	
	public static String xml(String xml){
		try{
		  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
		  DocumentBuilder db = dbf.newDocumentBuilder();  
	      Document document = db.parse(xml);  
	      NodeList list = document.getElementsByTagName("xml");  
	      for(int i = 0; i < list.getLength(); i++)  
	      {  
            Element element = (Element)list.item(i);  
            String content = element.getElementsByTagName("Articles").item(0).getFirstChild().getNodeValue();  
	        System.out.println(content);  
	      }  
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	          
	}
}
