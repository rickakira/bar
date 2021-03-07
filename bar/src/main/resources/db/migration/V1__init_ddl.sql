-- CREATE
CREATE TABLE TIPO_PRODUTO(
    ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    DESCRICAO VARCHAR(100) NOT NULL
) ENGINE = innodb;

CREATE TABLE MARCA(
    ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    DESCRICAO VARCHAR(100) NOT NULL
) ENGINE = innodb;

CREATE TABLE PRODUTO(
    ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    DESCRICAO VARCHAR(100) NOT NULL,
    ID_TIPO_PRODUTO INT,
    ID_MARCA INT,
    FOREIGN KEY (ID_TIPO_PRODUTO) REFERENCES TIPO_PRODUTO(ID),
    FOREIGN KEY (ID_MARCA) REFERENCES MARCA(ID)
) ENGINE = innodb;

CREATE TABLE PRECO_PRODUTO(
    ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ID_PRODUTO INT,
    PRECO_CUSTO DECIMAL(10,2) NOT NULL,
    PORCENTAGEM_LUCRO INT,
    PRECO_VENDA DECIMAL(10,2),
    FOREIGN KEY (ID_PRODUTO) REFERENCES PRODUTO(ID)
) ENGINE = innodb;