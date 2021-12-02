package main.java.dtos;

import java.time.LocalDate;
import java.util.List;

import main.java.clases.Consumo;

public class ConsumoDTO {
	private Integer id;
	private String descripcion;
	private Double valorUnitario;
	private LocalDate fechaConsumo;
	private Integer cantidadTotal;
	private List<ItemConsumoDTO> listaItemsConsumoDTO;
	
	public ConsumoDTO(Consumo consumo, List<ItemConsumoDTO> listaItemsDTO) {
		this.id = consumo.getId();
		this.descripcion = consumo.getDescripcion();
		this.valorUnitario = consumo.getValorUnitario();
		this.fechaConsumo = consumo.getFechaConsumo();
		this.cantidadTotal = consumo.getCantidadTotal();
		this.listaItemsConsumoDTO = listaItemsDTO;
	}
	
	public List<ItemConsumoDTO> getListaItems(){
		return listaItemsConsumoDTO;
	}

	public Integer getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public LocalDate getFechaConsumo() {
		return fechaConsumo;
	}

	public Integer getCantidadTotal() {
		return cantidadTotal;
	}
}
