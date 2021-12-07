package main.java.gestores;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import main.java.clases.Usuario;
import main.java.daos.UsuarioDAO;
import main.java.excepciones.UsuarioOContraseniaIncorrectaException;
import main.java.postgreImpl.UsuarioPostgreSQLImpl;

public class GestorUsuario {
	private static GestorUsuario instance;
	private UsuarioDAO dao;
	
	private GestorUsuario() {
		dao = new UsuarioPostgreSQLImpl();
	}
	
	public static GestorUsuario getInstance() {
		if (instance == null) instance = new GestorUsuario();
		
		return instance;
	}
	
	/*public void guardarUsuario(String usuario, String contrasenia) {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt);
			
			byte[] hashedPassword = md.digest(contrasenia.getBytes(StandardCharsets.UTF_8));
			 StringBuilder sb = new StringBuilder();
			for (int i = 0; i < hashedPassword.length; i++) {
	        	sb.append(Integer.toString((hashedPassword[i] & 0xff) + 0x100, 16)
	        			.substring(1));
			}
			String generatedPassword = sb.toString();
			
			System.out.println(generatedPassword);
		
			md.update(salt);
			byte[] hashedPassword2 = md.digest(contrasenia.getBytes(StandardCharsets.UTF_8));
			
			StringBuilder sb2 = new StringBuilder();
			for (int i = 0; i < hashedPassword2.length; i++) {
	        	sb2.append(Integer.toString((hashedPassword2[i] & 0xff) + 0x100, 16)
	        			.substring(1));
			}
			String generatedPassword2 = sb2.toString();
			
			System.out.println(generatedPassword2);
			
			Boolean iguales = hashedPassword.length == hashedPassword2.length;
			
			if (iguales) {
				for (int i = 0; i<hashedPassword.length; i++) {
					if (hashedPassword[i] != hashedPassword2[i]) iguales = false;
				}
			}
			
			System.out.println(iguales);
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}*/
	
	public void autentificar(String usuario, char[] contrasenia) throws UsuarioOContraseniaIncorrectaException {
		Usuario user = dao.buscar(usuario);
		Boolean iguales = true;
		
		if (user == null) throw new UsuarioOContraseniaIncorrectaException();
		
		Integer caractNoNulos = 0;
		
		while (caractNoNulos < contrasenia.length && contrasenia[caractNoNulos] != 0) {
			caractNoNulos++;
		}
		
		if (caractNoNulos != user.getContrasenia().length()) iguales = false;
		else {
			for (int i=0; i<caractNoNulos; i++) {
				if (contrasenia[i] != user.getContrasenia().charAt(i)) {
						iguales = false;
				}
			}
		}
		
		if (!iguales) throw new UsuarioOContraseniaIncorrectaException();
		
	}
}
