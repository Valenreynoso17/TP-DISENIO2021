package main.java.interfaces.clasesExtra;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderParaTablaPasajerosSeleccionados extends DefaultTableCellRenderer{
	
	private static final long serialVersionUID = 1L;
	
	Component c;
	
	Color colorPrimeraFila = Color.decode("#e0e0e0");

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        if(row == 0) {
        	c.setBackground(colorPrimeraFila);
        }
        else {
        	c.setBackground(Color.WHITE);
        }

        return c;
    }	

}
