/*
** TABLA USUARIOS
*/

INSERT INTO ARTESDORADAS_usuarios (codigo, correo_electronico, password, ultima_conexion, nombre, fecha_nacimiento, telefono, foto, tipo)
VALUES (01, 'ivanayusoolivera@gmail.com', 'lq02lei4mn', TO_DATE('04/02/2023', 'DD-MM-YYYY'), 'ivanao99', TO_DATE('08/12/1999', 'DD-MM-YYYY'), 7265898, './img/ivanao99.jpg', 'Administrador');

INSERT INTO ARTESDORADAS_usuarios (codigo, correo_electronico, password, ultima_conexion, nombre, fecha_nacimiento, telefono, foto, tipo)
VALUES (02, 'eazorin4@gmail.com', 'g01lw9dkg9', TO_DATE('04/02/2023', 'DD-MM-YYYY'), 'kikesito', TO_DATE('12/08/2001', 'DD-MM-YYYY'), 7752141, './img/kikesito.jpg', 'Administrador');

INSERT INTO ARTESDORADAS_usuarios (codigo, correo_electronico, password, ultima_conexion, nombre, fecha_nacimiento, telefono, foto, tipo)
VALUES (03, 'jlopez@gmail.com', '1la03jd7fn', TO_DATE('15/01/2023', 'DD-MM-YYYY'), 'jlopez65', TO_DATE('19/07/1965', 'DD-MM-YYYY'), 6593020, './img/jlopez65.jpg', 'Cliente');

INSERT INTO ARTESDORADAS_usuarios (codigo, correo_electronico, password, ultima_conexion, nombre, fecha_nacimiento, telefono, foto, tipo)
VALUES (04, 'm.garcia.c@gmail.com', '5ps01m2n4d', TO_DATE('26/01/2023', 'DD-MM-YYYY'), 'manuelgarciacano', TO_DATE('03/12/1996', 'DD-MM-YYYY'), 6748620, DEFAULT, 'Cliente');

INSERT INTO ARTESDORADAS_usuarios (codigo, correo_electronico, password, ultima_conexion, nombre, fecha_nacimiento, telefono, foto, tipo)
VALUES (05, 'mariacandel4312@gmail.com', 'p1ow0aken5', TO_DATE('28/01/2023', 'DD-MM-YYYY'), 'marieta95', TO_DATE('24/02/1995', 'DD-MM-YYYY'), 6532014, './img/marieta95.jpg', 'Cliente');

INSERT INTO ARTESDORADAS_usuarios (codigo, correo_electronico, password, ultima_conexion, nombre, fecha_nacimiento, telefono, foto, tipo)
VALUES (06, 'cristinalopez87@gmail.com', 'l0293je7fd', TO_DATE('02/02/2023', 'DD-MM-YYYY'), '97crislo', TO_DATE('06/02/1987', 'DD-MM-YYYY'), 6793882, DEFAULT, 'Cliente');

INSERT INTO ARTESDORADAS_usuarios (codigo, correo_electronico, password, ultima_conexion, nombre, fecha_nacimiento, telefono, foto, tipo)
VALUES (07, 'alex568@gmail.com', 'n384jd0a3l', TO_DATE('31/01/2023', 'DD-MM-YYYY'), 'alex568', TO_DATE('21/07/2000', 'DD-MM-YYYY'), 7220992, DEFAULT, 'Cliente');

/*
** TABLA TICKETS
*/

INSERT INTO ARTESDORADAS_tickets (codigo, mensaje, asunto, estado, cliente)
VALUES (01, 'Problema al realizar la compra', 'ERROR EN LA COMPRA', 'Pendiente', 03);

INSERT INTO ARTESDORADAS_tickets (codigo, mensaje, asunto, estado, cliente)
VALUES (02, 'No recibí el pedido', 'PEDIDO PERDIDO', 'Resuelto', 05);

INSERT INTO ARTESDORADAS_tickets (codigo, mensaje, asunto, estado, cliente)
VALUES (03, 'Quiero devolver un producto', 'DEVOLUCIÓN', 'Resuelto', 02);

