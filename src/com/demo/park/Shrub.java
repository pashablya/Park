package com.demo.park;

public abstract class Shrub extends Plant {

	public Shrub(int height) {
		super(height);		
	}
	
	@Override
	public String getSpecialty(){
		return getFlower();
	}
	
	public abstract String getFlower();

}
