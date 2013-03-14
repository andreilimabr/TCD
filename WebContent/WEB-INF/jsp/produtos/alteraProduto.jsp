<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alteração dos dados do produto</title>

<link rel="stylesheet" href="<c:url value='/resource/css/bootstrap.min.css'/>">
</head>
<body>
<script type="text/javascript" src="<c:url value='/resource/js/jquery-1.8.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resource/js/bootstrap.min.js'/>"></script>
<div class="container" style="margin-left:20px">
<form action="altera" method="post">
	<legend>Alteração de Produtos</legend>	
	<label>Título:</label><input type="text" name="produto.titulo" value="${produto.titulo}" /><br/>
	<label>Preço:</label><input type="text" name="produto.preco" value="${produto.preco }"/><br/>
	<label>Tipo:</label><input type="text" name="produto.tipo" value="${produto.tipo }"/><br/>
	<input type="hidden" name ="produto.id" value="${produto.id}">
	<button type="submit" class="btn">Salvar</button>&nbsp;<a href="<c:url value='listaProdutos'/>"><button type="button" class="btn">Voltar</button></a>
</form>
</div>
</body>	
</html>