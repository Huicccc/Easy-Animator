package cs5004.animator.model;

import cs5004.animator.auxiliary.Ellipse;
import cs5004.animator.auxiliary.IMotion;
import cs5004.animator.auxiliary.IShape;
import cs5004.animator.auxiliary.MotionImp;
import cs5004.animator.auxiliary.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * The class Model implements IModel is the model of program.
 */
public class ModelImpl implements IModel {

  private final TreeMap<Integer, LinkedList<IMotion>> tickMotion;
  private final LinkedHashMap<String, LinkedList<IMotion>> shapeMotion;
  private ArrayList<Integer> bound;
  private int speed;

  /**
   * Instantiates a new Model, initializes TreeMap to store the motions at same beginning tick,
   * initializes LinkedHashMap to store the motions data of same name, initializes ArrayList to
   * store bounds and set speed to default value 1.
   */
  public ModelImpl() {
    this.tickMotion = new TreeMap<>();
    this.shapeMotion = new LinkedHashMap<>();
    this.bound = new ArrayList<>();
    this.speed = 0;
  }

  @Override
  public TreeMap<Integer, LinkedList<IMotion>> copyTickMotion() {
    TreeMap<Integer, LinkedList<IMotion>> tickMotionCopy = new TreeMap<>();
    for (Map.Entry<Integer, LinkedList<IMotion>> entry : tickMotion.entrySet()) {
      for (int i = 0; i < entry.getValue().size(); i++) {
        IMotion motion = entry.getValue().get(i);
        Integer tick = motion.getStartShape().getTick();
        if (!tickMotionCopy.containsKey(tick)) {
          LinkedList<IMotion> ll = new LinkedList<>();
          ll.add(motion.copy());
          tickMotionCopy.put(tick, ll);
        } else {
          tickMotionCopy.get(tick).add(motion.copy());
        }
      }
    }
    return tickMotionCopy;
  }

  @Override
  public LinkedHashMap<String, LinkedList<IMotion>> copyShapeMotion() {
    LinkedHashMap<String, LinkedList<IMotion>> shapeMotionCopy = new LinkedHashMap<>();
    for (Map.Entry<String, LinkedList<IMotion>> entry : shapeMotion.entrySet()) {
      for (int i = 0; i < entry.getValue().size(); i++) {
        IMotion motion = entry.getValue().get(i);
        String name = motion.getStartShape().getName();
        if (!shapeMotionCopy.containsKey(name)) {
          LinkedList<IMotion> ll = new LinkedList<>();
          ll.add(motion.copy());
          shapeMotionCopy.put(name, ll);
        } else {
          shapeMotionCopy.get(name).add(motion.copy());
        }
      }
    }
    return shapeMotionCopy;
  }

  @Override
  public ArrayList<Integer> copyBound() {
    return new ArrayList<>(
        Arrays.asList(bound.get(0), bound.get(1), bound.get(2), bound.get(3)));
  }

  @Override
  public void setBounds(int x, int y, int width, int height) {
    this.bound = new ArrayList<>(Arrays.asList(x, y, width, height));
  }

  @Override
  public void addMotion(String name, String type, int t1, int x1, int y1, int w1, int h1,
      int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    IShape shape1;
    IShape shape2;
    if (!(type.toLowerCase().contentEquals("ellipse") || type.toLowerCase()
        .contentEquals("rectangle"))) {
      throw new IllegalArgumentException("Shape should be ellipse or rectangle!");
    }
    if (t2 < t1) {
      throw new IllegalArgumentException("End time should be larger than begin time!");
    }
    if (type.equalsIgnoreCase("ellipse")) {
      shape1 = new Ellipse(name, t1, x1, y1, w1, h1, r1, g1, b1);
      shape2 = new Ellipse(name, t2, x2, y2, w2, h2, r2, g2, b2);
    } else {
      shape1 = new Rectangle(name, t1, x1, y1, w1, h1, r1, g1, b1);
      shape2 = new Rectangle(name, t2, x2, y2, w2, h2, r2, g2, b2);
    }
    IMotion motion = new MotionImp(shape1, shape2);

    if (!shapeMotion.containsKey(name)) {
      LinkedList<IMotion> ll = new LinkedList<>();
      ll.add(motion);
      shapeMotion.put(name, ll);
    } else {
      shapeMotion.get(name).add(motion);
    }

    if (!tickMotion.containsKey(t1)) {
      LinkedList<IMotion> ll = new LinkedList<>();
      ll.add(motion);
      tickMotion.put(t1, ll);
    } else {
      tickMotion.get(t1).add(motion);
    }
  }

  @Override
  public void setSpeed(int speed) throws IllegalArgumentException {
    if (speed <= 0) {
      throw new IllegalArgumentException("Speed should be larger than 0!");
    } else {
      this.speed = speed;
    }
  }

  @Override
  public int getSpeed() {
    return this.speed;
  }

  @Override
  public String toString() {
    return "Bounds: " + this.bound.toString() + "\nSpeed: " + this.speed + "\nShapeMotion: "
        + this.shapeMotion + "\nTickMotion: " + this.tickMotion;
  }
}
