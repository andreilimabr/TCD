<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Altera��o dos dados do cliente</title>

<link rel="stylesheet" href="<c:url value='/resource/css/bootstrap.min.css'/>">
</head>
<body>
<script type="text/javascript" src="<c:url value='/resource/js/jquery-1.8.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resource/js/bootstrap.min.js'/>"></script>
<div class="container" style="margin-left:20px">
<form action="altera" method="post">
	<legend>Altera��o de clientes</legend>	
	<label>Nome:</label><input type="text" name="cliente.nome" value="${cliente.nome}" /><br/>
	<label>Endereco:</label><input type="text" name="cliente.endereco" value="${cliente.endereco }"/><br/>
	<label>CPF:</label><input type="text" name="cliente.cpf" value="${cliente.cpf }"/><br/>
	<label>E-Mail:</label><input type="text" name="cliente.email" value="${cliente.email }"/><br/>
	<input type="hidden" name ="cliente.id" value="${cliente.id}">
	<button type="submit" class="btn">Salvar</button>&nbsp;<a href="<c:url value='listaClientes'/>"><button type="button" class="btn">Voltar</button></a>
</form>
</div>
</body>	
</html>