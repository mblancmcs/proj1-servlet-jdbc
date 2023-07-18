package br.com.proj1servletjdbc.actions;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.taglibs.standard.tag.el.fmt.RequestEncodingTag;

import br.com.proj1servletjdbc.model.ConnectionFactory;
import br.com.proj1servletjdbc.model.ContaDAO;
import br.com.proj1servletjdbc.model.Titular;
import br.com.proj1servletjdbc.servlet.Acao;

public class AdminPage implements Acao {
	
	public String executa (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession sessao = request.getSession();
		Titular titularLogado = (Titular) sessao.getAttribute("titularLogado");
		
		ConnectionFactory connection = new ConnectionFactory();
		Connection conn = connection.recuperaConexao();
		
		String listaContas = new ContaDAO(conn).listarContasETitular();

		System.out.println(listaContas);
		System.out.println(titularLogado);
		
		request.setAttribute("listaContasETitular", listaContas); // envia um obj, tem que enviar uma string ou mudar para obj na classe dao
		
		return "forward:/admin/home.jsp";
		
	}

}
