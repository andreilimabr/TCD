<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix ="fmt"%> 
<table class="table table-striped">   
	<c:forEach var="item" items="${estoque}">
		<tr>
			<td style="width: 266px">${item.produto.titulo }</td>
			<td style="width: 180px">${item.qtd }</td>			
			<td>${item.qtdMin }</td>
			<td><a href="<c:url value='excluiEstoque?estoque.id=${item.id }'/>">Remover</a></td>
		</tr>	
	</c:forEach>
</table>	
