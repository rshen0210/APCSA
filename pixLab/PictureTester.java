/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  /** Method to test keepOnlyBlue */
  public static void testKeepOnlyBlue() {
	  Picture beach = new Picture("images/beach.jpg");
	  beach.explore();
	  beach.keepOnlyBlue();
	  beach.explore();
  }
  /** Method to test negate */
  public static void testNegate() {
	  Picture beach = new Picture("images/beach.jpg");
	  beach.explore();
	  beach.negate();
	  beach.explore();
  }
  /** Method to test gray scale */ 
  public static void testGrayscale() {
	  Picture beach = new Picture("images/beach.jpg");
	  beach.explore();
	  beach.grayscale();
	  beach.explore();
  }
  /** Method to test gray scale */ 
  public static void testPixelate() {
	  Picture swan = new Picture("images/swan.jpg");
	  swan.explore();
	  swan.pixelate(50);
	  swan.explore();
  }
  public static void testBlur() {
	  Picture swan = new Picture("images/swan.jpg");
	  swan.explore();
	  Picture bob = swan.blur(50);
	  bob.explore();
  }
  public static void testEnhance() {
	  Picture swan = new Picture("images/water.jpg");
	  swan.explore();
	  Picture bob = swan.enhance(5);
	  bob.explore();
  }
  
  public static void testShiftRight() {
	  Picture motor = new Picture("images/redMotorcycle.jpg");
	  motor.explore();
	  Picture bob = motor.shiftRight(75);
	  bob.explore();
  }
  public static void testStairStep() {
	  Picture motor = new Picture("images/redMotorcycle.jpg");
	  motor.explore();
	  Picture bob = motor.stairStep(1, 400);
	  bob.explore();
  }

  public static void testTurn90() {
	  Picture motor = new Picture("images/redMotorcycle.jpg");
	  motor.explore();
	  Picture bob = motor.turn90();
	  bob.explore();
  }
  public static void testZoomUpperLeft() {
	  Picture swan = new Picture("images/redMotorcycle.jpg");
	  swan.explore();
	  Picture bob = swan.zoomUpperLeft();
	  bob.explore();
  }
  public static void testTileMirror() {
	  Picture swan = new Picture("images/redMotorcycle.jpg");
	  swan.explore();
	  Picture bob = swan.tileMirror();
	  bob.explore();
  }
  public static void testEdgeDetectionBelow() {
	  Picture swan = new Picture("images/swan.jpg");
	  swan.explore();
	  Picture bob = swan.edgeDetectionBelow(10);
	  bob.explore();
  }
  public static void testGreenScreen() {
	  Picture swan = new Picture("GreenScreenCatMouse/IndoorJapeneseRoomBackground.jpg");
	  swan.explore();
	  Picture bob = swan.greenScreen();
	  bob.explore();
  }
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("images/caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("images/temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("images/640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("images/swan.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    //testZeroBlue();
    //testKeepOnlyBlue();
    //testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    //testNegate();
    //testGrayscale();
    //testPixelate();
    //testBlur();
    //testEnhance();
    //testZoomUpperLeft();
    //testTileMirror();
    //testEdgeDetectionBelow();
    testGreenScreen();
    //testShiftRight();
    //testStairStep();
    //testTurn90();
    //testFixUnderwater();
    //testMirrorVertical();
    //testMirrorTemple();
    //testMirrorArms();
    //testMirrorGull();
    //testMirrorDiagonal();
    //testCollage();
    //testCopy();
    //testEdgeDetection();
    //testEdgeDetection2();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
  }
}