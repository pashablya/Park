package com.demo.park;

public abstract class Tree extends Plant {

	protected  int size=3;
	
	public Tree(int height)
    {
		super(height);	
	}
	
	public abstract String getShodow();
	
	public int getShodowSquare()
	{
		return height*size;
	}
	
	@Override
	public String getSpecialty(){
		return getShodow();
	}
	
	
}
