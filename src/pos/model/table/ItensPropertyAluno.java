package pos.model.table;

import javafx.beans.property.SimpleStringProperty;

public class ItensPropertyAluno {

	private SimpleStringProperty matricula;
	private SimpleStringProperty nome;

	public ItensPropertyAluno(String matricula, String nome) {

		this.matricula = new SimpleStringProperty(matricula);
		this.nome = new SimpleStringProperty(nome);
	}

	public String getMatricula() {
		return matricula.get();
	}

	public void setMatricula(String matricula) {
		this.matricula.set(matricula);
	}

	public String getNome() {
		return nome.get();
	}

	public void setNome(String nome) {
		this.nome.set(nome);
	}

}
