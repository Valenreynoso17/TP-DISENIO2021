package main.java.daos;

import java.util.List;

import main.java.clases.Pais;

public interface PaisDAO {
	public List<Pais> buscar();
	public Pais buscar(Integer id);
}
