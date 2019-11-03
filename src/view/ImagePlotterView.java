package view;

import java.awt.Color;
import java.io.IOException;

public class ImagePlotterView implements View {

  private ImagePlotter plotter;

  public static ImagePlotterViewBuilder getBuilder() {
    return new ImagePlotterViewBuilder();
  }

  private ImagePlotterView(int width, int height, int dimensionsXMin, int dimensionsXMax,
      int dimensionsYMin, int dimensionsYMax) {
    plotter = new ImagePlotter();
    plotter.setWidth(width); //Params
    plotter.setHeight(height);
    plotter.setDimensions(dimensionsXMin, dimensionsXMax, dimensionsYMin, dimensionsYMax);
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

  public static class ImagePlotterViewBuilder {

    private int width;
    private int height;
    private int dimensionsXMin;
    private int dimensionsXMax;
    private int dimensionsYMin;
    private int dimensionsYMax;

    private ImagePlotterViewBuilder() {
      this.width = 400;
      this.height = 400;
      this.dimensionsXMin = -300;
      this.dimensionsXMax = 300;
      this.dimensionsYMin = -350;
      this.dimensionsYMax = 350;
    }

    public ImagePlotterViewBuilder setWidth(int width) {
      this.width = width;
      return this;
    }

    public ImagePlotterViewBuilder setHeight(int height) {
      this.height = height;
      return this;
    }

    public ImagePlotterViewBuilder setDimensionsXMin(int dimensionsXMin) {
      this.dimensionsXMin = dimensionsXMin;
      return this;
    }

    public ImagePlotterViewBuilder setDimensionsXMax(int dimensionsXMax) {
      this.dimensionsXMax = dimensionsXMax;
      return this;
    }

    public ImagePlotterViewBuilder setDimensionsYMin(int dimensionsYMin) {
      this.dimensionsYMin = dimensionsYMin;
      return this;
    }

    public ImagePlotterViewBuilder setDimensionsYMax(int dimensionsYMax) {
      this.dimensionsYMax = dimensionsYMax;
      return this;
    }

    public ImagePlotterView build() {
      return new ImagePlotterView(width, height, dimensionsXMin, dimensionsXMax, dimensionsYMin,
          dimensionsYMax);
    }
  }
}
