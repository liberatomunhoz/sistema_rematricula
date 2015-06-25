package rematricula.model;

public class Usuarios extends Entidade {

	private String loginUsuario;
	private String senhaUsuario;
	private NivelUsuario nivelUsuario;

	public Usuarios() {

	}

	 public Usuarios(String loginUsuario, String senhaUsuario){
	 }

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public NivelUsuario getNivelUsuario() {
		return nivelUsuario;
	}

	public void setNivelUsuario(NivelUsuario nivelUsuario) {
		this.nivelUsuario = nivelUsuario;
	}	

}
