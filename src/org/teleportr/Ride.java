package org.teleportr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Ride {

    public static final int SEARCH = 42;
    public static final int OFFER = 47;

    public static ArrayList<String> mockRides = new ArrayList<String>();

    private String mockRide = "";
    private List<Ride> subrides;
    private Place from;
    private Place to;
    private int price;
    private int seats;
    private Date dep;
    private Date arr;
    private String who;
    private String ref;

    public Ride() {
        mockRides.add(mockRide);
    }

    public Ride type(int type) {
        return this;
    }

    public Ride from(Place from) {
        mockRide += "\n from: " + from.geohash;
        this.from = from;
        return this;
    }

    public Ride via(Place via) {
        mockRide += "\n via: " + via.geohash;
        Ride sub = new Ride();
        if (subrides == null) {
            subrides = new ArrayList<Ride>();
            sub.from(from);
        } else {
            sub.from(subrides.get(subrides.size()-1).getTo());
        }
        sub.to(via);
        subrides.add(sub);
        return this;
    }

    public Ride to(Place to) {
        mockRide += "\n to: " + to.geohash;
        this.to = to;
        if (subrides != null) {
            Ride sub = new Ride();
            sub.from(subrides.get(subrides.size()-1).getTo());
            sub.to(to);
            subrides.add(sub);
        }
        return this;
    }

    public Ride dep(Date dep) {
        this.dep = dep;
        mockRide += "\n dep: " + dep;
        return this;
    }

    public Ride arr(Date arr) {
        this.arr = arr;
        mockRide += ", arr: " + arr;
        return this;
    }

    public Ride who(String who) {
        this.who = who;
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

    public Ride price(int price) {
        this.price = price;
        mockRide += "\n price: " + price;
        return this;
    }

    public Ride seats(int seats) {
        this.seats = seats;
        mockRide += "\n seats: " + seats;
        return this;
    }

    public Ride ref(String ref) {
        this.ref = ref;
        mockRide += "\n ref: " + ref;
        return this;
    }

    @Override
    public String toString() {
        return mockRide;
    }

    public List<Ride> getSubrides() {
        return subrides;
    }

    public List<Place> getVias() {
        ArrayList<Place> vias = new ArrayList<Place>();
        if (subrides != null) {
            for (int i = 1; i < subrides.size(); i++) {
                vias.add(subrides.get(i).getFrom());
            }
        }
        return vias;
    }

    public List<Place> getPlaces() {
        ArrayList<Place> places = new ArrayList<Place>();
        if (subrides != null) {
            for (int i = 0; i < subrides.size(); i++) {
                places.add(subrides.get(i).getFrom());
            }
            places.add(getTo());
        }
        return places;
    }

    public Place getFrom() {
        return from;
    }

    public Place getTo() {
        return to;
    }
    public Date getDep() {
        return dep;
    }

    public Date getArr() {
        return arr;
    }

    public String getWho() {
        return who;
    }

    public String getRef() {
        return ref;
    }

    public int getPrice() {
        return price;
    }

    public int getSeats() {
        return seats;
    }
}
