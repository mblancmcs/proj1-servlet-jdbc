<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>

	<ul>
		<li>${titularLogado.nome}</li>
		<li>${titularLogado.cpf}</li>
		<li>${titularLogado.email}</li>
		<li>${titularLogado.endereco}</li>
		<li>${titularLogado.perfil}</li>
	</ul>

	<h2>Lista de contas e seus titulares</h2>
	<ul>
		<c:forEach items="${listaContasETitular}" var="contaETitular">
			<li>${contaEtitular.toString()}</li>
		</c:forEach>
	</ul>
	
	<form action="entrada?acao=Cadastro" method="post">
		<h2>Titular</h2>
		Nome:<input type="text" name="nome" /><br />
		CPF:<input type="number" max="99999999999" name="cpf" /><br />
		E-mail:<input type="text" name="email" /><br />
		Endereco:<input type="text" name="endereco" /><br />
		Senha:<input type="text" name="senha" /><br />
	
		<h2>Conta</h2>
		Numero:<input type="text" name="numero" /><br />
		Agencia:<input type="text" name="agencia" /><br />
		Deposito inicial:<input type="text" name="depositoInicial" /><br />
		
		<button type="submit" >Cadastrar</button>
	</form>

</body>
</html>