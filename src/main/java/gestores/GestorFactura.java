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
import main.java.excepciones.RecargoNoEstaEnUltimaFacturaException;
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
	private Boolean seFacturaronTodosLosItems;
	private Boolean tieneRecargo;
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
	
	public void crearFactura(FacturaDTO facturaDTO, OcupacionDTO ocupacionDTO) throws NingunElementoSeleccionadoFacturacionException,
																					  RecargoNoEstaEnUltimaFacturaException {
		
		seFacturaronTodosLosItems = false;
		tieneRecargo = false;
		
		Ocupacion ocupacion = ocupacionDAO.buscarExtendido(ocupacionDTO.getId());
		List<ItemFactura> listaItemsFactura = crearListaItemsFactura(facturaDTO.getListaItemsFila(), ocupacion);
		
		if(listaItemsFactura.isEmpty()) {
			throw new NingunElementoSeleccionadoFacturacionException();
		}
		
		/* Si selecciono para facturar un recargo pero hay otros items por facturar no se debería permitir,
		 ya que los recargos son lo último que se deben facturar (deben estar en la ultima factura)*/
		if(tieneRecargo && !seFacturaronTodosLosItems) {
			throw new RecargoNoEstaEnUltimaFacturaException();
		}
		
		if(seFacturaronTodosLosItems) {
			ocupacion.setHoraYFechaSalidaReal(ocupacionDTO.getPosibleFechaHoraDeSalida());
			ocupacionDAO.guardar(ocupacion);
		}
		
		Habitacion habitacion = ocupacion.getHabitacion();
		
		ResponsableDePago responsablePago = responsableDAO.buscar(facturaDTO.getResponsablePagoDTO().getId());
		
		DatosResponsableDePago datosResponsable = new DatosResponsableDePago(responsablePago);
		
		Factura factura = new Factura(facturaDTO, habitacion, responsablePago, datosResponsable, listaItemsFactura);
		
		facturaDAO.guardar(factura);
		
		imprimir(factura);
	}
	
	public List<ItemFactura> crearListaItemsFactura(List<ItemFilaDTO> listaItems, Ocupacion ocupacion){
		
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
				
				if(unItem.esItemOcupacion()) {
					
					if(unItem.esRecargo()) {
						tieneRecargo = true;
					}
					
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
	
	public void imprimir(Factura factura) {
		System.out.println("Impresion de factura");
	}
}
