
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import model.Line2D;
import model.LinearRegressionModel;
import model.Point2D;
import org.junit.Before;
import org.junit.Test;

public class LinearRegressionModelTest {

  private final double DELTA = 0.00001;
  LinearRegressionModel model;
  ArrayList<Point2D> listOfPoints;

  @Before
  public void setUp() {
    model = new LinearRegressionModel();
    listOfPoints = new ArrayList<>();
  }

  @Test
  public void testLinearRegressionWithTwoPointsAndPositiveSlope() {
    Point2D p0 = new Point2D(1, 1);
    Point2D p1 = new Point2D(-1, -1);
    listOfPoints.add(p0);
    listOfPoints.add(p1);

    model.calculate(listOfPoints);

    Line2D result = model.getResultingLine();
    assertEquals(-6, result.getStart().getX(), DELTA);
    assertEquals(-1, result.getStart().getY(), DELTA);
    assertEquals(6, result.getEnd().getX(), DELTA);
    assertEquals(1, result.getEnd().getY(), DELTA);

  }

  @Test
  public void testLinearRegressionWithTwoPointsAndNegativeSlope() {
    Point2D p0 = new Point2D(-1, 1);
    Point2D p1 = new Point2D(1, -1);
    listOfPoints.add(p0);
    listOfPoints.add(p1);

    model.calculate(listOfPoints);

    Line2D result = model.getResultingLine();
    assertEquals(-6, result.getStart().getX(), DELTA);
    assertEquals(1, result.getStart().getY(), DELTA);
    assertEquals(6, result.getEnd().getX(), DELTA);
    assertEquals(-1, result.getEnd().getY(), DELTA);

  }

  @Test
  public void testLinearRegressionWithTwoPointsAndNoSlope() {
    Point2D p0 = new Point2D(-1, 0);
    Point2D p1 = new Point2D(1, 0);
    listOfPoints.add(p0);
    listOfPoints.add(p1);

    model.calculate(listOfPoints);

    Line2D result = model.getResultingLine();
    assertEquals(-6, result.getStart().getX(), DELTA);
    assertEquals(0, result.getStart().getY(), DELTA);
    assertEquals(6, result.getEnd().getX(), DELTA);
    assertEquals(0, result.getEnd().getY(), DELTA);

  }

  @Test
  public void testLinearRegressionWithTwoPointsAndWithSameXCoordinate() {
    Point2D p0 = new Point2D(2, 5);
    Point2D p1 = new Point2D(2, -5);
    listOfPoints.add(p0);
    listOfPoints.add(p1);

    model.calculate(listOfPoints);

    Line2D result = model.getResultingLine();
    assertEquals(-3, result.getStart().getX(), DELTA);
    assertEquals(0, result.getStart().getY(), DELTA);
    assertEquals(7, result.getEnd().getX(), DELTA);
    assertEquals(0, result.getEnd().getY(), DELTA);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testLinearRegressionWithDuplicatedPoints() {
    Point2D p0 = new Point2D(-1, 1);
    Point2D p1 = new Point2D(-1, 1);;
    Point2D p2 = new Point2D(-1, 1);;
    listOfPoints.add(p0);
    listOfPoints.add(p1);
    listOfPoints.add(p2);

    model.calculate(listOfPoints);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLinearRegressionWithNullInput() {
    model.calculate(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLinearRegressionWithInputOfSizeZero() {
    model.calculate(listOfPoints);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLinearRegressionWithInputOfSizeOne() {
    Point2D p1 = new Point2D(1, 0);
    listOfPoints.add(p1);
    model.calculate(listOfPoints);
  }

}