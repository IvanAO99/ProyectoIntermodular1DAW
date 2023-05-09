<%-- 
    Document   : datosUsuario
    Created on : May 2, 2023, 7:49:06 PM
    Author     : ivan
--%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="modelo.dto.Direccion"%>
<%@page import="modelo.dao.DireccionDAO"%>
<%@page import="modelo.dto.Tarjeta"%>
<%@page import="modelo.dto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Datos del usuario | ARTES DORADAS</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto&family=Rock+Salt&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="./css/header.css">
        <link rel="stylesheet" type="text/css" href="./css/datosUsuario.css">
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
                    <li><a href="./index.jsp"><i class="fa-solid fa-house"></i>Inicio</a></li>
                    <li><a href="./productos.jsp"><i class="fa-solid fa-list"></i>Productos</a></li>
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
            <%
                if ((usuarioSesion == null)) {
            %>
            <section id="datosUsuario">
                <p>JOSÉ RAMÓN!!! NO PUEDES VER ESTO SI NO ERES CLIENTE :'(</p>
                <p><a href="./index.jsp">Volver al index</a></p>
            </section>
            <%
            } else {
            %>
            <section id="datosUsuario">
                <article class="datoUsuario">
                    <h2>Foto</h2>
                    <figure>
                        <img src="<%=usuarioSesion.getFoto()%>" alt="Sin foto"/>
                    </figure>
                </article>
                <article class="datoUsuario">
                    <h2>Nombre</h2>
                    <p><%=usuarioSesion.getNombreCompleto()%></p>
                </article>
                <article class="datoUsuario">
                    <h2>Correo electrónico</h2>
                    <p><%=usuarioSesion.getCorreoElectronico()%></p>
                </article>
                <article class="datoUsuario">
                    <h2>Contraseña</h2>
                    <p><%=usuarioSesion.getPassword()%></p>
                </article>
                <article class="datoUsuario">
                    <h2>Tarjetas</h2>
                    <%
                        for (Tarjeta t : usuarioSesion.getTarjetas()) {
                    %>
                    <p><%=t.getNumero()%></p>
                    <%
                        }
                    %>
                </article>
                <article class="datoUsuario">
                    <h2>Direcciones</h2>
                    <%
                        DireccionDAO direccionDAO = new DireccionDAO();
                        for (Direccion d : direccionDAO.getDireccionesDe(usuarioSesion)) {
                    %>
                    <p><%=d.getDireccionCompleta()%></p>
                    <%
                        }
                    %>
                </article>
                <article class="datoUsuario">
                    <h2>Teléfono</h2>
                    <p><%=usuarioSesion.getTelefono()%></p>
                </article>
                <article class="datoUsuario">
                    <h2>Fecha de nacimiento</h2>
                    <p><%=usuarioSesion.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/uuuu"))%></p>
                </article>
                <article class="datoUsuario">
                    <h2>Fecha de última conexión</h2>
                    <p><%=usuarioSesion.getUltimaConexion().format(DateTimeFormatter.ofPattern("dd/MM/uuuu ' a las ' HH:mm:ss"))%></p>
                </article>
                <article class="datoUsuario">
                    <h2>Tipo</h2>
                    <p><%=usuarioSesion.getTipo().toString()%></p>
                </article>
            </section>
            <a href="./index.jsp">Volver a inicio</a>
            <a href="./cliente.jsp">Atrás</a>
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
