package rematricula.model;

import java.util.List;


public class AlunosDisciplinas extends Turmas {

	private int codigoAluno;
	private int codigoDisciplina;
	private String notaAluno;
	private int semestre;
	private String status;
	private List<AlunosDisciplinas> alunosDisciplinas;
	
	public int getCodigoAluno() {
		return codigoAluno;
	}
	
	public void setCodigoAluno(int codigoAluno) {
		this.codigoAluno = codigoAluno;
	}
	
	public int getCodigoDisciplina() {
		return codigoDisciplina;
	}
	
	public void setCodigoDisciplina(int codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}

	public String getNotaAluno() {
		return notaAluno;
	}

	public void setNotaAluno(String notaAluno) {
		this.notaAluno = notaAluno;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<AlunosDisciplinas> getAlunosDisciplinas() {
		return alunosDisciplinas;
	}

	public void setAlunosDisciplinas(List<AlunosDisciplinas> alunosDisciplinas) {
		this.alunosDisciplinas = alunosDisciplinas;
	}
}
