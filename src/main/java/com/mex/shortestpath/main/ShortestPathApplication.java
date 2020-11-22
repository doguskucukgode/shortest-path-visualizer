package com.mex.shortestpath.main;

import com.mex.shortestpath.config.AppConfig;
import com.mex.shortestpath.exception.CityNotFoundException;
import com.mex.shortestpath.exception.OutOfRangeException;
import com.mex.shortestpath.input.FileParser;
import com.mex.shortestpath.input.RangeChecker;
import com.mex.shortestpath.model.City;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Main class to be run
 */
@Command(
        subcommands = {CommandLine.HelpCommand.class},
        description = "Shortest Path application visualizer")
public class ShortestPathApplication implements Runnable {

    public static final String WIDTH_WITH_RANGE = "width with range ";
    public static final String DEFAULT_IS = ", default is ";

    @CommandLine.Option(names = {"-w", "--width"}, description = WIDTH_WITH_RANGE +
            AppConfig.MIN_WIDTH_HEIGHT_AS_STRING + " to " + AppConfig.MAX_WIDTH_HEIGHT_AS_STRING +
            DEFAULT_IS + AppConfig.DEFAULT_WIDTH_AS_STRING,
            defaultValue = AppConfig.DEFAULT_WIDTH_AS_STRING)
    private Integer width;
    @CommandLine.Option(names = {"-h", "--height"}, description = WIDTH_WITH_RANGE +
            AppConfig.MIN_WIDTH_HEIGHT_AS_STRING + " to " + AppConfig.MAX_WIDTH_HEIGHT_AS_STRING +
            DEFAULT_IS + AppConfig.DEFAULT_HEIGHT_AS_STRING,
            defaultValue = AppConfig.DEFAULT_HEIGHT_AS_STRING)
    private Integer height;
    @CommandLine.Option(names = {"-s", "--start"}, description = "Starting point, must be declared in input file",
            required = true)
    private String start;
    @CommandLine.Option(names = {"-e", "--end"}, description = "End point, must be declared in input file",
            required = true)
    private String end;
    @CommandLine.Option(names = {"-i", "--inputFile"},
            description = "Path to input file, default value is: " + AppConfig.DEFAULT_INPUT_FILE
                    + ", file should be in root folder",
            defaultValue = AppConfig.DEFAULT_INPUT_FILE)
    private File inputFile;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new ShortestPathApplication()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        try {
            FileParser fileParser = new FileParser(inputFile);
            fileParser.parse();
            RangeChecker rangeChecker = new RangeChecker(width, height);
            rangeChecker.check();
            City startCity = fileParser.getCity(start);
            City endCity = fileParser.getCity(end);
            ShortestPathGenerator shortestPathGenerator = new ShortestPathGenerator(fileParser.getCities(),
                    fileParser.getEdges(), width, height, startCity, endCity);
            shortestPathGenerator.generate();
            MapService mapService = new MapService(fileParser.getCities());
            mapService.run();
        } catch (FileNotFoundException | CityNotFoundException | OutOfRangeException e) {
            System.err.println(e.getMessage());
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
