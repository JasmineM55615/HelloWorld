/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	private String name = "";
	private int[] numbers = new int[11];

	/**
	 * Constructor: NameSurferEntry(line)
	 * Creates a new NameSurferEntry from a data line as it appears
	 * in the data file.  Each line begins with the name, which is
	 * followed by integers giving the rank of that name for each
	 * decade.
	 */
	
	public NameSurferEntry(String line) {
		String[] temp = line.split(" ");
		name = temp[0];
		for(int i = 1; i < temp.length; i++) {
			numbers[i - 1] = Integer.parseInt(temp[i]); //changes the numbers into integers
			//integer.parcent
		}
		// You fill this in //
//		try{
//			Scanner input = new Scanner(new File("names-data.txt"));
//			while(input.hasNext()) {
//				
//			}
//			input.close();
//		}catch(IOException ex) {
//			println("hi");
//		}
	}


	/**
	 * Public Method: getName()
	 * Returns the name associated with this entry.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Public Method: getRank(decade)
	 * Returns the rank associated with an entry for a particular
	 * decade.  The decade value is an integer indicating how many
	 * decades have passed since the first year in the database,
	 * which is given by the constant START_DECADE.  If a name does
	 * not appear in a decade, the rank value is 0.
	 */
	public int getRank(int decade) {
		// You need to turn this stub into a real implementation //
		if(decade < 0 || decade > (NDECADES - 1)) {
			return -1;
		}
		return numbers[decade];
	}

	/**
	 * Public Method: toString()
	 * Returns a string that makes it easy to see the value of a
	 * NameSurferEntry.
	 */
	public String toString() {
		// You need to turn this stub into a real implementation //
		String str = name + " [ ";
		for(int i = 0; i < NDECADES; i ++) {
			str += numbers[i] + ", ";
		}
		str = (str.substring(0, str.length() - 2)) + "]";
		//substring last two characters ", " and remove 
		return str;
	}
}

