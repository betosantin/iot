<%-- 
    Document   : listaUsuarios
    Created on : 10/10/2016, 19:58:37
    Author     : Roberto Santin
--%>

<%@page import="TableName.ContasName"%>
<%@page import="Entity.Usuario"%>
<%@page import="Entity.Conta"%>
<%@page import="Utilities.ConnectionBD"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">     

        <style type="text/css" media="all">
            table#tableTrClick tr.trClick{background: #fff; color: #000; cursor: pointer;}  
            table#tableTrClick tr.trClick:hover{background: green; color: #fff;}  
        </style>

        <script lang="javascript">
                $('#tableTrClick tr').click(function () {
                    $('#Usuarios_idUsuario').attr('value', $(this).find('td').eq(0).html() );
                    $('#Usuarios_idUsuario1').val($(this).find('td').eq(0).html());
                    $('#Usuarios_idUsuario').val($(this).find('td').eq(1).html());
                    $('#myModal').modal('hide');
                    
                });

        </script>


        <title>JSP Page</title>
    </head>
    <body>
        <h1>Lista de Contas</h1>
        <div id="content">
            <table class="table table-responsive" id="tableTrClick">               

                <tr>
                    <td>Id</td>
                    <td>Nome</td>
                    <td>Login</td>
                </tr>
                <%
                    ArrayList<Usuario> us = new ConnectionBD().getUsuariosService().getAll();

                    for (int i = 0; i < us.size(); i++) {
                        Usuario c = (Usuario) us.get(i);
                %>

                <tr class="trClick" >
                    <td><%= c.getIdUsuario()%></td>
                    <td><%= c.getNomeUsuario()%></td>
                    <td><%= c.getLoginUsuario()%></td>
                </tr>
                <%
                    }
                %>

            </table>

        </div>

        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
        </div>

    </body>
</html>
