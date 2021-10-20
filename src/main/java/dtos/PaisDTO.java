package main.java.dtos;

public class PaisDTO {
	private Integer id;
	private String nombre;
	private String nacionalidad;
	
	public PaisDTO() {
		super();
	}

	public PaisDTO(Integer id, String nombre, String nacionalidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}
	
	public String toString() {
		
		return this.nombre;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}
	
	

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	
}
