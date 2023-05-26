package cs3500.pa01.model;

import static cs3500.pa01.model.Difficulty.EASY;
import static cs3500.pa01.model.Difficulty.HARD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuestionModelTest {
  QuestionModel model;
  String location;
  String locationBad;
  String q1Question;
  String q1Answer;
  Difficulty q1Difficulty;
  String q2Question;
  String q2Answer;
  Difficulty q2Difficulty;


  @BeforeEach
  public void initData() {
    model = new QuestionModel();
    location = "sampleQuestions.sr";
    locationBad = "sampleQuestionsBad.sr";
    q1Question = "- What is the name of the my the Tiger in Winnie the Pooh?";
    q1Answer = "Tigger";
    q1Difficulty = EASY;
    q2Question = "- What countries form the G7?";
    q2Answer = "US, UK, Germany, France, Italy, Japan, Canada";
    q2Difficulty = HARD;

  }

  @Test
  public void testGetAllQuestions() {
    assertEquals(q1Question, model.getAllQuestions(location).get(0).getQuestion());
    assertEquals(q1Answer, model.getAllQuestions(location).get(0).getAnswer());
    assertEquals(q1Difficulty, model.getAllQuestions(location).get(0).getDiff());

    assertEquals(q2Question, model.getAllQuestions(location).get(1).getQuestion());
    assertEquals(q2Answer, model.getAllQuestions(location).get(1).getAnswer());
    assertEquals(q2Difficulty, model.getAllQuestions(location).get(1).getDiff());

    assertThrows(
        IllegalArgumentException.class,
        () -> model.getAllQuestions(locationBad));
  }

  @Test
  public void testGetSomeQuestions() {
    assertThrows(
        IllegalArgumentException.class,
        () -> model.getSomeQuestions(0));

    model.getAllQuestions(location);
    assertEquals(q2Question, model.getSomeQuestions(1).get(0).getQuestion());
    assertEquals(q2Answer, model.getSomeQuestions(2).get(0).getAnswer());
    assertEquals(q2Difficulty, model.getSomeQuestions(2).get(0).getDiff());

    assertEquals(q1Question, model.getSomeQuestions(2).get(1).getQuestion());
    assertEquals(q1Answer, model.getSomeQuestions(2).get(1).getAnswer());
    assertEquals(q1Difficulty, model.getSomeQuestions(2).get(1).getDiff());
  }

  @Test
  public void testStats() {
    Stats stats = new Stats();
    final String statText = "\nAll Finished!\n\nYou answered "
        + 2 + " questions\n"
        + 1 + " went from easy to hard\n"
        + 1 + " went from hard to easy\n"
        + "\nCurrent Counts in Question Bank:\n"
        + 1 + " Hard Questions\n"
        + 1 + " Easy Questions\n";
    this.initData();
    stats.addChange(HARD);
    stats.addChange(EASY);
    stats.endCounts(model.getAllQuestions(location));
    assertEquals(statText, stats.getStats());

  }


}