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
	private ArrayList<NameSurferEntry> inputs = new ArrayList<NameSurferEntry>();
	private Color[] colors = {Color.BLACK, Color.RED, Color.BLUE, Color.MAGENTA};
	private int counter = 0;

	public void init() {
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
	 * 
	 * 
	 */
	
	public void actionPerformed(ActionEvent e) {
		String newStrName = putName.getText();
		String lowerCase = newStrName.toLowerCase();
		String newStr = "";
		char c = 'a';
		for(int i = 1; i < lowerCase.length(); i++) {
			newStr += lowerCase.charAt(i); //turns the user input to all lowercase characters
		}
		if(newStr.length() > 0) { //checks if the user input has a length greater than zero
			c = Character.toUpperCase(lowerCase.charAt(0));
		}
		String newNew = c + newStr;
		if(e.getActionCommand().equals("Graph")){
			redraw();
			if(database.findEntry(newNew) != null) {
				int newcounter = counter % 4; //takes the counter variable mod 4 to rotate the color of the graph using four color
				Color col = colors[newcounter];
				counter += 1;
				NameSurferEntry t = database.findEntry(newNew);
				inputs.add(t);
				double xPointOne = 0;
				double yPointOne = getHeight() - GRAPH_MARGIN_SIZE;
				double xPointTwo = getWidth() / NDECADES;
				double yPointTwo = getHeight() - GRAPH_MARGIN_SIZE;
				for(int j = 0; j < NDECADES; j++) {
					double numOfName = t.getRank(j);
					String theName = t.getName();
					double mathYTwo = t.getRank(j+1);
					if(numOfName == 0) { //
						String nameStr = "" + theName + " *";
						GLabel nameLabel = new GLabel(nameStr);
						nameLabel.setColor(col);
						add(nameLabel, xPointOne, getHeight() - GRAPH_MARGIN_SIZE);
						yPointOne = getHeight() - GRAPH_MARGIN_SIZE;
					}else {
						GLabel nameLabel = new GLabel("" + theName + " " + ((int) numOfName));
						nameLabel.setColor(col);
						yPointOne = (numOfName / 1000) * (getHeight() - (GRAPH_MARGIN_SIZE * 2)) + GRAPH_MARGIN_SIZE;
						add(nameLabel, xPointOne, yPointOne);
					}
					if(mathYTwo == 0) {
						yPointTwo = getHeight() - GRAPH_MARGIN_SIZE;
					} else {
						yPointTwo = (mathYTwo / 1000) * (getHeight() - (GRAPH_MARGIN_SIZE * 2)) + GRAPH_MARGIN_SIZE;
					}
					if(yPointTwo > GRAPH_MARGIN_SIZE) {
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
		if (e.getActionCommand().equals("Clear")) {
			inputs.clear();
			counter = 0;
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
		removeAll();
		int XSpace = 0;
		for(int i = 0; i < NDECADES; i++) {
			GLine line = new GLine(XSpace, 0, XSpace, getHeight());
			XSpace += getWidth() / NDECADES;
			add(line);
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
		double xPointOne = 0;
		double yPointOne = getHeight() - GRAPH_MARGIN_SIZE;
		double xPointTwo = getWidth() / NDECADES;
		double yPointTwo = getHeight() - GRAPH_MARGIN_SIZE;
		int count = 0;
		for(int i = 0; i < inputs.size(); i++) {
			int newcounter = count % 4;
			Color col = colors[newcounter];
			count += 1;
			NameSurferEntry t = inputs.get(i);
			xPointOne = 0;
			xPointTwo = getWidth() / NDECADES;
			for(int j = 0; j < NDECADES; j++) {
				double numOfName = t.getRank(j);
				String theName = t.getName();
				double mathYTwo = t.getRank(j+1);
				if(numOfName == 0) {
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
				if(mathYTwo == 0) {
					yPointTwo = getHeight()-GRAPH_MARGIN_SIZE;
				} else {
					yPointTwo = (mathYTwo / 1000) * (getHeight() - (GRAPH_MARGIN_SIZE * 2)) + GRAPH_MARGIN_SIZE;
				}
				if(yPointTwo > GRAPH_MARGIN_SIZE) {
					GLine lineOnGraph = new GLine(xPointOne, yPointOne, xPointTwo, yPointTwo);
					lineOnGraph.setColor(col); //sets the color of the label depending on the variable counter
					add(lineOnGraph);
				}
				xPointOne = xPointTwo;
				yPointOne = yPointTwo;
				xPointTwo += getWidth()/NDECADES;
			}
		}
	}
}
