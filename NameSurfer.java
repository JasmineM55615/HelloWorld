/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.graphics.*;
import acm.program.*;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import acm.util.*;

public class NameSurfer extends GraphicsProgram implements NameSurferConstants {

	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the top of the window.
	 */

	private JLabel label = new JLabel("Name:");
	private JTextField putName = new JTextField(TEXT_FIELD_WIDTH);
	private JButton graph = new JButton("Graph");
	private JButton clear = new JButton("Clear");
	private NameSurferDataBase database;

	public void init() {
		// You fill this in, along with any helper methods //
		database = new NameSurferDataBase("names-data.txt");
		add(label, NORTH);
		putName.addActionListener(this);
		putName.setActionCommand("Graph");
		add(putName, NORTH);
		add(graph, NORTH);
		add(clear, NORTH);
		addActionListeners();
	}

	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	private ArrayList<NameSurferEntry> inputs = new ArrayList<NameSurferEntry>();
	
	private Color[] colors = {Color.BLACK, Color.RED, Color.BLUE, Color.MAGENTA};
	private int counter = 0;
	
	public void actionPerformed(ActionEvent e) {
		String newStrName = putName.getText();
		newStrName.toLowerCase();
		String newStr = "";
		for(int i = 1; i < newStrName.length(); i++) {
			newStr += newStrName.charAt(i);
		}
		char c = Character.toUpperCase(newStrName.charAt(0));
		String newNew = c + newStr;
		if(e.getActionCommand().equals("Graph")){
			redraw();

			if(database.findEntry(newNew) != null) {
				int newcounter = counter%4;
				Color col = colors[newcounter];
				counter+=1;
				NameSurferEntry t = database.findEntry(newNew);
				inputs.add(t);
				double xPointOne = 0;
				double yPointOne = getHeight() - GRAPH_MARGIN_SIZE;
				double xPointTwo = getWidth()/NDECADES;
				double yPointTwo = getHeight() - GRAPH_MARGIN_SIZE;
				
				for(int j = 0; j < NDECADES; j++) {
					double numOfName = t.getRank(j);
					String theName = t.getName();
					double mathYTwo = t.getRank(j+1);
					if(numOfName == 0 || numOfName == 1000) {
						String nameStr = "" + theName + " *";
						GLabel nameLabel = new GLabel(nameStr);
						nameLabel.setColor(col);
						add(nameLabel, xPointOne, getHeight() - GRAPH_MARGIN_SIZE);
						yPointOne = getHeight()-GRAPH_MARGIN_SIZE;
					}else {
						GLabel nameLabel = new GLabel("" + theName + " " + ((int) numOfName));
						nameLabel.setColor(col);
						yPointOne = (numOfName / 1000) * (getHeight() - (GRAPH_MARGIN_SIZE * 2)) + GRAPH_MARGIN_SIZE;
						add(nameLabel, xPointOne, yPointOne);
					}
					if(mathYTwo == 0 || mathYTwo == 1000) {
						yPointTwo = getHeight()-GRAPH_MARGIN_SIZE;
					} else {
						yPointTwo = (mathYTwo / 1000) * (getHeight() - (GRAPH_MARGIN_SIZE * 2)) + GRAPH_MARGIN_SIZE;
					}
					if(yPointTwo > 20) {
						GLine lineOnGraph = new GLine(xPointOne, yPointOne, xPointTwo, yPointTwo);
						lineOnGraph.setColor(col);
						add(lineOnGraph);
					}
					xPointOne = xPointTwo;
					yPointOne = yPointTwo;
					xPointTwo += getWidth()/NDECADES;
				}
				//println("You pressed enter or the button");
				//println("Graph: " + newNew);
			}
		}
		if (e.getActionCommand().equals("Clear")) {
			//println("Clear");
			inputs.clear();
			redraw();
		}
	}

	/**
	 * This class is responsible for detecting when the the canvas
	 * is resized. This method is called on each resize!
	 */
	public void componentResized(ComponentEvent e) { 
		redraw();
	}

	/**
	 * A helper method that we *strongly* recommend. Redraw clears the
	 * entire display and repaints it. Consider calling it when you change
	 * anything about the display.
	 */
	private void redraw() {
		// You fill this in //
		removeAll();
		
		int XSpace = 0;
		for(int i = 0; i < NDECADES; i++) {
			GLine line = new GLine(XSpace, 0, XSpace, getHeight());
			XSpace += getWidth() / NDECADES;
			add(line);
			//System.out.println("hi");
		}
		GLine otherLineOne = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		add(otherLineOne);
		GLine otherLineTwo = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
		add(otherLineTwo);
		int decade = 1900;
		int decadeSpace = 0;
		for(int i = 0; i < NDECADES; i++) {
			GLabel decadeDisplay = new GLabel("" + decade);
			decade+= 10;
			add(decadeDisplay, decadeSpace, getHeight() - DECADE_LABEL_MARGIN_SIZE);
			decadeSpace += getWidth() / NDECADES;
		}
		//for loop for drawing the lines 
		//double xPointOne = 0;
		double xPointOne = 0;
		double yPointOne = getHeight() - GRAPH_MARGIN_SIZE;
		double xPointTwo = getWidth()/NDECADES;
		double yPointTwo = getHeight() - GRAPH_MARGIN_SIZE;
		int count = 0;
		for(int i = 0; i < inputs.size(); i++) {
			int newcounter = count%4;
			Color col = colors[newcounter];
			count+=1;
			for(int j = 0; j < NDECADES; j++) {
				double numOfName = inputs.get(i).getRank(j);
				String theName = inputs.get(i).getName();
				double mathYTwo = inputs.get(i).getRank(j+1);
				if(numOfName == 0 || numOfName == 1000) {
					String nameStr = "" + theName + " *";
					GLabel nameLabel = new GLabel(nameStr);
					nameLabel.setColor(col);
					add(nameLabel, xPointOne, getHeight() - GRAPH_MARGIN_SIZE);
					yPointOne = getHeight()-GRAPH_MARGIN_SIZE;
				}else {
					GLabel nameLabel = new GLabel("" + theName + " " + ((int) numOfName));
					nameLabel.setColor(col);
					yPointOne = (numOfName / 1000) * (getHeight() - (GRAPH_MARGIN_SIZE * 2)) + GRAPH_MARGIN_SIZE;
					add(nameLabel, xPointOne, yPointOne);
				}
				if(mathYTwo == 0 || mathYTwo == 1000) {
					yPointTwo = getHeight()-GRAPH_MARGIN_SIZE;
				} else {
					yPointTwo = (mathYTwo / 1000) * (getHeight() - (GRAPH_MARGIN_SIZE * 2)) + GRAPH_MARGIN_SIZE;
				}
				if(yPointTwo > 20) {
					GLine lineOnGraph = new GLine(xPointOne, yPointOne, xPointTwo, yPointTwo);
					lineOnGraph.setColor(col);
					add(lineOnGraph);
				}
				xPointOne = xPointTwo;
				yPointOne = yPointTwo;
				xPointTwo += getWidth()/NDECADES;
			}
		}
	}
	//		for(int i = 0; i < inputs.size(); i++) {
	//			for(int j = 0; j < NDECADES; j++) {
	//				int YOne = inputs.get(i).getRank(j);
	//				if(YOne == 0) {
	//					String nameLabel = "" + inputs.get(i).getName() + "*";
	//				}else {
	//					nameLabel = 
	//				}
	//			}
	//		}
	//TO DO
	//forloop for lines (- 1)
	//for loop for label(just line)
	// for colors, mod 4

}

