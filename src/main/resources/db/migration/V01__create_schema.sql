-- -----------------------------------------------------
-- Schema restaurante
-- -----------------------------------------------------
USE `restaurante` ;

-- -----------------------------------------------------
-- Table `categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `categoria` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(25) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4;

-- -----------------------------------------------------
-- Table `item_cardapio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `item_cardapio` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `valor_vigente` DECIMAL(10,2) NOT NULL,
  `nome` VARCHAR(145) NOT NULL,
  `descricao` VARCHAR(145) NULL DEFAULT NULL,
  `disponivel` TINYINT NULL DEFAULT 1,
  `categoria_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_item_cardapio_categoria1`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `categoria` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4;

CREATE INDEX `fk_item_cardapio_categoria1_idx` ON `item_cardapio` (`categoria_id` ASC) VISIBLE;

-- -----------------------------------------------------
-- Table `pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pedido` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `total` DECIMAL(10,2) NULL DEFAULT '0.00',
  `mesa` INT(11) NULL DEFAULT '0',
  `estado` VARCHAR(15) NULL DEFAULT 'ABERTO', -- AGUARDANDO, FECHADO, CANCELADO
  `data_pedido` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `obs` VARCHAR(80) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4;

-- -----------------------------------------------------
-- Table `item_pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `item_pedido` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `qtd` INT(11) NOT NULL,
  `valor_unit` DECIMAL(10,2) NULL DEFAULT NULL,
  `estado` VARCHAR(15) NULL DEFAULT 'AGUARDANDO', -- EM PREPARO, PRONTO, ENTREGUE, CANCELADO
  `data_pedido` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `data_entregue` DATETIME NULL DEFAULT NULL,
  `pedido_id` INT(11) NOT NULL,
  `item_cardapio_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_item_pedido1`
    FOREIGN KEY (`pedido_id`)
    REFERENCES `pedido` (`id`),
  CONSTRAINT `fk_item_pedido_item_cardapio1`
    FOREIGN KEY (`item_cardapio_id`)
    REFERENCES `item_cardapio` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4;

CREATE INDEX `fk_item_pedido1_idx` ON `item_pedido` (`pedido_id` ASC) VISIBLE;

CREATE INDEX `fk_item_pedido_item_cardapio1_idx` ON `item_pedido` (`item_cardapio_id` ASC) VISIBLE;
