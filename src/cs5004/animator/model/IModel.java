package cs5004.animator.model;

import cs5004.animator.auxiliary.IMotion;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * The interface Model is the model part of program contains setBounds method to set bounds of
 * canvas, addMotion method which adds new movement into data, setSpeed method to set speed, getters
 * to get speed and copy of data.
 */
public interface IModel {

  /**
   * Sets bounds of canvas.
   *
   * @param x      the x position of canvas
   * @param y      the y position of canvas
   * @param width  the width of canvas
   * @param height the height of canvas
   */
  void setBounds(int x, int y, int width, int height);

  /**
   * Add motion into model data. Construct start shape and end shape of each movement, then
   * construct new movement with start shape and end shape, stores these movement into model data.
   *
   * @param name the name of shape
   * @param type the type of shape, rectangle or ellipse
   * @param t1   the start tick of movement
   * @param x1   the x position of shape at the beginning of movement
   * @param y1   the y position of shape at the beginning of movement
   * @param w1   the width of shape at the beginning of movement
   * @param h1   the height of shape at the beginning of movement
   * @param r1   the red color of shape at the beginning of movement
   * @param g1   the green color of shape at the beginning of movement
   * @param b1   the blue color of shape at the beginning of movement
   * @param t2   the end tick of movement
   * @param x2   the x position of shape at the end of movement
   * @param y2   the y position of shape at the end of movement
   * @param w2   the width of shape at the end of movement
   * @param h2   the height of shape at the end of movement
   * @param r2   the red color of shape at the end of movement
   * @param g2   the green color of shape at the end of movement
   * @param b2   the blue color of shape at the end of movement
   */
  void addMotion(String name, String type, int t1, int x1, int y1, int w1, int h1, int r1, int g1,
      int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2);

  /**
   * Sets speed of movement.
   *
   * @param speed the speed of movement
   * @throws IllegalArgumentException the illegal argument exception if speed is less or equal to 0
   */
  void setSpeed(int speed) throws IllegalArgumentException;

  /**
   * Gets speed of the movement.
   *
   * @return the speed of the movement
   */
  int getSpeed();

  /**
   * Copy tick motion tree map.
   *
   * @return the tree map of tick motion, the key is start tick of a movement and value is a
   *         linklist of movements starts at this tick, sequence of linked list is the entering
   *         sequence of adding new movement.
   */
  TreeMap<Integer, LinkedList<IMotion>> copyTickMotion();

  /**
   * Copy shape motion linked hash map.
   *
   * @return the linked hash map of shape motion, the key is the name of shape and value is a linked
   *         list of movements of the same shape name, sequence of linked list is the entering
   *         sequence of adding new movement.
   */
  LinkedHashMap<String, LinkedList<IMotion>> copyShapeMotion();

  /**
   * Copy bound array list.
   *
   * @return the array list of canvas bounds
   */
  ArrayList<Integer> copyBound();

  String toString();
}
