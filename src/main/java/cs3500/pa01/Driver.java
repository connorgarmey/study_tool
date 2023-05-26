package cs3500.pa01;

import cs3500.pa01.controller.QuestionController;
import cs3500.pa01.controller.StudyGuideController;
import java.util.Scanner;


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
      Scanner scan = new Scanner(System.in);
      new QuestionController(scan, System.out);
    }



  }
}