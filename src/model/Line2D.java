package model;

/**
 * Class that represents a line with a start point and an end point in a 2D plane.
 */
public class Line2D {

  private final Point2D start;
  private final Point2D end;

  /**
   * Cretes a line.
   * @param start the point where the line starts.
   * @param end the point where the line ends.
   */
  public Line2D(Point2D start, Point2D end) {
    this.start = start;
    this.end = end;
  }

  /**
   * Get the start point of the line.
   * @return the start point.
   */
  public Point2D getStart() {
    return start;
  }

  /**
   * Get the end point of the line.
   * @return the end point.
   */
  public Point2D getEnd() {
    return end;
  }

}
