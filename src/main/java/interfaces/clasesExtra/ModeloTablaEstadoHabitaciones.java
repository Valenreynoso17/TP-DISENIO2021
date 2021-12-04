package main.java.interfaces.clasesExtra;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import main.java.dtos.FueraDeServicioDTO;
import main.java.dtos.HabitacionDTO;
import main.java.dtos.OcupacionDTO;
import main.java.dtos.ReservaDTO;
import main.java.dtos.TipoHabitacionDTO;

public class ModeloTablaEstadoHabitaciones extends DefaultTableModel{
	
	private static final long serialVersionUID = 1L;
	
	public ModeloTablaEstadoHabitaciones(Map<TipoHabitacionDTO, List<HabitacionDTO>> mapHabitacionesTipo) {
		
		this.addColumn("Fecha");
		
		for(TipoHabitacionDTO tipo : mapHabitacionesTipo.keySet()) {
			
			for(HabitacionDTO h : mapHabitacionesTipo.get(tipo)) {
				
				this.addColumn(h.getNroHabitacion());	//Creo que funciona
			}
		}
	}
	
	   @SuppressWarnings({ "unchecked", "rawtypes" })
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
	
	public void cargarEstados(Map<String, List<HabitacionDTO>> mapEstadoHabitaciones) {	//TODO: Ver
		
		//Map<LocalDate, Object>
		List<Object[]> matrizTabla = new ArrayList<Object[]>();
		
		cargarMatriz(matrizTabla, mapEstadoHabitaciones);
		
		
	}
	
	public void cargarMatriz(List<Object[]> matrizTabla, Map<String, List<HabitacionDTO>> mapEstadoHabitaciones) {
		
//		for(String tipo : mapEstadoHabitaciones.keySet()) {
//			
//			for(HabitacionDTO h : mapEstadoHabitaciones.get(tipo)) {
//					
//				for(FueraDeServicioDTO f : h.getFuerasDeServicio()) {
//					
//					
//				}
//				
//				for(ReservaDTO h : mapEstadoHabitaciones.get(tipo)) {
//					
//					
//				}
//				
//				for(OcupacionDTO h : mapEstadoHabitaciones.get(tipo)) {
//					
//					
//				}
//			}
//
//		}
	}

	public void actualizarTabla(Map<String, List<HabitacionDTO>> estadoHabitaciones) {
		
		this.limpiarTabla();
		this.cargarEstados(estadoHabitaciones);
	}

}
