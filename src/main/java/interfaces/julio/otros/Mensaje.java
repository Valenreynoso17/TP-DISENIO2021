package main.java.interfaces.julio.otros;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Mensaje extends JFrame{

	private JPanel contentPane;
	
	private JButton button;
	
	private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 13);

	public Mensaje() {
		super("Sistema Hotel Premier");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 240);
		contentPane = new JPanel();
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		button = new JButton("Aceptar");

		button.setFont(fuenteBoton);
		button.setBackground(Color.decode("#E0E0E0"));
		button.setBorderPainted(false);
		button.setPreferredSize(new Dimension(100, 35));
		c.anchor = GridBagConstraints.EAST;	//c.insets = izq;
			c.gridx = 0; c.gridy = 0;
		contentPane.add(button, c);
		
		setContentPane(contentPane);
		
	}
	
}
