package br.com.proj1servletjdbc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TitularDAO {
	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	public TitularDAO(Connection conn) {
		this.conn= conn;
	}
	
	public List<Titular> listarTitulares() {
		
		sql = "SELECT * FROM titular";
		
		List<Titular> listaTitulares = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			Titular titular = null;
			
			while(rs.next()) {
				Integer id = rs.getInt(1);
				String nome = rs.getString(2);
				Long cpf = rs.getLong(3);
				String email = rs.getString(4);
				String endereco = rs.getString(5);
				String senha = rs.getString(6);
				String perfil = rs.getString(7);
				
				titular = new Titular(id, nome, cpf, email, endereco, senha, perfil);
				
				listaTitulares.add(titular);
			}
			
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return listaTitulares;
		
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
			Long cpf = rs.getLong(3);
			String email = rs.getString(4);
			String endereco = rs.getString(5);
			String senha = rs.getString(6);
			String perfil = rs.getString(7);
			
			titular = new Titular(idTitular, nome, cpf, email, endereco, senha, perfil);
			
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return titular;
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
	
	// Nao foi implementado o metodo de excluir o titular, pois eh preciso que
	// seja excluida a conta junto, logo ha apenas esse metodo na classe ContaDAO

}
