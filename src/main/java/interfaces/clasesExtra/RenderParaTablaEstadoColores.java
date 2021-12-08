package main.java.interfaces.clasesExtra;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import main.java.enums.EstadoHabitacion;

public class RenderParaTablaEstadoColores extends DefaultTableCellRenderer{
	
	private static final long serialVersionUID = 1L;
	
	Color colorRojo = Color.decode("#f44336");
	Color colorVerde = Color.decode("#53f436");
	Color colorAzul = Color.decode("#2196f3");
	Color colorAmarillo = Color.decode("#ffeb3b");
	Color colorSeleccionado = Color.decode("#bdbdbd");
	
	Component c;
	
	List<ArrayList<Integer>> celdasSeleccionadas = new ArrayList<ArrayList<Integer>>();
	List<ArrayList<Integer>> celdasReservadas = new ArrayList<ArrayList<Integer>>();
	List<ArrayList<Integer>> celdasOcupadas = new ArrayList<ArrayList<Integer>>();			
	List<ArrayList<Integer>> celdasFueraDeServicio = new ArrayList<ArrayList<Integer>>();	
	
	boolean banderaPrimeraVez = true;	//Bandera hecha simplemente para que la primera vez se entre al if y luego dependa de "columnaSeleccion"
	int columnaSeleccion = -1;			//Es la columna de la primera seleccion, para que no pueda seleccionar celdas de distintas columnas
	int ultimaFilaSeleccionada = -1;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		
		  if(column != 0) {
        	  c = super.getTableCellRendererComponent(table, "", isSelected, hasFocus, row, column);      
		  }
		  else {
			  c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);  
		  }

		  this.pintarCelda(value, c, row, column);
        	
		  ArrayList<Integer> filaYColumnaParaRePintar = new ArrayList<Integer>();	filaYColumnaParaRePintar.add(row); filaYColumnaParaRePintar.add(column);
		  
	      if (isSelected &&													 // Para pintar de gris										
	    	  table.getSelectedColumn() == column && 						 // Para que no coloree toda la fila, simplemente la columna indicada
	    	  column != 0 && 												 // column == 0 es la FECHA
	    	  !celdasOcupadas.contains(filaYColumnaParaRePintar) &&			 // No debe estar ocupada
	    	  !celdasFueraDeServicio.contains(filaYColumnaParaRePintar) &&	 // No debe estar fuera de servicio
	    	  // Si es la primera vez, entra por la bandera. Sino, debe cumplirse el seleccionar la misma columna y que la fila sea mayor a su celda anterior
	    	  (banderaPrimeraVez || (column == columnaSeleccion && row > ultimaFilaSeleccionada)) &&
	    	  this.comprobarQueNoExistaOcupacionNiFueraDeEstadoEnLaColumna()) {	// Comprueba si no hay una ocupacion o un fuera de estado en la misma columna actualmente
	    	  
	    	  if(banderaPrimeraVez) {
	    		  ultimaFilaSeleccionada = row;
	    		  columnaSeleccion = column;
		    	  banderaPrimeraVez = false;
	    	  }
	    		  
	    	  ArrayList<Integer> filaYColumna;
	    	  
	    	  c.setBackground(colorSeleccionado);
	    	  
	    	  for(int i = (row-ultimaFilaSeleccionada); i <= row; i++) {
				  
				  filaYColumna = new ArrayList<Integer>();	
				  filaYColumna.add(i); 	filaYColumna.add(column);
				  
				  if(!celdasSeleccionadas.contains(filaYColumna))
					  celdasSeleccionadas.add(filaYColumna);   	
	    	  }
	    	  
	    	  ultimaFilaSeleccionada = row;
	      }
	      
	      if(celdasSeleccionadas.contains(filaYColumnaParaRePintar)) {	// Si estan dentro de la lista, se vuelven a colorear de "colorSeleccionado"
	    	  c.setBackground(colorSeleccionado);
	    	  
	      }
	      
        return c;
    }	
	
	private boolean comprobarQueNoExistaOcupacionNiFueraDeEstadoEnLaColumna() {
		
		
		return true;
	}

	public void pintarCelda(Object value, Component c, int row, int column) {
		
  	  ArrayList<Integer> filaYColumna = new ArrayList<Integer>();	filaYColumna.add(row); filaYColumna.add(column);
  	 
  	  if(column == 0) {	//FECHA
  		c.setBackground(Color.white);
  	  }
  	  else if(value == null) {	//Ver si vale la pena dejar el value como null y hacer el if aca o colocar la fila como EstadoHabitacion.LIBRE
  		c.setBackground(colorVerde);
  	  }
  	  else {
  		switch((EstadoHabitacion) value) {
  		
  			case LIBRE:
  	    	  c.setBackground(colorVerde);
  	    	  break;
  			case RESERVADA:
  	    	  c.setBackground(colorAzul);
  	    	  celdasReservadas.add(filaYColumna);
  	    	  break;
  			case OCUPADA:
  	    	  c.setBackground(colorRojo);
  	    	  celdasOcupadas.add(filaYColumna);
  	    	  break;
  			case FUERA_DE_SERVICIO:
  	    	  c.setBackground(colorAmarillo);
  	    	  celdasFueraDeServicio.add(filaYColumna);
  	    	  break;
  		}
  		  
  	  }
	}
	
	public List<ArrayList<Integer>> getCeldasSeleccionadas(){
		
		return this.celdasSeleccionadas;
	}
	
	public List<ArrayList<Integer>> getCeldasReservadas(){
		
		return this.celdasReservadas;
	}
	
	public boolean celdaYaSeleccionada(int r, int c) {
		
		boolean resultado = false;
		
		ArrayList<Integer> filaYColumna = new ArrayList<Integer>();	filaYColumna.add(r); filaYColumna.add(c);
		
		if(this.celdasSeleccionadas.contains(filaYColumna)) {
			resultado = true;
		}
		
		
		return resultado;
	}
	
}

