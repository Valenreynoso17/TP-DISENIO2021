package main.java.clases;

import java.time.LocalDateTime;

import javax.persistence.*;


@Entity
@Table(name="disenio.reserva")
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "ingreso", nullable = false, unique = false)
	private LocalDateTime ingreso;
	
	@Column(name = "egreso", nullable = false, unique = false)
	private LocalDateTime egreso;
	
	@Column(name = "nombre", nullable = false, unique = false)
	private String nombre;
	
	@Column(name = "apellido", nullable = false, unique = false)
	private String apellido;
	
	@Column(name = "telefono", nullable = false, unique = false)
	private String telefono;
	
	@ManyToOne
	@JoinColumn(name = "idhabitacion", referencedColumnName = "id")
	private Habitacion habitacion;
}
