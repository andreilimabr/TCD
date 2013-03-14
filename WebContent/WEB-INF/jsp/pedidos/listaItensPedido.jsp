<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix ="fmt"%> 
<table class="table table-striped">   
	<c:forEach var="item" items="${itensPedido}">
		<tr>
			<td>${item.produto.titulo }</td>
			<td>${item.unitario }</td>			
			<td>${item.qtde }</td>
			<td>${item.total }</td>
		</tr>	
	</c:forEach>
</table>	
