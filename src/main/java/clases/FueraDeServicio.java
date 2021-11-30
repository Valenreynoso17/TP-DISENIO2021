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

	
	
	public FueraDeServicio() {
		super();
	}
	
	public FueraDeServicio(Integer id, LocalDateTime fechaInicio, LocalDateTime fechaFin, String descripcion,
			Habitacion habitacion) {
		super();
		this.id = id;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.descripcion = descripcion;
		this.habitacion = habitacion;
	}
	
	

	public Integer getId() {
		return id;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}
	
	

	public void setId(Integer id) {
		this.id = id;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}
}
