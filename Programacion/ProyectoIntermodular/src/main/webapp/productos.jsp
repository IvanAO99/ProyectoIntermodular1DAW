<%--
    Document   : productos
    Created on : May 7, 2023, 9:16:14 PM
    Author     : ivan
--%>

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
                    <form action="" class="inputWrapper">
                        <div class="inputLabelWrapper">
                            <input type="radio" name="categoria" id="checkboxLibros">
                            <label for="checkboxLibros" class="generoLabel">Libros</label>
                        </div>
                        <div class="inputLabelWrapper">
                            <input type="radio" name="categoria" id="checkboxDiscos">
                            <label for="checkboxDiscos" class="generoLabel">Discos</label>
                        </div>
                    </form>
                </div>
                <div id="generosWrapper">
                    <h2>GÉNEROS</h2>
                    <form action="" class="inputWrapper">
                        <%
                            Set<Categoria> categorias = new CategoriaDAO().getCategoriasDeLibros();
                            //Set<Categoria> categorias = new CategoriaDAO().getCategoriasDeDiscos();

                            for (Categoria categoria : categorias) {
                        %>
                        <div class="inputLabelWrapper">
                            <input type="checkbox" name="" id="checkboxGenero<%=categoria.getCodigo()%>">
                            <label for="checkboxGenero<%=categoria.getCodigo()%>"><%=categoria.getNombre()%></label>
                        </div>
                        <%
                            }
                        %>
                    </form>
                </div>
                <div id="submitResetWrapper">
                    <input type="submit" value="Aplicar" id="submitAplicar">
                    <input type="reset" value="Resetear" id="resetResetear">
                </div>
            </aside>
            <section id="productsMain">
                <input type="search" name="buscador" id="searchBox" placeholder="Buscador...">
                <section id="products">
                    <div id="productsWrapper">
                        <%
                            ArrayList<Producto> productos = new ProductoDAO().getAll();

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
