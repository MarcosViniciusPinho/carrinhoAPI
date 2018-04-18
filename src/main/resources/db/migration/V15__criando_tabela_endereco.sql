CREATE TABLE endereco(
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  cep VARCHAR(10),
  logradouro VARCHAR(100) NOT NULL,
  complemento VARCHAR(20),
  bairro VARCHAR(40) NOT NULL,
  municipio VARCHAR(70) NOT NULL,
  estado VARCHAR(5) NOT NULL
);