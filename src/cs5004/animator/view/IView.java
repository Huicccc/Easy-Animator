package cs5004.animator.view;

import cs5004.animator.auxiliary.IMotion;
import cs5004.animator.auxiliary.IShape;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * The interface View represents the view part of program.
 */
public interface IView {

  /**
   * Display text view, first describes the shapes that are part of the animation and their details,
   * then describes how these shapes will move as the animation proceeds from start to finish.
   *
   * @param tickMotion  LinkedHashMap with name as key, linklist of motions of same name as value
   * @param shapeMotion TreeMap with starting movement tick as key, linklist of motions as value
   * @param bound       the bound of canvas
   */
  void displayText(TreeMap<Integer, LinkedList<IMotion>> tickMotion,
      LinkedHashMap<String, LinkedList<IMotion>> shapeMotion, ArrayList<Integer> bound);

  /**
   * Display svg.
   *
   * @param shapeMotion TreeMap with starting movement tick as key, linklist of motions with same
   *                    starting tick as value
   * @param bound       the bound of canvas
   */
  void displaySVG(LinkedHashMap<String, LinkedList<IMotion>> shapeMotion,
      ArrayList<Integer> bound);

  /**
   * Display visual.
   *
   * @param tickShape TreeMap with each tick as key, linklist of shapes at this tick as value
   * @param bound     the bound of canvas
   */
  void displayVisual(TreeMap<Integer, LinkedList<IShape>> tickShape,
      ArrayList<Integer> bound);

  /**
   * Display playback visual.
   *
   * @param tickShape TreeMap with each tick as key, linklist of shapes at this tick as value
   * @param bound     the bound of canvas
   */
  void displayPlaybackVisual(TreeMap<Integer, LinkedList<IShape>> tickShape,
      ArrayList<Integer> bound);
}
