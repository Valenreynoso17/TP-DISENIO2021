package main.java.clases;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name="disenio.fueradeservicio")
public class FueraDeServicio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "fechainicio", nullable = false, unique = false)
	private LocalDateTime fechaInicio;
	
	@Column(name = "fechafin", nullable = false, unique = false)
	private LocalDateTime fechaFin;
	
	@Column(name = "descripcion", nullable = false, unique = false)
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "idhabitacion", referencedColumnName = "id")
	private Habitacion habitacion;
}
