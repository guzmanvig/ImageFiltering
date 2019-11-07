package controller;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import model.Line2D;
import model.Model;
import model.Point2D;
import view.View;

/**
 * Class that implements the Controller interface and creates and assigns the different algorithms
 * models and views.
 */
public class DataController implements Controller {

  private Model model;
  private View view;

  /**
   * Constructor for creating an instance of DataController.
   *
   * @param input a Readable object that represents the input of the controller.
   * @param model a Model object of our MVC.
   * @param view  a View object of our MVC.
   */
  public DataController(Readable input, Model model, View view) {
    if (input == null || model == null || view == null) {
      throw new IllegalArgumentException("Arguments must be not null");
    }
    this.model = model;
    this.view = view;
    List<Point2D> listOfPoints = getInputPoints(input);
    model.calculate(listOfPoints);
  }

  private List<Point2D> getInputPoints(Readable input) {
    Scanner scanner = new Scanner(input);
    List<Point2D> pointsList = new ArrayList<>();
    double xCoordinate;
    double yCoordinate;
    String line;
    String[] coordinates;
    while (scanner.hasNextLine()) {
      line = scanner.nextLine();
      coordinates = line.split("\\s");
      if (coordinates.length != 2) {
        throw new IllegalArgumentException("Wrong format of input file.");
      }
      xCoordinate = Double.parseDouble(coordinates[0]);
      yCoordinate = Double.parseDouble(coordinates[1]);
      pointsList.add(new Point2D(xCoordinate, yCoordinate));
    }
    return pointsList;
  }


  @Override
  public void execute(String fileName) throws IOException {
    Random rgbGenerator = new Random();
    Line2D line = model.getResultingLine();
    if (line != null) {
      view.drawLine(line.getStart().getX(), line.getStart().getY(),
          line.getEnd().getX(), line.getEnd().getY(),
          Color.RED);
    }

    List<Point2D> points = model.getResultingPoints();
    if (points != null) {
      for (Point2D point : points) {
        view.drawPoint(point.getX(), point.getY(), Color.BLACK);
      }
    }

    List<List<Point2D>> groupOfPoints = model.getResultingGroupOfPoints();
    if (groupOfPoints != null) {
      for (List<Point2D> pointGroup : groupOfPoints) {
        Color groupColor = new Color(rgbGenerator.nextFloat(), rgbGenerator.nextFloat(),
            rgbGenerator.nextFloat());
        for (Point2D point : pointGroup) {
          view.drawPoint(point.getX(), point.getY(), groupColor);
        }
      }
    }

    view.writeToFile(fileName);

  }
}
