package model;

import java.util.List;
import java.util.OptionalDouble;

public class LinearRegressionModel implements Model {

  private Line2D line;

  @Override
  public void calculate(List<Point2D> input) {
    int numberOfPoints = input.size();
    double sumOfX = input.stream().mapToDouble(p -> p.getX()).sum();
    System.out.println(sumOfX);
    double sumOfY = input.stream().mapToDouble(p -> p.getY()).sum();
    System.out.println(sumOfY);
    double avgX = sumOfX / numberOfPoints;
    System.out.println(avgX);
    double avgY = sumOfY / numberOfPoints;
    System.out.println(avgY);
    double syy = input.stream().mapToDouble(p -> Math.pow(p.getY() - avgY, 2)).sum();
    System.out.println(syy);
    double sxx = input.stream().mapToDouble(p -> Math.pow(p.getX() - avgX, 2)).sum();
    System.out.println(sxx);
    double sxy = input.stream().mapToDouble(p -> (p.getX() - avgX) * (p.getY() - avgY)).sum();
    System.out.println(sxy);

    double d = (2 * sxy) / (sxx - syy);
    System.out.println(d);
    double theta = Math.atan(d);
    System.out.println(theta);

    double t = ((syy - sxx) * Math.cos(theta) - 2 * sxy * Math.sin(theta)) > 0 ? theta :
        theta + Math.PI;
    System.out.println(t);

    double a = Math.cos(t / 2);
    System.out.println(a);
    double b = Math.sin(t / 2);
    System.out.println(b);
    double c = -a * avgX - b * avgY;
    System.out.println(c);

    OptionalDouble minX = input.stream().mapToDouble(p -> p.getX()).min();
    System.out.println(minX);
    OptionalDouble maxX = input.stream().mapToDouble(p -> p.getX()).max();
    System.out.println(maxX);

    line = new Line2D(new Point2D(minX.getAsDouble(), (-a / b) * minX.getAsDouble() + b),
        new Point2D(maxX.getAsDouble(), (-a / b) * maxX.getAsDouble() + b));

    System.out.println(line.getStart());
    System.out.println(line.getEnd());

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
