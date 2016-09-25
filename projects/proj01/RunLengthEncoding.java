/* RunLengthEncoding.java */

/**
 *  The RunLengthEncoding class defines an object that run-length encodes
 *  a PixImage object.  Descriptions of the methods you must implement appear
 *  below.  They include constructors of the form
 *
 *      public RunLengthEncoding(int width, int height);
 *      public RunLengthEncoding(int width, int height, int[] red, int[] green,
 *                               int[] blue, int[] runLengths) {
 *      public RunLengthEncoding(PixImage image) {
 *
 *  that create a run-length encoding of a PixImage having the specified width
 *  and height.
 *
 *  The first constructor creates a run-length encoding of a PixImage in which
 *  every pixel is black.  The second constructor creates a run-length encoding
 *  for which the runs are provided as parameters.  The third constructor
 *  converts a PixImage object into a run-length encoding of that image.
 *
 *  See the README file accompanying this project for additional details.
 */

import java.util.Iterator;
import list.*;

public class RunLengthEncoding implements Iterable {

  /**
   *  Define any variables associated with a RunLengthEncoding object here.
   *  These variables MUST be private.
   */
  private DList<int[]> runs;//length, red, green, blue, next, prev
  private int width;
  private int height;


  /**
   *  The following methods are required for Part II.
   */

  /**
   *  RunLengthEncoding() (with two parameters) constructs a run-length
   *  encoding of a black PixImage of the specified width and height, in which
   *  every pixel has red, green, and blue intensities of zero.
   *
   *  @param width the width of the image.
   *  @param height the height of the image.
   */

  public RunLengthEncoding(int width, int height) {
    // Your solution here.
    int[] run = {width * height, 0, 0, 0};
    runs = new DList<int[]>(run);
    this.width = width;
    this.height = height;
  }

  /**
   *  RunLengthEncoding() (with six parameters) constructs a run-length
   *  encoding of a PixImage of the specified width and height.  The runs of
   *  the run-length encoding are taken from four input arrays of equal length.
   *  Run i has length runLengths[i] and RGB intensities red[i], green[i], and
   *  blue[i].
   *
   *  @param width the width of the image.
   *  @param height the height of the image.
   *  @param red is an array that specifies the red intensity of each run.
   *  @param green is an array that specifies the green intensity of each run.
   *  @param blue is an array that specifies the blue intensity of each run.
   *  @param runLengths is an array that specifies the length of each run.
   *
   *  NOTE:  All four input arrays should have the same length (not zero).
   *  All pixel intensities in the first three arrays should be in the range
   *  0...255.  The sum of all the elements of the runLengths array should be
   *  width * height.  (Feel free to quit with an error message if any of these
   *  conditions are not met--though we won't be testing that.)
   */

  public RunLengthEncoding(int width, int height, int[] red, int[] green,
                           int[] blue, int[] runLengths) {
    // Your solution here.
    this.width =  width;
    this.height = height;
    runs = new DList<int[]>();
    for (int i = 0; i < red.length; i++) {
      int[] run = {runLengths[i], red[i], green[i], blue[i]};
      runs.insertBack(run);
    }
  }

  /**
   *  getWidth() returns the width of the image that this run-length encoding
   *  represents.
   *
   *  @return the width of the image that this run-length encoding represents.
   */

  public int getWidth() {
    // Replace the following line with your solution.
    return width;
  }

  /**
   *  getHeight() returns the height of the image that this run-length encoding
   *  represents.
   *
   *  @return the height of the image that this run-length encoding represents.
   */
  public int getHeight() {
    // Replace the following line with your solution.
    return height;
  }

  /**
   *  iterator() returns a newly created RunIterator that can iterate through
   *  the runs of this RunLengthEncoding.
   *
   *  @return a newly created RunIterator object set to the first run of this
   *  RunLengthEncoding.
   */
  public RunIterator iterator() {
    // Replace the following line with your solution.
    return new RunIterator(runs.front());
    // You'll want to construct a new RunIterator, but first you'll need to
    // write a constructor in the RunIterator class.
  }

