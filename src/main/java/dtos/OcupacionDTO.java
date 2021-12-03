package main.java.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import main.java.clases.Ocupacion;

public class OcupacionDTO {
	private Integer id;
	private LocalDate fechaEgreso;
	private LocalDate fechaIngreso;
	private LocalDateTime fechaHoraSalidaReal;
	private Double precioPorDia;
	private List<ConsumoDTO> listaConsumosDTO;
	private List<ItemOcupacionDTO> listaItemsOcupacionDTO;
	private List<PasajeroDTO> listaPasajerosDTO;	
	private PasajeroDTO responsable;
	private Integer idHabitacion;
	private LocalDateTime posibleFechaHoraDeSalida;
	
	public OcupacionDTO() {
		super();
	}
	
	public OcupacionDTO(Integer idHabitacion, LocalDate ingreso, LocalDate egreso, List<PasajeroDTO> listaPasajeros, PasajeroDTO responsable) {
		this.idHabitacion = idHabitacion;
		this.fechaIngreso = ingreso;
		this.fechaEgreso = egreso;
		this.listaPasajerosDTO = listaPasajeros;
		this.responsable = responsable;
	}
	
	public OcupacionDTO(Ocupacion ocupacion, List<PasajeroDTO> listaPasajerosDTO, List<ConsumoDTO> listaConsumosDTO,
						List<ItemOcupacionDTO> listaItemOcupacionDTO, PasajeroDTO responsable, LocalDateTime posibleFechaHoraDeSalida) {
		this.id = ocupacion.getId();
		this.fechaEgreso = ocupacion.getEgreso();
		this.fechaIngreso = ocupacion.getIngreso();
		this.fechaHoraSalidaReal = ocupacion.getHoraYFechaSalidaReal();
		this.precioPorDia = ocupacion.getPrecioPorDia();
		this.idHabitacion = ocupacion.getHabitacion().getId();
		this.posibleFechaHoraDeSalida = posibleFechaHoraDeSalida;
		this.responsable = responsable;
		this.listaPasajerosDTO = listaPasajerosDTO;
		this.listaConsumosDTO = listaConsumosDTO;
		this.listaItemsOcupacionDTO = listaItemOcupacionDTO;
	}

	public Integer getId() {
		return id;
	}

	public LocalDate getFechaEgreso() {
		return fechaEgreso;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public LocalDateTime getFechaHoraSalidaReal() {
		return fechaHoraSalidaReal;
	}

	public Double getPrecioPorDia() {
		return precioPorDia;
	}

	public List<ConsumoDTO> getListaConsumosDTO() {
		return listaConsumosDTO;
	}

	public List<ItemOcupacionDTO> getListaItemsOcupacionDTO() {
		return listaItemsOcupacionDTO;
	}

	public List<PasajeroDTO> getListaPasajerosDTO() {
		return listaPasajerosDTO;
	}

	public PasajeroDTO getResponsable() {
		return responsable;
	}
	
	public Integer getIdHabitacion() {
		return idHabitacion;
	}
	
	public LocalDateTime getPosibleFechaHoraDeSalida() {
		return posibleFechaHoraDeSalida;
	}
	
	

	public void setId(Integer id) {
		this.id = id;
	}

	public void setFechaEgreso(LocalDate fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public void setFechaHoraSalidaReal(LocalDateTime fechaHoraSalidaReal) {
		this.fechaHoraSalidaReal = fechaHoraSalidaReal;
	}

	public void setPrecioPorDia(Double precioPorDia) {
		this.precioPorDia = precioPorDia;
	}

	public void setListaConsumosDTO(List<ConsumoDTO> listaConsumosDTO) {
		this.listaConsumosDTO = listaConsumosDTO;
	}

	public void setListaItemsOcupacionDTO(List<ItemOcupacionDTO> listaItemsOcupacionDTO) {
		this.listaItemsOcupacionDTO = listaItemsOcupacionDTO;
	}

	public void setListaPasajerosDTO(List<PasajeroDTO> listaPasajerosDTO) {
		this.listaPasajerosDTO = listaPasajerosDTO;
	}

	public void setResponsable(PasajeroDTO responsable) {
		this.responsable = responsable;
	}

	public void setIdHabitacion(Integer idHabitacion) {
		this.idHabitacion = idHabitacion;
	}
	
	
}
