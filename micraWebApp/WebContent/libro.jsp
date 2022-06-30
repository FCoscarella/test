<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Libro</title>
        <link rel="stylesheet" href="style.css">

        <c:if test="${id == null || id == ''}">
            <c:redirect url="index.jsp"/>
        </c:if>
    </head>
    <body>
        <jsp:include page="header.jsp"/>


        <div class="col-md-8 offset-md-2">


            <div class="centered">
                <h1>${libro.titolo}</h1>
            </div>

            <form action="order?action=createOrder" method="post">
                
                <input type="hidden" name="id" id="id" value="${libro.id}">

                <table align="center" class="width30 margin-top-1">
                <tr>
                        <th>
                            <label for="email">Autore</label>
                        </th>
                    </tr>
                    <tr>
                        <td>
                            ${libro.autore}
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="email">Anno</label>
                        </th>
                    </tr>
                    <tr>
                        <td>
                            ${libro.anno}
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="password">Categoria</label>
                        </th>
                    </tr>
                    <tr>
                        <td>
                            <c:forEach items="${bookCategoriesList}" var="e">
                                <c:if test="${e.id == libro.idCategoriaLibro}">
                                    ${e.descrizione}
                                </c:if>
                            </c:forEach>
                        </td>
                    </tr>
                    <c:if test="${userSession != null && userSession.getIdCategoria() != 2 && libro.idStatoLibro == 1}">
                        <tr>
                            <td>
                                <input type="submit" name="order" value="Ordina" class="btn btn-primary width30 margin-top-1">
                            </td>
                        </tr>    
                    </c:if>   
                </table>

            </form>

        </div>

    </body>
</html>
