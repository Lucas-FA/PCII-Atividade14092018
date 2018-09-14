-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 31-Ago-2018 às 19:39
-- Versão do servidor: 5.7.17
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cadastro`
--
CREATE DATABASE IF NOT EXISTS `cadastro` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `cadastro`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `idcliente` int(3) NOT NULL,
  `nome` varchar(150) NOT NULL,
  `sexo` char(1) NOT NULL,
  `endereco` varchar(200) NOT NULL,
  `telefone` int(10) NOT NULL,
  `email` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`idcliente`, `nome`, `sexo`, `endereco`, `telefone`, `email`) VALUES
(1, 'João', 'M', 'Rua Fk', 29381432, 'joao@email.com');

-- --------------------------------------------------------

--
-- Estrutura da tabela `hardware`
--

CREATE TABLE `hardware` (
  `idhardware` int(3) NOT NULL,
  `descricao` varchar(300) NOT NULL,
  `preco` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `venda`
--

CREATE TABLE `venda` (
  `idvenda` int(3) NOT NULL,
  `idcliente` int(3) NOT NULL,
  `data` varchar(15) NOT NULL,
  `vlrtotal` float NOT NULL,
  `desconto` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `venda`
--

INSERT INTO `venda` (`idvenda`, `idcliente`, `data`, `vlrtotal`, `desconto`) VALUES
(2, 1, '13/04/2018', 90, 10),
(3, 1, '13/04/2018', 90, 10);

-- --------------------------------------------------------

--
-- Estrutura da tabela `vendaitens`
--

CREATE TABLE `vendaitens` (
  `iditem` int(3) NOT NULL,
  `idvenda` int(3) NOT NULL,
  `idhardware` int(3) NOT NULL,
  `qtde` int(3) NOT NULL,
  `totalitem` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idcliente`);

--
-- Indexes for table `hardware`
--
ALTER TABLE `hardware`
  ADD PRIMARY KEY (`idhardware`);

--
-- Indexes for table `venda`
--
ALTER TABLE `venda`
  ADD PRIMARY KEY (`idvenda`),
  ADD KEY `idcliente` (`idcliente`);

--
-- Indexes for table `vendaitens`
--
ALTER TABLE `vendaitens`
  ADD PRIMARY KEY (`iditem`),
  ADD KEY `idvenda` (`idvenda`),
  ADD KEY `idhardware` (`idhardware`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `idcliente` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `hardware`
--
ALTER TABLE `hardware`
  MODIFY `idhardware` int(3) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `venda`
--
ALTER TABLE `venda`
  MODIFY `idvenda` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `vendaitens`
--
ALTER TABLE `vendaitens`
  MODIFY `iditem` int(3) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `venda`
--
ALTER TABLE `venda`
  ADD CONSTRAINT `fk_idcliente` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`idcliente`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `vendaitens`
--
ALTER TABLE `vendaitens`
  ADD CONSTRAINT `fk_idhardware` FOREIGN KEY (`idhardware`) REFERENCES `hardware` (`idhardware`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_idvenda` FOREIGN KEY (`idvenda`) REFERENCES `venda` (`idvenda`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
