package br.com.proj1servletjdbc.model;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionFactory {
	
	public Connection recuperaConexao() {
		
		try {
			return createDataSource().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	private HikariDataSource createDataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://localhost:3306/excontabancaria");
		config.setUsername("root");
		config.setPassword("root");
		config.setMaximumPoolSize(10);
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        // quando eh usado "drivers antigos" eh preciso informar o caminho para ele.
        // tamb�m foi preciso adicionar mais duas bibliotecas com inicio "slf4j"
		
		return new HikariDataSource(config);
	}

}
