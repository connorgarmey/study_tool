package cs3500.pa01.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.view.View;
import java.io.CharArrayWriter;
import java.io.StringReader;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class QuestionControllerTest {

  Readable readable;
  QuestionController qc;
  Scanner scanner;
  View view;
  String preface;
  String questionNumprompt;
  Appendable appendable;
  String output;
  String[] lines;

  @BeforeEach
  public void initData() {
    readable = new StringReader("""
        sampleControlTestQuestions.sr
        1 
        3
        2
        1 \n 1 \n 4
        """);
    scanner = new Scanner(readable);
    appendable = new CharArrayWriter();
    qc = new QuestionController(scanner, appendable);
    //view = new View(appendable);
    preface = "Hello! Welcome to your personal study tool. \n"
        + "Please provide a path to a .sr file containing the questions"
        + " you would like to study";
    questionNumprompt = "Now please enter the number of questions "
        + "you would like to practice";
    qc.runApp();
    output = appendable.toString();
    lines = output.split("\n", 10);
  }

  @Test
  public void testViews() {
    assertEquals("Hello! Welcome to your personal study tool. ",
        lines[0]);
    assertEquals("Please provide a path to a .sr file containing "
        + "the questions you would like to study", lines[1]);
    assertEquals("Now please enter the number of questions "
        + "you would like to practice", lines[2]);
    assertEquals("", lines[3]);
    assertEquals("", lines[4]);
    assertEquals("", lines[5]);
    assertEquals("", lines[5]);
    assertEquals("- What countries form the G7?", lines[6]);
    assertEquals("________________________________"
        + "____________________", lines[7]);
    assertEquals("Easy <1>, Hard <2>, Show Answer <3>, "
        + "End Session <4>", lines[8]);

  }
}


