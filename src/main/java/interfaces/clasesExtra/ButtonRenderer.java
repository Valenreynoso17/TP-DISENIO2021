package main.java.interfaces.clasesExtra;

import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JButton implements TableCellRenderer {
	
	private Character tipo;

    public ButtonRenderer(Character c) {
        setOpaque(true);
        this.tipo = c;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		setMargin(new Insets(0, 0, 0, 0));
		setBorder(null);
		this.setBorder(null);
        setForeground(Color.BLACK);	
        setBackground(Color.decode("#e0e0e0"));	
        
        if(value == null) {	//Si es una fila en blanco
        	return null;
        }

        if(this.tipo == '+') {
        	
        	setText("+");
        }
        else if(this.tipo == '-') {
        	
        	setText("-");
        }
        
        return this;
    }
}
