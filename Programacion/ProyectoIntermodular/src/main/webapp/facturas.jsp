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
                facturas = (usuarioSesion.esAdmin()) ? facturaDAO.getAll() : facturaDAO.getByCliente(usuarioSesion);
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
                            <th>Código Factura</th>
                            <th>Pedido asociado</th>
                            <th>Dirección Facturación</th>
                            <th>Fecha de Facturación</th>
                            <th>Total con IVA</th>
                            <th>Exportar a XML</th>
                            <th>Exportar a PDF</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <%
                                for (Factura factura : facturas) {
                                    if (usuarioSesion.esAdmin()) {
                            %>
                            <td data-label="Cliente"><%=factura.getCliente().getCorreoElectronico()%></td>
                            <%
                                }
                            %>
                            <td data-label="Código factura"><%=factura.getCodigo()%></td>
                            <td data-label="Pedido asociado"><%=factura.getPedido().getCodigo()%></td>
                            <td data-label="Dirección facturación"><%=factura.getDireccion().getDireccionCompleta()%></td>
                            <td data-label="Fecha de facturación"><%=factura.getFechaFactura().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))%></td>
                            <td data-label="Total con IVA"><%=factura.getPedido().getPrecioTotal() + " €"%></td>
                            <td class="centrado" data-label="Exportar a XML"><a href="ExportarXML?id=<%=factura.getCodigo()%>"><img class="fotoTD" alt="logo XML" src="./img/xml.png"/></a></td>
                            <td class="centrado" data-label="Exportar a PDF"><a href="ExportarPDF?id=<%=factura.getCodigo()%>"><img class="fotoTD" alt="logo PDF" src="./img/pdf.png"/></a></td>
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
