package cs5004.animator.view;

import cs5004.animator.auxiliary.IMotion;
import cs5004.animator.auxiliary.IShape;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.TreeMap;
import javax.swing.JFrame;

/**
 * The class Visual view, draws and plays the animation inside a window.The animation should start
 * as the view is loaded, with no additional inputs from the user.
 */
public class VisualView extends JFrame implements IView {

  private JFrame frame;
  private int speed;

  /**
   * Instantiates a new Visual view.
   */
  public VisualView(int speed) {
    this.frame = new JFrame();
    frame.setSize(1000, 1000);
    frame.setLocation(0, 0);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.speed = speed;
  }

  @Override
  public void displayText(TreeMap<Integer, LinkedList<IMotion>> tickMotion,
      LinkedHashMap<String, LinkedList<IMotion>> shapeMotion,
      ArrayList<Integer> bound) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("");
  }

  @Override
  public void displaySVG(LinkedHashMap<String, LinkedList<IMotion>> shapeMotion,
      ArrayList<Integer> bound) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("");
  }


  @Override
  public void displayVisual(TreeMap<Integer, LinkedList<IShape>> tickShape,
      ArrayList<Integer> bound) {
    GraphPanel graphPanel = new GraphPanel(bound);
    frame.add(graphPanel, BorderLayout.CENTER);
    frame.setVisible(true);
    for (Entry<Integer, LinkedList<IShape>> entry : tickShape.entrySet()) {
      graphPanel.setShape(entry.getValue());
      graphPanel.repaint();
      try {
        Thread.sleep(1000 / speed);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  @Override
  public void displayPlaybackVisual(TreeMap<Integer, LinkedList<IShape>> tickShape,
      ArrayList<Integer> bound) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("");
  }
}
