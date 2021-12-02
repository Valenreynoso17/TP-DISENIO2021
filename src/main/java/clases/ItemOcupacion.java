package main.java.clases;

import javax.persistence.*;

import main.java.dtos.ItemFilaDTO;

@Entity
@Table(name="disenio.itemocupacion")
public class ItemOcupacion extends ItemFactura {
	@Column(name = "cantidaddias", nullable = false, unique = false)
	private Integer cantidadDias;
	
	@ManyToOne(optional = false, cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinColumn(name = "idocupacion", referencedColumnName = "id")
	private Ocupacion ocupacion;
	
	public ItemOcupacion(Integer id, Double precioUnitario, String descripcion, 
			Integer cantidadDias, Ocupacion ocupacion) {
		super(id, precioUnitario, descripcion);
		this.cantidadDias = cantidadDias;
		this.ocupacion = ocupacion;
		
		ocupacion.agregarItemOcupacion(this);
	}
	
	public ItemOcupacion(ItemFilaDTO unItem, Ocupacion ocupacion) {
		super(unItem.getPrecioUnitario(), unItem.getDescripcion());
		this.cantidadDias = unItem.getCantidadSeleccionada();
		this.ocupacion = ocupacion;
		
		ocupacion.agregarItemOcupacion(this);
	}
	
	public ItemOcupacion() {
		super();
	}
	
	public Integer getCantidadDias() {
		return this.cantidadDias;
	}
	
	
}