INSERT INTO ARTESDORADAS_tickets (codigo, mensaje, asunto, estado, cliente)
VALUES (04, 'Consulta sobre un libro', 'CONSULTA', 'Pendiente', 04);

INSERT INTO ARTESDORADAS_tickets (codigo, mensaje, asunto, estado, cliente)
VALUES (05, 'Necesito ayuda con mi cuenta', 'AYUDA CUENTA', 'Pendiente', 03);

/*
** TABLA TARJETAS
*/

INSERT INTO ARTESDORADAS_tarjetas (numero)
VALUES (40294758);

INSERT INTO ARTESDORADAS_tarjetas (numero)
VALUES (58302836);

INSERT INTO ARTESDORADAS_tarjetas (numero)
VALUES (34215834);

INSERT INTO ARTESDORADAS_tarjetas (numero)
VALUES (91820462);

INSERT INTO ARTESDORADAS_tarjetas (numero)
VALUES (71038475);

/*
** TABLA TARJETAS-USUARIOS
*/

INSERT INTO ARTESDORADAS_tarjetas_usuarios (cliente, tarjeta)
VALUES (03, 40294758);

INSERT INTO ARTESDORADAS_tarjetas_usuarios (cliente, tarjeta)
VALUES (04, 58302836);

INSERT INTO ARTESDORADAS_tarjetas_usuarios (cliente, tarjeta)
VALUES (05, 34215834);

INSERT INTO ARTESDORADAS_tarjetas_usuarios (cliente, tarjeta)
VALUES (06, 91820462);

INSERT INTO ARTESDORADAS_tarjetas_usuarios (cliente, tarjeta)
VALUES (07, 71038475);

/*
** TABLA DIRECCIONES
*/

INSERT INTO ARTESDORADAS_direcciones (cliente, codigo, tipo, cp, localidad, provincia, direccion_completa)
VALUES (03, 01, 'Avenida', 03640, 'Monóvar', 'Alicante', 'Avda. Comunidad Valenciana, 14');

INSERT INTO ARTESDORADAS_direcciones (cliente, codigo, tipo, cp, localidad, provincia, direccion_completa)
VALUES (03, 06, 'Calle', 03640, 'Monóvar', 'Alicante', 'Calle Andalucía, 04');

INSERT INTO ARTESDORADAS_direcciones (cliente, codigo, tipo, cp, localidad, provincia, direccion_completa)
VALUES (04, 02, 'Calle', 19125, 'Alcocer', 'Guadalajara', 'Calle Mayor, 20');

INSERT INTO ARTESDORADAS_direcciones (cliente, codigo, tipo, cp, localidad, provincia, direccion_completa)
VALUES (05, 03, 'Calle', 12598, 'Peñíscola', 'Castellón', 'Calle Blasco Ibáñez, 15 2C');

INSERT INTO ARTESDORADAS_direcciones (cliente, codigo, tipo, cp, localidad, provincia, direccion_completa)
VALUES (06, 04, 'Plaza', 03203, 'Elche', 'Alicante', 'Plaza España, 9 3B');

INSERT INTO ARTESDORADAS_direcciones (cliente, codigo, tipo, cp, localidad, provincia, direccion_completa)
VALUES (07, 05, 'Plaza', 14280, 'Belalcázar', 'Córdoba', 'Calle Santa Bárbara, 5');

/*
** TABLA PEDIDOS
*/

INSERT INTO ARTESDORADAS_pedidos (codigo, fecha, precio_total, facturado, cliente, cliente_envio, direccion_envio)
VALUES (01, TO_DATE ('15/01/2023', 'DD/MM/YYYY'), 25, 'Sí', 03, 03, 01);

INSERT INTO ARTESDORADAS_pedidos (codigo, fecha, precio_total, facturado, cliente, cliente_envio, direccion_envio)
VALUES (02, TO_DATE ('20/01/2023', 'DD/MM/YYYY'), 20, 'Sí', 04, 04, 02);

INSERT INTO ARTESDORADAS_pedidos (codigo, fecha, precio_total, facturado, cliente, cliente_envio, direccion_envio)
VALUES (03, TO_DATE ('21/01/2023', 'DD/MM/YYYY'), 50, 'No', 05, 05, 03);

