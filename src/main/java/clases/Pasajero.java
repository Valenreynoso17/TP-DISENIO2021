package main.java.clases;

import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.hibernate.engine.jdbc.env.spi.NameQualifierSupport;

import main.java.dtos.PasajeroDTO;
import main.java.enmus.PosicionFrenteIva;
import main.java.enmus.TipoDocumento;

@Entity
@Table(name="disenio.pasajero")
public class Pasajero {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "apellido", nullable = false, unique = false)
	private String apellido;
	
	@Column(name = "nombre", nullable = false, unique = false)
	private String nombre;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipodoc", nullable = false, unique = false)
	private TipoDocumento tipoDocumento;
	
	@Column(name = "documento", nullable = false, unique = false)
	private String documento;
	
	@Column(name = "fechaNacimiento", nullable = false, unique = false)
	private LocalDate fechaNacimiento;
	
	@Column(name = "email", nullable = false, unique = false)
	private String email;
	
	@Column(name = "ocupacion", nullable = false, unique = false)
	private String ocupacion;
	
	@Column(name = "telefono", nullable = false, unique = false)
	private String telefono;
	
	@Column(name = "cuit", nullable = false, unique = false)
	private String cuit;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "posicionFrenteIVA", nullable = false, unique = false)
	private PosicionFrenteIva posicionFrenteIva;
	
	@ManyToOne
	@JoinColumn(name = "idPais", referencedColumnName = "id")
	private Pais nacionalidad;

	@OneToOne(optional = false)
	@JoinColumn(name = "iddireccion", referencedColumnName = "id")
	private Direccion direccion; 
	
	//private ResponsableDePago responsableDePago;
	
	
	public Pasajero(Integer id, String apellido, String nombre, TipoDocumento tipoDocumento, String documento,
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
	
	public Pasajero(PasajeroDTO pasajeroDTO, Direccion direccion) {
		super();
		this.id = pasajeroDTO.getId();
		this.apellido = pasajeroDTO.getApellido();
		this.nombre = pasajeroDTO.getNombre();
		this.tipoDocumento = pasajeroDTO.getTipoDocumento();
		this.documento = pasajeroDTO.getNumeroDoc();
//		this.fechaNacimiento = pasajeroDTO.getfe
//		this.email = pasajeroDTO.get
//		this.ocupacion = pasajeroDTO.ge
//		this.telefono = pasajeroDTO.get
//		this.cuit = pasajeroDTO.get
//		this.posicionFrenteIva = pasajeroDTO.getp
//		this.nacionalidad = pasajeroDTO.get
		this.direccion = direccion;
	}
	
	public Pasajero() {
		super();
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
