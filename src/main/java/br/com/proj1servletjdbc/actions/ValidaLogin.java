package br.com.proj1servletjdbc.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.List;

import br.com.proj1servletjdbc.model.ConnectionFactory;
import br.com.proj1servletjdbc.model.Titular;
import br.com.proj1servletjdbc.model.TitularDAO;
import br.com.proj1servletjdbc.servlet.Acao;

public class ValidaLogin implements Acao {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String inputNome = req.getParameter("nome");
		String inputSenha = req.getParameter("senha");
		ConnectionFactory connection = new ConnectionFactory();
		
		Connection conn = connection.recuperaConexao();
		
		List<Titular> titulares = new TitularDAO(conn).listarTitulares();
		
		Titular titularValidado = null;
		for (Titular titular : titulares) {
			if (titular.getNome().equals(inputNome) && titular.getSenha().equals(inputSenha)) {
				titularValidado = titular;
			}
		}
		
		if(titularValidado != null && titularValidado.getPerfil().equals("admin")) {
			HttpSession sessao = req.getSession();
			sessao.setAttribute("titularLogado", titularValidado);
			return "redirect:entrada?acao=AdminPage";
		} else if (titularValidado != null && !(titularValidado.getNome().equals("admin"))) {
			HttpSession sessao = req.getSession();
			sessao.setAttribute("titularLogado", titularValidado);
			return "redirect:entrada?acao=UserPage";
		} else  {
			return "forward:/formLogin.jsp";
		}

	}
	
}
