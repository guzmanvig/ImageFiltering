package model;

public class Point2D {

  private double x;
  private double y;

  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public double calculateDistance(Point2D otherPoint) {
    return Math.sqrt(Math.pow(this.x - otherPoint.x, 2) + Math.pow(this.y - otherPoint.y, 2));
  }

  @Override
  public String toString() {
    return new StringBuilder()
        .append("(")
        .append(this.x)
        .append(", ")
        .append(this.y)
        .append(")")
        .toString();
  }

  @Override
  public int hashCode() {
    return this.toString().hashCode();
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
