package rematricula.model;

import java.util.List;

public class AlunosDisciplinas extends Entidade {

	private int codigoAluno;
	private int codigoDisciplina;
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
	
	public List<AlunosDisciplinas> getAlunosDisciplinas() {
		return alunosDisciplinas;
	}
	
	public void setAlunosDisciplinas(List<AlunosDisciplinas> alunosDisciplinas) {
		this.alunosDisciplinas = alunosDisciplinas;
	}
}
