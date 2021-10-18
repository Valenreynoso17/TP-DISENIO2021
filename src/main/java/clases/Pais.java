package main.java.clases;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="disenio.pais")
public class Pais {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombre", nullable = false, unique = false)
	private String nombre;
	
	@Column(name = "nacionalidad", nullable = false, unique = false)
	private String nacionalidad;
	
	@OneToMany(mappedBy = "pais")
	private List<Provincia> provincias;
	
	
	public Pais(Integer id, String nombre, String nacionalidad, List<Provincia> provincias) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.provincias = provincias;
	}
	
	public Pais() {
		super();
	}
	

	
	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public List<Provincia> getProvincias() {
		return provincias;
	}	
	
	
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public void setProvincias(List<Provincia> provincias) {
		this.provincias = provincias;
	}
	
	
	
	
	
}
