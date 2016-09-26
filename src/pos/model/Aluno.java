package pos.model;

import java.util.ArrayList;
import java.util.List;

public class Aluno extends Usuario {

	private List<Disciplina> disciplinas;
	private int qtdDisciplinas;

	public Aluno(String matricula, String nome) {
		super(matricula, nome);
	}

	public List<Disciplina> getDisciplinas() {
		if(disciplinas == null) {
			disciplinas = new ArrayList<>();
		}
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public int getQtdDisciplinas() {
		return qtdDisciplinas;
	}

	public void setQtdDisciplinas() {
		this.qtdDisciplinas++;
	}

}
