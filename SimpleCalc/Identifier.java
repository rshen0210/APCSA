/**
 * class specifically for the identifiers
 * @author Ryan Shen
 * @since 3/7/24
 */

public class Identifier {
   private String identifier;
   private double numericValue;

   public Identifier(String key, double value) {
      identifier = key;
      numericValue = value;
   }
	
	/** returns the var that set the operator. */
   public String getKey() {
      return identifier;
   }
	/** returns the value of the numeric value that was popped. */
   public double getValue() {
      return numericValue;
   }
	
	/** sets the the numeric value that was popped to the var. */
   public void setValue(double newValue) {
      numericValue = newValue;
   }

   /** Overriden method */
   @Override
	public String toString() {
		String formattedOutput;

		int exact = calculatePrecision(numericValue);
        if (exact == 0) {
            formattedOutput = String.format("%-6s = %d", identifier, 
                (int)numericValue);
        } else {
            formattedOutput = String.format("%-6s = %." + exact + "f", 
                identifier, numericValue);
        }
		return formattedOutput;
	}

	
	/** Overriden method */
   private int calculatePrecision(double input) {
      for(int exact = 6; exact >= 0; --exact) {
         double testNumber = input * Math.pow(10.0, exact);
         if (Math.abs(testNumber - (double)((int)testNumber)) > 0.0D) {
            return exact + 1;
         }
      }

      return 0;
   }
}