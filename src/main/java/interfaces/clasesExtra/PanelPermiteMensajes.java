package main.java.interfaces.clasesExtra;

import main.java.interfaces.CU11.FrameAltaPasajero;

public interface PanelPermiteMensajes{

	public PanelPermiteMensajes getPanel();
	public void confirmoElMensaje(Integer idMensaje);
	public void confirmoCancelar(Integer idMensaje);
}
