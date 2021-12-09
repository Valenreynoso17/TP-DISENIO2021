package main.java.interfaces.clasesExtra;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import main.java.enums.EstadoHabitacion;
import main.java.interfaces.CU05.PanelResultadosDeBusquedaHabitacionesGroupBox;

public class RenderParaTablaEstadoColores extends DefaultTableCellRenderer{
	
	private static final long serialVersionUID = 1L;
	
	private PanelResultadosDeBusquedaHabitacionesGroupBox panelGrilla;
	
	private EstadoHabitacion estadoCelda = EstadoHabitacion.LIBRE;	//Atributo creado para cuando una celda de un periodo se encuentra OCUPADA o FUERA DE SERVICIO
	
	Color colorRojo = Color.decode("#f44336");
	Color colorVerde = Color.decode("#53f436");
	Color colorAzul = Color.decode("#2196f3");
	Color colorAmarillo = Color.decode("#ffeb3b");
	Color colorSeleccionado = Color.decode("#bdbdbd");
	
	Component c;
	
	// Lista creada para cuando el conserje selecciona un período que no debería seleccionarse (porque existe una ocupación o la habitación
	// está fuera de servicio), entonces la celda que selecciona queda como "Seleccionada" y cuando baja y sube por la tabla (al volverse a
	// actualizar la celda) lanza nuevamente la excepción y el mensaje
	List<ArrayList<Integer>> celdasPreSeleccionadasQueVuelvenACargar;
	
	List<ArrayList<Integer>> celdasSeleccionadas;
	List<ArrayList<Integer>> celdasReservadas;
	List<ArrayList<Integer>> celdasOcupadas;			
	List<ArrayList<Integer>> celdasFueraDeServicio;	
	
	boolean banderaPrimeraVez = true;	//Bandera hecha simplemente para que la primera vez se entre al if y luego dependa de "columnaSeleccion"
	int columnaSeleccion = -1;			//Es la columna de la primera seleccion, para que no pueda seleccionar celdas de distintas columnas
	int ultimaFilaSeleccionada = -1;	//Fila de la última fila seleccionada, es decir, la fila anterior a la selección actual
	
	public RenderParaTablaEstadoColores (PanelResultadosDeBusquedaHabitacionesGroupBox panel) {
		this.panelGrilla = panel;
		this.celdasSeleccionadas = new ArrayList<ArrayList<Integer>>();
		this.celdasReservadas = new ArrayList<ArrayList<Integer>>();
		this.celdasOcupadas = new ArrayList<ArrayList<Integer>>();			
		this.celdasFueraDeServicio = new ArrayList<ArrayList<Integer>>();	
		
		this.celdasPreSeleccionadasQueVuelvenACargar = new ArrayList<ArrayList<Integer>>();
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		
		  if(column != 0) {
        	  c = super.getTableCellRendererComponent(table, "", isSelected, hasFocus, row, column);      
		  }
		  else {
			  c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);  
		  }

		  this.pintarCelda(value, c, row, column);
        	
		  ArrayList<Integer> celdaParaRePintar = new ArrayList<Integer>();	celdaParaRePintar.add(row); celdaParaRePintar.add(column);
		  
