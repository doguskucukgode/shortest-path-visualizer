package com.mex.shortestpath.projector;

public class SphericalMercator extends  Mercator {

    private int mapWidth;
    private int mapHeight;

    public SphericalMercator(int mapWidth, int mapHeight) {
        this.mapHeight = mapHeight;
        this.mapHeight = mapHeight;
    }

    @Override
    double xAxisProjection(double input) {
        return Math.toRadians(input) * RADIUS_MAJOR;
    }

    @Override
    double yAxisProjection(double input) {
        return Math.log(Math.tan(Math.PI / 4 + Math.toRadians(input) / 2)) * RADIUS_MAJOR;
    }

    public double getXCoordinate(double latitude, double longitude) {
        return new Double((longitude+180) * (mapWidth/360));
    }

    public double getYCoordinate(double latitude, double longitude) {
        double latRad = latitude * Math.PI / 180;
        double mercN = Math.log(Math.tan((Math.PI/4)+(latRad/2)));
        return new Double((mapHeight/2)-(mapWidth*mercN/(2*Math.PI)));
    }
}
