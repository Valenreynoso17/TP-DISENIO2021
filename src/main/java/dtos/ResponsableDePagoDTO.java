package main.java.dtos;

import main.java.clases.ResponsableDePago;

public class ResponsableDePagoDTO {
	
	private String razonSocial;
	
	public ResponsableDePagoDTO(ResponsableDePago responsable) {
		this.razonSocial = responsable.getRazonSocial();
	}
}
