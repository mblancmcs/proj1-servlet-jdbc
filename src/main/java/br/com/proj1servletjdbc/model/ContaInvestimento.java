package br.com.proj1servletjdbc.model;

import java.sql.Connection;

public class ContaInvestimento extends ContaCorrente implements Rendimentos {

	public ContaInvestimento(Integer numero, Integer agencia, String tipo,
			Titular titular) {
		super(numero, agencia, tipo, titular);
	}

	public ContaInvestimento(Integer numero, Integer agencia, String tipo, Double saldo,
			Titular titular) {
		super(numero, agencia, tipo, saldo, titular);
	}

	public ContaInvestimento(Integer id, Integer numero, Integer agencia, String tipo,
			Double saldo, Double taxa, Double limite, Titular titular) {
		super(id, numero, agencia, tipo, saldo, taxa, limite, titular);
	}

	@Override
	public void atualizarTaxa(Connection conn, Double taxa) {
		super.taxa = taxa;
		new ContaDAO(conn).atualizarTaxa(taxa, super.id);
	}

}
