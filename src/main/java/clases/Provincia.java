package main.java.clases;

import java.util.List;

public class Provincia {
	private String id;
	private String nombre;
	private Pais pais;
	private List<Localidad> localidades;
	
	
	public Provincia(String id, String nombre, Pais pais, List<Localidad> localidades) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.pais = pais;
		this.localidades = localidades;
	}
	
	public Provincia() {
		super();
	}
	
	

	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Pais getPais() {
		return pais;
	}

	public List<Localidad> getLocalidades() {
		return localidades;
	}
	
	
	
	public void setId(String id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public void setLocalidades(List<Localidad> localidades) {
		this.localidades = localidades;
	}
	
	
	
	
}
