package main.java.daos;

import java.util.List;

import main.java.clases.Localidad;

public interface LocalidadDAO {
	public Localidad buscar(Integer id);
	public List<Localidad> buscarPorProvincia(Integer idProvincia);
}
