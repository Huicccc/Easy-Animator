package cs5004.animator.auxiliary;

/**
 * ADTShape is abstract class implements interface IShape. Ellipse and Rectangle extend ADTShape.
 */
public abstract class ADTShape implements IShape {

  private final String name;
  private final int tick;
  private final Position position;
  private final int height;
  private final int width;
  private final Color color;

  /**
   * Instantiates a new shape with name, tick, position, width, height and color.
   *
   * @param name the name of shape
   * @param t    the current tick of shape
   * @param x    the x position of shape
   * @param y    the y position of shape
   * @param w    the width of shape
   * @param h    the height of shape
   * @param r    the red color of shape
   * @param g    the green color of shape
   * @param b    the blue color of shape
   */
  public ADTShape(String name, int t, int x, int y, int w, int h, int r, int g, int b) {
    this.name = name;
    this.tick = t;
    this.position = new Position(x, y);
    this.height = h;
    this.width = w;
    this.color = new Color(r, g, b);
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getTick() {
    return this.tick;
  }

  @Override
  public Position getPosition() {
    return this.position;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }


  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public String toString() {
    return this.getName() + " " + this.getType() + " " + this.getTick() + " " + this.getPosition()
        + " " + this.getWidth() + " " + this.getHeight() + " " + this.getColor() + "\n";
  }
}
