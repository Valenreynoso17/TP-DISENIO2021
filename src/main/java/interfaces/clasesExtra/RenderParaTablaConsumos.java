package main.java.interfaces.clasesExtra;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderParaTablaConsumos extends DefaultTableCellRenderer{
	
	private static final long serialVersionUID = 1L;
	
	private Font fuenteTotalTabla = new Font("SourceSansPro", Font.BOLD, 14);

	Component c;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        //if()
        //c.setFont(getFont());
        
        return c;
    }	

}
