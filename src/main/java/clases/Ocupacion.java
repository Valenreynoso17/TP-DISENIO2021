package main.java.clases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Ocupacion {
	private Integer id;
	private LocalDate ingreso;
	private LocalDate egreso;
	private LocalDateTime salidaReal;
	private Double precioPorDia;
	private Habitacion habitacion;
	private List<Pasajero> pasajeros;
	private Pasajero responsable;
	private List<ItemOcupacion> itemsOcupacion;
	private List<Consumo> cosumos;
}
