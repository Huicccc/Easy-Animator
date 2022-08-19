package mock;

import cs5004.animator.auxiliary.IMotion;
import cs5004.animator.auxiliary.IShape;
import cs5004.animator.view.IView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * The Mock playback view.
 */
public class MockPlayBackView implements IView {

  private Appendable output;

  /**
   * Instantiates a new Mock playback view.
   *
   * @param output the output used to pass out data in test
   */
  public MockPlayBackView(Appendable output) {
    this.output = output;
  }

  @Override
  public void displayText(TreeMap<Integer, LinkedList<IMotion>> tickMotion,
      LinkedHashMap<String, LinkedList<IMotion>> shapeMotion, ArrayList<Integer> bound) {
    throw new UnsupportedOperationException("");
  }

  @Override
  public void displaySVG(LinkedHashMap<String, LinkedList<IMotion>> shapeMotion,
      ArrayList<Integer> bound) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("");
  }

  @Override
  public void displayVisual(TreeMap<Integer, LinkedList<IShape>> tickShape,
      ArrayList<Integer> bound) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("");
  }

  @Override
  public void displayPlaybackVisual(TreeMap<Integer, LinkedList<IShape>> tickShape,
      ArrayList<Integer> bound) throws UnsupportedOperationException {
    String string =
        "displayPlayback\n" + "tickShape: " + tickShape.toString() + "\nbound: " + bound.toString();
    try {
      this.output.append(string);
    } catch (IOException e) {
      throw new IllegalArgumentException("Error when displayText");
    }
  }
}