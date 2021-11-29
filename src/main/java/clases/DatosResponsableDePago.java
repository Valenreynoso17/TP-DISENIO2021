package main.java.clases;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	
	@OneToOne(optional = false)
	@JoinColumn(name = "iddireccion", referencedColumnName = "id")
	private Direccion direccion;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "idresponsabledepago", referencedColumnName = "id")
	private ResponsableDePago reponsable;
}
