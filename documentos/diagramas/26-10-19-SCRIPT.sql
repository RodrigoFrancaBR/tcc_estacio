-- MySQL Script generated by MySQL Workbench
-- Sat Oct 26 13:42:08 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema DB_INVICTO
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema DB_INVICTO
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `DB_INVICTO` DEFAULT CHARACTER SET utf8 ;
USE `DB_INVICTO` ;

-- -----------------------------------------------------
-- Table `DB_INVICTO`.`TB_UNIDADE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB_INVICTO`.`TB_UNIDADE` (
  `id_unidade` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(20) NOT NULL,
  `endereco` VARCHAR(60) NOT NULL COMMENT 'Endereço não pode ser repetido.',
  `situacao` TINYINT NOT NULL DEFAULT 1 COMMENT 'USADO PARA EXCLUSÃO LÓGICA - 0 desativada 1 ativa',
  PRIMARY KEY (`id_unidade`),
  UNIQUE INDEX `endereco_UNIQUE` (`endereco` ASC) ,
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DB_INVICTO`.`TB_TURMA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB_INVICTO`.`TB_TURMA` (
  `id_turma` BIGINT NOT NULL AUTO_INCREMENT,
  `unidade_id` BIGINT NOT NULL,
  `nome` VARCHAR(20) NOT NULL,
  `situacao` TINYINT NOT NULL DEFAULT 1 COMMENT 'USADO PARA EXCLUSÃO LÓGICA - 0 desativada 1 ativa',
  PRIMARY KEY (`id_turma`),
  INDEX `FK_TB_TURMA_TB_UNIDADE_IDX` (`unidade_id` ASC) ,
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC, `unidade_id` ASC) ,
  CONSTRAINT `fk_TB_TURMA_TB_UNIDADE1`
    FOREIGN KEY (`unidade_id`)
    REFERENCES `DB_INVICTO`.`TB_UNIDADE` (`id_unidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DB_INVICTO`.`TB_ALUNO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB_INVICTO`.`TB_ALUNO` (
  `id_aluno` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(40) NOT NULL,
  `cpf` VARCHAR(14) NOT NULL COMMENT '111.111.111-11',
  `rg` VARCHAR(45) NOT NULL COMMENT '99.999.999-9',
  `orgao_exp` VARCHAR(20) NOT NULL,
  `uf_rg` VARCHAR(2) NOT NULL,
  `data_nascimento` DATE NOT NULL,
  `sexo` TINYINT NOT NULL COMMENT '0 FEMININO 1 MASCULINO',
  `celular` VARCHAR(15) NOT NULL COMMENT '99-99999-9999',
  `residencial` VARCHAR(14) NOT NULL COMMENT '99-9999-9999',
  `email` VARCHAR(60) NOT NULL,
  `cep` VARCHAR(9) NOT NULL COMMENT '99999-999',
  `endereco` VARCHAR(60) NOT NULL,
  `bairro` VARCHAR(30) NOT NULL,
  `cidade` VARCHAR(30) NOT NULL,
  `estado` VARCHAR(2) NOT NULL,
  `pai` VARCHAR(40) NOT NULL,
  `mae` VARCHAR(40) NOT NULL,
  `situacao` TINYINT NOT NULL DEFAULT 1 COMMENT 'USADO PARA EXCLUSÃO LÓGICA - 0 desativado 1 ativo',
  PRIMARY KEY (`id_aluno`),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) ,
  UNIQUE INDEX `rg_UNIQUE` (`rg` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DB_INVICTO`.`TB_CONTRATO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB_INVICTO`.`TB_CONTRATO` (
  `id_contrato` BIGINT NOT NULL AUTO_INCREMENT,
  `aluno_id` BIGINT NOT NULL,
  `turma_id` BIGINT NOT NULL,
  `taxa_matricula` DOUBLE NOT NULL,
  `valor_curso` DOUBLE NOT NULL,
  `desconto_curso` DOUBLE(3,2) NOT NULL COMMENT 'DE O ATÉ 100 %',
  `qtd_parcelas_curso` TINYINT NOT NULL COMMENT '1 =  AVISTA, x = NUMERO DE PARCELAS \nobs ACIMA DE 2 JÁ É PARCELADO',
  `valor_material` DOUBLE NOT NULL,
  `qtd_parcelas_material` TINYINT NOT NULL COMMENT '1 =  AVISTA, x = NUMERO DE PARCELAS \nobs ACIMA DE 2 JÁ É PARCELADO',
  `dia_vencimento` TINYINT NOT NULL COMMENT 'APENAS O DIA\n1,5,10 etc.',
  `forma_pagamento` TINYINT NOT NULL COMMENT '1= DINHEIRO, 2= CARTAO-CREDITO, 3= CARTAO-DEBITO, 4= CHEQUE',
  `data_matricula` DATE NOT NULL,
  `situacao` TINYINT NOT NULL DEFAULT 1 COMMENT 'USADO PARA EXCLUSÃO LÓGICA -\n0 desativado 1 ativo 2 EM ACORDO 3 ENCERRADO 4 SIMULAÇÃO',
  `matricula` VARCHAR(11) NOT NULL COMMENT 'cpf+turma+data_matricula\n20191111001',
  `condicao_contrato` TINYINT NOT NULL,
  PRIMARY KEY (`id_contrato`),
  INDEX `FK_TB_CONTRATO_TB_ALUNO_IDX` (`aluno_id` ASC) ,
  UNIQUE INDEX `matricula_UNIQUE` (`matricula` ASC) ,
  INDEX `FK_TB_CONTRATO_TB_TURMA_IDX` (`turma_id` ASC) ,
  UNIQUE INDEX `aluno_id_UNIQUE` (`aluno_id` ASC, `turma_id` ASC) ,
  UNIQUE INDEX `turma_id_UNIQUE` (`turma_id` ASC, `aluno_id` ASC) ,
  CONSTRAINT `fk_TB_CONTRATO_TB_ALUNO`
    FOREIGN KEY (`aluno_id`)
    REFERENCES `DB_INVICTO`.`TB_ALUNO` (`id_aluno`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_TB_CONTRATO_TB_TURMA`
    FOREIGN KEY (`turma_id`)
    REFERENCES `DB_INVICTO`.`TB_TURMA` (`id_turma`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
ROW_FORMAT = REDUNDANT;


-- -----------------------------------------------------
-- Table `DB_INVICTO`.`TB_PARCELA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB_INVICTO`.`TB_PARCELA` (
  `id_parcela` BIGINT NOT NULL AUTO_INCREMENT,
  `contrato_id` BIGINT NOT NULL,
  `data_vencimento` DATE NOT NULL,
  `valor_pago` DECIMAL(7,2) NULL COMMENT '\'VALOR PAGO NESTA DATA\'',
  `data_pagamento` DATE NULL,
  `valor_parcela_curso` DECIMAL(7,2) NOT NULL COMMENT 'VALOR COM DESCONTO E PARCELADO',
  `valor_parcela_material` DECIMAL(7,2) NOT NULL COMMENT 'VALOR PARCELADO',
  `valor_total_parcela` DECIMAL(7,2) NOT NULL COMMENT 'PARCELA_CURSO+PARCELA_MATERIAL+TAXA_MATRICULA',
  `situacao` TINYINT NOT NULL COMMENT '1= PAGO, 2 = A VENCER, 3 = ATRASADO 4 = CANCELADO POR ACORDO',
  PRIMARY KEY (`id_parcela`),
  UNIQUE INDEX `contrato_id_UNIQUE` (`contrato_id` ASC, `id_parcela` ASC) ,
  CONSTRAINT `fk_PARCELAS_MATRICULA1`
    FOREIGN KEY (`contrato_id`)
    REFERENCES `DB_INVICTO`.`TB_CONTRATO` (`id_contrato`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
