package cs5004.animator.controller;

import cs5004.animator.auxiliary.Ellipse;
import cs5004.animator.auxiliary.IMotion;
import cs5004.animator.auxiliary.IShape;
import cs5004.animator.auxiliary.Rectangle;
import cs5004.animator.auxiliary.Type;
import cs5004.animator.model.IModel;
import cs5004.animator.view.IView;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * The class Controller is the controller part of MVC model which is initialized with model and
 * view. Method animate pass the data of model into view. Method transform manipulating the data
 * passed from model into the data form that view needs.
 */
public class Controller implements IController {

  private IView view;
  private IModel model;

  /**
   * Instantiates a new Controller.
   *
   * @param model the model of Animator
   * @param view  the view of Animator
   */
  public Controller(IModel model, IView view) {
    this.view = view;
    this.model = model;
  }

  private TreeMap<Integer, LinkedList<IShape>> transform(
      TreeMap<Integer, LinkedList<IMotion>> tickMotion) {
    TreeMap<Integer, LinkedList<IShape>> eachTick = new TreeMap<>();
    for (Map.Entry<Integer, LinkedList<IMotion>> entry : tickMotion.entrySet()) {
      for (int j = 0; j < entry.getValue().size(); j++) {
        IMotion motion = entry.getValue().get(j);
        IShape startShape = motion.getStartShape();
        IShape endShape = motion.getEndShape();
        int startTick = startShape.getTick();
        int endTick = endShape.getTick();
        for (int i = startTick; i < endTick; i++) {
          int x = startShape.getPosition().getX();
          int y = startShape.getPosition().getY();
          int w = startShape.getWidth();
          int h = startShape.getHeight();
          int r = startShape.getColor().getR();
          int g = startShape.getColor().getG();
          int b = startShape.getColor().getB();
          if (!startShape.getColor().equals(endShape.getColor())) {
            r =
                startShape.getColor().getR() * (endTick - i) / (endTick - startTick)
                    + endShape.getColor().getR() * (i - startTick) / (endTick
                    - startTick);
            g =
                startShape.getColor().getG() * (endTick - i) / (endTick - startTick)
                    + endShape.getColor().getG() * (i - startTick) / (endTick
                    - startTick);
            b =
                startShape.getColor().getB() * (endTick - i) / (endTick - startTick)
                    + endShape.getColor().getB() * (i - startTick) / (endTick
                    - startTick);
          }
          if (!startShape.getPosition().equals(endShape.getPosition())) {
            x =
                startShape.getPosition().getX() * (endTick - i) / (endTick - startTick)
                    + endShape.getPosition().getX() * (i - startTick) / (endTick
                    - startTick);
            y =
                startShape.getPosition().getY() * (endTick - i) / (endTick - startTick)
                    + endShape.getPosition().getY() * (i - startTick) / (endTick
                    - startTick);
          }
          if (startShape.getHeight() != endShape.getHeight()) {
            h =
                startShape.getHeight() * (endTick - i) / (endTick - startTick)
                    + endShape.getHeight() * (i - startTick) / (endTick
                    - startTick);
          }
          if (startShape.getWidth() != endShape.getWidth()) {
            w =
                startShape.getWidth() * (endTick - i) / (endTick - startTick)
                    + endShape.getWidth() * (i - startTick) / (endTick
                    - startTick);
          }

          if (eachTick.containsKey(i)) {
            if (startShape.getType() == Type.ELLIPSE) {
              eachTick.get(i).add(new Ellipse(startShape.getName(), i, x, y, w, h, r, g, b));
            } else {
              eachTick.get(i).add(new Rectangle(startShape.getName(), i, x, y, w, h, r, g, b));
            }
          } else {
            LinkedList<IShape> shapeList = new LinkedList<>();
            if (startShape.getType() == Type.ELLIPSE) {
              shapeList.add(new Ellipse(startShape.getName(), i, x, y, w, h, r, g, b));
            } else {
              shapeList.add(new Rectangle(startShape.getName(), i, x, y, w, h, r, g, b));
            }
            eachTick.put(i, shapeList);
          }
        }
      }
    }
    return eachTick;
  }

  @Override
  public void animate() {
    try {
      view.displayText(this.model.copyTickMotion(), this.model.copyShapeMotion(),
          this.model.copyBound());
    } catch (UnsupportedOperationException e1) {
      try {
        view.displaySVG(this.model.copyShapeMotion(), this.model.copyBound());
      } catch (UnsupportedOperationException e2) {
        try {
          view.displayVisual(transform(this.model.copyTickMotion()), this.model.copyBound());
        } catch (UnsupportedOperationException e3) {
          view.displayPlaybackVisual(transform(this.model.copyTickMotion()),
              this.model.copyBound());
        }
      }
    }
  }
}
