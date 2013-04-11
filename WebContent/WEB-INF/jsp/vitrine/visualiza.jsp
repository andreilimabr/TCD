<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
		<table class="table table-bordered">
			<c:forEach items="${vitrineList }" var="item">
				<tr>
				 	<td><strong>${item.produto.titulo }</strong></td>
				 	<td>R$ ${item.preco }</td>
				 	<c:if test="${item.noCarrinho}">
				 		<td><img src="<c:url value='/resource/img/ico_carrinho_peq.gif' />" /></td>
				 	</c:if>
				 	<c:if test="${not item.noCarrinho}">
				 		<td id="${item.vitrine.categoria}_${item.id }"><a href="#" onclick="comprar(${item.id },'${item.vitrine.categoria}')">Comprar</a></td>
				 	</c:if>
				</tr> 
			</c:forEach>
		</table>
	