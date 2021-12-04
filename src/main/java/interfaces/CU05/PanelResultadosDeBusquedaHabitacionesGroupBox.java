package main.java.interfaces.CU05;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.*;

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
import main.java.dtos.TipoHabitacionDTO;
import main.java.excepciones.ContieneFechasReservadasException;
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
	
	private Insets insetTabla = new Insets(15, 100, 15, 100);
	
	private GestorHabitacion gestorHabitacion;

	private Map<TipoHabitacionDTO, List<HabitacionDTO>> mapHabitacionesTipo;
	
	private LocalDate fechaDesde;
	private LocalDate fechaHasta;
	
	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 18);	
	public PanelResultadosDeBusquedaHabitacionesGroupBox() {
		
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
		
		for(TipoHabitacionDTO tipo : mapHabitacionesTipo.keySet()) {
			
			ColumnaAgrupada cA = new ColumnaAgrupada(tipo.getTipo());
			
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
		    	
		    	if(e.isPopupTrigger() && e.getComponent() instanceof JTable) {	//Si pulsa el boton derecho dentro de la tabla, se limpia y carga la tabla desde 0 y se agrega otro Render
		    		
		    		if(((RenderParaTablaEstadoColores) tabla.getDefaultRenderer(String.class)).celdaYaSeleccionada(tabla.rowAtPoint(e.getPoint()), tabla.columnAtPoint(e.getPoint()))) {
		    			
		    			actualizarTabla();
		    			renderTablaEstadoColores = new RenderParaTablaEstadoColores();
		    			renderTablaEstadoColores.setHorizontalAlignment( JLabel.CENTER );
		    			tabla.setDefaultRenderer(String.class, renderTablaEstadoColores);
		    		}
		    	}
//
//		        if (tabla.getSelectedRow() >= 0) {
//		        	
//				    int r = tabla.rowAtPoint(e.getPoint());
//			        if (r >= 0 && r < tabla.getRowCount()) {
//			        	try {
//							filaSeleccionada = miModelo.getDataVector().elementAt(tabla.getSelectedRow());
//							nroFilaSeleccionada = tabla.getSelectedRow();
//			        	} catch(ArrayIndexOutOfBoundsException exc) {		//El "elementAt" fallta debido a que el click derecho busca el elemento -1 en el vector
//			        		
//			        		System.out.println("Click derecho por excepcion");
//			        		//tabla.setDefaultRenderer(String.class, new RenderParaTablaEstadoColores());	//Quizas mas ineficiente, pero mas simple
//			        	}
//			        } else {
//			        	tabla.clearSelection();
//			        }
//		       }
//
		    }
		});
		
		
		tabla.setRowHeight(30);
		cm.getColumn(0).setPreferredWidth(100);
		
	    for (int i = 1; i < cm.getColumnCount(); i++) {
	    	cm.getColumn(i).setPreferredWidth(30);
	    }
	    
	    tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		//PARA CENTRAR
//		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
//		tabla.setDefaultRenderer(Object.class, centerRenderer);
		
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
		
		renderTablaEstadoColores = new RenderParaTablaEstadoColores();
		renderTablaEstadoColores.setHorizontalAlignment( JLabel.CENTER );
		tabla.setDefaultRenderer(String.class, renderTablaEstadoColores);
		
		miModelo.actualizarTabla(gestorHabitacion.buscarEstadoHabitaciones(fechaDesde, fechaHasta));
	}
	
	public void desactivarTabla() {
		
		miModelo.limpiarTabla();
	}
	
	public void seleccionoUnRango() throws RangoNoSeleccionadoException{
		
		if(this.renderTablaEstadoColores.getCeldasSeleccionadas().isEmpty()) {	//Si la lista de celdas seleccionadas es vacia, tirar excepcion
			
			throw new RangoNoSeleccionadoException();
		}
	}
	
	public void validacionContieneFechasReservadas() throws ContieneFechasReservadasException{
		
		//Si CeldasReservadas tiene algun elemento que esta dentro de CeldasSeleccionadas, entonces debe tirar la excepcion y posteriormente el mensaje
		if(!Collections.disjoint(this.renderTablaEstadoColores.getCeldasReservadas(),this.renderTablaEstadoColores.getCeldasSeleccionadas())) {	
			
			throw new ContieneFechasReservadasException();
		}
	}

	public void deseleccionarPeriodo() {
		
		actualizarTabla();
		renderTablaEstadoColores = new RenderParaTablaEstadoColores();
		renderTablaEstadoColores.setHorizontalAlignment( JLabel.CENTER );
		tabla.setDefaultRenderer(String.class, renderTablaEstadoColores);
	}
	

		
	public void actualizarTabla() {
		
		miModelo.limpiarTabla();
		miModelo.cargarEstados(gestorHabitacion.buscarEstadoHabitaciones(fechaDesde, fechaHasta));
	}
}
