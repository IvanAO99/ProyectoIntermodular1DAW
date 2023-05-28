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
        <%@include file="./header.jsp"%>
        <main id="principalWeb">
            <%                if ((usuarioSesion == null)) {
            %>
            <div class="error">
                <p>JOSÉ RAMÓN!!! NO PUEDES VER ESTO SI NO ERES CLIENTE o ADMINISTRADOR :'(</p>
                <p><a href="./index.jsp">Volver al index</a></p>
            </div>
            <%
            } else {
            %>
            <%
                if (usuarioSesion.esCliente()) {
            %>
            <article class="enlaceASeccion">
                <a href="./datosUsuario.jsp"><h2 class="tituloEnlace">VER DATOS</h2></a>
            </article>
            <%
            } else {
            %>
            <article class="enlaceASeccion">
                <a href="./datosUsuario.jsp"><h2 class="tituloEnlace">VER CLIENTES</h2></a>
            </article>
            <%
                }
                if (usuarioSesion.esCliente()) {
            %>
            <article class="enlaceASeccion">
                <a href="#"><h2 class="tituloEnlace">LISTA DE DESEOS</h2></a>
            </article>
            <%
                }
            %>
            <%
                if (usuarioSesion.esCliente()) {
            %>
            <article class="enlaceASeccion">
                <a href="./carrito.jsp"><h2 class="tituloEnlace">CARRITO</h2></a>
            </article>
            <%
                }
            %>
            <article class="enlaceASeccion">
                <a href="./pedidos.jsp"><h2 class="tituloEnlace">PEDIDOS</h2></a>
            </article>
            <article class="enlaceASeccion">
                <a href="./facturas.jsp"><h2 class="tituloEnlace">FACTURAS</h2></a>
            </article>
            <article class="enlaceASeccion">
                <a href="#"><h2 class="tituloEnlace">OPINIONES</h2></a>
            </article>
            <article class="enlaceASeccion">
                <a href="LogoutServlet"><h2 class="tituloEnlace">LOG OUT</h2></a>
            </article>
            <%
                }
            %>
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
