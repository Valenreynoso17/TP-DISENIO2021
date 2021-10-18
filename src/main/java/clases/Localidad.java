package main.java.clases;

import javax.persistence.*;

@Entity
@Table(name="disenio.localidad")
public class Localidad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nombre", nullable = false, unique = false)
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "idprovincia", referencedColumnName = "id")
	private Provincia provincia;
	
	
	public Localidad(Integer id, String nombre, Provincia provincia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.provincia = provincia;
	}
	
	public Localidad() {
		super();
	}
	
	

	public Integer getId() {
		return id;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public String getNombre() {
		return nombre;
	}
	
	

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
	
	
	
}
