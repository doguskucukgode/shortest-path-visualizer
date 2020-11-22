package com.mex.shortestpath.input;

import com.mex.shortestpath.exception.CityNotFoundException;
import com.mex.shortestpath.model.City;
import com.mex.shortestpath.model.Edge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileParser {

    private static final String JUNCTIONS = "[junctions]";
    private static final String EDGES = "[edges]";
    private static final String EDGE = "edge";
    private static final String JUNCTION = "junction";
    private File inputFile;
    private List<City> cities;
    private List<Edge> edges;

    public List<City> getCities() {
        return cities;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public FileParser(File inputFile) {
        this.inputFile = inputFile;
    }

    public void parse() throws CityNotFoundException, FileNotFoundException {
        cities = new ArrayList<>();
        edges = new ArrayList<>();
        Scanner myReader = new Scanner(inputFile);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            if (data.equals(JUNCTIONS) || data.equals(EDGES)) {
                // ignore other line
                myReader.nextLine();
            }
            if (data.indexOf(JUNCTION) == 0) {
                String[] columns = split(data);
                cities.add(new City(columns[0].trim(),
                        Double.parseDouble(columns[1].trim()),
                        Double.parseDouble(columns[2].trim())));
            } else if (data.indexOf(EDGE) == 0) {
                String[] columns = split(data);
                City start = getCity(columns[0].trim());
                City end = getCity(columns[1].trim());
                edges.add(new Edge(start, end,
                        Double.parseDouble(columns[2].trim()),
                        Integer.parseInt(columns[3].trim())));
            }
        }
        myReader.close();
    }

    private String[] split(String data) {
        return data.substring(data.indexOf("=") + 1).split(",");
    }

    public City getCity(String name) throws CityNotFoundException {
        City city = cities.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElse(null);
        if (city == null) {
            throw new CityNotFoundException(String.format("city with name %s not found", name));
        }
        return city;
    }
}
