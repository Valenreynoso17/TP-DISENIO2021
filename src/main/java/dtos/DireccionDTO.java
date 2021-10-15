package main.java.dtos;

public class DireccionDTO {
	private String calle;
	private String numeroCalle;
	private String departamento;
	private String piso;
	private Integer idLocalidad;
	private Integer codigoPostal;
	
	
	public DireccionDTO() {
		super();
	}
	
	public DireccionDTO(String calle, String numeroCalle, String departamento, String piso, Integer idLocalidad,
			Integer codigoPostal) {
		super();
		this.calle = calle;
		this.numeroCalle = numeroCalle;
		this.departamento = departamento;
		this.piso = piso;
		this.idLocalidad = idLocalidad;
		this.codigoPostal = codigoPostal;
	}
	
	

	public String getCalle() {
		return calle;
	}

	public String getNumeroCalle() {
		return numeroCalle;
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
	
	

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public void setNumeroCalle(String numeroCalle) {
		this.numeroCalle = numeroCalle;
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
