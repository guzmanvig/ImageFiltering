package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * This class is a plotter of data onto an image. It provides operations to add points, lines and
 * circles to draw on the image. It is also possible to set the size of the image to be created,
 * along with the range of the data that is provided to it.
 */
public class ImagePlotter {

  private List<Integer> points;
  private List<java.awt.Color> pointColors;
  private List<Integer> lines;
  private List<java.awt.Color> lineColors;
  private List<Integer> circles;
  private List<java.awt.Color> circleColors;
  private int xmin;
  private int xmax;
  private int ymin;
  private int ymax;
  private final int pointSize;
  private int width;
  private int height;

  /**
   * Default constructor of ImagePlotter.
   */
  public ImagePlotter() {
    reset();
    pointSize = 3;
    width = height = 500;
  }

  /**
   * Add a point to be drawn on the image.
   *
   * @param x the x coordinate of the point we are drawing.
   * @param y the y coordinate of the point we are drawing.
   */
  public void addPoint(int x, int y) {
    points.add(x);
    points.add(y);
    pointColors.add(java.awt.Color.BLACK);
  }

  /**
   * Add a point to be drawn on the image with the specific color.
   *
   * @param x   the x coordinate of the point we are drawing.
   * @param y   the y coordinate of the point we are drawing.
   * @param col the color we are using to draw the point.
   */
  public void addPoint(int x, int y, java.awt.Color col) {
    points.add(x);
    points.add(y);
    pointColors.add(col);
  }

  /**
   * Add a line to be drawn on the image.
   *
   * @param x1 a number representing the x coordinate of the first point of the line we are
   *           drawing.
   * @param y1 a number representing the y coordinate of the first point of the line we are
   *           drawing.
   * @param x2 a number representing the x coordinate of the second point of the line we are
   *           drawing.
   * @param y2 a number representing the y coordinate of the second point of the line we are
   *           drawing.
   */
  public void addLine(int x1, int y1, int x2, int y2) {
    lines.add(x1);
    lines.add(y1);
    lines.add(x2);
    lines.add(y2);
    lineColors.add(java.awt.Color.RED);
  }

  /**
   * Add a line to be drawn on to the image with the specified color.
   *
   * @param x1  a number representing the x coordinate of the first point of the line we are
   *            drawing.
   * @param y1  a number representing the y coordinate of the first point of the line we are
   *            drawing.
   * @param x2  a number representing the x coordinate of the second point of the line we are
   *            drawing.
   * @param y2  a number representing the y coordinate of the second point of the line we are
   *            drawing.
   * @param col a Color that represents the color of the line we are drawing.
   */
  public void addLine(int x1, int y1, int x2, int y2, java.awt.Color col) {
    lines.add(x1);
    lines.add(y1);
    lines.add(x2);
    lines.add(y2);
    lineColors.add(col);
  }

  /**
   * Add a circle to be drawn on to the image.
   *
   * @param x      the x coordinate of the center of the circle we are drawing.
   * @param y      the y coordinate of the center of the circle we are drawing.
   * @param radius the radius of the circle we are drawing.
   */
  public void addCircle(int x, int y, int radius) {
    circles.add(x);
    circles.add(y);
    circles.add(radius);
    circleColors.add(java.awt.Color.GREEN);
  }

  /**
   * Add a circle to be drawn on to the image with the specified color.
   *
   * @param x      the x coordinate of the center of the circle we are drawing.
   * @param y      the y coordinate of the center of the circle we are drawing.
   * @param radius the radius of the circle we are drawing.
   * @param col    a Color we are using to set the color of the circle.
   */
  public void addCircle(int x, int y, int radius, java.awt.Color col) {
    circles.add(x);
    circles.add(y);
    circles.add(radius);
    circleColors.add(col);
  }

  /**
   * Set the range in which all the added points, circles and lines lie. This provides the range of
   * the data as added to this plotter.
   *
   * @param xmin the minimum x point of the area we are drawing.
   * @param xmax the maximum x point of the area we are drawing.
   * @param ymin the minimum y point of the area we are drawing.
   * @param ymax the maximum y point of the area we are drawing.
   */
  public void setDimensions(int xmin, int xmax, int ymin, int ymax) {
    this.xmin = xmin;
    this.xmax = xmax;
    this.ymin = ymin;
    this.ymax = ymax;

    //modify it to retain aspect ratio
    double aspectRatio = (double) width / height;
    double w = xmax - xmin;
    double h = ymax - ymin;
    if (h * aspectRatio < w) {
      h = w / aspectRatio;
    } else {
      w = h * aspectRatio;
    }
    this.xmin = (int) (0.5 * (xmin + xmax) - 0.5 * w);
    this.xmax = (int) (0.5 * (xmin + xmax) + 0.5 * w);
    this.ymin = (int) (0.5 * (ymin + ymax) - 0.5 * h);
    this.ymax = (int) (0.5 * (ymin + ymax) + 0.5 * h);

  }

  /**
   * Draw all the shapes added thus far to an image and save it to the specific path.
   *
   * @param path a file path for the image file we are generating.
   * @throws IOException when file throws a I/O exception.
   */
  public void write(String path) throws IOException {
    BufferedImage image = new BufferedImage(width, height, BufferedImage
        .TYPE_INT_ARGB);

    Graphics2D g2d = (Graphics2D) image.getGraphics();

    g2d.setColor(java.awt.Color.WHITE);
    g2d.fillRect(0, 0, width, height);
    AffineTransform mat = new AffineTransform();
    mat.concatenate(AffineTransform.getTranslateInstance(0, this.height));
    mat.concatenate(AffineTransform.getScaleInstance(1, -1));

    mat.concatenate(AffineTransform
        .getScaleInstance(
            (double) this.width / (xmax - xmin),
            (double) this.height / (ymax - ymin)));
    mat.concatenate(
        AffineTransform.getTranslateInstance(-xmin, -ymin));

    g2d.setTransform(mat);

    for (int i = 0; i < points.size(); i += 2) {
      g2d.setColor(pointColors.get(i / 2));
      g2d.fillOval(points.get(i) - pointSize, points.get(i + 1) - pointSize,
          2 * pointSize, 2 * pointSize);
    }

    for (int i = 0; i < lines.size(); i += 4) {
      g2d.setColor(lineColors.get(i / 4));
      g2d.drawLine(lines.get(i), lines.get(i + 1), lines.get(i + 2), lines.get(i + 3));
    }

    for (int i = 0; i < circles.size(); i += 3) {
      int size = circles.get(i + 2);
      g2d.setColor(circleColors.get(i / 3));
      g2d.drawOval(circles.get(i) - size, circles.get(i + 1) - size, 2 * size,
          2 * size);
    }

    String imageformat = path.substring(path.indexOf(".") + 1);
    ImageIO.write(
        image,
        imageformat,
        new FileOutputStream(path));

  }

  /**
   * Reset this plotter. All shapes are deleted as a result of resetting.
   */
  public void reset() {
    points = new ArrayList<Integer>();
    lines = new ArrayList<Integer>();
    circles = new ArrayList<Integer>();
    pointColors = new ArrayList<java.awt.Color>();
    lineColors = new ArrayList<java.awt.Color>();
    circleColors = new ArrayList<Color>();
  }

  /**
   * Set the width of the image that is created by this plotter.
   *
   * @param w the width of the image.
   */
  public void setWidth(int w) {
    width = w;
  }

  /**
   * Set the height of the image that is created by this plotter.
   *
   * @param h the height of the image.
   */

  public void setHeight(int h) {
    height = h;
  }
}
