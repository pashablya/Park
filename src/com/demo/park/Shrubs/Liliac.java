package com.demo.park.Shrubs;

import com.demo.park.Shrub;


public class Liliac extends Shrub {

	public Liliac(int height) {
		super(height);
		
	}

	@Override
	public String getFlower()
	{
		return "Lilac's flowers are purple and very beautiful";
	}

}
