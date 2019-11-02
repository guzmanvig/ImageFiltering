package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Line2D;
import model.Model;
import model.Point2D;
import view.Color;
import view.View;

public class DataController implements Controller {

  private Model model;
  private View view;
  private List<Point2D> listOfPoints;

  public DataController(Readable input, Model model, View view) {
    this.model = model;
    this.view = view;
    listOfPoints = getInputPoints(input);
    model.calculate(listOfPoints);
  }

  private List<Point2D> getInputPoints(Readable input) {
    Scanner scanner = new Scanner(input);
    List<Point2D> pointsList = new ArrayList<>();
    Double xCoordinate;
    Double yCoordinate;
    String line;
    String coordinates[];
    while (scanner.hasNextLine()) {
      line = scanner.nextLine();
      coordinates = line.split("\\s");
      xCoordinate = Double.parseDouble(coordinates[0]);
      yCoordinate = Double.parseDouble(coordinates[1]);
      System.out.println(yCoordinate);
      pointsList.add(new Point2D(xCoordinate, yCoordinate));
    }
    return pointsList;
  }


  @Override
  public void go() throws IOException {
    // TODO: FIX COLORS:
    Line2D line = model.getResultingLine();
    if (line != null) {
      view.drawLine(line.getStart().getX(), line.getStart().getY(),
          line.getEnd().getX(), line.getEnd().getY(),
          new Color(0, 0, 1));

      for (Point2D point :
          listOfPoints) {
        view.drawPoint(point.getX(), point.getY(),
            new Color(0, 0, 1));
      }

      view.writeToFile("linearRegression.png");
    }

//    List<List<Point2D>> groupsOfPoints = model.getResultingPoints();
//    for (List<Point2D> groupOfPoints : groupsOfPoints) {
//      for (Point2D point2D : groupOfPoints) {
//        view.drawPoint(point2D.getX(), point2D.getY(), new Color(0, 0, 1));
//      }
//    }
  }
}
