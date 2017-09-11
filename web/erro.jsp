<%-- 
    Document   : erro
    Created on : 22/08/2016, 19:49:00
    Author     : fabricio.pretto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%@include file ="top.jsp" %>
        <h1 class="error"> Erro!</h1>
        
        

        <%
            String pagina = (String) request.getAttribute("paginaRetorno");
        %>

        <h1 class="success1"><a href='<%=pagina %>'>Voltar</a></h1>
</html>
