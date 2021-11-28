package main.java.main;

import javax.swing.JPanel;
import javax.swing.JTable;

import main.java.interfaces.CU02.PanelGestionarPasajeroTabla;

public class PanelPrueba extends JPanel{
	private JPanel panel;
	
	public PanelPrueba() {
		panel = new PanelGestionarPasajeroTablaPrueba(null);
		this.add(panel);
	}
}
