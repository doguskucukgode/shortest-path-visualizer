# Shortest path visualizer
Finds the shortest path (Dijkstra's algorithm) between the two given locations in terms of distance or time. Exports map as png format and visializes route. Input file should be located on root folder. Image is exported on root folder as `output.png` 

## Maven
### Running with default options
```
mvn exec:java -Dexec.mainClass="com.mex.shortestpath.main.ShortestPathApplication"  -Dexec.args="-s Subotica -e Zrenjanin -i ./cities.dat"
```
### Running with different width height
```
mvn exec:java -Dexec.mainClass="com.mex.shortestpath.main.ShortestPathApplication"  -Dexec.args="-s Subotica -e Zrenjanin -i ./cities.dat -w 1000 -h 1000"
```
### Running with time based path
```
mvn exec:java -Dexec.mainClass="com.mex.shortestpath.main.ShortestPathApplication"  -Dexec.args="-s Subotica -e Zrenjanin -i ./cities.dat -w 1000 -h 1000 --time-based-path"
```

## Help screen for application
```
Usage: <main class> [--time-based-path] -e=<end> [-h=<height>] [-i=<inputFile>]
                    -s=<start> [-w=<width>] [COMMAND]
Shortest Path application visualizer
  -e, --end=<end>         End point, must be declared in input file
  -h, --height=<height>   width with range 100 to 1000, default is 600
  -i, --inputFile=<inputFile>
                          Path to input file, default value is: cities.dat,
                            file should be in root folder
  -s, --start=<start>     Starting point, must be declared in input file
      --time-based-path   Default path is generated by distance if not used
                            that option
  -w, --width=<width>     width with range 100 to 1000, default is 1000
Commands:
  help  Displays help information about the specified command
```

