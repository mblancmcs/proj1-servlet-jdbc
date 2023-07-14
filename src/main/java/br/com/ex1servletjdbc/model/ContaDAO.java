package br.com.ex1servletjdbc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaDAO { // responsabilidade de interação com o banco de dados

	private Connection conn;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String sql;
	
	public ContaDAO(Connection connection) {
		this.conn = connection;
	}
	
	public String listarContasETitular() {
		
		sql = "SELECT T.id, T.nome, T.cpf, C.id, C.numero, C.agencia, C.saldo "
				+ "FROM titular as T INNER JOIN conta as C "
				+ "ON(T.id = C.id_titular)";
		
		String contasString = null;
		
		try {
			ps = this.conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Integer idCliente = rs.getInt(1);
				String nome = rs.getString(2);
				Integer cpf = rs.getInt(3);
				Integer idConta = rs.getInt(4);
				Integer numero = rs.getInt(5);
				Integer agencia = rs.getInt(6);
				Double saldo = rs.getDouble(7);
				contasString += "Cod. Cliente: " + idCliente + " | Nome: " + nome + " | CPF: " + cpf + 
						"\nCod. Conta: " + idConta + " | Numero: " + numero + " | Agencia: " + 
						agencia + " | Saldo: " + saldo + "\n\n";
			}
			
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return contasString;
		
	}
	
	public Titular listarTitularById(Integer id) {
		
		sql = "SELECT id, nome, cpf, email, endereco, senha FROM titular WHERE id = ?";
		
		Titular titular = null;
		
		try {
			ps = this.conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			Integer idTitular = rs.getInt(1);
			String nome = rs.getString(2);
			Integer cpf = rs.getInt(3);
			String email = rs.getString(4);
			String endereco = rs.getString(5);
			String senha = rs.getString(6);
			
			titular = new Titular(idTitular, nome, cpf, email, endereco, senha);
			
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return titular;
	}
	
	public Conta listarContaById(Integer id) {
		
		sql = "SELECT id, numero, agencia, saldo, taxa, id_titular"
				+ "FROM conta "
				+ "WHERE id = ?";
		
		Conta conta = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			Integer idConta = rs.getInt(1);
			Integer numero = rs.getInt(2);
			Integer agencia = rs.getInt(3);
			Double saldo = rs.getDouble(4);
			Double taxa = rs.getDouble(5);
			Titular titular = listarTitularById(rs.getInt(6));
			
			conta = new Conta(idConta, numero, agencia, saldo, taxa, titular);
			
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return conta;
		
	}
	
	public void cadastrarConta(Conta conta, Titular titular) {
		
		if(titular == null) {
			return;
		}
		
		sql = "INSERT INTO titular(nome, cpf, email, endereco, senha)"
				+ " VALUES(?, ?, ?, ?, ?)";
		
		try {
			ps = this.conn.prepareStatement(sql);
			ps.setString(1, titular.getNome());
			ps.setInt(2, titular.getCpf());
			ps.setString(3, titular.getEmail());
			ps.setString(4, titular.getEndereco());
			ps.setString(5, titular.getSenha());
			
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		sql = "SELECT id FROM titular ORDER BY id DESC LIMIT 1";
		
		Integer idTitular;
		
		try {
			ps = this.conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			idTitular = rs.getInt(1);
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		sql = "INSERT INTO conta(numero, agencia, saldo, id_titular)"
				+ " VALUES(?, ?, ?, ?)";
		
		try {
			ps = this.conn.prepareStatement(sql);
			ps.setInt(1, conta.getNumero());
			ps.setInt(2, conta.getAgencia());
			ps.setDouble(3, conta.getSaldo());
			ps.setInt(4, idTitular);
			
			titular.setId(idTitular);
			
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		sql = "SELECT id FROM conta ORDER BY id DESC LIMIT 1";
		
		Integer idConta;
		
		try {
			ps = this.conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			idConta = rs.getInt(1);
			
			conta.setId(idConta);
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void editarSenha(Integer idTitular, String senha) {

		sql = "UPDATE titular SET senha = ? WHERE id = ?";
		
		try {
			ps = this.conn.prepareStatement(sql);
			ps.setString(1, senha);
			ps.setInt(2, idTitular);
			ps.execute();
			
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public void editarEmail(Integer idTitular, String email) {

		sql = "UPDATE titular SET email = ? WHERE id = ?";
		
		try {
			ps = this.conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setInt(2, idTitular);
			ps.execute();
			
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public void editarEndereco(Integer idTitular, String endereco) {

		sql = "UPDATE titular SET endereco = ? WHERE id = ?";
		
		try {
			ps = this.conn.prepareStatement(sql);
			ps.setString(1, endereco);
			ps.setInt(2, idTitular);
			ps.execute();
			
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void excluirConta(Integer idConta) {
		
		Integer idTitular = listarContaById(idConta).getTitular().getId();
		
		if(idTitular == null) {
			return;
		}

		sql = "DELETE FROM titular WHERE id = ?";
		
		try {
			ps = this.conn.prepareStatement(sql);
			ps.setInt(1, idTitular);
			ps.execute();
			
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		sql = "DELETE FROM conta WHERE id = ?";
		
		try {
			ps = this.conn.prepareStatement(sql);
			ps.setInt(1, idConta);
			ps.execute();
			
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public void sacar(Double valor, Integer idConta) {

		
		
	}

	public void depositar(Double valor, Integer idConta) {

		
		
	}

	public void transferir(Double valor, Integer idDestino, Integer idOrigem) {

		
		
	}
	
}
