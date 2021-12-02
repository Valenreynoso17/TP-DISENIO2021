package main.java.interfaces.clasesExtra;

import java.time.LocalDate;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import main.java.dtos.PasajeroDTO;
import main.java.enums.TipoDocumento;

public class ModeloTablaEstadoHabitaciones extends DefaultTableModel{
	
	public ModeloTablaEstadoHabitaciones() {	//TODO: Reemplazar
		this.addColumn("Fecha"); 
		this.addColumn("101"); 
		this.addColumn("102");
		this.addColumn("103"); 
		this.addColumn("..."); 
		this.addColumn("201"); 
		this.addColumn("202");
		this.addColumn("203"); 
		this.addColumn("..."); 
		this.addColumn("301"); 
		this.addColumn("302");
		this.addColumn("303"); 
		this.addColumn("..."); 
		this.addColumn("401"); 
		this.addColumn("402");
		this.addColumn("403"); 
		this.addColumn("..."); 
		this.addColumn("501"); 
		this.addColumn("502");
		this.addColumn("503"); 
		this.addColumn("..."); 
	}
	
	   public Class getColumnClass(int columna)
	   {
		  return String.class;
	   }
	   
	    @Override
	    public boolean isCellEditable(int row, int column) {
	    	
	       return false;
	    }

	public void limpiarTabla() {

		this.setRowCount(0); //Elimino todas las filas de la tabla
	}
	
	public void cargarEstados() {	//TODO: Ver
		
		for(int i = 0; i < 10; i++) {
			this.addRow(new Object[] {"","","","","","","","","","","","","","","","","","","",""});
		}

	}

}
