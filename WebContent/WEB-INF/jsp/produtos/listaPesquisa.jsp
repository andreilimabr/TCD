<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pesquisa de Produtos</title>

<link rel="stylesheet" href="<c:url value='/resource/css/bootstrap.min.css'/>">
</head>
<body>
<script type="text/javascript" src="<c:url value='/resource/js/jquery-1.8.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resource/js/bootstrap.min.js'/>"></script>
<script type="text/javascript">
	function adiciona(page,id){
		$.get(page + "?item.id=" +id, function(data){
				
			}
		);	
	}
</script>
<div class="container" style="margin-left: 20px">
	<h4>Pesquisa de Produtos</h4>
	<table class="table table-striped">
		<tr>
			<td></td>
			<td>Descrição</td>
			<td><strong>Título</strong></td>
			<td><strong>Preço</strong></td>
			<td><strong>Tipo</strong></td>
			<td>&nbsp;</td>
		</tr>
		<c:forEach var="item" items="${itemVitrineList}">
			<tr>
				
				<td><img src="<c:url value='/resource/img/${item.produto.id}.jpg'/>" class="img-polaroid" style="width: 100px" /></td>
				<td>${item.produto.titulo} - ${item.vitrine.descricao}</td>
				<td>${item.preco }</td>
				<td>${item.produto.tipo }</td>
				<td><a href="#" onclick="adiciona('<c:url value='/vitrine/comprar'  />',${item.id},this)" >Comprar</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="#">&lt;&lt;</a>
	<a href="#">&lt;</a>
	<c:forEach begin="1" end="${numeroPaginas + 1}" step="1" var="i">
		<a href="listaPesquisa?crit=${criterio}&comeco=${i-1}">${i }</a>
	</c:forEach>
	<a href="#">&gt;</a>
	<a href="#">&gt;&gt;</a>
	<hr/>
	<a href="<c:url value='/indice' />" ><button class="btn">Voltar</button></a>
</div>
</body>
</html>