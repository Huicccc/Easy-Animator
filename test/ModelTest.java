import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import cs5004.animator.auxiliary.Ellipse;
import cs5004.animator.auxiliary.IMotion;
import cs5004.animator.auxiliary.IShape;
import cs5004.animator.auxiliary.MotionImp;
import cs5004.animator.auxiliary.Rectangle;
import cs5004.animator.model.ModelImpl;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;
import org.junit.Test;

/**
 * The Model test.
 */
public class ModelTest {

  /**
   * Test model constructor.
   */
  @Test
  public void testModel() {
    ModelImpl model = new ModelImpl();
    assertEquals("Bounds: []\n"
        + "Speed: 0\n"
        + "ShapeMotion: {}\n"
        + "TickMotion: {}", model.toString());
  }

  /**
   * Test bounds.
   */
  @Test
  public void testBounds() {
    ModelImpl model = new ModelImpl();
    model.setBounds(200, 70, 360, 360);
    assertEquals("Bounds: [200, 70, 360, 360]\n"
        + "Speed: 0\n"
        + "ShapeMotion: {}\n"
        + "TickMotion: {}", model.toString());

    model.setBounds(10, 60, 200, 500);
    assertEquals("Bounds: [10, 60, 200, 500]\n"
        + "Speed: 0\n"
        + "ShapeMotion: {}\n"
        + "TickMotion: {}", model.toString());
  }

