package main.java.interfaces.CU05;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import main.java.dtos.HabitacionDTO;
import main.java.dtos.ReservaDTO;
import main.java.dtos.TipoHabitacionDTO;
import main.java.enums.EstadoHabitacion;
import main.java.excepciones.ContieneFechasReservadasException;
import main.java.excepciones.PasajeroNoSeleccionadoException;
import main.java.excepciones.RangoNoSeleccionadoException;
import main.java.gestores.GestorHabitacion;
import main.java.interfaces.clasesExtra.ColumnaAgrupada;
import main.java.interfaces.clasesExtra.HeaderTablaAgrupable;
import main.java.interfaces.clasesExtra.ModeloTablaEstadoHabitaciones;
import main.java.interfaces.clasesExtra.RenderParaHeaderTablas;
import main.java.interfaces.clasesExtra.RenderParaTablaEstadoColores;

public class PanelResultadosDeBusquedaHabitacionesGroupBox extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private boolean esPrimero = true;	//Valida que la primera vez que se cargan las columnas, la primera no sea parte de una habitacion (porque es la Fecha)
	
	private JTable tabla;
	private ModeloTablaEstadoHabitaciones miModelo;
	private RenderParaHeaderTablas renderTabla;
	private RenderParaTablaEstadoColores renderTablaEstadoColores;
	
	@SuppressWarnings({ "rawtypes", "unused" })
	private Vector filaSeleccionada = null;
	@SuppressWarnings("unused")
	private Integer nroFilaSeleccionada;
	private JScrollPane tableContainer;
	
	private Insets insetTabla = new Insets(15, 30, 15, 30);
	
	private GestorHabitacion gestorHabitacion;

	private Map<TipoHabitacionDTO, List<HabitacionDTO>> mapHabitacionesTipo;	//Tipos de habitacion (Individual, Suite Double, etc)
	
	private LocalDate fechaDesde;
	private LocalDate fechaHasta;
	
	private PanelMostrarEstadoHabitaciones panelAnterior;
	
	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 18);	
	
	public PanelResultadosDeBusquedaHabitacionesGroupBox(PanelMostrarEstadoHabitaciones panel) {
		
		this.panelAnterior = panel;
		
		gestorHabitacion = GestorHabitacion.getInstance();
		
		this.setBackground(Color.white);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), " Resultados de búsqueda", 0, 0, fuenteGroupBox));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		mapHabitacionesTipo = gestorHabitacion.buscarHabitaciones();
		
		miModelo = new ModeloTablaEstadoHabitaciones(mapHabitacionesTipo);
		
		tabla = new JTable(miModelo){
			
			private static final long serialVersionUID = 1L;
			
	      protected JTableHeader createDefaultTableHeader() {
	          return new HeaderTablaAgrupable(columnModel);
	      }
	  };
	  
		tableContainer = new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		renderTabla = new RenderParaHeaderTablas(tabla.getDefaultRenderer(Object.class), true);

		tabla.getTableHeader().setDefaultRenderer(renderTabla);
		
		TableColumnModel cm = tabla.getColumnModel();
		
	    HeaderTablaAgrupable header = (HeaderTablaAgrupable)tabla.getTableHeader();
		
		Integer tamanioAnterior = 0;
		
		ColumnaAgrupada cA;
		
		for(TipoHabitacionDTO tipo : mapHabitacionesTipo.keySet()) {
			
			cA = new ColumnaAgrupada(tipo.getTipo());
//			
//			if(tipo.getTipo().equals("SUITE DOBLE"))
//				cA = new ColumnaAgrupada("SUITE D.");
			
			for(int i = 0; i <= mapHabitacionesTipo.get(tipo).size(); i++) {
				
				if(esPrimero) {
					i++;
					esPrimero = false;
				}
				
				cA.add(cm.getColumn(i+tamanioAnterior));
			}
			
			tamanioAnterior += mapHabitacionesTipo.get(tipo).size();
		    header.addColumnGroup(cA);
		}
		
		header.getColumnModel().getColumn(4).setPreferredWidth(10);
		
		tabla.getTableHeader().setReorderingAllowed(false); //Para que no se muevan las columnas
		
		tabla.setRowSelectionAllowed(true);
		tabla.setColumnSelectionAllowed(false);

		tabla.setFocusable(false); //Para que no seleccione una sola columna
		
		tabla.getTableHeader().setResizingAllowed(false);	//Para que no puedas cambiar el tamaño de las columnas
		
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tabla.setAutoCreateRowSorter(false);	//Para que NO se ordenen
		
		tabla.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseReleased(MouseEvent e) {
		    	
		    	//Si pulsa el boton derecho dentro de la tabla, se limpia y carga la tabla desde 0 y se agrega otro Render
		    	if(e.isPopupTrigger() && e.getComponent() instanceof JTable) {	
		    		
		    		if(((RenderParaTablaEstadoColores) tabla.getDefaultRenderer(String.class)).celdaYaSeleccionada(tabla.rowAtPoint(e.getPoint()), tabla.columnAtPoint(e.getPoint()))) {
		    			
		    			deseleccionarPeriodo();
		    		}
		    	}    	
		    	
		    	 if (tabla.getSelectedRow() >= 0) {		    
		    		 
				       repaint();
				 } 
		    }
		});
		
		
		tabla.setRowHeight(30);
		cm.getColumn(0).setPreferredWidth(100);
		
	    for (int i = 1; i < cm.getColumnCount(); i++) {
	    	cm.getColumn(i).setPreferredWidth(30);
	    }
