package main.java.dtos;

import java.time.LocalDateTime;

public class ReservaDTO{
	private Integer id;
	private Integer idHabitacion;
	private LocalDateTime ingreso;
	private LocalDateTime egreso;
	private String nombre;
	private String apellido;
	
	public ReservaDTO(Integer id, Integer idHabitacion, LocalDateTime ingreso, LocalDateTime egreso, String nombre, String apellido) {
		super();
		this.id = id;
		this.idHabitacion = idHabitacion;
		this.ingreso = ingreso;
		this.egreso = egreso;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public Integer getId() {
		return id;
	}

	public Integer getIdHabitacion() {
		return idHabitacion;
	}

	public LocalDateTime getIngreso() {
		return ingreso;
	}

	public LocalDateTime getEgreso() {
		return egreso;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellido() {
		return apellido;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIdHabitacion(Integer idHabitacion) {
		this.idHabitacion = idHabitacion;
	}

	public void setIngreso(LocalDateTime ingreso) {
		this.ingreso = ingreso;
	}

	public void setEgreso(LocalDateTime egreso) {
		this.egreso = egreso;
	}
	
}
