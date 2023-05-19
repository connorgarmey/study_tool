package cs3500.pa01;

/**
 * Class for filtering text
 */
public class PoundBracket implements IFilter {
  boolean keepReading = false;


  /**
   * Filters and formats the given string by pound and
   * bracket characters
   *
   * @param notes all the notes for the study guide
   *
   * @return a filtered String containing all notes
   */
  public String drBracket(String notes) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < notes.length(); i++) {
      char c = notes.charAt(i);

      if (!keepReading && c == '#') {
        int endIndex = notes.indexOf('\n', i); //finds first instance of \n after i
        if (endIndex == -1) {
          endIndex = notes.length();
        }
        sb.append("\n");
        sb.append(notes, i, endIndex).append("\n");
        i = endIndex;

      } else if (!keepReading && c == '[' && notes.charAt(i + 1) == '[') {
        keepReading = true;
        sb.append("- ");
        i++;

      } else if (keepReading && c == ']' && notes.charAt(i + 1) == ']') {
        keepReading = false;
        sb.append("\n");
        i++;

      } else if (keepReading && c != '\n') {
        sb.append(c);
      }
    }
    if ((sb.length() > 0) && (sb.charAt(0) == '\n')) {
      return sb.substring(1);
    } else {
      return sb.toString();
    }
  }
}