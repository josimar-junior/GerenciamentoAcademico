package pos.dao;

import java.util.ArrayList;
import java.util.List;

import pos.model.Usuario;

public class UsuarioDAO {

	private static List<Usuario> usuarios = new ArrayList<>();

	public static void salvar(Usuario usuario) {
		usuarios.add(usuario);
	}
	
	public static List<Usuario> getUsuarios() {
		return usuarios;
	}
}
