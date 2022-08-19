package cs5004.animator.controller;

/**
 * Controller is the controller part of MVC model which is initialized with model and view. Method
 * animate pass the data of model into view. Method transform manipulating the data passed from
 * model into the data form that view needs.
 */
public interface IController {

  /**
   * Animate method takes data from model, passed transformed data into view, and makes view to
   * display visualize results.
   */
  void animate();
}
