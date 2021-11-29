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
}
