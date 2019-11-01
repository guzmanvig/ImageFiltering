package model;

import java.util.List;

public interface Model {

  void calculate(List<Point2D> input);

  List<List<Point2D>> getResultingPoints();

  Line2D getResultingLine();

}
