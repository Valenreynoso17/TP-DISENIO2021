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
import main.java.excepciones.NingunElementoSeleccionadoFacturacionException;
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
	
	public void crearFactura(FacturaDTO facturaDTO, OcupacionDTO ocupacionDTO) throws NingunElementoSeleccionadoFacturacionException {
		
		Boolean seFacturaronTodosLosItems = false;
		
		Ocupacion ocupacion = ocupacionDAO.buscar(ocupacionDTO.getId());
		List<ItemFactura> listaItemsFactura = crearListaItemsFactura(facturaDTO.getListaItemsFila(), ocupacion, seFacturaronTodosLosItems);
		
		if(listaItemsFactura.isEmpty()) {
			throw new NingunElementoSeleccionadoFacturacionException();
		}
		
		if(seFacturaronTodosLosItems) {
			ocupacion.setHoraYFechaSalidaReal(ocupacionDTO.getPosibleFechaHoraDeSalida());
			ocupacionDAO.guardar(ocupacion);
		}
		
		Habitacion habitacion = ocupacion.getHabitacion();
		ResponsableDePago responsablePago = responsableDAO.buscar(facturaDTO.getResponsablePagoDTO().getId());
		
		// Lo necesito crear? No existiria ya en la BD?
		DatosResponsableDePago datosResponsable = new DatosResponsableDePago(responsablePago);
		
		Factura factura = new Factura(facturaDTO, habitacion, responsablePago, datosResponsable, listaItemsFactura);
		
		facturaDAO.guardar(factura);
		// faltaria la parte de imprimir
	}
	
	public List<ItemFactura> crearListaItemsFactura(List<ItemFilaDTO> listaItems, Ocupacion ocupacion, Boolean seFacturaronTodosLosItems){
		
		List<ItemFactura> retorno = new ArrayList<ItemFactura>();
		Integer cantCosasAFacturar = 0;
		Integer cantCosasFacturadas = 0;
		Integer cantSeleccionadaEnItem;
		
		for(ItemFilaDTO unItem : listaItems) {
			
			cantCosasAFacturar += unItem.getCantidadMax();
			
			cantSeleccionadaEnItem = unItem.getCantidadSeleccionada();
			
			if(cantSeleccionadaEnItem > 0) {
				
				cantCosasFacturadas += cantSeleccionadaEnItem;
			
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
		
		/* Significa que facturó todos los items que tenia pendiente, la ocupacion debería darse por terminada
		 y se debería setar la fecha y hora final*/
		if (cantCosasAFacturar == cantCosasFacturadas) {
			seFacturaronTodosLosItems = true;
		}
		
		return retorno;
	}
}
