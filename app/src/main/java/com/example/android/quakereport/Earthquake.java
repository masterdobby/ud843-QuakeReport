package com.example.android.quakereport;

public class Earthquake {

    private final String location;
    private final double magnitude;
    private final long timestamp;
    private String url;

    public Earthquake(String location, double magnitude, long timestamp, String url) {
        this.location = location;
        this.magnitude = magnitude;
        this.timestamp = timestamp;
        this.url = url;
    }

    public String getLocation() {
        return location;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getUrl() {
        return url;
    }
}
