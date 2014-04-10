package com.demo.park;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlDomParser {
	
	
	private List<Plant> plants;
	private Park park;

    public XmlDomParser(File xmlFile)
    {
    	  this.park=park.getInstance();
	    plants=createPlants(getDocument(xmlFile));
	  
    }

    public List<Plant> getStudents()
    {
        return plants;
    }

    

    private List<Plant> createPlants(Document doc)
    {   
    	Node rootXmlElement = doc.getFirstChild();
    	NodeList plantsXmlelement = rootXmlElement.getChildNodes();   	
    	for(int i=0;i<plantsXmlelement.getLength();i++)
    	{
    		if (plantsXmlelement.item(i).getNodeType() == Node.ELEMENT_NODE)
    		{ 			
	    		Element plantType = (Element)plantsXmlelement.item(i);            
	            if(plantTypeValid(plantType.getNodeName())){
	            	processEachPlant(plantType) ;           	
	            }
    		}		
    	}  	      
        return plants;
   }
    
    private void processEachPlant(Element planType)
    {
    	NodeList plants = planType.getChildNodes();   	
    	for(int i=0;i<plants.getLength();i++)
    	{
    		if (plants.item(i).getNodeType() == Node.ELEMENT_NODE)
    		{ 			
	    		Element plant = (Element)plants.item(i);            
	            if(plantTypeValid(plant.getNodeName()))
	            {
	                processPlant(plant);
	            }
    		}		
    	}  	      
   	
    }
    
    private void processPlant(Element plant) 
    {   	
    	String count = plant.getAttribute("count");
    	String maxHeight = plant.getAttribute("min-height");
    	String minHeight = plant.getAttribute("max-height");
    	park.addPlants(upFirstChar(plant.getNodeName()), count, maxHeight, minHeight);
    }
    
    
    private static Document getDocument(File file) 
    {
        try 
        {
             DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
             factory.setValidating(false);
             DocumentBuilder builder = factory.newDocumentBuilder();
             return builder.parse(file);
        }
        catch (SAXException sax) 
        {
          return null;
        }
        catch (IOException io)
        {
          return null;
        }
        catch (ParserConfigurationException parserException)
        {
          return null;
        }
    }
    
    private boolean plantTypeValid(String className)
    {	   
        Package[] packages = Package.getPackages(); 	
    	for (final Package p : packages) 
    	{
            StringBuilder pack = new StringBuilder(p.getName());
            
            if(pack.indexOf("com.demo.park")!=-1)
            {
	            String tentative = pack + "." + upFirstChar(className);
	            try 
	            {
	                Class.forName(tentative);
	                return true;
	            } 
	            catch (final ClassNotFoundException e) 
	            {
	                continue;
	            }
            }
        }
       System.out.println("Error: undefinded class \""+className+"\"");
       return false;
    }
    
    private boolean validPackageName(String packageName)
    {  
        Pattern p = Pattern.compile("^[a-z0-9_-]{3,15}$");  
        Matcher m = p.matcher(packageName);  
        return m.matches();  
    }  
    
    private String upFirstChar(String str)
    {
    	String firstChar = str.substring(0, 1);
       	String otherChars= str.substring(1);
    	firstChar=firstChar.toUpperCase();
    	return firstChar+otherChars;
    }
  

}
