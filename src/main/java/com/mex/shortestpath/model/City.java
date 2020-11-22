package com.mex.shortestpath.model;

import java.awt.geom.Point2D;

public class City {

    private String name;
    private Double lat;
    private Double lng;
    private Point2D.Double imagePixels;
    private int adjustedX;
    private int adjustedY;

    public City(String name, Double lat, Double lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Point2D.Double getImagePixels() {
        return imagePixels;
    }

    public void setImagePixels(Point2D.Double imagePixels) {
        this.imagePixels = imagePixels;
    }

    public int getAdjustedX() {
        return adjustedX;
    }

    public void setAdjustedX(int adjustedX) {
        this.adjustedX = adjustedX;
    }

    public int getAdjustedY() {
        return adjustedY;
    }

    public void setAdjustedY(int adjustedY) {
        this.adjustedY = adjustedY;
    }
}
