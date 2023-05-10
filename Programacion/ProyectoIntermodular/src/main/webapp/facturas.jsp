<%--
    Document   : facturas
    Created on : May 9, 2023, 7:45:02 PM
    Author     : ivan
--%>

<%@page import="java.util.List"%>
<%@page import="modelo.dao.FacturaDAO"%>
<%@page import="modelo.dto.Factura"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="modelo.dto.Direccion"%>
<%@page import="modelo.dao.DireccionDAO"%>
<%@page import="modelo.dto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Factura> facturas = null;
    FacturaDAO facturaDAO = new FacturaDAO();
    Usuario usuarioSesion = (session != null && session.getAttribute("usuario") != null) ? (Usuario) session.getAttribute("usuario") : null;
%>

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
        <link rel="stylesheet" type="text/css" href="./css/datos.css">
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
                            if ((usuarioSesion == null)) {
                        %>
                    <li><a href="./login.jsp"><i class="fa-solid fa-user"></i>Login</a></li>
                        <%
                        } else {
                        %>
                    <li><a href="./cliente.jsp"><i class="fa-solid fa-user"></i><%= usuarioSesion.getNombreCompleto()%></a></li>
                            <%
                                if (usuarioSesion.esCliente()) {
                            %>
                    <li><a href="./cesta.html"><i class="fa-solid fa-phone"></i>Cesta</a></li>
                        <%
                                }
                            }
                        %>
                </ul>
            </nav>
        </header>
        <main id="principalWeb">
            <%
                if ((usuarioSesion == null)) {
            %>
            <section id="datos">
                <p>JOSÉ RAMÓN!!! NO PUEDES VER ESTO SI NO ERES CLIENTE o ADMINISTRADOR :'(</p>
                <p><a href="./index.jsp">Volver al index</a></p>
            </section>
            <%
            } else {
                facturas = (usuarioSesion.esAdmin()) ? facturaDAO.getAll() : facturaDAO.getByCliente(usuarioSesion);
            %>
            <section id="datos">
                <table border="1" width="100%">
                    <thead>
                        <tr>
                            <% if (usuarioSesion.esAdmin()) {
                            %>
                            <th>Cliente</th>
                                <%
                                    }
                                %>
                            <th>Código Factura</th>
                            <th>Pedido asociado</th>
                            <th>Dirección Facturación</th>
                            <th>Fecha de Facturación</th>
                            <th>Total con IVA</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <%
                                for (Factura factura : facturas) {
                                    if (usuarioSesion.esAdmin()) {
                            %>
                            <th><%=factura.getCliente().getCorreoElectronico()%></th>
                                <%
                                    }
                                %>
                            <td><%=factura.getCodigo()%></td>
                            <td><%=factura.getPedido().getCodigo()%></td>
                            <td><%=factura.getDireccion().getDireccionCompleta()%></td>
                            <td><%=factura.getFechaFactura().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))%></td>
                            <td><%=factura.getPedido().getPrecioTotal() + "€"%></td>
                            <td class=\"centrado\"><a href=\"ExportarXML?id="+ factura.getCodigo() +"\"><img class=\"icono\" alt=\"logo XML\" src=\"imagenes/xml.png\"/></a></td>
                            <td class=\"centrado\"><a href=\"ExportarPDF?id="+ factura.getCodigo() +"\"><img class=\"icono\" alt=\"logo PDF\" src=\"imagenes/pdf.png\"/></a></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
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