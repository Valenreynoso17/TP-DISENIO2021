package main.java.dtos;

public class ItemFilaDTO {
	private String nombre;
	private Double precioUnitario;
	private Integer cantidadMax;
	private Boolean esItemOcupacion;
	
	public ItemFilaDTO(String nombre, Double precioUnitario, Integer cantidadMax, Boolean esItemOcupacion) {
		super();
		this.nombre = nombre;
		this.precioUnitario = precioUnitario;
		this.cantidadMax = cantidadMax;
		this.esItemOcupacion = esItemOcupacion;
	}	
}
