import controller.Controller;
import controller.DataController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import model.KClusteringModel;
import model.LinearRegressionModel;
import model.Model;
import view.ImagePlotterView;
import view.View;

/**
 * Main class that creates two png file with each algorithm and different outputs.
 */
public class Main {

  /**
   * Main method.
   *
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {

    Model model = new LinearRegressionModel();
    View view = ImagePlotterView.getBuilder()
        .setHeight(200)
        .setWidth(200)
        .setDimensionsXMin(-400)
        .setDimensionsXMax(400)
        .setDimensionsYMin(-400)
        .setDimensionsYMax(400)
        .build();
    Controller controller = new DataController(new FileReader(new File("data/linedata-1.txt")),
        model,
        view);

    controller.go("linedata-1.png");

  }

}
