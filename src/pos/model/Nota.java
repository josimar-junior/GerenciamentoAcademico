package pos.model;

public class Nota {

	private Aluno aluno;
	private Disciplina disciplina;
	private Double nota1;
	private Double nota2;

	public Nota(Aluno aluno, Disciplina disciplina, Double nota1, Double nota2) {
		this.aluno = aluno;
		this.disciplina = disciplina;
		this.nota1 = nota1;
		this.nota2 = nota2;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Double getNota1() {
		return nota1;
	}

	public void setNota1(Double nota1) {
		this.nota1 = nota1;
	}

	public Double getNota2() {
		return nota2;
	}

	public void setNota2(Double nota2) {
		this.nota2 = nota2;
	}

}
