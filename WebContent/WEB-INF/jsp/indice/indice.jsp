<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mundo da música</title>

<link rel="stylesheet" href="<c:url value='/resource/css/bootstrap.min.css'/>">
</head>
<body>
<script type="text/javascript" src="<c:url value='/resource/js/jquery-1.8.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resource/js/bootstrap.min.js'/>"></script>
<script type="text/javascript">
	function comprar(id,lista){
		
		$("#" + lista).html("<img src=<c:url value="/resource/img/lightbox-ico-loading.gif"/> />");
		
		var request =$.ajax({
			url:"vitrine/comprar",
			type:"POST",
			data: "item.id=" + id,
			context: $("#" + lista)
		});
		
		request.done(
			function(data){
				$(this).html(data);	
			}
		);
		
		request.fail(
			function(data){
				alert("Não foi possível adicionar o produto no carrinho!");
			}	
		);
	}
	
	function buscaProduto(url){
		$("#btnBusca").attr("disabled",true);
		urlComp = url + $("#crit").attr("value");
		$("#frmPesquisa").attr("action",urlComp);
		$("#frmPesquisa").submit();
		$("#btnBusca").attr("disabled",false);
	}
	

	
</script>
<div class="container-fluid" style="margin-left: 20px;margin-top: 10px">
	<div class="row-fluid">
		<div class ="span2">
		<!-- conteudo da sidebar -->
			<table border="0">
				<tr><td><img src="<c:url value='/resource/img/cd_dvd_drive_w.png' />" style="width: 150px;height: 150px" /></td></tr>
				<tr><td style="width: 25px"><button class="btn" style="width: 150px">Home</button></td></tr>
				<tr><td style="width: 25px"><button class="btn" style="width: 150px">CDs</button></td></tr>
				<tr><td style="width: 25px"><button class="btn" style="width: 150px">DVDs</button></td></tr>
				<tr><td style="width: 25px"><button class="btn" style="width: 150px">Blue Rays</button></td></tr>
				<tr><td style="width: 25px"><button class="btn" style="width: 150px">Boxes</button></td></tr>
				<tr><td style="width: 25px"><hr style="width: 150px" /></td></tr>
				<tr><td style="width: 25px"><button class="btn" style="width: 150px">Trocar CDs</button></td></tr>
				<tr><td style="width: 25px"><button class="btn" style="width: 150px">Comunidade</button></td></tr>
			</table>
		</div>
		<div class="span10">
		<!-- conteúdo do corpo -->
			<table border="0">
				<tr>
					<!--  menu superior -->
					<td> 
					    <form class="form-search" id="frmPesquisa">
						    <input type="text" class="input-medium search-query" placeholder="buscar produto" id="crit" name="crit">
						    <!-- <input type="hidden" name="tamanho" value="5"> -->
						    <button class="btn" onclick="buscaProduto('<c:url value='/produtos/busca/' />')" id="btnBusca">Buscar</button>
					    </form>
					</td>
					<td style="width:50px">
					</td>
					<td>
						<form action="autenticar" method="post" class="form-inline">
							<input type="text" class="input-small"  placeholder="Usuário" name="usuario.nome">
							<input type="password" class="input-small"  placeholder="Senha" name="usuario.senha">
							<button class="btn" type="submit">Login</button>	
						</form>
					</td>
					<td style="width:20px">
					</td>
					<td>
						<form action="#" class="form-inline">
							<c:if test="${usuarioLogado eq '' }">
								<button class="btn" >Cadastrar-se</button>&nbsp;&nbsp;
							</c:if>
							<c:if test="${usuarioLogado ne '' }">
								<P><em>${usuarioLogado}</em><br>
							</c:if>
							<a href="<c:url value="carrinho"/>">Meu Carrinho</a><br/>
						</form>
					</td>
				</tr>
				<tr>
					<!--  Destaques -->
					<td colspan="5">
						 <div id="myCarousel" class="carousel slide">
						    <!-- Carousel items -->
						    <div class="carousel-inner">
						    	<c:forEach items="${destaqueList}" var="item" varStatus="status">
								    <div class="<c:if test='${status.index eq 0 }'>active item</c:if><c:if test='${status.index gt 0 }'>item</c:if>">
								    	<img src="<c:url value='/resource/img/${item.produto.id}.jpg'/>" style="width: 300px;height: 300px" />
								    	<div class="carousel-caption">
								    		<div class="row" style="margin-left: 10px">
								    			<div class="span4" >
										    			<h4>Destaque Especial</h4>
										    			<p>${item.produto.titulo }
										    	</div>
									    		<div class="span3 offset2">
									    			<h1><p align="right">R$ <fmt:formatNumber type="number" value="${item.preco }" minFractionDigits="2" maxFractionDigits="2"/></p></h1>	
									    		</div>
									    	</div>
								    	</div>
								    </div>
								</c:forEach>    
						    </div>
						    <!-- Carousel nav -->
						    <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
						    <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<h4>Lançamentos</h4>
						<div id="${novoList[0].vitrine.categoria }">
							<table class="table table-bordered">
								<c:forEach items="${novoList }" var="item">
									<tr>
									 	<td><strong>${item.produto.titulo }</strong></td>
									 	<td>R$ <fmt:formatNumber type="number" value="${item.preco }" minFractionDigits="2" maxFractionDigits="2"/></td>
									 	<c:if test="${item.noCarrinho}">
									 		<td><img src="<c:url value='/resource/img/ico_carrinho_peq.gif' />" /></td>
									 	</c:if>
									 	<c:if test="${not item.noCarrinho}">
									 		<td><a href="#" onclick="comprar(${item.id },'${item.vitrine.categoria}')">Comprar</a></td>
									 	</c:if>
									</tr> 
								</c:forEach>
							</table>
						</div>
					</td>
					<td colspan="2" style="background-color:#EEEEEE">
						<h4>Ofertas</h4>
						<div id="${ofertasList[0].vitrine.categoria }">
							<table class="table table-bordered">
								<c:forEach items="${ofertasList }" var="item">
									<tr>
									 	<td><strong>${item.produto.titulo }</strong></td>
									 	<td>R$ <fmt:formatNumber type="number" value="${item.preco }" minFractionDigits="2" maxFractionDigits="2"/></td>
									 	<c:if test="${item.noCarrinho}">
									 		<td><img src="<c:url value='/resource/img/ico_carrinho_peq.gif' />" /></td>
									 	</c:if>
									 	<c:if test="${not item.noCarrinho}">
									 		<td><a href="#" onclick="comprar(${item.id },'${item.vitrine.categoria}')">Comprar</a></td>
									 	</c:if>
									</tr> 
								</c:forEach>
							</table>
						</div>
					</td>
					<td colspan="1">
						<h4>Mais Vendidos</h4>
						<div id="${vendidosList[0].vitrine.categoria }">
							<table class="table table-bordered">
								<c:forEach items="${vendidosList }" var="item">
									<tr>
									 	<td><strong>${item.produto.titulo }</strong></td>
									 	<td><img src="<c:url value='/resource/img/${item.produto.id}.jpg'/>" class="img-polaroid" style="width: 50px" /></td>
									</tr> 
								</c:forEach>
							</table>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
</body>
</html>