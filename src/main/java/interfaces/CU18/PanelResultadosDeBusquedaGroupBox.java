package main.java.interfaces.CU18;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class PanelResultadosDeBusquedaGroupBox extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 20);	
	
	public PanelResultadosDeBusquedaGroupBox() {
		
		this.setBackground(Color.white);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), " Resultados de búsqueda", 0, 0, fuenteGroupBox));
		
		
	}
}
