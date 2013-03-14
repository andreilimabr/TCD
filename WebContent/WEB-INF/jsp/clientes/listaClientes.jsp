<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Clientes</title>

<link rel="stylesheet" href="<c:url value='/resource/css/bootstrap.min.css'/>">
</head>
<body>
<script type="text/javascript" src="<c:url value='/resource/js/jquery-1.8.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resource/js/bootstrap.min.js'/>"></script>
<div class="container" style="margin-left: 20px">
	<h4>Lista de Clientes</h4>
	<table class="table table-striped">
		<tr>
			<td><strong>Nome</strong></td>
			<td><strong>Endereco</strong></td>
			<td><strong>Cpf</strong></td>
			<td><strong>E-Mail</strong></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<c:forEach var="cliente" items="${clienteList}">
			<tr>
				<td>${cliente.nome}</td>
				<td>${cliente.endereco }</td>
				<td>${cliente.cpf }</td>
				<td>${cliente.email }</td>
				<td><a href="<c:url value='remove?cliente.id=${cliente.id}'/>">Remover</a></td>
				<td><a href="<c:url value='alteraCliente?cliente.id=${cliente.id}'/>">Alterar</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="<c:url value='cadastraCliente' />"><button class="btn">Cadastro de Clientes</button></a>
</div>
</body>
</html>