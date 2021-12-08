package main.java.reportes.reportes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.WindowConstants;

import main.java.clases.Factura;
import main.java.clases.ItemFactura;
import main.java.daos.FacturaDAO;
import main.java.dtos.ItemFacturaDTO;
import main.java.dtos.ItemFacturaImpresionDTO;
import main.java.enums.PosicionFrenteIva;
import main.java.postgreImpl.FacturaPostgreSQLImpl;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class CrearFactura {
public CrearFactura() {
	try {
		FacturaDAO dao = new FacturaPostgreSQLImpl();
		Factura f = dao.buscarConItems(2);
		
		//Map<String, Object> parameters = new HashMap<>();
		List<ItemFacturaImpresionDTO> itemsDTO = new ArrayList<>();
		
		for (ItemFactura i : f.getItems()) {
			itemsDTO.add(new ItemFacturaImpresionDTO(i));
			
			
		}
		
		JRBeanCollectionDataSource items = new JRBeanCollectionDataSource(itemsDTO);
		//List<ItemFactura> items = new ArrayList<>(f.getItems());
		
		Map<String, Object> parameters = new HashMap<>();
		
		parameters.put("tipoFactura", f.getTipo().toString());
		parameters.put("razonSocial", f.getDatosResponsable().getRazonSocial());
		parameters.put("posicionIva", f.getDatosResponsable().getPosicionFrenteIva().toString());
		parameters.put("direccion", f.getDatosResponsable().getDireccion().getDireccionDomicilio());
		parameters.put("cuit", f.getDatosResponsable().getCuit());
		parameters.put("numero", f.getNumero());
		parameters.put("localidad", f.getDatosResponsable().getDireccion().getLocalidad().getNombre());
		parameters.put("montoNeto", f.getMontoNeto());
		parameters.put("montoTotal", f.getMontoTotal());
		parameters.put("iva", Factura.getIVA()*f.getMontoTotal());
		parameters.put("items", items);
		
		
		JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("Factura.jasper"));
		JasperPrint jprint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
		JasperViewer view = new JasperViewer(jprint, false);
		view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		view.setVisible(true);
	}
	catch(Exception ex) {
		ex.printStackTrace();
	}
}




public static void main (String[] args) {
	CrearFactura jasper = new CrearFactura();
}}