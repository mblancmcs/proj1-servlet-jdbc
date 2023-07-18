package br.com.proj1servletjdbc.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.proj1servletjdbc.model.ConnectionFactory;
import br.com.proj1servletjdbc.model.Titular;
import br.com.proj1servletjdbc.model.TitularDAO;

@WebServlet("/getTitulares")
public class APITitulares extends HttpServlet {
	
	public void service (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		ConnectionFactory connection = new ConnectionFactory();
		Connection conn = connection.recuperaConexao();
		
		List<Titular> listaTitulares = new TitularDAO(conn).listarTitulares();
		
		String accept = request.getHeader("Accept");
		System.out.println(accept);
		
		if(accept.contains("json")) {
			
			Gson gson = new Gson();
			String json = gson.toJson(listaTitulares);
			System.out.println(json);
			
			response.setContentType("application/json");
			response.getWriter().print(json);
			
		} else {
			
			response.getWriter().print("{message:'no content'}");
			
		}
		
	}

}
