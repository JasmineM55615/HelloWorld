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

public class NameSurfer extends ConsoleProgram implements NameSurferConstants {

	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the top of the window.
	 */
	
	private JLabel label = new JLabel("Name:");
	putName.addActionListener(this);
	putName.setActionCommand("Go");
	private JTextField putName = new JTextField(TEXT_FIELD_WIDTH);
	putName.addActionListener(this);
	putName.setActionCommand("Go");
	private JButton graph = new JButton("Graph");
	private JButton clear = new JButton("Clear");
	public void init() {
		// You fill this in, along with any helper methods //
		add(label, NORTH);
		add(putName, NORTH);
		add(graph, NORTH);
		add(clear, NORTH);
	}

	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
		String newStrName = putName.getText();
		newStrName.toLowerCase();
		char c = Character.toUpperCase(newStrName.charAt(0));
	//	println(newStrName);
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
	}
}
