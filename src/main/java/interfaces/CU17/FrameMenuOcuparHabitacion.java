package main.java.interfaces.CU17;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class FrameMenuOcuparHabitacion extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	public FrameMenuOcuparHabitacion(FrameOcuparHabitacionConPasajeros frameA) {
		super("Sistema Hotel Premier");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 400);
		contentPane = new JPanel(new GridBagLayout());	GridBagConstraints c = new GridBagConstraints();	//TODO: Ver si puede hacerse de una manera mas eficiente
		c.weightx = 0.1; c.weighty = 0.1;	contentPane.setBackground(Color.WHITE);	c.fill = GridBagConstraints.BOTH;	c.insets = new Insets(10,30,15,30);
		contentPane.add(new PanelMenuOcuparHabitacion(this, frameA), c);
		
		//contentPane = new PanelMenuOcuparHabitacion(this);
		setContentPane(contentPane);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

}
