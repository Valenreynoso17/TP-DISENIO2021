package main.java.dtos;

import java.time.LocalDate;

import main.java.enums.PosicionFrenteIva;
import main.java.enums.TipoDocumento;

public class PasajeroDTO {
	private Integer id;
	private String apellido;
	private String nombre;
	private TipoDocumento tipoDocumento;
	private String numeroDoc;
	private String cuit;
	private PosicionFrenteIva posicionIVA;
	private String email;
	private String telefono;
	private LocalDate fechaNacimiento;
	private String ocupacion;
	private DireccionDTO direccion;
	private Integer idNacionalidad;
	
	public PasajeroDTO() {
		super();
	}
	
	public PasajeroDTO(Integer id, String apellido, String nombre, TipoDocumento tipoDocumento, String numeroDoc,
			String cuit, PosicionFrenteIva posicionIVA, String email, String telefono, LocalDate fechaNacimiento,
			String ocupacion, DireccionDTO direccion, Integer idNacionalidad) {
		super();
		this.id = id;
		this.apellido = apellido;
		this.nombre = nombre;
		this.tipoDocumento = tipoDocumento;
		this.numeroDoc = numeroDoc;
		this.cuit = cuit;
		this.posicionIVA = posicionIVA;
		this.email = email;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.ocupacion = ocupacion;
		this.direccion = direccion;
		this.idNacionalidad = idNacionalidad;
	}

	public Integer getId() {
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

	public String getCuit() {
		return cuit;
	}

	public PosicionFrenteIva getPosicionIVA() {
		return posicionIVA;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefono() {
		return telefono;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public DireccionDTO getDireccion() {
		return direccion;
	}

	public Integer getIdNacionalidad() {
		return idNacionalidad;
	}

	public void setId(Integer id) {
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

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public void setPosicionIVA(PosicionFrenteIva posicionIVA) {
		this.posicionIVA = posicionIVA;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public void setDireccion(DireccionDTO direccion) {
		this.direccion = direccion;
	}

	public void setIdNacionalidad(Integer idNacionalidad) {
		this.idNacionalidad = idNacionalidad;
	}
	
	
}
