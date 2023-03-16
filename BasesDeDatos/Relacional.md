# RELACIONAL

## TABLAS

USUARIOS (codigo, correo_electronico, password, ultima_conexion, nombre, fecha_nacimiento*, telefono*, foto*)

- PK: codigo
- UK: correo_electronico

ADMINISTRADORES (código)

- PK: codigo
- FK: codigo → USUARIO.codigo

CLIENTES (codigo)

- PK: codigo
- FK: codigo → USUARIO.codigo

TICKETS (codigo, mensaje, asunto, estado, cliente)

- PK: codigo
- FK: cliente → CLIENTE.codigo
- NN (cliente)

TARJETAS (numero)

- PK: numero

TARJETAS_USUARIOS (cliente, tarjeta)

- PK: cliente, tarjeta
- FK: cliente → CLIENTES.codigo
- FK: tarjeta → TARJETAS.número

DIRECCIONES (cliente, codigo, tipo, cp, localidad, provincia, direccion_completa)

- PK: cliente, codigo
- FK: cliente → CLIENTES.codigo

PEDIDOS (codigo, fecha, precio_total, facturado, cliente, cliente_envio, direccion_envio)

- PK: codigo
- FK: cliente → CLIENTES.codigo
- NN (cliente)
- FK: cliente_envio, direccion_envio → DIRECCIONES. cliente, codigo
- NN (cliente_envio, direccion_envio)

FACTURAS (codigo, fecha, cliente, direccion_factura, pedido)

- PK: codigo
- UK: pedido
- FK: cliente, direccion_factura → DIRECCIONES. cliente, codigo
- NN (cliente, direccion_factura)
- FK: pedido → PEDIDOS.codigo
- NN (pedido)

LISTAS_DESEOS (cliente, nombre)

- PK: cliente, nombre
- FK: cliente → CLIENTES.codigo

OPINIONES (codigo, mensaje, fecha_publicacion, cliente, producto)

- PK: codigo
- FK: cliente → CLIENTES.codigo
- NN (cliente)
- FK: producto → PRODUCTOS.codigo
- NN (producto)

PROVEEDORES (codigo, telefono, direccion, nombre, tipo)

- PK: codigo

PRODUCTOS (codigo, foto, nombre, descripcion, precio, unidad_medida, stock_minimo, stock, iva, proveedor, modificador*, creador, fecha_creacion, fecha_ultima_modificacion*)

- PK: codigo
- FK: proveedor → PROVEEDORES.codigo
- NN (proveedor)
- FK: modificador → ADMINISTRADORES.codigo
- FK: creador → ADMINISTRADORES.codigo
- NN (creador)

LIBROS (codigo, formato, editorial, autor, genero, isbn, numero_paginas)

- PK: codigo
- FK: codigo → PRODUCTOS.codigo

DISCOS (codigo, canciones, sello, genero, artista, tipo, asin)

- PK: codigo
- FK: codigo → PRODUCTOS.codigo

CATEGORIAS (codigo, nombre)

- PK: codigo

CATEGORIAS_PRODUCTOS (categoria,  producto)

- PK: categoria, producto
- FK: categoria → CATEGORIAS.codigo
- FK: producto → PRODUCTOS.codigo

PEDIDOS_PRODUCTOS (producto, pedido, cantidad, precio)

- PK: producto, pedido
- FK: producto → PRODUCTOS.codigo
- FK: pedido → PEDIDOS.codigo

LISTAS_DESEOS_PRODUCTOS (producto, nombre_lista, cliente)

- PK: producto, nombre_lista, cliente
- FK: producto → PRODUCTOS.codigo
- FK: nombre_lista, cliente → LISTAS_DESEOS.cliente, nombre

## PÉRDIDAS SEMÁNTICAS

- Generalización →  solamente hay pérdida si una generalización es total o exclusiva.

  - La generalización de *USUARIO* es tanto total como exclusiva. Se pierden tanto la totalidad como la exclusividad puesto que no podemos plasmarlas en el modelo relacional. La generalización de PRODUCTO es exclusiva. Se pierde la exclusividad ya que no se puede plasmar en el modelo relacional.

- (1,n) →  Se pierde el uno, no se puede representar. Ya que en el relacional sólo podemos captar la existencia mínima de ese producto en relaciones con restricción de  existencia.
  
  - Se pierde el 1 del (1,n) de PRODUCTOS-PROVEEDORES.
  
  - Se pierde el 1 del (1,n) de PRODUCTOS-LISTAS_DESEOS.

  - Se pierde el 1 del (1,n) de PRODUCTOS-PEDIDOS.

  - Se pierde el 1 del (1,n) de CLIENTES-PEDIDOS.

- 1:N o 1:1 →  Si hay atributo en la relación, no se puede saber su origen. En las relaciones 1:N ADMINISTRADOR-MODIFICAR-PRODUCTO y ADMINISTRADOR-CREAR-PRODUCTO, se pierde el origen de los atributos “Fecha de creación” y “Fecha de última modificación” porque no hacemos una tabla de la relación.
