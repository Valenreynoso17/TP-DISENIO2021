package main.java.dtos;

public class ProvinciaDTO {
	private Integer id;
	private String nombre;
	
	
	public ProvinciaDTO(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	public ProvinciaDTO() {
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
