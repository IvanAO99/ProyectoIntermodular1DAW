<%--
    Document   : producto
    Created on : May 16, 2023, 5:14:37 PM
    Author     : ivan
--%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="modelo.dto.Disco"%>
<%@page import="modelo.dto.Disco"%>
<%@page import="modelo.dto.Libro"%>
<%@page import="java.util.List"%>
<%@page import="modelo.dto.Opinion"%>
<%@page import="modelo.dao.OpinionDAO"%>
<%@page import="modelo.dao.LibroDAO"%>
<%@page import="modelo.dao.DiscoDAO"%>
<%@page import="modelo.dao.ProductoDAO"%>
<%@page import="modelo.dto.Usuario"%>
<%@page import="modelo.dto.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Producto producto = null;
    List<Opinion> opiniones = null;
    Usuario usuarioSesion = (session != null && session.getAttribute("usuario") != null) ? (Usuario) session.getAttribute("usuario") : null;
    if (request != null && request.getParameter("codProducto") != null && request.getParameter("codProducto").chars().allMatch(Character::isDigit)) {
        int codigo = Integer.parseInt(request.getParameter("codProducto"));
        //producto = new ProductoDAO().getByCodigo(codigo);
        producto = (new LibroDAO().getByCodigo(codigo) != null) ? new LibroDAO().getByCodigo(codigo) : new DiscoDAO().getByCodigo(codigo);
        if (producto != null) {
            opiniones = new OpinionDAO().getByProducto(producto);
        }
    }
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
        <link rel="stylesheet" type="text/css" href="./css/producto.css">
        <link rel="stylesheet" type="text/css" href="./css/footer.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <link rel="icon" type="image/png" href="./img/logo.png">
    </head>
    <body>
        <header id="cabeceraWeb">
            <div id="tittle">
                <a href="../index.html"><img id="logo" src="../img/logo.png"></a>
                <h1>ARTES DORADAS</h1>
            </div>
            <nav id="navegadorPrincipal">
                <ul id="menu">
                    <li><a href="./index.html"><i class="fa-solid fa-house"></i>INICIO</a></li>
                    <li><a href="./categorias.html"><i class="fa-solid fa-list"></i>CATEGORÍAS</a></li>
                    <li><a href="./sobre_nosotros.html"><i class="fa-solid fa-address-card"></i>SOBRE NOSOTROS</a></li>
                    <li><a href="./contacto.html"><i class="fa-solid fa-phone"></i>CONTACTO</a></li>
                    <li><a href="./formulario.html"><i class="fa-solid fa-user"></i>LOGIN</a></li>
                </ul>
            </nav>
        </header>
        <main id="principalWeb">
            <%
                if (producto == null) {
            %>
            <p id="error">(*) No se ha encontrado el producto</p>
            <%
            } else {
            %>
            <section id="datosPrincipalesyAnyadir">
                <figure>
                    <img src="<%=producto.getFoto()%>" alt="Foto del producto">
                </figure>
                <section>
                    <article>
                        <%
                            if (request.getAttribute("errorAnyadirCarrito") != null && (boolean) request.getAttribute("errorAnyadirCarrito")) {
                        %>
                        <p id="error">(*)
                            <a href="#">Inicia sesión</a>
                            o
                            <a href="#"> registrate</a>
                            para añadir a la lista de deseos o cesta
                        </p>
                        <%
                        } else if (request.getAttribute("errorFormCarrito") != null && (boolean) request.getAttribute("errorFormCarrito")) {
                        %>
                        <p id="error">(*) Error al añadir al carrito</p>
                        <%
                            }
                        %>
                        <h2 id="nombreProducto"><%=producto.getNombre()%></h2>
                        <p id="autorProducto"><%=producto instanceof Libro ? "Autor: ".concat(((Libro) producto).getAutor()) : "Artista: ".concat(((Disco) producto).getArtista())%></p>
                        <p id="proveedorProducto"><%=producto instanceof Libro ? "Editorial: ".concat(((Libro) producto).getEditorial()) : "Discográfica: ".concat(((Disco) producto).getDiscografica())%></p>
                    </article>
                    <form action="#">
                        <input type="submit" value="Añadir a deseados" class="boton">
                    </form>
                    <div id="cesta">
                        <p id="stock">Stock: <%=producto.getStock()%></p>
                        <p id="precio">Precio: <%=producto.getPrecio()%>€ + IVA(<%=producto.getIva()%>%)</p>
                        <form action="AnyadirAlCarrito" id="formCesta">
                            <input type="hidden" name="codProducto" value="<%=producto.getCodigo()%>"/>
                            <input type="number" name="cantidad" id="cantidad" min="1" max="99" value="1">
                            <input type="submit" value="Añadir a la cesta" class="boton">
                        </form>
                    </div>
                </section>
            </section>
            <section id="datosAlternativos">
                <article>
                    <!-- Descripción -->
                    <p><%=producto.getDescripcion()%></p>
                </article>
                <table id="tablaDatosAlternativos" cellspacing="0">
                    <tbody id="body">
                        <%
                            if (producto instanceof Libro) {
                                Libro libro = (Libro) producto;
                        %>
                        <tr>
                            <td class="tdIzquierda">Autor</td>
                            <td class="tdDerecha"><%=libro.getAutor()%></td>
                        </tr>
                        <tr>
                            <td class="tdIzquierda">Editorial</td>
                            <td class="tdDerecha"><%=libro.getEditorial()%></td>
                        </tr>
                        <tr>
                            <td class="tdIzquierda">Formato</td>
                            <td class="tdDerecha"><%=libro.getFormato()%></td>
                        </tr>
                        <tr>
                            <td class="tdIzquierda">Nº páginas</td>
                            <td class="tdDerecha"><%=libro.getnPaginas()%></td>
                        </tr>
                        <tr>
                            <td class="tdIzquierda">ISBN</td>
                            <td class="tdDerecha"><%=libro.getIsbn()%></td>
                        </tr>
                        <%
                        } else {
                            Disco disco = (Disco) producto;
                        %>
                        <tr>
                            <td class="tdIzquierda">Artista</td>
                            <td class="tdDerecha"><%=disco.getArtista()%></td>
                        </tr>
                        <tr>
                            <td class="tdIzquierda">Discográfica</td>
                            <td class="tdDerecha"><%=disco.getDiscografica()%></td>
                        </tr>
                        <tr>
                            <td class="tdIzquierda">Tipo</td>
                            <td class="tdDerecha"><%=disco.getTipo()%></td>
                        </tr>
                        <tr>
                            <td class="tdIzquierda">Canciones</td>
                            <td class="tdDerecha"><%=disco.getCanciones()%></td>
                        </tr>
                        <tr>
                            <td class="tdIzquierda">ASIN</td>
                            <td class="tdDerecha"><%=disco.getAsin()%></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </section>
            <section id="opiniones">
                <h2 id="tituloOpiniones">OPINIONES</h2>
                <div id="formularioOpinionWrapper">
                    <h2 id="tituloFormulario">Deja una opinión de este producto</h2>
                    <form action="#" id="formularioOpinion">
                        <p id="error">(*)
                            <a href="#">Inicia sesión</a>
                            o
                            <a href="#"> registrate</a>
                            para dejar una reseña
                        </p>
                        <p id="error">(*) Realiza un pedido de este producto para dejar una reseña</p>
                        <textarea name="opinion" id="opinion" rows="10" placeholder="Escribe una opinion..."></textarea>
                        <input type="submit" value="Enviar" class="boton">
                    </form>
                </div>
                <%
                    if (!opiniones.isEmpty()) {
                        for (Opinion opinion : opiniones) {
                %>
                <article class="opinion">
                    <div class="usuarioYfecha">
                        <div class="fotoUserYNombre">
                            <figure class="fotoPerfilWrapper">
                                <img src="<%=opinion.getCliente().getFoto()%>" alt="" class="fotoPerfil">
                            </figure>
                            <p><%=opinion.getCliente().getNombreCompleto()%></p>
                        </div>
                        <p><%=opinion.getFechaPublicacion().format(DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:ss"))%></p>
                    </div>
                    <p class="mensaje"><%=opinion.getMensaje()%></p>
                </article>
                <%
                    }
                } else {
                %>
                <p id="error">(*) Aún no hay reseñas</p>
                <%
                    }
                %>
            </section>
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
