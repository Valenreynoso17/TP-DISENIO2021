package main.java.clases;

import javax.persistence.*;

@Entity
@Table(name="disenio.tipohabitacion")
public class TipoHabitacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "tipo", nullable = false, unique = false)
	private String tipo;
	
	@Column(name = "costopornoche", nullable = false, unique = false)
	private Double costoPorNoche;
	
	@Column(name = "capacidad", nullable = false, unique = false)
	private Integer capacidad;

	// private List<Habitacion> habitacion
	// lo ponemos o no?
	
	
	
	public TipoHabitacion(Integer id, String tipo, Double costoPorNoche, Integer capacidad) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.costoPorNoche = costoPorNoche;
		this.capacidad = capacidad;
	}
	
	public TipoHabitacion() {
		super();
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
