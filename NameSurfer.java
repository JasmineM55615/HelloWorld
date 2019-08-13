/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.graphics.*;
import acm.program.*;
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
	
	public void init() {
		// You fill this in, along with any helper methods //
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
			//println("You pressed enter or the button");
			//println("Graph: " + newNew);
		}
		if (e.getActionCommand().equals("Clear")) {
			//println("Clear");
			
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
	private ArrayList<NameSurferEntry> inputs = new ArrayList<NameSurferEntry>();
	private void redraw() {
		// You fill this in //
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
		//for loop for drawing the lines 
		//
		for(int i = 0; i < inputs.size(); i++) {
			int YOne = inputs.get(i).getRank;
		}
		//forloop for lines (- 1)
		//for loop for label(just line)
		// for colors, mod 4

}
}