INSERT INTO ARTESDORADAS_pedidos (codigo, fecha, precio_total, facturado, cliente, cliente_envio, direccion_envio)
VALUES (04, TO_DATE ('23/01/2023', 'DD/MM/YYYY'), 65, 'Sí', 06, 06, 04);

INSERT INTO ARTESDORADAS_pedidos (codigo, fecha, precio_total, facturado, cliente, cliente_envio, direccion_envio)
VALUES (05, TO_DATE ('26/01/2023', 'DD/MM/YYYY'), 35, 'Sí', 07, 07, 05);

INSERT INTO ARTESDORADAS_pedidos (codigo, fecha, precio_total, facturado, cliente, cliente_envio, direccion_envio)
VALUES (06, TO_DATE ('27/01/2023', 'DD/MM/YYYY'), 40, 'No', 05, 05, 03);

INSERT INTO ARTESDORADAS_pedidos (codigo, fecha, precio_total, facturado, cliente, cliente_envio, direccion_envio)
VALUES (07, TO_DATE ('28/01/2023', 'DD/MM/YYYY'), 15, 'Sí', 07, 07, 05);

/*
**  TABLA FACTURAS
*/

INSERT INTO ARTESDORADAS_facturas (codigo, pedido, fecha, cliente, direccion_factura)
VALUES (01, 01, TO_DATE ('15/01/2023', 'DD/MM/YYYY'), 03, 01);

INSERT INTO ARTESDORADAS_facturas (codigo, pedido, fecha, cliente, direccion_factura)
VALUES (02, 02, TO_DATE ('20/01/2023', 'DD/MM/YYYY'), 04, 02);

INSERT INTO ARTESDORADAS_facturas (codigo, pedido, fecha, cliente, direccion_factura)
VALUES (03, 04, TO_DATE ('23/01/2023', 'DD/MM/YYYY'), 06, 04);

INSERT INTO ARTESDORADAS_facturas (codigo, pedido, fecha, cliente, direccion_factura)
VALUES (04, 05, TO_DATE ('26/01/2023', 'DD/MM/YYYY'), 07, 05);

INSERT INTO ARTESDORADAS_facturas (codigo, pedido, fecha, cliente, direccion_factura)
VALUES (05, 07, TO_DATE ('28/01/2023', 'DD/MM/YYYY'), 07, 05);

/*
** TABLA LISTAS_DESEOS
*/

INSERT INTO ARTESDORADAS_listas_deseos (cliente, nombre)
VALUES (03, 'Lista de deseos 1');

INSERT INTO ARTESDORADAS_listas_deseos (cliente, nombre)
VALUES (04, 'Lista de deseos 2');

INSERT INTO ARTESDORADAS_listas_deseos (cliente, nombre)
VALUES (05, 'Lista de deseos 3');

INSERT INTO ARTESDORADAS_listas_deseos (cliente, nombre)
VALUES (06, 'Lista de deseos 4');

INSERT INTO ARTESDORADAS_listas_deseos (cliente, nombre)
VALUES (07, 'Lista de deseos 5');

/*
** TABLA PROVEEDORES
*/

INSERT INTO ARTESDORADAS_proveedores (codigo, telefono, direccion, nombre, tipo)
VALUES (01, 969380039, 'Calle Juan de la Cierva, S/N - Madrid', 'La Casa del Libro', 'Libros');

INSERT INTO ARTESDORADAS_proveedores (codigo, telefono, direccion, nombre, tipo)
VALUES (02, 847382910, 'Avenida de la Marina, 233 - Barcelona', 'Discogs', 'Discos');

INSERT INTO ARTESDORADAS_proveedores (codigo, telefono, direccion, nombre, tipo)
VALUES (03, 910928333, 'Calle Poeta Quintana, 36 - Alicante', 'Discos Naranja Y Negro', 'Discos');

/*
** TABLA CATEGORIAS
*/

INSERT INTO ARTESDORADAS_categorias (codigo, nombre)
VALUES (01, 'Aventura');

INSERT INTO ARTESDORADAS_categorias (codigo, nombre)
VALUES (02, 'Humor');

