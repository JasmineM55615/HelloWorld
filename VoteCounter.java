/*
 * File: VoteCounter.java
 * ---------------------
 * A sandcastle program that uses collections to tally votes 
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
		printVoteCounts(votes);

	}

	/*
	 * Your job is to implement this method according to 
	 * the problem specification. 
	 */
	private void printVoteCounts(ArrayList<String> votes) {
		HashMap<String, Integer> indivNames = new HashMap<String, Integer>();
		int len = votes.size();
		for(int i = 0; i < len; i++) {
			String name = votes.get(i);
			int nameCount = 0;
			for(int j = 0; j < len; j++) {
				if(votes.get(j).equals(name)) {
					nameCount++;
				}
				indivNames.put(name, nameCount);
			}
		}
		for(String key: indivNames.keySet()) {
			println(key + ": " + indivNames.get(key));
		}
		//println(indivNames);
	}
}
