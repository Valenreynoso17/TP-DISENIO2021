package main.java.clases;

public class PersonaJuridica {
	private Integer id;
	private String cuit;
	private String telefono;
	private Direccion direccion;
	
	
	
	public PersonaJuridica() {
		super();
	}

	public PersonaJuridica(Integer id, String cuit, String telefono, Direccion direccion) {
		super();
		this.id = id;
		this.cuit = cuit;
		this.telefono = telefono;
		this.direccion = direccion;
	}

	
	
	public Integer getId() {
		return id;
	}

	public String getCuit() {
		return cuit;
	}

	public String getTelefono() {
		return telefono;
	}

	public Direccion getDireccion() {
		return direccion;
	}
	
	

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	
	
	
}
