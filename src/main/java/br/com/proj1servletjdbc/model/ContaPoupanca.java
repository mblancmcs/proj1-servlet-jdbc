package br.com.proj1servletjdbc.model;

import java.sql.Connection;

public class ContaPoupanca extends Conta implements Rendimentos {

	public ContaPoupanca(Integer numero, Integer agencia, String tipo,
			Titular titular) {
		super(numero, agencia, titular);
	}

	public ContaPoupanca(Integer numero, Integer agencia, String tipo, Double saldo,
			Titular titular) {
		super(numero, agencia, saldo, titular);
	}

	public ContaPoupanca(Integer id, Integer numero, Integer agencia, String tipo,
			Double saldo, Double taxa, Titular titular) {
		super(id, numero, agencia, tipo, saldo, taxa, titular);
	}

	@Override
	public void atualizarTaxa(Connection conn, Double taxa) {
		super.taxa = taxa;
		new ContaDAO(conn).atualizarTaxa(taxa, super.id);
	}

}
