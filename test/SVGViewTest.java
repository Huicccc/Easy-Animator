import static org.junit.Assert.assertEquals;

import mock.MockModel;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.view.SVGView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.junit.Test;

/**
 * The Svg view test.
 */
public class SVGViewTest {

  /**
   * The Mock model1.
   */
  public ModelImpl model1 = (new MockModel()).getModel1();
  /**
   * The Mock model2.
   */
  public ModelImpl model2 = (new MockModel()).getModel2();

  /**
   * Svg view test.
   */
  @Test
  public void svgViewTest() {
    StringBuilder outputLog1 = new StringBuilder();
    SVGView svgView1 = new SVGView(outputLog1);
    svgView1.displaySVG(model1.copyShapeMotion(), model1.copyBound());
    String expected1 =
        "<svg width=\"700\" height=\"900\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "\n"
            + "<rect>\n"
            + " <animate id=\"base\" begin=\"0;base.end\" dur=\"9900.0ms\" attrib"
            + "uteName=\"visibility\" from=\"hide\" to=\"hide\"/>\n"
            + "</rect>\n"
            + "\n"
            + "<ellipse id=\"C\" cx=\"440\" cy=\"70\" rx=\"60\" ry=\"30\" fill"
            + "=\"rgb(0,0,255)\" visibility=\"hidden\" >\n"
            + "<set attributeName=\"visibility\" attributeType=\"xml\" to"
            + "=\"visible\" begin = \"500ms\" dur=\"3500ms\" /> \n"
            + "<animate attributeType=\"xml\" begin = \"base.begin+1000ms\" dur"
            + "=\"1000ms\" attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb"
            + "(255,0,255)\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin = \"base.begin+2000ms\" dur"
            + "=\"1000ms\" attributeName=\"rx\" from=\"60\" to=\"50\" fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "\n"
            + "<rect id=\"R\" x=\"200\" y=\"200\" width=\"50\" height=\"100\" fill"
            + "=\"rgb(255,0,0)\" visibility=\"hidden\" >\n"
            + "<set attributeName=\"visibility\" attributeType=\"xml\" to"
            + "=\"visible\" begin = \"100ms\" dur=\"1900ms\" /> \n"
            + "<animate attributeType=\"xml\" begin = \"base.begin+1000ms\" dur"
            + "=\"1000ms\" attributeName=\"x\" from=\"200\" to=\"100\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin = \"base.begin+1000ms\" dur"
            + "=\"1000ms\" attributeName=\"y\" from=\"200\" to=\"200\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "\n"
            + "\n"
            + "</svg>";
    assertEquals(expected1, outputLog1.toString());

    StringBuilder outputLog2 = new StringBuilder();
    SVGView svgView2 = new SVGView(outputLog2);
    svgView2.displaySVG(model2.copyShapeMotion(), model2.copyBound());
    String expected2 =
        "<svg width=\"620\" height=\"350\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "\n"
            + "<rect>\n"
            + " <animate id=\"base\" begin=\"0;base.end\" dur=\"9900.0ms\" attributeName"
            + "=\"visibility\" from=\"hide\" to=\"hide\"/>\n"
            + "</rect>\n"
            + "\n"
            + "<ellipse id=\"C\" cx=\"440\" cy=\"70\" rx=\"60\" ry=\"30\" fill"
            + "=\"rgb(0,0,255)\" visibility=\"hidden\" >\n"
            + "<set attributeName=\"visibility\" attributeType=\"xml\" to"
            + "=\"visible\" begin = \"500ms\" dur=\"3500ms\" /> \n"
            + "<animate attributeType=\"xml\" begin = \"base.begin+1000ms\" dur"
            + "=\"500ms\" attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(255,0,255)\" fill"
            + "=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin = \"base.begin+1500ms\" dur"
            + "=\"500ms\" attributeName=\"rx\" from=\"60\" to=\"50\" fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "\n"
            + "<ellipse id=\"E\" cx=\"200\" cy=\"70\" rx=\"60\" ry=\"30\" fill"
            + "=\"rgb(0,0,255)\" visibility=\"hidden\" >\n"
            + "<set attributeName=\"visibility\" attributeType=\"xml\" to"
            + "=\"visible\" begin = \"100ms\" dur=\"900ms\" /> \n"
            + "</ellipse>\n"
            + "\n"
            + "<rect id=\"R\" x=\"200\" y=\"200\" width=\"50\" height=\"100\" fill"
            + "=\"rgb(255,0,0)\" visibility=\"hidden\" >\n"
            + "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"visible\" begin"
            + " = \"200ms\" dur=\"1800ms\" /> \n"
            + "<animate attributeType=\"xml\" begin = \"base.begin+800ms\" dur"
            + "=\"1200ms\" attributeName=\"x\" from=\"200\" to=\"100\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin = \"base.begin+800ms\" dur"
            + "=\"1200ms\" attributeName=\"y\" from=\"200\" to=\"200\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "\n"
            + "\n"
            + "</svg>";
    assertEquals(expected2, outputLog2.toString());

    FileWriter fileWriter1;
    FileWriter fileWriter2;
    try {
      fileWriter1 = new FileWriter("svgview-test1.svg");
      fileWriter2 = new FileWriter("svgview-test2.svg");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    SVGView svgViewFile1 = new SVGView(fileWriter1);
    SVGView svgViewFile2 = new SVGView(fileWriter2);
    svgViewFile1.displaySVG(model1.copyShapeMotion(), model1.copyBound());
    svgViewFile2.displaySVG(model2.copyShapeMotion(), model2.copyBound());
    try {
      fileWriter1.close();
      fileWriter2.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    String content1;
    String content2;
    try {
      content1 = new Scanner(new File("svgview-test1.svg")).useDelimiter("\\Z").next();
      content2 = new Scanner(new File("svgview-test2.svg")).useDelimiter("\\Z").next();
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    assertEquals(content1, outputLog1.toString());
    assertEquals(content2, outputLog2.toString());

  }
}

