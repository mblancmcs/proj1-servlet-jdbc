package br.com.proj1servletjdbc.model;

import java.sql.Connection;

public class Conta {
	
	protected Integer id;
	private Integer numero;
	private Integer agencia;
	protected Double saldo;
	protected String tipo;
	protected Double taxa;
	private Titular titular;
	
	public Conta(Integer numero, Integer agencia, String tipo, Titular titular) {
		this.numero = numero;
		this.agencia = agencia;
		this.tipo = tipo;
		this.titular = titular;
	}

	public Conta(Integer numero, Integer agencia, String tipo, Double saldo,
			Titular titular) {
		this.numero = numero;
		this.agencia = agencia;
		this.tipo = tipo;
		this.saldo = saldo;
		this.titular = titular;
	}
	
	public Conta(Integer id, Integer numero, Integer agencia, String tipo,
			Double saldo, Double taxa, Titular titular) {
		this.id = id;
		this.numero = numero;
		this.agencia = agencia;
		this.tipo = tipo;
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

	public Integer getNumero() {
		return numero;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public String getTipo() {
		return tipo;
	}

	public Double getTaxa() {
		return taxa;
	}

	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}

	public Double getSaldo() {
		return saldo;
	}
	
	// As actions fornecerao a conexao
	public void sacar(Connection conn, Double valor) {
		
		if(this.saldo < valor && valor > 0) {
			return;
		} else {
			Double novoSaldo = this.saldo - valor;
			new ContaDAO(conn).atualizarSaldo(novoSaldo, this.id);
		}
		
	}

	public void depositar(Connection conn, Double valor) {
		Double novoSaldo = this.saldo + valor;
		new ContaDAO(conn).atualizarSaldo(novoSaldo, this.id);
	}
	
	public void transferir(Connection conn, Double valor, Conta contaDestino) {
		
		if(this.saldo < valor && valor > 0) {
			return;
		} else {
			Double novoSaldo = this.saldo - valor;
			new ContaDAO(conn).atualizarSaldo(novoSaldo, this.id);
			
			novoSaldo = contaDestino.saldo + valor;
			new ContaDAO(conn).atualizarSaldo(novoSaldo, contaDestino.id);
		}
		
	}

}
