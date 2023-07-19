package br.com.proj1servletjdbc.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/entrada")
public class Autorizacao extends HttpFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String acaoParametro = request.getParameter("acao");
		boolean acaoProtegida = !(acaoParametro.equals("LoginForm") || acaoParametro.equals("ValidaLogin"));
		
		HttpSession sessao = request.getSession();
		if(acaoProtegida && sessao.getAttribute("titularLogado") == null) {
			response.sendRedirect("entrada?acao=LoginForm");
			return;
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

}
