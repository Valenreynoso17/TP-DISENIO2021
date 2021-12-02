package main.java.interfaces.CU05;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import main.java.interfaces.clasesExtra.ColumnaAgrupada;
import main.java.interfaces.clasesExtra.HeaderTablaAgrupable;
import main.java.interfaces.clasesExtra.ModeloTablaEstadoHabitaciones;
import main.java.interfaces.clasesExtra.RenderParaTablaEstadoColores;
import main.java.interfaces.clasesExtra.RenderParaTablas;

public class PanelResultadosDeBusquedaHabitacionesGroupBox extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JTable tabla;
	private ModeloTablaEstadoHabitaciones miModelo;
	private RenderParaTablas renderTabla;
	private RenderParaTablaEstadoColores renderTablaEstadoColores;
	
	@SuppressWarnings({ "rawtypes", "unused" })
	private Vector filaSeleccionada = null;
	@SuppressWarnings("unused")
	private Integer nroFilaSeleccionada;
	private JScrollPane tableContainer;
	
	private Insets insetTabla = new Insets(15, 100, 15, 100);

	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 18);	
	public PanelResultadosDeBusquedaHabitacionesGroupBox() {
		
		this.setBackground(Color.white);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), " Resultados de búsqueda", 0, 0, fuenteGroupBox));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		miModelo = new ModeloTablaEstadoHabitaciones();
		
		miModelo.cargarEstados();
		
		tabla = new JTable(miModelo){
			
			private static final long serialVersionUID = 1L;
			
	      protected JTableHeader createDefaultTableHeader() {
	          return new HeaderTablaAgrupable(columnModel);
	      }
	  };
	  
		tableContainer = new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		renderTabla = new RenderParaTablas(tabla.getDefaultRenderer(Object.class), true);
		
		renderTablaEstadoColores = new RenderParaTablaEstadoColores();
		
		tabla.setDefaultRenderer(String.class, renderTablaEstadoColores);
		
		tabla.getTableHeader().setDefaultRenderer(renderTabla);
		
	    TableColumnModel cm = tabla.getColumnModel();
	    ColumnaAgrupada colIndvidualEstandar = new ColumnaAgrupada("Individual Estándar", renderTabla);
	    colIndvidualEstandar.add(cm.getColumn(1));	//Habria que poner "for" hasta que toque el primer "2..."
	    colIndvidualEstandar.add(cm.getColumn(2));
	    colIndvidualEstandar.add(cm.getColumn(3));
	    colIndvidualEstandar.add(cm.getColumn(4));
	    ColumnaAgrupada colDobleEstandar = new ColumnaAgrupada("Doble Estándar");
	    colDobleEstandar.add(cm.getColumn(5));	//Habria que poner "for" hasta que toque el primer "3..."
	    colDobleEstandar.add(cm.getColumn(6));
	    colDobleEstandar.add(cm.getColumn(7));
	    colDobleEstandar.add(cm.getColumn(8));
	    ColumnaAgrupada colDobleSuperior = new ColumnaAgrupada("Doble Superior");
	    colDobleSuperior.add(cm.getColumn(9));	//Habria que poner "for" hasta que toque el primer "4..."
	    colDobleSuperior.add(cm.getColumn(10));
	    colDobleSuperior.add(cm.getColumn(11));
	    colDobleSuperior.add(cm.getColumn(12));
	    ColumnaAgrupada colSuperiorFamilyPlan = new ColumnaAgrupada("Superior Family Plan");
	    colSuperiorFamilyPlan.add(cm.getColumn(13));	//Habria que poner "for" hasta que toque el primer "5..."
	    colSuperiorFamilyPlan.add(cm.getColumn(14));
	    colSuperiorFamilyPlan.add(cm.getColumn(15));
	    colSuperiorFamilyPlan.add(cm.getColumn(16));
	    ColumnaAgrupada colSuiteDouble = new ColumnaAgrupada("Suite Double");
	    colSuiteDouble.add(cm.getColumn(17));	//Habria que poner "for" hasta que toque el ultimo "5..."
	    colSuiteDouble.add(cm.getColumn(18));
	    colSuiteDouble.add(cm.getColumn(19));
	    colSuiteDouble.add(cm.getColumn(20));

	    HeaderTablaAgrupable header = (HeaderTablaAgrupable)tabla.getTableHeader();
	    header.addColumnGroup(colIndvidualEstandar);
	    header.addColumnGroup(colDobleEstandar);
	    header.addColumnGroup(colDobleSuperior);
	    header.addColumnGroup(colSuperiorFamilyPlan);	
	    header.addColumnGroup(colSuiteDouble);
		
		tabla.getTableHeader().setReorderingAllowed(false); //Para que no se muevan las columnas
		
		tabla.setRowSelectionAllowed(true);
		tabla.setColumnSelectionAllowed(false);
		
		tabla.setFocusable(false); //Para que no seleccione una sola columna
		
		tabla.getTableHeader().setResizingAllowed(false);	//Para que no puedas cambiar el tamaño de las columnas
		
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tabla.setAutoCreateRowSorter(false);	//Para que se ordenen
		
		tabla.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseReleased(MouseEvent e) {
		    	
		    	if(e.isPopupTrigger() && e.getComponent() instanceof JTable) {	//Si pulsa el boton derecho dentro de la tabla
		    		
		    		if(((RenderParaTablaEstadoColores) tabla.getDefaultRenderer(String.class)).celdaYaSeleccionada(tabla.rowAtPoint(e.getPoint()), tabla.columnAtPoint(e.getPoint()))) {
		    			
		    			miModelo.limpiarTabla();
		    			miModelo.cargarEstados();
		    			tabla.setDefaultRenderer(String.class, new RenderParaTablaEstadoColores());

		    		}
		    	}

		        if (tabla.getSelectedRow() >= 0) {
		        	
				    int r = tabla.rowAtPoint(e.getPoint());
			        if (r >= 0 && r < tabla.getRowCount()) {
			        	try {
							filaSeleccionada = miModelo.getDataVector().elementAt(tabla.getSelectedRow());
							nroFilaSeleccionada = tabla.getSelectedRow();
			        	} catch(ArrayIndexOutOfBoundsException exc) {		//El "elementAt" fallta debido a que el click derecho busca el elemento -1 en el vector
			        		
			        		System.out.println("Click derecho por excepcion");
			        		//tabla.setDefaultRenderer(String.class, new RenderParaTablaEstadoColores());	//Quizas mas ineficiente, pero mas simple
			        	}
			        } else {
			        	tabla.clearSelection();
			        }
		       }

		    }
		});
		
//		tabla.addMouseListener(new MouseAdapter() {
//			public void mouseReleased(MouseEvent e) {				
//				filaSeleccionada = miModelo.getDataVector().elementAt(tabla.getSelectedRow());
//				nroFilaSeleccionada = tabla.getSelectedRow();
//			}
//		});
		
		tabla.setRowHeight(30);
		cm.getColumn(0).setPreferredWidth(100);
		
	    for (int i = 1; i < cm.getColumnCount(); i++) {
	    	cm.getColumn(i).setPreferredWidth(30);
	    }
	    
	    tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		//PARA CENTRAR
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		tabla.setDefaultRenderer(Object.class, centerRenderer);
		
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
		
}
