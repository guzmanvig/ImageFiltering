package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class KClusteringModel implements Model {

  private static final double THRESHOLD =  0.01;
  private static final int MAXIMUM_ITERATIONS = 100;
  private static final int RANSAC_ITERATIONS = 10;
  private final int k;
  private double error;
  private Map<Double, List<Cluster>> errorsAndClustersMap = new HashMap<>();

  public KClusteringModel(int k) {
    if (k <= 0) {
      throw new IllegalArgumentException("K must be greater than 0");
    }
    this.k = k;
  }

  @Override
  public void calculate(List<Point2D> input) {

  }

  @Override
  public List<List<Point2D>> getResultingPoints() {
    return null;
  }

  @Override
  public List<List<Line2D>> getResultingLines() {
    return null;
  }

  // TODO: make it void and return the correct thing
  public List<Cluster> calculateTest(List<Point2D> input) {

    for (int i = 0; i < RANSAC_ITERATIONS; i++) {

      error = Double.MAX_VALUE;

      List<Cluster> clusters = getKRandomClusters(input);

      addPointsToClusterAndRecalculateCenter(input, clusters, 0);

      errorsAndClustersMap.put(error, clusters);

    }

    Set<Double> allErrors = errorsAndClustersMap.keySet();
    Double minError = Collections.min(allErrors);

    List<Cluster> bestClusters = errorsAndClustersMap.get(minError);
    return bestClusters;

  }

  private List<Cluster> getKRandomClusters(List<Point2D> input) {
    List<Cluster> randomClusters = new ArrayList<>();
    Random randomIndex = new Random();
    for (int i = 0; i < k; i++) {
      Point2D c = input.get(randomIndex.nextInt(input.size()));
      randomClusters.add(new Cluster(c));
    }
    return randomClusters;
  }

  private void addPointsToClusterAndRecalculateCenter(List<Point2D> input, List<Cluster> clusters, int iterations) {
    if (iterations < MAXIMUM_ITERATIONS ) {

      // Clear the clusters.
      clearClusters(clusters);

      // Add points to the cluster with minimum distance.
      addPointsToCluster(input, clusters);

      // Recalculate the center of clusters.
      recalculateCenterOfClusters(clusters);

      // Calculate the new error as the average of distances to the new centers.
      double distancesSum = 0;
      for (Cluster cluster: clusters) {
        distancesSum = distancesSum + cluster.getAverageDistanceToCenter();
      }
      double newError = distancesSum / clusters.size();

      // Repeat the process until the new error is less than the error.
      double percentageError = Math.abs(newError - error) / error;
      if (percentageError < THRESHOLD) {
        error = newError;
        addPointsToClusterAndRecalculateCenter(input, clusters, iterations + 1);
      }

    }
  }

  private void clearClusters(List<Cluster> clusters) {
    for (Cluster cluster : clusters) {
      cluster.clear();
    }
  }

  private void addPointsToCluster(List<Point2D> input, List<Cluster> clusters) {
    // Put all the input points inside the correspondent cluster.
    for (Point2D currentPoint : input) {
      Cluster minCluster = clusters.get(0);
      double minDistance = minCluster.getCenter().calculateDistance(currentPoint);
      // Find the cluster with the minimum distance to the center
      for (int j = 1; j < clusters.size(); j++) {
        Cluster currentCluster = clusters.get(j);
        double distance = currentCluster.getCenter().calculateDistance(currentPoint);
        if (distance < minDistance) {
          minDistance = distance;
          minCluster = currentCluster;
        }
      }
      // Add the point to the cluster.
      minCluster.addPoint(currentPoint);
    }
  }

  private void recalculateCenterOfClusters(List<Cluster> clusters) {
    for (Cluster cluster : clusters) {
      cluster.recalculateCenter();
    }
  }

  // TODO: make private
  public static class Cluster {
    private Point2D center;
    private List<Point2D> points;

    Cluster(Point2D center) {
      this.center = center;
      points = new ArrayList<>();
    }

    void addPoint(Point2D point2D) {
      points.add(point2D);
    }

    Point2D getCenter() {
      return center;
    }

    void clear() {
      points.clear();
    }

    void recalculateCenter() {
      double xSum = 0;
      double ySum = 0;
      for (Point2D point : points) {
        xSum = xSum + point.getX();
        ySum = ySum + point.getY();
      }
      double averageX = xSum / points.size();
      double averageY = ySum / points.size();
      center.setX(averageX);
      center.setY(averageY);
    }

    double getAverageDistanceToCenter() {
      double distanceSum = 0;
      for (Point2D point : points) {
        distanceSum = distanceSum + center.calculateDistance(point);
      }
      return distanceSum / points.size();
    }


   }


}