  /**
   * Test set speed.
   */
  @Test
  public void testSetSpeed() {
    ModelImpl model = new ModelImpl();
    model.setSpeed(1);
    assertEquals("Bounds: []\n"
        + "Speed: 1\n"
        + "ShapeMotion: {}\n"
        + "TickMotion: {}", model.toString());

    model.setSpeed(10);
    assertEquals("Bounds: []\n"
        + "Speed: 10\n"
        + "ShapeMotion: {}\n"
        + "TickMotion: {}", model.toString());

    try {
      model.setSpeed(0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Speed should be larger than 0!");
    }
  }

  /**
   * Test addMotion.
   */
  @Test
  public void testAddMotion() {
    ModelImpl model = new ModelImpl();
    model.addMotion("R", "rectangle", 1, 200, 200, 50, 100, 255, 0, 0, 10, 200, 200, 50, 100,
        255, 0, 0);

    IShape shape1 = new Rectangle("R", 1, 200, 200, 50, 100, 255, 0, 0);
    IShape shape2 = new Rectangle("R", 10, 200, 200, 50, 100, 255, 0, 0);
    IMotion motion = new MotionImp(shape1, shape2);
    LinkedList<IMotion> tickMotionLinkedList1 = new LinkedList<>();
    LinkedList<IMotion> shapeMotionLinkedListR = new LinkedList<>();
    tickMotionLinkedList1.add(motion);
    shapeMotionLinkedListR.add(motion);
    TreeMap<Integer, LinkedList<IMotion>> tickMotion = new TreeMap<>();
    HashMap<String, LinkedList<IMotion>> shapeMotion = new HashMap<>();
    tickMotion.put(1, tickMotionLinkedList1);
    shapeMotion.put("R", shapeMotionLinkedListR);

    // test copy
    assertNotEquals(shape1, model.copyTickMotion().get(1).get(0).getStartShape());
    assertNotEquals(shape1, model.copyTickMotion().get(1).get(0).getEndShape());
    assertNotEquals(shape1, model.copyShapeMotion().get("R").get(0).getStartShape());
    assertNotEquals(shape1, model.copyShapeMotion().get("R").get(0).getEndShape());
    assertEquals(tickMotion.toString(), model.copyTickMotion().toString());
    assertEquals(shapeMotion.toString(), model.copyShapeMotion().toString());

    model.addMotion("R", "rectangle", 10, 200, 200, 50, 100, 255, 0, 0, 20, 100, 200, 50, 100, 255,
        0, 0);
    shape1 = new Rectangle("R", 10, 200, 200, 50, 100, 255, 0, 0);
    shape2 = new Rectangle("R", 20, 100, 200, 50, 100, 255, 0, 0);
    motion = new MotionImp(shape1, shape2);
    LinkedList<IMotion> tickMotionLinkedList10 = new LinkedList<>();
    tickMotionLinkedList10.add(motion);
    shapeMotionLinkedListR.add(motion);
    tickMotion.put(10, tickMotionLinkedList10);
    shapeMotion.put("R", shapeMotionLinkedListR);

    assertEquals(tickMotion.toString(), model.copyTickMotion().toString());
    assertEquals(shapeMotion.toString(), model.copyShapeMotion().toString());

    model.addMotion("C", "ellipse", 5, 440, 70, 120, 60, 0, 0, 255, 10, 440, 70, 120, 60, 0, 0,
        255);
    shape1 = new Ellipse("C", 5, 440, 70, 120, 60, 0, 0, 255);
    shape2 = new Ellipse("C", 10, 440, 70, 120, 60, 0, 0, 255);
    motion = new MotionImp(shape1, shape2);
    LinkedList<IMotion> tickMotionLinkedList5 = new LinkedList<>();
    LinkedList<IMotion> shapeMotionLinkedListC = new LinkedList<>();
    tickMotionLinkedList5.add(motion);
    shapeMotionLinkedListC.add(motion);
    tickMotion.put(5, tickMotionLinkedList5);
    shapeMotion.put("C", shapeMotionLinkedListC);

    assertEquals(tickMotion.toString(), model.copyTickMotion().toString());
    assertEquals(shapeMotion.toString(), model.copyShapeMotion().toString());

    model.addMotion("C", "ellipse", 5, 440, 70, 120, 60, 0, 0, 255, 10, 440, 70, 120, 60, 255, 0,
        255);
    shape1 = new Ellipse("C", 5, 440, 70, 120, 60, 0, 0, 255);
    shape2 = new Ellipse("C", 10, 440, 70, 120, 60, 255, 0, 255);
    motion = new MotionImp(shape1, shape2);
    tickMotionLinkedList5.add(motion);
    shapeMotionLinkedListC.add(motion);
    tickMotion.put(5, tickMotionLinkedList5);
    shapeMotion.put("C", shapeMotionLinkedListC);

    assertNotEquals(tickMotion, model.copyTickMotion());
    assertNotEquals(shapeMotion, model.copyShapeMotion());
    assertEquals(tickMotion.toString(), model.copyTickMotion().toString());
    assertEquals(shapeMotion.toString(), model.copyShapeMotion().toString());

    assertEquals("Bounds: []\n"
            + "Speed: 0\n"
            + "ShapeMotion: {R=[R RECTANGLE (200,200) 50 100 red 255, green 0, blue 0 "
            + "(200,200) 100 50 red 255, green 0, blue 0, R RECTANGLE (200,200) 50 100 "
            + "red 255, green 0, blue 0 (100,200) 100 50 red 255, green 0, blue 0], "
            + "C=[C ELLIPSE (440,70) 120 60 red 0, green 0, blue 255 (440,70) 60 120 "
            + "red 0, green 0, blue 255, C ELLIPSE (440,70) 120 60 red 0, green 0, blue 255 "
            + "(440,70) 60 120 red 255, green 0, blue 255]}\n"
            + "TickMotion: {1=[R RECTANGLE (200,200) 50 100 red 255, green 0, blue 0 "
            + "(200,200) 100 50 red 255, green 0, blue 0], 5=[C ELLIPSE (440,70) 120 60 "
            + "red 0, green 0, blue 255 (440,70) 60 120 red 0, green 0, blue 255, "
            + "C ELLIPSE (440,70) 120 60 red 0, green 0, blue 255 (440,70) 60 120 "
            + "red 255, green 0, blue 255], 10=[R RECTANGLE (200,200) 50 100 "
            + "red 255, green 0, blue 0 (100,200) 100 50 red 255, green 0, blue 0]}",
        model.toString());

    try {
      model.addMotion("false type", "fakeshape", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Shape should be ellipse or rectangle!");
    }

    try {
      model.addMotion("false time", "rectangle", 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "End time should be larger than begin time!");
    }
  }

  /**
   * Test toString.
   */
  @Test
  public void testToString() {
    ModelImpl model = new ModelImpl();
    model.addMotion("R", "rectangle", 1, 200, 200, 50, 100, 255, 0, 0, 10, 200, 200, 50, 100,
        255, 0, 0);
    assertEquals("Bounds: []\n"
            + "Speed: 0\n"
            + "ShapeMotion: {R=[R RECTANGLE (200,200) 50 100 red 255, green 0, blue 0 "
            + "(200,200) 100 50 red 255, green 0, blue 0]}\n"
            + "TickMotion: {1=[R RECTANGLE (200,200) 50 100 red 255, green 0, blue 0 "
            + "(200,200) 100 50 red 255, green 0, blue 0]}",
        model.toString());

    model.addMotion("R", "rectangle", 10, 200, 200, 50, 100, 255, 0, 0, 50, 300, 300, 50, 100,
        255, 0, 0);
    assertEquals("Bounds: []\n"
            + "Speed: 0\n"
            + "ShapeMotion: {R=[R RECTANGLE (200,200) 50 100 red 255, green 0, blue 0 "
            + "(200,200) 100 50 red 255, green 0, blue 0, R RECTANGLE (200,200) 50 100 "
            + "red 255, green 0, blue 0 (300,300) 100 50 red 255, green 0, blue 0]}\n"
            + "TickMotion: {1=[R RECTANGLE (200,200) 50 100 red 255, green 0, blue 0 "
            + "(200,200) 100 50 red 255, green 0, blue 0], 10=[R RECTANGLE (200,200) 50 100 "
            + "red 255, green 0, blue 0 (300,300) 100 50 red 255, green 0, blue 0]}",
        model.toString());

    model.setBounds(100, 200, 300, 400);
    model.addMotion("E", "ellipse", 50, 440, 250, 120, 60, 0, 0, 255, 70, 440, 370, 120, 60, 0,
        170, 85);
    model.addMotion("F", "rectangle", 70, 440, 370, 120, 60, 0, 170, 85, 80, 440, 370, 120, 60, 0,
        255, 0);

    assertEquals("Bounds: [100, 200, 300, 400]\n"
            + "Speed: 0\n"
            + "ShapeMotion: {R=[R RECTANGLE (200,200) 50 100 red 255, green 0, blue 0 "
            + "(200,200) 100 50 red 255, green 0, blue 0, R RECTANGLE (200,200) 50 100 "
            + "red 255, green 0, blue 0 (300,300) 100 50 red 255, green 0, blue 0], "
            + "E=[E ELLIPSE (440,250) 120 60 red 0, green 0, blue 255 (440,370) 60 120 "
            + "red 0, green 170, blue 85], F=[F RECTANGLE (440,370) 120 60 red 0, green 170, "
            + "blue 85 (440,370) 60 120 red 0, green 255, blue 0]}\n"
            + "TickMotion: {1=[R RECTANGLE (200,200) 50 100 red 255, green 0, blue 0 "
            + "(200,200) 100 50 red 255, green 0, blue 0], 10=[R RECTANGLE (200,200) 50 100 "
            + "red 255, green 0, blue 0 (300,300) 100 50 red 255, green 0, blue 0], "
            + "50=[E ELLIPSE (440,250) 120 60 red 0, green 0, blue 255 (440,370) 60 120 red 0, "
            + "green 170, blue 85], 70=[F RECTANGLE (440,370) 120 60 red 0, green 170, blue 85 "
            + "(440,370) 60 120 red 0, green 255, blue 0]}",
        model.toString());
  }

}
