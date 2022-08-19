package cs5004.animator.auxiliary;

/**
 * The class Motion implement IMotion, contains getters to get start shape of the movement and end
 * shape of the movement, and copy motion.
 */
public class MotionImp implements IMotion {

  private final IShape startShape;
  private final IShape endShape;

  /**
   * Instantiates a new Motion imp.
   *
   * @param startShape the shape at the beginning of movement
   * @param endShape   the shape at the end of movement
   */
  public MotionImp(IShape startShape, IShape endShape) {
    this.startShape = startShape;
    this.endShape = endShape;
  }

  @Override
  public IMotion copy() {
    return new MotionImp(this.startShape.copy(), this.endShape.copy());
  }

  @Override
  public IShape getStartShape() {
    return this.startShape;
  }

  @Override
  public IShape getEndShape() {
    return this.endShape;
  }

  @Override
  public String toString() {
    return startShape.getName() + " " + startShape.getType() + " " + startShape.getPosition() + " "
        + startShape.getWidth() + " " + startShape.getHeight() + " " + startShape.getColor()
        + " " + endShape.getPosition() + " " + endShape.getHeight() + " " + endShape.getWidth()
        + " " + endShape.getColor();
  }
}
