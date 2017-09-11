<%-- 
    Document   : editContas
    Created on : 15/09/2016, 02:44:53
    Author     : Roberto Santin
--%>

<%@page import="TableName.UsuariosName"%>
<%@page import="Utilities.ConnectionBD"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entity.Usuario"%>
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
        Usuario us = (Usuario) request.getAttribute("editUsuario");
        String retorno = (String) request.getSession().getAttribute("retorno");
        
        if ( us == null)
        {
            us = new Usuario();
        }
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
                                <div class="col-lg-6">
                                    <form name="salvarUsuario" action="/IoT/acao?parametro=salvarUsuario" method="post">
                                        <div class="form-group">
                                            <label>Código</label>
                                            <input name="<%=UsuariosName.idUsuario.toString()%>" 
                                                   class="form-control" readonly="true"
                                                   value="<%=us.getIdUsuario()%>">
                                        </div>
                                        <div class="form-group" >
                                            <label>Nome do Usuário*</label>
                                            <input name="<%=UsuariosName.nomeUsuario.toString()%>" 
                                                   class="form-control" placeholder="Nome do Usuário" required
                                                   value="<%=us.getNomeUsuario()%>">
                                        </div>
                                        <div class="form-group">
                                            <label>Login do Usuário*</label>
                                            <input name="<%=UsuariosName.loginUsuario.toString()%>"
                                                   class="form-control" placeholder="Login do Usuário" required
                                                   value="<%=us.getLoginUsuario()%>">
                                        </div>
                                        <div class="form-group">
                                            <label>Senha do Usuário*</label>
                                            <input name="<%=UsuariosName.senhaUsuario.toString()%>"
                                                   class="form-control" placeholder="Senha do Usuário" type="password" required
                                                   value="">
                                        </div>
                                        <div class="form-group">
                                            <label>E-mail do Usuário*</label>
                                            <input name="<%=UsuariosName.emailUsuario.toString()%>"
                                                   class="form-control" placeholder="E-mail do Usuário" type="mail" required
                                                   value="<%=us.getEmailUsuario()%>">
                                        </div>
                                        <div class="form-group">
                                            <label>Tipo do Usuário*<p><p></label>
                                            <label class="radio-inline">
                                                <input type="radio" name="<%=UsuariosName.tipoUsuario.toString()%>" id="tipo" 
                                                       <%  
                                                        if (us.getTipoUsuario() == Usuario.TIPO_ADMINISTRADOR )
                                                        {
                                                            %>checked
                                                       <%
                                                        }%>
                                                        value="Administrador"
                                                        >
                                                        Administrador
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="<%=UsuariosName.tipoUsuario.toString()%>" id="tipo2" 
                                                       <%  
                                                        if (us.getTipoUsuario() == Usuario.TIPO_OPERADOR )
                                                        {
                                                            %>checked
                                                       <%
                                                        }%>
                                                       Value="Operador"
                                                       >
                                                       Operador
                                            </label>
                                        </div>
                                        <div class="form-group">
                                            <label>Status</label>
                                            <div class="checkbox">
                                                <label>
                                                    <input name="<%=UsuariosName.statusUsuario.toString()%>"
                                                           <%  
                                                           if ( us.getStatusUsuario() == Usuario.STATUS_ATIVO )
                                                           {
                                                               %>checked
                                                           <%
                                                           }%>
                                                           type="checkbox">Ativo
                                                </label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>* Campos Obrigatórios</label>
                                        </div>
                                            
                                            <button type="submit" class="btn btn-primary">Salvar</button>
                                            <!--<button type="reset" class="btn btn-success" <a href="">Cancelar</button>-->
                                            <a href="cadUsuario.jsp" class="btn btn-success" role="button">Cancelar</a>
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