package cs5004.animator.auxiliary;

/**
 * The interface of Shape implemented by ADTShape.
 */
public interface IShape {

  /**
   * Gets name of shape.
   *
   * @return the name of shape
   */
  String getName();

  /**
   * Gets current tick of shape.
   *
   * @return the tick of shape
   */
  int getTick();

  /**
   * Gets position of shape.
   *
   * @return the position of shape
   */
  Position getPosition();

  /**
   * Gets width of shape.
   *
   * @return the width of shape
   */
  int getWidth();

  /**
   * Gets height of shape.
   *
   * @return the height of shape
   */
  int getHeight();

  /**
   * Gets color of shape.
   *
   * @return the color of shape
   */
  Color getColor();

  /**
   * Copy shape.
   *
   * @return the shape
   */
  IShape copy();

  /**
   * Gets type of shape, either Rectangle or Ellipse.
   *
   * @return the type of shape, either Rectangle or Ellipse
   */
  Type getType();

  @Override
  String toString();
}
