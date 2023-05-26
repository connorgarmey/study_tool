package cs3500.pa01.model;

import static cs3500.pa01.model.Difficulty.EASY;
import static cs3500.pa01.model.Difficulty.HARD;

import cs3500.pa01.controller.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Acts as the central model for the question mode of the
 * program, with methods for generating questions
 */
public class QuestionModel {
  private final ArrayList<Question> questions = new ArrayList<>();


  /**
   * Reads, formats, and returns a list of all the questions
   * in a given .sr file location
   *
   * @param location the path for the file to be read
   * @return a list of parsed questions in the proper format
   */
  public ArrayList<Question> getAllQuestions(String location) {
    FileReader fr  = new FileReader();
    String text = fr.readFromFile(Path.of(location));
    return this.parseQuestions(text);
  }

  /**
   *
   * @param text the questions in text form
   * @return the questions as a list of Question
   */
  private ArrayList<Question>  parseQuestions(String text) {
    String[] lines = text.split("\n");

    for (int i = 0; i < lines.length; i += 3) {
      String ques = lines[i];
      String ans = lines[i + 1];
      String diffString = lines[i + 2];
      Difficulty diff;

      if (diffString.equals("@EASY")) {
        diff = EASY;
      } else if (diffString.equals("@HARD")) {
        diff = HARD;
      } else {
        throw new IllegalArgumentException("Invalid Difficulty");
      }
      Question q = new Question(ques, ans, diff);
      questions.add(q);
    }
    return questions;
  }


  /**
   * Gets a certain amount of questions
   *
   * @param numQuestions the number of questions to study
   * @return a list of questions randomized with
   *     hard questions first
   */
  public ArrayList<Question> getSomeQuestions(int numQuestions) {
    ArrayList<Question> hardQs = new ArrayList<>();
    ArrayList<Question> easyQs = new ArrayList<>();

    if (numQuestions <= 0) {
      throw new IllegalArgumentException("Parameter must be greater than 0");
    }

    for (Question question : questions) {
      if (question.getDiffString().equals("@HARD")) {
        hardQs.add(question);

      } else {
        easyQs.add(question);
      }
    }
    Collections.shuffle(easyQs);

    Collections.shuffle(hardQs);


    ArrayList<Question> merged = new ArrayList<>();
    ArrayList<Question> result = new ArrayList<>();
    merged.addAll(hardQs);
    merged.addAll(easyQs);
    int newSize = Math.min(numQuestions, merged.size());

    for (int i = 0; i < newSize; i++) {
      result.add(merged.get(i));
    }
    return result;
  }


}
