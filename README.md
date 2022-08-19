# Easy-Animator

An application that facilitates the creation of simple but effective 2D animations from shapes, decoupling the description of an algorithm or phenomenon from its visual representation on the screen. This separation will enable the creation of an application that will render an animation according to its textual description, independent of how the description was generated. 

The command-line arguments are illustrated below:
-in "name-of-animation-file"
-out "where-output-show-go"
—view “text”, “svg”, “visual”, or "playback"
-speed "integer-ticks-per-second"

Here are some examples of valid command-line arguments and what they mean:
1. java -jar Animator.jar -in smalldemo.txt -view text -speed 2: use smalldemo.txt for the animation file, and create a text view with its output going to System.out, and a speed of 2 ticks per second.
2. java -jar Animator.jar -view svg -out out.svg -in toh-12.txt: use toh-12.txt for the animation file, and create an SVG view with its output going to the file out.svg, with a speed of 1 tick per second.
3. -in smalldemo.txt -view text: use smalldemo.txt for the animation file, and create a text view with its output going to System.out.
-in smalldemo.txt -speed 50 -view visual: use smalldemo.txt for the animation file, and create a visual view to show the animation at a speed of 50 ticks per second.
4. -in big-bang-big-crunc -speed 50 -view playback: use big-bang-big-crunch for the animation file, and create a playback view with speed and playing editor.


src.cs5004.animator:
1. Class EasyAnimator: contains main().
2. model package:
    1) Interface IModel
    2) Class ModelImpl implements IModel
3. controller package:
    1) Interface IController
    2) Class Controller implements IController
4. view package:
    1) Interface IView
    2) Class SVGView, TextView, VisualView and PlayBackView implements IView
    3) Class GraphPanel is composition of VisualView and PlayBackView
5. auxiliary package:
    1) Interface IMotion - Class MotionImp implements IMotion
                         - composition IShape: has-a start-shape and end-shape
    2) Interface IShape - Class ADTShape implements IShape
                        - Class Rectangle and Ellipse extend ADTShape
                        - composition Class Color, Position
    3) Type: Enum contains Rectangle, Ellipse
6. util package:
    1) Interface AnimationBuilder
    2) Class AnimationBuilderImpl implements AnimationBuilder
    3) Class AnimationReader

test:
1. mock package:
    1) mock model: Class MockModel
    2) mock view: Class MockVisualView, MockTextView. MockSVGView, MockPlayBackView
2. ControllerTest: test controller
3. ModelTest: test model
4. TextViewTest: test text view
5. SVGTest: test SVG view
6. VisualViewTest: test visual view
