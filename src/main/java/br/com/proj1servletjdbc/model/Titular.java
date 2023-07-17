package br.com.proj1servletjdbc.model;

import java.sql.Connection;

public class Titular {

	private Integer id;
	private String nome;
	private Integer cpf;
	private String email;
	private String endereco;
	private String senha;
	
	public Titular() {
		
	}
	
	public Titular(String nome, Integer cpf, String email, String endereco,
			String senha) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.endereco = endereco;
		this.senha = senha;
	}
	
	public Titular(Integer id, String nome, Integer cpf, String email,
			String endereco, String senha) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.endereco = endereco;
		this.senha = senha;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Integer getCpf() {
		return cpf;
	}

	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(Connection conn, Integer idTitular, String email) {
		this.email = email;
		new TitularDAO(conn).editarEmail(idTitular, email);
	}
	
	public String getEndereco() {
		return this.endereco;
	}
	
	public void setEndereco(Connection conn, Integer idTitular, String endereco) {
		this.endereco = endereco;
		new TitularDAO(conn).editarSenha(idTitular, endereco);
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(Connection conn, Integer idTitular, String senha) {
		this.senha = senha;
		new TitularDAO(conn).editarSenha(idTitular, senha);
	}
	
}
