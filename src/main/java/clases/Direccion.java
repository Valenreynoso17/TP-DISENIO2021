package main.java.clases;

import javax.persistence.*;

import main.java.dtos.DireccionDTO;

@Entity
@Table(name="disenio.direccion")
public class Direccion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "direcciondomicilio", nullable = false, unique = false)
	private String direccionDomicilio;
	
	@Column(name = "departamento", nullable = false, unique = false)
	private String departamento;
	
	@Column(name = "piso", nullable = false, unique = false)
	private String piso;
	
	@Column(name = "codigopostal", nullable = false, unique = false)
	private String codigoPostal;

	@ManyToOne
	@JoinColumn(name = "idlocalidad", referencedColumnName = "id")
	private Localidad localidad;
	
	
	public Direccion(Integer id, String direccionDomicilio, String departamento, String piso, String codigoPostal,
			Localidad localidad) {
		super();
		this.id = id;
		this.direccionDomicilio = direccionDomicilio;
		this.departamento = departamento;
		this.piso = piso;
		this.codigoPostal = codigoPostal;
		this.localidad = localidad;
	}
	
	public Direccion(DireccionDTO direccionDTO) {
		super();
		this.id = direccionDTO.getId();
		this.direccionDomicilio = direccionDTO.getDireccionDomicilio();
		this.departamento = direccionDTO.getDepartamento();
		this.piso = direccionDTO.getPiso();
		this.codigoPostal = direccionDTO.getCodigoPostal();
		this.localidad = null;		
	}
	
	public Direccion() {
		super();
	}
	
	public Integer getId() {
		return id;
	}

	public String getDireccionDomicilio() {
		return direccionDomicilio;
	}

	public String getDepartamento() {
		return departamento;
	}

	public String getPiso() {
		return piso;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDireccionDomicilio(String direccionDomicilio) {
		this.direccionDomicilio = direccionDomicilio;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	
	
	
	
	
	
	
}
