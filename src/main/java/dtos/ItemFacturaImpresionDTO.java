package main.java.dtos;

import main.java.clases.ItemFactura;

public class ItemFacturaImpresionDTO {
	private String consumo;
	private Integer cantidad;
	private Double precio;
	private Double precioNeto;
	
	
	
	
	public ItemFacturaImpresionDTO(ItemFactura item) {
		super();
		this.consumo = item.getDescripcion();
		this.cantidad = item.getCantidad();
		this.precio = item.getPrecioUnitario();
		this.precioNeto = precio * cantidad;
	}
	
	
	public String getConsumo() {
		return consumo;
	}
	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	
	public Double getPrecioNeto() {
		return this.precioNeto;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	
}
