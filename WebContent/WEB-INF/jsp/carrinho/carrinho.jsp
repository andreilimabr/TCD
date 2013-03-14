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
			<td style="width: 200px"><strong>Produto</strong></td>
			<td style="width:80px"><strong>Qtde</strong></td>
			<td><strong>Total</strong></td>
			<td>Ações</td>
		</tr>
	</table>
	<div id="listaItens" style="width: 900px;height: 150px;overflow-y: scroll;">	
		<table class="table table-striped">
			<c:forEach var="i" items="${itemCarrinhoComprasList}" >
				<tr>
					<td style="width: 80px">${i.produto.id}</td>
					<td style="width: 200px">${i.produto.titulo}</td>
					<td style="width:80px">${i.qtde}</td>
					<td>${i.preco}</td>
					<td><a href="<c:url value="carrinho/remove/item/${i.produto.id}"/>">Remover</a></td>
					<c:set var="soma" value="${soma + i.preco}"/>
				</tr>
			</c:forEach>	
		</table>
	</div>
	<table class="table">
		<tr><td style="width: 380px"><strong>Total geral:</strong></td><td><f:formatNumber maxFractionDigits="2" value=" ${soma}"/></td></tr>
	</table>
	
	<a href="<c:url value="indice"/>"><button style="btn">Voltar</button></a>
</div>
</body>
</html>