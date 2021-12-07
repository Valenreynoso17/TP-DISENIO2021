package main.java.dtos;

import java.time.LocalDateTime;

public class FueraDeServicioDTO{
	private Integer id;
	private Integer idHabitacion;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	
	public FueraDeServicioDTO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public Integer getIdHabitacion() {
		return idHabitacion;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIdHabitacion(Integer idHabitacion) {
		this.idHabitacion = idHabitacion;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

}
