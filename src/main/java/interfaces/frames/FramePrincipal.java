package main.java.interfaces.frames;

import java.awt.CardLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import main.java.interfaces.CU01.PanelAutenticarUsuario;

public class FramePrincipal extends JFrame implements FrameODialog{
	
	private static final long serialVersionUID = 1L;
	
	final static String panelActualString = "PanelActual";

	private CardLayout cardLayout;
	private JPanel panelPrincipal;
	
	private JPanel panelActual;

	public FramePrincipal() {
		super("Sistema Hotel Premier");
		setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		
		cardLayout = new CardLayout();
		panelPrincipal = new JPanel(cardLayout);
		
		panelActual = new PanelAutenticarUsuario(this);
		
		panelPrincipal.add(panelActual, panelActualString);
		
		mostrarPanelNuevo();
		
		setContentPane(panelPrincipal);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	public void setNuevoPanel(JPanel panelNuevo) {
		panelActual = panelNuevo;

		mostrarPanelNuevo();
	}
	
	private void mostrarPanelNuevo() {
		panelPrincipal.remove(panelActual);
		panelPrincipal.add(panelActual, panelActualString);
        cardLayout.show(panelPrincipal, panelActualString);
    }
	
	public void cambiarTamanio(int ancho, int largo) {
		
		setBounds(100, 100, ancho, largo);
		this.setLocationRelativeTo(null);
	}
	
	public void volverATamanioNormal() {
		
		setBounds(100, 100, 1024, 600);
		this.setLocationRelativeTo(null);
	}
}
