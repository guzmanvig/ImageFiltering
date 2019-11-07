package view;

import java.awt.Color;
import java.io.IOException;

/**
 * Implementation of a View. It uses the ImagePlotter class to draw into a file.
 */
public class ImagePlotterView implements View {

  private ImagePlotter plotter;

  /**
   * Get a builder to create an ImagePlotter.
   * @return the builder.
   */
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

  /**
   * Builder for an ImagePlotterView. It allows to create an ImagePlotterView and set parameters
   * to it.
   */
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

    /**
     * Sets the width of the image to be built.
     * @param width width of the image in pixels.
     * @return the builder.
     */
    public ImagePlotterViewBuilder setWidth(int width) {
      this.width = width;
      return this;
    }

    /**
     * Sets the height of the image to be built.
     * @param height width of the image in pixels.
     * @return the builder.
     */
    public ImagePlotterViewBuilder setHeight(int height) {
      this.height = height;
      return this;
    }

    /**
     * Sets the min range in x of the data that will be provided to the image.
     * @param dimensionsXMin min x range.
     * @return the builder.
     */
    public ImagePlotterViewBuilder setDimensionsXMin(int dimensionsXMin) {
      this.dimensionsXMin = dimensionsXMin;
      return this;
    }

    /**
     * Sets the max range in x of the data that will be provided to the image.
     * @param dimensionsXMax min x range.
     * @return the builder.
     */
    public ImagePlotterViewBuilder setDimensionsXMax(int dimensionsXMax) {
      this.dimensionsXMax = dimensionsXMax;
      return this;
    }

    /**
     * Sets the min range in y of the data that will be provided to the image.
     * @param dimensionsYMin min y range.
     * @return the builder.
     */
    public ImagePlotterViewBuilder setDimensionsYMin(int dimensionsYMin) {
      this.dimensionsYMin = dimensionsYMin;
      return this;
    }

    /**
     * Sets the max range in y of the data that will be provided to the image.
     * @param dimensionsYMax max y range.
     * @return the builder.
     */
    public ImagePlotterViewBuilder setDimensionsYMax(int dimensionsYMax) {
      this.dimensionsYMax = dimensionsYMax;
      return this;
    }


    /**
     * Creates the ImagePlotterView with the previously set parameters.
     * @return the created ImagePlotterView.
     */
    public ImagePlotterView build() {
      return new ImagePlotterView(width, height, dimensionsXMin, dimensionsXMax, dimensionsYMin,
          dimensionsYMax);
    }
  }
}
