package main.java.clases;

import java.time.LocalDate;

import main.java.enmus.PosicionFrenteIva;
import main.java.enmus.TipoDocumento;

public class Pasajero {
	private String id;
	private String apellido;
	private String nombre;
	private TipoDocumento tipoDocumento;
	private String documento;
	private LocalDate fechaNacimiento;
	private String email;
	private String ocupacion;
	private String telefono;
	private String cuit;
	private PosicionFrenteIva posicionFrenteIva;
	private Pais nacionalidad;
	private Direccion direccion; 
	//TODO ver si agregar o no responsableDePago
	
	
	public Pasajero(String id, String apellido, String nombre, TipoDocumento tipoDocumento, String documento,
			LocalDate fechaNacimiento, String email, String ocupacion, String telefono, String cuit,
			PosicionFrenteIva posicionFrenteIva, Pais nacionalidad, Direccion direccion) {
		super();
		this.id = id;
		this.apellido = apellido;
		this.nombre = nombre;
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
		this.ocupacion = ocupacion;
		this.telefono = telefono;
		this.cuit = cuit;
		this.posicionFrenteIva = posicionFrenteIva;
		this.nacionalidad = nacionalidad;
		this.direccion = direccion;
	}
	
	public Pasajero() {
		super();
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

	public String getDocumento() {
		return documento;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getEmail() {
		return email;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getCuit() {
		return cuit;
	}

	public PosicionFrenteIva getPosicionFrenteIva() {
		return posicionFrenteIva;
	}

	public Pais getNacionalidad() {
		return nacionalidad;
	}

	public Direccion getDireccion() {
		return direccion;
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

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public void setPosicionFrenteIva(PosicionFrenteIva posicionFrenteIva) {
		this.posicionFrenteIva = posicionFrenteIva;
	}

	public void setNacionalidad(Pais nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	
	
	
	
}
