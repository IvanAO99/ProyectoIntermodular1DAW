-- El prefijo que he usado para las tablas es el nombre de nuestra tienda, "ARTES DORADAS"
-- Todos los atributos son "no nulos" excepto los opcionales que si podrán serlo

-- CREACIÓN DE LAS TABLAS --

CREATE TABLE ARTESDORADAS_usuarios (
    codigo NUMBER(2) PRIMARY KEY,
    correo_electronico VARCHAR2(70) NOT NULL UNIQUE,
    password VARCHAR2(20) NOT NULL,
    ultima_conexion DATE NOT NULL,
    nombre VARCHAR2(20) NOT NULL,
    fecha_nacimiento DATE,
    telefono NUMBER(10),
    foto VARCHAR2(200),
    tipo VARCHAR2(20) NOT NULL
);

--  Las tablas de administrador y cliente, se han juntado en un atributo "tipo" en la tabla usuario

/* CREATE TABLE ARTESDORADAS_administradores (
    codigo NUMBER(2) PRIMARY KEY REFERENCES ARTESDORADAS_usuarios
);

CREATE TABLE ARTESDORADAS_clientes (
    codigo NUMBER(2) PRIMARY KEY REFERENCES ARTESDORADAS_usuarios
); */

CREATE TABLE ARTESDORADAS_tickets (
    codigo NUMBER(2) PRIMARY KEY,
    mensaje VARCHAR2(4000) NOT NULL,
    asunto VARCHAR2(20) NOT NULL,
    estado VARCHAR2(20) NOT NULL,
    cliente NUMBER(2) NOT NULL REFERENCES ARTESDORADAS_usuarios ON DELETE CASCADE
);

CREATE TABLE ARTESDORADAS_tarjetas (
    numero NUMBER(10) PRIMARY KEY
);

CREATE TABLE ARTESDORADAS_tarjetas_usuarios (
    cliente NUMBER(2) REFERENCES ARTESDORADAS_usuarios ON DELETE CASCADE,
    tarjeta NUMBER(10) REFERENCES ARTESDORADAS_tarjetas ON DELETE CASCADE,
    CONSTRAINT PK_tarjetas_usuarios PRIMARY KEY (cliente, tarjeta)
);

CREATE TABLE ARTESDORADAS_direcciones (
    cliente NUMBER(2) REFERENCES ARTESDORADAS_usuarios ON DELETE CASCADE,
    codigo NUMBER(2),
    tipo VARCHAR2(20) NOT NULL,
    cp NUMBER(5) NOT NULL,
    localidad VARCHAR2(20) NOT NULL,
    provincia VARCHAR2(20) NOT NULL,
    direccion_completa VARCHAR2(80) NOT NULL,
    CONSTRAINT PK_direcciones PRIMARY KEY (cliente, codigo)
);

/*
**  Antigua tabla de PEDIDOS y FACTURAS
**
CREATE TABLE ARTESDORADAS_pedidos_facturas (
    codigo_pedido NUMBER(2) PRIMARY KEY,
    fecha_pedido DATE NOT NULL,
    precio_total NUMBER(10) NOT NULL,
    facturado VARCHAR2(2) NOT NULL,
    cliente_pedido NUMBER(2) NOT NULL,
    direccion_pedido NUMBER(10) NOT NULL,
    codigo_factura NUMBER(2) NOT NULL UNIQUE,
    fecha_facturacion DATE,
    cliente_factura NUMBER(2),
    direccion_factura NUMBER(10),
    CONSTRAINT FK_direccion_pedido FOREIGN KEY (cliente_pedido, direccion_pedido) REFERENCES ARTESDORADAS_direcciones ON DELETE CASCADE,
    CONSTRAINT FK_direccion_factura FOREIGN KEY (cliente_factura, direccion_factura) REFERENCES ARTESDORADAS_direcciones ON DELETE CASCADE
);
*/

CREATE TABLE ARTESDORADAS_pedidos (
    codigo NUMBER(2) PRIMARY KEY,
    fecha DATE NOT NULL,
    precio_total NUMBER(10) NOT NULL,
    facturado VARCHAR2(2) NOT NULL,
    cliente NUMBER(2) NOT NULL,
    direccion_envio NUMBER(2) NOT NULL,
    CONSTRAINT FK_direccion_envio FOREIGN KEY (cliente, direccion_envio) REFERENCES ARTESDORADAS_direcciones ON DELETE CASCADE
);

