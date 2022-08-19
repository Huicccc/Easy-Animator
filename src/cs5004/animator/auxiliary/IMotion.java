package cs5004.animator.auxiliary;

/**
 * The interface Motion represents one movement of a shape with same name.
 */
public interface IMotion {

  /**
   * Gets start shape.
   *
   * @return the start shape
   */
  IShape getStartShape();

  /**
   * Gets end shape.
   *
   * @return the end shape
   */
  IShape getEndShape();

  /**
   * Copy motion.
   *
   * @return the motion
   */
  IMotion copy();

  @Override
  String toString();
}