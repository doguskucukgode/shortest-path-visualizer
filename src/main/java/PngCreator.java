import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PngCreator {

    public static void main(String[] args) throws IOException {

        int width = 500;
        int height = 500;

        // Constructs a BufferedImage of one of the predefined image types.
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Create a graphics which can be used to draw into the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();

        // fill all the image with white
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, width, height);

        // create a circle with black
        //g2d.setColor(Color.black);
        //g2d.fillOval(0, 0, width, height);

        // create a string with yellow
        g2d.setColor(Color.yellow);
        g2d.drawString("Java Code Geeks", 50, 120);
        // draw a line
        g2d.setColor(Color.blue);
        g2d.drawLine(50, 120, 100, 145);

        // Another line
        g2d.setColor(Color.red);
        g2d.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL)); // g2 is an instance of Graphics2D
        g2d.draw(new Line2D.Double(260, 330, 310, 355));

        // line with arrow
        LineArrow lineArrow = new LineArrow(260, 330, 310, 355, Color.PINK, 3);
        lineArrow.draw(g2d);

        // draw red point circle
        g2d.setColor(Color.RED);
        drawCenteredCircle(g2d, 260, 330, 10);


        // Disposes of this graphics context and releases any system resources that it is using.
        g2d.dispose();

        // Save as PNG
        File file = new File("myimage.png");
        ImageIO.write(bufferedImage, "png", file);


    }

    /**
     * Draw an arrow line between two points.
     *
     * @param g  the graphics component.
     * @param x1 x-position of first point.
     * @param y1 y-position of first point.
     * @param x2 x-position of second point.
     * @param y2 y-position of second point.
     * @param d  the width of the arrow.
     * @param h  the height of the arrow.
     */
    private static void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx * dx + dy * dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm * cos - ym * sin + x1;
        ym = xm * sin + ym * cos + y1;
        xm = x;

        x = xn * cos - yn * sin + x1;
        yn = xn * sin + yn * cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g.drawLine(x1, y1, x2, y2);

    }

    public static void drawCenteredCircle(Graphics2D g, int x, int y, int r) {
        x = x-(r/2);
        y = y-(r/2);
        g.fillOval(x,y,r,r);
    }
}
