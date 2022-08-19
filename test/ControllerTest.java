import static org.junit.Assert.assertEquals;

import mock.MockModel;
import mock.MockPlayBackView;
import mock.MockSVGView;
import mock.MockTextView;
import mock.MockVisualView;
import cs5004.animator.auxiliary.IMotion;
import cs5004.animator.controller.Controller;
import cs5004.animator.model.ModelImpl;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.TreeMap;
import org.junit.Test;

/**
 * Test cases for the controller, using mock view and model.
 */
public class ControllerTest {

  private ModelImpl model1 = (new MockModel()).getModel1();
  private TreeMap<Integer, LinkedList<IMotion>> tickMotion = model1.copyTickMotion();
  private LinkedHashMap<String, LinkedList<IMotion>> shapeMotion = model1.copyShapeMotion();
  private ArrayList<Integer> bound = model1.copyBound();
  private String tickShape = "{1=[R RECTANGLE 1 (200,200) 50 100 red 255, green 0, blue 0\n"
      + "], 2=[R RECTANGLE 2 (200,200) 50 100 red 255, green 0, blue 0\n"
      + "], 3=[R RECTANGLE 3 (200,200) 50 100 red 255, green 0, blue 0\n"
      + "], 4=[R RECTANGLE 4 (200,200) 50 100 red 255, green 0, blue 0\n"
      + "], 5=[R RECTANGLE 5 (200,200) 50 100 red 255, green 0, blue 0\n"
      + ", C ELLIPSE 5 (440,70) 120 60 red 0, green 0, blue 255\n"
      + "], 6=[R RECTANGLE 6 (200,200) 50 100 red 255, green 0, blue 0\n"
      + ", C ELLIPSE 6 (440,70) 120 60 red 0, green 0, blue 255\n"
      + "], 7=[R RECTANGLE 7 (200,200) 50 100 red 255, green 0, blue 0\n"
      + ", C ELLIPSE 7 (440,70) 120 60 red 0, green 0, blue 255\n"
      + "], 8=[R RECTANGLE 8 (200,200) 50 100 red 255, green 0, blue 0\n"
      + ", C ELLIPSE 8 (440,70) 120 60 red 0, green 0, blue 255\n"
      + "], 9=[R RECTANGLE 9 (200,200) 50 100 red 255, green 0, blue 0\n"
      + ", C ELLIPSE 9 (440,70) 120 60 red 0, green 0, blue 255\n"
      + "], 10=[R RECTANGLE 10 (200,200) 50 100 red 255, green 0, blue 0\n"
      + ", C ELLIPSE 10 (440,70) 120 60 red 0, green 0, blue 255\n"
      + "], 11=[R RECTANGLE 11 (190,200) 50 100 red 255, green 0, blue 0\n"
      + ", C ELLIPSE 11 (440,70) 120 60 red 25, green 0, blue 254\n"
      + "], 12=[R RECTANGLE 12 (180,200) 50 100 red 255, green 0, blue 0\n"
      + ", C ELLIPSE 12 (440,70) 120 60 red 51, green 0, blue 255\n"
      + "], 13=[R RECTANGLE 13 (170,200) 50 100 red 255, green 0, blue 0\n"
      + ", C ELLIPSE 13 (440,70) 120 60 red 76, green 0, blue 254\n"
      + "], 14=[R RECTANGLE 14 (160,200) 50 100 red 255, green 0, blue 0\n"
      + ", C ELLIPSE 14 (440,70) 120 60 red 102, green 0, blue 255\n"
      + "], 15=[R RECTANGLE 15 (150,200) 50 100 red 255, green 0, blue 0\n"
      + ", C ELLIPSE 15 (440,70) 120 60 red 127, green 0, blue 254\n"
      + "], 16=[R RECTANGLE 16 (140,200) 50 100 red 255, green 0, blue 0\n"
      + ", C ELLIPSE 16 (440,70) 120 60 red 153, green 0, blue 255\n"
      + "], 17=[R RECTANGLE 17 (130,200) 50 100 red 255, green 0, blue 0\n"
      + ", C ELLIPSE 17 (440,70) 120 60 red 178, green 0, blue 254\n"
      + "], 18=[R RECTANGLE 18 (120,200) 50 100 red 255, green 0, blue 0\n"
      + ", C ELLIPSE 18 (440,70) 120 60 red 204, green 0, blue 255\n"
      + "], 19=[R RECTANGLE 19 (110,200) 50 100 red 255, green 0, blue 0\n"
      + ", C ELLIPSE 19 (440,70) 120 60 red 229, green 0, blue 254\n"
      + "], 20=[C ELLIPSE 20 (440,70) 120 60 red 255, green 0, blue 255\n"
      + "], 21=[C ELLIPSE 21 (440,70) 118 60 red 255, green 0, blue 255\n"
      + "], 22=[C ELLIPSE 22 (440,70) 116 60 red 255, green 0, blue 255\n"
      + "], 23=[C ELLIPSE 23 (440,70) 114 60 red 255, green 0, blue 255\n"
      + "], 24=[C ELLIPSE 24 (440,70) 112 60 red 255, green 0, blue 255\n"
      + "], 25=[C ELLIPSE 25 (440,70) 110 60 red 255, green 0, blue 255\n"
      + "], 26=[C ELLIPSE 26 (440,70) 108 60 red 255, green 0, blue 255\n"
      + "], 27=[C ELLIPSE 27 (440,70) 106 60 red 255, green 0, blue 255\n"
      + "], 28=[C ELLIPSE 28 (440,70) 104 60 red 255, green 0, blue 255\n"
      + "], 29=[C ELLIPSE 29 (440,70) 102 60 red 255, green 0, blue 255\n"
      + "], 30=[C ELLIPSE 30 (440,70) 100 60 red 255, green 0, blue 255\n"
      + "], 31=[C ELLIPSE 31 (440,70) 100 60 red 255, green 0, blue 255\n"
      + "], 32=[C ELLIPSE 32 (440,70) 100 60 red 255, green 0, blue 255\n"
      + "], 33=[C ELLIPSE 33 (440,70) 100 60 red 255, green 0, blue 255\n"
      + "], 34=[C ELLIPSE 34 (440,70) 100 60 red 255, green 0, blue 255\n"
      + "], 35=[C ELLIPSE 35 (440,70) 100 60 red 255, green 0, blue 255\n"
      + "], 36=[C ELLIPSE 36 (440,70) 100 60 red 255, green 0, blue 255\n"
      + "], 37=[C ELLIPSE 37 (440,70) 100 60 red 255, green 0, blue 255\n"
      + "], 38=[C ELLIPSE 38 (440,70) 100 60 red 255, green 0, blue 255\n"
      + "], 39=[C ELLIPSE 39 (440,70) 100 60 red 255, green 0, blue 255\n"
      + "]}";

