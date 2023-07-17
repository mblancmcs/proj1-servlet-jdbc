package br.com.proj1servletjdbc.model;

import java.sql.Connection;

public class ContaCorrente extends Conta {
	
	private Double limite;

	public ContaCorrente(Integer numero, Integer agencia, String tipo,
			Titular titular) {
		super(numero, agencia, tipo, titular);
	}

	public ContaCorrente(Integer numero, Integer agencia, String tipo, Double saldo,
			Titular titular) {
		super(numero, agencia, tipo, saldo, titular);
	}

	public ContaCorrente(Integer id, Integer numero, Integer agencia, String tipo,
			Double saldo, Double taxa, Double limite, Titular titular) {
		super(id, numero, agencia, tipo, saldo, taxa, titular);
		this.limite = limite;
	}

	public Double getLimite() {
		return limite;
	}

	public void atualizarLimite(Connection conn, Double limite) {
		this.limite = limite;
		new ContaDAO(conn).atualizarLimite(limite, super.id);
	}
	
	public Double getSaldoComLimite() {
		return super.saldo + this.limite;
	}

}
