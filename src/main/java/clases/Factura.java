package main.java.clases;

import java.time.LocalDate;
import java.util.List;

import main.java.enums.EstadoFactura;
import main.java.enums.TipoFactura;

public class Factura extends DocumentoLegal {
	private Integer id;
	private TipoFactura tipo;
	private Double montoTotal;
	private Double montoNeto;
	private Double vuelto;
	private EstadoFactura estado;
	private LocalDate fechaFacturacion;
	private static Double iva;	// mmmmmmmm
	private Habitacion habitacion;
	private List<ItemFactura> items;
	
}
