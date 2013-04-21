<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Informações de Pagamento</title>

<link rel="stylesheet" href="<c:url value='/resource/css/bootstrap.min.css'/>">
</head>
<body>
<script type="text/javascript" src="<c:url value='/resource/js/jquery-1.8.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resource/js/bootstrap.min.js'/>"></script>
<div class="container" style="margin-left: 20px">
	<h4>Seu Carrinho</h4>
	<table class="table" style="width: 700px;">
		<tr>
			<td style="width:80px"><strong>Codigo</strong></td>
			<td style="width:250px"><strong>Produto</strong></td>
			<td style="width:80px"><strong>Qtde</strong></td>
			<td style="width:120px"><strong>Total</strong></td>
			<td></td>
		</tr>
	</table>
	<c:set var="soma" value="${0.0}"/>
	<div id="listaItens" style="width: 700px;height: 150px;overflow-y:scroll;">	
		<table class="table table-striped table-bordered table-condensed" >
			<c:forEach var="item" varStatus="i" items="${itemCarrinhoComprasList}" >
					<tr id="row${i.index}">
						<td style="width:80px">${item.produto.id}</td>
						<td style="width:250px">${item.produto.titulo}</td>
						<td style="width:80px">${item.qtde}</td>
						<td style="width:120px"><f:formatNumber maxFractionDigits="2" value="${item.preco}"/></td>
						<td></td>
						<c:set var="soma" value="${soma + item.preco}"/>
					</tr>
			</c:forEach>	
		</table>
	</div>
	<div id="sepTotais" style="width: 700px">
		<table class="table table-striped table-bordered table-condensed" >
				<tr>
					<td style="width:80px"><strong>Valor Total:</strong></td>
					<td style="width:250px"></td>
					<td style="width:80px"></td>
					<td style="width:120px"  id="txtSoma" ><f:formatNumber maxFractionDigits="2" value="${soma}"/></td>
					<td></td>
				</tr>
		</table>
		<hr/>
		<h5><strong>Sua compra está quase concluída, escolha o seu tipo de pagamento:</strong></h5>
		<label class="radio inline">
		<input type="radio" name="optionsRadios" id="optionsRadios1" value="boleto" checked>
			Boleto Bancário
		</label>
		<label class="radio inline">
		<input type="radio" name="optionsRadios" id="optionsRadios2" value="Cartão de crédito">
			Cartão de crédito
		</label>
		<hr/>
		<a href="<c:url value="/indice" />" class="btn" >Continuar Comprando</a>&nbsp;<a class="btn btn-success" href='<c:url value="/compra/pedido/gerapedido"/>' >Avançar</a>
	</div>
	
	
</div>
</body>
</html>