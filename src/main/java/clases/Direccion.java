package main.java.clases;

public class Direccion {
	private String id;
	private String calle;
	private Integer numero;
	private Integer departamento;
	private Integer piso;
	private Integer codigoPostal;
	private Localidad localidad;
	
	
	public Direccion(String id, String calle, Integer numero, Integer departamento, Integer piso, Integer codigoPostal,
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
	


	public String getId() {
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

	

	public void setId(String id) {
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
