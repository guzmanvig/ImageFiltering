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

public class Main {

  public static void main(String[] args) throws IOException {

    Model model = new KClusteringModel(3);
    View view = new ImagePlotterView();
    Controller controller = new DataController(new FileReader(new File("data/clusterdata-6.txt")),
        model,
        view);

    controller.go();

  }

}
