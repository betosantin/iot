<%-- 
    Document   : index
    Created on : 15/08/2016, 19:50:14
    Author     : Roberto Santin
--%>

<%@page import="TableName.UsuariosName"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Led Pi - Login</title>
        <link rel="stylesheet" type="text/css" href="css/login.css">
    </head>
    
    <link href="css/login.css">
    
    <%
        String error = (String) request.getSession().getAttribute("error");
    %>
    
    <body background="images/background.jpg">
        <form action="/IoT/login?parametro=login" method="post">
            <div class="login-page">
                <div class="form">
                    <form class="login-form">
                        <img src="assets/img/logo.png" width="221" height="98" alt="logo">
                        <br><br>
                        <input type="text" placeholder="Usuário" name="login"/>
                        <input type="password" placeholder="Senha" name="senha"/>
                        <button>Acessar</button>
                        <div><br></div>
                        <%
                            if ( error != null )
                            {
                        %>
                                <div><%=error%></div>
                            <%
                            }
                            %>

                            <div><br></div>
                        <p class="message"><a href="/IoT/recovery.jsp">Esqueceu a senha?</a></p>
                        
                    </form>
                </div>
            </div>
        </form>
</html>
