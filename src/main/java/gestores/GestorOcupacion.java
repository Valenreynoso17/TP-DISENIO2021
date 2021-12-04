package main.java.gestores;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import main.java.clases.Consumo;
import main.java.clases.Factura;
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
import main.java.enums.PosicionFrenteIva;
import main.java.excepciones.HabitacionInexistenteException;
import main.java.excepciones.OcupacionYaFacturadaException;
import main.java.postgreImpl.OcupacionPostgreSQLImpl;

public class GestorOcupacion {
	private static GestorOcupacion instance;
		
	private OcupacionDAO ocupacionDAO;
	
	private GestorHabitacion gestorHabitacion;
	private GestorPasajero gestorPasajero;
	
	private GestorOcupacion() {
		ocupacionDAO = new OcupacionPostgreSQLImpl();
	}
	
	public static GestorOcupacion getInstance() {

		if (instance == null) instance = new GestorOcupacion();
		
		return instance;
	}

	public OcupacionDTO buscarUltimaOcupacionDTO(Integer nroHabitacion, LocalDateTime horaSalida) throws OcupacionYaFacturadaException, 
																										 HabitacionInexistenteException {
		
		gestorHabitacion = GestorHabitacion.getInstance();
		Optional<Habitacion> optHabitacion = Optional.ofNullable(gestorHabitacion.buscarHabitacionPorNro(nroHabitacion));
		
		if (optHabitacion.isEmpty()) {
			throw new HabitacionInexistenteException();
		}
		
		Optional<Ocupacion> optOcupacion = Optional.ofNullable(ocupacionDAO.buscarUltimaOcupacion(nroHabitacion));
		
		if (optOcupacion.isEmpty()) {
			throw new OcupacionYaFacturadaException();
		}
		
		OcupacionDTO ocupacionDTO = crearOcupacionDTO(optOcupacion.get(), horaSalida);
		
		return ocupacionDTO;
		
	}
	
	public OcupacionDTO crearOcupacionDTO(Ocupacion ocupacion, LocalDateTime horaSalida) {
		List<PasajeroDTO> listaPasajerosDTO = crearListaPasajerosDTO(ocupacion);
		
		List<ConsumoDTO> listaConsumosDTO = crearListaConsumosDTO(ocupacion);
		
		List<ItemOcupacionDTO> listaItemOcupacionDTO = crearListaItemsOcupacion(ocupacion);
		
		PasajeroDTO responsable = listaPasajerosDTO.stream().filter(p -> p.getId() == ocupacion.getResponsable().getId()).findFirst().get();
		
		OcupacionDTO ocupacionDTO = new OcupacionDTO(ocupacion, listaPasajerosDTO, listaConsumosDTO, listaItemOcupacionDTO, responsable, horaSalida);
		
		return ocupacionDTO;
	}
	
	public List<PasajeroDTO> crearListaPasajerosDTO(Ocupacion ocupacion){
		List<PasajeroDTO> retorno = new ArrayList<PasajeroDTO>();
		
		for(Pasajero pasajeroAux : ocupacion.getPasajeros()) {
			retorno.add(new PasajeroDTO(pasajeroAux));
		}
		
		return retorno;
	}
	
