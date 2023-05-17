<%--
    Document   : producto
    Created on : May 16, 2023, 5:14:37 PM
    Author     : ivan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Cesta | ARTES DORADAS</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto&family=Rock+Salt&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="../css/header.css">
        <link rel="stylesheet" type="text/css" href="../css/producto.css">
        <link rel="stylesheet" type="text/css" href="../css/footer.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <link rel="icon" type="image/png" href="../img/logo.png">
    </head>
    <body>
        <header id="cabeceraWeb">
            <div id="tittle">
                <a href="../index.html"><img id="logo" src="../img/logo.png"></a>
                <h1>ARTES DORADAS</h1>
            </div>
            <nav id="navegadorPrincipal">
                <ul id="menu">
                    <li><a href="../index.html"><i class="fa-solid fa-house"></i>INICIO</a></li>
                    <li><a href="./categorias.html"><i class="fa-solid fa-list"></i>CATEGORÍAS</a></li>
                    <li><a href="./sobre_nosotros.html"><i class="fa-solid fa-address-card"></i>SOBRE NOSOTROS</a></li>
                    <li><a href="./contacto.html"><i class="fa-solid fa-phone"></i>CONTACTO</a></li>
                    <li><a href="./formulario.html"><i class="fa-solid fa-user"></i>LOGIN</a></li>
                </ul>
            </nav>
        </header>
        <main id="principalWeb">
            <section id="datosPrincipales">
                <figure>
                    <img src="#" alt="Foto del producto">
                </figure>
                <article>
                    <h2>Nombre</h2>
                    <p>Autor / Artista</p>
                    <p>Editorial / Discográfica</p>
                </article>
            </section>
            <section id="añadirAX">
                <form action="#">
                    <input type="submit" value="Añadir a deseados">
                </form>
                <div id="cesta">
                    <p>Stock: X</p>
                    <p>Precio + IVA</p>
                    <form action="#">
                        <input type="number" name="cantidad" id="cantidad" min="1" max="99" value="1">
                        <input type="submit" value="Añadir a la cesta">
                    </form>
                </div>
            </section>
            <section id="datosAlternativos">
                <article>
                    <!-- Descripción -->
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quae maiores consequuntur, quaerat commodi ex aliquam veritatis accusamus, nulla iste in iusto impedit sit blanditiis sequi doloribus, officia magni eius non.</p>
                </article>
                <table>
                    <tbody>
                        <tr>
                            <td>Autor / Artista</td>
                            <td>Autor / Artista</td>
                        </tr>
                        <tr>
                            <td>Editorial / Discográfica</td>
                            <td>Editorial / Discográfica</td>
                        </tr>
                        <tr>
                            <td>Formato / Tipo</td>
                            <td>Tapa dura / Vinilo</td>
                        </tr>
                        <tr>
                            <td>Nº páginas / Canciones</td>
                            <td>1000 / todas las canciones</td>
                        </tr>
                        <tr>
                            <td>ISBN / ASIN</td>
                            <td>ISBN / ASIN</td>
                        </tr>
                    </tbody>
                </table>
            </section>
            <section id="opiniones">
                <h2>OPINIONES</h2>
                <div id="formularioOpinion">
                    <h2>Deja una opinión de este producto</h2>
                    <form action="#">
                        <textarea name="opinion" id="opinion" cols="30" rows="10"></textarea>
                        <input type="submit" value="">
                    </form>
                </div>
                <article class="opinion">
                    <div class="usuarioYfecha">
                        <div class="fotoUserYNombre">
                            <figure>
                                <img src="#" alt="Foto de perfil">
                            </figure>
                            <p>Nombre</p>
                        </div>
                        <p>Fecha de publicación</p>
                    </div>
                    <p>Mensaje</p>
                </article>
                <article class="opinion">
                    <div class="usuarioYfecha">
                        <div class="fotoUserYNombre">
                            <figure>
                                <img src="#" alt="Foto de perfil">
                            </figure>
                            <p>Nombre</p>
                        </div>
                        <p>Fecha de publicación</p>
                    </div>
                    <p>Mensaje</p>
                </article>
                <article class="opinion">
                    <div class="usuarioYfecha">
                        <div class="fotoUserYNombre">
                            <figure>
                                <img src="#" alt="Foto de perfil">
                            </figure>
                            <p>Nombre</p>
                        </div>
                        <p>Fecha de publicación</p>
                    </div>
                    <p>Mensaje</p>
                </article>
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