  /**
   *  toPixImage() converts a run-length encoding of an image into a PixImage
   *  object.
   *
   *  @return the PixImage that this RunLengthEncoding encodes.
   */
  public PixImage toPixImage() {
    // Replace the following line with your solution.
    PixImage image = new PixImage(width, height);
    RunIterator i = iterator();
    int[] pixel = {0, 0};
    while(i.hasNext()) {
      int[] run = i.next();
      for (int j = 0; j < run[0]; j++) {
        //System.out.println("(" + pixel[0] + ", " + pixel[1] + ")" + "[" + run[1] + "," + run[2] +"," +run[3] +")");
        image.setPixel(pixel[0], pixel[1], (short)run[1], (short)run[2], (short)run[3]);
        pixel = image.nextPixel(pixel);
      }
    }
    return image;
  }

  /**
   *  toString() returns a String representation of this RunLengthEncoding.
   *
   *  This method isn't required, but it should be very useful to you when
   *  you're debugging your code.  It's up to you how you represent
   *  a RunLengthEncoding as a String.
   *
   *  @return a String representation of this RunLengthEncoding.
   */
  public String toString() {
    // Replace the following line with your solution.
    String rlst = "[ ";
    DListNode<int[]> node = runs.front();
    while(!runs.isSentinel(node)) {
      int[] run = node.item;
      rlst += "(";
      for (int i = 0; i < run.length; i++) rlst += run[i] + " ";
      rlst += ") ";
      node = node.next;
    }
    rlst += "]";
    return rlst;
  }


  /**
   *  The following methods are required for Part III.
   */

  /**
   *  RunLengthEncoding() (with one parameter) is a constructor that creates
   *  a run-length encoding of a specified PixImage.
   * 
   *  Note that you must encode the image in row-major format, i.e., the second
   *  pixel should be (1, 0) and not (0, 1).
   *
   *  @param image is the PixImage to run-length encode.
   */
  public RunLengthEncoding(PixImage image) {
    // Your solution here, but you should probably leave the following line
    // at the end.
    width = image.getWidth();
    height = image.getHeight();
    runs = new DList<int[]>();
    int[] pixel = {0, 0};
    while(image.hasNextPixel(pixel)) {
      //System.out.println("Start! (" + pixel[0] + "," + pixel[1] + ")");
      int red = image.getRed(pixel[0], pixel[1]);
      int green = image.getGreen(pixel[0], pixel[1]);
      int blue = image.getBlue(pixel[0], pixel[1]);
      int runLength = 1;
      while(image.hasNextPixel(image.nextPixel(pixel)) && image.isSamePixel(pixel, image.nextPixel(pixel))) {
        runLength++;
        pixel = image.nextPixel(pixel);
      }
      int[] run = {runLength, red, green, blue};
      runs.insertBack(run);
      pixel = image.nextPixel(pixel);
      //System.out.println("End! (" + pixel[0] + "," + pixel[1] + ")");
    }
    check();
  }

  /**
   *  check() walks through the run-length encoding and prints an error message
   *  if two consecutive runs have the same RGB intensities, or if the sum of
   *  all run lengths does not equal the number of pixels in the image.
   */
  public void check(){
    // Your solution here.
    DListNode<int[]> run = runs.front();
    int length = 0;
    while(!runs.isSentinel(run)) {
      int[] lrgb = run.item;
      if (lrgb[0] < 1) {
        System.out.println("ERROR! Construct a valid length: " + lrgb[0]);
      }
      length += lrgb[0];
      if (!runs.isSentinel(run.next) && isSameRun(run.item, run.next.item)) {
        System.out.println("ERROR! Two consecutive runs have same contents");
      }
      run = run.next;
    }
    if (length != width * height) {
      System.out.println("ERROR! Sum of runs does not equal the size of PixImage");
    }
  }

