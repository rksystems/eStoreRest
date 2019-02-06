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

    
drop table `estore`.`productscatalog`;
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
    
drop table `estore`.`orders`;
    
CREATE TABLE `estore`.`orders` (
  `orderId` INT NOT NULL AUTO_INCREMENT,
  `total` DECIMAL(20,5) NOT NULL,
  `tax` DECIMAL(20,5) NOT NULL,
  `shipping` DECIMAL(20,5) NOT NULL,
  `description` VARCHAR(254) NULL COMMENT '\'A mnemonic description of the order, entered by the customer, suitable for display to the customer.\'',
  `storeId` VARCHAR(45) NULL,
  `currency` VARCHAR(10) NOT NULL DEFAULT 'INR',
  `status` VARCHAR(15) NULL COMMENT 'Order Placed, Cancelled, Shipped, Delivered, Completed',
  `userId` INT NULL COMMENT 'The customer that placed the order.',
  PRIMARY KEY (`orderId`),
  INDEX `userIdFK_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `orders_userId_FK`
    FOREIGN KEY (`userId`)
    REFERENCES `estore`.`users` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'Orders of the customer';

drop table `estore`.`orderitems`;
CREATE TABLE `estore`.`orderitems` (
  `orderItemId` INT NOT NULL AUTO_INCREMENT,
  `orderId` INT NULL COMMENT 'The order of which this order item is part',
  `productCatalogId` INT NULL,
  `orderItemName` VARCHAR(65) NOT NULL,
  `userId` INT NULL,
  `addressId` INT NULL COMMENT 'The shipping address, if any, for this order item',
  `price` DECIMAL(20,5) NOT NULL,
  `quantity` INT NOT NULL,
  `totalPrice` DECIMAL(20,5) NOT NULL,
  `tax` DECIMAL(20,5) NOT NULL,
  `adjustments` DECIMAL(20,5) NOT NULL,
  `shipping` DECIMAL(20,5) NOT NULL,
  `status` VARCHAR(15) NULL,
  PRIMARY KEY (`orderItemId`),
  INDEX `orderItem_order_fk_idx` (`orderId` ASC) VISIBLE,
  INDEX `orderItem_address_fk_idx` (`addressId` ASC) VISIBLE,
  INDEX `orderItem_user_fk_idx` (`userId` ASC) VISIBLE,
  INDEX `orderItem_productcatalog_fk_idx` (`productCatalogId` ASC) VISIBLE,
  CONSTRAINT `orderItem_order_fk`
    FOREIGN KEY (`orderId`)
    REFERENCES `estore`.`orders` (`orderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `orderItem_address_fk`
    FOREIGN KEY (`addressId`)
    REFERENCES `estore`.`address` (`addressId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `orderItem_user_fk`
    FOREIGN KEY (`userId`)
    REFERENCES `estore`.`users` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `orderItem_productcatalog_fk`
    FOREIGN KEY (`productCatalogId`)
    REFERENCES `estore`.`productscatalog` (`productID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

