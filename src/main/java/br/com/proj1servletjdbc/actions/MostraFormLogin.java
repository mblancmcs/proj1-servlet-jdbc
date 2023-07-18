package br.com.proj1servletjdbc.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.proj1servletjdbc.servlet.Acao;

@WebServlet("/MostraFormLogin")
public class MostraFormLogin implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return "forward:formLogin.jsp";
		
	}

}
