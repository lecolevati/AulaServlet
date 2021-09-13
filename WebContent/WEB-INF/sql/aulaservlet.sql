CREATE DATABASE aulaservlet
GO
USE aulaservlet
GO
CREATE TABLE cliente (
id			INT				NOT NULL,
nome		VARCHAR(100)	NOT NULL,
telefone	CHAR(11)		NOT NULL
PRIMARY KEY (id))
GO
CREATE TABLE produto (
id			INT				NOT NULL,
nome		VARCHAR(100)	NOT NULL,
valor		DECIMAL(7,2)	NULL
PRIMARY KEY (id))
GO
CREATE TABLE compra (
codigo		INT				NOT NULL,
idCliente	INT				NOT NULL,
idProduto	INT				NOT NULL,
qtd			INT				NOT NULL,
dt_compra	DATE			NOT NULL
PRIMARY KEY (codigo, idCliente, idProduto, dt_compra)
FOREIGN KEY (idCliente) REFERENCES cliente (id),
FOREIGN KEY (idProduto) REFERENCES produto (id))

CREATE PROCEDURE sp_iud_cliente (@cod CHAR(1), @id INT, 
	@nome VARCHAR(100), @telefone CHAR(11), @saida VARCHAR(50) OUTPUT)
AS
	IF (UPPER(@cod) = 'I')
	BEGIN
		INSERT INTO cliente VALUES
		(@id, @nome, @telefone)
		SET @saida = 'Cliente inserido com sucesso'
	END
	ELSE
	BEGIN
		IF (UPPER(@cod) = 'U')
		BEGIN
			UPDATE cliente 
			SET nome = @nome, telefone = @telefone
			WHERE id = @id
			SET @saida = 'Cliente atualizado com sucesso'
		END
		ELSE
		BEGIN
			IF (UPPER(@cod) = 'D')
			BEGIN
				DELETE cliente 
				WHERE id = @id
				SET @saida = 'Cliente excluído com sucesso'
			END
			ELSE
			BEGIN
				RAISERROR('Código inválido', 16, 1)
			END
		END
	END

CREATE PROCEDURE sp_iud_produto (@cod CHAR(1), @id INT, 
	@nome VARCHAR(100), @valor DECIMAL(7,2), @saida VARCHAR(50) OUTPUT)
AS
	IF (UPPER(@cod) = 'I')
	BEGIN
		INSERT INTO produto VALUES
		(@id, @nome, @valor)
		SET @saida = 'Produto inserido com sucesso'
	END
	ELSE
	BEGIN
		IF (UPPER(@cod) = 'U')
		BEGIN
			UPDATE produto 
			SET nome = @nome, valor = @valor
			WHERE id = @id
			SET @saida = 'Produto atualizado com sucesso'
		END
		ELSE
		BEGIN
			IF (UPPER(@cod) = 'D')
			BEGIN
				DELETE cliente 
				WHERE id = @id
				SET @saida = 'Produto excluído com sucesso'
			END
			ELSE
			BEGIN
				RAISERROR('Código inválido', 16, 1)
			END
		END
	END

SELECT * FROM cliente
SELECT * FROM produto

SELECT id, nome,
	'('+SUBSTRING(telefone,1,2)+')'+SUBSTRING(telefone,3,5)+'-'
	+SUBSTRING(telefone,8,4) AS telefone 
FROM cliente

SELECT com.codigo, cli.id AS idCliente, cli.nome AS nomeCliente, 
	cli.telefone, p.id AS idProduto, p.nome AS nomeProduto,
	p.valor, com.qtd, p.valor * com.qtd AS vl_total, 
	CONVERT(CHAR(10), com.dt_compra, 103) AS dt_compra
FROM cliente cli, produto p, compra com
WHERE cli.id = com.idCliente
	AND p.id = com.idProduto
	AND com.codigo = 100001