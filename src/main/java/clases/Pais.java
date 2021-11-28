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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nacionalidad == null) ? 0 : nacionalidad.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((provincias == null) ? 0 : provincias.hashCode());
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
		Pais other = (Pais) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nacionalidad == null) {
			if (other.nacionalidad != null)
				return false;
		} else if (!nacionalidad.equals(other.nacionalidad))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (provincias == null) {
			if (other.provincias != null)
				return false;
		} else if (!provincias.equals(other.provincias))
			return false;
		return true;
	}
	
	
	
	
	
}
