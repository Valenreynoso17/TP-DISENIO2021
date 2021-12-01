package main.java.clases;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="disenio.consumo")
public class Consumo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "descripcion", nullable = false, unique = false)
	private String descripcion;
	
	@Column(name = "valorunitario", nullable = false, unique = false)
	private Double valorUnitario;
	
	@Column(name = "fechaconsumo", nullable = false, unique = false)
	private LocalDate fechaConsumo;
	
	@Column(name = "cantidadtotal", nullable = false, unique = false)
	private Integer cantidadTotal;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "idocupacion", referencedColumnName = "id")
	private Ocupacion ocupacion;
	
	@OneToMany(mappedBy = "consumo", fetch = FetchType.EAGER)
	private List<ItemConsumo> items;

	
	
	public Consumo() {
		super();
	}
	
	public Consumo(Integer id, String descripcion, Double valorUnitario, LocalDate fechaConsumo, Integer cantidadTotal,
			Ocupacion ocupacion, List<ItemConsumo> items) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.valorUnitario = valorUnitario;
		this.fechaConsumo = fechaConsumo;
		this.cantidadTotal = cantidadTotal;
		this.ocupacion = ocupacion;
		this.items = items;
	}
	
	

	public Integer getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public LocalDate getFechaConsumo() {
		return fechaConsumo;
	}

	public Integer getCantidadTotal() {
		return cantidadTotal;
	}

	public Ocupacion getOcupacion() {
		return ocupacion;
	}

	public List<ItemConsumo> getItems() {
		return items;
	}
	
	

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public void setFechaConsumo(LocalDate fechaConsumo) {
		this.fechaConsumo = fechaConsumo;
	}

	public void setCantidadTotal(Integer cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}

	public void setOcupacion(Ocupacion ocupacion) {
		this.ocupacion = ocupacion;
	}

	public void setItems(List<ItemConsumo> items) {
		this.items = items;
	}	
	
	public void agregarItemConsumo(ItemConsumo item) {
		this.items.add(item);
	}
}
