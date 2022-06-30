<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>Libreria</title>
        <link rel="stylesheet" href="style.css">
        
        <c:redirect url="catalogue?action=redirectCatalogue">
            <c:param name="msg" value="${msg}"></c:param>
            <c:param name="error" value="${error}"></c:param>
        </c:redirect>
        
    </head>
    <body>
        
        <jsp:include page="header.jsp"/>

    </body>
</html>
