package com.mex.shortestpath.model;

public class Edge {

    private City start;
    private City end;
    private Double distance;
    private Integer time;

    public Edge(City start, City end, Double distance, Integer time) {
        this.start = start;
        this.end = end;
        this.distance = distance;
        this.time = time;
    }

    public City getStart() {
        return start;
    }

    public void setStart(City start) {
        this.start = start;
    }

    public City getEnd() {
        return end;
    }

    public void setEnd(City end) {
        this.end = end;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
