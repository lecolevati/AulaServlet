<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Produto</title>
</head>
<body>
	<div>
		<jsp:include page="menu.jsp" />
		<br />
	</div>
	<form action="produto" method="post">
	<table>
		<tr>
			<td colspan="3"><input type="number" id="id" name="id" placeholder="ID"
				value="${produto.id }"></td>
			<td><input type="submit" value="Buscar" id="button" name="button"></td>
		</tr>
		<tr>
			<td colspan="4"><input type="text" id="nome" name="nome" placeholder="Nome" size="40"
				value="${produto.nome }"></td>
		</tr>
		<tr>
			<td colspan="4"><input type="number" step="0.01" id="valor" name="valor" placeholder="Valor" size="40"
				value="${produto.valor }"></td>
		</tr>
		<tr>
			<td><input type="submit" value="Cadastrar" id="button" name="button"></td>
			<td><input type="submit" value="Atualizar" id="button" name="button"></td>
			<td><input type="submit" value="Excluir" id="button" name="button"></td>
			<td><input type="submit" value="Listar" id="button" name="button"></td>
		</tr>
	</table>
	</form>
	<div>
		<c:if test="${not empty saida }">
			<p><c:out value="${saida }" /></p>
		</c:if>
		<c:if test="${not empty erro }">
			<h2 style="color: red"><c:out value="${erro }" /></h2>
		</c:if>
	</div>
	<div>
		<c:if test="${not empty listaProdutos }">
			<table border="1">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nome</th>
					<th>Valor</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="p" items="${listaProdutos }">
				<tr>
					<td>${p.id }</td>
					<td>${p.nome }</td>
					<td>${p.valor }</td>
				</tr>
			</c:forEach>
			</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>