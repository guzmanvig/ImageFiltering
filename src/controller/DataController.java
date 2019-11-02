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

public class DataController implements Controller {

  private Model model;
  private View view;

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
      xCoordinate = Double.parseDouble(coordinates[0]);
      yCoordinate = Double.parseDouble(coordinates[1]);
      pointsList.add(new Point2D(xCoordinate, yCoordinate));
    }
    return pointsList;
  }


  @Override
  public void go() throws IOException {
    Random rgbGenerator = new Random();
    Line2D line = model.getResultingLine();
    if (line != null) {
      view.drawLine(line.getStart().getX(), line.getStart().getY(),
          line.getEnd().getX(), line.getEnd().getY(),
          Color.RED);
    }

    List<List<Point2D>> points = model.getResultingPoints();
    for (List<Point2D> pointGroup : points) {
      Color groupColor = new Color(rgbGenerator.nextFloat(), rgbGenerator.nextFloat(),
                                                                      rgbGenerator.nextFloat());
      for (Point2D point : pointGroup) {
        view.drawPoint(point.getX(), point.getY(), groupColor);
      }
    }

    view.writeToFile("linearRegression.png");

  }
}
