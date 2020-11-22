package com.mex.shortestpath.projector;

import com.mex.shortestpath.config.AppConfig;
import com.mex.shortestpath.model.City;

public class WidthHeightProjector {

    private int width;
    private int height;
    private SphericalMercator sphericalMercator;

    public WidthHeightProjector(int width, int height, SphericalMercator sphericalMercator) {
        this.width = width;
        this.height = height;
        this.sphericalMercator = sphericalMercator;
    }

    public void adjustPixels(Iterable<City> cities) {

        int paddingBothSides = AppConfig.DEFAULT_PADDING * 2;

        int mapWidth = width - paddingBothSides;
        int mapHeight = height - paddingBothSides;

        double mapWidthRatio = mapWidth / sphericalMercator.getMaxCoordinates().x;
        double mapHeightRatio = mapHeight / sphericalMercator.getMaxCoordinates().y;

        double globalRatio = Math.min(mapWidthRatio, mapHeightRatio);

        double heightPadding = (height - (globalRatio * sphericalMercator.getMaxCoordinates().y)) / 2;
        double widthPadding = (width - (globalRatio * sphericalMercator.getMaxCoordinates().x)) / 2;

        for (City city : cities) {
            city.setAdjustedX((int) (widthPadding + (city.getImagePixels().getX() * globalRatio)));
            city.setAdjustedY((int) (height - heightPadding - (city.getImagePixels().getY() * globalRatio)));
        }
    }

}
