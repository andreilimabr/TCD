<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Produtos</title>

<link rel="stylesheet" href="<c:url value='/resource/css/bootstrap.min.css'/>">
</head>
<body>
<script type="text/javascript" src="<c:url value='/resource/js/jquery-1.8.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resource/js/bootstrap.min.js'/>"></script>
<div class="container" style="margin-left: 20px">
	<h4>Lista de Produtos</h4>
	<table class="table table-striped">
		<tr>
			<td><strong>Título</strong></td>
			<td><strong>Preço</strong></td>
			<td><strong>Tipo</strong></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<c:forEach var="produto" items="${produtoList}">
			<tr>
				<td>${produto.titulo}</td>
				<td>${produto.preco }</td>
				<td>${produto.tipo }</td>
				<td><a href="<c:url value='remove?produto.id=${produto.id}'/>">Remover</a></td>
				<td><a href="<c:url value='alteraProduto?produto.id=${produto.id}'/>">Alterar</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="<c:url value='cadastraProduto' />"><button class="btn">Cadastro de Produtos</button></a>
</div>
</body>
</html>