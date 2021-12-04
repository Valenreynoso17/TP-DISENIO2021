package main.java.interfaces.clasesExtra;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import main.java.dtos.PasajeroDTO;
import main.java.enums.TipoDocumento;

public class ModeloTablaFacturar extends DefaultTableModel{
	
	private static final long serialVersionUID = 1L;
	
	public List<PasajeroDTO> pasajerosHabitacion;

	public ModeloTablaFacturar() {
		this.addColumn("Apellido"); 
		this.addColumn("Nombre"); 
		this.addColumn("Tipo de documento");
		this.addColumn("Número de documento"); 
		this.addColumn("Fecha de nacimiento"); 
		pasajerosHabitacion = new ArrayList<PasajeroDTO>();
	}
	
	   @SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int columna)
	   {
	      if (columna == 0) return String.class;
	      if (columna == 1) return String.class;
	      if (columna == 2) return TipoDocumento.class;
	      if (columna == 3) return String.class;
	      if (columna == 4) return LocalDate.class;
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
	
	public void cargarPasajeros(List<PasajeroDTO> pasajeros) {
		
		this.pasajerosHabitacion.addAll(pasajeros);
		
		System.out.println(this.pasajerosHabitacion);
		
		for(PasajeroDTO p : pasajeros) {
			this.addRow(new Object[] {p.getApellido()
									, p.getNombre()
									, p.getTipoDocumento()
									, p.getNumeroDoc()
									, p.getFechaNacimiento()});
		}

	}
	
	public List<PasajeroDTO> getPasajerosHabitacion(){
		return  this.pasajerosHabitacion;
	}

	public void actualizarTabla(List<PasajeroDTO> listaPasajerosDTO) {
		
		this.limpiarTabla();
		this.cargarPasajeros(listaPasajerosDTO);
	}

}
