/**
 * Fahrgemeinschaft / Ridesharing App
 * Copyright (c) 2013 by it's authors.
 * Some rights reserved. See LICENSE..
 *
 */

package org.teleportr;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;


public abstract class Connector {


    public abstract long search(Place from, Place to, Date dep, Date arr);

    public abstract int publish(Ride offer) throws Exception;

    public void resolvePlace(Place place) {}

    public String authenticate() { return null; }

    public String getAuth() {
        return authenticate();
    }

    public ArrayList<String> placesBatch;
    public ArrayList<String> ridesBatch;
    public Map<String, String> settings;

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
    
    public String set(String key, String value) {
        return settings.put(key, value);
    }

    public String get(String key) {
        return settings.get(key);
    }
    
    public JSONObject loadJson(HttpURLConnection conn) {
        StringBuilder result = new StringBuilder();
        try {
            InputStreamReader in = new InputStreamReader(
                    new BufferedInputStream(conn.getInputStream()));
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                result.append(buff, 0, read);
            }
            System.out.println("\n" + result.toString() + "\n");
            return new JSONObject(result.toString());
        } catch (JSONException e) {
            System.out.println("json error");
        } catch (IOException e) {
            System.out.println("io error");
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return null;
    }
}