  /**
   * Test text view.
   */
  @Test
  public void testTextView() {
    StringBuilder output = new StringBuilder();
    MockTextView mockTextView = new MockTextView(output);
    Controller controller = new Controller(model1, mockTextView);
    controller.animate();
    String expected = "displayText\n" +
        "tickMotion: " + tickMotion.toString() + "\nshapeMotion: " + shapeMotion.toString()
        + "\nbound: " + bound.toString();
    assertEquals(expected, output.toString());
  }

  /**
   * Test svg view.
   */
  @Test
  public void testSVGView() {
    StringBuilder output = new StringBuilder();
    MockSVGView mockSVGView = new MockSVGView(output);
    Controller controller = new Controller(model1, mockSVGView);
    controller.animate();
    String expected =
        "displaySVG\n" + "shapeMotion: " + shapeMotion.toString() + shapeMotion.toString()
            + "\nbound: " + bound.toString();
    assertEquals(expected, output.toString());
  }

  /**
   * Test visual view.
   */
  @Test
  public void testVisualView() {
    StringBuilder output = new StringBuilder();
    MockVisualView mockVisualView = new MockVisualView(output);
    Controller controller = new Controller(model1, mockVisualView);
    controller.animate();
    String expected =
        "displayVisual\n" + "tickShape: " + tickShape + "\nbound: " + bound.toString();
    assertEquals(expected, output.toString());
  }

  /**
   * Test play back view.
   */
  @Test
  public void testPlayBackView() {
    StringBuilder output = new StringBuilder();
    MockPlayBackView mockPlayBackView = new MockPlayBackView(output);
    Controller controller = new Controller(model1, mockPlayBackView);
    controller.animate();
    String expected =
        "displayPlayback\n" + "tickShape: " + tickShape + "\nbound: " + bound.toString();
    assertEquals(expected, output.toString());
  }
}