INSERT INTO ARTESDORADAS_categorias (codigo, nombre)
VALUES (03, 'Suspense');

INSERT INTO ARTESDORADAS_categorias (codigo, nombre)
VALUES (04, 'Thriller');

INSERT INTO ARTESDORADAS_categorias (codigo, nombre)
VALUES (05, 'Biología');

INSERT INTO ARTESDORADAS_categorias (codigo, nombre)
VALUES (06, 'Pop');

INSERT INTO ARTESDORADAS_categorias (codigo, nombre)
VALUES (07, 'Rock');

/*
** TABLA PRODUCTOS
*/

INSERT INTO ARTESDORADAS_productos (codigo, foto, nombre, descripcion, precio, unidad_medida, stock, stock_minimo, iva, proveedor, creador, modificador, fecha_creacion, fecha_ultima_modificacion)
VALUES (01, './img/cucodecristal.jpg', 'Cuco de cristal', 'Nueva York, 2017. Cora Merlo, médico residente de primer año, sufre un infarto fulminante que la obliga a un trasplante de corazón. Aún convaleciente la joven recibe la visita de una extraña mujer con una enigmática oferta: pasar unos días en Steelville, un pequeño pueblo de interior, para conocer la vida de su hijo Charles, el donante de su corazón. Cora se adentra así en un hogar lleno de secretos, en un misterio que se extiende durante veinte años y en un pueblo hermético en el que, justo el día de su llegada, desaparece un bebé en un parque público.', 20, 'cm', 15, 10, 21, 01, 01, NULL, TO_DATE ('13/02/2023', 'DD/MM/YYYY'), NULL);

INSERT INTO ARTESDORADAS_productos (codigo, foto, nombre, descripcion, precio, unidad_medida, stock, stock_minimo, iva, proveedor, creador, modificador, fecha_creacion, fecha_ultima_modificacion)
VALUES (02, './img/hijosdelafabula.jpg', 'Hijos de la fabula', 'Tras el exito de Patria, esta nueva novela de Fernando Aramburu nos arrastra, de una manera agilísima y sorprendente, por una peripecia inesperada con un desenlace magistral. Contada con un humor permanente, cáustica, veloz, escrita con frases cuya brevedad es un autentico virtuosismo, Hijos de la fábula vuelve a demostrarnos que Fernando Aramburu pertenece a la estirpe de los grandes escritores, los que nos cuentan historias como nadie es capaz de contar.', 17, 'cm', 12, 10, 21, 01, 02, NULL, TO_DATE ('14/02/2022', 'DD/MM/YYYY'), NULL);

INSERT INTO ARTESDORADAS_productos (codigo, foto, nombre, descripcion, precio, unidad_medida, stock, stock_minimo, iva, proveedor, creador, modificador, fecha_creacion, fecha_ultima_modificacion)
VALUES (03, './img/eljuegodelalma.jpg', 'El juego del alma', 'Nueva York, 2011. Una chica de quince años aparece crucificada en un suburbio a las afueras. Miren Triggs, periodista de investigación del Manhattan Press, recibe de manera inesperada un extraño sobre. En su interior, la polaroid de otra adolescente amordazada y maniatada, con una sola anotación: "GINA PEBBLES, 2002". Miren Triggs y Jim Schmoer, su antiguo profesor de periodismo, seguirán la pista de la chica de la imagen mientras investigan la crucifixión de Nueva York. Así se adentrarán en una institución religiosa en la que todo son secretos y en un enigma único lleno de suspense en el que deberán descifrar tres preguntas de respuesta imposible: ¿que le sucedió a Gina?, ¿quien envía la polaroid? y, la más importante; ¿están conectadas ambas historias?', 18, 'cm', 23, 10, 21, 01, 01, NULL, TO_DATE ('14/02/2022', 'DD/MM/YYYY'), NULL);

