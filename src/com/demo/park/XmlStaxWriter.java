package com.demo.park;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class XmlStaxWriter {
		
	public static void writeXML(String filePath, List< Map<String,String>> plants) {
		String rootElement = "park";	   
		XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
        try
        {
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileOutputStream(filePath), "UTF-8");          
            xmlStreamWriter.writeStartDocument("UTF-8", "1.0");
            xmlStreamWriter.writeCharacters("\n");
            xmlStreamWriter.writeStartElement(rootElement);
            
            write(xmlStreamWriter);
            
            xmlStreamWriter.writeCharacters("\n");
            xmlStreamWriter.writeEndElement();
             
            //write end document
            xmlStreamWriter.writeEndDocument();
             
            //flush data to file and close writer
            xmlStreamWriter.flush();
            xmlStreamWriter.close();
            
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	}
	
	private static void writePlant(XMLStreamWriter writer, Map<String,String> plant) throws XMLStreamException
	{
		 writer.writeCharacters("\n\t");
         writer.writeStartElement(plant.get("name"));
         writer.writeAttribute("id", plant.get("id"));
         writer.writeCharacters("\n\t");
         writer.writeStartElement("height");
         writer.writeCharacters(plant.get("height"));
         writer.writeCharacters("\n\t");
         writer.writeEndElement();
         writer.writeStartElement("specialty");
         writer.writeCharacters(plant.get("specialty"));
         writer.writeCharacters("\n\t");
         writer.writeEndElement();
         writer.writeEndElement();
	}
	
	private static void write(XMLStreamWriter writer ) throws XMLStreamException{
		List <Class> plantSuperClasses = Park.getInstance().getSuperClasses();
		List <Class> plantClasses = Park.getInstance().getPlantClasses();
		for(Class superClass : plantSuperClasses)
		{
			 writer.writeCharacters("\n\t");
	         writer.writeStartElement(superClass.getSimpleName());         
			for(Class plantClass : plantClasses)
			{			
				if (plantClass.getSuperclass()==superClass){
				 	 writer.writeCharacters("\n\t");
					 writer.writeStartElement(plantClass.getSimpleName());
					 for(Map<String, String> plantMap : Park.getInstance().getPlantsByType(plantClass))
			         {
			        	 writePlant(writer,plantMap);
			         } 
					 writer.writeCharacters("\n\t");
				     writer.writeEndElement();
				}	
				
			}             
	        writer.writeCharacters("\n\t");
	        writer.writeEndElement();	         
		}		
	}

}
