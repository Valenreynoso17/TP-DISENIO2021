package main.java.interfaces.clasesExtra;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderParaTablaEstadoColores extends DefaultTableCellRenderer{
	
	private static final long serialVersionUID = 1L;
	
	Color colorRojo = Color.decode("#f44336");
	Color colorVerde = Color.decode("#53f436");
	Color colorAzul = Color.decode("#2196f3");
	Color colorAmarillo = Color.decode("#ffeb3b");
	Color colorSeleccionado = Color.decode("#bdbdbd");
	
	Component c;
	
	List<ArrayList<Integer>> celdasSeleccionadas = new ArrayList<ArrayList<Integer>>();
	List<ArrayList<Integer>> celdasOcupadas = new ArrayList<ArrayList<Integer>>();			//TODO: Ver si vale la pena, quizas mas adelante no haga falta
	List<ArrayList<Integer>> celdasFueraDeServicio = new ArrayList<ArrayList<Integer>>();	//TODO: Ver si vale la pena, quizas mas adelante no haga falta
	
	int columnaSeleccion = 0;			//Es la columna de la primera seleccion, para que no pueda seleccionar celdas de distintas columnas
	boolean banderaPrimeraVez = true;	//Bandera hecha simplemente para que la primera vez se entre al if y luego dependa de "columnaSeleccion"

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        this.pintarTodasLasCeldas(c, row, column);
        	
        ArrayList<Integer> filaYColumnaParaRePintar = new ArrayList<Integer>();	filaYColumnaParaRePintar.add(row); filaYColumnaParaRePintar.add(column);
        
       // System.out.println(filaYColumnaParaRePintar);
     
	      if (isSelected &&													 // Para pintar de gris
	    	  table.getSelectedColumn() == column && 						 // Para que no coloree toda la fila, simplemente la columna indicada
	    	  column != 0 && 												 // column == 0 es la FECHA
	    	  !celdasOcupadas.contains(filaYColumnaParaRePintar) &&				 // No debe estar ocupada
	    	  !celdasFueraDeServicio.contains(filaYColumnaParaRePintar) &&		 // No debe estar fuera de servicio
	    	  (banderaPrimeraVez || column == columnaSeleccion)) {			 // Debe permanecer siempre en una misma columna
	    	  
	    	  banderaPrimeraVez = false;	// La bandera se pone en falso porque ya cumplio su funcion (dejar entrar la primera vez al if)
	    	  columnaSeleccion = column;
	    	  
	    	  if(celdasSeleccionadas.isEmpty()) {
	    		  
		    	  ArrayList<Integer> filaYColumna = new ArrayList<Integer>();	filaYColumna.add(row); filaYColumna.add(column);
		    	  celdasSeleccionadas.add(filaYColumna);
		    	  
		    	  c.setBackground(colorSeleccionado);
	    	  }
	    	  else {
		    	  int ultimaFilaSeleccionada = celdasSeleccionadas.get(celdasSeleccionadas.size()-1).get(0);
		    	  System.out.println("UltimaFilaSeleccionada: "+ultimaFilaSeleccionada);
		    	  this.mostrarArrayList(celdasSeleccionadas);
		    	  
		    	 	//Si la fila seleccionada ahora es igual a la siguiente fila que habia seleccionado antes
		    	  	if(table.getSelectedRow() == ultimaFilaSeleccionada+1) {
		    		  
		    		  c.setBackground(colorSeleccionado);	//Para que no pueda seleccionar fechas anteriores a la primera seleccion
		    		  
		    		  ArrayList<Integer> filaYColumna = new ArrayList<Integer>();	filaYColumna.add(row); filaYColumna.add(column);
	    			  celdasSeleccionadas.add(filaYColumna);
		    		  
//		    		  for(int i = 1; i <= (table.getSelectedRow()-ultimaFilaSeleccionada); i++) {
//		    			  
//		    			  ArrayList<Integer> filaYColumna = new ArrayList<Integer>();	filaYColumna.add(i); filaYColumna.add(column);
//		    			  celdasSeleccionadas.add(filaYColumna);
//		    	    	  
//		    	    	  
//		    		  }
		    	  }
	    	  }
	    	  
	
	      }
	      
	      if(celdasSeleccionadas.contains(filaYColumnaParaRePintar)) {	// Si estan dentro de la lista, se vuelven a colorear de "colorSeleccionado"
	    	  c.setBackground(colorSeleccionado);
	      }
	      
     
        return c;
    }	
	
	public void pintarTodasLasCeldas(Component c, int row, int column) {
		
	    	if (column == 0) {
	    	  c.setBackground(Color.white);
	      } else if (column == 1) {
	    	  c.setBackground(colorRojo);
	    	  ArrayList<Integer> filaYColumna = new ArrayList<Integer>();	filaYColumna.add(row); filaYColumna.add(column);
	    	  celdasOcupadas.add(filaYColumna);
	      } else if (column == 2) {
	    	  c.setBackground(colorVerde);
	      } else if (column == 3) {
	    	  c.setBackground(colorAzul);
	      } else if (column == 4) {
	    	  c.setBackground(colorAmarillo);
	    	  ArrayList<Integer> filaYColumna = new ArrayList<Integer>();	filaYColumna.add(row); filaYColumna.add(column);
	    	  celdasFueraDeServicio.add(filaYColumna);
	      }
	      else {
	    	  c.setBackground(colorVerde);
	      }
	}
	
	public List<ArrayList<Integer>> getCeldasSeleccionadas(){
		
		return this.celdasSeleccionadas;
	}
	
	public boolean celdaYaSeleccionada(int r, int c) {
		
		boolean resultado = false;
		
		ArrayList<Integer> filaYColumna = new ArrayList<Integer>();	filaYColumna.add(r); filaYColumna.add(c);
		
		if(this.celdasSeleccionadas.contains(filaYColumna)) {
			resultado = true;
		}
		
		
		return resultado;
	}
	
	public void mostrarArrayList(List<ArrayList<Integer>> lista) {
		
		for(int i = 0; i < lista.size(); i++) {
			
			System.out.println(lista.get(i)+": fila: "+lista.get(i).get(0)+" columna: "+lista.get(i).get(1));
		}
	}
}

