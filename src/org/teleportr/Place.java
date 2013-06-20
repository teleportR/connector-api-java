package org.teleportr;

import java.util.HashMap;

import ch.hsr.geohash.GeoHash;

public class Place {

    public static HashMap<String, String> mockPlaces = new HashMap<String, String>();

    public void set(String key, String value) {
        mockPlaces.put(geohash + "---" + key, value);
    }

    public String get(String key) {
        return mockPlaces.get(geohash + "---" + key);
    }

    public long id;
    public int lat;
    public int lng;
    public String geohash;

    public Place(String geohash) {
        geohash(geohash);
    }

    public Place(int lat, int lng) {
        latlon(lat, lng);
    }

    public Place(double lat, double lng) {
        latlon(lat, lng);
    }

    public Place(long id) {
        this.id = id;
    }

    public Place geohash(String geohash) {
        this.geohash = geohash;
        try {
            GeoHash gh = GeoHash.fromGeohashString(geohash);
            this.lat = (int) (Math.round(gh.getPoint().getLatitude() * 1E6));
            this.lng = (int) (Math.round(gh.getPoint().getLongitude() * 1E6));
        } catch (NullPointerException e) {
        }
        return this;
    }

    public Place latlon(int lat, int lng) {
        geohash = GeoHash.withBitPrecision(((double) lat) / 1E6,
                ((double) lng) / 1E6, 55).toBase32();
        this.lat = lat;
        this.lng = lng;
        return this;
    }

    public Place latlon(double lat, double lng) {
        return geohash(GeoHash.withBitPrecision(lat, lng, 55).toBase32());
    }

    public Place name(String name) {
        set("name", name);
        return this;
    }

    public Place address(String address) {
        set("address", address);
        return this;
    }

    @Override
    public String toString() {
        return geohash;
    }

    public double getLat() {
        return ((double) lat) / 1E6;
    }

    public double getLng() {
        return ((double) lng) / 1E6;
    }
}
