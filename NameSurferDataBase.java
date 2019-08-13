/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import acm.util.*;
import java.util.*;
import java.io.*;

public class NameSurferDataBase implements NameSurferConstants {

	// TODO: Add instance variables
	
	/**
	 * Constructor: NameSurferDataBase(filename)
	 * Creates a new NameSurferDataBase and initializes it using the
	 * data in the specified file.  The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
	public NameSurferDataBase(String filename) {
		// You fill this in //
		ArrayList<String> babyNames = new ArrayList<String>();
		String str = "";
		try{
			Scanner input = new Scanner(new File("names-data.txt"));
			while(input.hasNext()) {
				String yote = input.nextLine();
				babyNames.add(new NameSurferEntry yote);
			}
			input.close();
		} catch(IOException ex) {
			//System.out.println("hi");
		}
		//ArrayList<String> babyNames = new ArrayList<String>();
	}

	/**
	 * Public Method: findEntry(name)
	 * Returns the NameSurferEntry associated with this name, if one
	 * exists.  If the name does not appear in the database, this
	 * method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
		// You need to turn this stub into a real implementation //
		return null;
	}
}

