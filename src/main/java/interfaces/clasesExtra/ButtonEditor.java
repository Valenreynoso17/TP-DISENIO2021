package main.java.interfaces.clasesExtra;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class ButtonEditor extends DefaultCellEditor {
	
	private static final long serialVersionUID = 1L;

    protected JButton button;
    private String label;
    private boolean isPushed;
    
    private Character tipo;

    public ButtonEditor(JCheckBox checkBox, Character c) {
        super(checkBox);
        this.tipo = c;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    	
        button.setForeground(Color.BLACK);	
        button.setBackground(Color.WHITE);
        
        if(value == null) {		//Si es una fila en blanco
        	return null;
        }
        
        label = "";
        button.setText(label);
        isPushed = true;
        return button;
        
        
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            if(this.tipo == '+') {
            	
            }
            else if(this.tipo == '-') {
            	
            }
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}
