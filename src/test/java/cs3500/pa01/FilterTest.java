package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa01.model.Filter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test the filter class
 */
class FilterTest {
  String one;
  String two;
  String three;
  String four;
  String five;
  String six;
  String seven;
  Filter filter;

  /**
   * initializes the data for testing
   */
  @BeforeEach
  public void initData() {
    one = "The cat [and the dog";
    two = "\n# The apple fell from the tree";
    three = " [[This is very important]] blabla [[ This is a question \n"
        + ":::answer]]";
    four = """
        Hi there[[The spider ran up \n::: the web]] #Header
        dfijfbnd [[Keep reading\s
         keep reading]]""";
    five = '\n' + "[[A]]";
    six = "[[Fake :: question]] [ important?]";
    filter = new Filter();
  }

  /**
   * tests the pound filtering
   */
  @Test
  public void testPound() {
    assertEquals("", filter.drBracket(one)[0]);
    assertEquals("# The apple fell from the tree\n", filter.drBracket(two)[0]);
  }

  /**
   * tests the filter for brackets and other cases
   */
  @Test
  public void testBrackets() {
    assertEquals("- This is very important\n", filter.drBracket(three)[0]);
    filter = new Filter();
    assertEquals("#Header\n- Keep reading  keep reading\n", filter.drBracket(four)[0]);
    filter = new Filter();
    assertEquals("- Fake :: question\n", filter.drBracket(six)[0]);
  }

  @Test
  public void testQuestions() {
    assertEquals("", filter.drBracket(one)[1]);
    assertEquals("", filter.drBracket(two)[1]);
    assertEquals("-  This is a question \nanswer\n@HARD", filter.drBracket(three)[1]);
    filter = new Filter();
    assertEquals("- The spider ran up \n the web\n@HARD", filter.drBracket(four)[1]);
  }

  @Test
  public void testRemoveIndent() {
    assertEquals("- A\n", filter.drBracket(five)[0]);
  }


}