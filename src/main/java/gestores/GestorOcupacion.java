package main.java.gestores;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import main.java.clases.Consumo;
import main.java.clases.Habitacion;
import main.java.clases.ItemConsumo;
import main.java.clases.ItemOcupacion;
import main.java.clases.Ocupacion;
import main.java.clases.Pasajero;
import main.java.clases.ResponsableDePago;
import main.java.daos.OcupacionDAO;
import main.java.dtos.ConsumoDTO;
import main.java.dtos.ItemConsumoDTO;
import main.java.dtos.ItemFilaDTO;
import main.java.dtos.ItemOcupacionDTO;
import main.java.dtos.OcupacionDTO;
import main.java.dtos.PasajeroDTO;
import main.java.dtos.ResponsableDePagoDTO;
import main.java.postgreImpl.OcupacionPostgreSQLImpl;

public class GestorOcupacion {
	private static GestorOcupacion instance;
		
	private OcupacionDAO ocupacionDAO;
	
	private GestorHabitacion gestorHabitacion;
	private GestorPasajero gestorPasajero;
	
	private GestorOcupacion() {
		ocupacionDAO = new OcupacionPostgreSQLImpl();
		gestorHabitacion = GestorHabitacion.getInstance();
		gestorPasajero = GestorPasajero.getInstance();
	}
	
	public static GestorOcupacion getInstance() {

		if (instance == null) instance = new GestorOcupacion();
		
		return instance;
	}

	public OcupacionDTO buscarUltimaOcupacionDTO(Integer nroHabitacion, LocalTime horaSalida) {
		
		// Validar datos
		
		Ocupacion ocupacion = ocupacionDAO.buscarUltimaOcupacion(nroHabitacion);
		
		OcupacionDTO ocupacionDTO = crearOcupacionDTO(ocupacion);
		
		return ocupacionDTO;
		
	}
	
	public OcupacionDTO crearOcupacionDTO(Ocupacion ocupacion) {
		List<PasajeroDTO> listaPasajerosDTO = crearListaPasajerosDTO(ocupacion);
		
		List<ConsumoDTO> listaConsumosDTO = crearListaConsumosDTO(ocupacion.getConsumos());
		
		List<ItemOcupacionDTO> listaItemOcupacionDTO = crearListaItemsOcupacion(ocupacion);
		
		PasajeroDTO responsable = new PasajeroDTO(ocupacion.getResponsable());
		
		OcupacionDTO ocupacionDTO = new OcupacionDTO(ocupacion, listaPasajerosDTO, listaConsumosDTO, listaItemOcupacionDTO, responsable);
		
		return ocupacionDTO;
	}
	
	public List<PasajeroDTO> crearListaPasajerosDTO(Ocupacion ocupacion){
		List<PasajeroDTO> retorno = new ArrayList<PasajeroDTO>();
		
		for(Pasajero pasajeroAux : ocupacion.getPasajeros()) {
			retorno.add(new PasajeroDTO(pasajeroAux));
		}
		
		return retorno;
	}
	
	public List<ConsumoDTO> crearListaConsumosDTO(List<Consumo> listaConsumos){
		List<ConsumoDTO> retorno = new ArrayList<ConsumoDTO>();
		
		for(Consumo unConsumo : listaConsumos) {
			
			List<ItemConsumoDTO> listaItemsAux = new ArrayList<ItemConsumoDTO>();
			
			for(ItemConsumo unItem : unConsumo.getItems()) {
				listaItemsAux.add(new ItemConsumoDTO(unItem));
			}
			
			retorno.add(new ConsumoDTO(listaItemsAux, unConsumo));
		}
		
		return retorno;
	}
	
	public List<ItemOcupacionDTO> crearListaItemsOcupacion(Ocupacion ocupacion){
		List<ItemOcupacionDTO> retorno = new ArrayList<ItemOcupacionDTO>();
		
		for(ItemOcupacion unItemOcupacion : ocupacion.getItemsOcupacion()) {
			retorno.add(new ItemOcupacionDTO(unItemOcupacion));
		}
		
		return retorno;
	}
	
