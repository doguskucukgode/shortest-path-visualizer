package com.mex.shortestpath.projector;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

public class LineArrow {

    private final int x;
    private final int y;
    private final int endX;
    private final int endY;
    private final Color color;
    private final int thickness;

    public LineArrow(int x, int y, int x2, int y2, Color color, int thickness) {
        super();
        this.x = x;
        this.y = y;
        this.endX = x2;
        this.endY = y2;
        this.color = color;
        this.thickness = thickness;
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(color);
        g2.setStroke(new BasicStroke(thickness));
        g2.drawLine(x, y, endX, endY);;
        drawArrowHead(g2);
        g2.dispose();
    }

    private void drawArrowHead(Graphics2D g2) {

        Polygon arrowHead = new Polygon();
        AffineTransform tx = new AffineTransform();
        arrowHead.addPoint(0, 5);
        arrowHead.addPoint(-5, -5);
        arrowHead.addPoint(5, -5);

        tx.setToIdentity();
        double angle = Math.atan2(endY - y, endX - x);
        tx.translate(endX, endY);
        tx.rotate(angle - Math.PI / 2d);

        g2.setTransform(tx);
        g2.fill(arrowHead);
    }

}