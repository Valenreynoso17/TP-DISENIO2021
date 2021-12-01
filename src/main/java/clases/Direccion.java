package main.java.clases;

import javax.persistence.*;

import main.java.dtos.DireccionDTO;

@Entity
@Table(name="disenio.direccion")
public class Direccion implements Cloneable {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoPostal == null) ? 0 : codigoPostal.hashCode());
		result = prime * result + ((departamento == null) ? 0 : departamento.hashCode());
		result = prime * result + ((direccionDomicilio == null) ? 0 : direccionDomicilio.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((localidad == null) ? 0 : localidad.hashCode());
		result = prime * result + ((piso == null) ? 0 : piso.hashCode());
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
		Direccion other = (Direccion) obj;
		if (codigoPostal == null) {
			if (other.codigoPostal != null)
				return false;
		} else if (!codigoPostal.equals(other.codigoPostal))
			return false;
		if (departamento == null) {
			if (other.departamento != null)
				return false;
		} else if (!departamento.equals(other.departamento))
			return false;
		if (direccionDomicilio == null) {
			if (other.direccionDomicilio != null)
				return false;
		} else if (!direccionDomicilio.equals(other.direccionDomicilio))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (localidad == null) {
			if (other.localidad != null)
				return false;
		} else if (!localidad.equals(other.localidad))
			return false;
		if (piso == null) {
			if (other.piso != null)
				return false;
		} else if (!piso.equals(other.piso))
			return false;
		return true;
	}
	
	
	protected Direccion clonar() {
		Direccion direccion = new Direccion();
		
		direccion.setDireccionDomicilio(this.direccionDomicilio);
		direccion.setPiso(this.piso);
		direccion.setDepartamento(this.departamento);
		direccion.setCodigoPostal(this.codigoPostal);
		direccion.setLocalidad(this.localidad);
		
		return direccion;
	}
	
	
	
	
}