CREATE TABLE ARTESDORADAS_facturas (
    codigo NUMBER(2) PRIMARY KEY,
    pedido NUMBER(2) NOT NULL UNIQUE REFERENCES ARTESDORADAS_pedidos ON DELETE CASCADE,
    fecha DATE NOT NULL,
    cliente NUMBER(2) NOT NULL,
    direccion_factura NUMBER(2) NOT NULL,
    CONSTRAINT FK_direccion_factura FOREIGN KEY (cliente, direccion_factura) REFERENCES ARTESDORADAS_direcciones ON DELETE CASCADE
);

CREATE TABLE ARTESDORADAS_pedidos_clientes (
    cliente NUMBER(2) REFERENCES ARTESDORADAS_usuarios ON DELETE CASCADE,
    pedido NUMBER(2) REFERENCES ARTESDORADAS_pedidos ON DELETE CASCADE,
    CONSTRAINT PK_pedidos_clientes PRIMARY KEY (cliente, pedido)
);

CREATE TABLE ARTESDORADAS_listas_deseos (
    cliente NUMBER(2) REFERENCES ARTESDORADAS_usuarios ON DELETE CASCADE,
    nombre VARCHAR2(20),
    CONSTRAINT PK_listas_deseos PRIMARY KEY (cliente, nombre)
);

CREATE TABLE ARTESDORADAS_opiniones (
    codigo NUMBER(2) PRIMARY KEY,
    mensaje VARCHAR2(4000) NOT NULL,
    fecha_publicacion DATE NOT NULL,
    cliente NUMBER(2) NOT NULL REFERENCES ARTESDORADAS_usuarios ON DELETE CASCADE
);

CREATE TABLE ARTESDORADAS_proveedores (
    codigo NUMBER(2) PRIMARY KEY,
    telefono NUMBER(10) NOT NULL,
    direccion VARCHAR2(100) NOT NULL,
    nombre VARCHAR2(50) NOT NULL,
    tipo VARCHAR2(20) NOT NULL
);

/*
**  Antigua tabla de PRODUCTOS y CATEGORIAS
**
CREATE TABLE ARTESDORADAS_producto_buscar_categoria (
    codigo_producto NUMBER(2) PRIMARY KEY,
    foto VARCHAR2(20) NOT NULL,
    nombre VARCHAR2(20) NOT NULL,
    descripcion VARCHAR2(4000) NOT NULL,
    precio NUMBER(2) NOT NULL,
    unidad_medida VARCHAR2(20) NOT NULL,
    stock NUMBER(3) NOT NULL,
    stock_minimo NUMBER(3) NOT NULL,
    iva NUMBER(2) NOT NULL,
    codigo_proveedor NUMBER(2) NOT NULL REFERENCES ARTESDORADAS_proveedores ON DELETE CASCADE,,
    codigo_categoria NUMBER(2) NOT NULL UNIQUE,
    nombre_categoria VARCHAR2(20) NOT NULL
    creador NUMBER(2) NOT NULL REFERENCES ARTESDORADAS_usuarios ON DELETE CASCADE,
    modificador NUMBER(2) REFERENCES ARTESDORADAS_usuarios ON DELETE CASCADE,
    fecha_creacion DATE,
    fecha_ultima_modificacion DATE
);
*/

CREATE TABLE ARTESDORADAS_productos (
    codigo NUMBER(2) PRIMARY KEY,
    foto VARCHAR2(50) NOT NULL,
    nombre VARCHAR2(50) NOT NULL,
    descripcion VARCHAR2(4000) NOT NULL,
    precio NUMBER(2) NOT NULL,
    unidad_medida VARCHAR2(20) NOT NULL,
    stock NUMBER(3) NOT NULL,
    stock_minimo NUMBER(3) NOT NULL,
    iva NUMBER(2) NOT NULL,
    proveedor NUMBER(2) NOT NULL REFERENCES ARTESDORADAS_proveedores ON DELETE CASCADE,
    creador NUMBER(2) NOT NULL REFERENCES ARTESDORADAS_usuarios ON DELETE CASCADE,
    modificador NUMBER(2) REFERENCES ARTESDORADAS_usuarios ON DELETE CASCADE,
    fecha_creacion DATE,
    fecha_ultima_modificacion DATE
);

ALTER TABLE ARTESDORADAS_opiniones ADD (producto NUMBER(2) NOT NULL REFERENCES ARTESDORADAS_productos ON DELETE CASCADE);

CREATE TABLE ARTESDORADAS_libros (
    codigo NUMBER(2) PRIMARY KEY REFERENCES ARTESDORADAS_productos ON DELETE CASCADE,
    formato VARCHAR2(20) NOT NULL,
    editorial VARCHAR2(20) NOT NULL,
    autor VARCHAR2(20) NOT NULL,
    genero VARCHAR2(20) NOT NULL,
    isbn NUMBER(20) NOT NULL,
    numero_paginas VARCHAR2(20) NOT NULL
);

