package main.java.interfaces.frames;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import main.java.interfaces.CU01.PanelAutenticarUsuario;

public class FramePrincipal extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	final static String panelActualString = "PanelActual";

	private CardLayout cardLayout;
	private JPanel panelPrincipal;
	
	private JPanel panelActual;

	public FramePrincipal() {
		super("Sistema Hotel Premier");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		
		cardLayout = new CardLayout();
		panelPrincipal = new JPanel(cardLayout);
		
		panelActual = new PanelAutenticarUsuario(this);
		
		panelPrincipal.add(panelActual, panelActualString);
		
		mostrarPanelNuevo();
		
		setContentPane(panelPrincipal);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
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
	
//	final static String panelActualString = "PanelActual";
//	final static String panelViejoString = "PanelViejo";
//
//	private CardLayout cardLayout;
//	private JPanel panelPrincipal;
//	
//	private JPanel panelViejo;
//	private JPanel panelActual;
//
//	public FramePrincipal() {
//		super("Sistema Hotel Premier");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 1024, 600);
//		
//		cardLayout = new CardLayout();
//		panelPrincipal = new JPanel(cardLayout);
//		
//		panelActual = new PanelAutenticarUsuario(this);
//		panelViejo = new JPanel();
//		
//		panelPrincipal.add(panelActual, panelActualString);
//		panelPrincipal.add(panelViejo, panelViejoString);
//		
//		mostrarPanelNuevo();
//		
//		setContentPane(panelPrincipal);
//		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		
//		this.setVisible(true);
//		this.setLocationRelativeTo(null);
//	}
//	
//	public void setNuevoPanel(JPanel panelNuevo) {
//
//		panelViejo = panelActual;
//		panelActual = panelNuevo;
//
//		mostrarPanelNuevo();
//	}
//	
//	public void cargarPanelViejo() {
//		
//		mostrarPanelViejo();
//	}
//
//	private void mostrarPanelNuevo() {
//		panelPrincipal.remove(panelActual);
//		panelPrincipal.add(panelActual, panelActualString);
//        cardLayout.show(panelPrincipal, panelActualString);
//    }
//
//    private void mostrarPanelViejo() {
//		panelPrincipal.remove(panelViejo);
//		panelPrincipal.add(panelViejo, panelViejoString);
//    	cardLayout.show(panelPrincipal, panelViejoString);
//    }
}
