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
 * The class Svg view will produce a textual description of the animation that is compliant with the
 * SVG file format.
 */
public class SVGView implements IView {

  private Appendable output;

  /**
   * Instantiates a new Svg view with the output.
   *
   * @param output the output can be System.Out or a file name that being written into this file
   *               parameter
   */
  public SVGView(Appendable output) {
    this.output = output;
  }

  @Override
  public void displayText(TreeMap<Integer, LinkedList<IMotion>> tickMotion,
      LinkedHashMap<String, LinkedList<IMotion>> shapeMotion, ArrayList<Integer> bound)
      throws UnsupportedOperationException {
    throw new UnsupportedOperationException("");
  }


  @Override
  public void displaySVG(LinkedHashMap<String, LinkedList<IMotion>> shapeMotion,
      ArrayList<Integer> bound) {
    StringBuilder string = new StringBuilder();
    string.append("<svg width=\"" + (bound.get(0) + bound.get(2)) + "\" height=\"" + (bound.get(1)
        + bound.get(3))
        + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n\n" + "<rect>\n"
        + " <animate id=\"base\" begin=\"0;base.end\" dur=\"9900.0ms\" "
        + "attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n"
        + "</rect>\n"
        + "\n");

    for (Map.Entry<String, LinkedList<IMotion>> entry : shapeMotion.entrySet()) {
      IMotion appearMotion = entry.getValue().get(0);
      IShape appearShape = appearMotion.getStartShape();
      if (appearShape.getType() == Type.RECTANGLE) {
        string.append("<rect id=\"")
            .append(appearShape.getName() + "\" x=\"" + appearShape.getPosition().getX()
                + "\" y=\"" + appearShape.getPosition().getY() + "\" width=\""
                + appearShape.getWidth()
                + "\" height=\"" + appearShape.getHeight() + "\" fill=\"rgb("
                + appearShape.getColor().getR() + "," + appearShape.getColor().getG() + ","
                + appearShape.getColor().getB() + ")\" visibility=\"hidden\" >\n");
      } else {
        string.append("<ellipse id=\"")
            .append(appearShape.getName() + "\" cx=\"" + appearShape.getPosition().getX()
                + "\" cy=\"" + appearShape.getPosition().getY() + "\" rx=\""
                + appearShape.getWidth() / 2
                + "\" ry=\"" + appearShape.getHeight() / 2 + "\" fill=\"rgb("
                + appearShape.getColor().getR() + "," + appearShape.getColor().getG() + ","
                + appearShape.getColor().getB() + ")\" visibility=\"hidden\" >\n");
      }

      for (int i = 0; i < entry.getValue().size(); i++) {
        IMotion motion = entry.getValue().get(i);
        IShape startShape = motion.getStartShape();
        IShape endShape = motion.getEndShape();

        if (i == 0) {
          string.append(
              "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"visible\" begin = \""
                  + (startShape.getTick() * 100) + "ms\" dur=\""
                  + ((entry.getValue().get(entry.getValue().size() - 1).getEndShape().getTick()
                  - startShape.getTick()) * 100) + "ms\" /> \n");
        }

        if (!startShape.getColor().equals(endShape.getColor())) {
          string.append("<animate attributeType=\"xml\" begin = \"base.begin+")
              .append(startShape.getTick() * 100).append("ms\" dur=\"")
              .append((endShape.getTick() - startShape.getTick()) * 100)
              .append("ms\" attributeName=\"fill\" from=\"rgb(")
              .append(startShape.getColor().getR()).append(",")
              .append(startShape.getColor().getG()).append(",")
              .append(startShape.getColor().getB()).append(")\" to=\"rgb(")
              .append(endShape.getColor().getR()).append(",")
              .append(endShape.getColor().getG()).append(",")
              .append(endShape.getColor().getB()).append(")\" fill=\"freeze\" />\n");
        }
        if (!startShape.getPosition().equals(endShape.getPosition())) {
          if (appearShape.getType() == Type.ELLIPSE) {
            string.append("<animate attributeType=\"xml\" begin = \"base.begin+")
                .append(startShape.getTick() * 100).append("ms\" dur=\"")
                .append((endShape.getTick() - startShape.getTick()) * 100)
                .append("ms\" attributeName=\"cx\" from=\"")
                .append(startShape.getPosition().getX()).append("\" to=\"")
                .append(endShape.getPosition().getX()).append("\" fill=\"freeze\" />\n");
            string.append("<animate attributeType=\"xml\" begin = \"base.begin+")
                .append(startShape.getTick() * 100).append("ms\" dur=\"")
                .append((endShape.getTick() - startShape.getTick()) * 100)
                .append("ms\" attributeName=\"cy\" from=\"")
                .append(startShape.getPosition().getY()).append("\" to=\"")
                .append(endShape.getPosition().getY()).append("\" fill=\"freeze\" />\n");
          } else {
            string.append("<animate attributeType=\"xml\" begin = \"base.begin+")
                .append(startShape.getTick() * 100).append("ms\" dur=\"")
                .append((endShape.getTick() - startShape.getTick()) * 100)
                .append("ms\" attributeName=\"x\" from=\"")
                .append(startShape.getPosition().getX()).append("\" to=\"")
                .append(endShape.getPosition().getX()).append("\" fill=\"freeze\" />\n");
            string.append("<animate attributeType=\"xml\" begin = \"base.begin+")
                .append(startShape.getTick() * 100).append("ms\" dur=\"")
                .append((endShape.getTick() - startShape.getTick()) * 100)
                .append("ms\" attributeName=\"y\" from=\"")
                .append(startShape.getPosition().getY()).append("\" to=\"")
                .append(endShape.getPosition().getY()).append("\" fill=\"freeze\" />\n");
          }
        }
        if (startShape.getHeight() != endShape.getHeight()) {
          if (appearShape.getType() == Type.ELLIPSE) {
            string.append("<animate attributeType=\"xml\" begin = \"base.begin+")
                .append(startShape.getTick() * 100).append("ms\" dur=\"")
                .append((endShape.getTick() - startShape.getTick()) * 100)
                .append("ms\" attributeName=\"ry\" from=\"")
                .append(startShape.getHeight() / 2).append("\" to=\"")
                .append(endShape.getHeight() / 2).append("\" fill=\"freeze\" />\n");
          } else {
            string.append("<animate attributeType=\"xml\" begin = \"base.begin+")
                .append(startShape.getTick() * 100).append("ms\" dur=\"")
                .append((endShape.getTick() - startShape.getTick()) * 100)
                .append("ms\" attributeName=\"height\" from=\"")
                .append(startShape.getHeight()).append("\" to=\"")
                .append(endShape.getHeight()).append("\" fill=\"freeze\" />\n");
          }
        }
        if (startShape.getWidth() != endShape.getWidth()) {
          if (appearShape.getType() == Type.ELLIPSE) {
            string.append("<animate attributeType=\"xml\" begin = \"base.begin+")
                .append(startShape.getTick() * 100).append("ms\" dur=\"")
                .append((endShape.getTick() - startShape.getTick()) * 100)
                .append("ms\" attributeName=\"rx\" from=\"")
                .append(startShape.getWidth() / 2).append("\" to=\"")
                .append(endShape.getWidth() / 2).append("\" fill=\"freeze\" />\n");
          } else {
            string.append("<animate attributeType=\"xml\" begin = \"base.begin+")
                .append(startShape.getTick() * 100).append("ms\" dur=\"")
                .append((endShape.getTick() - startShape.getTick()) * 100)
                .append("ms\" attributeName=\"width\" from=\"")
                .append(startShape.getWidth()).append("\" to=\"")
                .append(endShape.getWidth()).append("\" fill=\"freeze\" />\n");
          }
        }
      }

      if (appearShape.getType() == Type.RECTANGLE) {
        string.append("</rect>\n\n");
      } else {
        string.append("</ellipse>\n\n");
      }
    }
    string.append("\n</svg>");
    try {
      this.output.append(string);
    } catch (IOException e) {
      throw new IllegalStateException("Error when display SVG.");
    }
  }

  @Override
  public void displayVisual(TreeMap<Integer, LinkedList<IShape>> tickShape,
      ArrayList<Integer> bound) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("");
  }

  @Override
  public void displayPlaybackVisual(TreeMap<Integer, LinkedList<IShape>> tickShape,
      ArrayList<Integer> bound) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("");
  }
}
