package controller;

import java.io.IOException;

public interface Controller {

  /**
   * Executes the controller.
   *
   * @param filePath the name of the file in which we are storing the figure.
   * @throws IOException
   */
  void go(String filePath) throws IOException;
}
