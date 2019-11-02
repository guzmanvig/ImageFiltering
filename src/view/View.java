package view;

import java.io.IOException;

public interface View {

  void drawPoint(double x, double y, Color rgb);

  void drawLine(double xStart, double yStart, double xEnd, double yEnd, Color rgb);

  void writeToFile(String fileName) throws IOException;

}
