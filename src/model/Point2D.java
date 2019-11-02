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

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
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
}
