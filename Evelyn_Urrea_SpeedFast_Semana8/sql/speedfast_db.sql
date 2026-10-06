CREATE DATABASE IF NOT EXISTS speedfast_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE speedfast_db;

CREATE TABLE IF NOT EXISTS repartidores (
  id_repartidor INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS pedidos (
  id_pedido INT AUTO_INCREMENT PRIMARY KEY,
  direccion VARCHAR(200) NOT NULL,
  tipo ENUM('COMIDA','ENCOMIENDA','EXPRESS') NOT NULL,
  estado ENUM('PENDIENTE','EN_REPARTO','ENTREGADO') NOT NULL DEFAULT 'PENDIENTE'
);

CREATE TABLE IF NOT EXISTS entregas (
  id_entrega INT AUTO_INCREMENT PRIMARY KEY,
  id_pedido INT NOT NULL,
  id_repartidor INT NOT NULL,
  fecha_hora DATETIME NOT NULL,
  CONSTRAINT fk_entrega_pedido FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido),
  CONSTRAINT fk_entrega_repartidor FOREIGN KEY (id_repartidor) REFERENCES repartidores(id_repartidor)
);
