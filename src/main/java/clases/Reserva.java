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

	
	
	public Reserva() {
		super();
	}
	
	public Reserva(Integer id, LocalDateTime ingreso, LocalDateTime egreso, String nombre, String apellido,
			String telefono, Habitacion habitacion) {
		super();
		this.id = id;
		this.ingreso = ingreso;
		this.egreso = egreso;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.habitacion = habitacion;
	}
	
	

	public Integer getId() {
		return id;
	}

	public LocalDateTime getIngreso() {
		return ingreso;
	}

	public LocalDateTime getEgreso() {
		return egreso;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}
	
	

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIngreso(LocalDateTime ingreso) {
		this.ingreso = ingreso;
	}

	public void setEgreso(LocalDateTime egreso) {
		this.egreso = egreso;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}
}
