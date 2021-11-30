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
	@Column(name = "numero", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer numero;
	
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
	@JoinColumn(name = "idhabitacion", referencedColumnName = "id")
	private Habitacion habitacion;
	
	@OneToMany
	@JoinColumn(name = "idfactura", referencedColumnName = "id")
	private List<ItemFactura> items;

	
	
	public Factura() {
		super();
	}
	
	public Factura(Integer id, DatosResponsableDePago datosResponsable, Integer numero, TipoFactura tipo, Double montoTotal, 
			Double montoNeto, Double vuelto, EstadoFactura estado, LocalDate fechaFacturacion, Habitacion habitacion, 
			List<ItemFactura> items) {
		super(id, datosResponsable);
		this.numero = numero;
		this.tipo = tipo;
		this.montoTotal = montoTotal;
		this.montoNeto = montoNeto;
		this.vuelto = vuelto;
		this.estado = estado;
		this.fechaFacturacion = fechaFacturacion;
		this.habitacion = habitacion;
		this.items = items;
	}
	
	

	public Integer getNumero() {
		return numero;
	}

	public TipoFactura getTipo() {
		return tipo;
	}

	public Double getMontoTotal() {
		return montoTotal;
	}

	public Double getMontoNeto() {
		return montoNeto;
	}

	public Double getVuelto() {
		return vuelto;
	}

	public EstadoFactura getEstado() {
		return estado;
	}

	public LocalDate getFechaFacturacion() {
		return fechaFacturacion;
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public List<ItemFactura> getItems() {
		return items;
	}
	
	

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public void setTipo(TipoFactura tipo) {
		this.tipo = tipo;
	}

	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public void setMontoNeto(Double montoNeto) {
		this.montoNeto = montoNeto;
	}

	public void setVuelto(Double vuelto) {
		this.vuelto = vuelto;
	}

	public void setEstado(EstadoFactura estado) {
		this.estado = estado;
	}

	public void setFechaFacturacion(LocalDate fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}
}
