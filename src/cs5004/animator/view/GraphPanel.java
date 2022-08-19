package cs5004.animator.view;

import cs5004.animator.auxiliary.IShape;
import cs5004.animator.auxiliary.Type;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 * The class Graph panel extends JPanel set graphic drawing JPanel and paint with the data of shape
 * list.
 */
public class GraphPanel extends JPanel {

  private LinkedList<IShape> shapeList;

  /**
   * Instantiates a new Graph panel.
   *
   * @param bound the bound of canvas
   */
  public GraphPanel(ArrayList<Integer> bound) {
    super(true);
    this.setBackground(Color.WHITE);
    this.setSize(bound.get(2), bound.get(3));
  }

  /**
   * Pass the shape list data into JPanel.
   *
   * @param shapeList the shape list at the same tick
   */
  public void setShape(LinkedList<IShape> shapeList) {
    this.shapeList = shapeList;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (shapeList != null) {
      for (IShape shape : this.shapeList) {
        int x = shape.getPosition().getX();
        int y = shape.getPosition().getY();
        int width = shape.getWidth();
        int height = shape.getHeight();
        g.setColor(
            new Color(shape.getColor().getR(), shape.getColor().getG(), shape.getColor().getB()));
        if (shape.getType() == Type.RECTANGLE) {
          g.fillRect(x, y, width, height);
        } else {
          g.fillOval(x, y, width, height);
        }
      }
    }
  }

}