  private boolean isSameRun(int[] curr, int[] next) {
    if (curr[1]== next[1] && curr[2]== next[2] && curr[3]== next[3]) return true;
    return false;
  }


  /**
   *  The following method is required for Part IV.
   */

  /**
   *  setPixel() modifies this run-length encoding so that the specified color
   *  is stored at the given (x, y) coordinates.  The old pixel value at that
   *  coordinate should be overwritten and all others should remain the same.
   *  The updated run-length encoding should be compressed as much as possible;
   *  there should not be two consecutive runs with exactly the same RGB color.
   *
   *  @param x the x-coordinate of the pixel to modify.
   *  @param y the y-coordinate of the pixel to modify.
   *  @param red the new red intensity to store at coordinate (x, y).
   *  @param green the new green intensity to store at coordinate (x, y).
   *  @param blue the new blue intensity to store at coordinate (x, y).
   */
  public void setPixel(int x, int y, short red, short green, short blue) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
    // First find the Run that correspons to this particular coordinate.
    // 1) If the pixel is different that the RGB of the run it is stored in:
    //    1) If the coordinate is the first pixel in the Run
    //       1) Check if the pixel matches the pixel before
    //          1) increase the length in previous Run
    //          2) decrease the length in current Run
    //          3) break
    //       2) Add New Run
    //    2) If the cooridinate is the last pixel in the Run
    //       1) Check if the pixel matches the pixel after
    //          1) increase the length in next Run
    //          2) decrease the length in current Run
    //       2) Add New Run
    //    3) If the pixel is in the middle of the Run AND different
    //       1) Break this RUN into two pieces: Run1_length =  - 1
    //          Run2_length = Run_length - pixel_place
    //       2) Create a new Run: Run3 = {length=1, red, gree, blue}
    //       3) Run.prev -> Run1 -> Run3 -> Run2 -> Run.next
    // 2) If the pixel is the same RGB as the Run
    //    1) Doing nothing.
    if (x >= width || y >= height || x * y < 0) {
      System.out.println("ERROR! Index out off bound!");
      return;
    }
    // find the Run
    int[] newItem = {1, red, green, blue};
    DListNode<int[]> run = runs.front();
    int index = y * width + x;
    int length = 0;
    while(!runs.isSentinel(run)) {
      if ((length + run.item[0] - 1) >= index) break;
      length += run.item[0];
      run = run.next;
    }
    //System.out.println("Find Run: (" + run.item[0] + "," + run.item[1] + "," + run.item[2] + "," + run.item[3] + "); length is " + length);
    if (isSameRun(newItem, run.item)) {
      //the pixel is the same RGB as the Run
    } else if (isFirstPixel(index, run.item[0], length)) {
      //is the first pixel
      if (!runs.isSentinel(run.prev) && isSameRun(run.prev.item, newItem)) {
        run.prev.item[0]++;
      } else {
        runs.insertBefore(newItem, run);
      }
      run.item[0]--;
    } else if (isLastPixel(index, run.item[0], length)) {
      //is the last pixel in the Run
      if (!runs.isSentinel(run.next) && isSameRun(run.next.item, newItem)) {
        run.next.item[0]++;
      } else {
        runs.insertAfter(newItem, run);
      }
      run.item[0]--;
      //if (run.item[0] == 0) runs.remove(run);
    } else {
      //is in the middle of the Run AND different
      int runLength = run.item[0];
      run.item[0] = index - length;//run1
      runs.insertAfter(newItem, run);//run2
      int[] lrgb = {length + runLength - index - 1, run.item[1], run.item[2], run.item[3]};
      runs.insertAfter(lrgb, run.next);
    }
    optimizeRuns(run);//remove run with 0 length, combine consecutive runs with same contents
    check();
  }

  private boolean isFirstPixel(int index, int runLength, int lengthBeforeRun) {
    return index == lengthBeforeRun;
  }

  private boolean isLastPixel(int index, int runLength, int lengthBeforeRun) {
    return index == lengthBeforeRun + runLength - 1;
  }

  private void optimizeRuns(DListNode<int[]> run) {
    //System.out.println("Start optimize...");
    if (run.item[0] == 0) {
      //System.out.println("Remove 0 length run");
      runs.remove(run);
      run = run.prev;
    }
      
    if (!runs.isSentinel(run.prev) && isSameRun(run.prev.item, run.item)) {
      //System.out.println("Combine forward!");
      run.prev.item[0] += run.item[0];
      runs.remove(run);
    }
    if (!runs.isSentinel(run.next) && isSameRun(run.next.item, run.item)) {
      //System.out.println("Combine backward!");
      run.item[0] += run.next.item[0];
      runs.remove(run.next);
    }
  }


  /**
   * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
   * You are welcome to add tests, though.  Methods below this point will not
   * be tested.  This is not the autograder, which will be provided separately.
   */


  /**
   * doTest() checks whether the condition is true and prints the given error
   * message if it is not.
   *
   * @param b the condition to check.
   * @param msg the error message to print if the condition is false.
   */
  private static void doTest(boolean b, String msg) {
    if (b) {
      System.out.println("Good.");
    } else {
      System.err.println(msg);
    }
  }

  /**
   * array2PixImage() converts a 2D array of grayscale intensities to
   * a grayscale PixImage.
   *
   * @param pixels a 2D array of grayscale intensities in the range 0...255.
   * @return a new PixImage whose red, green, and blue values are equal to
   * the input grayscale intensities.
   */
  private static PixImage array2PixImage(int[][] pixels) {
    int width = pixels.length;
    int height = pixels[0].length;
    PixImage image = new PixImage(width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                       (short) pixels[x][y]);
      }
    }
    return image;
  }

  /**
   * setAndCheckRLE() sets the given coordinate in the given run-length
   * encoding to the given value and then checks whether the resulting
   * run-length encoding is correct.
   *
   * @param rle the run-length encoding to modify.
   * @param x the x-coordinate to set.
   * @param y the y-coordinate to set.
   * @param intensity the grayscale intensity to assign to pixel (x, y).
   */
  private static void setAndCheckRLE(RunLengthEncoding rle,
                                     int x, int y, int intensity) {
    rle.setPixel(x, y,
                 (short) intensity, (short) intensity, (short) intensity);
    rle.check();
  }

  /**
   * main() runs a series of tests of the run-length encoding code.
   */
  public static void main(String[] args) {
    /*System.out.println("Testing second constructor: ");
    PixImage image = array2PixImage(new int[][] { { 7, 88, 0 },
                                                  { 7, 88, 0 },
                                                  { 7, 88, 0 },
                                                  { 88, 88, 0 }});
    System.out.println(image);
    RunLengthEncoding rle = new RunLengthEncoding(image);
    System.out.println(rle);
    System.out.println(rle.toPixImage());
    System.out.println("\nTesting setPixel()");
    rle.setPixel(0, 1, (short) 7, (short) 7, (short) 7);
    System.out.println(rle);*/
    // Be forwarned that when you write arrays directly in Java as below,
    // each "row" of text is a column of your image--the numbers get
    // transposed.
    PixImage image1 = array2PixImage(new int[][] { { 0, 3, 6 },
                                                   { 1, 4, 7 },
                                                   { 2, 5, 8 } });
    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 3x3 image.  Input image:");
    System.out.print(image1);
    RunLengthEncoding rle1 = new RunLengthEncoding(image1);
    rle1.check();
    System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
    doTest(rle1.getWidth() == 3 && rle1.getHeight() == 3,
           "RLE1 has wrong dimensions");
    System.out.println("Testing toPixImage() on a 3x3 encoding.");
    System.out.println(rle1);
    doTest(image1.equals(rle1.toPixImage()),
           "image1 -> RLE1 -> image does not reconstruct the original image");
    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 0, 42);
    image1.setPixel(0, 0, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           /*
                       array2PixImage(new int[][] { { 42, 3, 6 },
                                                    { 1, 4, 7 },
                                                    { 2, 5, 8 } })),
           */
           "Setting RLE1[0][0] = 42 fails.");
    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 1, 0, 42);
    image1.setPixel(1, 0, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[1][0] = 42 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 1, 2);
    image1.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[0][1] = 2 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 0, 0);
    image1.setPixel(0, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[0][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 2, 2, 7);
    image1.setPixel(2, 2, (short) 7, (short) 7, (short) 7);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[2][2] = 7 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 2, 2, 42);
    image1.setPixel(2, 2, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[2][2] = 42 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 1, 2, 42);
    image1.setPixel(1, 2, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[1][2] = 42 fails.");


    PixImage image2 = array2PixImage(new int[][] { { 2, 3, 5 },
                                                   { 2, 4, 5 },
                                                   { 3, 4, 6 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on another 3x3 image.  Input image:");
    System.out.print(image2);
    RunLengthEncoding rle2 = new RunLengthEncoding(image2);
    rle2.check();
    System.out.println(rle2);//check the original rle2
    System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
    doTest(rle2.getWidth() == 3 && rle2.getHeight() == 3,
           "RLE2 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x3 encoding.");
    doTest(rle2.toPixImage().equals(image2),
           "image2 -> RLE2 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle2, 0, 1, 2);
    image2.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
    doTest(rle2.toPixImage().equals(image2),
           "Setting RLE2[0][1] = 2 fails.");
    System.out.println(rle2);//check rle2

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle2, 2, 0, 2);
    image2.setPixel(2, 0, (short) 2, (short) 2, (short) 2);
    doTest(rle2.toPixImage().equals(image2),
           "Setting RLE2[2][0] = 2 fails.");
    System.out.println(rle2);

    PixImage image3 = array2PixImage(new int[][] { { 0, 5 },
                                                   { 1, 6 },
                                                   { 2, 7 },
                                                   { 3, 8 },
                                                   { 4, 9 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 5x2 image.  Input image:");
    System.out.print(image3);
    RunLengthEncoding rle3 = new RunLengthEncoding(image3);
    rle3.check();
    System.out.println("Testing getWidth/getHeight on a 5x2 encoding.");
    doTest(rle3.getWidth() == 5 && rle3.getHeight() == 2,
           "RLE3 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 5x2 encoding.");
    doTest(rle3.toPixImage().equals(image3),
           "image3 -> RLE3 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 4, 0, 6);
    image3.setPixel(4, 0, (short) 6, (short) 6, (short) 6);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[4][0] = 6 fails.");

    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 0, 1, 6);
    image3.setPixel(0, 1, (short) 6, (short) 6, (short) 6);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[0][1] = 6 fails.");

    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 0, 0, 1);
    image3.setPixel(0, 0, (short) 1, (short) 1, (short) 1);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[0][0] = 1 fails.");


    PixImage image4 = array2PixImage(new int[][] { { 0, 3 },
                                                   { 1, 4 },
                                                   { 2, 5 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 3x2 image.  Input image:");
    System.out.print(image4);
    RunLengthEncoding rle4 = new RunLengthEncoding(image4);
    rle4.check();
    System.out.println("Testing getWidth/getHeight on a 3x2 encoding.");
    doTest(rle4.getWidth() == 3 && rle4.getHeight() == 2,
           "RLE4 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x2 encoding.");
    doTest(rle4.toPixImage().equals(image4),
           "image4 -> RLE4 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 2, 0, 0);
    image4.setPixel(2, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[2][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 1, 0, 0);
    image4.setPixel(1, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[1][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 1, 0, 1);
    image4.setPixel(1, 0, (short) 1, (short) 1, (short) 1);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[1][0] = 1 fails.");
  }
}
