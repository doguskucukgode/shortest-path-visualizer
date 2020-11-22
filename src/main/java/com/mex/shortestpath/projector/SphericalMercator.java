package com.mex.shortestpath.projector;

import com.mex.shortestpath.model.City;

import java.awt.geom.Point2D;

public class SphericalMercator {

    private final Point2D.Double minCoordinates = new Point2D.Double(-1, -1);
    private final Point2D.Double maxCoordinates = new Point2D.Double(-1, -1);

    public void transFormPixels(Iterable<City> cities) {
        // set minimum
        for (City city : cities) {
            // convert to radian
            double latitude = Math.toRadians(city.getLat());
            double longitude = Math.toRadians(city.getLng());

            Point2D.Double xy = new Point2D.Double(
                    longitude, // x
                    Math.log(Math.tan((Math.PI / 4.0) + 0.5 * latitude))); // y

            minCoordinates.x = (minCoordinates.x == -1) ? xy.x : Math.min(minCoordinates.x, xy.x);
            minCoordinates.y = (minCoordinates.y == -1) ? xy.y : Math.min(minCoordinates.y, xy.y);
            city.setImagePixels(xy);
        }
        // set maximum
        for (City city : cities) {
            Point2D.Double point = city.getImagePixels();
            point.x = point.x - minCoordinates.x;
            point.y = point.y - minCoordinates.y;

            // now, we need to keep track the max X and Y values
            maxCoordinates.x = (maxCoordinates.x == -1) ? point.x : Math.max(maxCoordinates.x, point.x);
            maxCoordinates.y = (maxCoordinates.y == -1) ? point.y : Math.max(maxCoordinates.y, point.y);
        }
    }

    public Point2D.Double getMinCoordinates() {
        return minCoordinates;
    }

    public Point2D.Double getMaxCoordinates() {
        return maxCoordinates;
    }
}
