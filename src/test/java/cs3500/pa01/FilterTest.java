package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
  PoundBracket filter;

  /**
   * initializes the data for testing
   */
  @BeforeEach
  public void initData() {
    one = "The cat [and the dog";
    two = "# The apple fell from the tree";
    three = "The [[cat]] sat";
    four = "[[The ho]use was large]]";
    five = "Ooga booga]] booga";
    six = "[[a b c d e";
    seven = "[[1 2 3 \n 4 5";
    filter = new PoundBracket();
  }

  /**
   * tests the pound filtering
   */
  @Test
  public void testPound() {
    assertEquals("", filter.drBracket(one));
    assertEquals("# The apple fell from the tree\n", filter.drBracket(two));
  }

  /**
   * tests the filter for brackets and other cases
   */
  @Test
  public void testFilter() {
    assertEquals("", filter.drBracket(one));
    assertEquals("- cat\n", filter.drBracket(three));
    assertEquals("- The ho]use was large\n", filter.drBracket(four));
    filter.keepReading = true;
    assertEquals("The cat [and the dog", filter.drBracket(one));
    assertEquals("Ooga booga\n", filter.drBracket(five));
    filter.keepReading = false;
    assertEquals("- a b c d e", filter.drBracket(six));
    assertTrue(filter.keepReading);
    filter.keepReading = false;
    assertEquals("- 1 2 3  4 5", filter.drBracket(seven));


  }
}