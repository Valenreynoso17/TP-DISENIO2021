package main.java.interfaces.clasesExtra;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.table.DefaultTableModel;
import main.java.dtos.FueraDeServicioDTO;
import main.java.dtos.HabitacionDTO;
import main.java.dtos.OcupacionDTO;
import main.java.dtos.ReservaDTO;
import main.java.dtos.TipoHabitacionDTO;
import main.java.enums.EstadoHabitacion;

public class ModeloTablaEstadoHabitaciones extends DefaultTableModel{
	
	private static final long serialVersionUID = 1L;

	private List<HabitacionDTO> habitaciones;

	private Map<Integer, List<ReservaDTO>> mapReservasPorHabitacion;	//Guarda una lista de reservas por cada habitacion
	private Map<Integer, OcupacionDTO> mapOcupacionActualPorHabitacion;		//Guarda la ocupacion actual (fechaHoraSalidaReal == null) por habitacion
	
	private LocalDate fechaDesde;
	private LocalDate fechaHasta;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public ModeloTablaEstadoHabitaciones(Map<TipoHabitacionDTO, List<HabitacionDTO>> mapHabitacionesTipo) {
		
		this.habitaciones = new ArrayList<HabitacionDTO>();
		
		this.addColumn("Fecha");
		
		for(TipoHabitacionDTO tipo : mapHabitacionesTipo.keySet()) {
			
			for(HabitacionDTO h : mapHabitacionesTipo.get(tipo)) {

				this.addColumn(h.getNroHabitacion());
				habitaciones.add(h);
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
		
		Map<LocalDate, Map<Integer, EstadoHabitacion>> mapFechaHabitacionEstado = cargarMatriz(mapEstadoHabitaciones);
		
		//for(LocalDate fecha : mapFechaHabitacionEstado.keySet()) {
		for (LocalDate fecha = fechaDesde; fecha.isBefore(fechaHasta.plusDays(1)); fecha = fecha.plusDays(1)) {
			
			Object[] fila = new Object[habitaciones.size()+1];
			
			fila[0] = fecha.format(formatter);
			
			if(mapFechaHabitacionEstado.get(fecha) != null) {
				
				for(HabitacionDTO h : habitaciones) {
					
					if(mapFechaHabitacionEstado.get(fecha).containsKey(h.getId())) {
						
						fila[habitaciones.indexOf(h)+1] = mapFechaHabitacionEstado.get(fecha).get(h.getId());
					}
					else {
						
						fila[habitaciones.indexOf(h)+1] = EstadoHabitacion.LIBRE;
					}
				}
			}
			else {
				//Podria cargar la fila completa con "EstadoHabitacion.LIBRE" o podria dejarlo null y luego verlo en el Render
			}
			
			this.addRow(fila);
		}
	}
	
	public Map<LocalDate, Map<Integer, EstadoHabitacion>> cargarMatriz(Map<String, List<HabitacionDTO>> mapEstadoHabitaciones) {
		
		Map<LocalDate, Map<Integer, EstadoHabitacion>> mapFechaHabitacionEstado = new TreeMap<LocalDate, Map<Integer, EstadoHabitacion>>();	//(f1, f2) -> f1.compareTo(f2)
		
		this.mapReservasPorHabitacion = new TreeMap<Integer, List<ReservaDTO>>();
		this.mapOcupacionActualPorHabitacion = new TreeMap<Integer, OcupacionDTO>();
		
		for(String tipo : mapEstadoHabitaciones.keySet()) {
			
			for(HabitacionDTO h : mapEstadoHabitaciones.get(tipo)) {
					
				List<ReservaDTO> reservas = new ArrayList<ReservaDTO>();
					
				for(ReservaDTO r : h.getReservas()) {
					
					reservas.add(r);
					
				    for (LocalDate fecha = r.getIngreso().toLocalDate(); fecha.isBefore(r.getEgreso().toLocalDate()); fecha = fecha.plusDays(1)) {
				        
				    	if(!mapFechaHabitacionEstado.containsKey(fecha)) {
				    		
					    	Map<Integer, EstadoHabitacion> estadoHabitacion = new HashMap<Integer, EstadoHabitacion>();
					    	//estadoHabitacion.put(h.getId(), f);
					    	mapFechaHabitacionEstado.put(fecha, estadoHabitacion);
				    	}
				    	
				    	mapFechaHabitacionEstado.get(fecha).put(h.getId(), EstadoHabitacion.RESERVADA);
				    }
				}
				
				mapReservasPorHabitacion.put(h.getId(), reservas);
				
				for(OcupacionDTO o : h.getOcupaciones()) {
					
					if(o.getFechaHoraSalidaReal() == null)	//TODO: VER
						mapOcupacionActualPorHabitacion.put(h.getId(), o);
					
				    for (LocalDate fecha = o.getFechaIngreso(); fecha.isBefore(o.getFechaEgreso()); fecha = fecha.plusDays(1)) {
				        
				    	if(!mapFechaHabitacionEstado.containsKey(fecha)) {
				    		
					    	Map<Integer, EstadoHabitacion> estadoHabitacion = new HashMap<Integer, EstadoHabitacion>();
					    	//estadoHabitacion.put(h.getId(), f);
					    	mapFechaHabitacionEstado.put(fecha, estadoHabitacion);
				    	}
				    	
				    	mapFechaHabitacionEstado.get(fecha).put(h.getId(), EstadoHabitacion.OCUPADA);
				    }
				}
				
				for(FueraDeServicioDTO f : h.getFuerasDeServicio()) {
					
				    for (LocalDate fecha = f.getFechaInicio().toLocalDate(); fecha.isBefore(f.getFechaFin().toLocalDate()); fecha = fecha.plusDays(1)) {
				        
				    	if(!mapFechaHabitacionEstado.containsKey(fecha)) {
				    		
					    	Map<Integer, EstadoHabitacion> estadoHabitacion = new HashMap<Integer, EstadoHabitacion>();
					    	//estadoHabitacion.put(h.getId(), f);
					    	mapFechaHabitacionEstado.put(fecha, estadoHabitacion);
				    	}
				    	
				    	mapFechaHabitacionEstado.get(fecha).put(h.getId(), EstadoHabitacion.FUERA_DE_SERVICIO);
				    	
				    }
				}
			}

		}
		
		return mapFechaHabitacionEstado;
	}

	public void actualizarTabla(LocalDate fechaDesde, LocalDate fechaHasta, Map<String, List<HabitacionDTO>> estadoHabitaciones) {
		
		this.limpiarTabla();
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.cargarEstados(estadoHabitaciones);
	}

	public List<HabitacionDTO> getHabitaciones() {

		return this.habitaciones;
	}

	public LocalDate getFechaDesde() {

		return this.fechaDesde;
	}

	public Map<Integer, List<ReservaDTO>> getMapReservasPorHabitacion(){
		return this.mapReservasPorHabitacion;
	}
}
