<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix ="fmt"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Pedidos</title>

<link rel="stylesheet" href="<c:url value='/resource/css/bootstrap.min.css'/>">
</head>
<body>
<script type="text/javascript" src="<c:url value='/resource/js/jquery-1.8.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resource/js/bootstrap.min.js'/>"></script>
<script type="text/javascript">
	function listaItensPedido(id){
		$.get("listaItensPedido?pedido.id=" + id,
			function(data){
				$("#listaItensPedido").html(data);
			}
		);
	}
</script>
<div class="container" style="margin-left: 20px">
	<h4>Lista de Pedidos</h4>	
	<table class="table" style="width: 900px;">
		<tr>
			<td><strong>Nome</strong></td>
			<td><strong>Data</strong></td>
			<td><strong>Status</strong></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	</table>
	<div id="listaPedidos" style="width: 900px;height: 150px;overflow-y: scroll;">	
	<table class="table table-striped">
		<c:forEach var="pedido" items="${pedidoList}">
			<tr>
				<td>${pedido.cliente.nome}</td>
				<td><fmt:formatDate value="${pedido.data.time }" pattern="dd/MM/yyyy"/></td>
				<td>${pedido.status }</td>
				<td><a href="<c:url value='remove?pedido.id=${pedido.id}'/>">Remover</a></td>
				<td><a href="<c:url value='alteraPedido?pedido.id=${pedido.id}'/>">Alterar</a></td>
				<td><a href="#" onclick="listaItensPedido(${pedido.id})">Itens</a></td>
			</tr>
		</c:forEach>
	</table>
	</div>
	<hr style="width: 900px;">
	<h4>Itens do Pedido</h4>	
	<table class="table" style="width: 900px;">
		<tr>
			<td><strong>Produto</strong></td>
			<td><strong>Vlr Unit.</strong></td>
			<td><strong>Qtde</strong></td>
			<td><strong>Vlr. Total</strong></td>
		</tr>
	</table>
	<div id="listaItensPedido" style="width: 900px;height: 150px;overflow-y: scroll">
	
	</div>
</div>
</body>
</html>