<%--
    Document   : productos
    Created on : May 7, 2023, 9:16:14 PM
    Author     : ivan
--%>

<%@page import="modelo.dao.DiscoDAO"%>
<%@page import="modelo.dao.LibroDAO"%>
<%@page import="java.util.TreeSet"%>
<%@page import="modelo.dto.Libro"%>
<%@page import="modelo.dao.ProductoDAO"%>
<%@page import="modelo.dto.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.dao.CategoriaDAO"%>
<%@page import="java.util.Set"%>
<%@page import="modelo.dto.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Productos | ARTES DORADAS</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto&family=Rock+Salt&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="./css/categorias.css">
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
                    <li><a href="./formulario.html"><i class="fa-solid fa-user"></i>Login</a></li>
                </ul>
            </nav>
        </header>
        <main id="principalWeb">
            <aside id="filtro">
                <div id="categoriasWrapper">
                    <h2>CATEGORÍAS</h2>
                    <form action="MostrarGeneros" class="inputWrapper">
                        <%
                            String tipo = request.getParameter("tipo");

                            if (tipo == null) {
                        %>
                        <div class="inputLabelWrapper">
                            <input type="radio" name="tipoProducto" id="checkboxLibros" onchange="this.form.submit()" value="libro">
                            <label for="checkboxLibros" class="generoLabel">Libros</label>
                        </div>
                        <div class="inputLabelWrapper">
                            <input type="radio" name="tipoProducto" id="checkboxDiscos" onchange="this.form.submit()" value="disco">
                            <label for="checkboxDiscos" class="generoLabel">Discos</label>
                        </div>
                        <%
                        } else if (tipo.equals("libros")) {
                        %>
                        <div class="inputLabelWrapper">
                            <input type="radio" name="tipoProducto" id="checkboxLibros" onchange="this.form.submit()" value="libro" checked="checked">
                            <label for="checkboxLibros" class="generoLabel">Libros</label>
                        </div>
                        <div class="inputLabelWrapper">
                            <input type="radio" name="tipoProducto" id="checkboxDiscos" onchange="this.form.submit()" value="disco">
                            <label for="checkboxDiscos" class="generoLabel">Discos</label>
                        </div>
                        <%
                        } else {
                        %>
                        <div class="inputLabelWrapper">
                            <input type="radio" name="tipoProducto" id="checkboxLibros" onchange="this.form.submit()" value="libro">
                            <label for="checkboxLibros" class="generoLabel">Libros</label>
                        </div>
                        <div class="inputLabelWrapper">
                            <input type="radio" name="tipoProducto" id="checkboxDiscos" onchange="this.form.submit()" value="disco" checked="checked">
                            <label for="checkboxDiscos" class="generoLabel">Discos</label>
                        </div>
                        <%
                            }
                        %>
                    </form>
                </div>
                <div id="generosWrapper">
                    <h2>GÉNEROS</h2>
                    <form action="MostrarProductos" class="inputWrapper">
                        <%
                            Set<Categoria> categorias = (((Set<Categoria>) request.getAttribute("categorias")) == null) ? new TreeSet<>() : (Set<Categoria>) request.getAttribute("categorias");

                            for (Categoria categoria : categorias) {
                        %>
                        <div class="inputLabelWrapper">
                            <input type="hidden" name="tipo" value="<%=tipo%>">
                            <input type="checkbox" name="categoria" id="checkboxGenero<%=categoria.getCodigo()%>" value="<%=categoria.getCodigo()%>">
                            <label for="checkboxGenero<%=categoria.getCodigo()%>"><%=categoria.getNombre()%></label>
                        </div>
                        <%
                            }
                            if (!categorias.isEmpty()) {
                        %>
                        <div id="submitResetWrapper">
                            <input type="submit" value="Aplicar" id="submitAplicar">
                            <input type="reset" value="Resetear" id="resetResetear">
                        </div>
                        <%
                            }
                        %>
                    </form>
                </div>
            </aside>
            <section id="productsMain">
                <input type="search" name="buscador" id="searchBox" placeholder="Buscador...">
                <section id="products">
                    <div id="productsWrapper">
                        <%
                            ArrayList<Libro> libros = (ArrayList<Libro>) request.getAttribute("productos");

                            if (libros == null) {
                                ArrayList<? extends Producto> productos = new ArrayList<>();
                                if (tipo == null) {
                                    productos = new ProductoDAO().getAll();
                                } else if (tipo.equals("libros")) {
                                    productos = new LibroDAO().getAll();
                                } else {
                                    productos = new DiscoDAO().getAll();
                                }

                                for (Producto producto : productos) {

                        %>
                        <a href="./producto.jsp?codProducto=<%=producto.getCodigo()%>">
                            <section class="productWrapper">
                                <figure class="imgWrapper">
                                    <img src="<%=producto.getFoto()%>" alt="Sin foto">
                                </figure>
                                <h2><%=producto.getNombre()%></h2>
                                <p><%=producto.getPrecio() + " €"%></p>
                            </section>
                        </a>
                        <%
                            }
                        } else {
                            for (Producto producto : libros) {

                        %>
                        <a href="./producto.jsp?codProducto=<%=producto.getCodigo()%>">
                            <section class="productWrapper">
                                <figure class="imgWrapper">
                                    <img src="<%=producto.getFoto()%>" alt="Sin foto">
                                </figure>
                                <h2><%=producto.getNombre()%></h2>
                                <p><%=producto.getPrecio() + " €"%></p>
                            </section>
                        </a>
                        <%
                                }
                            }
                        %>
                    </div>
                    <p>Ver más</p>
                </section>
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
    </body>
</html>
