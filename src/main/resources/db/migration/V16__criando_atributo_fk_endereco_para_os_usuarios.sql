ALTER TABLE usuario ADD COLUMN id_endereco BIGINT;
ALTER TABLE usuario ADD CONSTRAINT fk_prod_id_endereco FOREIGN KEY (id_endereco) REFERENCES endereco (id);