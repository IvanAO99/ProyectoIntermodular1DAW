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
        <section id="datosPrincipalesyAnyadir">
            <figure>
                <img src="./img/libro.jpg" alt="Foto del producto">
            </figure>
            <section>
                <article>
                    <p id="error">(*)
                        <a href="#">Inicia sesión</a>
                        o
                        <a href="#"> registrate</a>
                        para añadir a la lista de deseos o cesta
                    </p>
                    <h2 id="nombreProducto">Nombre</h2>
                    <p id="autorProducto">Autor / Artista</p>
                    <p id="proveedorProducto">Editorial / Discográfica</p>
                </article>
                <form action="#">
                    <input type="submit" value="Añadir a deseados" class="boton">
                </form>
                <div id="cesta">
                    <p id="stock">Stock: X</p>
                    <p id="precio">Precio + IVA</p>
                    <form action="#" id="formCesta">
                        <input type="number" name="cantidad" id="cantidad" min="1" max="99" value="1">
                        <input type="submit" value="Añadir a la cesta" class="boton">
                    </form>
                </div>
            </section>
        </section>
        <section id="datosAlternativos">
            <article>
                <!-- Descripción -->
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quae maiores consequuntur, quaerat commodi
                    ex aliquam veritatis accusamus, nulla iste in iusto impedit sit blanditiis sequi doloribus, officia
                    magni eius non.</p>
            </article>
            <table id="tablaDatosAlternativos" cellspacing="0">
                <tbody id="body">
                    <tr>
                        <td class="tdIzquierda">Autor / Artista</td>
                        <td class="tdDerecha">Autor / Artista</td>
                    </tr>
                    <tr>
                        <td class="tdIzquierda">Editorial / Discográfica</td>
                        <td class="tdDerecha">Editorial / Discográfica</td>
                    </tr>
                    <tr>
                        <td class="tdIzquierda">Formato / Tipo</td>
                        <td class="tdDerecha">Tapa dura / Vinilo</td>
                    </tr>
                    <tr>
                        <td class="tdIzquierda">Nº páginas / Canciones</td>
                        <td class="tdDerecha">1000 / todas las canciones</td>
                    </tr>
                    <tr>
                        <td class="tdIzquierda">ISBN / ASIN</td>
                        <td class="tdDerecha">ISBN / ASIN</td>
                    </tr>
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
            <article class="opinion">
                <div class="usuarioYfecha">
                    <div class="fotoUserYNombre">
                        <figure class="fotoPerfilWrapper">
                            <img src="./img/user.jpg" alt="Foto de perfil" class="fotoPerfil">
                        </figure>
                        <p>Nombre</p>
                    </div>
                    <p>Fecha de publicación</p>
                </div>
                <p class="mensaje">Mensaje</p>
            </article>
            <article class="opinion">
                <div class="usuarioYfecha">
                    <div class="fotoUserYNombre">
                        <figure class="fotoPerfilWrapper">
                            <img src="./img/careto.png" alt="Foto de perfil" class="fotoPerfil">
                        </figure>
                        <p>Nombre</p>
                    </div>
                    <p>Fecha de publicación</p>
                </div>
                <p class="mensaje">Mensaje</p>
            </article>
            <article class="opinion">
                <div class="usuarioYfecha">
                    <div class="fotoUserYNombre">
                        <figure class="fotoPerfilWrapper">
                            <img src="./img/personaLeyendo.jpg" alt="Foto de perfil" class="fotoPerfil">
                        </figure>
                        <p>Nombre</p>
                    </div>
                    <p>Fecha de publicación</p>
                </div>
                <p class="mensaje">Mensaje</p>
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
