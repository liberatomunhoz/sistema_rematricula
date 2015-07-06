package rematricula.model;

public class Turmas extends Professores {

	private int numeroTurma;
	
	//FK
	private int codigoProfessor;
	private int codigoDisciplina;
	private int codigoProfessorTurma;
	private int codigoTurmaDisciplina;
	private String nomeDisciplina;
	private String notaAluno;

	public int getNumeroTurma() {
		return numeroTurma;
	}

	public void setNumeroTurma(int numeroTurma) {
		this.numeroTurma = numeroTurma;
	}

	public int getCodigoProfessor() {
		return codigoProfessor;
	}

	public void setCodigoProfessor(int codigoProfessor) {
		this.codigoProfessor = codigoProfessor;
	}

	public int getCodigoDisciplina() {
		return codigoDisciplina;
	}

	public void setCodigoDisciplina(int codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}

	public int getCodigoTurmaDisciplina() {
		return codigoTurmaDisciplina;
	}

	public void setCodigoTurmaDisciplina(int codigoTurmaDisciplina) {
		this.codigoTurmaDisciplina = codigoTurmaDisciplina;
	}

	public int getCodigoProfessorTurma() {
		return codigoProfessorTurma;
	}

	public void setCodigoProfessorTurma(int codigoProfessorTurma) {
		this.codigoProfessorTurma = codigoProfessorTurma;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public String getNotaAluno() {
		return notaAluno;
	}

	public void setNotaAluno(String notaAluno) {
		this.notaAluno = notaAluno;
	}
	
}
