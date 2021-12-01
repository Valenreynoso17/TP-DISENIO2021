package main.java.dtos;

public class ItemFilaDTO {
	private String nombre;
	private Double precioUnitario;
	private Integer cantidadSeleccionada;
	private Integer cantidadMax;
	private Boolean esItemOcupacion;
	
	public ItemFilaDTO(String nombre, Double precioUnitario, Integer cantidadMax, Boolean esItemOcupacion) {
		super();
		this.nombre = nombre;
		this.precioUnitario = precioUnitario;
		this.cantidadSeleccionada = 0;
		this.cantidadMax = cantidadMax;
		this.esItemOcupacion = esItemOcupacion;
	}	
}
