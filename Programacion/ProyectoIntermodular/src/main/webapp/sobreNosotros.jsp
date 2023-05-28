<%--
    Document   : sobreNosotros
    Created on : May 28, 2023, 8:55:17 PM
    Author     : ivan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Sobre nosotros | ARTES DORADAS</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto&family=Rock+Salt&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="./css/header.css">
        <link rel="stylesheet" type="text/css" href="./css/about.css">
        <link rel="stylesheet" type="text/css" href="./css/footer.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <link rel="icon" type="image/png" href="./img/logo.png">
    </head>
    <body>
        <script src="js/scripts.js"></script>
        <%@include file="./header.jsp"%>
        <main>
            <section id="sobre-nosotros">
                <div class="container">
                    <h2>Sobre nosotros</h2>
                    <p>Bienvenidos a Artes Doradas, tu tienda online de libros y vinilos.</p>
                    <p>Nos especializamos en ofrecer una amplia selección de libros de diversos géneros literarios, así como
                        una extensa colección de vinilos de música para los amantes del sonido analógico.</p>
                    <p>En Artes Doradas creemos en la importancia de la cultura y el arte, y nos esforzamos por brindar a
                        nuestros clientes productos de alta calidad que les permitan disfrutar de experiencias
                        enriquecedoras.</p>
                    <p>Trabajamos con los mejores proveedores para asegurarnos de ofrecer libros y vinilos auténticos y
                        originales. Nuestro equipo está compuesto por apasionados de la literatura y la música, y estamos
                        aquí para ayudarte a encontrar exactamente lo que estás buscando.</p>
                    <p>Explora nuestra tienda online y descubre nuestra cuidadosa selección de libros y vinilos. ¡Estamos
                        seguros de que encontrarás algo que te emocione!</p>
                    <p>Gracias por visitar Artes Doradas. Si tienes alguna pregunta o necesitas ayuda, no dudes en ponerte
                        en contacto con nuestro equipo de atención al cliente. ¡Estaremos encantados de ayudarte!</p>
                    <p>¡Disfruta de la cultura y el arte con Artes Doradas!</p>
                </div>
            </section>
        </main>
        <main>
            <section class="id-card">
                <section class="id-title">
                    <h2>EMPLEADO 1</h2>
                    <p><strong>Artes Doradas, S.L.</strong></p>
                </section>
                <section class="informacion-personal">
                    <section class="foto">
                        <figure class="careto">
                            <img src="./img/ivan.jpeg" alt="">
                        </figure>
                    </section>
                    <section class="datos">
                        <article>
                            <h2 class="h2">Nombre:</h2>
                            <p>Iván Ayuso Olivera</p>
                        </article>
                        <article>
                            <h2 class="h2">Puesto:</h2>
                            <p>Administrador</p>
                        </article>
                        <article>
                            <h2 class="h2">Se unió:</h2>
                            <p>15/09/2022</p>
                        </article>
                        <article>
                            <h2 class="h2">Código de empleado:</h2>
                            <p>AD-A001</p>
                        </article>
                    </section>
                </section>
                <section class="cod-barras">
                    <figure>
                        <img src="./img/codbarras.png" alt="">
                    </figure>
                </section>
                <section class="caducidad">
                    <p>Expira el 21/06/2023</p>
                </section>
                <section class="card-footer">
                    <figure class="partner">
                        <img src="./img/partner.png" alt="">
                    </figure>
                    <figure class="qr">
                        <img src="./img/qr.png" alt="">
                    </figure>
                </section>
            </section>
            <section class="id-card">
                <section class="id-title">
                    <h2>EMPLEADO 2</h2>
                    <p><strong>Artes Doradas, S.L.</strong></p>
                </section>
                <section class="informacion-personal">
                    <section class="foto">
                        <figure class="careto">
                            <img src="./img/enrique.png" alt="">
                        </figure>
                    </section>
                    <section class="datos">
                        <article>
                            <h2 class="h2">Nombre:</h2>
                            <p>Enrique Azorín Castellano</p>
                        </article>
                        <article>
                            <h2 class="h2">Puesto:</h2>
                            <p>Administrador</p>
                        </article>
                        <article>
                            <h2 class="h2">Se unió:</h2>
                            <p>15/09/2022</p>
                        </article>
                        <article>
                            <h2 class="h2">Código de empleado:</h2>
                            <p>AD-A002</p>
                        </article>
                    </section>
                </section>
                <section class="cod-barras">
                    <figure>
                        <img src="./img/codbarras.png" alt="">
                    </figure>
                </section>
                <section class="caducidad">
                    <p>Expira el 21/06/2023</p>
                </section>
                <section class="card-footer">
                    <figure class="partner">
                        <img src="./img/partner.png" alt="">
                    </figure>
                    <figure class="qr">
                        <img src="./img/qr.png" alt="">
                    </figure>
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
