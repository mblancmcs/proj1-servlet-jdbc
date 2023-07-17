package br.com.proj1servletjdbc.model;

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
		
		sql = "SELECT T.id, T.nome, T.cpf, C.id, C.tipo, C.numero, C.agencia, C.saldo, C.limite "
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
				String tipo = rs.getString(5);
				Integer numero = rs.getInt(6);
				Integer agencia = rs.getInt(7);
				Double saldo = rs.getDouble(8);
				Double limite = rs.getDouble(9);
				contasString += "Cod. Cliente: " + idCliente + " | Nome: " + nome + " | CPF: " + cpf + 
						"\nCod. Conta: " + idConta + " | Tipo: Conta " + tipo + " | Numero: " + numero
						+ " | Agencia: " + agencia + " | Saldo: " + saldo + " | Limite: " + limite + "\n\n";
			}
			
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return contasString;
		
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
			String tipo = rs.getString(4);
			Double saldo = rs.getDouble(5);
			Double taxa = rs.getDouble(6);
			Titular titular =  new TitularDAO(conn).listarTitularById(rs.getInt(6));
			
			conta = new Conta(idConta, numero, agencia, tipo, saldo, taxa, titular);
			
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

	public void atualizarSaldo(Double valor, Integer idConta) {

		sql = "UPDATE conta SET valor = ? WHERE id = idConta";
		
		try {
			ps = this.conn.prepareStatement(sql);
			ps.setDouble(1, valor);
			ps.setInt(1, idConta);
			
			ps.execute();
			
			ps.close();
			conn.close();
		} catch (SQLException e) {
			new RuntimeException(e);
		}
		
	}
	
	public void atualizarTipoConta (String tipo, Integer idConta) {
		
		sql = "UPDATE conta SET tipo = ? WHERE id = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, tipo);
			ps.setInt(2, idConta);
			
			ps.execute();
			
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void atualizarTaxa (Double taxa, Integer idConta) {
		
		sql = "UPDATE conta SET taxa = ? WHERE id = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, taxa);
			ps.setInt(2, idConta);
			
			ps.execute();
			
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void atualizarLimite (Double limite, Integer idConta) {
		
		sql = "UPDATE conta SET limite = ? WHERE id = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, limite);
			ps.setInt(2, idConta);
			
			ps.execute();
			
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
