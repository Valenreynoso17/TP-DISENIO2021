package main.java.clases;

import javax.persistence.*;

@Entity
@Table(name="disenio.habitacion")
public class Habitacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "numero", nullable = false, unique = false)
	private Integer numero;
	
	@ManyToOne
	@JoinColumn(name = "idtipohabitacion", referencedColumnName = "id")
	private TipoHabitacion tipo;

	
	
	public Habitacion(Integer id, Integer numero, TipoHabitacion tipo) {
		super();
		this.id = id;
		this.numero = numero;
		this.tipo = tipo;
	}
	
	public Habitacion() {
		super();
	}
	
	

	public Integer getId() {
		return id;
	}

	public Integer getNumero() {
		return numero;
	}

	public TipoHabitacion getTipo() {
		return tipo;
	}
	
	

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public void setTipo(TipoHabitacion tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Habitacion other = (Habitacion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	
	
	
	
}
