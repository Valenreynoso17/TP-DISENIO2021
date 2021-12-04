package main.java.interfaces.clasesExtra;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import main.java.dtos.PasajeroDTO;

public class ModeloPasajerosSeleccionadosOcuparHabitacion extends DefaultTableModel{
	
	private static final long serialVersionUID = 1L;
	
	public List<PasajeroDTO> pasajerosSeleccionados;
	
	private Integer capacidadHabitacion;

	public ModeloPasajerosSeleccionadosOcuparHabitacion(Integer capacidad) {
		this.capacidadHabitacion = capacidad;
		this.addColumn("Apellido"); 
		this.addColumn("Nombre"); 
		pasajerosSeleccionados = new ArrayList<PasajeroDTO>();
	}
	
	   @SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int columna)
	   {
	      return String.class;
	   }
	   
	    @Override
	    public boolean isCellEditable(int row, int column) {
	       //all cells false
	       return false;
	    }

	public void limpiarTabla() {

		this.setRowCount(0); //Elimino todas las filas de la tabla
	}
	
	public void eliminarPasajero(int fila) {
		
		this.removeRow(fila);
		pasajerosSeleccionados.remove(fila);
	}
	
	public void cargarPasajero(PasajeroDTO p) {	
		
		if(this.getRowCount() < capacidadHabitacion) {	
			
			if(!this.pasajerosSeleccionados.contains(p)) {	//Si ya contiene al pasajero, no hay que volver a agregarlo
				
				this.addRow(new Object[] {p.getApellido(), p.getNombre()});
				this.pasajerosSeleccionados.add(p);
			}
		}
	}
	
	public List<PasajeroDTO> getPasajerosSeleccionados(){
		return  this.pasajerosSeleccionados;
	}

}
