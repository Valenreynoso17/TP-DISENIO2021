package main.java.clases;

import javax.persistence.*;

@Entity
@Table(name="disenio.itemocupacion")
public class ItemOcupacion extends ItemFactura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "cantidaddias", nullable = false, unique = false)
	private Integer cantidadDias;
	
	@ManyToOne()
	@JoinColumn(name = "idocupacion", referencedColumnName = "id")
	private Ocupacion ocupacion;
}
