package main.java.clases;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="disenio.provincia")
public class Provincia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nombre", nullable = false, unique = false)
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "idpais", referencedColumnName = "id")
	private Pais pais;
	
	@OneToMany(mappedBy = "provincia")
	private List<Localidad> localidades;
	
	
	public Provincia(Integer id, String nombre, Pais pais, List<Localidad> localidades) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.pais = pais;
		this.localidades = localidades;
	}
	
	public Provincia() {
		super();
	}
	
	

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Pais getPais() {
		return pais;
	}

	public List<Localidad> getLocalidades() {
		return localidades;
	}
	
	
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public void setLocalidades(List<Localidad> localidades) {
		this.localidades = localidades;
	}
	
	
	
	
}
