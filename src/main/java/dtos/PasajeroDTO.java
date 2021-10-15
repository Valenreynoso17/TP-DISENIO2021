package main.java.dtos;

import main.java.enmus.TipoDocumento;

public class PasajeroDTO {
	private String id;
	private String apellido;
	private String nombre;
	private TipoDocumento tipoDocumento;
	private String numeroDoc;
	private DireccionDTO direccion;
	private Integer idNacionalidad;
	
	
	
	public PasajeroDTO() {
		super();
	}
	
	public PasajeroDTO(String id, String apellido, String nombre, TipoDocumento tipoDocumento, String numeroDoc,
			DireccionDTO direccion, Integer idNacionalidad) {
		super();
		this.id = id;
		this.apellido = apellido;
		this.nombre = nombre;
		this.tipoDocumento = tipoDocumento;
		this.numeroDoc = numeroDoc;
		this.direccion = direccion;
		this.idNacionalidad = idNacionalidad;
	}

	
	
	public String getId() {
		return id;
	}
	
	public String getApellido() {
		return apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public String getNumeroDoc() {
		return numeroDoc;
	}

	public DireccionDTO getDireccion() {
		return direccion;
	}

	public Integer getIdNacionalidad() {
		return idNacionalidad;
	}
	
	
	
	public void setId(String id) {
		this.id = id;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	public void setNumeroDoc(String numeroDoc) {
		this.numeroDoc = numeroDoc;
	}

	public void setDireccion(DireccionDTO direccion) {
		this.direccion = direccion;
	}

	public void setIdNacionalidad(Integer idNacionalidad) {
		this.idNacionalidad = idNacionalidad;
	}	
	
	
}
