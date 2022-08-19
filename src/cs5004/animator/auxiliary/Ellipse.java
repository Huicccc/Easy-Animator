package cs5004.animator.auxiliary;

/**
 * The class of shape Ellipse.
 */
public class Ellipse extends ADTShape {

  /**
   * Instantiates a new Ellipse with given name, tick, position, width, height and color.
   *
   * @param name the name of ellipse
   * @param t    the tick of current ellipse
   * @param x    the x position of ellipse
   * @param y    the y position of ellipse
   * @param w    the width of ellipse
   * @param h    the height of ellipse
   * @param r    the red color of ellipse
   * @param g    the green color of ellipse
   * @param b    the blue color of ellipse
   */
  public Ellipse(String name, int t, int x, int y, int w, int h, int r, int g, int b) {
    super(name, t, x, y, w, h, r, g, b);
  }

  public Type getType() {
    return Type.ELLIPSE;
  }

  @Override
  public IShape copy() {
    return new Ellipse(this.getName(), this.getTick(), this.getPosition().getX(),
        this.getPosition().getY(), this.getWidth(), this.getHeight(), this.getColor().getR(),
        this.getColor().getG(), this.getColor().getB());
  }
}
