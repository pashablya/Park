package com.demo.park;
import java.io.File;

import com.demo.park.Shrubs.Liliac;
import com.demo.park.Trees.Pine;


public class Main {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		
		Class a=Pine.class;
		Class b=Liliac.class;
	    System.out.print(new Pine(0).getClass()+"");
		new XmlDomParser(new File("/home/alex/workspace/Park/src/park_settings.xml"));
		XmlStaxWriter.writeXML("park_result.xml",Park.getInstance().getHashMapsPlants());
		
	}

}
