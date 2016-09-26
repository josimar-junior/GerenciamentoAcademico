package pos.dao;

import java.util.ArrayList;
import java.util.List;

import pos.model.Disciplina;

public class DisciplinaDAO {

	private static List<Disciplina> disciplinas = new ArrayList<>();

	public static void salvar(Disciplina disciplina) {
		disciplinas.add(disciplina);
	}

	public static List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
}
