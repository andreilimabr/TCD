<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Identificação do cliente</title>

<link rel="stylesheet" href="<c:url value='/resource/css/bootstrap.min.css'/>">
</head>
<body>
<script type="text/javascript" src="<c:url value='/resource/js/jquery-1.8.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resource/js/bootstrap.min.js'/>"></script>
<div class="container" style="margin-left:20px">
<form action="adiciona" method="post">
	<legend>Novo cliente</legend>
	<label>Nome:</label><input type="text" name="cliente.nome" value="${novoCliente.nome}" /><br/>
	<label>Endereco:</label><input type="text" name="cliente.endereco"/><br/>
	<label>CPF:</label><input type="text" name="cliente.cpf"/><br/>
	<label>E-Mail:</label><input type="text" name="cliente.email"/><br/>
	<label>Cep:</label><input type="text" name="cliente.cep"/><br/>
	<hr/>
	<label>Senha:</label><input type="password" name="cliente.usuario.senha"/><br/>
	<label>Confirma Senha:</label><input type="password" name="confirmaSenha"/><br/>	
	<c:if test="${erro}">
		 <div class="alert" style="width: 400px">
		    <button type="button" class="close" data-dismiss="alert">×</button>
		    <strong>Atenção!</strong> senhas não conferem
		</div>
	</c:if>
	<c:if test="${erroExiste}">
		 <div class="alert" style="width: 400px">
		    <button type="button" class="close" data-dismiss="alert">×</button>
		    <strong>Atenção!</strong> Usuário já existe.
		</div>
	</c:if>
	<button type="submit" class="btn btn-success">Confirmar</button>&nbsp;<a href="<c:url value='listaClientes'/>"><button type="button" class="btn">Voltar</button></a>
</form>

</div>
</body>	
</html>