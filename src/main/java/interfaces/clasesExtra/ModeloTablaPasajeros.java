package main.java.interfaces.clasesExtra;

import java.time.LocalTime;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import main.java.clases.Pasajero;
import main.java.enmus.TipoDocumento;

public class ModeloTablaPasajeros extends DefaultTableModel{

	public ModeloTablaPasajeros() {
		this.addColumn("Apellido"); 
		this.addColumn("Nombre"); 
		this.addColumn("Tipo de documento");
		this.addColumn("Número de documento"); 
	}
	
	   public Class getColumnClass(int columna)
	   {
	      if (columna == 0) return String.class;
	      if (columna == 1) return String.class;
	      if (columna == 2) return TipoDocumento.class;
	      if (columna == 3) return String.class;
	      return Object.class;
	   }

	public void limpiarTabla() {

		this.setRowCount(0); //Elimino todas las filas de la tabla
	}
	
	public void cargarPasajeros(List<Pasajero> pasajeros) {
		
//		for(Pasajero p : pasajeros) {
//			this.addRow(new Object[] {p.getApellido()
//									, p.getNombre()
//									, p.getTipoDocumento()
//									, p.getNumeroDocumento()});
//		}

	}
	
}
