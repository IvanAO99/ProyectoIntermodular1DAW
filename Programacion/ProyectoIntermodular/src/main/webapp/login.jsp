<%-- 
    Document   : login.jsp
    Created on : May 2, 2023, 8:15:38 PM
    Author     : ivan
--%>

<%@page import="modelo.dto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!Doctype html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Login | ARTES DORADAS</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto&family=Rock+Salt&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="./css/header.css">
        <link rel="stylesheet" type="text/css" href="./css/login.css">
        <link rel="stylesheet" type="text/css" href="./css/footer.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <link rel="icon" type="image/png" href="../img/logo.png">
    </head>
    <body>
        <script src="js/scripts.js"></script>
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
        <main>
            <section class="formulario">
                <section class="encabezadoForm">
                    <h2>INICIAR SESION</h2>
                </section>
                <section class="datosForm">
                    <form action="LoginServlet" method="post">
                        <label for="email">CORREO:</label>
                        <input id="email" type="email" name="email" placeholder="Correo electrónico">
                        <label for="password">CONTRASEÑA:</label>
                        <input id="password" type="password" name="password" placeholder="Contraseña">
                        <input type="submit" name="enviar" id="submit" value="Enviar">
                        <%
                            if (request.getAttribute("error") != null && (boolean) request.getAttribute("error")) {
                                out.println("<p>(*) Nombre de correo o contraseña inválidos. Vuelve a intentarlo</p>");
                            }
                        %>
                    </form>
                    <p class="login">
                        ¿No tienes una cuenta?
                        <a class="enlace" href="./registro.html">
                            Registrate
                        </a>
                    </p>
                </section>
                <section class="formFooter">
                    <a class="enlace" href="../index.jsp">
                        <p>
                            Volver a inicio
                        </p>
                    </a>
                </section>
            </section>
        </main>
        <footer>
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
