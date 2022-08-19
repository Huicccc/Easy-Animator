package cs5004.animator.auxiliary;

/**
 * The class Position represents the position of shape contains x and y fields.
 */
public class Position {

  private final int x;
  private final int y;

  /**
   * Instantiates a new Position.
   *
   * @param x the x position of shape
   * @param y the y position of shape
   */
  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Gets x position of shape.
   *
   * @return the x position of shape
   */
  public int getX() {
    return x;
  }

  /**
   * Gets y position of shape.
   *
   * @return the y position of shape
   */
  public int getY() {
    return y;
  }

  @Override
  public String toString() {
    return "(" + this.x + "," + this.y + ")";
  }


  @Override
  public boolean equals(Object o) {
    Position other = (Position) o;
    return x == other.getX() && y == other.getY();
  }

  @Override
  public int hashCode() {
    return x + y;
  }
}
