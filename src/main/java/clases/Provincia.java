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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((localidades == null) ? 0 : localidades.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
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
		Provincia other = (Provincia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (localidades == null) {
			if (other.localidades != null)
				return false;
		} else if (!localidades.equals(other.localidades))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		return true;
	}
	
	
	
	
}
