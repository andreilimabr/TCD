<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="<c:url value='/resource/css/bootstrap.min.css'/>">
</head>
<body>
<div class="container" style="margin-left: 20px">
<script type="text/javascript" src="<c:url value='/resource/js/jquery-1.8.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resource/js/bootstrap.min.js'/>"></script>
	<h4>Seu Carrinho</h4>	
	<table class="table" style="width: 900px;">
		<tr>
			<td style="width:80px"><strong>Codigo</strong></td>
			<td style="width:250px"><strong>Produto</strong></td>
			<td style="width:80px"><strong>Qtde</strong></td>
			<td style="width:120px"><strong>Total</strong></td>
			<td><strong>Ações</strong></td>
		</tr>
	</table>
	<c:set var="soma" value="${0.0}"/>
	<div id="listaItens" style="width: 900px;height: 150px;overflow-y:scroll;">	
		<table class="table table-striped" >
			<c:forEach var="item" varStatus="i" items="${itemCarrinhoComprasList}" >
					<tr>
						<td style="width:80px">${item.produto.id}</td>
						<td style="width:250px">${item.produto.titulo}</td>
						<td style="width:80px">${item.qtde}</td>
						<td style="width:120px">${item.preco}</td>
						<td><a href="<c:url value="carrinho/remove/item/${i.index}"/>">Remover</a></td>
						<c:set var="soma" value="${soma + item.preco}"/>
					</tr>
			</c:forEach>	
		</table>
	</div>
	<div id="sepTotais" style="width: 900px">
		<table class="table table-striped" >
				<tr>
					<td style="width:80px"><strong>Valor Total:</strong></td>
					<td style="width:250px"></td>
					<td style="width:80px"></td>
					<td style="width:120px"><f:formatNumber maxFractionDigits="2" value="${soma}"/></td>
					<td></td>
				</tr>
		</table>
	</div>
	<hr>
	<a href="<c:url value="indice"/>"><button class="btn" type="button">Voltar</button></a>
</div>
</body>
</html>
