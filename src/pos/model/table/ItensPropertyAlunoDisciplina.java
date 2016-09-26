package pos.model.table;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class ItensPropertyAlunoDisciplina {

	private SimpleStringProperty nomeAluno;
	private SimpleDoubleProperty nota1;
	private SimpleDoubleProperty nota2;
	private SimpleDoubleProperty media;

	public ItensPropertyAlunoDisciplina(String nomeAluno, Double nota1, Double nota2, Double media) {
		this.nomeAluno = new SimpleStringProperty(nomeAluno);
		this.nota1 = new SimpleDoubleProperty(nota1);
		this.nota2 = new SimpleDoubleProperty(nota2);
		this.media = new SimpleDoubleProperty(media);
	}

	public String getNomeAluno() {
		return nomeAluno.get();
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno.set(nomeAluno);
	}

	public Double getNota1() {
		return nota1.get();
	}

	public void setNota1(Double nota1) {
		this.nota1.set(nota1);
	}
	
	public Double getNota2() {
		return nota2.get();
	}

	public void setNota2(Double nota2) {
		this.nota2.set(nota2);
	}
	
	public Double getMedia() {
		return media.get();
	}

	public void setMedia(Double media) {
		this.media.set(media);
	}

}
