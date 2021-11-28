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
	
	
	
	
}
