package rematricula.model;

import java.sql.Date;

public class DadosUsuarios extends Entidade {
	
	private int cpf;
	private int rg;
	private String nomeCompleto;
	private Date dataNascimento;
	private String nascionalidade;
	private String ufNascimento;
	private String email;
	private String endereco;
	private String bairro;
	private String cidade;
	private String cep;
	private String ufEndereco;
	private int telefone;
	private int celular;
	
	public int getCpf() {
		return cpf;
	}
	
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	
	public int getRg() {
		return rg;
	}
	
	public void setRg(int rg) {
		this.rg = rg;
	}
	
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getNascionalidade() {
		return nascionalidade;
	}
	
	public void setNascionalidade(String nascionalidade) {
		this.nascionalidade = nascionalidade;
	}
	
	public String getUfNascimento() {
		return ufNascimento;
	}
	
	public void setUfNascimento(String ufNascimento) {
		this.ufNascimento = ufNascimento;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getUfEndereco() {
		return ufEndereco;
	}
	
	public void setUfEndereco(String ufEndereco) {
		this.ufEndereco = ufEndereco;
	}
	
	public int getTelefone() {
		return telefone;
	}
	
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	
	public int getCelular() {
		return celular;
	}
	
	public void setCelular(int celular) {
		this.celular = celular;
	}
}
