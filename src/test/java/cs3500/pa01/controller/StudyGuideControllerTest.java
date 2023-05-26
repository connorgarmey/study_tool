package cs3500.pa01.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudyGuideControllerTest {
  FileReader fr;
  StudyGuideController sgc;
  String[] args;
  String[] badArgs;

  @BeforeEach
  public void initData() {
    fr = new FileReader();
    args = new String[3];
    args[0] = "src/test/resources/exampleDirectory/aa.md";
    args[1] = "filename";
    args[2] = "testStudyGuideOutput.md";
    badArgs = new String[3];
    badArgs[0] = "slime";
    badArgs[1] = "filename";
    badArgs[2] = "bad";
    sgc = new StudyGuideController(args);
  }

  @Test
  public void sgControllerTest() {
    sgc.runApp();
    assertEquals("# Study Guide\n"
            + "- by Connor Garmey\n"
            + "- Welcome to the study guide\n",
        fr.readFromFile(Path.of(args[2])));

    assertThrows(
        RuntimeException.class,
        () -> new StudyGuideController(badArgs));
  }

}