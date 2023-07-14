package br.com.ex1servletjdbc.model;

public class ContaCorrente extends Conta {
	
	private Double limite;

	public ContaCorrente(Integer numero, Integer agencia, Titular titular) {
		super(numero, agencia, titular);
	}

	public Double getLimite() {
		return limite;
	}

	public void setLimite(Double limite) {
		this.limite = limite;
	}
	
	public Double getSaldoComLimite() {
		return super.saldo + this.limite;
	}

}