	public List<ConsumoDTO> crearListaConsumosDTO(Ocupacion ocupacion){
		List<ConsumoDTO> retorno = new ArrayList<ConsumoDTO>();
		
		for(Consumo unConsumo : ocupacion.getConsumos()) {
			
			List<ItemConsumoDTO> listaItemsAux = new ArrayList<ItemConsumoDTO>();
			
			for(ItemConsumo unItem : unConsumo.getItems()) {
				listaItemsAux.add(new ItemConsumoDTO(unItem));
			}
			
			retorno.add(new ConsumoDTO(unConsumo, listaItemsAux));
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
	
	public List<ItemFilaDTO> calcularItemsAPagar(OcupacionDTO ocupacionDTO, ResponsableDePagoDTO responsableDTO) {
		
		List<ItemFilaDTO> listaItemsFila = new ArrayList<ItemFilaDTO>();
		Double multiplicadorIVA = (responsableDTO.getPosicionFrenteIva() == PosicionFrenteIva.CONSUMIDOR_FINAL) ? 1 : 1 + Factura.getIVA();
		
		calcularValorItemsEstadia(listaItemsFila, ocupacionDTO, multiplicadorIVA);
		
		calcularValorItemsConsumos(listaItemsFila, ocupacionDTO, multiplicadorIVA);
		
		calcularValorItemsRecargo(listaItemsFila, ocupacionDTO, multiplicadorIVA);
		
		return listaItemsFila;
	}
	
	private void calcularValorItemsEstadia(List<ItemFilaDTO> listaItemsFila, OcupacionDTO ocupacionDTO, Double multiplicadorIVA) {
		Integer cantDiasOcupacion = Period.between(ocupacionDTO.getFechaIngreso(), ocupacionDTO.getFechaEgreso()).getDays();
		Integer cantDiasFacturados = 0;
		
		for(ItemOcupacionDTO unItem : ocupacionDTO.getListaItemsOcupacionDTO()) {
			cantDiasFacturados += unItem.getCantidadDias();
		}
		
		if (cantDiasFacturados < cantDiasOcupacion) {
			listaItemsFila.add(new ItemFilaDTO(ocupacionDTO.getId(), "VALOR DE LA ESTADIA", ocupacionDTO.getPrecioPorDia() * multiplicadorIVA, cantDiasOcupacion - cantDiasFacturados, true, false));
		}
	}
	
	private void calcularValorItemsConsumos(List<ItemFilaDTO> listaItemsFila, OcupacionDTO ocupacionDTO, Double multiplicadorIVA) {
		for(ConsumoDTO unConsumo : ocupacionDTO.getListaConsumosDTO()) {
			Integer cantidadTotal = unConsumo.getCantidadTotal();
			Integer cantidadFacturada = 0;
			
			for(ItemConsumoDTO unItem : unConsumo.getListaItems()) {
				cantidadFacturada += unItem.getCantidad();
			}
			
			if(cantidadFacturada < cantidadTotal) {
				listaItemsFila.add(new ItemFilaDTO(unConsumo.getId(), unConsumo.getDescripcion(), ocupacionDTO.getPrecioPorDia() * multiplicadorIVA, cantidadTotal - cantidadFacturada, false, false));
			}
		}
	}
	
	private void calcularValorItemsRecargo(List<ItemFilaDTO> listaItemsFila, OcupacionDTO ocupacionDTO, Double multiplicadorIVA) {
		
		LocalTime hora11 = LocalTime.of(11, 0);
		LocalTime hora18 = LocalTime.of(18, 0);
		LocalDateTime fechaMaximaEgreso = LocalDateTime.of(ocupacionDTO.getFechaEgreso(), hora18);
		
		// La fechaMaxima sera la fecha del egreso a las 18 horas
		if(ocupacionDTO.getPosibleFechaHoraDeSalida().isBefore(fechaMaximaEgreso)) {
			
			listaItemsFila.add(new ItemFilaDTO(ocupacionDTO.getId(), "RECARGO CHECKOUT TARDÍO", ocupacionDTO.getPrecioPorDia() * multiplicadorIVA, 1, true, true));
			
		} else {
			
			// Fecha actual = fecha egreso y hora > 11:
			if(ocupacionDTO.getFechaEgreso().isEqual(ocupacionDTO.getPosibleFechaHoraDeSalida().toLocalDate()) && ocupacionDTO.getPosibleFechaHoraDeSalida().toLocalTime().isBefore(hora11)) {
				// crear un itemFila que tenga la mitad del precio de la habitacion por dia
				listaItemsFila.add(new ItemFilaDTO(ocupacionDTO.getId(), "RECARGO 50%", ocupacionDTO.getPrecioPorDia() / 2 * multiplicadorIVA, 1, true, true));
			}
		}
		
	}
	
	/*
		Crea una ocupacion a partir de un DTO y la guarda
		Para que funcione el metodo el DTO debe tener: idHabitacion, ingreso, egreso, listaPasajeros, Responsable
		La hora de salida se setea en null
	*/
	public void guardarOcupacion(OcupacionDTO o) {
		gestorHabitacion = GestorHabitacion.getInstance();
		Habitacion habitacion = gestorHabitacion.buscarHabitacion(o.getIdHabitacion());
		
		gestorPasajero = GestorPasajero.getInstance();
		List<Pasajero> pasajeros = gestorPasajero.buscarPasajeros(o.getListaPasajerosDTO());
		Set<Pasajero> setPasajeros = new HashSet<>();
		
		Pasajero responsable = null;
		
		for (Pasajero p : pasajeros) {
			setPasajeros.add(p);
			
			if (p.getId().equals(o.getResponsable().getId())) responsable = p;
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
