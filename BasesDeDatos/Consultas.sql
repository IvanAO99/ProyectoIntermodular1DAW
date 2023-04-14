/*
 *  3 consultas de la sintaxis select básica (pŕactica 5.1)
*/

--  Seleccionar los datos de un USUARIO del que sabemos su CORREO
SELECT * 
FROM ARTESDORADAS_usuarios
WHERE correo_electronico LIKE 'cristinalopez87@gmail.com';

--  Seleccionar los PRODUCTOS que han sido modificados
SELECT * 
FROM ARTESDORADAS_productos
WHERE modificador IS NOT NULL AND fecha_ultima_modificacion IS NOT NULL;

--  Seleccionar los PEDIDOS que han sido facturados
SELECT * 
FROM ARTESDORADAS_pedidos 
WHERE facturado LIKE 'Sí';

/*
 *  3 consultas usando funciones SQL (práctica 5.2)
*/

-- Edad de los USUARIOS
SELECT nombre "Usuario", TRUNC(MONTHS_BETWEEN(SYSDATE, fecha_nacimiento) / 12) "Edad" 
FROM ARTESDORADAS_usuarios
ORDER BY 2 DESC, 1;

--  Precio medio de los PRODUCTOS, teniendo en cuenta el IVA. Redondeado a dos decimales
SELECT ROUND(AVG(precio + ((iva / 100) * precio)), 2) "Precio medio" 
FROM ARTESDORADAS_productos;

-- Seleccionar el producto con menor STOCK y su STOCK MÍNIMO
SELECT nombre "Producto", stock "Stock actual", stock_minimo "Stock mínimo"
FROM ARTESDORADAS_productos
WHERE stock = (SELECT MIN(stock) FROM ARTESDORADAS_productos);

/*
 *  3 consultas usando Agrupaciones, combinaciones u operaciones de conjuntos (práctica 5.3)
*/

-- Queremos relacionar los PRODUCTOS con los LIBROS y los DISCOS
SELECT *
FROM ARTESDORADAS_productos p
LEFT JOIN ARTESDORADAS_libros l ON p.codigo = l.codigo
LEFT JOIN ARTESDORADAS_discos d ON p.codigo = d.codigo;

-- PRODUCTOS y las veces que han sido comprados
SELECT produ.nombre "Producto", COUNT(pedido.producto) "Veces comprado"
FROM ARTESDORADAS_productos produ
LEFT JOIN ARTESDORADAS_pedidos_productos pedido ON produ.codigo = pedido.producto
GROUP BY produ.nombre
ORDER BY COUNT(pedido.producto) DESC;

-- Queremos saber que tarjetas y que direcciones pertenecen a que usuario
SELECT usu.nombre "Usuario", tarjeta.tarjeta "Nº de tarjeta", dir.direccion_completa "Dirección"
FROM ARTESDORADAS_usuarios usu
LEFT JOIN ARTESDORADAS_tarjetas_usuarios tarjeta ON usu.codigo = tarjeta.cliente
LEFT JOIN ARTESDORADAS_direcciones dir ON usu.codigo = dir.cliente;

/*
 *  1 vista, un insert y un borrado o actualización desde select, en total, serán tres sentencias (práctica 5.4)
*/

--  Esta vista nos puede servir para la pagina principal de los productos, en donde se muestra
--  la foto, el nombre y el precio
CREATE OR REPLACE VIEW catalogo AS
SELECT producto.foto "Foto", producto.nombre "Nombre", producto.precio "Precio"
FROM ARTESDORADAS_productos producto;

SELECT * FROM catalogo;

-- Te hemos insertado como CLIENTE usando la tabla de CICLOS_PROFESOR
INSERT INTO ARTESDORADAS_usuarios (codigo, correo_electronico, password, ultima_conexion, nombre, fecha_nacimiento, telefono, foto, tipo)
SELECT 8, email_profesor, 'contraseña', SYSDATE, LOWER(REPLACE(nombre_profesor, ' ', '')), fecha_nacimiento_profesor, telefono_profesor, null, 'Cliente'
FROM CICLOS_profesor
WHERE LOWER(nombre_profesor) LIKE 'silvia%';

--  El IVA de los LIBROS a subido a 22, asi que tenemos que actualizarlo en todos los productos que sean LIBROS
UPDATE ARTESDORADAS_productos
SET iva = 22
WHERE codigo IN(
    SELECT p.codigo
    FROM ARTESDORADAS_productos p
    LEFT JOIN ARTESDORADAS_libros l on p.codigo = l.codigo
    WHERE l.codigo IS NOT NULL
);