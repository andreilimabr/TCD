<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastrar novo usuário</title>

<link rel="stylesheet" href="<c:url value='/resource/css/bootstrap.min.css'/>">
</head>
<body>
<script type="text/javascript" src="<c:url value='/resource/js/jquery-1.8.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resource/js/bootstrap.min.js'/>"></script>
<script type="text/javascript">
	function adicionaUsuario(){
		var dados =$("#adicionaUsuario").serialize();
		var request =$.ajax({
			url:"adiciona",
			type:"POST",
			data: dados
		});
		
		request.done(
			function(data){
				alert("Usuário adicionado com sucesso!");
				$("#adicionaUsuario").get(0).reset();
				$("#nomeUsuario").focus();
			}
		);
		
		request.fail(
			function(data){
				alert("Usuário já consta na lista de usuários!");
			}	
		);
		
	}
</script>
<div class="container" style="margin-left:20px">
<form action="adiciona" method="post" id="adicionaUsuario">
	<legend>Cadastro de Usuários</legend>
	<label>Nome:</label><input type="text" name="usuario.nome" id="nomeUsuario" /><br/>
	<label>Senha:</label><input type="password" name="usuario.senha"/><br/>
	<label class="checkbox"><input type="checkbox" name="usuario.administrador" />Administrador</label><br/>
	<button type="button" class="btn" onclick="adicionaUsuario()">Salvar</button>
</form>

</div>
</body>	
</html>