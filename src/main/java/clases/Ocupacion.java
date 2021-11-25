package main.java.clases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="disenio.ocupacion")
public class Ocupacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "ingreso", nullable = false, unique = false)
	private LocalDate ingreso;
	
	@Column(name = "egreso", nullable = false, unique = false)
	private LocalDate egreso;
	
	@Column(name = "horayfechasalidareal", nullable = false, unique = false)
	private LocalDateTime horaYFechaSalidaReal;
	
	@Column(name = "precio", nullable = false, unique = false)
	private Double precioPorDia;
	
	@ManyToOne
	@JoinColumn(name = "idhabitacion", referencedColumnName = "id")
	private Habitacion habitacion;
	
	@ManyToMany
	@JoinTable(
			name = "disenio.ocupacionpasajeros",
			joinColumns = @JoinColumn(name = "idocupacion", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "idpasajero", referencedColumnName = "id")
			)	
	private List<Pasajero> pasajeros;
	
	@ManyToOne
	@JoinColumn(name = "idpasajeroresponsable", referencedColumnName = "id")
	private Pasajero responsable;
	
	@OneToMany
	@JoinColumn(name = "idocupacion", referencedColumnName = "id")
	private List<ItemOcupacion> itemsOcupacion;
	//private List<Consumo> consumos;
}