INSERT INTO ARTESDORADAS_productos (codigo, foto, nombre, descripcion, precio, unidad_medida, stock, stock_minimo, iva, proveedor, creador, modificador, fecha_creacion, fecha_ultima_modificacion)
VALUES (04, './img/lachicadenieve.jpg', 'La chica de nieve', 'Nueva York, 1998, cabalgata de Acción de Gracias. Kiera Templeton, desaparece entre la multitud. Tras una búsqueda frenética por toda la ciudad, alguien encuentra unos mechones de pelo junto a la ropa que llevaba puesta la pequeña. En 2003, el día que Kiera habría cumplido ocho años, sus padres, Aaron y Grace Templeton, reciben en casa un extraño paquete: una cinta VHS con la grabación de un minuto de Kiera jugando en una habitación desconocida. Javier Castillo, autor deEl día que se perdió la cordura, El día que se perdió el amor, Todo lo que sucedió con Miranda HuffyEl juego del alma, vuelve a poner en jaque la cordura conLa chica de nieve,un oscuro viaje a las profundidades de Miren Triggs, una estudiante de periodismo que inicia una investigación paralela y descubre que tanto su vida como la de Kiera están llenas de incógnitas.', 15, 'cm', 28, 10, 21, 01, 01, NULL, TO_DATE ('14/02/2022', 'DD/MM/YYYY'), NULL);

INSERT INTO ARTESDORADAS_productos (codigo, foto, nombre, descripcion, precio, unidad_medida, stock, stock_minimo, iva, proveedor, creador, modificador, fecha_creacion, fecha_ultima_modificacion)
VALUES (05, './img/astuciadeinsectos.jpg', 'Astucia de insectos', 'No les ha quedado un solo ecosistema sin conquistar. Mires hacia dónde mires, siempre habrá un insecto cerca. ¿Cuál es el hábitat más extremo sobre la faz de la Tierra que te puedas imaginar? ¡Seguro que hay algún artrópodo! Desiertos, glaciares, montañas, volcanes, profundas simas, estepas, bosques, grandes ciudades, ríos, islas e incluso en la piel de otros animales.', 10, 'cm', 28, 10, 21, 01, 02, NULL, TO_DATE ('14/02/2022', 'DD/MM/YYYY'), NULL);

INSERT INTO ARTESDORADAS_productos (codigo, foto, nombre, descripcion, precio, unidad_medida, stock, stock_minimo, iva, proveedor, creador, modificador, fecha_creacion, fecha_ultima_modificacion)
VALUES (06, './img/musicofthespheres.jpg', 'Music of the Spheres', 'Noveno álbum de estudio de la banda británica Coldplay; lanzado el 15 de octubre de 2021. El álbum fue producido por Max Martin; quien es un nuevo colaborador de la discografía de la banda además de Bill Rahko y Oscar Holter. El álbum cuenta con la participación de BTS («My Universe»); Selena Gómez («Let Somebody Go») y We Are King y Jacob Collier («Human Heart»); además del antiguo colaborador de la banda; el productor y músico Jon Hopkins. El álbum es el segundo álbum conceptual de temática espacial dela banda; después de 2005. Está ambientado en un sistema planetario ficticio llamado The Spheres; que contiene nueve planetas; tres satélites naturales; una estrella y una nebulosa. Cada uno de ellos corresponde a una canción. Según el cantante principal Chris Martin; su concepto y temas se inspiraron en la franquicia cinematográfica de Star Wars; lo que hizo que él y los otros miembros de la banda se preguntaran cómo podrían ser otros artistas en todo el universo.', 35, 'pulgadas', 15, 15, 21, 02, 02, NULL, TO_DATE ('14/02/2022', 'DD/MM/YYYY'), NULL);

INSERT INTO ARTESDORADAS_productos (codigo, foto, nombre, descripcion, precio, unidad_medida, stock, stock_minimo, iva, proveedor, creador, modificador, fecha_creacion, fecha_ultima_modificacion)
VALUES (07, './img/returntoommadawn.jpg', 'Return to Ommadawn', 'Return To Ommadawn supone el regreso de Mike Oldfield a la música que le labró un nombre a principios de los 70. Compuesto, interpretado, mezclado y producido por Mike Oldfield en el estudio de su casa en Nassau, Return To Ommadawn es un álbum centrado casi en su totalidad en el sonido acústico de cuerdas que consta de dos preciosas piezas, ‘Return To Ommadawn’. Mike lo describe como hecho a mano. Cada instrumento (22 en total, entre los que se incluyen mandolina, guitarras, contrabajo, bodhrán, tambores africanos y flauta irlandesa) está interpretado por Mike y el tiempo lo mantiene acompañándose de un metrónomo vintage. Return To Ommadawn también contiene canción Celta y cuenta, por supuesto, con la ardiente guitarra eléctrica marca de la casa de Mike.', 23, 'pulgadas', 19, 15, 21, 02, 02, NULL, TO_DATE ('14/02/2022', 'DD/MM/YYYY'), NULL);

