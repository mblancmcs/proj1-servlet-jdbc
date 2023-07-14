package br.com.proj1servletjdbc.model;

import java.sql.Connection;

public class Conta {
	
	private Integer id;
	private Integer numero;
	private Integer agencia;
	protected Double saldo;
	private Double taxa;
	private Titular titular;
	
	public Conta(Integer numero, Integer agencia, Titular titular) {
		this.numero = numero;
		this.agencia = agencia;
		this.titular = titular;
	}

	public Conta(Integer numero, Integer agencia, Double saldo,
			Titular titular) {
		this.numero = numero;
		this.agencia = agencia;
		this.saldo = saldo;
		this.titular = titular;
	}
	
	public Conta(Integer id, Integer numero, Integer agencia,
			Double saldo, Double taxa, Titular titular) {
		this.id = id;
		this.numero = numero;
		this.agencia = agencia;
		this.saldo = saldo;
		this.taxa = taxa;
		this.titular = titular;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Titular getTitular() {
		return titular;
	}

	public void setTitular(Titular titular) {
		this.titular = titular;
	}

	public Double getTaxa() {
		return taxa;
	}

	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}

	public Integer getNumero() {
		return numero;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public Double getSaldo() {
		return saldo;
	}
	
	// As actions fornecerao a conexao
	public void sacar(Connection conn, Double valor) {
		new ContaDAO(conn).sacar(valor, this.id);
	}

	public void depositar(Connection conn, Double valor) {
		new ContaDAO(conn).depositar(valor, this.id);
	}
	
	public void transferir(Connection conn, Double valor, Integer idDestino) {
		new ContaDAO(conn).transferir(valor, idDestino, this.id);
	}

}
