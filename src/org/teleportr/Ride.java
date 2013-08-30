package org.teleportr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;


public class Ride {

    public static final int SEARCH = 42;
    public static final int OFFER = 47;

    public static enum Mode {
        CAR, TRAIN
    };

    public static ArrayList<String> mockRides = new ArrayList<String>();

    private String mockRide = "";
    public List<Ride> subrides;
    public Place from;
    public Place to;
    public int price;
    public int seats;
    public Date dep;
    public Date arr;
    public String who;
    public String ref;
    public JSONObject details;
    public boolean marked;
    public Mode mode;
    public boolean active;

    public Ride() {
        mockRides.add(mockRide);
        mode(Mode.CAR);
    }

    public Ride type(int type) {
        return this;
    }

    public Ride mode(Mode mode) {
        this.mode = mode;
        mockRide += "\n mode: " + this.mode;
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
        mockRide += "\n arr: " + arr;
        return this;
    }

    public Ride who(String who) {
        this.who = who;
        mockRide += "\n who: " + who;
        return this;
    }

    public Ride set(String key, String val) {
        try {
            getDetails().put(key, val.substring(0, Math.min(val.length(), 21)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    public Ride marked() {
        this.marked = true;
        mockRide += "\n marked: " + marked;
        return this;
    }

    public Ride activate() {
        this.active = true;
        mockRide += "\n active: " + true;
        return this;
    }

    public Ride deactivate() {
        this.active = false;
        mockRide += "\n active: " + false;
        return this;
    }

    @Override
    public String toString() {
        if (details != null)
            mockRide += "\n details: " + details.toString();
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

    public Mode getMode() {
        return mode;
    }

    public boolean isActive() {
        return active;
    }

    public String get(String key) {
        try {
            return getDetails().getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONObject getDetails() {
        if (details == null)
            details = new JSONObject();
        return details;
    }
}
