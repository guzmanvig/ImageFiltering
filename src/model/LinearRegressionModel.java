package model;

import java.util.List;
import java.util.OptionalDouble;

public class LinearRegressionModel implements Model {

  private Line2D line;

  @Override
  public void calculate(List<Point2D> input) {
    int numberOfPoints = input.size();
    double sumOfX = input.stream().mapToDouble(p -> p.getX()).sum();
    double sumOfY = input.stream().mapToDouble(p -> p.getY()).sum();
    double avgX = sumOfX / numberOfPoints;
    double avgY = sumOfY / numberOfPoints;
    double syy = input.stream().mapToDouble(p -> Math.pow(p.getY() - avgY, 2)).sum();
    double sxx = input.stream().mapToDouble(p -> Math.pow(p.getX() - avgX, 2)).sum();
    double sxy = input.stream().mapToDouble(p -> (p.getX() - avgX) * (p.getY() - avgY)).sum();

    double d = (2 * sxy) / (sxx - syy);
    double theta = Math.atan(d);

    double t = ((syy - sxx) * Math.cos(theta) - 2 * sxy * Math.sin(theta)) > 0 ? theta :
        theta + Math.PI;

    double a = Math.cos(t / 2);
    double b = Math.sin(t / 2);
    double c = -a * avgX - b * avgY;

    OptionalDouble minX = input.stream().mapToDouble(p -> p.getX()).min();
    OptionalDouble maxX = input.stream().mapToDouble(p -> p.getX()).max();

    line = new Line2D(new Point2D(minX.getAsDouble(), (-a * minX.getAsDouble() - c) / b),
        new Point2D(maxX.getAsDouble(), (-a * maxX.getAsDouble() - c) / b));
  }

  @Override
  public List<List<Point2D>> getResultingPoints() {
    return null;
  }

  @Override
  public Line2D getResultingLine() {
    return line;
  }

}
