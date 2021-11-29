package main.java.interfaces.CU07;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;

import main.java.enums.TipoDocumento;
import main.java.gestores.GestorPasajero;
import main.java.interfaces.TextPrompt;
import main.java.interfaces.CU02.PanelPaginacion;
import main.java.interfaces.MenuPrincipal.FrameMenuPrincipal;
import main.java.interfaces.clasesExtra.FrameMuestraEstadoHabitaciones;
import main.java.interfaces.clasesExtra.ModeloTablaFacturar;
import main.java.interfaces.clasesExtra.ModeloTablaPasajeros;
import main.java.interfaces.clasesExtra.RoundedBorder;

public class PanelResultadosDeBusquedaFacturarGroupBox extends JPanel{
	
	private JButton facturarANombreDeUnTercero;
	
	private JTable tabla;
	private ModeloTablaFacturar miModelo;
	private PanelPaginacion paginacion;
	
	private Vector filaSeleccionada = null;
	private Integer nroFilaSeleccionada;
	private JScrollPane tableContainer;
	
	private Insets insetTabla = new Insets(15, 100, 15, 100);

	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 18);	
	private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 14);
	
	private RoundedBorder bordeBoton = new RoundedBorder(10, Color.decode("#BDBDBD"));
	
	private Insets insetPanelBusqueda = new Insets(30,30,5,30);
	private Insets insetPanelTabla = new Insets(0,30,0,30);
	
	private FrameMenuPrincipal frameAnterior;
	private FrameFacturar frameActual;
	
	private FrameFacturarANombreDeUnTercero frameFacturarANombreDeUnTercero;
	
	private Dimension dimensionBoton = new Dimension(250, 33);
	
	private GestorPasajero gestorPasajero;
	
	final private Integer tamPagina = 10;
	
	public PanelResultadosDeBusquedaFacturarGroupBox(FrameFacturar frame) {
		
		this.frameActual = frame;
		
		this.setBackground(Color.white);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), " Resultados de búsqueda", 0, 0, fuenteGroupBox));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		miModelo = new ModeloTablaFacturar();
		
		tabla = new JTable(miModelo);
		tableContainer = new JScrollPane(tabla);
		
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
			}
		});
		
		Object[] prueba = {"Perez", "Juan", TipoDocumento.DNI, "32333444", LocalDate.now()};
		
		miModelo.addRow(prueba);	//TODO: Borrar
		
		//PARA CENTRAR
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		tabla.setDefaultRenderer(Object.class, centerRenderer);
		
		tabla.setBackground(Color.white);
		tabla.setGridColor(Color.white);
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
			frameFacturarANombreDeUnTercero = new FrameFacturarANombreDeUnTercero(frame);
		});
		c.anchor = GridBagConstraints.CENTER;		//c.insets = new Insets(0,60,10,0);
		c.gridy = 1;
		this.add(facturarANombreDeUnTercero, c);
		
		
	}
}
