-- Cria o banco
CREATE DATABASE `petchoperp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;

-- Cria a tabela de produto
CREATE TABLE `produto` (
  `idproduto` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(20) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `descricao` varchar(200) DEFAULT NULL,
  `peso` double DEFAULT NULL,
  `preco` double DEFAULT NULL,
  `dtCadastro` timestamp NULL DEFAULT NULL,
  `emEstoque` tinyint(4) DEFAULT NULL,
  `desativado` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idproduto`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;