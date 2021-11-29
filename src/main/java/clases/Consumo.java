package main.java.clases;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="disenio.consumo")
public class Consumo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "descripcion", nullable = false, unique = false)
	private String descripcion;
	
	@Column(name = "valorunitario", nullable = false, unique = false)
	private Double valorUnitario;
	
	@Column(name = "fechaconsumo", nullable = false, unique = false)
	private LocalDate fechaConsumo;
	
	@Column(name = "cantidadtotal", nullable = false, unique = false)
	private Integer cantidadTotal;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "idocupacion", referencedColumnName = "id")
	private Ocupacion ocupacion;
	
	@OneToMany(mappedBy = "consumo")
	private List<ItemConsumo> items;
}
