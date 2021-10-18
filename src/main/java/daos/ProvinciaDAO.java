package main.java.daos;

import java.util.List;

import main.java.clases.Provincia;

public interface ProvinciaDAO {
	public Provincia buscar(Integer id);
	public List<Provincia> buscarPorPais(Integer idPais);
}
