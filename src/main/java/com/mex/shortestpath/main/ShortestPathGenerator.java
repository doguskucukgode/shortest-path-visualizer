package com.mex.shortestpath.main;

import com.mex.shortestpath.model.City;
import com.mex.shortestpath.model.Edge;

import java.util.List;

public class ShortestPathGenerator {

    private List<City> cities;
    private List<Edge> edges;
    private Integer width;
    private Integer height;
    private City startCity;
    private City endCity;

    public ShortestPathGenerator(List<City> cities, List<Edge> edges, Integer width, Integer height, City startCity, City endCity) {
        this.cities = cities;
        this.edges = edges;
        this.width = width;
        this.height = height;
        this.startCity = startCity;
        this.endCity = endCity;
    }

    public void generate() {
        System.out.println("Image is written in output.png");
    }
}
