package pos.dao;

import java.util.ArrayList;
import java.util.List;

import pos.model.Nota;

public class NotaDAO {

	private static List<Nota> notas = new ArrayList<>();
	
	public static void cadastrar(Nota nota) {
		notas.add(nota);
	}
	
	public static List<Nota> getNotas() {
		return notas;
	}
}
