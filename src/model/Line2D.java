package model;

public class Line2D {

  private final Point2D start;
  private final Point2D end;

  public Line2D(Point2D start, Point2D end) {
    this.start = start;
    this.end = end;
  }

  public Point2D getStart() {
    return start;
  }

  public Point2D getEnd() {
    return end;
  }

}
