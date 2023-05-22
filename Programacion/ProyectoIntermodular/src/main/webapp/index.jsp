<%--
    Document   : index
    Created on : May 2, 2023, 7:31:31 PM
    Author     : ivan
--%>

<%@page import="modelo.dto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Inicio | ARTES DORADAS</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <link rel="stylesheet" href="./css/header.css">
        <link rel="stylesheet" href="./css/index.css">
        <link rel="stylesheet" href="./css/footer.css">
        <link href="https://fonts.googleapis.com/css2?family=Roboto&family=Rock+Salt&display=swap" rel="stylesheet">
        <link rel="icon" type="image/png" href="./img/logo.png">
    </head>
    <body>
        <%
            Usuario usuarioSesion = (session != null && session.getAttribute("usuario") != null) ? (Usuario) session.getAttribute("usuario") : null;

            if (usuarioSesion != null && usuarioSesion.getTipo().toString().equals("Administrador")) {
        %>
        <header>

        </header>
        <main>
            <h1>Vista de administrador</h1>
            <a href="./facturas.jsp"><p>Ver facturas de clientes</p></a>
            <a href="./pedidos.jsp"><p>Ver pedidos de clientes</p></a>
        </main>
        <footer>

        </footer>
        <%
        } else {
        %>
        <header id="cabeceraWeb">
            <div id="tittle">
                <a href="./index.jsp"><img id="logo" src="./img/logo.png"></a>
                <h1 id="nombreEmpresa">ARTES DORADAS</h1>
            </div>
            <nav id="navegadorPrincipal">
                <ul id="menu">
                    <li><a href="./index.jsp"><i class="fa-solid fa-house"></i>Inicio</a></li>
                    <li><a href="MostrarGeneros"><i class="fa-solid fa-list"></i>Productos</a></li>
                    <li><a href="./sobre_nosotros.html"><i class="fa-solid fa-address-card"></i>Sobre nosotros</a></li>
                    <li><a href="./contacto.html"><i class="fa-solid fa-phone"></i>Contacto</a></li>
                        <%
                            if ((usuarioSesion == null)) {
                        %>
                    <li><a href="./login.jsp"><i class="fa-solid fa-user"></i>Inicia sesión</a></li>
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
            <h2 id="tituloLibros">Libros</h2>
            <section id="libroPrincipal">
                <figure>
                    <a href="MostrarGeneros?tipoProducto=libro">
                        <img src="./img/libroInicio.jpg" alt="">
                    </a>
                </figure>
            </section>
            <h2 id="tituloDiscos">Discos</h2>
            <section id="discoPrincipal">
                <figure>
                    <a href="MostrarGeneros?tipoProducto=disco">
                        <img src="./img/vinilo.jpg" alt="">
                    </a>
                </figure>
            </section>
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
        <%
            }
        %>
    </body>
</html>
