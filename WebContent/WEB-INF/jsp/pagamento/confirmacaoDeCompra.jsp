<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Obrigado por nos escolher!</title>

<link rel="stylesheet" href="<c:url value='/resource/css/bootstrap.min.css'/>">
</head>
<body>
<script type="text/javascript" src="<c:url value='/resource/js/jquery-1.8.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resource/js/bootstrap.min.js'/>"></script>
<div class="container" style="margin-left: 20px">
	<div class="hero-unit" style="width:600px">
	    <h3>Sua compra foi realizada com sucesso!</h3>
	    <p>Guarde os dados do seu pedido:</p>
	    <p>Id do Pedido: <b class="text-info">${pedido.id}</b>  -  Cliente: <b class="text-info">${pedido.cliente.nome}</b>  - Data: <b class="text-info"><f:formatDate value="${pedido.data.time}" pattern="dd/MM/yyyy" /></b>   -  Valor:<b class="text-info"><f:formatNumber value="${pedido.total}" maxFractionDigits="2"/></b><br/> 
	    <p>
	    <p>Seu pedido está em fase de aprovação e logo será liberado para entrega.
	    <p>Endereço de Entrega: <b class="text-info">${pedido.cliente.endereco}</b> - CEP: <b class="text-info">${pedido.cliente.cep}</b>
	    <p>
	    <a class="btn btn-primary" href='<c:url value="/indice"/>'>
	    	Voltar á loja
	    </a>
	    </p>
    </div>
</div>	
</body>
</html>