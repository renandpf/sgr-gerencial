CREATE TABLE IF NOT EXISTS `sgr_gerencial_database`.`Cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `cpf` varchar(11) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `endereco` varchar(100) DEFAULT NULL,
  `telefone` varchar(45) DEFAULT NULL,
  `statusCadastro` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `sgr_gerencial_database`.`Produto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `descricao` varchar(500) DEFAULT NULL,
  `imagem` text,
  `valor` float NOT NULL,
  `categoriaId` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
