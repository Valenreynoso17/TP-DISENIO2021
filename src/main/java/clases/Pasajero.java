package main.java.clases;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.hibernate.engine.jdbc.env.spi.NameQualifierSupport;

import main.java.dtos.PasajeroDTO;
import main.java.enums.PosicionFrenteIva;
import main.java.enums.TipoDocumento;

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

	@OneToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "iddireccion", referencedColumnName = "id")
	private Direccion direccion; 
	
	
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
	
	public Pasajero(PasajeroDTO pasajeroDTO, Direccion direccion, Pais nacionalidad) {
		super();
		this.id = pasajeroDTO.getId();
		this.apellido = pasajeroDTO.getApellido();
		this.nombre = pasajeroDTO.getNombre();
		this.tipoDocumento = pasajeroDTO.getTipoDocumento();
		this.documento = pasajeroDTO.getNumeroDoc();
		this.fechaNacimiento = pasajeroDTO.getFechaNacimiento();
		this.email = pasajeroDTO.getEmail();
		this.ocupacion = pasajeroDTO.getOcupacion();
		this.telefono = pasajeroDTO.getTelefono();
		this.cuit = pasajeroDTO.getCuit();
		this.posicionFrenteIva = pasajeroDTO.getPosicionIVA();
		this.nacionalidad = nacionalidad;
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
	
	public Integer getEdad() {
		return Period.between(fechaNacimiento, LocalDate.now()).getYears();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result + ((cuit == null) ? 0 : cuit.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((documento == null) ? 0 : documento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nacionalidad == null) ? 0 : nacionalidad.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((ocupacion == null) ? 0 : ocupacion.hashCode());
		result = prime * result + ((posicionFrenteIva == null) ? 0 : posicionFrenteIva.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		result = prime * result + ((tipoDocumento == null) ? 0 : tipoDocumento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pasajero other = (Pasajero) obj;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (cuit == null) {
			if (other.cuit != null)
				return false;
		} else if (!cuit.equals(other.cuit))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (documento == null) {
			if (other.documento != null)
				return false;
		} else if (!documento.equals(other.documento))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fechaNacimiento == null) {
			if (other.fechaNacimiento != null)
				return false;
		} else if (!fechaNacimiento.equals(other.fechaNacimiento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nacionalidad == null) {
			if (other.nacionalidad != null)
				return false;
		} else if (!nacionalidad.equals(other.nacionalidad))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (ocupacion == null) {
			if (other.ocupacion != null)
				return false;
		} else if (!ocupacion.equals(other.ocupacion))
			return false;
		if (posicionFrenteIva != other.posicionFrenteIva)
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		if (tipoDocumento != other.tipoDocumento)
			return false;
		return true;
	}


	
	
	
	
	
}
