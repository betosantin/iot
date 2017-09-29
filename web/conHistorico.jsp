<%-- 
    Document   : conHistorico
    Created on : 28/09/2017, 20:16:59
    Author     : Roberto Santin
--%>

<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.URL"%>
<%@page import="java.util.List"%>
<%@page import="Entity.Dispositivo"%>
<%@page import="Utilities.ConnectionBD"%>
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
        <link href="assets/plugins/morris/morris-0.4.3.min.css" rel="stylesheet" />
        <link href="assets/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
        <!-- Page-Level CSS -->

        <script src="assets/plugins/jquery-1.10.2.js"></script>
    </head>
    <body>


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
                    <!-- Page Header -->
                    <div class="col-lg-12">
                        <h1 class="page-header">Controle</h1>
                    </div>
                    <!--End Page Header -->
                </div>

                <div class="row">
                    <!-- Welcome -->
                    <div class="col-lg-12">
                        <div class="alert alert-info">
                        </div>
                    </div>
                    <!--end  Welcome -->
                </div>

                <div class="row"  >
                    <div class="col-lg-10"  >
                        <div class="row">
                            <div class="col-lg-12">
                                <!-- Advanced Tables -->
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        Tabela de eventos
                                    </div>
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <table class="table table-striped table-bordered table-hover" id="dataTable">
                                                <thead>
                                                    <tr>
                                                        <th>Evento</th>
                                                        <th>Direção</th>
                                                        <th>Valor de Retorno</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr class="odd gradeX">
                                                        <td>acenderled</td>
                                                        <td>Enviado</td>
                                                        <td>255,255,255</td>
                                                    </tr>
                                                    <tr class="odd gradeX">
                                                        <td>acenderled</td>
                                                        <td>Retorno</td>
                                                        <td>Ok</td>
                                                    </tr>
                                                    <tr class="odd gradeX">
                                                        <td>obtertemperatura</td>
                                                        <td>Enviado</td>
                                                        <td></td>
                                                    </tr>
                                                    <tr class="odd gradeX">
                                                        <td>obtertemperatura</td>
                                                        <td>Retorno</td>
                                                        <td>28ºC</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end page-wrapper -->
        </div>

        <!-- end wrapper -->

        <!-- Core Scripts - Include with every page -->

        <script src="assets/plugins/bootstrap/bootstrap.min.js"></script>
        <script src="assets/plugins/metisMenu/jquery.metisMenu.js"></script>
        <script src="assets/plugins/pace/pace.js"></script>
        <script src="assets/scripts/siminta.js"></script>
        <!-- Page-Level Plugin Scripts-->

        <script src="assets/plugins/dataTables/jquery.dataTables.js"></script>
        <script src="assets/plugins/dataTables/dataTables.bootstrap.js"></script>
        <script>
            $(document).ready(function () {
                $('#dataTable').dataTable();
            });
        </script>

    </body>

</html>
