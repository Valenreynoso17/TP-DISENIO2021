package main.java.dtos;

import java.time.LocalDateTime;

public class FueraDeServicioDTO {
	private Integer id;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	
	public FueraDeServicioDTO() {
		super();
	}

	public Integer getId() {
		return id;
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

	public void setFechaInicio(LocalDateTime ingreso) {
		this.fechaInicio = ingreso;
	}

	public void setFechaFin(LocalDateTime egreso) {
		this.fechaFin = egreso;
	}
}
