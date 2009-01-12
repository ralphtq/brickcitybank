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
  `UserName` VARCHAR(45) NULL ,
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
  `idAccount` INT(12) NOT NULL AUTO_INCREMENT,
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
  `term` DATE NOT NULL ,
  `interest` VARCHAR(45) NOT NULL ,
  `credit_line` INT(12) NULL ,
  PRIMARY KEY (`idLoan`) ,
  INDEX `idLoan` (`idLoan` ASC) ,
  CONSTRAINT `idLoan`
    FOREIGN KEY (`idLoan` )
    REFERENCES `mydb`.`Account` (`idAccount` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TransactionType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`TransactionType` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`TransactionType` (
  `id_type` CHAR(1) NOT NULL ,
  `type_name` VARCHAR(12) NOT NULL ,
  PRIMARY KEY (`id_type`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Transaction` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Transaction` (
  `idTransaction` INT NOT NULL AUTO_INCREMENT,
  `type` CHAR(1) NOT NULL ,
  `account1` INT(12) NULL ,
  `account2` INT(12) NULL ,
  `Date` DATE NOT NULL ,
  `Time` TIME NOT NULL ,
  `old_balance1` DOUBLE NULL ,
  `new_balance1` DOUBLE NULL ,
  `old_balance2` DOUBLE NULL ,
  `new_balance2` DOUBLE NULL ,
  PRIMARY KEY (`idTransaction`),
  INDEX `type` (`type` ASC) ,
  INDEX `account1` (`account1` ASC) ,
  INDEX `account2` (`account2` ASC) ,
  CONSTRAINT `type`
    FOREIGN KEY (`type` )
    REFERENCES `mydb`.`TransactionType` (`id_type` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `account1`
    FOREIGN KEY (`account1` )
    REFERENCES `mydb`.`Account` (`idAccount` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
	CONSTRAINT `account2`
    FOREIGN KEY (`account2` )
    REFERENCES `mydb`.`Account` (`idAccount` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
  )
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




/**
* INSERTION
*/

/* User Table*/

INSERT INTO user(LastName, FirstName, UserName, Password, Street, City, State, Zipcode) VALUES('Smith', 'Bob', '1234', 'password', '1 Random Street', 'Nowhere', 'NY', '12345');
INSERT INTO user(LastName, FirstName, UserName, Password, Street, City, State, Zipcode) VALUES('Turner', 'Joe', '2345', 'password1', '234 It Street', 'Somewhere', 'NV', '12384');
INSERT INTO user(LastName, FirstName, UserName, Password, Street, City, State, Zipcode) VALUES('Hanks', 'Tom', '2565', 'password4', '2 LA Street', 'Los Angeles', 'CA', '1284');
INSERT INTO user(LastName, FirstName, UserName, Password, Street, City, State, Zipcode) VALUES('Farmer', 'Mylene', '2687', 'pass7', '13 paradize Street', 'New York City', 'NY', '10023');

/*Account Table */
INSERT INTO Account(Balance) VALUES('45679');
INSERT INTO Account(Balance) VALUES('78');
INSERT INTO Account(Balance) VALUES('500000');
INSERT INTO Account(Balance) VALUES('567');
INSERT INTO Account(Balance) VALUES('6555555');
/*loans*/
INSERT INTO Account(Balance) VALUES('10000');
INSERT INTO Account(Balance) VALUES('20000');

/* Credit_card Table */
INSERT INTO Credit_card(idCredit_card,pin,expiration_date) VALUES('3457','3457','2009-01-08');
INSERT INTO Credit_card(idCredit_card,pin,expiration_date) VALUES('3450','1257','2010-01-08');
INSERT INTO Credit_card(idCredit_card,pin,expiration_date) VALUES('6574','3887','2010-01-07');
INSERT INTO Credit_card(idCredit_card,pin,expiration_date) VALUES('0097','1234','2009-01-07');
INSERT INTO Credit_card(idCredit_card,pin,expiration_date) VALUES('0007','1784','2009-01-09');




/*User_Accounts Table*/
INSERT INTO User_Accounts(account_number,user_id,card_number) VALUES('1','1','3457');
INSERT INTO User_Accounts(account_number,user_id,card_number) VALUES('1','2','3450');
INSERT INTO User_Accounts(account_number,user_id,card_number) VALUES('2','3','6574');
INSERT INTO User_Accounts(account_number,user_id,card_number) VALUES('3','4','0097');
INSERT INTO User_Accounts(account_number,user_id,card_number) VALUES('2','1','0007');
INSERT INTO User_Accounts(account_number,user_id) VALUES('4','3');
INSERT INTO User_Accounts(account_number,user_id) VALUES('5','4');
INSERT INTO User_Accounts(account_number,user_id) VALUES('6','2');
INSERT INTO User_Accounts(account_number,user_id) VALUES('7','1');


/* Checking Table*/
INSERT INTO Checking(idChecking) VALUES('4');
INSERT INTO Checking(idChecking) VALUES('5');

/*Saving Table*/
INSERT INTO Saving(idSaving,interest) VALUES('1','4');
INSERT INTO Saving(idSaving,interest) VALUES('2','4');
INSERT INTO Saving(idSaving,interest) VALUES('3','4');

/*Loan Table*/
INSERT INTO Loan(idLoan,term, interest, credit_line) VALUES('6','2015-01-08','15','15000');
INSERT INTO Loan(idLoan,  term, interest, credit_line) VALUES('7','2020-01-08','15','25000');


/* TransactionType Table */
INSERT INTO TransactionType(id_type,type_name) VALUES('W','Withdrawal');
INSERT INTO TransactionType(id_type,type_name) VALUES('D','Deposit');
INSERT INTO TransactionType(id_type,type_name) VALUES('T','Transfert');

/*Transactions Table */
INSERT INTO Transaction(type, account1, Date, Time,old_balance1, new_balance1) VALUES('W','1','2008-01-08', '12:09:56','45699','45679');
INSERT INTO Transaction(type, account1, account2, Date, Time,old_balance1, new_balance1,old_balance2,new_balance2) VALUES('T','1','2','2008-01-07','12:09:56','45699','45679','679','699');
