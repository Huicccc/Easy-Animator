package cs5004.animator.view;

import cs5004.animator.auxiliary.IMotion;
import cs5004.animator.auxiliary.IShape;
import cs5004.animator.auxiliary.Type;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * The class Text view will produce a textual description of the animation, first describes the
 * shapes that are part of the animation and their details, * then describes how these shapes will
 * move as the animation proceeds from start to finish.
 */
public class TextView implements IView {

  private Appendable output;

  /**
   * Instantiates a new Text view.
   *
   * @param output the output can be System.Out or a file name that being written into this file *
   *               parameter
   */
  public TextView(Appendable output) {
    this.output = output;
  }

  @Override
  public void displayText(TreeMap<Integer, LinkedList<IMotion>> tickMotion,
      LinkedHashMap<String, LinkedList<IMotion>> shapeMotion,
      ArrayList<Integer> bound) {
    for (Map.Entry<String, LinkedList<IMotion>> entry : shapeMotion.entrySet()) {
      IShape startShape = entry.getValue().get(0).getStartShape();
      String string;
      if (startShape.getType() == Type.ELLIPSE) {
        string =
            "Create oval " + startShape.getName() + " with center at " + startShape.getPosition()
                + ", radius " + startShape.getHeight() + " and " + startShape.getWidth()
                + ", color "
                + startShape.getColor() + "\n";
      } else {
        string = "Create rectangle " + startShape.getName() + " with corner at "
            + startShape.getPosition()
            + ", height " + startShape.getHeight() + " and width " + startShape.getWidth()
            + ", color "
            + startShape.getColor() + "\n";
      }
      try {
        output.append(string);
      } catch (IOException e) {
        throw new IllegalStateException("Error when trying to get shape list.");
      }
    }

    for (Map.Entry<String, LinkedList<IMotion>> entry : shapeMotion.entrySet()) {
      IMotion firstMotion = entry.getValue().get(0);
      IMotion lastMotion = entry.getValue().get(entry.getValue().size() - 1);
      IShape lastStartShape = lastMotion.getStartShape();
      IShape lastEndShape = lastMotion.getEndShape();
      String string;
      if (lastStartShape.getPosition().equals(lastEndShape.getPosition())
          && lastStartShape.getColor().equals(lastEndShape.getColor())
          && lastStartShape.getHeight() == lastEndShape.getHeight()
          && lastStartShape.getWidth() == lastEndShape.getWidth()) {
        string = lastStartShape.getName() + " appears at time t="
            + firstMotion.getStartShape().getTick() + " and disappears at time t="
            + lastEndShape.getTick() + "\n";
      } else {
        string = firstMotion.getStartShape().getName() + " appears at time t="
            + firstMotion.getStartShape().getTick() + " and stays\n";
      }
      try {
        output.append(string);
      } catch (IOException e) {
        throw new IllegalStateException("Error when trying to get shape appearance.");
      }
    }

    for (Map.Entry<Integer, LinkedList<IMotion>> entry : tickMotion.entrySet()) {
      for (int i = 0; i < entry.getValue().size(); i++) {
        IMotion motion = entry.getValue().get(i);
        IShape startShape = motion.getStartShape();
        IShape endShape = motion.getEndShape();
        String string = "";
        if (!startShape.getColor().equals(endShape.getColor())) {
          string +=
              startShape.getName() + ": change color from " + startShape.getColor() + " to "
                  + endShape.getColor() + ", from time t=" + startShape.getTick() + " to t="
                  + endShape.getTick() + "\n";
        }
        if (!startShape.getPosition().equals(endShape.getPosition())) {
          string +=
              startShape.getName() + ": change position from " + startShape.getPosition() + " to "
                  + endShape.getPosition() + " from time t=" + startShape.getTick() + " to t="
                  + endShape.getTick() + "\n";
        }
        if (startShape.getHeight() != endShape.getHeight()) {
          string +=
              startShape.getName() + ": change height from " + startShape.getHeight() + " to "
                  + endShape.getHeight() + " from time t=" + startShape.getTick() + " to t="
                  + endShape.getTick() + "\n";
        }
        if (startShape.getWidth() != endShape.getWidth()) {
          string +=
              startShape.getName() + ": change width from " + startShape.getWidth() + " to "
                  + endShape.getWidth() + " from time t=" + startShape.getTick() + " to t="
                  + endShape.getTick() + "\n";
        } else {
          string += "";
        }
        try {
          output.append(string);
        } catch (IOException e) {
          throw new IllegalStateException("Error when trying to get shape motion.");
        }
      }
    }
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
      ArrayList<Integer> bound) {
    throw new UnsupportedOperationException("");
  }
}
