<%-- 
    Document   : navbar
    Created on : 05/09/2016, 22:07:32
    Author     : Roberto Santin
--%>

<%@page import="Entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <nav class="navbar-default navbar-static-side" role="navigation">
        <!-- sidebar-collapse -->
        <div class="sidebar-collapse">
            <!-- side-menu -->
            <ul class="nav" id="side-menu">
                <li>
                    <!-- user image section-->
                    <div class="user-section">
<!--                        <div class="user-section-inner">
                            <img src="assets/img/user.jpg" alt="">
                        </div>-->
                        <div class="user-info">
                            <div><%= user.getNomeUsuario()%></div>
                            <div class="user-text-online">
                                <%= user.getEmailUsuario()%>
                            </div>
                        </div>
                    </div>
                    <!--end user image section-->
                </li>
                <!--<li class="selected">-->
                <li>
                    <a href="home.jsp"><i class="fa fa-dashboard fa-fw"></i>Controle</a>
                </li>
                <%
                    if ( user.getTipoUsuario() == Usuario.TIPO_ADMINISTRADOR )
                    { %>
                    <li>
                        <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Cadastros<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="cadUsuario.jsp">Usuários </a>
                            </li>
                            <li>
                                <a href="cadDispositivos.jsp">Dispositivos </a>
                            </li>
                        </ul>
                        <!-- second-level-items -->
                    </li>
                    <%
                    }
                %>
<!--                <li>
                    <a href="cadLancamentos.jsp"><i class="fa fa-flask fa-fw"></i>Lançamentos</a>
                </li>-->
            <!--<li>
                    <a href="tables.html"><i class="fa fa-table fa-fw"></i>Tables</a>
                </li>
                <li>
                    <a href="forms.jsp"><i class="fa fa-edit fa-fw"></i>Forms</a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-wrench fa-fw"></i>UI Elements<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="panels-wells.html">Panels and Wells</a>
                        </li>
                        <li>
                            <a href="buttons.html">Buttons</a>
                        </li>
                        <li>
                            <a href="notifications.html">Notifications</a>
                        </li>
                        <li>
                            <a href="typography.html">Typography</a>
                        </li>
                        <li>
                            <a href="grid.html">Grid</a>
                        </li>
                    </ul>
                     second-level-items 
                </li>
                <li>
                    <a href="#"><i class="fa fa-sitemap fa-fw"></i>Multi-Level Dropdown<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="#">Second Level Item</a>
                        </li>
                        <li>
                            <a href="#">Second Level Item</a>
                        </li>
                        <li>
                            <a href="#">Third Level <span class="fa arrow"></span></a>
                            <ul class="nav nav-third-level">
                                <li>
                                    <a href="#">Third Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Third Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Third Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Third Level Item</a>
                                </li>
                            </ul>
                             third-level-items 
                        </li>
                    </ul>
                     second-level-items 
                </li>
                <li>
                    <a href="#"><i class="fa fa-files-o fa-fw"></i>Sample Pages<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="blank.html">Blank Page</a>
                        </li>
                        <li>
                            <a href="home.jsp">Login Page</a>
                        </li>
                    </ul>
                     second-level-items 
                </li>-->
            </ul>
            <!-- end side-menu -->
        </div>
        <!-- end sidebar-collapse -->
    </nav>
</html>
