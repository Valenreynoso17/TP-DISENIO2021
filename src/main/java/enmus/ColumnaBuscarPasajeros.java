package main.java.enmus;

public enum ColumnaBuscarPasajeros {
	APELLIDO("apellido"), NOMBRE("nombre"), TIPO_DOCUMENTO("tipoDocumento"), NUMERO_DOCUMENTO("documento");
	
	private String nombreAtributo;
	
	private ColumnaBuscarPasajeros(String nombreAtributo) {
		this.nombreAtributo = nombreAtributo;
	}
	
	public String getNombreAtributo() {
		return nombreAtributo;
	}
}