	public List<ItemFilaDTO> calcularCosto(OcupacionDTO ocupacionDTO, ResponsableDePagoDTO responsableDTO) {
		
		List<ItemFilaDTO> listaItemsFila = new ArrayList<ItemFilaDTO>();
		
		calcularValorEstadia(listaItemsFila, ocupacionDTO);
		
		calcularValorConsumos(listaItemsFila, ocupacionDTO);
		
		return listaItemsFila;
	}
	
	private void calcularValorEstadia(List<ItemFilaDTO> listaItemsFila, OcupacionDTO ocupacionDTO) {
		Integer cantDiasOcupacion = Period.between(ocupacionDTO.getFechaIngreso(), ocupacionDTO.getFechaEgreso()).getDays();
		Integer cantDiasFacturados = 0;
		
		for(ItemOcupacionDTO unItem : ocupacionDTO.getListaItemsOcupacionDTO()) {
			cantDiasFacturados += unItem.getCantidadDias();
		}
		
		if (cantDiasFacturados < cantDiasOcupacion) {
			listaItemsFila.add(new ItemFilaDTO("VALOR DE LA ESTADIA", ocupacionDTO.getPrecioPorDia(), cantDiasOcupacion - cantDiasFacturados, true));
		}
	}
	
	private void calcularValorConsumos(List<ItemFilaDTO> listaItemsFila, OcupacionDTO ocupacionDTO) {
		for(ConsumoDTO unConsumo : ocupacionDTO.getListaConsumosDTO()) {
			
			Integer cantidadFacturada = 0;
			
			for(ItemConsumoDTO unItem : unConsumo.getListaItems()) {
				cantidadFacturada += unItem.getCantidad();
			}
			
			if(cantidadFacturada < unConsumo.getCantidadTotal()) {
				listaItemsFila.add(new ItemFilaDTO(unConsumo.getDescripcion(), ocupacionDTO.getPrecioPorDia(), unConsumo.getCantidadTotal() - cantidadFacturada, false));
			}
		}
	}
	
	/*
		Crea una ocupacion a partir de un DTO y la guarda
		Para que funcione el metodo el DTO debe tener: idHabitacion, ingreso, egreso, listaPasajeros, Responsable
		La hora de salida se setea en null
	*/
	public void guardarOcupacion(OcupacionDTO o) {
		Habitacion habitacion = gestorHabitacion.buscarHabitacion(o.getIdHabitacion());
		
		List<Pasajero> pasajeros = gestorPasajero.buscarPasajeros(o.getListaPasajerosDTO());
		Set<Pasajero> setPasajeros = new HashSet<>();
		
		Pasajero responsable = null;
		
		for (Pasajero p : pasajeros) {
			setPasajeros.add(p);
			
			if (p.getId().equals(o.getIdHabitacion())) responsable = p;
		}
		
		Ocupacion ocupacion = new Ocupacion(null, 
											o.getFechaIngreso(), 
											o.getFechaEgreso(),  
											habitacion.getTipo().getCostoPorNoche(), 
											habitacion, 
											setPasajeros, 
											responsable);
		
		ocupacionDAO.guardar(ocupacion);		
	}
	
	public List<OcupacionDTO> buscarOcupaciones(LocalDate fechaDesde, LocalDate fechaHasta) {
		List<Ocupacion> ocupaciones = ocupacionDAO.buscar(fechaDesde, fechaHasta);
		List<OcupacionDTO> ocupacionesDTO = new ArrayList<>();
		
		for (Ocupacion o : ocupaciones) {
			OcupacionDTO dto = new OcupacionDTO();
			dto.setId(o.getId());
			dto.setFechaIngreso(o.getIngreso());
			dto.setFechaEgreso(o.getEgreso());
			dto.setIdHabitacion(o.getHabitacion().getId());
			
			ocupacionesDTO.add(dto);
		}
		
		return ocupacionesDTO;
	}
}
