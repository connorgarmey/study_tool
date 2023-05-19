package cs3500.pa01;

import java.io.File;
import java.util.ArrayList;

public class QuestionController implements IController, IModel {
  Model model;

  @Override
  public void runApp() {

  }

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
    return null;
  }

  @Override
  public Stats generateStats() {
    return null;
  }
}
