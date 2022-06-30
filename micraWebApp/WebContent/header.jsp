<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div class="container">
            <h1>Libreria</h1>
            
            <c:choose>
                <c:when test="${userSession != null}">
                    <h5>Benvenuto, ${userSession.nome}</h5>
                    
                    <a href="catalogue?action=redirectCatalogue" class="btn btn-primary">Catalogo</a>
                    
                    <c:choose>
                        <c:when test="${userSession.getIdCategoria() == 2}">
                            <a href="catalogue?action=redirectInsertBook" class="btn btn-primary">Inserisci libro</a>
                        </c:when>
                        <c:otherwise>
                            <a href="order?action=redirectOrder" class="btn btn-primary">I tuoi ordini</a>
                        </c:otherwise>
                    </c:choose>
                            
                    <a href="login?action=redirectLogout" class="btn btn-primary">Logout</a>
                    
                </c:when>
                <c:otherwise>
                    <a href="catalogue?action=redirectCatalogue" class="btn btn-primary">Catalogo</a>
                    <a href="login?action=redirectLogin" class="btn btn-primary">Accedi</a>
                </c:otherwise>
            </c:choose>

            <hr>
    </body>
</html>