	      if (isSelected &&													 // Para pintar de gris										
	    	  table.getSelectedColumn() == column && 						 // Para que no coloree toda la fila, simplemente la columna indicada
	    	  column != 0 && 												 // column == 0 es la FECHA
	    	  !celdasOcupadas.contains(celdaParaRePintar) &&			 	// No debe estar ocupada
	    	  !celdasFueraDeServicio.contains(celdaParaRePintar) &&	 		// No debe estar fuera de servicio
	    	  // Si esta en la lista de "celdasPreSeleccionadasQueVuelvenACargar", entonces es una celda que no deberia ser seleccionada ya que esta luego 
	    	  // de un periodo en el cual la habitación está ocupada o fuera de servicio y, por ende, no debe seleccionarse	 
	    	  !celdasPreSeleccionadasQueVuelvenACargar.contains(celdaParaRePintar) &&
	    	  // Si es la primera vez, entra por la bandera. Sino, debe cumplirse el seleccionar la misma columna y que la fila sea mayor a su celda anterior
	    	  (banderaPrimeraVez || (column == columnaSeleccion && row > ultimaFilaSeleccionada))) {	

	    	  // Si es la primera vez que entra, se guarda la columna de la seleccion, la fila es = 0 (desde el principio) y la bandera se pone en false
	    	  if(banderaPrimeraVez) {
	    		  ultimaFilaSeleccionada = 0;
	    		  columnaSeleccion = column;
		    	  banderaPrimeraVez = false;
	    	  }
	    	  

	    	  
	    	  List<ArrayList<Integer>> celdasPreSeleccionadas = new ArrayList<ArrayList<Integer>>();
	    		  
	    	  ArrayList<Integer> filaYColumna;
	    	  
	    	  for(int filaNueva = ultimaFilaSeleccionada; filaNueva <= row; filaNueva++) {
				  
				  filaYColumna = new ArrayList<Integer>();	
				  filaYColumna.add(filaNueva); 	filaYColumna.add(column);
				  
				  if(!celdasPreSeleccionadas.contains(filaYColumna))
					  celdasPreSeleccionadas.add(filaYColumna);   	
	    	  } 
	    	  
	    	  if(this.comprobarQueNoExistaOcupacionNiFueraDeEstadoEnLaColumna(celdasPreSeleccionadas)) {
	    		  
	    		  for(int posicionCelda = 0; posicionCelda < celdasPreSeleccionadas.size(); posicionCelda++) {
	    			  
	    			  if(!celdasSeleccionadas.contains(celdasPreSeleccionadas.get(posicionCelda))) {
	    				  celdasSeleccionadas.add(celdasPreSeleccionadas.get(posicionCelda));
	    			  }
	    		  }

		    	  c.setBackground(colorSeleccionado);
		    	  ultimaFilaSeleccionada = row;
	    	  }
	    	  else {
	    		  	// Si no hay ninguna celda seleccionada, entonces la bandera vuelve a activarse dado que
					// la celda que quiso seleccionar era inválida (contenía un período en que la habitación
					// estaba ocupada o fuera de servicio)
	    		  	if(celdasSeleccionadas.isEmpty())	
	    		  		banderaPrimeraVez = true;
	    		  	celdasPreSeleccionadasQueVuelvenACargar.add(celdaParaRePintar);
	    		  	this.panelGrilla.habitacionConOcupacionOFueraDeServicioHoy(estadoCelda);
	    	  }	    	  

	      }
	      
	      if(celdasSeleccionadas.contains(celdaParaRePintar)) {	// Si estan dentro de la lista, se vuelven a colorear de "colorSeleccionado"
	    	  c.setBackground(colorSeleccionado);
	      }
	      
        return c;
    }	
	
	private boolean comprobarQueNoExistaOcupacionNiFueraDeEstadoEnLaColumna(List<ArrayList<Integer>> celdas) {
		
		// Ahora mismo tengo las celdas que han sido preSeleccionadas, es decir, las celdas entre la fila seleccionada
		// y la fila anterior (un periodo). El metodo se fija si en ese periodo alguna de las celdas corresponde a una
		// ocupacion o a un fuera de servicio. En caso positivo, el metodo devuelve FALSE. Si todas las celdas de las
		// preSeleccionadas corresponden a LIBRE o RESERVADA, entonces el metodo devuelve TRUE
		
		for(int posicionCelda = 0; posicionCelda < celdas.size(); posicionCelda++) {
			
			if(this.celdasOcupadas.contains(celdas.get(posicionCelda))){
				
				estadoCelda = EstadoHabitacion.OCUPADA;
				return false;
			}
			else if(this.celdasFueraDeServicio.contains(celdas.get(posicionCelda))) {
				
				estadoCelda = EstadoHabitacion.FUERA_DE_SERVICIO;
				return false;
			}
		}
		
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

