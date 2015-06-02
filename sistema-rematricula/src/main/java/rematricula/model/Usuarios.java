package rematricula.model;

public class Usuarios {

	private int codigoUsuario;
	private String nomeUsuario;
	private String loginUsuario;
	private String senhaUsuario;
	private int adminSistema;

	public Usuarios() {

	}

	 public Usuarios(String loginUsuario, String senhaUsuario){
	 this.loginUsuario = loginUsuario;
	 this.senhaUsuario = senhaUsuario;

	 }

	public int getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
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

	public int getAdminSistema() {
		return adminSistema;
	}

	public void setAdminSistema(int adminSistema) {
		this.adminSistema = adminSistema;
	}

}
