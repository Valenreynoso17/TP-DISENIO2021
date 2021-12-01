package main.java.dtos;

import java.util.List;

public class HabitacionDTO {
	private Integer id;
	private Integer nroHabitacion;
	private TipoHabitacionDTO tipo;
	private List<OcupacionDTO> ocupaciones;
	private List<ReservaDTO> reservas;
	private List<FueraDeServicioDTO> fuerasDeServicio;
	
	
	public HabitacionDTO() {
		super();
	}
	
	public HabitacionDTO(Integer id, Integer nroHabitacion, TipoHabitacionDTO tipo, List<OcupacionDTO> ocupaciones, List<ReservaDTO> reservas,
			List<FueraDeServicioDTO> fuerasDeServicio) {
		super();
		this.id = id;
		this.nroHabitacion = nroHabitacion;
		this.tipo = tipo;
		this.ocupaciones = ocupaciones;
		this.reservas = reservas;
		this.fuerasDeServicio = fuerasDeServicio;
	}
	
	

	public Integer getId() {
		return id;
	}

	public Integer getNroHabitacion() {
		return nroHabitacion;
	}

	public TipoHabitacionDTO getTipo() {
		return tipo;
	}

	public List<OcupacionDTO> getOcupaciones() {
		return ocupaciones;
	}

	public List<ReservaDTO> getReservas() {
		return reservas;
	}

	public List<FueraDeServicioDTO> getFuerasDeServicio() {
		return fuerasDeServicio;
	}
	
	

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNroHabitacion(Integer nroHabitacion) {
		this.nroHabitacion = nroHabitacion;
	}

	public void setTipo(TipoHabitacionDTO tipo) {
		this.tipo = tipo;
	}

	public void setOcupaciones(List<OcupacionDTO> ocupaciones) {
		this.ocupaciones = ocupaciones;
	}

	public void setReservas(List<ReservaDTO> reservas) {
		this.reservas = reservas;
	}

	public void setFuerasDeServicio(List<FueraDeServicioDTO> fuerasDeServicio) {
		this.fuerasDeServicio = fuerasDeServicio;
	}
	
	
	
	
}
