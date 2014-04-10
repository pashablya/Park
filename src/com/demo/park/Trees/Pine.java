package com.demo.park.Trees;

import com.demo.park.Tree;


public class Pine extends Tree {

	public Pine(int height)
    {
		super(height);
		size=2;
	}
	
	@Override
	public String getShodow() 
	{
		return "Pain cast thin shadow"+getShodowSquare();
	}

}
