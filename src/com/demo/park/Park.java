package com.demo.park;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Park {
	
	private List<Plant> plants;
	private List<Class> plantSuperClasses;
	private List<Class> plantClasses;
	private static Park instance=null;
	
	private Park(){
		plants = new ArrayList<Plant>();
		plantClasses = new ArrayList<Class>();
		plantSuperClasses = new ArrayList<Class>();
	}
	
	public static Park getInstance(){
		if(instance==null)
		{
			instance=new Park();
			return instance;
		}
		else
		{
			return instance;
		}	
	}
	
	public List<Class> getSuperClasses(){
		return plantSuperClasses;
	}
	
	public List<Class> getPlantClasses(){
		return plantClasses;
	}
	
	public void addPlants(String className, String count, String minHeight, String maxHeight) 
	{
		try
		{			
			Class plantClass = getClassFromName(className);
			addSuperClass(plantClass);
			addClass(plantClass);
			for(int i = 0; i<Integer.parseInt(count);i++)
			{
				Constructor constructor = plantClass.getConstructor(int.class);
				Plant plant=(Plant)constructor.newInstance(generateRandomInt(Integer.parseInt(minHeight), Integer.parseInt(maxHeight)));
				//plant.plant();
				
				System.out.println(getNameFormClass(plantClass.getSuperclass())+"-"+getNameFormClass(plantClass));
				plants.add(plant);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	
	
	public List< Map<String, String>> getPlantsByType(Class plantClass)
	{
		List< Map<String, String> > plantByTypeMaps = new ArrayList< Map<String, String> >();
		for(Plant plant : plants)
		{
		  if (plant.getClass() == plantClass)
		  {
			  plantByTypeMaps.add(getHashMapForPlant(plant));
		  }
		}		
		return plantByTypeMaps;
	}
	
	public Map<String, String> getHashMapForPlant(Plant plant)
	{
		Map<String,String> plantMap = new HashMap<String, String>();
		plantMap.put("name",getNameFormClass(plant.getClass()));
		plantMap.put("id",plant.getId()+"");
		plantMap.put("height",plant.getHeight()+"");
		plantMap.put("specialty", plant.getSpecialty());
		return plantMap;	
	}
	
	public List< Map<String, String>> getHashMapsPlants(){
		List< Map<String, String> > plantMaps = new ArrayList< Map<String, String> >();
		Map<String,String> plantMap;
		for(Plant plant:plants)
		{
			plantMap = new HashMap<String, String>();
			plantMap.put("name",getNameFormClass(plant.getClass()));
			plantMap.put("id",plant.getId()+"");
			plantMap.put("height",plant.getHeight()+"");
			plantMap.put("specialty", plant.getSpecialty());
			plantMaps.add(plantMap);
		}
		return plantMaps;		
	}
	
	
	
	private void addSuperClass(Class plantClass)
	{
		Class superClass = plantClass.getSuperclass();
		if (!plantSuperClasses.contains(superClass))
		{
			plantSuperClasses.add(superClass);	
		}			
	}
	
	private void addClass(Class plantClass)
	{	
		if (!plantClasses.contains(plantClass))
		{
			plantClasses.add(plantClass);
		}			
	}
	

	
	public Class getClassFromName(String name)
	{
		 Package[] packages = Package.getPackages(); 	
	    	for (final Package p : packages) 
	    	{
	            StringBuilder pack = new StringBuilder(p.getName());            
	            if(pack.indexOf("com.demo.park")!=-1)
	            {
		            String tentative = pack + "." + upFirstChar(name);
		            try 
		            {
		                return Class.forName(tentative);	                
		            } 
		            catch (final ClassNotFoundException e) 
		            {
		                continue;
		            }
	            }
	    	}
	    	return null;
	}
	
	private String getNameFormClass(Class plantClass)
	{
		StringBuilder name = new StringBuilder(plantClass.getCanonicalName());	
		name.delete(0, name.lastIndexOf(".")+1);
		return downFirstChar(name.toString());
	}
	
	private int generateRandomInt(int min, int max)
	{
		return (min+new Random().nextInt(max-min));
	}
	
	private String upFirstChar(String str)
    {
    	String firstChar = str.substring(0, 1);
       	String otherChars= str.substring(1);
    	firstChar=firstChar.toUpperCase();
    	return firstChar+otherChars;
    }
	
	private String downFirstChar(String str)
    {
    	String firstChar = str.substring(0, 1);
       	String otherChars= str.substring(1);
    	firstChar=firstChar.toLowerCase();
    	return firstChar+otherChars;
    }

	

}
