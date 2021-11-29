package main.java.clases;

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
	private Integer numero;
	
	@OneToOne
	@JoinColumn(name = "iddatosresponsable", referencedColumnName = "id")
	private DatosResponsableDePago datosResponsable;

	
	
	public DocumentoLegal() {
		super();
	}
	
	public DocumentoLegal(Integer numero, DatosResponsableDePago datosResponsable) {
		super();
		this.numero = numero;
		this.datosResponsable = datosResponsable;
	}
	
	

	public Integer getNumero() {
		return numero;
	}

	public DatosResponsableDePago getDatosResponsable() {
		return datosResponsable;
	}
	
	

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public void setDatosResponsable(DatosResponsableDePago datosResponsable) {
		this.datosResponsable = datosResponsable;
	}
	
	
	
	
	
}
