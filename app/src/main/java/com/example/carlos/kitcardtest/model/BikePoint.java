package com.example.carlos.kitcardtest.model;

/**
 * Created by Carlos2 on 01/04/2016.
 */
public class BikePoint {

    private String name;
    private String id;
    private float lat;
    private float lon;
    private String docks;
    private String emptyDocks;
    private boolean locked = false;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getLat() {
        return this.lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return this.lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public String getEmptyDocks() {
        return this.emptyDocks;
    }

    public void setEmptyDocks(String emptyDocks) {
        this.emptyDocks = emptyDocks;
    }

    public boolean isLocked() {
        return this.locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getDocks() {
        return this.docks;
    }

    public void setDocks(String docks) {
        this.docks = docks;
    }
}
