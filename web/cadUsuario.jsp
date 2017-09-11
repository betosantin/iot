<%-- 
    Document   : cadUsuario
    Created on : 12/09/2016, 19:19:51
    Author     : Roberto Santin
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Utilities.ConnectionBD"%>
<%@page import="Entity.Usuario"%>
<%@page import="TableName.UsuariosName"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Led Pi - Home</title>
    <!-- Core CSS - Include with every page -->
    <link href="assets/plugins/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href="assets/plugins/pace/pace-theme-big-counter.css" rel="stylesheet" />
    <link href="assets/css/style.css" rel="stylesheet" />
    <link href="assets/css/main-style.css" rel="stylesheet" />
    <!-- Page-Level CSS -->
    <link href="assets/plugins/morris/morris-0.4.3.min.css" rel="stylesheet" />
   </head>
<body>
    
    <%
        String retorno = (String) request.getSession().getAttribute("retorno");
    %>
    
    <!--  wrapper -->
    <div id="wrapper">
        <!-- navbar top -->
        <%@include file ="top.jsp" %>
        <!-- end navbar top -->

        <!-- navbar side -->
        <%@include file ="navbar.jsp" %>
        
        <!-- end navbar side -->
        
        
        <!--  page-wrapper -->
          <div id="page-wrapper">
            <div class="row">
                 <!-- page header -->
                <div class="col-lg-12">
                    <h1 class="page-header">Cadastro</h1>
                </div>
                
                <!--end page header -->
            </div>
            <div >
                <h5 class="panel panel-default">   
                   <%
                       if (retorno != null)
                       {
                   %>
                           <div><%=retorno%></div>
                    <%
                       }%>
                   </h5>
           </div>
            <div class="row">
                <div class="col-lg-12">
                    <!-- Form Elements -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Cadastro de Usuários
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-8">
                                    <form role="form">
                                        <div class="table-responsive">
                                            
                                            <table class="form-group pull-right">
                                                <tr>
                                                    <td>Procurar</td>
                                                    <td>&nbsp;</td>
                                                    <td><input type="text" class="search form-control" placeholder="O que você procura?"></td>
                                                </tr>
                                            </table>
                                            <!--<span class="counter pull-right"></span>-->
                                            
                                            <table class="table table-hover table-bordered results" >
                                                <thead>
                                                    <tr>
                                                        <th class="col-md-5 col-xs-5">Id</th>
                                                        <th class="col-md-5 col-xs-5">Nome Usuário</th>
                                                        <th class="col-md-4 col-xs-4">Login</th>
                                                        <th class="col-md-3 col-xs-3">Tipo</th>
                                                        <th class="col-md-3 col-xs-3">E-mail</th>
                                                        <th class="col-md-3 col-xs-3">Status</th>
                                                        <th class="col-md-3 col-xs-3">Editar</th>
                                                        <th class="col-md-3 col-xs-3">Excluir</th>
                                                    </tr>
                                                    <tr class="warning no-result">
                                                        <td colspan="8"><i class="fa fa-warning"></i> Sem Resultado</td>
                                                    </tr>
                                                </thead>

                                                <%
                                                    ArrayList<Usuario> usuarios = new ConnectionBD().getUsuariosService().getAll();

                                                    for (int i = 0; i < usuarios.size(); i++)
                                                    {
                                                        Usuario c = usuarios.get(i);
                                                %>

                                                <tr>
                                                    <td><%=c.getIdUsuario()%></td>
                                                    <td><%=c.getNomeUsuario()%></td>
                                                    <td><%=c.getLoginUsuario()%></td>
                                                    <td><%=c.getTipoString()%></td>
                                                    
                                                    <td><%=c.getEmailUsuario()%></td>
                                                    <td><%=c.getStatusString()%></td>
                                                            
                                                    <td><a href="/IoT/acao?parametro=editarUsuario&id=<%=c.getIdUsuario()%>"
                                                           <button type="submit" class="btn btn-primary">Editar</button></a></td>
                                                    <td><a OnClick="return confirm('Confirma exclusão?')" 
                                                           href="/IoT/acao?parametro=excluirUsuario&id=<%=c.getIdUsuario()%>"
                                                           <button type="submit" class="btn btn-danger">Excluir</button></a></td>
                                                    
                                                </tr>
                                                <%
                                                    }
                                                %>

                                            </table>
                                        </div>  
                                        
                                        <a href="/IoT/editUsuario.jsp"
                                            <button type="submit" class="btn btn-success">Novo</button></a>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                     <!-- End Form Elements -->
                </div>
            </div>
        </div>
        <!-- end page-wrapper -->
        
    </div>
    <!-- end wrapper -->

    <!-- Core Scripts - Include with every page -->
    <script src="assets/plugins/jquery-1.10.2.js"></script>
    <script src="assets/plugins/bootstrap/bootstrap.min.js"></script>
    <script src="assets/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="assets/plugins/pace/pace.js"></script>
    <script src="assets/scripts/siminta.js"></script>
    <!-- Page-Level Plugin Scripts-->
    <script src="assets/plugins/morris/raphael-2.1.0.min.js"></script>
    <script src="assets/plugins/morris/morris.js"></script>
    <script src="assets/scripts/dashboard-demo.js"></script>

</body>

</html>