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
    <!-- Page-Level CSS -->
    
    <script src="assets/plugins/jquery-1.10.2.js"></script>
   </head>
<body>

    <script>
        
        function exibirFuncoes() {
            var urlOn = "/IoT/controller?parametro&showoptions=";

            urlOn += $(comboboxdispositivo).find('option:selected').val();
            
            var posting = $.post( urlOn );

            posting.done(function( data ) {
                $( "#result" ).empty().append( data );
            });

        }
    </script>
    

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
                    <!--<form class="form-inline" >-->
                        
                    <table class="form-group pull-right" >
                        <tr>
                        <div class="form-group" >
                            <label>Dispositivo</label>
                            <select id="comboboxdispositivo" >

                                <%                    List<Dispositivo> dps = new ConnectionBD().getDispositivosService().getAll();

                                    for (Dispositivo d : dps) {
                                %>
                                <option value="<%=d.getIdDispositivo()%>"><%=d.getNomeDispositivo()%></option>
                                <%  }

                                %>
                            </select>
                            &nbsp;
                            <button onclick='exibirFuncoes()' >Ok</button></a>
                        </div>

                        <div id='result' >

                        </div>    

                        </tr>
                    </table>


                    
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

    <script src="assets/plugins/morris/raphael-2.1.0.min.js"></script>
    <script src="assets/plugins/morris/morris.js"></script>
    <script src="assets/scripts/dashboard-demo.js"></script>

</body>

</html>
