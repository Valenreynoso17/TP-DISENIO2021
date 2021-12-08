package main.java.interfaces.CU17;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SortOrder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import main.java.dtos.PasajeroDTO;
import main.java.enums.ColumnaBuscarPasajeros;
import main.java.excepciones.PasajeroNoSeleccionadoException;
import main.java.gestores.GestorPasajero;
import main.java.interfaces.clasesExtra.ModeloTablaPasajeros;
import main.java.interfaces.clasesExtra.RenderParaHeaderTablas;
import main.java.interfaces.clasesExtra.RenderParaTablaEstadoColores;
import main.java.interfaces.clasesExtra.RenderParaTablaOcuparHabitacion;

public class PanelOcuparHabitacionTabla extends JPanel implements Paginable{
	
	private static final long serialVersionUID = 1L;

	private JTable tabla;
	private ModeloTablaPasajeros miModelo;
	private PanelPaginacion paginacion;
	private RenderParaHeaderTablas renderTabla;
	
	@SuppressWarnings("rawtypes")
	private Vector filaSeleccionada = null;
	private Integer nroFilaSeleccionada;
	private JScrollPane tableContainer;
	
	private Insets insetTabla = new Insets(10,10,0,10);
	
	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 18);	
	
	private PasajeroDTO filtros;
	private Integer cantResultados;
	private Integer paginaActual;
	private ColumnaBuscarPasajeros columnaFiltro;
	private SortOrder orden;
	private List<PasajeroDTO> ultimosResultados;
	
	private GestorPasajero gestorPasajero;
	
	final private Integer tamPagina = 10;
	
	//private RoundedBorder bordeCampo = new RoundedBorder(5, Color.decode("#BDBDBD"));
	
	//Predicate<Pasajero> FiltroApellido, FiltroNombre, FiltroTipoDocumento, FiltroNumeroDocumento;
	
	public PanelOcuparHabitacionTabla(PanelPasajerosSeleccionadosGroupBox panelPasajerosSeleccionados) {
		paginaActual = 1;
		cantResultados = 0;
		
		gestorPasajero = GestorPasajero.getInstance();
		
		columnaFiltro = ColumnaBuscarPasajeros.NOMBRE;
		orden = SortOrder.ASCENDING;
		
		this.setBackground(Color.WHITE);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), "Resultados de búsqueda", 0, 0, fuenteGroupBox));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		miModelo = new ModeloTablaPasajeros();
		
		tabla = new JTable(miModelo);
		tableContainer = new JScrollPane(tabla);
		
		tabla.setSelectionBackground(Color.decode("#e0e0e0"));
		
		renderTabla = new RenderParaHeaderTablas(tabla.getDefaultRenderer(Object.class), false);
		
		tabla.getTableHeader().setDefaultRenderer(renderTabla);
		
		tabla.getTableHeader().setReorderingAllowed(false); //Para que no se muevan las columnas
		
		tabla.setRowSelectionAllowed(true);
		tabla.setColumnSelectionAllowed(false);
		
		tabla.setFocusable(false); //Para que no seleccione una sola columna
		
		tabla.getTableHeader().setResizingAllowed(false);	//Para que no puedas cambiar el tamaño de las columnas
		
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tabla.setAutoCreateRowSorter(true);	//Para que se ordenen
		
		tabla.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseReleased(MouseEvent e) {

		        if (tabla.getSelectedRow() >= 0) {
		        	
				    int r = tabla.rowAtPoint(e.getPoint());
			        if (r >= 0 && r < tabla.getRowCount()) {
			        	try {
			        		
							filaSeleccionada = miModelo.getDataVector().elementAt(tabla.getSelectedRow());
							nroFilaSeleccionada = tabla.getSelectedRow();
							
							//Cuando selecciona a alguien, se copia tambien en la otra tabla pero NO desaparece de ésta
							panelPasajerosSeleccionados.seleccionaronPasajero(pasajeroSeleccionado());
							
			        	} catch(ArrayIndexOutOfBoundsException exc) {		//El "elementAt" fallta debido a que el click derecho busca el elemento -1 en el vector
			        		
			        		exc.printStackTrace();
			        	} catch(PasajeroNoSeleccionadoException exc) {
			        		
			        		exc.printStackTrace();
			        	}
			        } else {
			        	tabla.clearSelection();
			        }
		       }

		    }
		});
		
		tabla.getTableHeader().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int col = tabla.columnAtPoint(e.getPoint());	
				
				switch (col) {
				case 0:
					columnaFiltro = ColumnaBuscarPasajeros.APELLIDO;
					break;
				case 1:
					columnaFiltro = ColumnaBuscarPasajeros.NOMBRE;
					break;
				case 2:
					columnaFiltro = ColumnaBuscarPasajeros.TIPO_DOCUMENTO;
					break;
				case 3:
					columnaFiltro = ColumnaBuscarPasajeros.NUMERO_DOCUMENTO;
				}
				
				orden = tabla.getRowSorter().getSortKeys().get(0).getSortOrder();
				
				if (filtros != null) {
					actualizarTabla();
				}						
				
			}
			
		});		
		
		//PARA CENTRAR
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		tabla.setDefaultRenderer(Object.class, centerRenderer);
		
		tabla.setBackground(Color.white);
		tabla.setGridColor(Color.black);
		tabla.setBorder(new LineBorder(Color.BLACK));
		
		tabla.setRowHeight(17);	//Para que ocupe mejor el espacio de la tabla
		
		//tabla.setPreferredScrollableViewportSize(tabla.getPreferredSize());	//TODO: Ver si se puede arreglar, en el peor de los casos quedara asi
		
		//tabla.setvi
		
		//this.add(tableContainer, BorderLayout.CENTER);
		c.fill = GridBagConstraints.BOTH;
		//c.anchor = GridBagConstraints.CENTER;
		c.insets = insetTabla;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		this.add(tableContainer, c);
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0.1;
		c.weighty = 0.1;
		c.gridwidth = 1;
		
			c.anchor = GridBagConstraints.CENTER; c.insets = new Insets(0,0,0,0);
		
		//label = new JLabel("PAGINACIÓN");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 1;	this.add(label, c);
		paginacion = new PanelPaginacion(this, tamPagina);	c.gridx = 0;	c.gridy = 1;
		this.add(paginacion, c);
		
		paginacion.refrescarCantidadResultados(cantResultados, paginaActual);
		
		
	}	
	
	public void buscarResultados(PasajeroDTO filtros, Integer cantResultados) {
		this.filtros = filtros;
		this.cantResultados = cantResultados;
		
		if (cantResultados / ((double) tamPagina) < paginaActual) paginaActual = 1;
		
		actualizarTabla();
		
		paginacion.refrescarCantidadResultados(cantResultados, paginaActual);
	}
	
	// No se usa de momento
	public void reordenar(ColumnaBuscarPasajeros columnaFiltro, SortOrder orden) {
		this.columnaFiltro = columnaFiltro;
		this.orden = orden;
		
		actualizarTabla();
	}
	
	public void actualizarTabla() {
		ultimosResultados = gestorPasajero.buscarPaginado(filtros, tamPagina, paginaActual, columnaFiltro, orden);
		
		miModelo.limpiarTabla();
		miModelo.cargarPasajeros(ultimosResultados);
		
	}
	
	// TODO queda corroborar que anda bien una vez que la paginacion este hecha
	public PasajeroDTO pasajeroSeleccionado() throws PasajeroNoSeleccionadoException {
		Integer indice = tabla.getSelectedRow();
		
		if (indice < 0) throw new PasajeroNoSeleccionadoException();
		return ultimosResultados.get(tabla.getSelectedRow());
	}


	@Override
	public void cambiarPagina(Integer pagina) {
		this.paginaActual = pagina;
		paginacion.refrescarBotones(pagina);
		actualizarTabla();
		
	}
}
	
	
