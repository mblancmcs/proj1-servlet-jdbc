package br.com.proj1servletjdbc.actions;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.proj1servletjdbc.model.ConnectionFactory;
import br.com.proj1servletjdbc.model.Conta;
import br.com.proj1servletjdbc.model.ContaDAO;
import br.com.proj1servletjdbc.model.Titular;
import br.com.proj1servletjdbc.servlet.Acao;

public class Cadastro implements Acao {
	
	public String executa (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String nome = request.getParameter("nome");
		Long cpf = Long.valueOf(request.getParameter("cpf"));
		String email = request.getParameter("email");
		String endereco = request.getParameter("endereco");
		String senha = request.getParameter("senha");
		
		Titular titular = new Titular(nome, cpf, email, endereco, senha);
		
		Integer numero = Integer.valueOf(request.getParameter("numero"));
		Integer agencia = Integer.valueOf(request.getParameter("agencia"));
		Double depositoInicial = Double.valueOf(request.getParameter("depositoInicial"));
		
		Conta conta = new Conta(numero, agencia, depositoInicial, titular);
		
		ConnectionFactory connection = new ConnectionFactory();
		Connection conn = connection.recuperaConexao();
		
		new ContaDAO(conn).cadastrarConta(conta, titular);
		
		return "redirect:entrada?acao=AdminPage";
		
	}

}
