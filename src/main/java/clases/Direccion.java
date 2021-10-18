package main.java.clases;

import javax.persistence.*;

@Entity
@Table(name="disenio.direccion")
public class Direccion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "calle", nullable = false, unique = false)
	private String calle;
	
	@Column(name = "numero", nullable = false, unique = false)
	private Integer numero;
	
	@Column(name = "departamento", nullable = false, unique = false)
	private Integer departamento;
	
	@Column(name = "piso", nullable = false, unique = false)
	private Integer piso;
	
	@Column(name = "codigopostal", nullable = false, unique = false)
	private Integer codigoPostal;

	@ManyToOne
	@JoinColumn(name = "idlocalidad", referencedColumnName = "id")
	private Localidad localidad;
	
	
	public Direccion(Integer id, String calle, Integer numero, Integer departamento, Integer piso, Integer codigoPostal,
			Localidad localidad) {
		super();
		this.id = id;
		this.calle = calle;
		this.numero = numero;
		this.departamento = departamento;
		this.piso = piso;
		this.codigoPostal = codigoPostal;
		this.localidad = localidad;
	}
	
	
	public Direccion() {
		super();
	}
	


	public Integer getId() {
		return id;
	}

	public String getCalle() {
		return calle;
	}

	public Integer getNumero() {
		return numero;
	}

	public Integer getDepartamento() {
		return departamento;
	}

	public Integer getPiso() {
		return piso;
	}

	public Integer getCodigoPostal() {
		return codigoPostal;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public void setDepartamento(Integer departamento) {
		this.departamento = departamento;
	}

	public void setPiso(Integer piso) {
		this.piso = piso;
	}

	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	
	
	
	
	
	
	
}
