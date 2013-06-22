package org.teleportr;

import java.util.ArrayList;
import java.util.Date;


public class Ride {

    public static final int SEARCH = 42;
    public static final int OFFER = 47;

    public static ArrayList<String> mockRides = new ArrayList<String>();

    String mockRide = "";

    public Ride() {
        mockRides.add(mockRide);
    }

    public Ride type(int type) {
        return this;
    }

    public Ride from(Place from) {
        mockRide += "\n from: " + from.geohash;
        return this;
    }

    public Ride via(Place via) {
        mockRide += "\n via: " + via.geohash;
        return this;
    }

    public Ride to(Place to) {
        mockRide += "\n to: " + to.geohash;
        return this;
    }

    public Ride dep(Date dep) {
        mockRide += "\n dep: " + dep;
        return this;
    }

    public Ride arr(Date arr) {
        mockRide += ", arr: " + arr;
        return this;
    }

    public Ride who(String who) {
        mockRide += "\n who: " + who;
        return this;
    }

    public Ride set(String key, String value) {
        mockRide += "\n " + key + ": " + value;
        return this;
    }

    public Ride details(String details) {
        mockRide += "\n details: "
                + details.substring(0, Math.min(details.length(), 21));
        return this;
    }

    public Ride price(long price) {
        mockRide += "\n price: " + price;
        return this;
    }

    public Ride seats(long seats) {
        mockRide += "\n seats: " + seats;
        return this;
    }

    public Ride ref(String ref) {
        mockRide += "\n ref: " + ref;
        return this;
    }

    @Override
    public String toString() {
        return mockRide;
    }
}
