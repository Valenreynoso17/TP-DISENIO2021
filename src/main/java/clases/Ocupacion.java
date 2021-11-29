package main.java.clases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

import javax.persistence.CascadeType;

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
	
	@Column(name = "horayfechasalidareal", nullable = true, unique = false)
	private LocalDateTime horaYFechaSalidaReal;
	
	@Column(name = "precio", nullable = false, unique = false)
	private Double precioPorDia;
	
	@ManyToOne
	@JoinColumn(name = "idhabitacion", referencedColumnName = "id")
	private Habitacion habitacion;
	
	// TODO falta hacer que se guarden los pasajeros cuando se guarda la ocupacion
	@ManyToMany(cascade =  {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable(
			schema = "disenio",
			name = "ocupacionpasajeros",
			joinColumns = @JoinColumn(name = "idocupacion", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "idpasajero", referencedColumnName = "id")
			)
	private Set<Pasajero> pasajeros;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "idpasajeroresponsable", referencedColumnName = "id")
	private Pasajero responsable;
	
	@OneToMany(mappedBy = "ocupacion")
	private List<ItemOcupacion> itemsOcupacion;
	
	@OneToMany(mappedBy = "ocupacion")
	private List<Consumo> consumos;

	
	
	public Ocupacion(Integer id, LocalDate ingreso, LocalDate egreso, LocalDateTime horaYFechaSalidaReal,
			Double precioPorDia, Habitacion habitacion, Set<Pasajero> pasajeros, Pasajero responsable,
			List<ItemOcupacion> itemsOcupacion, List<Consumo> consumos) {
		super();
		this.id = id;
		this.ingreso = ingreso;
		this.egreso = egreso;
		this.horaYFechaSalidaReal = horaYFechaSalidaReal;
		this.precioPorDia = precioPorDia;
		this.habitacion = habitacion;
		this.pasajeros = pasajeros;
		this.responsable = responsable;
		this.itemsOcupacion = itemsOcupacion;
		this.consumos = consumos;
	}
	
	public Ocupacion() {
		super();	
	}
	
	

	public Integer getId() {
		return id;
	}

	public LocalDate getIngreso() {
		return ingreso;
	}

	public LocalDate getEgreso() {
		return egreso;
	}

	public LocalDateTime getHoraYFechaSalidaReal() {
		return horaYFechaSalidaReal;
	}

	public Double getPrecioPorDia() {
		return precioPorDia;
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public Set<Pasajero> getPasajeros() {
		return pasajeros;
	}

	public Pasajero getResponsable() {
		return responsable;
	}

	public List<ItemOcupacion> getItemsOcupacion() {
		return itemsOcupacion;
	}

	public List<Consumo> getConsumos() {
		return consumos;
	}
	
	

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIngreso(LocalDate ingreso) {
		this.ingreso = ingreso;
	}

	public void setEgreso(LocalDate egreso) {
		this.egreso = egreso;
	}

	public void setHoraYFechaSalidaReal(LocalDateTime horaYFechaSalidaReal) {
		this.horaYFechaSalidaReal = horaYFechaSalidaReal;
	}

	public void setPrecioPorDia(Double precioPorDia) {
		this.precioPorDia = precioPorDia;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	public void setPasajeros(Set<Pasajero> pasajeros) {
		this.pasajeros = pasajeros;
	}

	public void setResponsable(Pasajero responsable) {
		this.responsable = responsable;
	}

	public void setItemsOcupacion(List<ItemOcupacion> itemsOcupacion) {
		this.itemsOcupacion = itemsOcupacion;
	}

	public void setConsumos(List<Consumo> consumos) {
		this.consumos = consumos;
	}
	
	
	
	
	
}
