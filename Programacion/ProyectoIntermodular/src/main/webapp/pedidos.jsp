<%--
    Document   : facturas
    Created on : May 9, 2023, 7:45:02 PM
    Author     : ivan
--%>

<%@page import="modelo.dao.PedidoDAO"%>
<%@page import="modelo.dto.Pedido"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="modelo.dto.Direccion"%>
<%@page import="modelo.dao.DireccionDAO"%>
<%@page import="modelo.dto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Pedido> pedidos = null;
    PedidoDAO pedidoDAO = new PedidoDAO();
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
                pedidos = (usuarioSesion.esAdmin()) ? pedidoDAO.getAll() : pedidoDAO.getByCliente(usuarioSesion);
            %>
            <section id="datos">
                <table width="max-content" cellspacing="0">
                    <thead>
                        <tr>
                            <% if (usuarioSesion.esAdmin()) {
                            %>
                            <th>Cliente</th>
                                <%
                                    }
                                %>
                            <th>Código de pedido</th>
                            <th>Códigos de productos</th>
                            <th>Dirección</th>
                            <th>Fecha</th>
                            <th>Precio total + IVA</th>
                            <th>Facturado</th>
                                <%
                                    if (usuarioSesion.esCliente() || usuarioSesion.esAdmin()) {
                                %>
                            <th></th>
                                <%
                                    }
                                %>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Pedido pedido : pedidos) {
                        %>
                        <tr>
                            <%
                                if (usuarioSesion.esAdmin()) {
                            %>
                            <td><%=pedido.getCliente().getCorreoElectronico()%></td>
                            <%
                                }
                            %>
                            <td><%=pedido.getCodigo()%></td>
                            <td><%=pedido.getCodigosProductos()%></td>
                            <td><%=pedido.getDireccion().getDireccionCompleta()%></td>
                            <td><%=pedido.getFechaPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))%></td>
                            <td><%=pedido.getPrecioTotal() + " €"%></td>
                            <td><%=pedido.isFacturado() ? "Sí" : "No"%></td>
                            <%
                                if (usuarioSesion.esCliente() || usuarioSesion.esAdmin()) {
                            %>
                            <td>
                                <%
                                    if (pedido.isFacturado()) {
                                        out.print("-");
                                    } else {
                                %>
                                <form name="facturar-<%= pedido.getCodigo()%>" method="post" action="Facturar" id="formFacturar">
                                    <input name="id" type="hidden" value="<%= pedido.getCodigo()%>" />
                                    <select name="direccion" class="boton">
                                        <option selected="selected" hidden="hidden" value="<%=pedido.getDireccion().getCodigo()%>"><%=pedido.getDireccion().getDireccionCompleta()%></option>
                                        <%
                                            for (Direccion dirFact : new DireccionDAO().getDireccionesDe(pedido.getCliente())) {
                                        %>
                                        <option value="<%=dirFact.getCodigo()%>"><%=dirFact.getDireccionCompleta()%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                    <input type="submit" value="Facturar" class="boton"/>
                                </form>
                                <%
                                    }
                                %>
                            </td>
                            <%
                                }
                            %>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </section>
            <a href="./index.jsp" class="boton">Volver a inicio</a>
            <a href="./cliente.jsp" class="boton">Atrás</a>
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
