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
<div class="container" style="margin-left: 20px;">
	<div class="row-fluid">
		<div class="span6">
			<form action="<c:url value='autenticar'/>" method="post" id="loginAcesso">
				<legend>Já Sou cliente</legend>
				<label>Nome:</label><input type="text" name="usuario.nome" id="nomeUsuario" /><br/>
				<label>Senha:</label><input type="password" name="usuario.senha"/><br/>
				<input type="hidden" name="uriTo" value="${uriTo}">
				<button class="btn" type="submit">Entrar</button>
			</form>
			<c:if test="${erro}">
				 <div class="alert" style="width: 400px">
				    <button type="button" class="close" data-dismiss="alert">×</button>
				    <strong>Atenção!</strong> Usuário ou senha inválida.
				</div>
			</c:if>
		</div>
		<div class="span6">
			<form action="<c:url value='clientes/cadastro'/>" method="post" id="loginAcesso">
				<legend>Ainda não sou cliente</legend>
				<label>Informe seu nome:</label><input type="text" name="cliente.nome" id="nomeUsuario" /><br/>
				<input type="hidden" name="uriTo" value="${uriTo}">
				<button class="btn btn-success" type="submit">Cliente Novo</button>
			</form>
		</div>
	</div>
</div>
</body>
</html>