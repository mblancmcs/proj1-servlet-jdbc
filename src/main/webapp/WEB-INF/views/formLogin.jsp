<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/entrada" var="linkServlet" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>

	<form action="${linkServlet}" method="post" >
		Nome: <input type="text" name="nome" /><br />
		Senha: <input type="password" name="senha" />
		<input type="hidden" name="acao" value="ValidaLogin" /><br />
		<button type="submit">Logar</button>
	</form>

</body>
</html>