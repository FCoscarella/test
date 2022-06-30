<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ordini</title>

        <c:if test="${userSession == null || userSession.getIdCategoria() == 2}">
            <c:redirect url="login.jsp"/>
        </c:if>

    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <div class="centered margin-top-1">${msg}</div>
        <div class="centered margin-top-1">${error}</div>

        <c:choose>
            <c:when test="${orderList.size() > 0}">
                <div class="col-md-8 offset-md-2">
                    <table class="table">

                        <tr>
                            <th>Numero ordine</th>
                            <th>Data creazione</th>
                            <th>Libro</th>
                            <th></th>
                        </tr>

                        <c:forEach items="${orderList}" var="o">
                            <tr>
                                <td>
                                    ${o.id}
                                </td>
                                <td>
                                    ${o.dataCreazione.toLocalDateTime().format(DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy"))}
                                </td>
                                <td>
                                    <c:forEach items="${booksList}" var="a">
                                        <c:if test="${a.id == o.idLibro}">
                                            <a href="book?action=redirectLibro&id=${a.id}">${a.titolo}</a>
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td>
                                    <form action="order?action=delete" method="post">
                                        <input type="hidden" name="id" id="id" value="${o.id}">
                                        <input type="submit" name="delete" value="Elimina" class="btn btn-primary">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>

                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <div class="centered">
                    Non hai effettuato nessun ordine
                </div>
            </c:otherwise>
        </c:choose>

    </body>
</html>
