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
		// go thorugh each index and get the name at that index
		// go through every other index and check if it has the same name as the one we chose
		//if it does, we add one and save that name to another string
		//repeat
		

		HashMap<String, Integer> indivNames = new HashMap<String, Integer>();
		//ArrayList<String> indivNames = new ArrayList<String>();
		int len = votes.size();
		for(int i = 0; i < len; i++) {
			String name = votes.get(i);
			int nameCount = 0;
			for(int j = i + 1; j < len; j++) {
				if(votes.get(j).equals(name)) {
					nameCount++;
				}else {
					nameCount = 1;
				}
				
			}
			indivNames.put(name, nameCount);
		}

		println(indivNames);
	}
}
