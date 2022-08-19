package cs5004.animator;

import cs5004.animator.controller.Controller;
import cs5004.animator.model.IModel;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.IView;
import cs5004.animator.view.PlaybackView;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextView;
import cs5004.animator.view.VisualView;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The class Easy animator contains main function.
 * Need to create an Application run configuration that chooses cs5004.animator.EasyAnimator as its
 * main class. In this run configuration, specify command-line arguments. Program will take inputs
 * as command-line arguments (available in program through the argument args above).
 */
public final class EasyAnimator {

  /**
   * The entry point of application. Program needs to take inputs as command-line arguments.
   * The command-line arguments will be of the form: -in "name-of-animation-file" -view
   * "type-of-view" -out "where-output-show-go" -speed "integer-ticks-per-second"
   * example:
   * -in smalldemo.txt -speed 50 -view visual: use smalldemo.txt for the animation file, and create
   * a visual view to show the animation at a speed of 50 ticks per second.
   *
   * @param args the input arguments taken from command-line, characteristics of a valid input are:
   *             1. Each pair of arguments (-in "input-file", -out "output-file", etc.) may appear
   *             in any order (e.g. the -view pair can appear first, followed by -in and so on) 2.
   *             Each pair of arguments are ordered, if the user types -in then the next input must
   *             be the name of an input file, and so on. 3.Providing an input file (the -in pair)
   *             and a view (the -view pair) are mandatory. If the output set is not specified and
   *             the view needs it, the default should be System.out. If the speed is not specified
   *             and the view needs it, the default is 1 tick per second. 4."visual" for visual
   *             view, "text" for text view, "svg" for svg view, "playback" for visual view with
   *             editor
   */
  public static void main(String[] args) {
    String inputName = "";
    String viewName = "";
    String outputName = "";
    int speed = 1;

    for (int i = 0; i < args.length; i++) {
      if (args[i].contentEquals("-in")) {
        try {
          if (args[i + 1].charAt(0) == '-') {
            throw new IllegalArgumentException("-in must be followed by an input file name");
          }
          inputName = args[i + 1];
        } catch (NullPointerException e) {
          System.out.println(e.getMessage());
        }
      }

      if (args[i].contentEquals("-out")) {
        try {
          if (args[i + 1].charAt(0) == '-') {
            throw new IllegalArgumentException("-in must be followed by a output file name");
          }
          outputName = args[i + 1];
        } catch (NullPointerException e) {
          System.out.println(e.getMessage());
        }
      }

      if (args[i].contentEquals("-view")) {
        try {
          if (args[i + 1].charAt(0) == '-') {
            throw new IllegalArgumentException("-in must be followed by a view name");
          }
          viewName = args[i + 1];
          if (!(viewName.contentEquals("visual") || viewName.contentEquals("text")
              || viewName.contentEquals("svg") || viewName.contentEquals("playback"))) {
            throw new IllegalArgumentException("View must be txt, svg, visual or playback");
          }
        } catch (NullPointerException e) {
          System.out.println(e.getMessage());
        }
      }

      if (args[i].contentEquals("-speed")) {
        try {
          if (args[i + 1].charAt(0) == '-') {
            throw new IllegalArgumentException("-in must be followed by an integer");
          }
          speed = Integer.parseInt(args[i + 1]);
        } catch (NullPointerException e) {
          System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
          throw new IllegalArgumentException("Speed must be an integer!");
        }
      }
    }

    IModel model;
    try {
      model = AnimationReader.parseFile(new FileReader(inputName), new AnimationBuilderImpl());
    } catch (FileNotFoundException ioe) {
      throw new IllegalArgumentException("Input file not found!");
    }
    model.setSpeed(speed);

    IView view;
    FileWriter fileWriter = null;

    if (viewName.contentEquals("text") || viewName.contentEquals("svg")) {
      if (outputName.equals("")) {
        if (viewName.contentEquals("text")) {
          view = new TextView(System.out);
        } else {
          view = new SVGView(System.out);
        }
      } else {
        try {
          fileWriter = new FileWriter(outputName);
        } catch (IOException e) {
          System.out.println(e.getMessage());
        }
        if (viewName.contentEquals("text")) {
          view = new TextView(fileWriter);
        } else {
          view = new SVGView(fileWriter);
        }
      }
    } else if (viewName.contentEquals("visual")) {
      view = new VisualView(model.getSpeed());
    } else {
      view = new PlaybackView(model.getSpeed());
    }

    Controller controller = new Controller(model, view);
    controller.animate();

    if (viewName.contentEquals("text") || viewName.contentEquals("svg")) {
      try {
        fileWriter.close();
      } catch (IOException e) {
        throw new IllegalArgumentException("output file is null!");
      }
    }
  }
}

