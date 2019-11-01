package controller;

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

  public DataController(Readable input, Model model, View view) {
    this.model = model;
    this.view = view;
    model.calculate(getInputPoints(input));
  }

  private List<Point2D> getInputPoints(Readable input) {
    Scanner scanner = new Scanner(input);
    List<Point2D> pointsList = new ArrayList<>();
    Double xCoordinate;
    Double yCoordinate;
    while (scanner.hasNextLine()) {
      scanner.nextLine();
      xCoordinate = scanner.nextDouble();
      yCoordinate = scanner.nextDouble();
      pointsList.add(new Point2D(xCoordinate, yCoordinate));
    }
    return pointsList;
  }


  @Override
  public void go() {
    // TODO: FIX COLORS:
    Line2D line = model.getResultingLine();
    view.drawLine(line.getStart().getX(), line.getStart().getY(),
        line.getEnd().getX(), line.getEnd().getY(),
        new Color(0, 0, 1));

    List<List<Point2D>> groupsOfPoints = model.getResultingPoints();
    for (List<Point2D> groupOfPoints : groupsOfPoints) {
      for (Point2D point2D : groupOfPoints) {
        view.drawPoint(point2D.getX(), point2D.getY(), new Color(0, 0, 1));
      }
    }
  }
}
