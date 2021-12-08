package main.java.reportes.reportes;


import java.util.ArrayList;
import java.util.List;

import main.java.clases.Factura;
import main.java.clases.ItemFactura;
import main.java.daos.FacturaDAO;
import main.java.dtos.ItemFacturaImpresionDTO;
import main.java.postgreImpl.FacturaPostgreSQLImpl;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRField;

public class DatosFacturacion implements JRDataSource {
	
	Integer index =-1;
	FacturaDAO dao = new FacturaPostgreSQLImpl();
	Factura factura = dao.buscarConItems(2);
	List<ItemFacturaImpresionDTO> items;
	
	public DatosFacturacion() {
		items = new ArrayList<>();
		for (ItemFactura i : factura.getItems()) {
			items.add(new ItemFacturaImpresionDTO(i));
		}
	}
	
	
	
	@Override
	public Object getFieldValue(JRField jrField) throws JRException{
		Object value = null;
		String fieldName = jrField.getName();
		System.out.println(fieldName);
		switch(fieldName) {
		case "tipoFactura":
			value = factura.getTipo().toString();
			break;
		case "razonSocial":
			value = factura.getDatosResponsable().getRazonSocial();
			break;
		case "cuit":
			value = factura.getDatosResponsable().getCuit();
			break;
		case "direccion":
			value = factura.getDatosResponsable().getDireccion().getDireccionDomicilio();
			break;
		case "localidad":
			value = factura.getDatosResponsable().getDireccion().getLocalidad().getNombre();
			break;
		case "posicionFrenteIVA":
			value = factura.getDatosResponsable().getPosicionFrenteIva().toString();
			break;
		case "nroFactura":
			value = factura.getNumero();
			break;
		case "montoNeto":
			value = factura.getMontoNeto();
			break;
		case "IVA":
			value = Factura.getIVA()*factura.getMontoTotal();
			break;
		case "montoTotal":
			value = factura.getMontoTotal();
			break;
		case "items":
			value = items;
			break;
		}
		return value;
	}
	public static JRDataSource getDataSource() {
		return new DatosFacturacion();
	}
	@Override
	public boolean next() throws JRException {
		index++;
		return (index<factura.getItems().size());
	}


}
