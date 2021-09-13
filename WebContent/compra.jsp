<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Compra</title>
</head>
<body>
	<div>
		<jsp:include page="menu.jsp" />
		<br />
	</div>
	<div>
		<form action="compra" method="post">
		<table>
			<tr>
				<td colspan="2"><input type="number" id="codigo" name="codigo" placeholder="Código">
			</tr>	
			<tr>
				<td colspan="2">
					<c:if test="${not empty listaClientes }">
						<select id="cliente" name="cliente">
							<option value="0">Selecione um Cliente</option>
							<c:forEach var="cli" items="${listaClientes }">
								<option value="${cli.id }">${cli }</option>
							</c:forEach>
						</select>
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<c:if test="${not empty listaProdutos }">
						<select id="produto" name="produto">
							<option value="0">Selecione um Produto</option>
							<c:forEach var="p" items="${listaProdutos }">
								<option value="${p.id }">${p }</option>
							</c:forEach>
						</select>
					</c:if>
				</td>
			</tr>	
			<tr>
				<td colspan="2"><input type="number" id="qtd" name="qtd" placeholder="Quantidade">
			</tr>
			<tr>
				<td><input type="submit" value="Cadastrar" id="button" name="button"></td>
				<td><input type="submit" value="Buscar" id="button" name="button"></td>
			</tr>
		</table>		
		</form>
	</div>
	<div>
		<c:if test="${not empty saida }">
			<p><c:out value="${saida }" /></p>
		</c:if>
		<c:if test="${not empty erro }">
			<H2 style="color: red"><c:out value="${erro }" /></H2>
		</c:if>
	</div>
	<div>
		<c:if test="${not empty listaCompras }">
			<p><c:out value="${listaCompras[0].codigo }"/> - 
				<c:out value="${listaCompras[0].cliente }"/> - 
				<c:out value="${listaCompras[0].data }"/>
			</p>
			<table border = 1>
				<thead>
					<tr>
						<th>ID Produto</th>
						<th>Nome Produto</th>
						<th>Valor Produto</th>
						<th>Qtd Comprada</th>
						<th>Valor Total</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="com" items="${listaCompras }">
						<tr>
							<td>${com.produtoComprado.produto.id }</td>
							<td>${com.produtoComprado.produto.nome }</td>
							<td>${com.produtoComprado.produto.valor }</td>
							<td>${com.produtoComprado.qtd }</td>
							<td>${com.produtoComprado.valorTotal }</td>
						</tr>
					</c:forEach>				
				</tbody>
			</table>			
		</c:if>
	</div>	
</body>
</html>