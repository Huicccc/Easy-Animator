import static org.junit.Assert.assertEquals;

import mock.MockModel;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.view.TextView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.junit.Test;

/**
 * The Text view test.
 */
public class TextViewTest {

  /**
   * The Mock model1.
   */
  public ModelImpl model1 = (new MockModel()).getModel1();
  /**
   * The Mock model2.
   */
  public ModelImpl model2 = (new MockModel()).getModel2();

  /**
   * Test view.
   */
  @Test
  public void testView() {
    StringBuilder outputLog1 = new StringBuilder();
    TextView textView1 = new TextView(outputLog1);
    textView1.displayText(model1.copyTickMotion(), model1.copyShapeMotion(), model1.copyBound());
    String expected1 =
        "Create oval C with center at (440,70), radius 60 and 120, "
            + "color red 0, green 0, blue 255\n"
            + "Create rectangle R with corner at (200,200), height 100 and width 50, "
            + "color red 255, green 0, blue 0\n"
            + "C appears at time t=5 and disappears at time t=40\n"
            + "R appears at time t=1 and stays\n"
            + "R: change position from (200,200) to (100,200) from time t=10 to t=20\n"
            + "C: change color from red 0, green 0, blue 255 to red 255, green 0, blue 255, "
            + "from time t=10 to t=20\n"
            + "C: change width from 120 to 100 from time t=20 to t=30\n";
    assertEquals(expected1, outputLog1.toString());

    StringBuilder outputLog2 = new StringBuilder();
    TextView textView2 = new TextView(outputLog2);
    textView2.displayText(model2.copyTickMotion(), model2.copyShapeMotion(), model2.copyBound());
    String expected2 =
        "Create oval C with center at (440,70), radius 60 and 120, "
            + "color red 0, green 0, blue 255\n"
            + "Create oval E with center at (200,70), radius 60 and 120, "
            + "color red 0, green 0, blue 255\n"
            + "Create rectangle R with corner at (200,200), height 100 and width 50, "
            + "color red 255, green 0, blue 0\n"
            + "C appears at time t=5 and disappears at time t=40\n"
            + "E appears at time t=1 and disappears at time t=10\n"
            + "R appears at time t=2 and stays\n"
            + "R: change position from (200,200) to (100,200) from time t=8 to t=20\n"
            + "C: change color from red 0, green 0, blue 255 to red 255, green 0, blue 255, "
            + "from time t=10 to t=15\n"
            + "C: change width from 120 to 100 from time t=15 to t=20\n";
    assertEquals(expected2, outputLog2.toString());

    FileWriter fileWriter1;
    FileWriter fileWriter2;
    try {
      fileWriter1 = new FileWriter("textview-test1.txt");
      fileWriter2 = new FileWriter("textview-test2.txt");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    TextView textViewFile1 = new TextView(fileWriter1);
    TextView textViewFile2 = new TextView(fileWriter2);
    textViewFile1.displayText(model1.copyTickMotion(), model1.copyShapeMotion(),
        model1.copyBound());
    textViewFile2.displayText(model2.copyTickMotion(), model2.copyShapeMotion(),
        model2.copyBound());
    try {
      fileWriter1.close();
      fileWriter2.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    String content1;
    String content2;
    try {
      content1 = new Scanner(new File("textview-test1.txt")).useDelimiter("\\Z").next();
      content2 = new Scanner(new File("textview-test2.txt")).useDelimiter("\\Z").next();
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    assertEquals(content1 + "\n", outputLog1.toString());
    assertEquals(content2 + "\n", outputLog2.toString());
  }
}
