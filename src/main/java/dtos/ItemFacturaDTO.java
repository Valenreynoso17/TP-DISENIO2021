package main.java.dtos;

import main.java.clases.ItemConsumo;
import main.java.clases.ItemOcupacion;

public abstract class ItemFacturaDTO {
	private Integer id;
	private Double precioUnitario;
	private String descripcion;
	
	public ItemFacturaDTO(ItemOcupacion itemOcupacion) {
		this.id = itemOcupacion.getId();
		this.precioUnitario = itemOcupacion.getPrecioUnitario();
		this.descripcion = itemOcupacion.getDescripcion();
	}
	
	public ItemFacturaDTO(ItemConsumo itemConsumo) {
		this.id = itemConsumo.getId();
		this.precioUnitario = itemConsumo.getPrecioUnitario();
		this.descripcion = itemConsumo.getDescripcion();
	}

	public abstract Integer getCantidad();
	
	public Integer getId() {
		return id;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	
}
