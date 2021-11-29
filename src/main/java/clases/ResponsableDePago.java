package main.java.clases;

import javax.persistence.*;

import main.java.excepciones.YaAsociadoConPasajeroException;
import main.java.excepciones.YaAsociadoConPersonaJuridicaException;

@Entity
@Table(name="disenio.responsabledepago")
public class ResponsableDePago {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "razonSocial")
	private String razonSocial;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "idresponsabledepago", nullable = true, referencedColumnName = "id")
	private PersonaJuridica personaJuridica;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "idpasajero", nullable = true, referencedColumnName = "id")
	private Pasajero pasajero;
	
	public ResponsableDePago() {
		super();
	}

	public ResponsableDePago(Integer id, String razonSocial) {
		super();
		this.id = id;
		this.razonSocial = razonSocial;
	}
	
	

	public Integer getId() {
		return id;
	}

	public String getRazonSocial() {
		return razonSocial;
	}
	
	public PersonaJuridica getPersonaJuridica() {
		return personaJuridica;
	}

	public Pasajero getPasajero() {
		return pasajero;
	}
	


	public void setId(Integer id) {
		this.id = id;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	public void setPersonaJuridica(PersonaJuridica personaJuridica) {
		if (this.pasajero != null) throw new YaAsociadoConPasajeroException();
		this.personaJuridica = personaJuridica;
	}

	public void setPasajero(Pasajero pasajero) {
		if (this.personaJuridica != null) throw new YaAsociadoConPersonaJuridicaException();
		this.pasajero = pasajero;
	}
	
	
	
	
}
