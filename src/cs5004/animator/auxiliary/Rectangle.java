package cs5004.animator.auxiliary;

/**
 * The class Rectangle extends ADTShape represents a rectangle shape.
 */
public class Rectangle extends ADTShape {

  /**
   * Instantiates a new Rectangle.
   *
   * @param name the name of rectangle
   * @param t    the tick of current rectangle
   * @param x    the x position of current rectangle
   * @param y    the y position of current rectangle
   * @param w    the width of current rectangle
   * @param h    the height of current rectangle
   * @param r    the red color of current rectangle
   * @param g    the green color of current rectangle
   * @param b    the blue color of current rectangle
   */
  public Rectangle(String name, int t, int x, int y, int w, int h, int r, int g, int b) {
    super(name, t, x, y, w, h, r, g, b);
  }

  public Type getType() {
    return Type.RECTANGLE;
  }

  @Override
  public IShape copy() {
    return new Rectangle(this.getName(), this.getTick(), this.getPosition().getX(),
        this.getPosition().getY(), this.getWidth(), this.getHeight(), this.getColor().getR(),
        this.getColor().getG(), this.getColor().getB());
  }
}
