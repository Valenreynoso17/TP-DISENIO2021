package main.java.dtos;

import java.time.LocalDate;
import java.util.List;

import main.java.enums.TipoFactura;

public class FacturaDTO {
	
	TipoFactura tipoFactura;
	LocalDate fechaFacturacion;
	Double montoNeto;
	Double montoIva;
	Double montoTotal;
	ResponsableDePagoDTO responsablePagoDTO;
	List<ItemFilaDTO> listaItemsFila;
	
	public FacturaDTO(ResponsableDePagoDTO responsablePagoDTO, TipoFactura tipoFactura, Double montoIva, Double montoTotal, List<ItemFilaDTO> listaItems) {
		super();
		this.responsablePagoDTO = responsablePagoDTO;
		this.tipoFactura = tipoFactura;
		this.fechaFacturacion = LocalDate.now();
		this.montoNeto = 0.0;
		this.montoIva = montoIva;
		this.montoTotal = montoTotal;
		this.listaItemsFila = listaItems;
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

	public List<ItemFilaDTO> getListaItemsFila() {
		return listaItemsFila;
	}
	
	
	
}