INSERT INTO ARTESDORADAS_productos (codigo, foto, nombre, descripcion, precio, unidad_medida, stock, stock_minimo, iva, proveedor, creador, modificador, fecha_creacion, fecha_ultima_modificacion)
VALUES (08, './img/labuenasuerte.jpg', 'La buena suerte', '‘La buena suerte’ es el título del nuevo álbum de SHINOVA. Producido por Manuel Colmenero y grabado en los estudios Sonobox de Madrid, afianza el sonido de sus dos discos predecesores, ‘Volver’ y ‘Cartas de navegación’. Sin perder los rasgos característicos del quinteto, ‘La buena suerte’ muestra una evolución clara en la que pop, rock y la vanguardia más reciente, conviven bajo la textura sónica de un álbum destinado a ser imprescindible en la discografía de SHINOVA.', 20, 'pulgadas', 22, 15, 21, 03, 01, NULL, TO_DATE ('14/02/2022', 'DD/MM/YYYY'), NULL);

INSERT INTO ARTESDORADAS_productos (codigo, foto, nombre, descripcion, precio, unidad_medida, stock, stock_minimo, iva, proveedor, creador, modificador, fecha_creacion, fecha_ultima_modificacion)
VALUES (09, './img/autoterapia.jpg', 'Autoterapia', 'Nuevo CD de Izal titulado "Autoterapia". La banda indie de pop/rock madrileña publica un nuevo disco que sigue la linea de los anteriores trabajos que los han llevado a lo más alto del panorama de la música indie de nuestro pais.', 16, 'pulgadas', 20, 15, 21, 02, 01, NULL, TO_DATE ('14/02/2022', 'DD/MM/YYYY'), NULL);

INSERT INTO ARTESDORADAS_productos (codigo, foto, nombre, descripcion, precio, unidad_medida, stock, stock_minimo, iva, proveedor, creador, modificador, fecha_creacion, fecha_ultima_modificacion)
VALUES (10, './img/lalenguadelospajaros.jpg', 'La lengua de los pájaros', 'Hay quien asegura que la música es la lengua de los pájaros Itaca Band se ha inspirado en esta afirmación para titular su quinto disco, "La lengua de los br>pájaros", que pretende homenajear a la música, una disciplina que no entiende de fronteras, y el Pájaro como símbolo de libertad. Un pájaro que canta y viaja en grupo, como los músicos. Para esta nueva etapa, Itaca Band se ha rodeado de tres productores de primera línea, David Rosell, Genís Trani y Carlos Manzanares, con los que han firmado un disco que recupera su sonido de directo contundente y enérgico que hace bailar a todo el mundo, pero con una sonoridad más rock y latina y con elementos electrónicos. Para que sus seguidores lo entiendan, "la lengua de los br>pájaros" Tiene la contundente de su disco "temerario" Y los detalles de "explosiva". El resultado son doce canciones enormes con un sonido impecable que re con for tan por la serenidad que desprende. Una serenidad propia de los que han vivido mucho y de los que ya no quieren demostrar nada a nadie más que no sea ellos mismos. La formación tradicional de los Itaca -Albert Garcia a la voz, Pere Mercader los teclados, Maria astallé al trombón, Unai Eizaguirre a la guitarra, kel sangüesa al bajo y Jordi Busquets a la batería- se sabe preparada para encarar un futuro prometedor cargado de libertad y música. Y es que de eso va la vida: de levantar el vuelo una y otra vez.', 21, 'pulgadas', 18, 15, 21, 03, 02, NULL, TO_DATE ('14/02/2022', 'DD/MM/YYYY'), NULL);

/*
** TABLA LIBROS
*/

