package main.java.clases;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="disenio.consumo")
public class Consumo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "descripcion", nullable = false, unique = false)
	private String descripcion;
	private Double valorUnitario;
	private LocalDate fechaConsumo;
	private Integer cantidadTotal;
	private Ocupacion ocupacion;
}
