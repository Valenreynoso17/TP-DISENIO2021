package main.java.clases;

import javax.persistence.*;

@Entity
@Table(name="disenio.responsabledepago")
public class ResponsableDePago {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "razonSocial")
	private String razonSocial;
	
	
	public ResponsableDePago() {
		super();
	}

	public ResponsableDePago(Integer id, String razonSocial) {
		super();
		this.id = id;
		this.razonSocial = razonSocial;
	}
	
	

	public Integer getId() {
		return id;
	}

	public String getRazonSocial() {
		return razonSocial;
	}
	
	

	public void setId(Integer id) {
		this.id = id;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	
	
	
}
