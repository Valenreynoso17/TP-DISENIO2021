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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((capacidad == null) ? 0 : capacidad.hashCode());
		result = prime * result + ((costoPorNoche == null) ? 0 : costoPorNoche.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoHabitacion other = (TipoHabitacion) obj;
		if (capacidad == null) {
			if (other.capacidad != null)
				return false;
		} else if (!capacidad.equals(other.capacidad))
			return false;
		if (costoPorNoche == null) {
			if (other.costoPorNoche != null)
				return false;
		} else if (!costoPorNoche.equals(other.costoPorNoche))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	
	
	
	

	
	
	
	
}
