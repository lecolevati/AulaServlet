<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cliente</title>
</head>
<body>
	<div>
		<jsp:include page="menu.jsp" />
		<br />
	</div>
	<form action="cliente" method="post">
	<table>
		<tr>
			<td colspan="3"><input type="number" id="id" name="id" placeholder="ID"
				value="${cliente.id }"></td>
			<td><input type="submit" value="Buscar" id="button" name="button"></td>
		</tr>
		<tr>
			<td colspan="4"><input type="text" id="nome" name="nome" placeholder="Nome" size="40"
				value="${cliente.nome }"></td>
		</tr>
		<tr>
			<td colspan="4"><input type="text" id="telefone" name="telefone" placeholder="Telefone" size="40"
				value="${cliente.telefone }"></td>
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
		<c:if test="${not empty listaClientes }">
			<table border="1">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nome</th>
					<th>Telefone</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="cli" items="${listaClientes }">
				<tr>
					<td>${cli.id }</td>
					<td>${cli.nome }</td>
					<td>${cli.telefone }</td>
				</tr>
			</c:forEach>
			</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>