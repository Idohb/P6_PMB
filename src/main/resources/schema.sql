-- MySQL Script generated by MySQL Workbench
-- Fri Nov 26 16:16:00 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Person` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Person` (
  `idPerson` INT NOT NULL AUTO_INCREMENT,
  `FirstName` VARCHAR(45) NOT NULL,
  `LastName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPerson`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Transaction_Internal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Transaction_Internal` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Transaction_Internal` (
  `idTransaction` INT NOT NULL AUTO_INCREMENT,
  `Description` TEXT(100) NULL,
  `Amount` DECIMAL(5,2) NULL,
  `TimeTransaction` DATETIME NULL,
  `Crediteur` INT NOT NULL,
  `Debiteur` INT NOT NULL,
  PRIMARY KEY (`idTransaction`),
  INDEX `fk_crediteur_id_person_idx` (`Crediteur` ASC) VISIBLE,
  INDEX `fk_crediteur_id_person_idx1` (`Debiteur` ASC) VISIBLE,
  CONSTRAINT `fk_crediteur_id_person`
    FOREIGN KEY (`Crediteur`)
    REFERENCES `mydb`.`Person` (`idPerson`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dediteur_id_person`
    FOREIGN KEY (`Debiteur`)
    REFERENCES `mydb`.`Person` (`idPerson`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Login`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Login` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Login` (
  `idLogin` INT NOT NULL AUTO_INCREMENT,
  `Email` VARCHAR(100) NOT NULL,
  `Password` VARCHAR(100) NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`idLogin`),
  UNIQUE INDEX `Email_unique_id` (`Email` ASC) INVISIBLE,
  INDEX `fk_user_id_person_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_id_person_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`Person` (`idPerson`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Bank`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Bank` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Bank` (
  `idBanque` INT NOT NULL AUTO_INCREMENT,
  `IBAN` VARCHAR(45) NOT NULL,
  `Mountant` DECIMAL(5,2) NULL,
  `User_id` INT NOT NULL,
  PRIMARY KEY (`idBanque`),
  INDEX `fk_identifiant_idx` (`User_id` ASC) VISIBLE,
  UNIQUE INDEX `Iban_unique_id` (`IBAN` ASC) INVISIBLE,
  CONSTRAINT `fk_identifiant`
    FOREIGN KEY (`User_id`)
    REFERENCES `mydb`.`Person` (`idPerson`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Commission`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Commission` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Commission` (
  `idCommission` INT NOT NULL,
  `Transaction_id` INT NOT NULL,
  `Amount` DECIMAL(5,2) NULL,
  PRIMARY KEY (`idCommission`),
  INDEX `fk_transaction_id_crediteur_id_idx` (`Transaction_id` ASC) VISIBLE,
  CONSTRAINT `fk_transaction_id_crediteur_id`
    FOREIGN KEY (`Transaction_id`)
    REFERENCES `mydb`.`Transaction_Internal` (`Crediteur`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Social_Networks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Social_Networks` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Social_Networks` (
  `idConnexion` INT NOT NULL,
  `ThisPerson` INT NOT NULL,
  `IsRelatedTo` INT NOT NULL,
  PRIMARY KEY (`idConnexion`),
  INDEX `fk_ThisPerson_id_idx` (`ThisPerson` ASC) VISIBLE,
  INDEX `fk_IsRelatedTo_id_idx` (`IsRelatedTo` ASC) VISIBLE,
  CONSTRAINT `fk_ThisPerson_id`
    FOREIGN KEY (`ThisPerson`)
    REFERENCES `mydb`.`Person` (`idPerson`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_IsRelatedTo_id`
    FOREIGN KEY (`IsRelatedTo`)
    REFERENCES `mydb`.`Person` (`idPerson`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Transaction_External`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Transaction_External` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Transaction_External` (
  `idTransaction_external` INT NOT NULL,
  `User_id` INT NOT NULL,
  `Amount` DECIMAL(5,2) NULL,
  `Time_Transaction` DATETIME NULL,
  `Description` TEXT(100) NULL,
  PRIMARY KEY (`idTransaction_external`),
  INDEX `fk_user_id_person_idx` (`User_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_id_person`
    FOREIGN KEY (`User_id`)
    REFERENCES `mydb`.`Person` (`idPerson`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
