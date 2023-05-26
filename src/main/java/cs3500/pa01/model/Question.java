package cs3500.pa01.model;

import static cs3500.pa01.model.Difficulty.HARD;

/**
 * Represents a question with a question,
 * answer, and difficulty
 */
public class Question {
  Difficulty diff;
  String question;
  String answer;

  Question(String q, String a, Difficulty d) {
    this.question = q;
    this.answer = a;
    this.diff = d;
  }

  /**
   * Sets the difficulty of a question and
   * updates the stats field if it changed
   *
   * @param d difficulty to change to
   * @param s the Stats class
   */
  public void setDifficulty(Difficulty d, Stats s) {
    if (diff != d) {
      diff = d;
      s.addChange(diff);
    }
  }

  /**
   * getter for Question class
   *
   * @return question as a String
   */
  public String getQuestion() {
    return this.question;
  }

  /**
   * getter for Question class
   *
   * @return answer as a String
   */
  public String getAnswer() {
    return this.answer;
  }

  /**
   * getter for Question class
   *
   * @return question difficulty enumeration
   */
  public Difficulty getDiff() {
    return this.diff;
  }

  /**
   * getter for Question class
   *
   * @return difficulty as a String
   */
  public String getDiffString() {
    if (diff == HARD) {
      return "@HARD";
    } else {
      return "@EASY";
    }
  }


}
