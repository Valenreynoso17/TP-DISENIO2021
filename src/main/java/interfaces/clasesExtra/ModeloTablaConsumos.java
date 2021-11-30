package main.java.interfaces.clasesExtra;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class ModeloTablaConsumos extends DefaultTableModel{
	
	public ModeloTablaConsumos() {
		this.addColumn("Consumos"); 
		this.addColumn("");
		this.addColumn("Cantidad"); 
		this.addColumn("");
		this.addColumn("Precio unitario");
		this.addColumn("Total neto"); 
	}
	
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
