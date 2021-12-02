package main.java.clases;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="disenio.itemfactura")
public abstract class ItemFactura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	@Column(name = "preciounitario", nullable = false, unique = false)
	protected Double precioUnitario;
	
	@Column(name = "descripcion", nullable = false, unique = false)
	protected String descripcion;

	public ItemFactura() {
		super();
	}
	
	public ItemFactura(Integer id, Double precioUnitario, String descripcion) {
		super();
		this.id = id;
		this.precioUnitario = precioUnitario;
		this.descripcion = descripcion;
		//this.factura = factura;
	}
	
	public ItemFactura(Double precioUnitario, String descripcion) {
		super();
		this.precioUnitario = precioUnitario;
		this.descripcion = descripcion;
		//this.factura = factura;
	}



	public Integer getId() {
		return id;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}
	
	public String getDescripcion() {
		return descripcion;
	}



	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((precioUnitario == null) ? 0 : precioUnitario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemFactura other = (ItemFactura) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (precioUnitario == null) {
			if (other.precioUnitario != null)
				return false;
		} else if (!precioUnitario.equals(other.precioUnitario))
			return false;
		return true;
	}
	
	
}
