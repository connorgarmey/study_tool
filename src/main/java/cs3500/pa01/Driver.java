package cs3500.pa01;

import java.io.IOException;


/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   *
   * @param args  Directory location, ordering flag, new File location
   */
  public static void main(String[] args) {
    if (args.length == 3) {
      new StudyGuideController(args);
    } else {
      new QuestionController();
    }



  }
}