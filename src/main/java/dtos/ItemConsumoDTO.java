package main.java.dtos;

import main.java.clases.ItemConsumo;

public class ItemConsumoDTO extends ItemFacturaDTO{
	private Integer id;
	private Integer cantidad;
	
	public ItemConsumoDTO(ItemConsumo unItem) {
		this.id = unItem.getId();
		this.cantidad = unItem.getCantidad();
	}
	
	public Integer getCantidad() {
		return cantidad;
	}
}
