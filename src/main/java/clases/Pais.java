package main.java.clases;

import java.util.List;

public class Pais {
	private String id;
	private String nombre;
	private String nacionalidad;
	private List<Provincia> provincias;
	
	
	public Pais(String id, String nombre, String nacionalidad, List<Provincia> provincias) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.provincias = provincias;
	}
	
	public Pais() {
		super();
	}
	

	
	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public List<Provincia> getProvincias() {
		return provincias;
	}	
	
	
	
	public void setId(String id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public void setProvincias(List<Provincia> provincias) {
		this.provincias = provincias;
	}
	
	
	
	
	
}
