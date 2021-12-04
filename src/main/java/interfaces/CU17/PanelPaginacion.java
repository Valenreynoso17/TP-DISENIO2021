package main.java.interfaces.CU17;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelPaginacion extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private Paginable panelPadre;
	private Integer tamPagina;
	private Integer cantPaginas;
	
	static private Integer botonesACadaLado = 2;
	
	public PanelPaginacion(Paginable panelPadre, Integer tamPagina) {
		this.panelPadre = panelPadre;
		this.tamPagina = tamPagina;
		this.cantPaginas = 1;
		
		this.setBackground(Color.WHITE);
		
		//this.add(new Label("Paginacion"));
		
	}
	
	public void refrescarCantidadResultados(Integer cantResultados, Integer paginaActual) {
		this.cantPaginas = cantResultados / this.tamPagina;
		
		if (cantResultados % this.tamPagina != 0) this.cantPaginas++;
		
		refrescarBotones(paginaActual);
	}
	
	public void refrescarBotones(Integer paginaActual) {
		this.removeAll();
		
		if (cantPaginas > 1) {
			if (paginaActual <= botonesACadaLado + 2) {
				for (int i = 1; i<paginaActual; i++) {
					agregarBoton(i, Color.WHITE);
				}
			}
			else {
				agregarBoton(1, Color.WHITE);
				this.add(new JLabel("..."));
				
				for (int i = paginaActual-botonesACadaLado; i<paginaActual; i++) {
					agregarBoton(i, Color.WHITE);
				}
			}
			
			this.agregarBoton(paginaActual, Color.LIGHT_GRAY);
			
			if (cantPaginas - paginaActual <= botonesACadaLado + 1) {
				for (int i = paginaActual + 1; i <= cantPaginas; i++) {
					agregarBoton(i, Color.WHITE);
				}				
			}
			else {
				for (int i = paginaActual + 1; i <= paginaActual + botonesACadaLado; i++) {
					agregarBoton(i, Color.WHITE);
				}
				this.add(new JLabel("..."));
				agregarBoton(cantPaginas, Color.WHITE);
			}
		}
		
		((JPanel) panelPadre).validate();
		((JPanel) panelPadre).repaint();
	}
	
	public void agregarBoton(Integer nroBoton, Color color) {
		JButton boton = new JButton(nroBoton.toString());
		
		boton.setBackground(color);
		
		boton.setBorder(new EmptyBorder(8, 10, 8, 10));
		boton.setFocusable(false);
		boton.addActionListener(e -> panelPadre.cambiarPagina(nroBoton));
		this.add(boton);
	}
	
}
