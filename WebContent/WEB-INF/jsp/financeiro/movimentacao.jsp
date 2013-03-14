<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix ="fmt"%>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movimentacoes Financeiras</title>

<link rel="stylesheet" href="<c:url value='/resource/css/bootstrap.min.css'/>">
</head>
<body>
<script type="text/javascript" src="<c:url value='/resource/js/jquery-1.8.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resource/js/bootstrap.min.js'/>"></script>

<div class="container"  style="margin-left:20px">
	<h4>Movimentações financeiras</h4>
	<form action="movimentacao" method="post" >
		<label>Cliente:</label>
		<select name="cliente.id" id="clienteid">	
			<c:forEach var="cliente" items="${listaCliente}">
				<option value="${cliente.id}" >${cliente.nome}</option>
			</c:forEach>
		</select>	
		<br/><button class="btn" type="submit"> Filtrar </button>
	</form>

          
	<hr style="width: 900px"/>
	<h4>Lista de Movimentações:</h4>
	<table class="table" style="width: 900px;">
		<tr>
			<td style="width: 76px" ><strong>Vlr.Pago</strong></td>
			<td style="width: 76px"><strong>Troco</strong></td>
			<td style="width: 76px"><strong>Data</strong></td>
			<td style="width: 159px"><strong>Status</strong></td>
			<td style="width: 76px"><strong>Total</strong></td>
			<td style="width: 159px"><strong>Cliente</strong></td>
			<td style="width: 124px"><strong>Ação</strong>
		</tr>
	</table>
	<div id="listaMovimentacoes" style="width: 900px;height: 150px;overflow-y: scroll">
		<table class="table table-striped">
			<c:forEach items="${listaMovimentacao}" var="item">
				<tr>
					<td style="width: 76px">${item.valorPago}</td>
					<td style="width: 76px">${item.troco }</td>
					<td style="width: 76px"><fmt:formatDate value="${item.data.time }" pattern="dd/MM/yyyy" /></td>
					<td style="width: 159px">${item.pedido.status }</td>
					<td style="width: 76px">${item.pedido.total }</td>
					<td style="width: 159px">${item.pedido.cliente.nome }</td>
					<td style="width: 124px"><a href="remove?mov.id=${item.id }">Remover</a></td>
				</tr>
			</c:forEach>
		</table>	
	</div>
</div>
</body>
</html>