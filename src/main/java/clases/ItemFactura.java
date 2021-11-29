package main.java.clases;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="disenio.itemfactura")
public abstract class ItemFactura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "preciounitario", nullable = false, unique = false)
	private Double precioUnitario;
	
	@Column(name = "descripcion", nullable = false, unique = false)
	private String descripcion;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "idfactura", referencedColumnName = "id")
	private Factura factura;
}
