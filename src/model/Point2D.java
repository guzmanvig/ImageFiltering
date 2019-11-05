package model;

/**
 * This class represents a Point2D that represents a point in a cartesian plane given two
 * coordinates: x and y.
 */
public class Point2D {

  private double x;
  private double y;

  /**
   * Constructor for Point 2D.
   *
   * @param x a double that represents the component of a point in the X axe.
   * @param y a double that represents the component of a point in the Y axe.
   */
  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Getter for the X coordinate.
   *
   * @return a double.
   */
  public double getX() {
    return x;
  }

  /**
   * Getter for the Y coordinate.
   *
   * @return a double.
   */
  public double getY() {
    return y;
  }

  /**
   * Calculate the distance between two points.
   *
   * @param otherPoint the point to which I want to calculate the distance.
   * @return a double.
   */
  public double calculateDistance(Point2D otherPoint) {
    return Math.sqrt(Math.pow(this.x - otherPoint.x, 2) + Math.pow(this.y - otherPoint.y, 2));
  }

  @Override
  public int hashCode() {
    return (int) this.x ^ (int) this.y;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof Point2D)) {
      return false;
    }

    Point2D other = (Point2D) o;
    return this.x == other.x && this.y == other.y;
  }
}
