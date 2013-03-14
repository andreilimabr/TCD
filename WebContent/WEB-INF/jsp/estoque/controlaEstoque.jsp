<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Controle de Estoque</title>

<link rel="stylesheet" href="<c:url value='/resource/css/bootstrap.min.css'/>">
</head>
<body>
<script type="text/javascript" src="<c:url value='/resource/js/jquery-1.8.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resource/js/bootstrap.min.js'/>"></script>
<script type="text/javascript">
	function adicionaProduto(){
		var dados =$("#formIncluiEstoque").serialize();
		var request =$.ajax({
			url:"adicionaProdutoEstoque",
			type:"POST",
			data: dados
		});
		
		request.done(
			function(data){
				$("#formIncluiEstoque").get(0).reset();
				$("#listaItensEstoque").html(data);	
			}
		);
		
		request.fail(
			function(data){
				alert("Produto já consta na lista de estoque!");
			}	
		);
		
	}
	
	function excluiEstoque(id){
		var request =$.ajax({
			url:"excluiEstoque?estoque.id=" + id,
			type:"POST"
		});
		
		request.done(
			function(data){
				$("#formIncluiEstoque").get(0).reset();
				$("#listaItensEstoque").html(data);	
			}
		);
	}
</script>
<div class="container"  style="margin-left:20px">
	<h4>Controle de Estoque</h4>
	<form action="adicionaProdutoEstoque" method="post" id="formIncluiEstoque">
		<label>Produto:</label>
		<select name="estoque.produto.id" id="estoque.produto.id">	
			<c:forEach var="p" items="${produto}">
				<option value="${p.id}" >${p.titulo}</option>
			</c:forEach>
		</select>
		<label>Qtde. em Estoque:</label><input type="text" name="estoque.qtd">
		<label>Qtde. <font color="red">Mínima</font> em Estoque:</label><input type="text" name="estoque.qtdMin"><br/>
		<a href="#" onclick="adicionaProduto()" ><button class="btn" type="button"> Grava </button></a>
	</form>

          
	<hr style="width: 900px"/>
	<h4>Produtos em estoque:</h4>
	<table class="table" style="width: 900px;">
		<tr>
			<td style="width: 266px"><strong>Produto</strong></td>
			<td style="width:180px"><strong>Qtde</strong></td>
			<td style="width: 84px"><strong>Qtd.Mín.</strong></td>
			<td><strong>Ação</strong>
		</tr>
	</table>
	<div id="listaItensEstoque" style="width: 900px;height: 150px;overflow-y: scroll">
		<table class="table table-striped">
			<c:forEach items="${estoque}" var="e">
				<tr>
					<td style="width: 266px">${e.produto.titulo }</td>
					<td style="width:180px">${e.qtd }</td>
					<td style="width: 84px">${e.qtdMin }</td>
					<td><a href="#" onclick="excluiEstoque(${e.id })">Remover</a></td>
				</tr>
			</c:forEach>
		</table>	
	</div>
</div>
</body>
</html>