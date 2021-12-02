package main.java.dtos;

import java.time.LocalDate;
import java.util.List;

import main.java.enums.TipoFactura;

public class FacturaDTO {
	
	TipoFactura tipoFactura;
	LocalDate fechaFacturacion;
	Double montoNeto;
	Double montoTotal;
	ResponsableDePagoDTO responsablePagoDTO;
	List<ItemFilaDTO> listaItems;
	
	public FacturaDTO(ResponsableDePagoDTO responsablePagoDTO, TipoFactura tipoFactura, Double montoTotal, List<ItemFilaDTO> listaItems) {
		super();
		this.responsablePagoDTO = responsablePagoDTO;
		this.tipoFactura = tipoFactura;
		this.fechaFacturacion = LocalDate.now();
		this.montoNeto = 0.0;
		this.montoTotal = montoTotal;
		this.listaItems = listaItems;
	}

	public TipoFactura getTipoFactura() {
		return tipoFactura;
	}

	public LocalDate getFechaFacturacion() {
		return fechaFacturacion;
	}

	public Double getMontoNeto() {
		return montoNeto;
	}

	public Double getMontoTotal() {
		return montoTotal;
	}

	public ResponsableDePagoDTO getResponsablePagoDTO() {
		return responsablePagoDTO;
	}

	public List<ItemFilaDTO> getListaItems() {
		return listaItems;
	}
	
	
	
}
