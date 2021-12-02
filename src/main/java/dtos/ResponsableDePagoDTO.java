package main.java.dtos;

import main.java.clases.ResponsableDePago;
import main.java.enums.PosicionFrenteIva;

public class ResponsableDePagoDTO {
	
	private Integer id;
	private String razonSocial;
	private String cuit;
	private PosicionFrenteIva posicionFrenteIva;
	
	
	public ResponsableDePagoDTO(ResponsableDePago responsable) {
		this.id = responsable.getId();
		this.razonSocial = responsable.getRazonSocial();
		this.cuit = responsable.getCuit();
		this.posicionFrenteIva = responsable.getPosicionFrenteIva();
	}


	public Integer getId() {
		return id;
	}


	public String getRazonSocial() {
		return razonSocial;
	}


	public String getCuit() {
		return cuit;
	}


	public PosicionFrenteIva getPosicionFrenteIva() {
		return posicionFrenteIva;
	}
	
	
}
