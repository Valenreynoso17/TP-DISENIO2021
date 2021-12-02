package main.java.interfaces.clasesExtra;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import main.java.dtos.PasajeroDTO;

public class ModeloPasajerosSeleccionadosOcuparHabitacion extends DefaultTableModel{
	
	private static final long serialVersionUID = 1L;

	public ModeloPasajerosSeleccionadosOcuparHabitacion() {
		this.addColumn("Apellido"); 
		this.addColumn("Nombre"); 
	}
	
	   @SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int columna)
	   {
	      if (columna == 0) return String.class;
	      if (columna == 1) return String.class;
	      return Object.class;
	   }
	   
	    @Override
	    public boolean isCellEditable(int row, int column) {
	       //all cells false
	       return false;
	    }

	public void limpiarTabla() {

		this.setRowCount(0); //Elimino todas las filas de la tabla
	}
	
//	public void cargarPasajeros(List<PasajeroDTO> pasajeros) {	//TODO: Ver
//		
//		for(PasajeroDTO p : pasajeros) {
//			this.addRow(new Object[] {p.getApellido()
//									, p.getNombre()});
//		}
//
//	}
	
	public void cargarPasajeros() {	//TODO: Ver
		
		for(int i = 0; i < 5; i++) {
			this.addRow(new Object[] {"Rodriguez"
									, "Juan"});
		}

	}

}
