package main.java.clases;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="disenio.personajuridica")
public class PersonaJuridica {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "cuit", nullable = false, unique = true)
	private String cuit;
	
	@Column(name = "telefono", nullable = false, unique = false)
	private String telefono;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "iddireccion", referencedColumnName = "id")
	private Direccion direccion;
	
	
	
	public PersonaJuridica() {
		super();
	}

	public PersonaJuridica(Integer id, String cuit, String telefono, Direccion direccion) {
		super();
		this.id = id;
		this.cuit = cuit;
		this.telefono = telefono;
		this.direccion = direccion;
	}

	
	
	public Integer getId() {
		return id;
	}

	public String getCuit() {
		return cuit;
	}

	public String getTelefono() {
		return telefono;
	}

	public Direccion getDireccion() {
		return direccion;
	}
	
	

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	
	
	
}
