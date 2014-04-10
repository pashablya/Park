package com.demo.park.Trees;

import com.demo.park.Tree;


public class Oak extends Tree {

	public Oak(int height)
	{
		super(height);		
	}

	@Override
	public String getShodow() 
	{
		return "Oak cast large shadow";
	}

}
