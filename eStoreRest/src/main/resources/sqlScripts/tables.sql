drop table store;
CREATE TABLE `estore`.`store` (
  `storeId` INT NOT NULL AUTO_INCREMENT,
  `storeName` VARCHAR(45) NOT NULL,
  `createdDate` DATETIME NULL,
  `storeLogoPath` VARCHAR(45) NULL,
  PRIMARY KEY (`storeId`));

drop table `estore`.`users`;

CREATE TABLE `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  `active` varchar(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userName_UNIQUE` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


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
  `city` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  `pinCode` INT(6) NOT NULL,
  `email1` VARCHAR(45) NULL,
  `email2` VARCHAR(45) NULL,
  `phoneNo1` VARCHAR(15) NULL,
  `phoneNo2` VARCHAR(15) NULL,
  `mobilePhoneNo1` VARCHAR(15) NULL,
  `mobilePhoneNo2` VARCHAR(15) NULL,
  `taxCode` VARCHAR(10) NULL,
  `storeId` INT NULL,
  `userId` INT NULL,
  PRIMARY KEY (`addressId`),
  INDEX `storeIdFK_idx` (`storeId` ASC) VISIBLE,
  INDEX `userIdFK_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `storeIdFK`
    FOREIGN KEY (`storeId`)
    REFERENCES `estore`.`store` (`storeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `userIdFK`
    FOREIGN KEY (`userId`)
    REFERENCES `estore`.`users` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    
    drop table productscatalog;
    CREATE TABLE `estore`.`productscatalog` (
  `productID` INT NOT NULL AUTO_INCREMENT,
  `productName` VARCHAR(45) NOT NULL,
  `productShortDesc` VARCHAR(100) NULL,
  `productLongDesc` VARCHAR(4000) NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `discount` DECIMAL(10,2) NULL,
  `imageLocation` VARCHAR(500) NULL,
  `rating` INT NULL,
  `quantity` INT NULL,
  `category` VARCHAR(45) NULL,
  `subCategory` VARCHAR(45) NULL,
  `active` VARCHAR(1) NULL,
  PRIMARY KEY (`productID`))
COMMENT = 'This table stores the information about the Products sold by Business.';
    
    
    