//	    
//	    cm.getColumn(cm.getColumnCount()-2).setPreferredWidth(50);
//	    cm.getColumn(cm.getColumnCount()-1).setPreferredWidth(50);

	    
	    tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		tabla.setBackground(Color.white);
		tabla.setGridColor(Color.black);
		tabla.setBorder(new LineBorder(Color.BLACK));
		
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = insetTabla;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		this.add(this.tableContainer, c);
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0.1;
		c.weighty = 0.1;
		c.gridwidth = 1;
		
	}
	
	public void activarTabla(LocalDate fechaDesde, LocalDate fechaHasta) {
		
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		
		renderTablaEstadoColores = new RenderParaTablaEstadoColores(this);
		renderTablaEstadoColores.setHorizontalAlignment( JLabel.CENTER );
		tabla.setDefaultRenderer(String.class, renderTablaEstadoColores);
		
		miModelo.actualizarTabla(fechaDesde, fechaHasta, gestorHabitacion.buscarEstadoHabitaciones(fechaDesde, fechaHasta));
	}
	
	public void desactivarTabla() {
		
		miModelo.limpiarTabla();
	}
	
	public void seleccionoUnRango() throws RangoNoSeleccionadoException{
		
		if(this.renderTablaEstadoColores.getCeldasSeleccionadas().isEmpty()) {	//Si la lista de celdas seleccionadas es vacia, tirar excepcion
			
			throw new RangoNoSeleccionadoException();
		}
	}
	
	public void habitacionConOcupacionOFueraDeServicioHoy(EstadoHabitacion estado){
		
		this.panelAnterior.setTextoMensajeHabitacionConOcupacionOFS(estado);
	}
	
	public void validacionOcupacionActualNoFinalizada(){
		
		
	}
	
	public void validacionContieneFechasReservadas() throws ContieneFechasReservadasException{
		
		//Si CeldasReservadas tiene algun elemento que esta dentro de CeldasSeleccionadas, entonces debe tirar la excepcion y posteriormente el mensaje
		if(!Collections.disjoint(this.renderTablaEstadoColores.getCeldasReservadas(),this.renderTablaEstadoColores.getCeldasSeleccionadas())) {	
			
			List<ReservaDTO> reservasSeleccionadas = new ArrayList<ReservaDTO>();
			List<ArrayList<Integer>> celdasReservadasYSeleccionadas = this.celdasReservadasYSeleccionadas();
			
			Map<Integer, List<ReservaDTO>>  mapReservasPorHabitacion = miModelo.getMapReservasPorHabitacion();
			
			//La columna de la primera celda (la habitacion), menos 1 por como es la tabla
			Integer idHabitacionSeleccionada = miModelo.getHabitaciones().get(celdasReservadasYSeleccionadas.get(0).get(1)-1).getId();	
			
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");		
			
			for(ArrayList<Integer> celda : this.renderTablaEstadoColores.getCeldasSeleccionadas()) {	//Para cada una de las celdas
				
				//Se pasa del string que se muestra en la grilla a un LocalDate, para asi poder usar el map con el metodo "get"
				String fechaCeldaString = miModelo.getValueAt(celda.get(0), 0).toString();
				LocalDate fechaCelda = LocalDate.of(Integer.parseInt(fechaCeldaString.substring(6, 10)), 
													Integer.parseInt(fechaCeldaString.substring(3, 5)), 
													Integer.parseInt(fechaCeldaString.substring(0, 2)));
				
				//Para cada reserva que se encuentra en la lista de reservas de la habitacion seleccionada
				for(ReservaDTO r :mapReservasPorHabitacion.get(idHabitacionSeleccionada)) {
					
					for (LocalDate fecha = r.getIngreso().toLocalDate(); fecha.isBefore(r.getEgreso().toLocalDate()); fecha = fecha.plusDays(1)) {
					
						//Si la fecha contenida en la celda es igual a alguna fecha dentro del periodo de la reserva
						if(fecha.isEqual(fechaCelda)) {
							
							if(!reservasSeleccionadas.contains(r)) {	//Se guarda en la lista de reservasSeleccionadas, si no esta ya contenida en la lista
								
								reservasSeleccionadas.add(r);								
							}
						}
						else if(fecha.isAfter(fechaCelda)) {
							
							break;	//Si ya se estan comparando fechas despues de la fecha en la fila seleccionada, salir del for
						}
					}
				}
			}
			
			throw new ContieneFechasReservadasException(reservasSeleccionadas);
		}
	}
	
	private List<ArrayList<Integer>> celdasReservadasYSeleccionadas(){
		
		//De la lista de celdasSeleccionadas, hay que quitar las que no se encuentren dentro de celdasReservadas (otra opcion es crear una lista nueva)
		
		//Primero, copio la lista de celdasSeleccionadas
		List<ArrayList<Integer>> celdasReservadasYSeleccionadas = this.renderTablaEstadoColores.getCeldasSeleccionadas().stream().collect(Collectors.toList());
		
		for(ArrayList<Integer> celda : this.renderTablaEstadoColores.getCeldasSeleccionadas()) {	//Itero sobre la lista original, porque la otra la voy modificando
			
			if(!this.renderTablaEstadoColores.getCeldasReservadas().contains(celda)) {	//Si no la contiene
				
				celdasReservadasYSeleccionadas.remove(celda);	//Se elimina de la lista de celdasSeleccionadas
			}
		}

		return celdasReservadasYSeleccionadas;
	}

	public void deseleccionarPeriodo() {
		
		miModelo.actualizarTabla(fechaDesde, fechaHasta, gestorHabitacion.buscarEstadoHabitaciones(fechaDesde, fechaHasta));
		renderTablaEstadoColores = new RenderParaTablaEstadoColores(this);
		renderTablaEstadoColores.setHorizontalAlignment( JLabel.CENTER );
		tabla.setDefaultRenderer(String.class, renderTablaEstadoColores);
	}

	public HabitacionDTO getHabitacion() {
		
		return miModelo.getHabitaciones().get(tabla.getSelectedColumn()-1);
	}

	public LocalDate getFechaDesde() {	
		
		Integer diasQueHayQueSumar = renderTablaEstadoColores.getCeldasSeleccionadas().get(0).get(0);	//Fila de la primera celda seleccionada (fecha desde)
		
		return miModelo.getFechaDesde().plusDays(diasQueHayQueSumar);
	}

	public LocalDate getFechaHasta() {

		//Fila de la ultima celda seleccionada (fecha hasta)
		Integer diasQueHayQueSumar = renderTablaEstadoColores.getCeldasSeleccionadas().get(renderTablaEstadoColores.getCeldasSeleccionadas().size()-1).get(0);	
		
		return miModelo.getFechaDesde().plusDays(diasQueHayQueSumar);
	}
	
	public int getCantidadDeFilas() {
		
		return tabla.getRowCount();
	}
}
