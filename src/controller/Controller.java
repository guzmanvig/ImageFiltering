package controller;

import java.io.IOException;

/**
 * Controller interface.
 */
public interface Controller {

  /**
   * Executes the controller.
   *
   * @param filePath the name of the file in which we are storing the figure.
   * @throws IOException when file cannot be written.
   */
  void execute(String filePath) throws IOException;
}
