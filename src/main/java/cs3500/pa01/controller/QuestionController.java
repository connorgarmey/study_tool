package cs3500.pa01.controller;

import static cs3500.pa01.model.Difficulty.EASY;
import static cs3500.pa01.model.Difficulty.HARD;

import cs3500.pa01.model.Question;
import cs3500.pa01.model.QuestionModel;
import cs3500.pa01.model.Stats;
import cs3500.pa01.view.View;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;



/**
 * The controller class for the question mode of the program.
 * Handles input and output related tasks
 */
public class QuestionController implements Icontroller {
  private final Scanner input;
  private boolean isInputValid;
  private final Stats stats;
  private Path path;
  private ArrayList<Question> practiceQuestions;
  private ArrayList<Question> allQuestions;
  private final ArrayList<Question> answeredQuestions;
  private Appendable appendable;

  /**
   * Constructor for Question Controller
   *
   * @param scanner the input scanner
   * @param a the output appendable
   */
  public QuestionController(Scanner scanner, Appendable a) {
    this.input = scanner;
    this.isInputValid = false;
    this.answeredQuestions = new ArrayList<>();
    this.allQuestions = new ArrayList<>();
    this.stats = new Stats();
    this.appendable = a;
    this.runApp();
  }

  /**
   * Progresses through the programs question mode, acting
   * as the central control method
   */
  @Override
  public void runApp() {
    View view = new View();
    QuestionModel model = new QuestionModel();

    this.printLine(view.showPreface());

    // src/test/resources/studyGuide.sr
    while (!isInputValid) {
      try {
        String stringPath = input.next();
        allQuestions = model.getAllQuestions(stringPath);
        isInputValid = true;
        path = Path.of(stringPath);
      } catch (RuntimeException e) {
        this.printLine("Please enter a valid file path");
      }
    }
    isInputValid = false;

    this.printLine(view.showQnumPrompt());

    while (!isInputValid) {
      try {
        practiceQuestions = model.getSomeQuestions(Integer.parseInt(input.next()));
        isInputValid = true;
      } catch (Exception e) {
        this.printLine("Please enter a valid input (integer greater than 0)");
        System.out.println(e.getClass());
      }
    }
    isInputValid = false;

    this.questionStage();

    stats.endCounts(answeredQuestions);
    this.printLine(stats.getStats());

    this.rewriteFile();

  }


  /**
   * Handles the actual session of ranking questions, seeing the
   * answers, and or ending the session. Responds to the inputs
   * 1 (rank easy), 2 (rank hard), 3 (show answer), and 4 (end session)
   * in the terminal
   */
  private void questionStage() {
    outer:
    for (int i = 0; i < practiceQuestions.size(); ) {
      Question q = practiceQuestions.get(i);
      this.printLine("\n\n\n" + q.getQuestion());
      this.printLine("____________________________________________________"
          + "\nEasy <1>, Hard <2>, Show Answer <3>, End Session <4>");

      do {
        if (input.hasNextInt()) {
          int userInput = input.nextInt();
          isInputValid = true;

          switch (userInput) {
            case 1:
              q.setDifficulty(EASY, stats);
              answeredQuestions.add(q);
              this.printLine("Question Marked Easy");
              i++;
              break;
            case 2:
              q.setDifficulty(HARD, stats);
              answeredQuestions.add(q);
              this.printLine("Question Marked Hard");
              i++;
              break;
            case 3:
              this.printLine(q.getAnswer() + "\n\nPlease rank difficulty");
              break;
            case 4:
              this.printLine("Session Ended");
              break outer;
            default:
              this.printLine("Invalid input. Please enter 1, 2, 3, or 4");
              break;
          }
        } else {
          input.next();
          this.printLine("Invalid input. Please enter a valid integer.");
        }
      } while (!isInputValid);
    }
  }


  /**
   * Rewrites the file using the updated list of questions
   * to change the difficulties based on user inputs
   */
  private void rewriteFile() {
    WriteFile write = new WriteFile();
    StringBuilder sb = new StringBuilder();
    for (Question q : allQuestions) {
      sb.append(q.getQuestion()).append("\n");
      sb.append(q.getAnswer()).append("\n");
      sb.append(q.getDiffString()).append("\n");
    }
    write.rewriteFile(sb.toString(), path);
  }


  /**
   * Prints the given line to the terminal
   *
   * @param line the String to print
   */
  private void printLine(String line) {
    try {
      appendable.append(line).append(System.lineSeparator());
    } catch (Exception e) {
      // Handle exception if append fails
      e.printStackTrace();
    }
  }
}








