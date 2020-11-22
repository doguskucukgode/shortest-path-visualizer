package com.mex.shortestpath.main;

import com.mex.shortestpath.algorithm.DijkstraAlgorithm;
import com.mex.shortestpath.model.City;
import com.mex.shortestpath.model.Edge;
import com.mex.shortestpath.projector.PngCreator;
import com.mex.shortestpath.projector.SphericalMercator;
import com.mex.shortestpath.projector.WidthHeightProjector;

import java.io.IOException;
import java.util.LinkedList;
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

    public void generate() throws IOException {
        SphericalMercator sphericalMercator = new SphericalMercator();
        sphericalMercator.transFormPixels(cities);
        WidthHeightProjector widthHeightProjector = new WidthHeightProjector(width, height, sphericalMercator);
        widthHeightProjector.adjustPixels(cities);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(cities, edges);
        dijkstra.execute(startCity);
        LinkedList<City> path = dijkstra.getPath(endCity);
        PngCreator pngCreator = new PngCreator(cities, edges, width, height, path);
        pngCreator.exportImage();
    }
}
