<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrati</title>
        <link rel="stylesheet" href="style.css">
        
        <c:if test="${userSession != null}">
            <c:redirect url="index.jsp"/>
        </c:if> 
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <h2 class="centered">Registrati</h2>

        <form action="registration?action=register" method="post">
            <table align="center" class="width30 margin-top-1">
                <tr>
                    <th>
                        <label for="email">Email</label>
                    </th>
                </tr>
                <tr>
                    <td>
                        <input type="email" name="email" id="email" value="${user.email}" placeholder="Email" required="required" class="width30 margin-top-1">
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="password">Password</label>
                    </th>
                </tr>
                <tr>
                    <td>
                        <input type="password" name="password" id="password" value="${user.password}" placeholder="Password" required="required" class="width30 margin-top-1">
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="name">Nome</label>
                    </th>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="name" id="name" value="${user.nome}" placeholder="Nome" required="required" class="width30 margin-top-1">
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="surname">Cognome</label>
                    </th>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="surname" id="surname" value="${user.cognome}" placeholder="Cognome" required="required" class="width30 margin-top-1">
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="birthdate">Data di nascita</label>
                    </th>
                </tr>
                <tr>
                    <td>
                        <input type="date" name="birthdate" id="birthdate" value="${user.dataNascita}" required="required" class="width30 margin-top-1">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" name="btnRegister" value="Registrati" class="btn btn-primary width30 margin-top-1">
                    </td>
                </tr>
            </table>
        </form>

        <div class="centered">
            <a href="login?action=redirectLogin" class="btn btn-secondary width30 margin-top-1">Accedi</a>
        </div>

        <div class="centered margin-top-1">${msg}</div>
        <div class="centered margin-top-1">${error}</div>
    </body>
</html>
