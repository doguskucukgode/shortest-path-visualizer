package com.mex.shortestpath.projector;

import org.junit.jupiter.api.Test;

public class SphericalMercatorTest {

    private SphericalMercator sphericalMercator = new SphericalMercator(1000, 600);
    private Double SAMPLE_LAT1 = 46.099482;
    private Double SAMPLE_LNG1 = 19.670069;
    private Double SAMPLE_LAT2 = 44.878964;
    private Double SAMPLE_LNG2 = 20.657296;

    @Test
    void checkCoordinates() {
        System.out.print(sphericalMercator.getXCoordinate(SAMPLE_LAT1, SAMPLE_LNG1));
        System.out.print(" - ");
        System.out.println(sphericalMercator.getYCoordinate(SAMPLE_LAT1, SAMPLE_LNG1));
        System.out.println("-----");
        System.out.print(sphericalMercator.getXCoordinate(SAMPLE_LAT2, SAMPLE_LNG2));
        System.out.print(" - ");
        System.out.println(sphericalMercator.getYCoordinate(SAMPLE_LAT2, SAMPLE_LNG2));
    }

}
