package controller;

import java.util.ArrayList;
import java.util.List;
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

  private ArrayList<Point2D> getInputPoints(Readable input) {
    // TODO: parse input.
    return null;
  }



  @Override
  public void go() {
    // TODO: FIX COLORS:
    List<List<Line2D>> groupsOfLines = model.getResultingLines();
    for (List<Line2D> groupOfLines : groupsOfLines) {
      for (Line2D line : groupOfLines) {
        view.drawLine(line.getStart().getX(), line.getStart().getY(),
                      line.getEnd().getX(), line.getEnd().getY(),
                       new Color(0,0,1));
      }
    }

    List<List<Point2D>> groupsOfPoints = model.getResultingPoints();
    for (List<Point2D> groupOfPoints : groupsOfPoints) {
      for (Point2D point2D : groupOfPoints) {
        view.drawPoint(point2D.getX(), point2D.getY(),new Color(0,0,1));
      }
    }
  }
}
