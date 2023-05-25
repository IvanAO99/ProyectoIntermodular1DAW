<%--
    Document   : header
    Created on : May 23, 2023, 1:19:43 AM
    Author     : ivan
--%>

<%@page import="modelo.dto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Usuario usuarioSesion = (session != null && session.getAttribute("usuario") != null) ? (Usuario) session.getAttribute("usuario") : null;
%>

<header id="cabeceraWeb">
    <div id="tittle">
        <a href="./index.jsp"><img id="logo" src="./img/logo.png"></a>
        <h1 id="nombreEmpresa">ARTES DORADAS</h1>
        <i class="fa-solid fa-bars" id="hamburguesa"></i>
    </div>
    <nav id="navegadorPrincipal">
        <ul id="menu">
            <div id="comun">
                <li><a href="./index.jsp"><i class="fa-solid fa-house"></i>Inicio</a></li>
                <li><a href="MostrarGeneros"><i class="fa-solid fa-list"></i>Productos</a></li>
                <li><a href="./sobre_nosotros.html"><i class="fa-solid fa-address-card"></i>Sobre nosotros</a></li>
                <li><a href="./contacto.html"><i class="fa-solid fa-phone"></i>Contacto</a></li>
            </div>
            <div id="unico">
                <%
                    if ((usuarioSesion == null)) {
                %>
                <li><a href="./login.jsp"><i class="fa-solid fa-user"></i>Iniciar sesión</a></li>
                    <%
                    } else {
                    %>
                <li id="fotoYNombreNav">
                    <figure id="fotoPerfilWrapperNav">
                        <img src="<%=usuarioSesion.getFoto()%>" alt="" id="fotoPerfilNav">
                    </figure>
                    <a href="./cliente.jsp"><%= usuarioSesion.getNombreCompleto()%></a>
                </li>
                <%
                    if (usuarioSesion.esCliente()) {
                %>
                <li><a href="./cesta.jsp"><i class="fa-solid fa-phone"></i>Cesta</a></li>
                    <%
                            }
                        }
                    %>
            </div>
        </ul>
    </nav>
</header>