INSERT INTO ARTESDORADAS_libros (codigo, formato, editorial, autor, isbn, numero_paginas)
VALUES (01, 'Tapa blanda', 'Suma', 'Javier Castillo', 9788491293552, 488);

INSERT INTO ARTESDORADAS_libros (codigo, formato, editorial, autor, isbn, numero_paginas)
VALUES (02, 'Tapa blanda', 'Tusquets', 'F. Aramburu', 9788411072281, 320);

INSERT INTO ARTESDORADAS_libros (codigo, formato, editorial, autor, isbn, numero_paginas)
VALUES (03, 'Tapa blanda', 'DeBolsillo', 'Javier Castillo', 9788466359184, 536);

INSERT INTO ARTESDORADAS_libros (codigo, formato, editorial, autor, isbn, numero_paginas)
VALUES (04, 'Tapa blanda', 'DeBolsillo', 'Javier Castillo', 9788466357098, 512);

INSERT INTO ARTESDORADAS_libros (codigo, formato, editorial, autor, isbn, numero_paginas)
VALUES (05, 'Tapa blanda', 'Guadalmazar', 'Jairo Robla', 9788417547967, 320);

/*
** TABLA DISCOS
*/

INSERT INTO ARTESDORADAS_discos (codigo, canciones, sello, artista, tipo, asin)
VALUES (06, 'Coldplay-Music Of The Spheres, Coldplay-Higher Power, Coldplay-Humankind, Coldplay-Alien Choir, Coldplay X Selena Gomez-Let Somebody Go, Coldplay X We Are King X Jacob Collier-Human Heart, Coldplay-People Of The Pride, Coldplay-Biutyful, Coldplay-Music Of The Spheres II, Coldplay X BTS(4)-My Universe, Coldplay-Infinity Sign, Coldplay-Coloratura', 'Parlophone', 'Coldplay', 'Vinilo', 190296666964);

INSERT INTO ARTESDORADAS_discos (codigo, canciones, sello, artista, tipo, asin)
VALUES (07, 'Return To Ommadawn Pt. I, Return To Ommadawn Pt. II', 'Virgin EMI', 'Mike Oldfield', 'Vinilo', 602557256703);

INSERT INTO ARTESDORADAS_discos (codigo, canciones, sello, artista, tipo, asin)
VALUES (08, 'Puedes Apostar Por Mí, Te Debo Una Canción, La Sonrisa Intacta, Torre De Naipes, Solo Ruido, Ciudades En El Mar, Ídolos (Los Mejores Momentos Están Por Llegar), Palabras, Mi Vida Sin Mí, Gigantes, Ovnis Y Estrellas', 'Warner', 'Shinova', 'CD', 1005110602892);

INSERT INTO ARTESDORADAS_discos (codigo, canciones, sello, artista, tipo, asin)
VALUES (09, 'Autoterapia, El Pozo, Ruido Blanco, Bill Murray, Pausa, Santa Paz, Canción Para Nadie, La Increíble Historia Del Hombre Que Podía Volar Pero No Sabía Cómo, El Temblor, Temas Amables, Variables', 'Hook', 'IZAL', 'CD', 1005110606589);

INSERT INTO ARTESDORADAS_discos (codigo, canciones, sello, artista, tipo, asin)
VALUES (10, 'Todas Nuestras Luchas, Bailaremos, Apropat, Amaneceres Por Vivir Feat-Youthstar, Romper La Rueda, Poesía Feat-Dorian(2), Vengo, En Son De Guerra, Tots Els Sols, La Noche En Vela, Com Si No Hi Hagués Demà, La Lengua De Los Pájaros', 'Halley', 'Itaca Band', 'CD', 8424295366375);

/*
**  TABLA CATEGORIAS_PRODUCTOS
*/

INSERT INTO ARTESDORADAS_categorias_productos (producto, categoria)
VALUES (01, 01);

INSERT INTO ARTESDORADAS_categorias_productos (producto, categoria)
VALUES (02, 02);

INSERT INTO ARTESDORADAS_categorias_productos (producto, categoria)
VALUES (03, 03);

INSERT INTO ARTESDORADAS_categorias_productos (producto, categoria)
VALUES (04, 04);

