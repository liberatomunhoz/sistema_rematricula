package rematricula.model;

public class Professores extends DadosUsuarios {
	
	//FK
	private int codigoLoginProfessor;

	public int getCodigoLoginProfessor() {
		return codigoLoginProfessor;
	}

	public void setCodigoLoginProfessor(int codigoLoginProfessor) {
		this.codigoLoginProfessor = codigoLoginProfessor;
	}
	
}
