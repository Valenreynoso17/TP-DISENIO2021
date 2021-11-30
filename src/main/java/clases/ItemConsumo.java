package main.java.clases;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "disenio.itemconsumo")
public class ItemConsumo extends ItemFactura {
	@Column(name = "cantidad", nullable = false, unique = false)
	private Integer cantidad;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "idconsumo", referencedColumnName = "id")
	private Consumo consumo;

	public ItemConsumo(Integer id, Double precioUnitario, String descripcion, 
			Integer cantidad, Consumo consumo) {
		super(id, precioUnitario, descripcion);
		this.cantidad = cantidad;
		this.consumo = consumo;
		
		consumo.agregarItemConsumo(this);
	}
	
	

	public Integer getCantidad() {
		return cantidad;
	}

	public Consumo getConsumo() {
		return consumo;
	}
	
	

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public void setConsumo(Consumo consumo) {
		this.consumo = consumo;
	}
	
	
}
