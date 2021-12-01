package main.java.dtos;

public class TipoHabitacionDTO {
	private Integer id;
	private String tipo;
	private Double costoPorNoche;
	private Integer capacidad;
	
	
	
	public TipoHabitacionDTO() {
		super();
	}
	
	public TipoHabitacionDTO(Integer id, String tipo, Double costoPorNoche, Integer capacidad) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.costoPorNoche = costoPorNoche;
		this.capacidad = capacidad;
	}
	
	

	public Integer getId() {
		return id;
	}

	public String getTipo() {
		return tipo;
	}

	public Double getCostoPorNoche() {
		return costoPorNoche;
	}

	public Integer getCapacidad() {
		return capacidad;
	}
	
	

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setCostoPorNoche(Double costoPorNoche) {
		this.costoPorNoche = costoPorNoche;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}
	
	
	
	
	
}
