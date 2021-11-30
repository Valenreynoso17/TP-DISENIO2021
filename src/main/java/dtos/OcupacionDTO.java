package main.java.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
	private PasajeroDTO responsableDTO;
	
	public OcupacionDTO(Ocupacion ocupacion, List<PasajeroDTO> listaPasajerosDTO, List<ConsumoDTO> listaConsumosDTO, List<ItemOcupacionDTO> listaItemOcupacionDTO) {
		this.id = ocupacion.getId();
		this.fechaEgreso = ocupacion.getEgreso();
		this.fechaIngreso = ocupacion.getIngreso();
		this.fechaHoraSalidaReal = ocupacion.getHoraYFechaSalidaReal();
		this.precioPorDia = ocupacion.getPrecioPorDia();
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

	public PasajeroDTO getResponsableDTO() {
		return responsableDTO;
	}
	
	
}
