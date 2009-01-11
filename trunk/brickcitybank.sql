SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `mydb`;

-- -----------------------------------------------------
-- Table `mydb`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`User` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`User` (
  `idUser` INT NOT NULL AUTO_INCREMENT ,
  `LastName` VARCHAR(45) NOT NULL ,
  `FirstName` VARCHAR(45) NOT NULL ,
  `Pin` VARCHAR(45) NULL ,
  `Password` VARCHAR(45) NOT NULL ,
  `Street` VARCHAR(40) NULL ,
  `City` VARCHAR(45) NULL ,
  `State` VARCHAR(45) NULL ,
  `Zipcode` VARCHAR(10) NULL ,
  PRIMARY KEY (`idUser`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Account` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Account` (
  `idAccount` INT(12) NOT NULL ,
  `Balance` DOUBLE NOT NULL ,
  PRIMARY KEY (`idAccount`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Checking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Checking` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Checking` (
  `idChecking` INT(12) NOT NULL ,
  PRIMARY KEY (`idChecking`) ,
  INDEX `idCheking` (`idChecking` ASC) ,
  CONSTRAINT `idCheking`
    FOREIGN KEY (`idChecking` )
    REFERENCES `mydb`.`Account` (`idAccount` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Saving`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Saving` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Saving` (
  `idSaving` INT(12) NOT NULL ,
  `interest` DOUBLE NOT NULL ,
  PRIMARY KEY (`idSaving`) ,
  INDEX `idSaving` (`idSaving` ASC) ,
  CONSTRAINT `idSaving`
    FOREIGN KEY (`idSaving` )
    REFERENCES `mydb`.`Account` (`idAccount` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Loan`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Loan` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Loan` (
  `idLoan` INT(12) NOT NULL ,
  `type` VARCHAR(45) NOT NULL ,
  `term` DATE NOT NULL ,
  `interst` VARCHAR(45) NOT NULL ,
  `credit_line` VARCHAR(45) NULL ,
  PRIMARY KEY (`idLoan`) ,
  INDEX `idLoan` (`idLoan` ASC) ,
  CONSTRAINT `idLoan`
    FOREIGN KEY (`idLoan` )
    REFERENCES `mydb`.`Account` (`idAccount` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Transaction` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Transaction` (
  `idTransaction` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL ,
  `account1` INT(12) NULL ,
  `account2` INT(12) NULL ,
  `Date` DATE NOT NULL ,
  `Time` TIME NOT NULL ,
  `old_balance` DOUBLE NULL ,
  `new_balance` DOUBLE NULL ,
  PRIMARY KEY (`idTransaction`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Credit_card`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Credit_card` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Credit_card` (
  `idCredit_card` INT NOT NULL ,
  `pin` INT NOT NULL ,
  `expiration_date` DATE NULL ,
  PRIMARY KEY (`idCredit_card`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`User_Accounts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`User_Accounts` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`User_Accounts` (
  `account_number` INT NOT NULL ,
  `user_id` INT NOT NULL ,
  `card_number` INT NULL ,
  PRIMARY KEY (`account_number`, `user_id`) ,
  INDEX `account_number` (`account_number` ASC) ,
  INDEX `user_id` (`user_id` ASC) ,
  INDEX `card_number` (`card_number` ASC) ,
  CONSTRAINT `account_number`
    FOREIGN KEY (`account_number` )
    REFERENCES `mydb`.`Account` (`idAccount` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id` )
    REFERENCES `mydb`.`User` (`idUser` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `card_number`
    FOREIGN KEY (`card_number` )
    REFERENCES `mydb`.`Credit_card` (`idCredit_card` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;






INSERT INTO user(LastName, FirstName, Pin, Password, Street, City, State, Zipcode) VALUES('Smith', 'Bob', '1234', 'password', '1 Random Street', 'Nowhere', 'NY', '12345');
INSERT INTO user(LastName, FirstName, Pin, Password, Street, City, State, Zipcode) VALUES('Turner', 'Joe', '2345', 'password1', '234 It Street', 'Somewhere', 'NV', '12384');








