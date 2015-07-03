package rematricula.model;

public class Alunos extends DadosUsuarios {
	
	private int matriculaAluno;
	private String nomePaiAluno;
	private String nomeMaeAluno;
	
	//FK
	private int codigoLoginAluno;
	
	public int getMatriculaAluno() {
		return matriculaAluno;
	}
	
	public void setMatriculaAluno(int matriculaAluno) {
		this.matriculaAluno = matriculaAluno;
	}

	public String getNomePaiAluno() {
		return nomePaiAluno;
	}

	public void setNomePaiAluno(String nomePaiAluno) {
		this.nomePaiAluno = nomePaiAluno;
	}

	public String getNomeMaeAluno() {
		return nomeMaeAluno;
	}

	public void setNomeMaeAluno(String nomeMaeAluno) {
		this.nomeMaeAluno = nomeMaeAluno;
	}

	public int getCodigoLoginAluno() {
		return codigoLoginAluno;
	}

	public void setCodigoLoginAluno(int codigoLoginAluno) {
		this.codigoLoginAluno = codigoLoginAluno;
	}
	
}