CREATE TABLE ARTESDORADAS_discos (
    codigo NUMBER(2) PRIMARY KEY REFERENCES ARTESDORADAS_productos ON DELETE CASCADE,
    canciones VARCHAR2(4000) NOT NULL,
    sello VARCHAR2(20) NOT NULL,
    genero VARCHAR2(20) NOT NULL,
    artista VARCHAR2(20) NOT NULL,
    tipo VARCHAR2(20) NOT NULL,
    asin NUMBER(20) NOT NULL
);

CREATE TABLE ARTESDORADAS_categorias (
    codigo NUMBER(2) PRIMARY KEY,
    nombre VARCHAR2(20) NOT NULL
);

/*
**  Antigua tabla de SUBCATEGORIAS
**
CREATE TABLE ARTESDORADAS_subcategorias (
    codigo NUMBER(2) PRIMARY KEY,
    nombre VARCHAR2(20) NOT NULL,
    categoria NUMBER(2) NOT NULL REFERENCES ARTESDORADAS_categorias ON DELETE CASCADE
);
*/

CREATE TABLE ARTESDORADAS_categorias_productos (
    producto NUMBER(2) REFERENCES ARTESDORADAS_productos,
    categoria NUMBER(2) REFERENCES ARTESDORADAS_categorias,
    CONSTRAINT PK_categorias_productos PRIMARY KEY (producto, categoria)
);

CREATE TABLE ARTESDORADAS_pedidos_productos (
    producto NUMBER(2) REFERENCES ARTESDORADAS_productos ON DELETE CASCADE,
    pedido NUMBER(2) REFERENCES ARTESDORADAS_pedidos ON DELETE CASCADE,
    cantidad NUMBER(2) NOT NULL,
    precio NUMBER(3) NOT NULL,
    CONSTRAINT PK_pedidos_productos PRIMARY KEY (producto, pedido)
);

CREATE TABLE ARTESDORADAS_listas_deseos_productos (
    producto NUMBER(2) REFERENCES ARTESDORADAS_productos ON DELETE CASCADE,
    nombre_lista VARCHAR2(20),
    cliente NUMBER(2),
    CONSTRAINT FK_listas_deseos_productos FOREIGN KEY (cliente, nombre_lista) REFERENCES ARTESDORADAS_listas_deseos ON DELETE CASCADE,
    CONSTRAINT PK_listas_deseos_productos PRIMARY KEY (producto, nombre_lista, cliente)
);

-- CHECKS --

-- Solamente tenemos dos tipos de usuarios, "Administrador" y "Cliente"
ALTER TABLE ARTESDORADAS_usuarios ADD (CONSTRAINT CK_validar_tipo_cliente CHECK (tipo = 'Administrador' OR tipo = 'Cliente'));

-- Queremos validar que todos los codigos sean numeros positivos, por lo tanto necesitamos una restricción que compruebe si el codigo es mayor que cero
-- Un codigo no puede ser "00", los codigos empezarán por "01"
ALTER TABLE ARTESDORADAS_usuarios ADD (CONSTRAINT CK_validar_codigo_usuario CHECK (codigo > 0));
ALTER TABLE ARTESDORADAS_tickets ADD (CONSTRAINT CK_validar_codigo_ticket CHECK (codigo > 0));
ALTER TABLE ARTESDORADAS_direcciones ADD (CONSTRAINT CK_validar_codigo_direccion CHECK (codigo > 0));
ALTER TABLE ARTESDORADAS_pedidos ADD (CONSTRAINT CK_validar_codigo_pedido CHECK (codigo > 0));
ALTER TABLE ARTESDORADAS_facturas ADD (CONSTRAINT CK_validar_codigo_factura CHECK (codigo > 0));
ALTER TABLE ARTESDORADAS_opiniones ADD (CONSTRAINT CK_validar_codigo_opinion CHECK (codigo > 0));
ALTER TABLE ARTESDORADAS_proveedores ADD (CONSTRAINT CK_validar_codigo_proveedor CHECK (codigo > 0));
ALTER TABLE ARTESDORADAS_productos ADD (CONSTRAINT CK_validar_codigo_producto CHECK (codigo > 0));
ALTER TABLE ARTESDORADAS_categorias ADD (CONSTRAINT CK_validar_codigo_categoria CHECK (codigo > 0));

--  El alter de subcategorias ha quedado inservible por los cambios realizados
--  ALTER TABLE ARTESDORADAS_subcategorias ADD (CONSTRAINT CK_validar_codigo_subcategoria CHECK (codigo > 0));

