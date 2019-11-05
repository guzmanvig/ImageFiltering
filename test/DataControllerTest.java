import static org.junit.Assert.*;

import controller.DataController;
import java.awt.Color;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import model.KClusteringModel;
import model.Line2D;
import model.Model;
import model.Point2D;
import org.junit.Before;
import org.junit.Test;
import view.ImagePlotterView;
import view.View;

/**
 * Tests for the DataController class.
 */
public class DataControllerTest {

  private MockModel mockModel;
  private MockView mockView;
  private final String mockFileName = "mock.png";

  // Mock model to be able to test if the controller passes the input to the model correctly,
  // and if the controller passes the output of the model to the view correctly.
  // For that, it stores the input in a public variable, and returns some fixed values
  // in its methods.

  private static class MockModel implements Model {

    List<Point2D> input;

    @Override
    public void calculate(List<Point2D> input) {
      this.input = input;
    }

    @Override
    public List<List<Point2D>> getResultingGroupOfPoints() {
      List<List<Point2D>> ret = new ArrayList<>();
      List<Point2D> firstMockList = new ArrayList<>();
      firstMockList.add(new Point2D(5, 6));
      firstMockList.add(new Point2D(7, 8));
      List<Point2D> secondMockList = new ArrayList<>();
      secondMockList.add(new Point2D(9, 10));
      ret.add(firstMockList);
      ret.add(secondMockList);
      return ret;
    }

    @Override
    public List<Point2D> getResultingPoints() {
      List<Point2D> mockList = new ArrayList<>();
      mockList.add(new Point2D(1, 2));
      mockList.add(new Point2D(3, 4));
      return mockList;
    }

    @Override
    public Line2D getResultingLine() {
      return new Line2D(new Point2D(1, 2), new Point2D(3, 4));
    }
  }

  // Mock view to test if the controller passes the output from the model to it correctly.
  // For that, it stores the input to the view in public variables so the test can access them.
  private static class MockView implements View {

    ArrayList<Point2D> pointsDrawn = new ArrayList<>();
    ArrayList<Point2D> lineDrawn = new ArrayList<>();

    @Override
    public void drawPoint(double x, double y, Color rgb) {
      pointsDrawn.add(new Point2D(x, y));
    }

    @Override
    public void drawLine(double xStart, double yStart, double xEnd, double yEnd, Color rgb) {
      lineDrawn.add(new Point2D(xStart, yStart));
      lineDrawn.add(new Point2D(xEnd, yEnd));
    }

    @Override
    public void writeToFile(String fileName) throws IOException {
    }
  }


  @Before
  public void setup() {
    mockView = new MockView();
    mockModel = new MockModel();

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullInput() {
    Model model = new KClusteringModel(1);
    new DataController(null, model, mockView);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    Reader in = new StringReader("");
    new DataController(in, null, mockView);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullView() {
    Reader in = new StringReader("");
    Model model = new KClusteringModel(1);
    new DataController(in, model, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrongInput0() {
    Readable input = new StringReader("1 2 3");
    Model model = new KClusteringModel(1);
    new DataController(input, model, mockView);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrongInput1() {
    Readable input = new StringReader("");
    Model model = new KClusteringModel(1);
    new DataController(input, model, mockView);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrongInput2() {
    Readable input = new StringReader("a");
    Model model = new KClusteringModel(1);
    new DataController(input, model, mockView);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrongInput3() {
    Readable input = new StringReader("a b \n");
    Model model = new KClusteringModel(1);
    new DataController(input, model, mockView);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrongInput4() {
    Readable input = new StringReader("5 \n");
    Model model = new KClusteringModel(1);
    new DataController(input, model, mockView);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrongInput5() {
    Readable input = new StringReader("5 6 5\n");
    Model model = new KClusteringModel(1);
    new DataController(input, model, mockView);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrongInput6() {
    Readable input = new StringReader("5 6 \n 1 \n");
    Model model = new KClusteringModel(1);
    new DataController(input, model, mockView);
  }

  @Test
  public void testOneInput() throws IOException {
    Readable input = new StringReader("1 2");
    DataController controller = new DataController(input, mockModel, mockView);
    controller.go(mockFileName);
    List<Point2D> points = mockModel.input;
    assertEquals(1, points.size());
    assertEquals(1, points.get(0).getX(), 0.1);
    assertEquals(2, points.get(0).getY(), 0.1);
  }

  @Test
  public void testTwoInputs() throws IOException {
    Readable input = new StringReader("1 2\n3 4");
    DataController controller = new DataController(input, mockModel, mockView);
    controller.go(mockFileName);
    List<Point2D> points = mockModel.input;
    assertEquals(2, points.size());
    assertEquals(1, points.get(0).getX(), 0.1);
    assertEquals(2, points.get(0).getY(), 0.1);
    assertEquals(3, points.get(1).getX(), 0.1);
    assertEquals(4, points.get(1).getY(), 0.1);
  }

  @Test
  public void testLineOutput() throws IOException {
    Readable input = new StringReader("1 2");
    DataController controller = new DataController(input, mockModel, mockView);
    controller.go(mockFileName);
    ArrayList<Point2D> mockLine = mockView.lineDrawn;
    // Check against the known values that the mock model return.
    assertEquals(2, mockLine.size());
    assertEquals(1, mockLine.get(0).getX(), 0.1);
    assertEquals(2, mockLine.get(0).getY(), 0.1);
    assertEquals(3, mockLine.get(1).getX(), 0.1);
    assertEquals(4, mockLine.get(1).getY(), 0.1);
  }

  @Test
  public void testPointsOutput() throws IOException {
    Readable input = new StringReader("1 2");
    DataController controller = new DataController(input, mockModel, mockView);
    controller.go(mockFileName);
    ArrayList<Point2D> mockPoints = mockView.pointsDrawn;
    // Check against the known values that the mock model return.
    assertEquals(5, mockPoints.size());
    assertEquals(1, mockPoints.get(0).getX(), 0.1);
    assertEquals(2, mockPoints.get(0).getY(), 0.1);
    assertEquals(3, mockPoints.get(1).getX(), 0.1);
    assertEquals(4, mockPoints.get(1).getY(), 0.1);
    assertEquals(5, mockPoints.get(2).getX(), 0.1);
    assertEquals(6, mockPoints.get(2).getY(), 0.1);
    assertEquals(7, mockPoints.get(3).getX(), 0.1);
    assertEquals(8, mockPoints.get(3).getY(), 0.1);
    assertEquals(9, mockPoints.get(4).getX(), 0.1);
    assertEquals(10, mockPoints.get(4).getY(), 0.1);
  }


}