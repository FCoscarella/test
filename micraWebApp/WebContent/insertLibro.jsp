<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">

        <c:if test="${userSession == null || userSession.getIdCategoria() != 2}">
            <c:redirect url="index.jsp" />
        </c:if>

        <c:choose>
            <c:when test="${libro.id != null}">
                <title>Modifica un libro</title>
            </c:when>
            <c:otherwise>
                <title>Inserisci un libro</title>
            </c:otherwise>
        </c:choose>
    </head>
    <body>

        <jsp:include page="header.jsp"/>

        <c:choose>
            <c:when test="${libro.id != null}">
                <h2 class="centered">Modifica un libro</h2>
            </c:when>
            <c:otherwise>
                <h2 class="centered">Inserisci un libro</h2>
            </c:otherwise>
        </c:choose>

        <form action="catalogue?action=update" method="post">

            <input type="hidden" name="id" id="id" value="${libro.id}">

            <table align="center" class="width30 margin-top-1">
                <tr>
                    <th>
                        <label for="email">Titolo</label>
                    </th>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="titolo" id="titolo" value="${libro.titolo}" placeholder="Titolo" required="required" class="width30 margin-top-1">
                    </td>
                </tr>

                <tr>
                    <th>
                        <label for="password">Autore</label>
                    </th>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="autore" id="autore" value="${libro.autore}" placeholder="Autore" required="required" class="width30 margin-top-1">
                    </td>
                </tr>

                <tr>
                    <th>
                        <label for="password">Anno</label>
                    </th>
                </tr>
                <tr>
                    <td>
                        <input type="number" name="anno" id="modello" value="${libro.anno}" min="1890" step="1" placeholder="Anno" required="required" class="width30 margin-top-1">
                    </td>
                </tr>

                <tr>
                    <th>
                        <label for="password">Categoria</label>
                    </th>
                </tr>
                <tr>
                    <td>
                        <select name="categoriaLibro" id="categoriaLibro" required="required" class="width30 margin-top-1">
                            <c:forEach items="${bookCategoriesList}" var="e">
                                <c:choose>
                                    <c:when test="${libro.idCategoriaLibro == e.id}">
                                        <option value="${e.id}" selected="selected">${e.descrizione}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${e.id}">${e.descrizione}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${libro.id != null}">
                                <input type="submit" name="btnEdit" value="Modifica" class="btn btn-primary width30 margin-top-1">
                            </c:when>
                            <c:otherwise>
                                <input type="submit" name="btnInsert" value="Inserisci" class="btn btn-primary width30 margin-top-1">
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </table>
        </form>

        <div class="centered margin-top-1">${msg}</div>
        <div class="centered margin-top-1">${error}</div>

    </body>
</html>
