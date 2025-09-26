
-- Tabla tipodocumento
CREATE TABLE tipodocumento (
  id SERIAL PRIMARY KEY,
  tipo VARCHAR(50) NOT NULL
);

INSERT INTO tipodocumento (id, tipo) VALUES
  (5, 'CEDULA'),
  (6, 'TARJETA'),
  (7, 'PASAPORTE');

-- Tabla cliente
CREATE TABLE cliente (
  id SERIAL PRIMARY KEY,
  idtipodocumento INTEGER NOT NULL,
  numerodocumento VARCHAR(30) NOT NULL,
  direccion VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  nombre VARCHAR(200) NOT NULL,
  telefono VARCHAR(20) NOT NULL,
  CONSTRAINT fk_tipodocumento_cliente FOREIGN KEY (idtipodocumento) REFERENCES tipodocumento(id)
);

-- Tabla proveedor
CREATE TABLE proveedor (
  id SERIAL PRIMARY KEY,
  ciudad VARCHAR(255),
  direccion VARCHAR(255),
  nombre VARCHAR(255),
  telefono VARCHAR(15),
  nit VARCHAR(100)
);

INSERT INTO proveedor (id, ciudad, direccion, nombre, telefono, nit) VALUES
  (2, 'Bogotá', 'Carrera 123 # 12 - 80', 'Colgate', '312456123', '890123122'),
  (4, 'Bogotá', 'Avenida 7 # 12 - 80', 'Carvajal S.A', '23467811', '890123564');

-- Tabla usuario
CREATE TABLE usuario (
  id SERIAL PRIMARY KEY,
  idtipodocumento INTEGER NOT NULL,
  numerodocumento VARCHAR(40) NOT NULL,
  email VARCHAR(255) NOT NULL,
  nombre VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  nombreusuario VARCHAR(255) NOT NULL,
  CONSTRAINT fk_tipodocumento_usuario FOREIGN KEY (idtipodocumento) REFERENCES tipodocumento(id)
);

INSERT INTO usuario (id, idtipodocumento, numerodocumento, email, nombre, password, nombreusuario) VALUES
  (1, 5, '63124561', 'pedro@lafloresta.com', 'Pedro Alonso Paquetiva', '123', 'admin'),
  (2, 5, '9123455', 'pepe@gmail.com', 'Pepe', '123456', 'cliente'),
  (4, 6, '890123445', 'palonso@floresta.edu.co', 'Pedro Alonso Paquetiva', 'pepito', 'pepe'),
  (9, 5, '91239999', 'JAnono@uis.edu.co', 'Jorge Arturo Cifuentes Velez', 'nono123456', 'elnono'),
  (10, 5, '91234190', 'llopez@gmail.com', 'Luis Pedro López', '123456', 'llopez'),
  (11, 5, '63124561', 'Sninio@uis.edu.co', 'Sandra Milena NIño', 'ssndra123', 'sninio'),
  (12, 5, '1098123451', 'Falarcon@gmail.com', 'Fernando José Alarcón Suarez', '12345', 'FAlarcon'),
  (13, 6, '65081136415', 'lcalderon@gmail.com', 'Luis Fernando Calderón', '12311', 'lcalderon'),
  (14, 5, '90213778', 'jsuarez@gmail.com', 'Jorge Ernesto Suarez', '123456', 'jsuarez');

-- Tabla producto
CREATE TABLE producto (
  id SERIAL PRIMARY KEY,
  idproveedor INTEGER NOT NULL,
  ivacompra double precision NOT NULL,
  nombre VARCHAR(255) NOT NULL,
  preciocompra double precision NOT NULL,
  precioventa double precision NOT NULL,
  CONSTRAINT fk_proveedor_producto FOREIGN KEY (idproveedor) REFERENCES proveedor(id)
);

-- Tabla venta
CREATE TABLE venta (
  id SERIAL PRIMARY KEY,
  idcliente INTEGER NOT NULL,
  idusuario INTEGER NOT NULL,
  ivaventa double precision NOT NULL,
  totalventa double precision NOT NULL,
  valorventa double precision NOT NULL,
  CONSTRAINT fk_cliente_venta FOREIGN KEY (idcliente) REFERENCES cliente(id),
  CONSTRAINT fk_usuario_venta FOREIGN KEY (idusuario) REFERENCES usuario(id)
);

-- Tabla detalleventa
CREATE TABLE detalleventa (
  id SERIAL PRIMARY KEY,
  idproducto INTEGER NOT NULL,
  idventa INTEGER NOT NULL,
  cantidadproducto INTEGER NOT NULL,
  valortotal double precision NOT NULL,
  valorventa double precision NOT NULL,
  valoriva double precision NOT NULL,
  CONSTRAINT fk_producto_detalle FOREIGN KEY (idproducto) REFERENCES producto(id),
  CONSTRAINT fk_venta_detalle FOREIGN KEY (idventa) REFERENCES venta(id)
);
