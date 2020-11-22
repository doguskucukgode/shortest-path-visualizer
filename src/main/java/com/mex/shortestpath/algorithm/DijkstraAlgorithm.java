package com.mex.shortestpath.algorithm;

import com.mex.shortestpath.model.City;
import com.mex.shortestpath.model.Edge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class DijkstraAlgorithm {

    private final List<Edge> edges;
    private Set<City> settledNodes;
    private Set<City> unSettledNodes;
    private Map<City, City> predecessors;
    private Map<City, Double> distance;
    private boolean timeBasedPath = true;

    public DijkstraAlgorithm(List<Edge> edges, boolean timeBasedPath) {
        this.edges = new ArrayList<>(edges);
        this.timeBasedPath = timeBasedPath;
    }

    public void execute(City source) {
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
        distance.put(source, 0.0);
        unSettledNodes.add(source);
        while (!unSettledNodes.isEmpty()) {
            City node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(City node) {
        List<City> adjacentNodes = getNeighbors(node);
        for (City target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private double getDistance(City node, City target) {
        for (Edge edge : edges) {
            if (edge.getStart().equals(node)
                    && edge.getEnd().equals(target)) {
                if (timeBasedPath) {
                    return edge.getTime();
                } else {
                    return edge.getDistance();
                }
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<City> getNeighbors(City node) {
        List<City> neighbors = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.getStart().equals(node)
                    && !isSettled(edge.getEnd())) {
                neighbors.add(edge.getEnd());
            }
        }
        return neighbors;
    }

    private City getMinimum(Iterable<City> vertexes) {
        City minimum = null;
        for (City vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(City vertex) {
        return settledNodes.contains(vertex);
    }

    private Double getShortestDistance(City destination) {
        Double d = distance.get(destination);
        if (d == null) {
            return Double.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<City> getPath(City target) {
        LinkedList<City> path = new LinkedList<>();
        City step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

}
