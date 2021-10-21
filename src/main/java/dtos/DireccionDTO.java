package main.java.dtos;

public class DireccionDTO {
	private Integer id;
	private String direccionDomicilio;
	private String departamento;
	private String piso;
	private Integer idLocalidad;
	private Integer codigoPostal;
	
	
	public DireccionDTO() {
		super();
	}
	
	public DireccionDTO(Integer id, String direccionDomicilio, String departamento, String piso, Integer idLocalidad,
			Integer codigoPostal) {
		super();
		this.id = id;
		this.direccionDomicilio = direccionDomicilio;
		this.departamento = departamento;
		this.piso = piso;
		this.idLocalidad = idLocalidad;
		this.codigoPostal = codigoPostal;
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
	
	public Integer getIdLocalidad() {
		return idLocalidad;
	}

	public Integer getCodigoPostal() {
		return codigoPostal;
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

	public void setIdLocalidad(Integer idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	
	
	
	
}
