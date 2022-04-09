package io.weatherAPI.models;

import com.google.gson.annotations.Expose;

public class Result {

    @Expose
    private String lat = "";

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}

