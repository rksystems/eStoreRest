drop table store;
CREATE TABLE `estore`.`store` (
  `storeId` INT NOT NULL AUTO_INCREMENT,
  `storeName` VARCHAR(45) NOT NULL,
  `createdDate` DATETIME NULL,
  `storeLogoPath` VARCHAR(45) NULL,
  PRIMARY KEY (`storeId`));

drop table address;

CREATE TABLE `estore`.`address` (
  `addressId` INT NOT NULL AUTO_INCREMENT,
  `nickName` VARCHAR(20) NULL,
  `addressType` VARCHAR(1) NOT NULL,
  `billingCode` INT NULL,
  `status` VARCHAR(1) NOT NULL,
  `personTitle` VARCHAR(10) NULL,
  `firstName` VARCHAR(50) NOT NULL,
  `lastName` VARCHAR(50) NOT NULL,
  `addrLine1` VARCHAR(45) NOT NULL,
  `addrLine2` VARCHAR(45) NULL,
  `addrLine3` VARCHAR(45) NULL,
  `city` VARCHAR(25) NOT NULL,
  `state` VARCHAR(25) NOT NULL,
  `country` VARCHAR(25) NOT NULL,
  `pinCode` INT(6) NOT NULL,
  `email1` VARCHAR(45) NULL,
  `email2` VARCHAR(45) NULL,
  `phoneNo1` VARCHAR(15) NULL,
  `phoneNo2` VARCHAR(15) NULL,
  `mobilePhoneNo1` VARCHAR(15) NULL,
  `mobilePhoneNo2` VARCHAR(15) NULL,
  `taxCode` VARCHAR(10) NULL,
  `memberId` INT NULL,
  PRIMARY KEY (`addressId`),
  INDEX `AddrStoreFK_idx` (`memberId` ASC) VISIBLE,
  CONSTRAINT `AddrStoreFK`
    FOREIGN KEY (`memberId`)
    REFERENCES `estore`.`store` (`storeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
