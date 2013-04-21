<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forum - Toca de Cds/DVDs</title>

<link rel="stylesheet" href="<c:url value='/resource/css/bootstrap.min.css'/>">
</head>
<body>

<script type="text/javascript" src="<c:url value='/resource/js/jquery-1.8.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resource/js/bootstrap.min.js'/>"></script>
<div class="container" style="margin-left: 20px;width:900px">
	<table class="table" style="width:530px;margin-bottom:10px">
		<tr>
			<td style="width:70%">Você está logado(a) como <strong>${usuario.nome}</strong></td>
			<td><a href='<c:url value="/indice"/>' style="padding-left: 33%">Home</a></td>
			<td><a href='<c:url value="/logout"/>' style="padding-left: 33%">Logout</a></td>
			<td><a href='<c:url value="/forum/indice"/>' style="padding-left: 33%">Tópicos</a></td>
		</tr>	
	</table>
	<h4>Lista de Discussão - Tópico: <b class="text-info">${topico.titulo}</b></h4>	
	<hr/>
	<form action="<c:url value="/forum/postar"/>" name="frmPostar" method="post">
		<textarea rows="3" name="post.postagem" style="width:500px"></textarea><br/>
		<input type="hidden" name="post.topico.id" value ="${topico.id}">
		<button type="submit" class="btn btn-success">Postar</button>
		<button type="reset" class="btn">Limpar</button>
	</form>
	<table class="table table-striped" style="width: 900px;">
		<c:forEach items="${postList}" var="item">
			<tr>
				<td >
					Postado por: ${item.usuarioPostagem.nome} em <f:formatDate value="${item.dataPostagem.time}" pattern="dd/MM/yyyy HH:mm:ss"/> 
				</td>
			</tr>
			<tr>	
				<td>
					${item.postagem}
				</td>
			</tr>
		</c:forEach>	
	</table>
	
	
</div>
</body>
</html>