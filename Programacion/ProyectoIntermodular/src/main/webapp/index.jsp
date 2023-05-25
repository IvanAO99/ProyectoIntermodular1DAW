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
        <%@include file="./header.jsp"%>
        <main id="principalWeb">
            <%                if (usuarioSesion == null || usuarioSesion.esCliente()) {
            %>
            <section id="libroPrincipal">
                <h2 id="tituloLibros">Libros</h2>
                <a href="MostrarGeneros?tipoProducto=libro" class="enlaceAProductos">
                    <figure>
                        <img src="./img/libroInicio.jpg" alt="">
                    </figure>
                </a>
            </section>
            <section id="discoPrincipal">
                <h2 id="tituloDiscos">Discos</h2>
                <a href="MostrarGeneros?tipoProducto=disco" class="enlaceAProductos">
                    <figure>
                        <img src="./img/vinilo.jpg" alt="">
                    </figure>
                </a>
            </section>
            <%
            } else {
            %>
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
