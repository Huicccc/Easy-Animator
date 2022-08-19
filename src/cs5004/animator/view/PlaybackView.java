package cs5004.animator.view;

import cs5004.animator.auxiliary.IMotion;
import cs5004.animator.auxiliary.IShape;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.TreeMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The class Playback view, functions of start, pause, resume, and restart with corresponding button
 * click, enable/disable looping enabling looping should cause the animation to automatically
 * restart once it reaches the end, increase or decrease the speed of the animation, as it is being
 * played, and immediately see the results.
 */
public class PlaybackView extends JFrame implements IView, ActionListener {

  private TreeMap<Integer, LinkedList<IShape>> tickShape;
  private JFrame frame;
  private GraphPanel graphPanel;
  private int delay;
  private Timer timer;
  private Timer disableLoopTimer;
  private int tick;

  /**
   * Instantiates a new Playback view with speed.
   *
   * @param speed the speed of the animation
   */
  public PlaybackView(int speed) {
    this.frame = new JFrame();
    frame.setSize(1000, 1000);
    frame.setLocation(0, 0);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.delay = 1000 / speed;
    this.tick = 0;
  }

  @Override
  public void displayPlaybackVisual(TreeMap<Integer, LinkedList<IShape>> tickShape,
      ArrayList<Integer> bound) {
    this.tickShape = tickShape;

    JPanel buttonPanel = new JPanel();
    String[] buttonNames = {"Start", "Pause", "Resume", "Restart", "Enable Loop", "Disable Loop",
        "Increase Speed", "Decrease Speed"};
    for (String buttonName : buttonNames) {
      JButton button = new JButton(buttonName);
      button.setName(buttonName);
      buttonPanel.add(button);
      button.addActionListener(this);
    }

    frame.add(buttonPanel, BorderLayout.SOUTH);
    this.graphPanel = new GraphPanel(bound);
    frame.add(graphPanel, BorderLayout.CENTER);
    frame.setVisible(true);

    this.timer = new Timer(this.delay, e -> {
      this.draw(graphPanel, tickShape.get(tick));
      if (tick >= tickShape.size()) {
        tick = 0;
      } else {
        tick++;
      }
    });

    this.disableLoopTimer = new Timer(this.delay, e -> {
      tick++;
      if (tick >= tickShape.size()) {
        disableLoopTimer.stop();
      } else {
        this.draw(graphPanel, tickShape.get(tick));
      }
    });
  }

  private void draw(GraphPanel graphPanel, LinkedList<IShape> shapeList) {
    graphPanel.setShape(shapeList);
    graphPanel.repaint();
    timer.setDelay(delay);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Component c = (Component) e.getSource();
    if (c.getName().equals("Start")) {
      timer.start();
    }
    if (c.getName().equals("Pause")) {
      timer.stop();
    }
    if (c.getName().equals("Resume")) {
      timer.start();
    }
    if (c.getName().equals("Restart")) {
      tick = 0;
      timer.restart();
    }
    if (c.getName().equals("Enable Loop")) {
      timer.start();
    }
    if (c.getName().equals("Disable Loop")) {
      timer.stop();
      disableLoopTimer.start();
    }
    if (c.getName().equals("Increase Speed")) {
      delay = delay - 20;
      if (delay <= 0) {
        delay = 1;
      }
      timer.setDelay(delay);
    }
    if (c.getName().equals("Decrease Speed")) {
      delay = delay + 20;
      timer.setDelay(delay);
    }
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
      ArrayList<Integer> bound) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("");
  }
}
