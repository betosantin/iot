<%-- 
    Document   : editContas
    Created on : 15/09/2016, 02:44:53
    Author     : Roberto Santin
--%>

<%@page import="TableName.DispositivosName"%>
<%@page import="Entity.Dispositivo"%>
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
        Dispositivo us = (Dispositivo) request.getAttribute("editDispositivo");
        String retorno = (String) request.getSession().getAttribute("retorno");
        
        if ( us == null)
        {
            us = new Dispositivo();
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
                                    <form name="salvarDispositivo" action="/IoT/acao?parametro=salvarDispositivo" method="post">
                                        <div class="form-group">
                                            <label>Código</label>
                                            <input name="<%=DispositivosName.idDispositivo.toString()%>" 
                                                   class="form-control" readonly="true"
                                                   value="<%=us.getIdDispositivo()%>">
                                        </div>
                                        <div class="form-group" >
                                            <label>Nome do Dispositivo*</label>
                                            <input name="<%=DispositivosName.nomeDispositivo.toString()%>" 
                                                   class="form-control" placeholder="Nome do Dispositivo" required
                                                   value="<%=us.getNomeDispositivo()%>">
                                        </div>
                                        <div class="form-group">
                                            <label>Endereço de IP* Ex: https://192.168.2.2</label>
                                            <input name="<%=DispositivosName.ip.toString()%>"
                                                   class="form-control" placeholder="Endereço de IP" required
                                                   value="<%=us.getIp()%>">
                                        </div>
                                        <div class="form-group">
                                            <label>Porta* Ex: 8443</label>
                                            <input name="<%=DispositivosName.porta.toString()%>"
                                                   class="form-control" placeholder="Porta da conexão" required 
                                                   type="number"
                                                   value="<%=us.getPorta()%>">
                                        </div>
                                        <div class="form-group">
                                            <label>Serviço*</label>
                                            <input name="<%=DispositivosName.servico.toString()%>"
                                                   class="form-control" placeholder="Serviço" required
                                                   value="<%=us.getServico()%>">
                                        </div>
                                        <div class="form-group">
                                            <label>Servelet*</label>
                                            <input name="<%=DispositivosName.servelet.toString()%>"
                                                   class="form-control" placeholder="Servelet" required
                                                   value="<%=us.getServelet()%>">
                                        </div>

                                        <div class="form-group">
                                            <label>* Campos Obrigatórios</label>
                                        </div>
                                            
                                            <button type="submit" class="btn btn-primary">Salvar</button>
                                            <!--<button type="reset" class="btn btn-success" <a href="">Cancelar</button>-->
                                            <a href="cadDispositivos.jsp" class="btn btn-success" role="button">Cancelar</a>
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

</body>

</html>