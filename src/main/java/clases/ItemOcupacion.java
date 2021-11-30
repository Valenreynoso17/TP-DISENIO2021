package main.java.clases;

import javax.persistence.*;

@Entity
@Table(name="disenio.itemocupacion")
public class ItemOcupacion extends ItemFactura {
	@Column(name = "cantidaddias", nullable = false, unique = false)
	private Integer cantidadDias;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "idocupacion", referencedColumnName = "id")
	private Ocupacion ocupacion;
	
	public ItemOcupacion(Integer id, Double precioUnitario, String descripcion, 
			Integer cantidadDias, Ocupacion ocupacion) {
		super(id, precioUnitario, descripcion);
		this.cantidadDias = cantidadDias;
		this.ocupacion = ocupacion;
		
		//ocupacion.agregarItemOcupacion(this);
	}
	
	public ItemOcupacion() {
		super();
	}
	
	public Integer getCantidadDias() {
		return this.cantidadDias;
	}
}
