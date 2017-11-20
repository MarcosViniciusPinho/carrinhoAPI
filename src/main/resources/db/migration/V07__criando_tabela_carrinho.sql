CREATE TABLE carrinho(
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  data_compra DATETIME NOT NULL,
  id_usuario BIGINT NOT NULL
);

ALTER TABLE carrinho ADD CONSTRAINT fk_carr_id_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id);