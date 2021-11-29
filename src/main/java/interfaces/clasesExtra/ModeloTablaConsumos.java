package main.java.interfaces.clasesExtra;

import java.time.LocalDate;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import main.java.dtos.PasajeroDTO;
import main.java.enums.TipoDocumento;

public class ModeloTablaConsumos extends DefaultTableModel{
	
	public ModeloTablaConsumos() {
		this.addColumn("Apellido"); 
		this.addColumn("Nombre"); 
		this.addColumn("Tipo de documento");
		this.addColumn("Número de documento"); 
		this.addColumn("Fecha de nacimiento"); 
	}
	
	   public Class getColumnClass(int columna)
	   {
	      if (columna == 0) return String.class;
	      if (columna == 1) return Integer.class;
	      if (columna == 2) return double.class;
	      if (columna == 3) return double.class;
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
	
//	public void cargarConsumos(List<PasajeroDTO> pasajeros) {	//TODO: Ver
//		
//		for(PasajeroDTO p : pasajeros) {
//			this.addRow(new Object[] {p.getApellido()
//									, p.getNombre()
//									, p.getTipoDocumento()
//									, p.getNumeroDoc()
//									, p.getFechaNacimiento()});
//		}
//
//	}

}
