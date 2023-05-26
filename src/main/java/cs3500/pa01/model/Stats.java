package cs3500.pa01.model;

import static cs3500.pa01.model.Difficulty.HARD;

import java.util.ArrayList;

/**
 * The Stats class computes statistics relating to the
 * user's inputs into the question study mode
 */
public class Stats {
  private int totalAnswered;
  private int easyToHardQs;
  private int hardToEasyQs;
  private int endHardCount;
  private int endEasyCount;



  /**
   * Adds to the counts of changed difficulty if a question's
   * difficulty field is changed
   *
   * @param diff the difficulty the question was changed to
   */
  public void addChange(Difficulty diff) {
    if (diff == HARD) {
      easyToHardQs++;
    } else {
      hardToEasyQs++;
    }
  }


  /**
   * Counts the new amounts of hard and easy questions and
   * updates the fields of stats accordingly. Also updates the
   * total count of questions answered
   *
   * @param answered the final list of questions
   *                 answered with updated difficulties
   */
  public void endCounts(ArrayList<Question> answered) {
    for (Question q : answered) {
      if (q.diff == HARD) {
        endHardCount++;
      } else {
        endEasyCount++;
      }
    }
    totalAnswered = answered.size();

  }


  /**
   * Gets the relevant fields of stats and returns them
   * with context
   *
   * @return String of stats and context
   */
  public String getStats() {
    return "\nAll Finished!\n\nYou answered "
        + totalAnswered + " questions\n"
        + easyToHardQs + " went from easy to hard\n"
        + hardToEasyQs + " went from hard to easy\n"
        + "\nCurrent Counts in Question Bank:\n"
        + endHardCount + " Hard Questions\n"
        + endEasyCount + " Easy Questions\n";
  }
}

