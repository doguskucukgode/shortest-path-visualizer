package com.mex.shortestpath.projector;

import com.mex.shortestpath.model.City;
import com.mex.shortestpath.model.Edge;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PngCreator {

    private List<City> cities;
    private List<Edge> edges;
    private int width;
    private int height;

    public PngCreator(List<City> cities, List<Edge> edges, int width, int height) {
        this.cities = cities;
        this.edges = edges;
        this.width = width;
        this.height = height;
    }

    public void exportImage() throws IOException {
        BufferedImage bufferedImage = new BufferedImage(width,
                height,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g = bufferedImage.createGraphics();
        Map<RenderingHints.Key, Object> map = new HashMap<>();
        map.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        map.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        map.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        RenderingHints renderHints = new RenderingHints(map);
        g.setRenderingHints(renderHints);

        for (City city: cities) {
            g.setColor(Color.red);
            drawCenteredCircle(g, city.getAdjustedX(), city.getAdjustedY(), 10);
            g.setColor(Color.WHITE);
            g.drawString(city.getName(), city.getAdjustedX(), city.getAdjustedY());
        }
        for (Edge edge: edges) {
            g.setColor(Color.WHITE);
            g.drawLine(edge.getStart().getAdjustedX(), edge.getStart().getAdjustedY(),
                    edge.getEnd().getAdjustedX(), edge.getEnd().getAdjustedY());
        }
        // create the image file
        ImageIO.write(bufferedImage, "PNG", new File("output.png"));
    }

    public static void drawCenteredCircle(Graphics2D g, int x, int y, int r) {
        x = x-(r/2);
        y = y-(r/2);
        g.fillOval(x,y,r,r);
    }
}
