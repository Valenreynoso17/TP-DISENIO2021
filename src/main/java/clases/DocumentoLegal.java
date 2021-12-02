package main.java.clases;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="disenio.documentolegal")
public abstract class DocumentoLegal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	protected Integer id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "iddatosresponsable", referencedColumnName = "id")
	protected DatosResponsableDePago datosResponsable;

	
	
	public DocumentoLegal() {
		super();
	}
	
	public DocumentoLegal(Integer id, DatosResponsableDePago datosResponsable) {
		super();
		this.id = id;
		this.datosResponsable = datosResponsable;
	}
	
	public DocumentoLegal(DatosResponsableDePago datosResponsable) {
		super();
		this.datosResponsable = datosResponsable;
	}
	
	

	public Integer getId() {
		return id;
	}

	public DatosResponsableDePago getDatosResponsable() {
		return datosResponsable;
	}
	
	

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDatosResponsable(DatosResponsableDePago datosResponsable) {
		this.datosResponsable = datosResponsable;
	}
	
	
	
	
	
}
