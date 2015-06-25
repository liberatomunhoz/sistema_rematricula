package rematricula.model;

public class Disciplinas extends Entidade {
	
	private String nomeDisciplina;
	private int preRequisito;
	
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
	
}
