package cs3500.pa01.model;

/**
 * Class to handle filtering text to be properly formatted
 * for questions and study guides
 */
public class Filter {
  boolean isQuestion;
  StringBuilder studyGuide;
  StringBuilder storage;
  StringBuilder questions;

  /**
   * Class for filtering and formatting text
   */
  public Filter() {
    isQuestion = false;
    studyGuide = new StringBuilder();
    storage = new StringBuilder();
    questions = new StringBuilder();
  }


  /**
   * Filters and formats the given string by semicolons
   * separating a question and answer
   *
   * @param notes all the notes for the study guide
   * @return a filtered String containing all notes
   */
  public String[] drBracket(String notes) {
    boolean keepReading = false;
    String[] both = new String[2];

    for (int i = 0; i < notes.length(); i++) {
      char c = notes.charAt(i);

      if (!keepReading && c == '#') {
        int endIndex = notes.indexOf('\n', i); //finds first instance of \n after i
        if (endIndex == -1) {
          endIndex = notes.length();
        }
        studyGuide.append("\n");
        studyGuide.append(notes, i, endIndex).append("\n");
        i = endIndex;

      } else if (!keepReading && c == '[' && notes.charAt(i + 1) == '[') {
        keepReading = true;
        storage.append("- ");
        i++;

      } else if (c == ':' && notes.charAt(i + 1) == ':' && notes.charAt(i + 2) == ':') {
        isQuestion = true;
        storage.append("\n");
        i += 2;

      } else if (keepReading && c == ']' && notes.charAt(i + 1) == ']') {
        keepReading = false;
        storage.append("\n"); // We want a new line for both modes

        i += this.formatQuestions();

      } else if (keepReading && c != '\n') {
        storage.append(c);
      }
    }
    both[0] = this.removeIndent(studyGuide);
    both[1] = this.removeIndent(questions);
    return both;
  }

  /**
   * Eliminates any indentation at the start of the text
   *
   * @param stringBuilder all of the text
   * @return the text without an indent at the start
   */
  private String removeIndent(StringBuilder stringBuilder) {
    if ((stringBuilder.length() > 0) && (stringBuilder.charAt(0) == '\n')) {
      return stringBuilder.substring(1);
    } else {
      return stringBuilder.toString();
    }
  }

  /**
   * Separates out questions from the study guide
   *
   * @return int for incrementing the for loop in drBracket
   */
  private int formatQuestions() {
    if (isQuestion) {
      isQuestion = false;
      // New line, question, answer, then default difficulty
      questions.append("\n").append(storage).append("@HARD");
      storage = new StringBuilder();
      return 1;
    } else {
      studyGuide.append(storage);
      storage = new StringBuilder();
      return 0;
    }

  }


}
