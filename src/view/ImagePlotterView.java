package view;

import java.awt.Color;
import java.io.IOException;

public class ImagePlotterView implements View {

  ImagePlotter plotter;

  public ImagePlotterView() {
    plotter = new ImagePlotter();
    plotter.setWidth(400); //Params
    plotter.setHeight(400);
    plotter.setDimensions(-300,300,-350,350);
  }

  @Override
  public void drawPoint(double x, double y, Color rgb) {
    plotter.addPoint((int) x, (int) y, rgb);
  }

  @Override
  public void drawLine(double xStart, double yStart, double xEnd, double yEnd, Color rgb) {
    plotter.addLine((int) xStart, (int) yStart, (int) xEnd + 20, (int) yEnd, rgb);
  }

  @Override
  public void writeToFile(String fileName) throws IOException {
    plotter.write(fileName);
  }
}
