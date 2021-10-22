package main.java.dtos;

public class LocalidadDTO {
	private Integer id;
	private String nombre;
	
	
	public LocalidadDTO(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	public LocalidadDTO() {
		super();
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
	
	

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	
}
