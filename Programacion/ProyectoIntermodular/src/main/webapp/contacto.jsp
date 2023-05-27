<%--
    Document   : contacto
    Created on : May 27, 2023, 5:30:06 PM
    Author     : ivan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Contacto | ARTES DORADAS</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto&family=Rock+Salt&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="./css/header.css">
        <link rel="stylesheet" type="text/css" href="./css/contacto.css">
        <link rel="stylesheet" type="text/css" href="./css/footer.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <link rel="icon" type="image/png" href="./img/logo.png">
    </head>
    <body>
        <%@include file="./header.jsp"%>
        <main id="principalWeb">
            <section class="contacto">
                <h2 class="contactoTittle">INFORMACIÓN DE CONTACTO</h2>
                <article class="contactoArticle">
                    <p>Estamos encantados de ayudarte y responder tus preguntas. Puedes contactarnos de las siguientes formas:</p>
                    <p><strong>Teléfono:</strong> (+34) 985 326 712</p>
                    <p><strong>Correo electrónico:</strong> info@artesdoradas.es</p>
                </article>
            </section>
            <section class="tickets">
                <h2 class="ticketsTittle">TICKETS</h2>
                <p>Si prefieres, también puedes utilizar el siguiente formulario para enviarnos un ticket. Intentaremos contestar en la mayor brevedad posible:</p>
                <form action="#" class="ticketsForm">
                    <input type="text" name="asunto" id="ticketsAsunto" placeholder="Asunto...">
                    <textarea name="mensaje" id="ticketsMensaje" placeholder="Mensaje..."></textarea>
                    <div class="submitAndResetWrapper">
                        <input type="submit" value="Enviar" class="ticketsEnviar">
                        <input type="reset" value="Borrar todo" class="ticketsBorrar">
                    </div>
                </form>
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
