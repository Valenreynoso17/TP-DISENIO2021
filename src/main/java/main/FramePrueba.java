package main.java.main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class FramePrueba extends JFrame{
	public FramePrueba() {
		setSize(new Dimension(800, 600));
		setContentPane(new PanelPrueba());
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
