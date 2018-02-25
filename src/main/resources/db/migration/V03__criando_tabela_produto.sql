CREATE TABLE produto(
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(30) NOT NULL,
  descricao VARCHAR(60),
  valor DECIMAL(10,2) NOT NULL,
  imagem LONGTEXT NOT NULL,
  quantidade VARCHAR(10) NOT NULL,
  id_categoria BIGINT NOT NULL
);

ALTER TABLE produto ADD CONSTRAINT fk_prod_id_categoria FOREIGN KEY (id_categoria) REFERENCES categoria (id);