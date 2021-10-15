package main.java.clases;

public class Localidad {
	private String id;
	private String nombre;
	private Provincia Provincia;
	
	
	public Localidad(String id, String nombre, main.java.clases.Provincia provincia) {
		super();
		this.id = id;
		this.nombre = nombre;
		Provincia = provincia;
	}
	
	public Localidad() {
		super();
	}
	
	

	public String getId() {
		return id;
	}

	public Provincia getProvincia() {
		return Provincia;
	}

	public String getNombre() {
		return nombre;
	}
	
	

	public void setId(String id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setProvincia(Provincia provincia) {
		Provincia = provincia;
	}
	
	
	
	
}
