ALTER TABLE carrinho ADD COLUMN id_endereco BIGINT;
ALTER TABLE carrinho ADD CONSTRAINT fk_prod_id_endereco FOREIGN KEY (id_endereco) REFERENCES endereco (id);