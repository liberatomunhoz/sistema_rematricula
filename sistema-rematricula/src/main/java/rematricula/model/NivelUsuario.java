package rematricula.model;

public enum NivelUsuario {
	
	ADMINISTRADOR("Administrador"), PROFESSOR("Professor"), ALUNO("Aluno");

	private String descricao;

	private NivelUsuario(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
