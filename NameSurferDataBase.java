/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * Name: Jasmine Mann
 * Section Leader: Trey Connelly
 * 
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


	private List<NameSurferEntry> babyNames; 
	/**
	 * Constructor: NameSurferDataBase(filename)
	 * Creates a new NameSurferDataBase and initializes it using the
	 * data in the specified file.  The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
	public NameSurferDataBase(String filename) {
		babyNames = new ArrayList<NameSurferEntry>();
		try{
			Scanner input = new Scanner(new File(filename)); //reads the filename and stores all the babyname data
			while(input.hasNextLine()) {
				String yote = input.nextLine();
				NameSurferEntry t = new NameSurferEntry(yote);
				babyNames.add(t);
			}
			input.close();
		} catch(IOException ex) {
			System.out.println("hi");
		}
	}

	/**
	 * Public Method: findEntry(name)
	 * Returns the NameSurferEntry associated with this name, if one
	 * exists.  If the name does not appear in the database, this
	 * method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
		for(NameSurferEntry i : babyNames) { //goes through the array and returns the name surfer entry if the name inputted by the user is a valid name
			if(i.getName().equals(name)) {
				return i;
			}
		}
		return null;
	}
}

