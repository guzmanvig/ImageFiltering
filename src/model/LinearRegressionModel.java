package model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * LinearRegressionModel class. It implements the Linear Regression algorithm.
 */
public class LinearRegressionModel implements Model {

  private final double LINE_MARGIN = 5;
  private Line2D line;
  private List<Point2D> listOfPoints;


  @Override
  public void calculate(List<Point2D> input) {

    if (input == null) {
      throw new IllegalArgumentException("The input cannot be null.");
    }

    List<Point2D> listWithNoDuplicates = removeDuplicates(input);
    this.listOfPoints = listWithNoDuplicates;

    if (this.listOfPoints.size() < 2) {
      throw new IllegalArgumentException("The input must contain more than 1 point.");
    }

    double avgX = input.stream().mapToDouble(p -> p.getX()).average().orElse(0);
    double avgY = input.stream().mapToDouble(p -> p.getY()).average().orElse(0);
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

    double minX = input.stream().mapToDouble(p -> p.getX()).min().orElse(0);
    double maxX = input.stream().mapToDouble(p -> p.getX()).max().orElse(0);

    double minXValued = b == 0 ? 0 : (-a * minX - c) / b;
    double maxXValued = b == 0 ? 0 : (-a * maxX - c) / b;

    line = new Line2D(
        new Point2D(minX - LINE_MARGIN, minXValued),
        new Point2D(maxX + LINE_MARGIN, maxXValued));
  }

  private static List<Point2D> removeDuplicates(List<Point2D> list) {

    Set<Point2D> set = new LinkedHashSet<>();
    List<Point2D> newList = new ArrayList<>();
    set.addAll(list);
    newList.addAll(set);
    return newList;
  }

  @Override
  public List<List<Point2D>> getResultingGroupOfPoints() {
    return null;
  }

  @Override
  public List<Point2D> getResultingPoints() {
    return this.listOfPoints;
  }

  @Override
  public Line2D getResultingLine() {
    return line;
  }

}
