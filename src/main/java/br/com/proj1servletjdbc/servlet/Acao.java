package br.com.proj1servletjdbc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Acao {
	
	public String executa (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;

}
