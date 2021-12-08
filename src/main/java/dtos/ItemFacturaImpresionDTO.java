package main.java.dtos;

import java.text.DecimalFormat;

import main.java.clases.ItemFactura;

public class ItemFacturaImpresionDTO {
	private String consumo;
	private Integer cantidad;
	private String precio;
	private String precioNeto;
	
	
	final DecimalFormat df = new DecimalFormat("0.00");
	
	public ItemFacturaImpresionDTO(ItemFactura item) {
		super();
		this.consumo = item.getDescripcion();
		this.cantidad = item.getCantidad();
		this.precio = "$ " + df.format(item.getPrecioUnitario());
		this.precioNeto = "$ " + df.format(item.getPrecioUnitario() * cantidad);
	}
	
	public ItemFacturaImpresionDTO() {
		super();
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
	
	public String getPrecioNeto() {
		return this.precioNeto;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	
	
}
