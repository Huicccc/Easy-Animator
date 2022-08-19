package cs5004.animator.auxiliary;

/**
 * The class of Color consisting red, green and blue fields.
 */
public class Color {

  private final int r;
  private final int g;
  private final int b;

  /**
   * Instantiates a new Color.
   *
   * @param r the red element of color
   * @param g the green element of color
   * @param b the blue element of color
   */
  public Color(int r, int g, int b) {
    if (r < 0 || g < 0 || b < 0 || r > 255 | g > 255 | b > 255) {
      throw new IllegalArgumentException("Invalid color input!");
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Gets red part of color.
   *
   * @return the red field
   */
  public int getR() {
    return r;
  }

  /**
   * Gets green part of color.
   *
   * @return the green field
   */
  public int getG() {
    return g;
  }

  /**
   * Gets blue part of color.
   *
   * @return the blue field
   */
  public int getB() {
    return b;
  }

  @Override
  public String toString() {
    return "red " + this.r + ", green " + this.g + ", blue " + this.b;
  }

  @Override
  public boolean equals(Object o) {
    Color other = (Color) o;
    return r == other.getR() && g == other.getG() && b == other.getB();
  }

  @Override
  public int hashCode() {
    return r + g + b;
  }
}
