package cs3500.pa01;

import java.io.File;
import java.util.ArrayList;

public interface IModel {

  String getStudyGuide();

  File generateSrFile();

  void setDifficulty(Difficulty diff);

  ArrayList<Question> getQuestions(int numQuestion);

  Stats generateStats();


}
