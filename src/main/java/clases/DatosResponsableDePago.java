package main.java.clases;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import main.java.enums.PosicionFrenteIva;

@Entity
@Table(name="disenio.datosresponsabledepago")
public class DatosResponsableDePago {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "razonSocial", nullable = false, unique = false)
	private String razonSocial;
	
	@Column(name = "cuit", nullable = false, unique = false)
	private String cuit;
	
	@Column(name = "telefono", nullable = false, unique = false)
	private String telefono;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "posicionFrenteIVA", nullable = false, unique = false)
	private PosicionFrenteIva posicionFrenteIVA;
	
	@OneToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "iddireccion", referencedColumnName = "id")
	private Direccion direccion;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "idresponsabledepago", referencedColumnName = "id")
	private ResponsableDePago reponsable;

	
	
	public DatosResponsableDePago() {
		super();
	}
	
	public DatosResponsableDePago(Integer id, String razonSocial, String cuit, String telefono, PosicionFrenteIva posicionFrenteIva,
			Direccion direccion, ResponsableDePago reponsable) {
		super();
		this.id = id;
		this.razonSocial = razonSocial;
		this.cuit = cuit;
		this.telefono = telefono;
		this.posicionFrenteIVA = posicionFrenteIva;
		this.direccion = direccion;
		this.reponsable = reponsable;
	}
	
	public DatosResponsableDePago(ResponsableDePago r) {
		this(null, r.getRazonSocial(), r.getCuit(), r.getTelefono(), r.getPosicionFrenteIva(), r.getDireccion().clonar(), r);
	}
	
	

	public Integer getId() {
		return id;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public String getCuit() {
		return cuit;
	}

	public String getTelefono() {
		return telefono;
	}
	
	public PosicionFrenteIva getPosicionFrenteIva() {
		return posicionFrenteIVA;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public ResponsableDePago getReponsable() {
		return reponsable;
	}
	
	

	public void setId(Integer id) {
		this.id = id;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public void setPosicionFrenteIva(PosicionFrenteIva posicionFrenteIva) {
		this.posicionFrenteIVA = posicionFrenteIva;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public void setReponsable(ResponsableDePago reponsable) {
		this.reponsable = reponsable;
	}
}
