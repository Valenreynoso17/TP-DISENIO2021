package main.java.dtos;

import java.util.List;

public class ConsumoDTO {
	private List<ItemConsumoDTO> listaItemsConsumoDTO;
	
	public ConsumoDTO(List<ItemConsumoDTO> listaItemsDTO) {
		this.listaItemsConsumoDTO = listaItemsDTO;
	}
	
	public List<ItemConsumoDTO> getListaItems(){
		return listaItemsConsumoDTO;
	}
}
