package com.demo.park.Trees;

import com.demo.park.Tree;


public class Linden extends Tree{
	
	

	public Linden(int height) 
	{
		super(height);
	   // size=1.5;
	}

	@Override
	public String getShodow() 
	{
		return "Linden cast average shadow, square is "+getShodowSquare();
	}

}
