package main.java.gestores;

import java.util.ArrayList;
import java.util.List;

import main.java.clases.Consumo;
import main.java.clases.DatosResponsableDePago;
import main.java.clases.Factura;
import main.java.clases.Habitacion;
import main.java.clases.ItemConsumo;
import main.java.clases.ItemFactura;
import main.java.clases.ItemOcupacion;
import main.java.clases.Ocupacion;
import main.java.clases.ResponsableDePago;
import main.java.daos.FacturaDAO;
import main.java.daos.HabitacionDAO;
import main.java.daos.OcupacionDAO;
import main.java.daos.ResponsableDePagoDAO;
import main.java.dtos.FacturaDTO;
import main.java.dtos.ItemFilaDTO;
import main.java.dtos.OcupacionDTO;
import main.java.postgreImpl.FacturaPostgreSQLImpl;
import main.java.postgreImpl.HabitacionPostgreSQLImpl;
import main.java.postgreImpl.OcupacionPostgreSQLImpl;
import main.java.postgreImpl.ResponsableDePagoPostgreSQLImpl;

public class GestorFactura {
private static GestorFactura instance;
	
	private FacturaDAO facturaDAO;
	private ResponsableDePagoDAO responsableDAO;
	private HabitacionDAO habitacionDAO;
	private OcupacionDAO ocupacionDAO;
	//private ConsumoDAO consumoDAO;
	
	private GestorFactura() {
		facturaDAO = new FacturaPostgreSQLImpl();
		responsableDAO = new ResponsableDePagoPostgreSQLImpl();
		habitacionDAO = new HabitacionPostgreSQLImpl();
		ocupacionDAO = new OcupacionPostgreSQLImpl();
	}
	
	public static GestorFactura getInstance() {
		if (instance == null) instance = new GestorFactura();
		
		return instance;
	}
	
	public void crearFactura(FacturaDTO facturaDTO, OcupacionDTO ocupacionDTO, List<ItemFilaDTO> listaItems) {
		
		Ocupacion ocupacion = ocupacionDAO.buscar(ocupacionDTO.getId());
		Habitacion habitacion = ocupacion.getHabitacion();
		ResponsableDePago responsablePago = responsableDAO.buscar(facturaDTO.getResponsablePagoDTO().getId());
		DatosResponsableDePago datosResponsable = new DatosResponsableDePago(responsablePago);
		
		List<ItemFactura> listaItemsFactura = crearListaItemsFactura(listaItems, ocupacion);
		
		Factura factura = new Factura(facturaDTO, habitacion, responsablePago, datosResponsable, listaItemsFactura);
		
		facturaDAO.guardar(factura);
		// faltaria la parte de imprimir
	}
	
	public List<ItemFactura> crearListaItemsFactura(List<ItemFilaDTO> listaItems, Ocupacion ocupacion){
		
		List<ItemFactura> retorno = new ArrayList<ItemFactura>();
		
		for(ItemFilaDTO unItem : listaItems) {
			
			if(unItem.getCantidadSeleccionada() > 0) {
			
				ItemFactura itemFactura;
				
				if(unItem.getEsItemOcupacion()) {
					
					itemFactura = new ItemOcupacion(unItem, ocupacion);
					
				} else {
					
					Consumo consumo = ocupacion.getConsumos().stream().filter(c -> c.getId() == unItem.getIdAsociado()).findFirst().get();
					
					itemFactura = new ItemConsumo(unItem, consumo);
					
				}
				
				retorno.add(itemFactura);
			}
		}
		
		// Habria que ver que pasa si el usuario no selecciono ningun objeto		
		return retorno;
	}
}