INSERT INTO ARTESDORADAS_categorias_productos (producto, categoria)
VALUES (05, 05);

INSERT INTO ARTESDORADAS_categorias_productos (producto, categoria)
VALUES (06, 06);

INSERT INTO ARTESDORADAS_categorias_productos (producto, categoria)
VALUES (07, 07);

INSERT INTO ARTESDORADAS_categorias_productos (producto, categoria)
VALUES (08, 06);

INSERT INTO ARTESDORADAS_categorias_productos (producto, categoria)
VALUES (08, 07);

INSERT INTO ARTESDORADAS_categorias_productos (producto, categoria)
VALUES (09, 07);

INSERT INTO ARTESDORADAS_categorias_productos (producto, categoria)
VALUES (10, 07);

/*
** TABLA OPINIONES
*/

INSERT INTO ARTESDORADAS_opiniones (codigo, mensaje, fecha_publicacion, cliente, producto)
VALUES (01, 'Me encantó el producto', TO_DATE ('20/01/2023', 'DD/MM/YYYY'), 03, 01);

INSERT INTO ARTESDORADAS_opiniones (codigo, mensaje, fecha_publicacion, cliente, producto)
VALUES (02, 'Excelente calidad', TO_DATE ('22/01/2023', 'DD/MM/YYYY'), 04, 06);

INSERT INTO ARTESDORADAS_opiniones (codigo, mensaje, fecha_publicacion, cliente, producto)
VALUES (03, 'Lo recomiendo 100%', TO_DATE ('25/01/2023', 'DD/MM/YYYY'), 05, 10);

INSERT INTO ARTESDORADAS_opiniones (codigo, mensaje, fecha_publicacion, cliente, producto)
VALUES (04, 'Llegó en el tiempo esperado', TO_DATE ('27/01/2023', 'DD/MM/YYYY'), 06, 04);

INSERT INTO ARTESDORADAS_opiniones (codigo, mensaje, fecha_publicacion, cliente, producto)
VALUES (05, 'Muy interesante', TO_DATE ('29/01/2023', 'DD/MM/YYYY'), 07, 08);

/*
** TABLA PEDIDOS_PRODUCTOS
*/

INSERT INTO ARTESDORADAS_pedidos_productos (producto, pedido, cantidad, precio)
VALUES (01, 01, 1, 10);

INSERT INTO ARTESDORADAS_pedidos_productos (producto, pedido, cantidad, precio)
VALUES (06, 02, 1, 18);

INSERT INTO ARTESDORADAS_pedidos_productos (producto, pedido, cantidad, precio)
VALUES (10, 03, 1, 20);

INSERT INTO ARTESDORADAS_pedidos_productos (producto, pedido, cantidad, precio)
VALUES (04, 04, 2, 25);

INSERT INTO ARTESDORADAS_pedidos_productos (producto, pedido, cantidad, precio)
VALUES (08, 05, 1, 20);

/*
** TABLA LISTAS_DESEOS_PRODUCTOS
*/

INSERT INTO ARTESDORADAS_listas_deseos_productos (producto, nombre_lista, cliente)
VALUES (03, 'Lista de deseos 1', 03);

INSERT INTO ARTESDORADAS_listas_deseos_productos (producto, nombre_lista, cliente)
VALUES (10, 'Lista de deseos 2', 04);

INSERT INTO ARTESDORADAS_listas_deseos_productos (producto, nombre_lista, cliente)
VALUES (06, 'Lista de deseos 3', 05);

INSERT INTO ARTESDORADAS_listas_deseos_productos (producto, nombre_lista, cliente)
VALUES (02, 'Lista de deseos 4', 06);

INSERT INTO ARTESDORADAS_listas_deseos_productos (producto, nombre_lista, cliente)
VALUES (01, 'Lista de deseos 5', 07);

INSERT INTO ARTESDORADAS_listas_deseos_productos (producto, nombre_lista, cliente)
VALUES (05, 'Lista de deseos 2', 04);

INSERT INTO ARTESDORADAS_listas_deseos_productos (producto, nombre_lista, cliente)
VALUES (04, 'Lista de deseos 1', 03);