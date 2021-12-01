package main.java.dtos;

import java.time.LocalDateTime;

public class ReservaDTO {
	private Integer id;
	private LocalDateTime ingreso;
	private LocalDateTime egreso;
	
	public ReservaDTO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public LocalDateTime getIngreso() {
		return ingreso;
	}

	public LocalDateTime getEgreso() {
		return egreso;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIngreso(LocalDateTime ingreso) {
		this.ingreso = ingreso;
	}

	public void setEgreso(LocalDateTime egreso) {
		this.egreso = egreso;
	}
	
	
}
