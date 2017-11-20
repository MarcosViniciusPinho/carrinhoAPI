CREATE TABLE produto_carrinho(
  id_carrinho BIGINT NOT NULL,
  quantidade INTEGER NOT NULL,
  id_produto BIGINT NOT NULL
);

ALTER TABLE produto_carrinho ADD CONSTRAINT fk_prca_id_produto FOREIGN KEY (id_produto) REFERENCES produto (id);

ALTER TABLE produto_carrinho ADD CONSTRAINT fk_prca_id_carrinho FOREIGN KEY (id_carrinho) REFERENCES carrinho (id);