package main.java.interfaces.clasesExtra;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class RenderParaTablaEstadoColores extends DefaultTableCellRenderer{
	
	private static final long serialVersionUID = 1L;
	
	Color colorRojo = Color.decode("#f44336");
	Color colorVerde = Color.decode("#53f436");
	Color colorAzul = Color.decode("#2196f3");
	Color colorAmarillo = Color.decode("#ffeb3b");
	Color colorSeleccionado = Color.decode("#bdbdbd");
	
	List<Integer[]> celdasSeleccionadas = new ArrayList<Integer[]>();
	
	int ultimaFilaSeleccionada = 0;
	int ultimaColumnaSeleccionada = 0;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
//        if(ultimaFilaSeleccionada != 0 && ultimaColumnaSeleccionada != 0) {	//Si se seleccionó una casilla anteriormente
//        	
//	    	  if(celdasSeleccionadas.get(0)[1] == table.getSelectedColumn()) {	//La casilla seleccionada está en la misma columna que la seleccionada anteriormente
//	    		  for(int i = celdasSeleccionadas.get(0)[0]; i <= (table.getSelectedRow()-celdasSeleccionadas.get(0)[0])+1; i++) {
//
//	    			  super.getTableCellRendererComponent(table, value, isSelected, hasFocus, i, celdasSeleccionadas.get(0)[1]).setBackground(colorSeleccionado);
//	    		  }
//	    	  }
//        }
//        if(!super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column).getBackground().equals(colorSeleccionado)) {
	      
        
        	if (column == 0) {
	    	  c.setBackground(Color.white);
	      } else if (column == 1) {
	    	  c.setBackground(colorRojo);
	      } else if (column == 2) {
	    	  c.setBackground(colorVerde);
	      } else if (column == 3) {
	    	  c.setBackground(colorAzul);
	      } else if (column == 4) {
	    	  c.setBackground(colorAmarillo);
	      }
//	      else {
//	    	  c.setBackground(colorVerde);
//	      }
//		}	      
	      if (isSelected && table.getSelectedColumn() == column && column != 0) {	//Para pintar de gris / column == 0 es la FECHA
	    	  c.setBackground(colorSeleccionado);
	    	  
	    	  celdasSeleccionadas.add(new Integer[] {row, column});
	    	  
	    	  this.pintarCelda(c, table, value, isSelected, hasFocus);
	    	  
	    	  ultimaFilaSeleccionada = row;
	    	  ultimaColumnaSeleccionada = column;
	      }
	      
	      if(celdasSeleccionadas.contains(new Integer[] {row, column})) {
	    	  c.setBackground(colorSeleccionado);
	      };

	      
        return c;
    }	
	
	public void pintarCelda(Component c, JTable table, Object value, boolean isSelected, boolean hasFocus) {
		
	      for(int j = 0; j < celdasSeleccionadas.size(); j++) {
	    	  c.setBackground(colorSeleccionado);
	    	  //super.getTableCellRendererComponent(table, value, isSelected, hasFocus, celdasSeleccionadas.get(j)[0], celdasSeleccionadas.get(j)[1]).setBackground(colorSeleccionado);
	      }
	}
	
}

