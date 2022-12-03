-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema tateti
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tateti
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tateti` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `tateti` ;

-- -----------------------------------------------------
-- Table `tateti`.`historial`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tateti`.`historial` (
  `id_resultado` INT NOT NULL AUTO_INCREMENT,
  `fecha_comienzo` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_fin` TIMESTAMP NOT NULL,
  `jugador_nombre` VARCHAR(45) NOT NULL,
  `ganador` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_resultado`))
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `tateti`.`idioma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tateti`.`idioma` (
  `id` INT NOT NULL,
  `descripcion` VARCHAR(250) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `tateti`.`mensajeidioma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tateti`.`mensajeidioma` (
  `id_mensaje` INT NOT NULL,
  `id_idioma` INT NOT NULL,
  `descripcion` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`id_mensaje`, `id_idioma`),
  INDEX `id_idx` (`id_idioma` ASC) VISIBLE,
  CONSTRAINT `id`
    FOREIGN KEY (`id_idioma`)
    REFERENCES `tateti`.`idioma` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
