package main.java.reportes.reportes;

import javax.swing.WindowConstants;

import main.java.clases.Factura;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class CrearFactura {
public CrearFactura() {
	try {
		JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("Factura.jasper"));
		JasperPrint jprint = JasperFillManager.fillReport(report, null, DatosFacturacion.getDataSource());
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