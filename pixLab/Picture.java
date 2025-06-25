import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  /** Method to set everything other than blue to 0 */
  public void keepOnlyBlue() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels)
		{
			for (Pixel pixelObj : rowArray)
			{
				pixelObj.setRed(0);
				pixelObj.setGreen(0);
			}
		}
  }
  /** Method to negate all colors */
  public void negate() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels)
		{
			for (Pixel pixelObj : rowArray)
			{
				pixelObj.setRed(255-pixelObj.getRed());
				pixelObj.setGreen(255-pixelObj.getGreen());
				pixelObj.setBlue(255-pixelObj.getBlue());
			}
		}
	}
  /** Method to make an image just shades of gray (black and white) */
  public void grayscale() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels)
		{
			for (Pixel pixelObj : rowArray)
			{
				int average = 
				(pixelObj.getRed()+pixelObj.getGreen()+pixelObj.getBlue())/3;
				pixelObj.setRed(average);
				pixelObj.setGreen(average);
				pixelObj.setBlue(average);
			}
		}
  }
  /** To pixelate by dividing area into size x size.
	* @param size Side length of square area to pixelate.
	*/
	public void pixelate(int size) {
    Pixel[][] pixels = this.getPixels2D();

    // Loop through center points in the array
    for (int rowCenter=size/2;rowCenter<pixels.length;rowCenter+=size/2+1) {
        for (int colCenter=size/2;colCenter<pixels[rowCenter].length;colCenter+=size/2+1) {

            // Total values
            int total = 0;
            int totalRed = pixels[rowCenter][colCenter].getRed();
            int totalBlue = pixels[rowCenter][colCenter].getBlue();
            int totalGreen = pixels[rowCenter][colCenter].getGreen();

            // Parameters for the loop
            // Goes from rowMin-rowMax and colMin-colMax
            int rowMin = Math.max(0, rowCenter - size / 2);
            int rowMax = Math.min(rowCenter + size / 2, pixels.length - 1);
            int colMin = Math.max(colCenter - size / 2, 0);
            int colMax = Math.min(colCenter + size / 2, pixels[0].length - 1);

            for (int row = rowMin; row <= rowMax; row++) {
                for (int col = colMin; col <= colMax; col++) {
                    // Update the totals
                    totalRed += pixels[row][col].getRed();
                    totalBlue += pixels[row][col].getBlue();
                    totalGreen += pixels[row][col].getGreen();
                    total++;
                }
            }

            // Average out the totals
            totalRed /= total;
            totalGreen /= total;
            totalBlue /= total;

            // Set the totals to everything within the size by size square
            for (int row = rowMin; row <= rowMax; row++) {
                for (int col = colMin; col <= colMax; col++) {
                    pixels[row][col].setRed(totalRed);
                    pixels[row][col].setGreen(totalGreen);
                    pixels[row][col].setBlue(totalBlue);
                }
            }
        }
    }
  }
    /** Method that blurs the picture
  * @param size Blur size, greater is more blur
  * @return Blurred picture
  */
  public Picture blur(int size) {
    // Create a copy of the pixels array
    Pixel[][] pixels = this.getPixels2D();
    Picture result = new Picture(pixels.length, pixels[0].length);
    Pixel[][] pixel = result.getPixels2D();

    // Go through each cell in pixels
    for (int row = 0; row < pixels.length; row++) {
        for (int col = 0; col < pixels[row].length; col++) {
            int total = 0; // Number of cells we go through
            int totalRed = 0; // Total red from the cells
            int totalBlue = 0; // Total blue from the cells
            int totalGreen = 0; // Total green from the cells

            // Parameters for the cells we will go through
            // Goes from rowMin - rowMax and colMin - colMax
            int rowMin = Math.max(0, row - size / 2);
            int rowMax = Math.min(row + size / 2, pixels.length - 1);
            int colMin = Math.max(col - size / 2, 0);
            int colMax = Math.min(col + size / 2, pixels[0].length - 1);

            for (int k = rowMin; k <= rowMax && k < pixels.length; k++) {
                for (int l = colMin; l <= colMax && l < pixels[k].length; l++) {
                    // Update all the totals
                    totalRed += pixels[k][l].getRed();
                    totalBlue += pixels[k][l].getBlue();
                    totalGreen += pixels[k][l].getGreen();
                    total++;
                }
            }
            
            // Average out all the totals
            totalRed /= total;
            totalGreen /= total;
            totalBlue /= total;

            // Set the current cell to the averages
            pixel[row][col].setRed(totalRed);
            pixel[row][col].setGreen(totalGreen);
            pixel[row][col].setBlue(totalBlue);
        }
    }
    return result;
  }
  
    /** Method that enhances a picture by getting average Color around
  * a pixel then applies the following formula:
  *
  * pixelColor <- 2 * currentValue - averageValue
  *
  * size is the area to sample for blur.
  *
  * @param size Larger means more area to average around pixel
  * and longer compute time.
  * @return enhanced picture
  */
  public Picture enhance(int size)
  {
    // Make a copy of the pixels array
    Pixel[][] pixels = this.getPixels2D();
    Picture result = new Picture(pixels.length, pixels[0].length);
    Pixel[][] resultPixels = result.getPixels2D(); 

    // Go through each element in pixels
    for (int row = 0; row < pixels.length; row++) {
        for (int col = 0; col < pixels[row].length; col++) {

            // Total values for blue, red, and green as well as the
            // total elements we go through
            int total = 0;
            int totalRed = pixels[row][col].getRed();
            int totalBlue = pixels[row][col].getBlue();
            int totalGreen = pixels[row][col].getGreen();

            // The rowMin and rowMax we go through
            int rowMin = Math.max(0, row - size / 2);
            int rowMax = Math.min(row + size / 2, pixels.length - 1);

            // The column min to column max we go through
            int colMin = Math.max(col - size / 2, 0);
            int colMax = Math.min(col + size / 2, pixels[0].length - 1);

            for (int r = rowMin; r <= rowMax; r++) {
                for (int c = colMin; c <= colMax; c++) {
                    // Add corresponding values to totals
                    totalRed += pixels[r][c].getRed();
                    totalBlue += pixels[r][c].getBlue();
                    totalGreen += pixels[r][c].getGreen();
                    total++;
                }
            }

            // Average out the totals
            totalRed /= total;
            totalGreen /= total;
            totalBlue /= total;

            // Use formula for enhancing
            for (int r = rowMin; r <= rowMax; r++) {
                for (int c = colMin; c <= colMax; c++) {
                    resultPixels[r][c].setRed(2 * pixels[r][c].getRed() - totalRed);
                    resultPixels[r][c].setGreen(2 * pixels[r][c].getGreen() - totalGreen);
                    resultPixels[r][c].setBlue(2 * pixels[r][c].getBlue() - totalBlue);
                }
            }
        }
    }
    
    return result;
  }
	/**
   * this method just shifts an image a percentage to the right
   * @param percent
   * @return
   */
	public Picture shiftRight(int percent) {
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] pixel = result.getPixels2D();
		for (int c = 0;c<pixels[0].length;c++)
		{
			for (int r = 0;r<pixels.length;r++)
			{
				int shift = 
				((int)((pixels[0].length * percent)*0.01)+c) % 
				(pixels[0].length-1);
				pixel[r][shift].setRed(pixels[r][c].getRed());
				pixel[r][shift].setGreen(pixels[r][c].getGreen());
				pixel[r][shift].setBlue(pixels[r][c].getBlue());
					
			}
		}
		return result;
	}
  /**
   * this method creates a stair case looking image from the original image
   * @param shiftCount
   * @param steps
   * @return
   */
	public Picture stairStep(int shiftCount, int steps) {
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] pixel = result.getPixels2D();
		int step = pixels.length/steps;
		
		for (int r = 0;r<pixels.length;r++) // rows
		{
			for (int c = 0;c<pixels[0].length;c++)
			{
				int shift =shiftCount * (r/step);
				shift = (shift+c) % (pixels[0].length-1);
				pixel[r][shift].setRed(pixels[r][c].getRed());
				pixel[r][shift].setBlue(pixels[r][c].getBlue());
				pixel[r][shift].setGreen(pixels[r][c].getGreen());
			}
		}
		return result;
				
	}
  /**
   * this method rotates the image 90 degrees
   * @return
   */
  public Picture turn90() {
    Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels[0].length, pixels.length);
		Pixel[][] pixel = result.getPixels2D();
    for (int r = 0;r<pixels.length;r++) // rows
		{
			for (int c = 0;c<pixels[0].length;c++)
			{
        pixel[c][pixels.length - 1 - r].setRed(pixels[r][c].getRed());
        pixel[c][pixels.length - 1 - r].setBlue(pixels[r][c].getBlue());
        pixel[c][pixels.length - 1 - r].setGreen(pixels[r][c].getGreen());
      }
    }
    return result;
  }
  /**
   * zooms into the upper left corner of the screen
   * @return result 
   */
  public Picture zoomUpperLeft() {
		//make copy of the pixels array 
		Pixel[][] pixels = this.getPixels2D() ; 
		Picture result = new Picture(pixels.length , pixels[0].length) ;
		Pixel[][] pixel = result.getPixels2D() ; 
		
		//go through each pixel and zoom in on values
		for(int row = 0 ; row < pixel.length; row ++) {
			for(int col=0; col < pixel[row].length; col++) {
				pixel[row][col].setRed(pixels[row/2][col/2].getRed()); 
				pixel[row][col].setGreen(pixels[row/2][col/2].getGreen());
				pixel[row][col].setBlue(pixels[row/2][col/2].getBlue()) ;  
			}
		}
		return result; 
	}
  /**
	 * split an image into fourths and set each quadrant to an image 
   * reflected
	 */ 
	public Picture tileMirror() {
		//make a copy of the pixels array
		Pixel[][] pixels = this.getPixels2D() ; 
		Picture result = new Picture(pixels.length, pixels[0].length) ; 
		Pixel[][] pixel = result.getPixels2D() ;
		
		//corresponding row value in resultPixels array 
		int row = 0 ; 
		
		//skip 2 so it will be shrunk down by 4
		for(int i = 0 ; i < pixels.length; i += 2) {
			//coresponding column value in resultPixels array
			int col = 0 ; 
			//skip 2 so it will be shrunked down by 4
			for(int j=0; j < pixels[i].length; j+=2) {
				//set the resultPixels values
				pixel[row][col].setGreen(
				pixels[i][j].getGreen()) ; 
				pixel[row][col].setRed(pixels[i][j].getRed()) ; 
				pixel[row][col].setBlue(pixels[i][j].getBlue()) ; 
				//update columns
				col ++ ; 
			}
			//update row
			row ++ ; 
		}
		//reset rows
		row = 0 ; 
		//set top right quadrant to corresponding values
		for(int i = 0; i < pixels.length ; i += 2) {
			//set column to correct position
			int col = pixels[0].length/2 ;  
			//go through array and set the top right quadrant
			for(int j = pixels[i].length - 1 ; j >= 0 ; j -= 2) {
				pixel[row][col].setGreen(
				pixels[i][j].getGreen()) ; 
				pixel[row][col].setRed(pixels[i][j].getRed()) ; 
				pixel[row][col].setBlue(pixels[i][j].getBlue()) ; 
				//update columns
				col++ ; 
			}
			//update rows
			row++; 
		}
		//reset rows to bottom left
		row = pixels.length/2 ; 
		//set the bottom left values in resultPixels
		for(int i = pixels.length - 1; i >= 0 ; i -= 2) {
			//set col value to corresponding place 
			int col = 0 ; 
			//go through bottom left and set all the values to the
			//corresponding place in pixels
			for(int j = pixels[i].length - 1; j >= 0 ; j -= 2) {
				pixel[row][col].setGreen(
				pixels[i][j].getGreen()) ; 
				pixel[row][col].setRed(pixels[i][j].getRed()) ; 
				pixel[row][col].setBlue(pixels[i][j].getBlue()) ; 
				//update columns
				col++ ; 
			}
			//update rows
			row ++ ; 
		}
		//update rows to the bottom right of the array
		row = pixels.length/2 ; 
		for(int i = pixels.length - 1; i >= 0 ; i -= 2) {
			//update columns to bottom right of the array
			int col = pixels[0].length/2; 
			//go through bottom right and set all the values to the 
			//corresponding values in the pixels array
			for(int j = 0 ; j < pixels[i].length ; j += 2) {
				pixel[row][col].setGreen(
				pixels[i][j].getGreen()) ; 
				pixel[row][col].setRed(pixels[i][j].getRed()) ; 
				pixel[row][col].setBlue(pixels[i][j].getBlue()) ; 
				//update columns
				col++ ;
			}
			//update rows 
			row ++ ; 
		}
		return result; 
	}
  /**
   * Method to show large changes in color
   * @param threshold
   * @return
   */
  public Picture edgeDetectionBelow(int threshold) {
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    Color bottomColor = null;
    //make a copy of the pixels array
    Pixel[][] pixels = this.getPixels2D(); 
    Picture result = new Picture(pixels.length, pixels[0].length); 
    Pixel[][] pixel = result.getPixels2D();
    for (int row = 0;row<pixels.length-1;row++) // rows
		{
			for (int col = 0;col<pixels[0].length-1;col++)
			{
        topPixel = pixels[row][col];
        bottomPixel = pixels[row+1][col];
        bottomColor = bottomPixel.getColor();
        if (topPixel.colorDistance(bottomColor) > threshold)
          pixel[row][col].setColor(Color.BLACK);
        else
          pixel[row][col].setColor(Color.WHITE);
      }
    }
    return result;
  }
  /**
   * Takes three pictures and blends two onto the background
   */
  public Picture greenScreen() {
    Picture cat = new Picture("GreenScreenCatMouse/kitten.jpg");
    Picture dog = new Picture("GreenScreenCatMouse/puppy.jpg");
    Picture back = new Picture("GreenScreenCatMouse/IndoorJapeneseRoomBackground.jpg");
    Pixel[][] background = back.getPixels2D(); 
    Picture result = new Picture(background.length, background[0].length); 
    Pixel[][] pixels = result.getPixels2D();
    Pixel[][] catPixels = cat.getPixels2D();
    Pixel[][] dogPixels = dog.getPixels2D();
    for (int row = 0;row<background.length;row++) {
      for (int col = 0;col<background[0].length;col++) {
        pixels[row][col].setColor(background[row][col].getColor());
      }
    }
    int x, y;
    x = 0;
    y = 0;
    int catOriginY = background.length-280;
    int catOriginX = 500;

    for (int i = catOriginY;i<catPixels.length+catOriginY;i++) { // rows
      for (int j = catOriginX;j<catPixels[0].length+catOriginX;j++) { // cols
        pixels[i][j].setColor(catPixels[y][x].getColor());
        x++;
      }
      y++;
      x=0;
    }
    int a, b;
    a = 0;
    b = 0;
    int dogOriginY = background.length-300;
    int dogOriginX = 150;

    for (int i = dogOriginY;i<dogPixels.length+dogOriginY;i++) { // rows
      for (int j = dogOriginX;j<dogPixels[0].length+dogOriginX;j++) { // cols
        pixels[i][j].setColor(dogPixels[b][a].getColor());
        a++;
      }
      b++;
      a=0;
    }
    for (int row = 0;row<background.length;row++) {
      for (int col = 0;col<background[0].length;col++) {
        if (pixels[row][col].getRed() > 25 && pixels[row][col].getRed() < 140) {
          if (pixels[row][col].getGreen() > 160 && pixels[row][col].getGreen() < 217) {
            if (pixels[row][col].getBlue() > 25 && pixels[row][col].getBlue() < 140) {
              pixels[row][col].setColor(background[row][col].getColor());
            }
          }
        }
      }
    }
    return result;
  }
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this