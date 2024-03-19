USE HOMESERVICE;

/*Insertar datos en la tabla usuarios*/
INSERT INTO usuarios (email, contrasenia, avatar)
VALUES ('usuario1@example.com', 'contraseña1', NULL),
       ('usuario2@example.com', 'contraseña2', NULL);
       
INSERT INTO usuarios (email, contrasenia, avatar)
VALUES ('usuario3@example.com', 'contraseña3', NULL),
       ('usuario4@example.com', 'contraseña4', NULL);
       
/*Insertar datos en la tabla direcciones*/
INSERT INTO direcciones (calle, numero, provincia, localidad)
VALUES ('Calle principal', '123', 'Provincia', 'Localidad');

/*Insertar datos en la tabla clientes*/
INSERT INTO clientes (nombre, apellido, telefono, dni, cliente_usuario_id, cliente_direccion_id)
VALUES ('Mario', 'Apellido1', '123456789', '12345678', 21, 2),
       ('Juan', 'Apellido2', '987654321', '87654321', 22, 2);
       SELECT * FROM homeservice.clientes;

/* Insertar datos en la tabla profesionales*/
INSERT INTO profesionales (nombre, apellido, telefono, cuit, profesional_usuario_id, PROFESION, profesional_direccion_id, cbu)
VALUES ('Miguel', 'Apellido3', '123456789', '12345678901', 23, 'PLUMBER', 2, '1234567890123456789012'),
       ('Angel', 'Apellido4', '987654321', '10987654321', 24, 'ELECTRICIAN', 2, '2109876543210987654321');
       SELECT * FROM homeservice.profesionales;

/* Insertar datos en la tabla productos*/
INSERT INTO productos (nombre, precio, descripcion, cantidad, foto, profesionales_id)
VALUES ('Cable', 10.00, 'Descripción del producto 1', 2, NULL, 1),
       ('tubos', 20.00, 'Descripción del producto 2', 3, NULL, 1),
       ('Sierra', 30.00, 'Descripción del producto 3', 4, NULL, 2);
SELECT * FROM homeservice.productos;

-- Insertar una compra y calcular el total
INSERT INTO compras (fecha, clientes_id)
VALUES (CURRENT_DATE, 7);

INSERT INTO compras (fecha, clientes_id)
VALUES (CURRENT_DATE, 6);
SELECT * FROM homeservice.compras;


/* Obtener el ID de la compra recién insertada*/
SET @compra_id = LAST_INSERT_ID();

/*Insertar los detalles de la compra*/
INSERT INTO detalles_productos_compras (productos_id, compras_id, cantidad, descuento)
VALUES (1, @compra_id, 2, 0.00),
       (1, @compra_id, 2, 0.00),
       (2, @compra_id, 2, 0.00);

INSERT INTO detalles_productos_compras (productos_id, compras_id, cantidad, descuento)
VALUES (1, @compra_id, 1, 0.00),
       (2, @compra_id, 1, 0.00),
       (3, @compra_id, 1, 0.00);
SELECT * FROM homeservice.detalles_productos_compras;

/*Calcular total de la compra aplicando descuento total*/
UPDATE compras
SET total = (SELECT SUM(detalles_productos_compras.cantidad * (productos.precio - detalles_productos_compras.descuento)) 
             FROM detalles_productos_compras
             JOIN productos ON detalles_productos_compras.productos_id = productos.id
             WHERE detalles_productos_compras.compras_id = @compra_id)
WHERE id = @compra_id;

/*consultar las compras de un cliente*/
SELECT *
FROM compras
WHERE clientes_id = 7;


SELECT compras.id AS compra_id, 
       compras.fecha, 
       clientes.nombre AS cliente_nombre, 
       clientes.apellido AS cliente_apellido,
       productos.nombre AS producto_nombre, 
       detalles_productos_compras.cantidad, 
       detalles_productos_compras.descuento,
       (detalles_productos_compras.cantidad * (productos.precio - detalles_productos_compras.descuento)) AS subtotal
FROM compras
JOIN clientes ON compras.clientes_id = clientes.id
JOIN detalles_productos_compras ON compras.id = detalles_productos_compras.compras_id
JOIN productos ON detalles_productos_compras.productos_id = productos.id
WHERE compras.id = @compra_id;

SELECT 
    compras.id AS compra_id,
    compras.fecha,
    clientes.nombre AS cliente_nombre,
    clientes.apellido AS cliente_apellido,
    GROUP_CONCAT(productos.nombre SEPARATOR ', ') AS productos_comprados,
    GROUP_CONCAT(detalles_productos_compras.cantidad SEPARATOR ', ') AS cantidades,
    GROUP_CONCAT(productos.precio SEPARATOR ', ') AS precios_unitarios,
    GROUP_CONCAT(detalles_productos_compras.descuento SEPARATOR ', ') AS descuentos,
    SUM(detalles_productos_compras.cantidad * (productos.precio - detalles_productos_compras.descuento)) AS total
FROM compras
JOIN clientes ON compras.clientes_id = clientes.id
JOIN detalles_productos_compras ON compras.id = detalles_productos_compras.compras_id
JOIN productos ON detalles_productos_compras.productos_id = productos.id
WHERE compras.id = @compra_id
GROUP BY compras.id, compras.fecha, clientes.nombre, clientes.apellido;

