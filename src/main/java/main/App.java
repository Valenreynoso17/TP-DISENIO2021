package main.java.main;

import javax.swing.UIManager;

import main.java.gestores.GestorPaisProvincia;
import main.java.interfaces.CU01.FrameAutenticarUsuario;
import main.java.interfaces.CU02.FrameGestionarPasajero;
import main.java.interfaces.CU11.FrameAltaPasajero;
import main.java.postgreImpl.PaisPostgreSQLImpl;
import main.java.postgreImpl.PasajeroPostgreSQLImpl;

public class App {

	public static void main(String[] args) {
			//FrameAltaPasajero f = new FrameAltaPasajero();

			FrameAutenticarUsuario fAU = new FrameAutenticarUsuario();
		
			//FrameGestionarPasajero fGP = new FrameGestionarPasajero();
		
			//GestorPaisProvincia gPP = GestorPaisProvincia.getInstance();
			
			//System.out.println(gPP.buscarPaises());
	}

}

