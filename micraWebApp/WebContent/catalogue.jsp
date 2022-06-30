<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Libreria</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="centered margin-top-1">${msg}</div>
	<div class="centered margin-top-1">${error}</div>

	<c:choose>
		<c:when test="${booksList.size() > 0}">
			<div class="col-md-8 offset-md-2">
				<table class="table">

					<tr>
						<th>Titolo</th>
						<th>Autore</th>
						<th>Anno</th>
						<th>Categoria</th>
						<c:if test="${userSession.getIdCategoria() == 2}">
							<th>Stato</th>
							<th></th>
							<th></th>
						</c:if>
					</tr>

					<c:forEach items="${booksList}" var="a">
						<tr>
							<td><a href="book?action=redirectLibro&id=${a.id}">${a.titolo}</a>
							</td>
							<td>${a.autore}</td>
							<td>${a.anno}</td>
							<td><c:forEach items="${bookCategoriesList}" var="e">
									<c:if test="${e.id == a.idCategoriaLibro}">
                                            ${e.descrizione}
                                        </c:if>
								</c:forEach></td>
							<c:if test="${userSession.getIdCategoria() == 2}">
								<td><c:forEach items="${statusList}" var="s">
										<c:if test="${s.id == a.idStatoLibro}">
                                                ${s.descrizione}
                                            </c:if>
									</c:forEach></td>
								<td>
									<form action="catalogue?action=redirectInsertBook" method="post">
										<input type="hidden" name="id" id="id" value="${a.id}">
										<input type="submit" name="btnEdit" value="Modifica"
											class="btn btn-primary">
									</form>
								</td>
								<td>
									<form action="catalogue?action=disable" method="post">
										<input type="hidden" name="id" id="id" value="${a.id}">
										<input type="hidden" name="oldState" id="oldState"
											value="${a.idStatoLibro}">
										<c:choose>
											<c:when test="${a.idStatoLibro == 1}">
												<input type="submit" name="btnChange" value="Disabilita"
													class="btn btn-primary">
											</c:when>
											<c:otherwise>
												<input type="submit" name="btnChange" value="Abilita"
													class="btn btn-primary">
											</c:otherwise>
										</c:choose>

									</form>
								</td>
							</c:if>
						</tr>
					</c:forEach>

				</table>
			</div>
		</c:when>
		<c:otherwise>
			<div class="centered">Nessun libro in lista</div>
		</c:otherwise>
	</c:choose>
</body>
</html>
