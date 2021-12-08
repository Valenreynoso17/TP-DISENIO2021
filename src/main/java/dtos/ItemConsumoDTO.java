package main.java.dtos;

import main.java.clases.ItemConsumo;

public class ItemConsumoDTO extends ItemFacturaDTO{
	private Integer cantidad;
	
	public ItemConsumoDTO(ItemConsumo itemConsumo) {
		super(itemConsumo);
		this.cantidad = itemConsumo.getCantidad();
	}
	
	@Override
	public Integer getCantidad() {
		return cantidad;
	}
}
