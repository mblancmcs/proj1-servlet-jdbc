package br.com.ex1servletjdbc.model;

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
		config.setJdbcUrl("jdbc://localhost:3306/excontabancaria");
		config.setUsername("root");
		config.setPassword("root");
		config.setMaximumPoolSize(10);
		
		return new HikariDataSource(config);
	}

}
