package model;

import java.util.List;

/**
 * The Model Interface.
 */
public interface Model {

  /**
   * Given a certain input, the model executes a calculation on it.
   *
   * @param input a List of Points2D.
   */
  void calculate(List<Point2D> input);

  /**
   * Returns a list of group clusters, where each cluster is a group of points.
   *
   * @return List<List < Point2D>> a list of groups.
   */
  List<List<Point2D>> getResultingGroupOfPoints();

  /**
   * Returns a list of the resulting points after applying the calculation on the input.
   *
   * @return a List of Points2D.
   */
  List<Point2D> getResultingPoints();

  /**
   * Returns a line resulting after calculating the algorithm on the input.
   *
   * @return a Line2D.
   */
  Line2D getResultingLine();

}
