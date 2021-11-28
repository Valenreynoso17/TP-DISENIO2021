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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((provincia == null) ? 0 : provincia.hashCode());
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
		Localidad other = (Localidad) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		return true;
	}
	
	
	
	
}
