package main.java.interfaces.CU07;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SortOrder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import main.java.dtos.OcupacionDTO;
import main.java.dtos.PasajeroDTO;
import main.java.enums.ColumnaBuscarPasajeros;
import main.java.enums.TipoDocumento;
import main.java.excepciones.PasajeroNoSeleccionadoException;
import main.java.gestores.GestorPasajero;
import main.java.interfaces.clasesExtra.ModeloTablaFacturar;
import main.java.interfaces.clasesExtra.RenderParaTablas;
import main.java.interfaces.clasesExtra.RoundedBorder;

public class PanelResultadosDeBusquedaFacturarGroupBox extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JButton facturarANombreDeUnTercero;
	
	private JTable tabla;
	private ModeloTablaFacturar miModelo;
	
	@SuppressWarnings({ "rawtypes", "unused" })
	private Vector filaSeleccionada = null;
	@SuppressWarnings("unused")
	private Integer nroFilaSeleccionada;
	private JScrollPane tableContainer;
	
	private Insets insetTabla = new Insets(15, 100, 15, 100);

	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 18);	
	private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 14);
	
	private RoundedBorder bordeBoton = new RoundedBorder(10, Color.decode("#BDBDBD"));
	
	private FrameFacturar frameActual;
	
	private Dimension dimensionBoton = new Dimension(250, 33);

	public PanelResultadosDeBusquedaFacturarGroupBox(FrameFacturar frame) {
		
		this.frameActual = frame;
		
		this.setBackground(Color.white);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), " Resultados de búsqueda", 0, 0, fuenteGroupBox));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		miModelo = new ModeloTablaFacturar();
		
		//miModelo.cargarPasajeros(ocupacionDTO.getListaPasajerosDTO());
		
		tabla = new JTable(miModelo);
		tableContainer = new JScrollPane(tabla);
		
		RenderParaTablas a = new RenderParaTablas(tabla.getDefaultRenderer(Object.class), false);
		
		tabla.setDefaultRenderer(Object.class, a);
		tabla.getTableHeader().setDefaultRenderer(a);
		
		tabla.getTableHeader().setReorderingAllowed(false); //Para que no se muevan las columnas
		
		tabla.setRowSelectionAllowed(true);
		tabla.setColumnSelectionAllowed(false);
		
		tabla.setFocusable(false); //Para que no seleccione una sola columna
		
		tabla.getTableHeader().setResizingAllowed(false);	//Para que no puedas cambiar el tamaño de las columnas
		
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tabla.setAutoCreateRowSorter(true);	//Para que se ordenen
		
		tabla.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {				
				filaSeleccionada = miModelo.getDataVector().elementAt(tabla.getSelectedRow());
				nroFilaSeleccionada = tabla.getSelectedRow();
				
				System.out.println("Fila: "+tabla.getSelectedRow()+" nombre: "+tabla.getValueAt(tabla.getSelectedRow(), 1));
			}
		});

//		Object[] prueba = {"Perez", "Juan", TipoDocumento.DNI, "32333444", LocalDate.now()};	miModelo.addRow(prueba);	//TODO: Borrar
//		Object[] prueba1 = {"Gomez", "Pedro", TipoDocumento.DNI, "5435634634", LocalDate.now()};	miModelo.addRow(prueba1);	//TODO: Borrar
//		Object[] prueba2 = {"Pereira", "María", TipoDocumento.DNI, "12412444", LocalDate.now()};	miModelo.addRow(prueba2);	//TODO: Borrar
		
		//PARA CENTRAR
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		tabla.setDefaultRenderer(Object.class, centerRenderer);
		
		tabla.setBackground(Color.white);
		tabla.setGridColor(Color.black);
		tabla.setBorder(new LineBorder(Color.BLACK));
		
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
		
		facturarANombreDeUnTercero = new JButton("Facturar a nombre de un tercero");
		facturarANombreDeUnTercero.setMinimumSize(dimensionBoton);
		facturarANombreDeUnTercero.setPreferredSize(dimensionBoton);
		facturarANombreDeUnTercero.setBackground(Color.decode("#E0E0E0"));
		facturarANombreDeUnTercero.setFont(fuenteBoton);
		facturarANombreDeUnTercero.setBorder(bordeBoton);
		facturarANombreDeUnTercero.addActionListener(e -> {
			
			frameActual.setEnabled(false);
			//new FrameFacturarANombreDeUnTercero(frame, ocupacionDTO); 
		});
		c.anchor = GridBagConstraints.CENTER;		//c.insets = new Insets(0,60,10,0);
		c.gridy = 1;
		this.add(facturarANombreDeUnTercero, c);
		
		
	}
	
		public void seleccionoUnPasajero() throws PasajeroNoSeleccionadoException{
		
			if(tabla.getSelectedRow() < 0) {
			
				throw new PasajeroNoSeleccionadoException();
		}
	}
		
		public void ocupacionSeleccionada(OcupacionDTO ocupacionDTO) {
			
			miModelo.cargarPasajeros(ocupacionDTO.getListaPasajerosDTO());
		}
}
