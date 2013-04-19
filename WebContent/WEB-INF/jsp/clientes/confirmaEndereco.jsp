<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Identificação do Cliente</title>

<link rel="stylesheet" href="<c:url value='/resource/css/bootstrap.min.css'/>">
</head>
<body>
<script type="text/javascript" src="<c:url value='/resource/js/jquery-1.8.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resource/js/bootstrap.min.js'/>"></script>
<div class="container" style="margin-left:20px">
<form action="<c:url value="/compra/pagamento"/>" method="post">
	<legend>Confirme se seus dados estão corretos</legend>	
	<p>Nome:</p><p class="text-info">${cliente.nome}</p><br/>
	<p>Endereco:</p><p class="text-info">${cliente.endereco }</p><br/>
	<p>CPF:</p><p class="text-info">${cliente.cpf }</p><br/>
	<p>E-Mail:</p><p class="text-info">${cliente.email }</p><br/>
	<p>Cep:</p><p class="text-info">${cliente.cep }</p><br/>
	<input type="hidden" name ="cliente.id" value="${cliente.id}">
	<input type="hidden" name ="cliente.usuario.id" value="${cliente.usuario.id}">	
	<button type="submit" class="btn btn-success">Prosseguir</button>&nbsp;<a href="<c:url value='/compra/identificacao/cliente'/>" class="btn">Corrigir</a>
</form>
</div>
</body>	
</html>