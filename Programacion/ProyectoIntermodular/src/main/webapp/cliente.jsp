<%--
    Document   : cliente
    Created on : May 2, 2023, 7:53:52 PM
    Author     : ivan
--%>

<%@page import="modelo.dto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Cliente | ARTES DORADAS</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto&family=Rock+Salt&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="./css/header.css">
        <link rel="stylesheet" type="text/css" href="./css/cliente.css">
        <link rel="stylesheet" type="text/css" href="./css/footer.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <link rel="icon" type="image/png" href="./img/logo.png">
    </head>
    <body>
        <header id="cabeceraWeb">
            <div id="tittle">
                <a href="./index.jsp"><img id="logo" src="./img/logo.png"></a>
                <h1>ARTES DORADAS</h1>
            </div>
            <nav id="navegadorPrincipal">
                <ul id="menu">
                    <li><a href="../index.html"><i class="fa-solid fa-house"></i>Inicio</a></li>
                    <li><a href="./categorias.html"><i class="fa-solid fa-list"></i>Productos</a></li>
                    <li><a href="./sobre_nosotros.html"><i class="fa-solid fa-address-card"></i>Sobre nosotros</a></li>
                    <li><a href="./contacto.html"><i class="fa-solid fa-phone"></i>Contacto</a></li>
                        <%
                            Usuario usuarioSesion = (session != null && session.getAttribute("usuario") != null) ? (Usuario) session.getAttribute("usuario") : null;

                            if ((usuarioSesion == null)) {
                        %>
                    <li><a href="./login.jsp"><i class="fa-solid fa-user"></i>Login</a></li>
                        <%
                        } else {
                        %>
                    <li><a href="./cliente.jsp"><i class="fa-solid fa-user"></i><%= usuarioSesion.getNombreCompleto()%></a></li>
                    <li><a href="./cesta.html"><i class="fa-solid fa-phone"></i>Cesta</a></li>
                        <%
                            }
                        %>
                </ul>
            </nav>
        </header>
        <main id="principalWeb">
            <a href="./datosUsuario.jsp">
                <section id="verDatos">
                    <h2>VER DATOS</h2>
                </section>
            </a>
            <a href="./lista_deseos.html">
                <section id="lista-deseos">
                    <h2>LISTA DE DESEOS</h2>
                </section>
            </a>
            <a href="./carrito.jsp">
                <section id="cesta">
                    <h2>CESTA</h2>
                </section>
            </a>
            <a href="./pedidos.jsp">
                <section id="pedidos">
                    <h2>PEDIDOS</h2>
                </section>
            </a>
            <a href="./facturas.jsp">
                <section id="facturas">
                    <h2>FACTURAS</h2>
                </section>
            </a>
            <a href="./opiniones">
                <section id="opiniones">
                    <h2>OPINIONES</h2>
                </section>
            </a>
            <a href="LogoutServlet">
                <section id="log-out">
                    <h2>LOG OUT</h2>
                </section>
            </a>
        </main>
        <footer id="webFooter">
            <section>
                <h3>Términos y condiciones</h3>
                <p><a href="./privacidad.html">Política de privacidad</a></p>
                <p id="copyright">
                    © 2022-23, Iván Ayuso & Enrique Azorín
                    <br>1º DAW | IES Poeta Paco Mollá (Petrer)
                </p>
            </section>
        </footer>
    </body>
</html>
