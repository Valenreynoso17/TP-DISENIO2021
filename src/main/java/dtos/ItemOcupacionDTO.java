package main.java.dtos;

import main.java.clases.ItemOcupacion;

public class ItemOcupacionDTO extends ItemFacturaDTO{
	private Integer cantidadDias;
	
	public ItemOcupacionDTO(ItemOcupacion itemOcupacion) {
		super(itemOcupacion);
		this.cantidadDias = itemOcupacion.getCantidadDias();
	}
	
	public Integer getCantidadDias() {
		return cantidadDias;
	}
}
