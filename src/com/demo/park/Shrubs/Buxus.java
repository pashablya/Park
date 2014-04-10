package com.demo.park.Shrubs;

import com.demo.park.Shrub;

public class Buxus extends Shrub {

	public Buxus(int height)
	{
		super(height);
	}

	@Override
	public String getFlower()
	{
		return "Buxuses flowers are green perhaps";
	}

}
