package org.teleportr;

import java.util.ArrayList;
import java.util.Date;



public class Ride {
	
	public static ArrayList<String> mockRides = new ArrayList<String>();

	String mockRide = "";
	
	
	public Ride() {
		mockRides.add(mockRide);
	}	
	
	public static Ride store() {
		return new Ride();
	}
	
	
	public Ride from(Place orig) {
		mockRide += ", from=" + orig.geohash;
		return this;
	}
	
	public Ride to(Place orig) {
		mockRide += ", to=" + orig.geohash;
		return this;
	}
	
	public Ride dep(Date dep) {
		mockRide += ", dep=" + dep;
		return this;
	}
	
	public Ride arr(Date arr) {
		mockRide += ", arr=" + arr;
		return this;
	}
	
	
}
