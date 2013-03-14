<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastrar novo Produto</title>

<link rel="stylesheet" href="<c:url value='/resource/css/bootstrap.min.css'/>">
</head>
<body>
<script type="text/javascript" src="<c:url value='/resource/js/jquery-1.8.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resource/js/bootstrap.min.js'/>"></script>
<div class="container" style="margin-left:20px">

<form action="adiciona" method="post">
	<legend>Cadastro de Produtos</legend>
	<label>Titulo:</label><input type="text" name="produto.titulo"/><br/>
	<label>Preco:</label><input type="text" name="produto.preco"/><br/>
	<label>Tipo:</label><input type="text" name="produto.tipo"/><br/>
	<button type="submit" class="btn">Salvar</button>&nbsp;<button type="button" class="btn">Voltar</button>
</form>
</div>
</body>	
</html>