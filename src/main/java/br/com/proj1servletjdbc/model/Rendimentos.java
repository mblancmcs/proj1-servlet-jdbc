package br.com.proj1servletjdbc.model;

import java.sql.Connection;

public interface Rendimentos {
	
	public void atualizarTaxa(Connection conn, Double taxa);

}
