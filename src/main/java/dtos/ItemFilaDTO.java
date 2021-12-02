package main.java.dtos;

public class ItemFilaDTO {
	private Integer idAsociado;
	private String descripcion;
	private Double precioUnitario;
	private Integer cantidadSeleccionada;
	private Integer cantidadMax;
	private Boolean esItemOcupacion;
	
	public ItemFilaDTO(Integer idAsociado, String descripcion, Double precioUnitario, Integer cantidadMax, Boolean esItemOcupacion) {
		super();
		this.idAsociado = idAsociado;
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
		this.cantidadSeleccionada = 0;
		this.cantidadMax = cantidadMax;
		this.esItemOcupacion = esItemOcupacion;
	}
	
	public Integer getIdAsociado() {
		return idAsociado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public Integer getCantidadSeleccionada() {
		return cantidadSeleccionada;
	}

	public Integer getCantidadMax() {
		return cantidadMax;
	}

	public Boolean getEsItemOcupacion() {
		return esItemOcupacion;
	}
	
	
}
