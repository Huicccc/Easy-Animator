package cs5004.animator.util;

import cs5004.animator.model.IModel;
import cs5004.animator.model.ModelImpl;
import java.util.HashMap;


/**
 * The class Animation builder implements AnimationBuilder, a helper class to build model using
 * current model. It is called in main and returns a model to pass the model to the controller, as a
 * parameter of AnimationReader parseFile method.
 */
public class AnimationBuilderImpl implements AnimationBuilder<IModel> {

  private ModelImpl model;
  private HashMap<String, String> shapeType;

  /**
   * Instantiates a new Animation builder.
   */
  public AnimationBuilderImpl() {
    this.model = new ModelImpl();
    this.shapeType = new HashMap<>();
  }

  @Override
  public IModel build() {
    return this.model;
  }

  @Override
  public AnimationBuilder<IModel> setBounds(int x, int y, int width, int height) {
    this.model.setBounds(x, y, width, height);
    return this;
  }

  @Override
  public AnimationBuilder<IModel> declareShape(String name, String type) {
    if (!this.shapeType.containsKey(name)) {
      this.shapeType.put(name, type);
    }
    return this;
  }

  @Override
  public AnimationBuilder<IModel> addMotion(String name, int t1, int x1, int y1, int w1, int h1,
      int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    this.model.addMotion(name, this.shapeType.get(name), t1, x1, y1, w1, h1, r1, g1, b1,
        t2, x2, y2, w2, h2, r2, g2, b2);
    return this;
  }
}
