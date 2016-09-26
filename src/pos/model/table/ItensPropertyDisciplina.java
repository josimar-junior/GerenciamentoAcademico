package pos.model.table;

import javafx.beans.property.SimpleStringProperty;

public class ItensPropertyDisciplina {

	private SimpleStringProperty nome;

	public ItensPropertyDisciplina(String nome) {

		this.nome = new SimpleStringProperty(nome);
	}

	public String getNome() {
		return nome.get();
	}

	public void setNome(String nome) {
		this.nome.set(nome);
	}

}
