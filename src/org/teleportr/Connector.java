package org.teleportr;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;


public abstract class Connector {


    public long getRides(Place from, Place to, Date dep, Date arr) { return 0; }

    public void postRide(Place from, Place to, Date dep, Date arr) {}

    public void resolvePlace(Place place) {}


    public ArrayList<String> placesBatch;
    public ArrayList<String> ridesBatch;

    public Connector() {
        placesBatch = new ArrayList<String>();
        ridesBatch = new ArrayList<String>();
    }

    protected Place store(Place place) {
        placesBatch.add(place.toString());
        return place;
    }

    protected void store(Ride ride) {
        ridesBatch.add(ride.toString());
    }

    public void printResults() {
        for (int i = 0; i < ridesBatch.size(); i++) {
            System.out.println(ridesBatch.get(i));
        }
    }

    public String getSetting(String key) {
        if (key.equals("radius_from")) return "15";
        if (key.equals("radius_to")) return "25";
        return null;
    }
    
    public static String httpGet(String url) {
        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (IOException e) {
            System.out.println("io exception " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return jsonResults.toString();
    }
}
