package com.demo.park;

public abstract class Plant {
			
	private static int count=0;
	protected int height=0;
	protected int id;

	public Plant(int height)
	{
		id=++count;
		this.height=height;
	}
	
	public abstract String getSpecialty();
	
	public final void plant()
	{
		String className=(getClass()+"");
		System.out.println(className + " was planted, id=" + id + " height=" + height);
	}
		
	public final int getHeight()
	{
		return height;
	}
	
	
	public static int getCount() {
		return count;
	}

	public int getId() {
		return id;
	}
	
	
	

}
