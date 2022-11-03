CREATE SEQUENCE IF NOT EXISTS uf_sequence;
CREATE SEQUENCE IF NOT EXISTS municipio_sequence;
CREATE SEQUENCE IF NOT EXISTS pessoa_sequence;
CREATE SEQUENCE IF NOT EXISTS endereco_sequence;
CREATE SEQUENCE IF NOT EXISTS bairro_sequence;

CREATE TABLE IF NOT EXISTS uf (

    codigo INT NOT NULL,
    sigla VARCHAR(3) NOT NULL,
    nome VARCHAR(60) NOT NULL,
    status INT NOT NULL,

    CONSTRAINT pk_uf PRIMARY KEY (codigo)

);

CREATE TABLE IF NOT EXISTS municipio (

    codigo INT NOT NULL,
    nome VARCHAR(200) NOT NULL,
    status INT NOT NULL,
    codigo_uf INT NOT NULL,

    CONSTRAINT pk_municipio PRIMARY KEY (codigo),
    CONSTRAINT fk_municipio_uf FOREIGN KEY (codigo_uf) REFERENCES uf (codigo)

);

CREATE TABLE IF NOT EXISTS pessoa (

    codigo INT NOT NULL,
    nome VARCHAR(200) NOT NULL,
    sobrenome VARCHAR(200) NOT NULL,
    idade INT NOT NULL,
    login VARCHAR(50) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    status INT NOT NULL,

    CONSTRAINT pk_pessoa PRIMARY KEY (codigo)

);

CREATE TABLE IF NOT EXISTS bairro (

    codigo INT NOT NULL,
    nome VARCHAR(200) NOT NULL,
    status INT NOT NULL,
    codigo_municipio INT NOT NULL,

    CONSTRAINT pk_bairro PRIMARY KEY (codigo),
    CONSTRAINT fk_bairro_municipio FOREIGN KEY (codigo_municipio) REFERENCES municipio (codigo)

);

CREATE TABLE IF NOT EXISTS endereco (

    codigo INT NOT NULL,
    nome_rua VARCHAR(200) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    complemento VARCHAR(20),
    cep VARCHAR(10) NOT NULL,
    codigo_bairro INT NOT NULL,
    codigo_pessoa INT NOT NULL,

    CONSTRAINT pk_endereco PRIMARY KEY (codigo),
    CONSTRAINT fk_endereco_bairro FOREIGN KEY (codigo_bairro) REFERENCES bairro (codigo),
    CONSTRAINT fk_endereco_pessoa FOREIGN KEY (codigo_pessoa) REFERENCES pessoa (codigo)

);

ALTER TABLE uf ALTER COLUMN codigo SET DEFAULT NEXTVAL('uf_sequence');
ALTER TABLE municipio ALTER COLUMN codigo SET DEFAULT NEXTVAL('municipio_sequence');
ALTER TABLE pessoa ALTER COLUMN codigo SET DEFAULT NEXTVAL('pessoa_sequence');
ALTER TABLE endereco ALTER COLUMN codigo SET DEFAULT NEXTVAL('endereco_sequence');
ALTER TABLE bairro ALTER COLUMN codigo SET DEFAULT NEXTVAL('bairro_sequence');
