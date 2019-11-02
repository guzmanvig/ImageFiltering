
import java.util.ArrayList;
import model.LinearRegressionModel;
import model.Point2D;
import org.junit.Test;

public class LinearRegressionModelTest {

  @Test
  public void testLinearRegression() {
    // TODO: use interface.
    LinearRegressionModel model = new LinearRegressionModel();
    ArrayList<Point2D> list = new ArrayList<>();

    Point2D p0 = new Point2D(-5, 6);
    Point2D p1 = new Point2D(-3, -2);
    Point2D p2 = new Point2D(2, 2);
    Point2D p3 = new Point2D(6, -8);
    list.add(p0);
    list.add(p1);
    list.add(p2);
    list.add(p3);

    model.calculate(list);

  }

}