-- La cantidad de los produtos no pueden ser "0" o negativa.
ALTER TABLE ARTESDORADAS_pedidos_productos ADD (CONSTRAINT CK_validar_cantidad_pedido CHECK (cantidad > 0));

-- Los diferentes precios de los podructos/pedidos, no pueden ser "0" o números negativos
ALTER TABLE ARTESDORADAS_pedidos ADD (CONSTRAINT CK_validar_precio_total CHECK (precio_total > 0));
ALTER TABLE ARTESDORADAS_productos ADD (CONSTRAINT CK_validar_precio_producto CHECK (precio > 0));
ALTER TABLE ARTESDORADAS_pedidos_productos ADD (CONSTRAINT CK_validar_precio_pedido CHECK (precio > 0));

--  Las tablas de pedidos y facturas ahora están separadas por las modificaciones, este alter ha quedado inservible
-- La fecha de facturación no puede ser anterior a la fecha de pedido.
--  ALTER TABLE ARTESDORADAS_pedidos_facturas ADD (CONSTRAINT CK_validar_fecha_facturacion CHECK (fecha_facturacion >= fecha_pedido));

-- Los tickets solo podrán tener dos estados, "pendiente" o "resuelto"
ALTER TABLE ARTESDORADAS_tickets ADD (CONSTRAINT CK_validar_estado CHECK (estado = 'Pendiente' OR estado = 'Resuelto'));

--  Hemos vuelto ha analizar la lógica, y el tipo de dirección ha sido cambiado, no necesitamos este check
-- Solo pordrá haber dos tipos de dirección, de "pedido" o de "factura"
--  ALTER TABLE ARTESDORADAS_direcciones ADD (CONSTRAINT CK_validar_tipo_direccion CHECK (tipo = 'Pedido' OR tipo = 'Factura'));

-- Solo puede haber dos casos en facturado: "SI" o "NO"
ALTER TABLE ARTESDORADAS_pedidos ADD (CONSTRAINT CK_validar_facturado CHECK (facturado = 'Sí' OR facturado = 'No'));

-- Solo puede haber dos tipos de proveedores: "DISCOS" o "LIBROS"
ALTER TABLE ARTESDORADAS_proveedores ADD (CONSTRAINT CK_validar_tipo_proveedor CHECK (tipo = 'Discos' OR tipo = 'Libros'));

-- BORRADO DE LAS TABLAS --

--  Algunos drops ya no funcionan por los cambios realizados, como ADMINISTRADOR y CLIENTE
/*
DROP TABLE ARTESDORADAS_listas_deseos_productos CASCADE CONSTRAINTS;
DROP TABLE ARTESDORADAS_pedidos_productos CASCADE CONSTRAINTS;

DROP TABLE ARTESDORADAS_subcategorias CASCADE CONSTRAINTS;

DROP TABLE ARTESDORADAS_categorias_productos CASCADE CONSTRAINTS;
DROP TABLE ARTESDORADAS_categorias CASCADE CONSTRAINTS;
DROP TABLE ARTESDORADAS_discos CASCADE CONSTRAINTS;
DROP TABLE ARTESDORADAS_libros CASCADE CONSTRAINTS;
DROP TABLE ARTESDORADAS_productos CASCADE CONSTRAINTS;
DROP TABLE ARTESDORADAS_proveedores CASCADE CONSTRAINTS;
DROP TABLE ARTESDORADAS_opiniones CASCADE CONSTRAINTS;
DROP TABLE ARTESDORADAS_listas_deseos CASCADE CONSTRAINTS;
DROP TABLE ARTESDORADAS_pedidos_clientes CASCADE CONSTRAINTS;
DROP TABLE ARTESDORADAS_facturas CASCADE CONSTRAINTS;
DROP TABLE ARTESDORADAS_pedidos CASCADE CONSTRAINTS;

DROP TABLE ARTESDORADAS_pedidos_facturas CASCADE CONSTRAINTS;

DROP TABLE ARTESDORADAS_direcciones CASCADE CONSTRAINTS;
DROP TABLE ARTESDORADAS_tarjetas_usuarios CASCADE CONSTRAINTS;
DROP TABLE ARTESDORADAS_tarjetas CASCADE CONSTRAINTS;
DROP TABLE ARTESDORADAS_tickets CASCADE CONSTRAINTS;

DROP TABLE ARTESDORADAS_cliente CASCADE CONSTRAINTS;
DROP TABLE ARTESDORADAS_administrador CASCADE CONSTRAINTS;

DROP TABLE ARTESDORADAS_usuarios CASCADE CONSTRAINTS;
*/