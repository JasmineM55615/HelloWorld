/*
 * File: VoteCounter.java
 * ---------------------
 * Name: Jasmine Mann
 * Section Leader: Trey Connelly
 * A sandcastle program that uses collections to tally votes.
 * It checks how many time each student has been voted for
 * and prints out the studnet's name with the number of votes
 * they have beside it.
 */

import acm.program.*;
import java.util.*;

public class VoteCounter extends ConsoleProgram {
	public void run() {
		ArrayList<String> votes = new ArrayList<String>();
		votes.add("Zaphod Beeblebrox");
		votes.add("Arthur Dent");
		votes.add("Trillian McMillian");
		votes.add("Zaphod Beeblebrox");
		votes.add("Marvin");
		votes.add("Mr. Zarniwoop");
		votes.add("Trillian McMillian");
		votes.add("Zaphod Beeblebrox");
		votes.add("Zaphod Beeblebrox");
		printVoteCounts(votes);

	}

	/*
	 * Goes through the arraylist votes and checks how many times a single name appears
	 * and keeps track of the count of each name. It prints out each individual name
	 * with the number of votes it has beside it.
	 */
	private void printVoteCounts(ArrayList<String> votes) {
		HashMap<String, Integer> indivNames = new HashMap<String, Integer>();
		int len = votes.size();
		for(int i = 0; i < len; i++) {
			String name = votes.get(i);
			int nameCount = 0;
			for(int j = 0; j < len; j++) {
				if(votes.get(j).equals(name)) { //goes through the arraylist votes to see how many times the name appears
					nameCount++; //adds one to nameCount each time that name appears in the arraylist
				}
				indivNames.put(name, nameCount); //adds the name of person and the number of votes they have to a new HashMap
			}
		}
		for(String key: indivNames.keySet()) {
			println("Votes for " + key + ": " + indivNames.get(key));
		}
	}
}
