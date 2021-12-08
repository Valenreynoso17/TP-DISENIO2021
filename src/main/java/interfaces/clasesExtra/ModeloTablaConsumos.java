package main.java.interfaces.clasesExtra;

import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import main.java.dtos.ItemFilaDTO;

public class ModeloTablaConsumos extends DefaultTableModel{
	
	private static final long serialVersionUID = 1L;
	
	private List<ItemFilaDTO> items;
	
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	private Integer cantidadFilasEnBlanco = 10;
	
	private double total = 0.0;
	
	public ModeloTablaConsumos(List<ItemFilaDTO> itemsDTO) {
		this.items = itemsDTO;
		this.addColumn("Consumos"); 
		this.addColumn("");
		this.addColumn("Cantidad"); 
		this.addColumn("");
		this.addColumn("Precio unitario");
		this.addColumn("Total neto"); 
	}
	
	   @SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int columna)
	   {
	      if (columna == 0) return String.class;
	      if (columna == 1) return JButton.class;
	      if (columna == 2) return Integer.class;
	      if (columna == 3) return JButton.class;
	      if (columna == 4) return double.class;
	      if (columna == 5) return double.class;
	      return Object.class;
	   }
	   
	    @Override
	    public boolean isCellEditable(int row, int column) {
	    	if(column == 1 || column == 3) {
	    		return true;
	    	}
	       //All other cells false
	       return false;
	    }

	public void limpiarTabla() {

		this.setRowCount(0); //Elimino todas las filas de la tabla
		if(this.items != null)
			this.items.clear();
	}
	
	public void cargarConsumos() {
		
		for(ItemFilaDTO i : items) {
			this.addRow(new Object[] {i.getDescripcion()
									, ""				//Boton -
									, i.getCantidadSeleccionada() + "/" + i.getCantidadMax()
									, ""				//Boton +
									, "$ "+df.format(i.getPrecioUnitario())
									, "$ 0.00"});		//Se cargan todas con cantidadSeleccionada = 0
		}
		
		cantidadFilasEnBlanco = (items.size() > 10)? 0 : 10 - items.size();	//Mas de 10 items -> filasEnBlanco = 0 // Menos de 10 items -> filasEnBlanco = 10 - cantItems
		
		for(int i = 0; i < cantidadFilasEnBlanco; i++)
			this.addRow(new Object[]{null,null,null,null,null,null});	//Fila en blanco
		
		this.addRow(new Object[] {"TOTAL",null,"",null,"", "$ 0.00"});

	}

	public double actualizarFila(Character c, int fila) {
		
		ItemFilaDTO itemCambioCantidad = items.get(fila);
		
		//Para calcular el "TOTAL" que se muestra en la ultima fila
		double total = 0.0;
		
		//Si la cantidadSeleccionada es distinta de la capacidad maxima (todavia no se llego a seleccionar todas las unidades del item)
		if(c == '+' && itemCambioCantidad.getCantidadSeleccionada() != itemCambioCantidad.getCantidadMax()) {
				
			//Primero le sumo 1 en la cantidad seleccionada, luego abajo simplemente llamo el metodo "get"
			itemCambioCantidad.setCantidadSeleccionada(itemCambioCantidad.getCantidadSeleccionada()+1);
			
			//Aumenta la Cantidad en 1, con respecto a la que tenia anteriormente
			this.setValueAt(itemCambioCantidad.getCantidadSeleccionada()+"/"+itemCambioCantidad.getCantidadMax(), fila, 2);	
			
			//Actualiza el campo "TotalNeto" en funcion de la cantidad y del precio unitario
			this.setValueAt("$ "+df.format(itemCambioCantidad.getCantidadSeleccionada()*itemCambioCantidad.getPrecioUnitario()), fila, 5);
			
			for(ItemFilaDTO i : items) {
				
				total += i.getCantidadSeleccionada() * i.getPrecioUnitario();
			}
			
			//Actualiza el campo "TOTAL" en funcion de la cantidad y del precio unitario de todas las filas
			this.setValueAt("$ "+df.format(total), this.cantidadFilasEnBlanco+items.size(), 5);	
			
			this.total = total;
		}
		//Si la cantidadSeleccionada es distinta de 0
		else if(c == '-' && itemCambioCantidad.getCantidadSeleccionada() != 0) {
			
			//Primero le resto 1 en la cantidad seleccionada, luego abajo simplemente llamo el metodo "get"
			itemCambioCantidad.setCantidadSeleccionada(itemCambioCantidad.getCantidadSeleccionada()-1);
			
			//Aumenta la Cantidad en 1, con respecto a la que tenia anteriormente
			this.setValueAt(itemCambioCantidad.getCantidadSeleccionada()+"/"+itemCambioCantidad.getCantidadMax(), fila, 2);	
			
			//Actualiza el campo "TotalNeto" en funcion de la cantidad y del precio unitario
			this.setValueAt("$ "+df.format(itemCambioCantidad.getCantidadSeleccionada()*itemCambioCantidad.getPrecioUnitario()), fila, 5);
			
			for(ItemFilaDTO i : items) {
				
				total += i.getCantidadSeleccionada() * i.getPrecioUnitario();
			}
			
			//Actualiza el campo "TOTAL" en funcion de la cantidad y del precio unitario de todas las filas
			this.setValueAt("$ "+df.format(total), this.cantidadFilasEnBlanco+items.size(), 5);
			
			this.total = total;
		}
		
		return this.total;
	}

}
