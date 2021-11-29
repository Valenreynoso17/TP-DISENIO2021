package main.java.clases;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import main.java.enums.EstadoFactura;
import main.java.enums.TipoFactura;

@Entity
@Table(name="disenio.factura")
public class Factura extends DocumentoLegal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", nullable = false, unique = false)
	private TipoFactura tipo;
	
	@Column(name = "montototal", nullable = false, unique = false)
	private Double montoTotal;
	
	@Column(name = "montoneto", nullable = false, unique = false)
	private Double montoNeto;
	
	@Column(name = "vuelto", nullable = false, unique = false)
	private Double vuelto;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "estado", nullable = false, unique = false)
	private EstadoFactura estado;
	
	@Column(name = "fechafacturacion", nullable = false, unique = false)
	private LocalDate fechaFacturacion;
	
	private static Double iva = 0.21;
	
	@ManyToOne(optional = false)
	private Habitacion habitacion;
	
	@OneToMany(mappedBy = "factura")
	private List<ItemFactura> items;
	
}
