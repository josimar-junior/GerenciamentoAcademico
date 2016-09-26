package pos.model;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {

	private static int codigoInc;
	private int codigo;
	private String nome;

	public Disciplina(String nome) {
		this.nome = nome;
		codigoInc++;
		this.setCodigo(codigoInc);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}
	
	
}
