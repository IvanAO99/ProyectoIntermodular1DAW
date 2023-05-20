<%--
    Document   : carrito
    Created on : May 20, 2023, 7:53:09 PM
    Author     : ivan
--%>

<%@page import="modelo.dto.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="modelo.dto.Direccion"%>
<%@page import="modelo.dao.DireccionDAO"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="modelo.dto.Producto"%>
<%@page import="modelo.dto.Pedido"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario usuarioSesion = (session != null && session.getAttribute("usuario") != null) ? (Usuario) session.getAttribute("usuario") : null;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Cesta | ARTES DORADAS</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto&family=Rock+Salt&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="./css/header.css">
        <link rel="stylesheet" type="text/css" href="./css/cesta.css">
        <link rel="stylesheet" type="text/css" href="./css/footer.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <link rel="icon" type="image/png" href="../img/logo.png">
    </head>
    <body>
        <header id="cabeceraWeb">
            <div id="tittle">
                <a href="./index.jsp"><img id="logo" src="./img/logo.png"></a>
                <h1 id="nombreEmpresa">ARTES DORADAS</h1>
            </div>
            <nav id="navegadorPrincipal">
                <ul id="menu">
                    <li><a href="./index.jsp"><i class="fa-solid fa-house"></i>Inicio</a></li>
                    <li><a href="./productos.jsp"><i class="fa-solid fa-list"></i>Productos</a></li>
                    <li><a href="./sobre_nosotros.html"><i class="fa-solid fa-address-card"></i>Sobre nosotros</a></li>
                    <li><a href="./contacto.html"><i class="fa-solid fa-phone"></i>Contacto</a></li>
                        <%
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
                if (usuarioSesion == null || usuarioSesion.esAdmin()) {
            %>
            <p>(*) No tienes permisos para acceder a esta sección</p>
            <%
            } else if (session.getAttribute("carrito") == null) {
            %>
            <p>(*) No tienes creado ningún carrito</p>
            <%
            } else {
                Pedido carrito = (Pedido) session.getAttribute("carrito");
            %>
            <<form method="post" action="ActualizarCarrito" id="actualizarform">
                <section id="productos">
                    <%
                        for (Entry<Producto, Entry<Integer, Double>> linea : carrito.getLineasPedido().entrySet()) {
                    %>

                    <article class="producto">
                        <figure>
                            <img src="<%=linea.getKey().getFoto()%>" alt="img del producto">
                        </figure>
                        <div class="datosProducto">
                            <p><%=linea.getKey().getNombre()%></p>
                            <input type="hidden" name="codProducto" value="<%=linea.getKey().getCodigo()%>"/>
                            <input type="number" onchange="this.form.submit()" name="cantidad" id="" min="1" max="99" value="<%=linea.getValue().getKey()%>">
                            <p><%=linea.getValue().getKey() * linea.getValue().getValue()%></p>
                        </div>
                        <p>Eliminar</p>
                    </article>
                    <%
                        }

                        List<Direccion> direcciones = new DireccionDAO().getDireccionesDe(carrito.getCliente());
                    %>
                </section>
                <section id="direcciones">
                    <select name="direccion" id="direccion" onchange="this.form.submit()">
                        <<option selected="selected" hidden="hidden" value="<%=direcciones.get(0).getCodigo()%>"><%=direcciones.get(0).getDireccionCompleta()%></option>
                        <%
                            for (Direccion direccion : direcciones) {
                        %>
                        <option value="<%=direccion.getCodigo()%>"><%=direccion.getDireccionCompleta()%></option>
                        <%
                            }
                        %>
                    </select>
                </section>
                <section id="pedido">
                    <p><%=carrito.getPrecioTotal()%></p>
                    <input type="submit" value="Realizar pedido">
                </section>
            </form>
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
