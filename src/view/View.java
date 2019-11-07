package view;

import java.awt.Color;
import java.io.IOException;

/**
 * Interface that represents a view in the Model View Controller design.
 */
public interface View {

  /**
   * Draw a point in a 2D plane.
   * @param x x coordinate of the point to draw.
   * @param y y coordinate of the point to draw.
   * @param rgb RGB color of the point to draw.
   */
  void drawPoint(double x, double y, Color rgb);

  /**
   * Draw a line from start to end in 2D plane.
   * @param xStart x coordinate of the start point of the line.
   * @param yStart y coordinate of the start point of the line.
   * @param xEnd x coordinate of the end point of the line.
   * @param yEnd y coordinate of the end point of the line.
   * @param rgb RGB color of the line to draw.
   */
  void drawLine(double xStart, double yStart, double xEnd, double yEnd, Color rgb);

  /**
   * Write the drawing to a file.
   * @param fileName name of the file where the drawing are to be written.
   * @throws IOException if the file cannot be created or written in.
   */
  void writeToFile(String fileName) throws IOException;

}
