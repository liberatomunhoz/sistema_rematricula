package rematricula.model;

public class Disciplinas extends Cursos {
	
	private String nomeDisciplina;
	private int preRequisito;
	private int semestre;
	
	//FK
	private int codCurso;
	
	public int getPreRequisito() {
		return preRequisito;
	}
	public void setPreRequisito(int preRequisito) {
		this.preRequisito = preRequisito;
	}
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}
	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}
	public int getCodCurso() {
		return codCurso;
	}
	public void setCodCurso(int codCurso) {
		this.codCurso = codCurso;
	}
	public int getSemestre() {
		return semestre;
	}
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	
}
