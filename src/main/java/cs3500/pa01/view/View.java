package cs3500.pa01.view;


/**
 * Class for handling the user view
 */
public class View {

  /**
   * The intro statement for the question mode
   *
   * @return the String intro
   */
  public String showPreface() {
    return "Hello! Welcome to your personal study tool. \n"
        + "Please provide a path to a .sr file containing the questions"
          + " you would like to study";
  }

  /**
   * Prompts the user enter a number of questions
   * they want to practice
   *
   * @return the String prompt
   */
  public String showQnumPrompt() {
    return "Now please enter the number of questions "
        + "you would like to practice";
  }
}
