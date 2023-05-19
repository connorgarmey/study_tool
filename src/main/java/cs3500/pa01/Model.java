package cs3500.pa01;

import java.io.File;
import java.util.ArrayList;

public class Model implements IModel {
  Stats stats;
  ArrayList<Question> questions;
  String studyGuide;


  @Override
  public String getStudyGuide() {
    return null;
  }

  @Override
  public File generateSrFile() {
    return null;
  }

  @Override
  public void setDifficulty(Difficulty diff) {
  }

  @Override
  public ArrayList<Question> getQuestions(int numQuestion) {
    // some for loop shit using generateQuestion()
    return null;
  }

  @Override
  public Stats generateStats() {
    return null;
  }
}
