package main.java.dtos;

import main.java.clases.ItemOcupacion;

public class ItemOcupacionDTO extends ItemFacturaDTO{
	private Integer id;
	private Integer cantidadDias;
	
	public ItemOcupacionDTO(ItemOcupacion itemOcupacion) {
		this.id = itemOcupacion.getId();
		this.cantidadDias = itemOcupacion.getCantidadDias();
	}
	
	public Integer getCantidadDias() {
		return cantidadDias;
	}
}
