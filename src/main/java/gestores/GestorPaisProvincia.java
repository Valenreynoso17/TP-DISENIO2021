package main.java.gestores;

import java.util.ArrayList;
import java.util.List;

import main.java.clases.Localidad;
import main.java.clases.Pais;
import main.java.clases.Provincia;
import main.java.daos.LocalidadDAO;
import main.java.daos.PaisDAO;
import main.java.daos.ProvinciaDAO;
import main.java.dtos.LocalidadDTO;
import main.java.dtos.PaisDTO;
import main.java.dtos.ProvinciaDTO;
import main.java.postgreImpl.LocalidadPostgreSQLImpl;
import main.java.postgreImpl.PaisPostgreSQLImpl;
import main.java.postgreImpl.ProvinciaPostgreSQLImpl;

public class GestorPaisProvincia {
	private static GestorPaisProvincia instance;
	private PaisDAO paisDAO;
	private ProvinciaDAO provinciaDAO;
	private LocalidadDAO localidadDAO;
	
	private GestorPaisProvincia() {
		paisDAO = new PaisPostgreSQLImpl();
		provinciaDAO = new ProvinciaPostgreSQLImpl();
		localidadDAO = new LocalidadPostgreSQLImpl();
	}
	
	public static GestorPaisProvincia getInstance() {
		if (instance == null) instance = new GestorPaisProvincia();
		
		return instance;
	}
	
	// Devuelve todos los paises en forma de DTO
	public List<PaisDTO> buscarPaises() {
		List<Pais> paises = paisDAO.buscar();
		List<PaisDTO> paisesDTO = new ArrayList<PaisDTO>();
		
		for (Pais p : paises) {
			paisesDTO.add(crearPaisDTO(p));
		}
		
		return paisesDTO;	
	}
	
	// Dado el id de un pais devuelve todas las provincias de ese pais, en forma de DTO
	public List<ProvinciaDTO> buscarProviciasPorPais(Integer idPais) {
		List<Provincia> provincias = provinciaDAO.buscarPorPais(idPais);
		List<ProvinciaDTO> provinciasDTO = new ArrayList<ProvinciaDTO>();
		
		for (Provincia p : provincias) {
			provinciasDTO.add(crearProvinciaDTO(p));
		}
		
		return provinciasDTO;
	}
	
	// Dado el id de una provincia devuelve todas las localidades de esa provincia, en forma de DTO
	public List<LocalidadDTO> buscarLocalidadesPorProvincia(Integer idProvincia) {
		List<Localidad> localidades = localidadDAO.buscarPorProvincia(idProvincia);
		List<LocalidadDTO> localidadesDTO = new ArrayList<LocalidadDTO>();
		
		for (Localidad l : localidades) {
			localidadesDTO.add(crearLocalidadDTO(l));
		}
		
		return localidadesDTO;
	}
	
	public Localidad buscarLocalidadPorID(Integer idLocalidad) {
		return localidadDAO.buscar(idLocalidad);
	}
	
	// Crea un paisDTO a partir de un pais
	private PaisDTO crearPaisDTO(Pais pais) {
		PaisDTO dto = new PaisDTO();
		dto.setId(pais.getId());
		dto.setNombre(pais.getNombre());
		dto.setNacionalidad(pais.getNacionalidad());
		
		return dto;
	}
	
	// Crea un provinciaDTO a partir de una provincia
	private ProvinciaDTO crearProvinciaDTO(Provincia provincia) {
		ProvinciaDTO dto = new ProvinciaDTO();
		dto.setId(provincia.getId());
		dto.setNombre(provincia.getNombre());
		
		return dto;
	}
	
	// Crea un localidadDTO a partir de una localidad
	private LocalidadDTO crearLocalidadDTO(Localidad localidad) {
		LocalidadDTO dto = new LocalidadDTO();
		dto.setId(localidad.getId());
		dto.setNombre(localidad.getNombre());
		
		return dto;
	}
}
