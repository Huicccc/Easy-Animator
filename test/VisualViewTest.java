import cs5004.animator.view.PlaybackView;
import mock.MockModel;
import cs5004.animator.controller.Controller;
import cs5004.animator.model.ModelImpl;

/**
 * The Visual view test.
 */
public class VisualViewTest {

  /**
   * The visual test.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    ModelImpl model2 = (new MockModel()).getModel2();
    PlaybackView playbackView = new PlaybackView(model2.getSpeed());
    Controller controller2 = new Controller(model2, playbackView);
    controller2.animate();
  }
}
