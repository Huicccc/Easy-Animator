## About

A Java application that facilitates the creation of simple but effective 2D animations from shapes, decoupling the description of an algorithm or phenomenon from its visual representation on the screen. This separation will enable the creation of an application that will render an animation according to its textual description, independent of how the description was generated. 

![big-bang-big-crunch](https://user-images.githubusercontent.com/105135459/206825445-ccdb201e-9757-4c20-b46d-60275abc56a2.gif)

![night](https://user-images.githubusercontent.com/105135459/206825501-4175ba71-096c-4c02-9903-d0c586b3c5c7.gif)


![toh-5](https://user-images.githubusercontent.com/105135459/206825537-1fa01703-11d5-48a1-aa49-2c4fb9e7a773.gif)

## User Manual
- The command-line arguments are illustrated below: </br>
```-in```     "name-of-animation-file" </br>
```-out```    "where-output-show-go" </br>
```-view```   "text", "svg", "visual", or "playback" </br>
```-speed```  "integer-ticks-per-second" </br>

Here are some examples of valid command-line arguments and what they mean: </br>
- Use smalldemo.txt for the animation file, and create a text view with its output going to System.out, and a speed of 2 ticks per second.</br>
```java -jar Animator.jar -in smalldemo.txt -view text -speed 2``` 
- Use toh-12.txt for the animation file, and create an SVG view with its output going to the file out.svg, with a speed of 1 tick per second.</br>
```java -jar Animator.jar -view svg -out out.svg -in toh-12.txt```
- Use smalldemo.txt for the animation file, and create a visual view to show the animation at a speed of 50 ticks per second.</br>
```in smalldemo.txt -speed 50 -view visual```
- Use big-bang-big-crunch for the animation file, and create a playback view with speed and playing editor.</br>
```-in big-bang-big-crunc -speed 50 -view playback```

## MVC Design
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

## Unit-testing
test:
1. mock package:
    1) mock model: Class MockModel
    2) mock view: Class MockVisualView, MockTextView. MockSVGView, MockPlayBackView
2. ControllerTest: test controller
3. ModelTest: test model
4. TextViewTest: test text view
5. SVGTest: test SVG view
6. VisualViewTest: test visual view
