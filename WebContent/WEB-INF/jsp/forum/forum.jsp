<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forum - Toca de Cds/DVDs</title>

<link rel="stylesheet" href="<c:url value='/resource/css/bootstrap.min.css'/>">
</head>
<body>
<script type="text/javascript">
	function execute(action){
		$("#frmForum").attr("action",action);
		$("#frmForum").submit();
	}
	function goTopic(id,titulo){
		$("#idTopico").attr("value",id);
		$("#tituloTopico").attr("value",titulo);
	}
</script>
<script type="text/javascript" src="<c:url value='/resource/js/jquery-1.8.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resource/js/bootstrap.min.js'/>"></script>
<div class="container" style="margin-left: 20px;width:900px">
	<table class="table" style="width:530px;margin-bottom:10px">
		<tr>
			<td style="width:70%">Você está logado(a) como <strong>${usuario.nome}</strong></td>
			<td><a href='<c:url value="/indice"/>' style="padding-left: 33%">Home</a></td>
			<td><a href='<c:url value="/logout"/>' style="padding-left: 33%">Logout</a></td>
		</tr>	
	</table>
	<form class="form form-inline" method="post" id="frmForum">
	<table class="table" style="width:900px">
		<tr>
			<td style="width:70%"><input type="text" placeholder="Digite o Tópico aqui" style="width:500px" name="topico"/></td>
			<td><button class="btn" onclick="execute('<c:url value="/forum/busca"/>');">Buscar Tópico</button></td>
			<td><button class="btn" onclick="execute('<c:url value="/forum/criar"/>');">Criar Tópico</button></td>
		</tr>
	</table>		
	</form>
	<form action="<c:url value="/forum/discussao"/>" id="frmDiscussao" method="get">
	<table class="table table-striped" style="width: 900px;">
		<thead><strong>Tópicos</strong></thead>
		<c:forEach items="${topicoList}" var="item">
			<tr>
				<td  style="width:55%">
					<strong><button class="btn btn-link" onclick="goTopic(${item.id},'${item.titulo}');">${item.titulo}</button></strong> 
					<small class="text-info"><strong>${fn:length(item.posts)} Posts</strong></small>
				</td>
				<td>
					<small>Postado por:<strong>${item.usuarioAbertura.nome}</strong>
					<c:if test="${item.usuarioAbertura.administrador}">(Administrador)</c:if></small>
				</td>
				<td>
					<small>Em: <f:formatDate value="${item.dataAbertura.time}" pattern="dd/MM/yyyy HH:mm:ss"/></small>
				</td>
			</tr>
		</c:forEach>	
	</table>
	<input type="hidden" name="topico.id" id="idTopico" value="">
	<input type="hidden" name="topico.titulo" id="tituloTopico" value="">
	</form>
	
</div>
</body>
</html>