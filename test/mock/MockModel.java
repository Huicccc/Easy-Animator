package mock;

import cs5004.animator.model.ModelImpl;

/**
 * The Mock model for testing, contains two different models.
 */
public class MockModel {

  private ModelImpl model1;
  private ModelImpl model2;

  /**
   * Instantiates a new Mock model.
   */
  public MockModel() {
    this.model1 = model1();
    this.model2 = model2();
  }

  /**
   * Gets model 1.
   *
   * @return the model 1
   */
  public ModelImpl getModel1() {
    return model1;
  }

  /**
   * Gets model 2.
   *
   * @return the model 2
   */
  public ModelImpl getModel2() {
    return model2;
  }

  private ModelImpl model1() {
    ModelImpl model = new ModelImpl();
    model.setSpeed(1);
    model.setBounds(100, 200, 600, 700);
    model.addMotion("C", "ellipse", 5, 440, 70, 120, 60, 0, 0, 255, 10, 440, 70, 120, 60, 0, 0,
        255);
    model.addMotion("R", "rectangle", 1, 200, 200, 50, 100, 255, 0, 0, 10, 200, 200, 50, 100, 255,
        0, 0);
    model.addMotion("R", "rectangle", 10, 200, 200, 50, 100, 255, 0, 0, 20, 100, 200, 50, 100, 255,
        0, 0);
    model.addMotion("C", "ellipse", 10, 440, 70, 120, 60, 0, 0, 255, 20, 440, 70, 120, 60, 255, 0,
        255);
    model.addMotion("C", "ellipse", 20, 440, 70, 120, 60, 255, 0, 255, 30, 440, 70, 100, 60, 255, 0,
        255);
    model.addMotion("C", "ellipse", 30, 440, 70, 100, 60, 255, 0, 255, 40, 440, 70, 100, 60, 255, 0,
        255);
    return model;
  }

  private ModelImpl model2() {
    ModelImpl model = new ModelImpl();
    model.setSpeed(10);
    model.setBounds(20, 50, 600, 300);
    model.addMotion("C", "ellipse", 5, 440, 70, 120, 60, 0, 0, 255, 10, 440, 70, 120, 60, 0, 0,
        255);
    model.addMotion("E", "ellipse", 1, 200, 70, 120, 60, 0, 0, 255, 10, 200, 70, 120, 60, 0, 0,
        255);
    model.addMotion("R", "rectangle", 2, 200, 200, 50, 100, 255, 0, 0, 8, 200, 200, 50, 100, 255, 0,
        0);
    model.addMotion("R", "rectangle", 8, 200, 200, 50, 100, 255, 0, 0, 20, 100, 200, 50, 100, 255,
        0, 0);
    model.addMotion("C", "ellipse", 10, 440, 70, 120, 60, 0, 0, 255, 15, 440, 70, 120, 60, 255, 0,
        255);
    model.addMotion("C", "ellipse", 15, 440, 70, 120, 60, 255, 0, 255, 20, 440, 70, 100, 60, 255, 0,
        255);
    model.addMotion("C", "ellipse", 20, 440, 70, 100, 60, 255, 0, 255, 40, 440, 70, 100, 60, 255, 0,
        255);
    return model;
  }

}
